function homePage() {
    return 'Homepage';
}

function bookingPage() {
    return document.getElementById("booking-template").innerHTML;
}

async function bookingRequest(path, options = {}) {
    const response = await fetch(
        "/adventureexperience/bookings" + path, options
    );
    const data = await response.json();

    if (!response.ok) {
        throw new Error(data.error || data.message || "HTTP " + response.status);
    }

    return data;
}

async function setupBookingForm() {
    const get = name => document.getElementById("booking-" + name);
    const formatTime = value => value.slice(11, 16);
    const searchForm = get("search");
    const bookingForm = get("save-form");
    const details = get("details");
    const message = get("message");

    let criteria, price, slots = [], activities = [];

    // Reused for activities, times and employees.
    function fillSelect(name, items, text, value) {
        const select = get(name);
        select.replaceChildren(new Option("Vælg...", ""));

        for (const item of items) {
            select.add(new Option(text(item), value(item)));
        }
    }

    function clearChoices() {
        slots = [];
        bookingForm.hidden = true;
        details.hidden = true;
        details.disabled = true;
        get("confirmation").hidden = true;
        message.textContent = "";
    }

    function setBusy(busy) {
        get("search-fields").disabled = busy;
        get("time").disabled = busy;
        details.disabled = busy || details.hidden;
    }

    // Changing the search invalidates the previous choices.
    searchForm.addEventListener("input", clearChoices);

    // Find available times.
    searchForm.addEventListener("submit", async event => {
        event.preventDefault();
        clearChoices();
        setBusy(true);

        criteria = {
            activityId: Number(get("activity").value),
            date: get("date").value,
            numOfGuests: Number(get("guests").value)
        };

        try {
            const data = await bookingRequest(
                "/available-slots?" + new URLSearchParams(criteria)
            );

            slots = data.slots;
            price = data.price;

            if (!slots.length) {
                message.textContent = data.capacityAvailable
                    ? "Ingen ledige tider."
                    : "Ikke nok udstyr til deltagerantallet.";
                return;
            }

            fillSelect("time", slots,
                slot => formatTime(slot.startTime),
                slot => slot.startTime);

            bookingForm.hidden = false;
        } catch (error) {
            message.textContent = error.message;
        } finally {
            setBusy(false);
        }
    });

    // Show employees and details for the selected time.
    get("time").addEventListener("change", () => {
        const slot = slots.find(s => s.startTime === get("time").value);

        details.hidden = !slot;
        details.disabled = !slot;
        if (!slot) return;

        fillSelect("employee", slot.availableEmployees,
            employee => employee.name,
            employee => employee.employeeId);

        get("end").textContent = formatTime(slot.endTime);
        get("price").textContent = price + " kr.";
    });

    // Save the booking.
    bookingForm.addEventListener("submit", async event => {
        event.preventDefault();

        const activity = activities.find(
            a => a.activityId === criteria.activityId
        );

        const activityName = activity.activityName;

        const input = {
            activityType: activity,
            employee: {
                employeeId: Number(get("employee").value)
            },
            bookingDate: criteria.date,
            startTime: get("time").value,
            numOfGuests: criteria.numOfGuests,
            price: price,
            contactEmail: get("email").value.trim(),
            contactNumber: get("phone").value.trim()
        };

        setBusy(true);

        try {
            const saved = await bookingRequest("", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(input)
            });

            clearChoices();
            get("email").value = "";
            get("phone").value = "";

            get("confirmation").textContent = [
                "Booking #" + saved.bookingId + " er oprettet.",
                activityName + " – " + input.numOfGuests + " deltagere",
                saved.startTime.slice(0, 10) + " kl. "
                + formatTime(saved.startTime)
                + " – " + formatTime(saved.endTime),
                "Medarbejder: " + saved.employeeName,
                "Samlet pris: " + saved.price + " kr."
            ].join("\n");

            get("confirmation").hidden = false;
        } catch (error) {
            clearChoices();
            message.textContent = error.message + " Søg ledige tider igen.";
        } finally {
            setBusy(false);
        }
    });

    // Load activities when the page opens.
    try {
        activities = await bookingRequest("/activities");

        fillSelect("activity", activities,
            activity => activity.activityName,
            activity => activity.activityId);

        get("search-fields").disabled = !activities.length;
        message.textContent = activities.length
            ? ""
            : "Ingen aktiviteter oprettet.";
    } catch (error) {
        message.textContent = error.message;
    }
}

function reservationPage() {
    return 'Reservationer';
}

function equipmentPage() {
    return 'Inventory';
}

function employeePage() {
    return`
        <section>
            <h1>Medarbejdere</h1>
                
                <h2>Managers</h2>
                    <ul>
                        <li id="manager-list">Loading...</li>
                    </ul>
                    
                <h2>Employees</h2>
                    <ul id="employee-list">
                        <li>Loading...</li>
                    </ul>           
        </section>`;
}

function employeeLogin() {
    return`
        <form id="login-form">
            <div class="container">
                <label for="username"><b>Username</b></label>
                <input type="text" id="employeeEmail" placeholder="Enter Employee Email" required>

                <label for="password"><b>Password</b></label>
                <input type="password" id="employeePassword" placeholder="Enter Employee Password" required>

                <p id="login-error" role="alert" hidden></p>
                <button type="submit">Login</button>
            </div>
        </form>
    `;
}

function customerBookingPage() {return 'BOOKING OVERVIEW CUSTOMERS'}
function customerShop() {return 'Overview of snacks and beers'}

const routes = {
    // Homepage and Log-in routing
    "/": {side: homePage, needsLogin: false},
    "/login": {side: employeeLogin, needsLogin: false, onRender: setupLoginForm},

    // Company internal links
        "/booking": {
        side: bookingPage, needsLogin: false, onRender: setupBookingForm},
    "/reservationer": {side: reservationPage, needsLogin: true},
    "/inventar": {side: equipmentPage, needsLogin: true},
    "/employees": {side: employeePage, needsLogin: true},

    // Customer directed links
    "/booking-overview": {side: customerBookingPage, needsLogin: false},
    "/shop": {side: customerShop, needsLogin: false}

};

const LOGIN_URL = "http://localhost:8080";
const isLoggedIn = () => sessionStorage.getItem("session") !== null;

function renderApp(html) {
    document.getElementById("app").innerHTML = html;
}

function handleRoutes() {
    let path = routes[location.pathname] || routes["/"];

    if (path.needsLogin && !isLoggedIn()) {
        history.replaceState(null, "", "/login");
        path = routes["/login"];
    }

    document.getElementById("app").innerHTML = path.side();
    renderApp(path.side());
    path.onRender?.();
}

async function login(employee){
    const response = await fetch(LOGIN_URL + "/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(employee)
    })

    if (!response.ok) {
        throw new Error("HTTP " + response.status)
    }

    return await response.json();
}

function navigate(path) {
    history.pushState(null, "", path); {
        handleRoutes();
    }
}

function setupLoginForm() {
    const error = document.getElementById("login-error");

    document.getElementById("login-form").addEventListener("submit", async (e) => {
        e.preventDefault();
        error.hidden = true;

        try {
            const data = await login({
                employeeEmail: document.getElementById("employeeEmail").value.trim(),
                employeePassword: document.getElementById("employeePassword").value
            });
            sessionStorage.setItem("session", JSON.stringify(data));
            navigate("/booking")
        } catch (err) {
            error.textContent = err.message;
            error.hidden = false;
        }
    });
}

document.addEventListener("click", (e) => {
    if (e.target.matches("[data-link]")) {
        e.preventDefault();

        const href = e.target.getAttribute("href");
        history.pushState(null, "", href);
        handleRoutes();
    }
});

// Employee overview
const EMPLOYEES_URL = "http://localhost:8080";

const managerList = document.getElementById("manager-list");
const regularEmployeeList = document.getElementById("employee-list");

async function fetchAllEmployees() {
    const response = await fetch(EMPLOYEES_URL);

    if (!response.ok) {
        throw new Error("HTTP " + response.status)
    }

    return await response.json();
}

function createEmployeeItem(employee) {
    const li = document.createElement("li");
    li.textContent = employee.name;
    return li;
}

function renderEmployeeList(employees) {
    managerList.innerHTML = "";
    regularEmployeeList.innerHTML = "";

    employees.forEach(employee => {
        const li = createEmployeeItem(employee);

        if (employee.role === "MANAGER") {
            managerList.append(li);
        }

        if (employee.role === "EMPLOYEE") {
            regularEmployeeList.append(li);
        }
    });
}

function showError() {
    console.log(Error);
    managerList.innerHtml = "Failed to load";
    regularEmployeeList.innerHTML = "Failed to load";
}

fetchAllEmployees()
    .then(renderEmployeeList)
    .catch(showError);

window.onpopstate = handleRoutes;
handleRoutes();
