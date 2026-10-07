function homePage() {
    return 'Homepage';
}

function bookingPage() {
    return `<h1>Book en aktivitet</h1>

    <form id="bookingForm" novalidate>

        <label for="activityTypeId">Aktivitet
            <select id="activityTypeId" name="activityTypeId" required>
                <option value="">-- Vælg aktivitet --</option>
            </select>
            <span class="error" data-error="activityTypeId"></span>
        </label>

        <label for="startTime">Starttidspunkt
            <input type="datetime-local" id="startTime" name="startTime" required>
                <span class="error" data-error="startTime"></span>
        </label>

        <label for="numOfGuests">Antal deltagere
            <input type="number" id="numOfGuests" name="numOfGuests" min="1" value="1" required>
                <span class="error" data-error="numOfGuests"></span>
        </label>

        <label for="employeeId">Medarbejder (valgfri)
            <select id="employeeId" name="employeeId">
                <option value="">-- Ingen præference --</option>
            </select>
            <span class="error" data-error="employeeId"></span>
        </label>

        <label for="contactEmail">E-mail
            <input type="email" id="contactEmail" name="contactEmail" required>
                <span class="error" data-error="contactEmail"></span>
        </label>

        <label for="contactNumber">Telefonnummer
            <input type="tel" id="contactNumber" name="contactNumber" required>
                <span class="error" data-error="contactNumber"></span>
        </label>

       <!-- <p>Pris: <strong id="price">–</strong></p> -->

        <button type="submit">Book</button>
    </form>

    <div id="result"></div>`;
}

function reservationPage() {
    return`
        <section>
            <h1>Reservations</h1>
           
            
        
        </section>`;
}

function equipmentPage() {
    return 'Inventory';
}

function employeePage() {
    return`
        <section>
            <h1>Medarbejdere</h1>
                
                <h2>Managers</h2>
                    <ul id="manager-list">
                        <li>Emp</li>
                    </ul>
                    
                <h2>Employees</h2>
                    <ul id="employee-list">
                        <li>Emp</li>
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

function customerShop() {
    return`
        <section>
            <h1>Menu</h1>
            <ul id="product-list">
                <li>Henter produkter...</li>
            </ul>
        </section>`;
}


function employeeShop(){}

const routes = {
    // Homepage and Log-in routing
    "/": {side: homePage, needsLogin: false},
    "/login": {side: employeeLogin, needsLogin: false, onRender: setupLoginForm},

    // Company internal links
    "/booking": {side: bookingPage, needsLogin: false, onRender: renderBooking},
    "/reservationer": {side: reservationPage, needsLogin: true},
    "/inventar": {side: equipmentPage, needsLogin: true},
    "/employees": {side: employeePage, needsLogin: false, onRender: loadEmployees},

    // Customer directed links
    "/booking-overview": {side: customerBookingPage, needsLogin: false},
    "/customerShop": {side: customerShop, needsLogin: false, onRender: loadProducts}
};

// ROUTE HANDLING
function handleRoutes() {
    let path = routes[location.pathname] || routes["/"];

    if (path.needsLogin && !isLoggedIn()) {
        history.replaceState(null, "", "/login");
        path = routes["/login"];
    }

    document.getElementById("app").innerHTML = path.side();
    path.onRender?.();
}

// LOGIN FORM
const isLoggedIn = () => sessionStorage.getItem("session") !== null;

function renderApp(html) {
    document.getElementById("app").innerHTML = html;
}

async function login(employee){
    const response = await fetch("/login", {
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


// EMPLOYEE OVERVIEW
const EMPLOYEES_URL = "/api/employees";

async function fetchAllEmployees() {
    const response = await fetch(EMPLOYEES_URL);

    if (!response.ok) {
        throw new Error("HTTP " + response.status)
    }

    return await response.json();
}

function createEmployeeItem(employee) {
    const li = document.createElement("li");
    li.textContent = employee.employeeName;
    return li;
}

function renderEmployeeList(employees) {
    const managerList = document.getElementById("manager-list");
    const regularEmployeeList = document.getElementById("employee-list");
    managerList.innerHTML = "";
    regularEmployeeList.innerHTML = "";

    employees.forEach(employee => {
        const li = createEmployeeItem(employee);

        const roleType = employee.roleId?.roleName?.toUpperCase();

        if (roleType=== "MANAGER") {
            managerList.append(li);
        }

        if (roleType === "EMPLOYEE") {
            regularEmployeeList.append(li);
        }
    });
}

function showError() {
    console.error(Error);

    const managerList = document.getElementById("manager-list");
    const regularEmployeeList = document.getElementById("employee-list");
    if (managerList) managerList.innerHTML = "Failed to load managers";
    if (regularEmployeeList) regularEmployeeList.innerHTML = "Failed to load employee";
}

function loadEmployees() {
    fetchAllEmployees()
        .then(renderEmployeeList)
        .catch(showError);
}

// CREATE Booking
async function loadBookingForm() {
    const response = await fetch("/api/booking");

    if (!response.ok) {
        throw new Error("Http " + response.status);
    }

    const data = await response.json();

    const activitySelect = document.getElementById("activityTypeId");
    data.activityTypeList.forEach((activityType) => {
        activitySelect.add(new Option(activityType.activityName, activityType.activityId));
    });

    const employeeSelect = document.getElementById("employeeId");
    data.employeeList.forEach((employeeType) => {
        employeeSelect.add(new Option(employeeType.employeeName, employeeType.employeeId));
    });
}

async function createBooking(booking) {
    const result = await fetch("/api/booking", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(booking)
    });

    if (!result.ok) {
        throw new Error("Http: " + result.status);
    }

    return await result.json();
}

async function handleBookingSubmit(e) {
    e.preventDefault();
    const bookingForm = e.target;

    const booking = {
        contactEmail: bookingForm.contactEmail.value,
        contactNumber: bookingForm.contactNumber.value,
        numOfGuests: Number(bookingForm.numOfGuests.value),
        startTime: bookingForm.startTime.value,
        activityTypeId: bookingForm.activityTypeId.value ? Number(bookingForm.activityTypeId.value) : null,
        employeeId: bookingForm.employeeId.value ? Number(bookingForm.employeeId.value) : null
    };

    const result = document.getElementById("result");

    try {
        const saved = await createBooking(booking);
        result.className = "success";
        result.textContent = "Booking oprettet (nr. " + saved.bookingId + ", pris " + saved.price + " kr.)";
        result.style.display = "block";
        bookingForm.reset();
    } catch (err) {
        result.className = "failure";
        result.textContent = "Kunne ikke oprette booking: " + err.message;
        result.style.display = "block";
        console.error(err);
    }
}

function renderBooking() {
    const bookingForm = document.getElementById("bookingForm");
    loadBookingForm();

    bookingForm.removeEventListener("submit", handleBookingSubmit);
    bookingForm.addEventListener("submit", handleBookingSubmit);
}

//SHOP
async function loadProducts() {
    const productList = document.getElementById("product-list");

    try {
        const response = await fetch("/api/products");
        const products = await response.json();

        productList.innerHTML = "";
        products.forEach(product => {
            const li = document.createElement("li");
            li.textContent = product.productName + " - " + product.price + " kr.";
            productList.append(li);
        });
    } catch (err) {
        productList.innerHTML = "Failed to load products";
    }
}

window.onpopstate = handleRoutes;
handleRoutes();