function homePage() {
    return `
<section class="activities">
    <h1>Vores aktiviteter</h1>
    <p>Experience Adventure ( Age restrictions may wary).</p>
    <div class="cards">
       <article class="card">
    <h2>GoKart</h2>
    <p><strong>Price per person:</strong> 450 DKK</p>
    <p><strong>Max people per run:</strong> 10</p>
    <p><strong>Duration:</strong> 60 minutes</p>
    <p><strong>Age restriction:</strong> Age restriction: 8+</p>
</article>

      <article class="card">
        <h2>Minigolf</h2>
        <p><strong>Price per person:</strong> 200 DKK</p>
        <p><strong>Max people per run:</strong> 20</p>
        <p><strong>Duration:</strong> 60 minutes </p>
        <p><strong>Age restriction:</strong> Age restriction: 6+</p>
      </article>

      <article class="card">
        <h2>Paintball</h2>
        <p><strong>Price per person:</strong> 200 DKK</p>
        <p><strong>Max people per run:</strong> 16 </p>
        <p><strong>Duration:</strong> 60 minutes </p>
        <p><strong>Age restriction:</strong> Age restriction: 12+</p>
      </article>

      <article class="card">
        <h2>Sumo wrestling</h2>
        <p><strong>Price per person:</strong> Pris </p>
        <p><strong>Max people per run:</strong> 8 </p>
        <p><strong>Duration:</strong> 30 minutes</p>
        <p><strong>Age restriction:</strong> Age restriction: 6+</p>
      </article>
    </div>
  </section>
    `;
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
        
        <!-- DETTE KAN BRUGES TIL AT INDSTILLE ÅBEN OG LUK FRA (8-20) med 30 min intervaller 
        <select class="timeSelect" data-from="8" data-to="20" data-interval="30"
        data-start-now="false" data-format="h:i a">
        </select>
        -->
        <label for="bookingDate">Date
            <input type="date" id="bookingDate" name="bookingDate" required>
            <span class="error" data-error="bookingDate"></span>
        </label>
        
        <label for="startTime">Start time
            <select id="startTime" name="startTime" required>
                <option value="">-- Vælg tid --</option>
            </select>
            <span class="error" data-error="startTime"></span>
        </label>
        
        <label for="endTime">End time
            <select id="endTime" name="endTime" required disabled>
                <option value="">-- Vælg starttid først --</option>
            </select>
            <span class="error" data-error="endTime"></span>
        </label>
    
        <label for="numOfGuests">Number of participants
            <input type="number" id="numOfGuests" name="numOfGuests" min="1" value="1" required>
                <span class="error" data-error="numOfGuests"></span>
        </label> 

        <label for="employeeId">Employee
            <select id="employeeId" name="employeeId">
                <option value="">-- Ingen præference --</option>
            </select>
            <span class="error" data-error="employeeId"></span>
        </label>

        <label for="contactEmail">E-mail
            <input type="email" id="contactEmail" name="contactEmail" required>
                <span class="error" data-error="contactEmail"></span>
        </label>

        <label for="contactNumber">Phone-number
            <input type="tel" id="contactNumber" name="contactNumber" required>
                <span class="error" data-error="contactNumber"></span>
        </label>

        <p>Pris: <strong id="price">–</strong></p>

        <button type="submit">Book</button>
    </form>

    <div id="result"></div>`;
}

function reservationPage() {
    return `
        <section>
            <h1>Reservations</h1>
           
            
        
        </section>`;
}

function equipmentPage() {
    return `
        <section>
            <h1>Admin Inventory</h1>
            <p id="equipment-message"></p>

            <h2>In service</h2>
            <ul id="equipment-list">
                <li>Loading equipment...</li>
            </ul>

            <h2>Out of service</h2>
            <ul id="out-of-service-list"></ul>
        </section>`;
}

function employeePage() {
    return `
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
    return `
        <form id="login-form">
            <div class="container">
                <label for="email"><b>Email</b></label>
                <input type="text" id="employeeEmail" placeholder="Enter Employee Email" required>

                <label for="password"><b>Password</b></label>
                <input type="password" id="employeePassword" placeholder="Enter Employee Password" required>

                <p id="login-error" role="alert" hidden></p>
                <button type="submit">Login</button>
            </div>
        </form>
    `;
}

function customerBookingPage() {
    return 'Welcome to the Adventure'
}

function bookingSchedulePage() {
    return `
        <section class="booking-overview-page">
            <h1>Bookingoversigt</h1>
            <p id="booking-count">Henter bookinger...</p>
            <div id="booking-calendar"></div>
        </section>
    `;
}

function customerShop() {
    return `
        <section>
            <h1>Menu</h1>
            <ul id="product-list">
                <li>Loading products...</li>
            </ul>
        </section>`;
}


function employeeShop() {
    return `
        <section>
            <h1>Menu</h1>
            <p id="shop-message"></p>
            <ul id="product-list">
               <li>Loading products...</li>
            </ul>
        </section>`;
}

function productAdmin() {
    return `
        <section>
            <h1>Admin Products</h1>
            <p id="shop-message"></p>
            <ul id="product-list">
               <li>Loading products...</li>
            </ul>

            <h2>Add new product</h2>
            <input type="text" id="new-name" placeholder="Name">
            <input type="number" id="new-price" placeholder="Price">
            <button onclick="createProduct()">Add</button>

            <h2>Inactive products</h2>
            <ul id="inactive-list"></ul>
        </section>`;
}

const pageHeaders = {
    "/": {layout: "hero", title: "Velkommen til", subtitle: "Tekstbeskrivelse"},
    "/booking-overview": {layout: "default", title: "Booking overview", subtitle: ""},
    "/customerShop": {layout: "default", title: "Shop", subtitle: "Se vores produkter"},
    "/booking": {layout: "compact", title: "Booking", subtitle: ""},
    "/inventar": {layout: "compact", title: "Inventar", subtitle: ""},
    "/employees": {layout: "compact", title: "Medarbejdere", subtitle: ""},
    "/employeeShop": {layout: "compact", title: "Shop", subtitle: ""},
    "/products": {layout: "compact", title: "Products", subtitle: ""},
    "/login": {layout: "none"}
};

function updateHeader(path) {
    const config = pageHeaders[path] ?? {layout: "default", title: "", subtitle: ""};
    const header = document.getElementById("page-header");

    document.body.dataset.layout = config.layout;
    document.getElementById("page-title").textContent = config.title ?? "";
    document.getElementById("page-subtitle").textContent = config.subtitle ?? "";
    header.hidden = config.layout === "none";
}

function updateNav() {
    const loggedIn = isLoggedIn();

    document.querySelectorAll("[data-needs-login]").forEach(el => {
        el.hidden = !loggedIn;
    });

    // Optional: hide the "Employee login" footer link once logged in
    const loginLink = document.querySelector('footer a[href="/login"]');
    if (loginLink) loginLink.hidden = loggedIn;
}
// BASE API
const BASE_API = "https://adventurexp5g-eyccewdaf3bzgbfd.swedencentral-01.azurewebsites.net/"
//const BASE_API = "http://localhost:8080"

const routes = {
    // Homepage and Log-in routing
    "/": {side: homePage, needsLogin: false},
    "/login": {side: employeeLogin, needsLogin: false, onRender: setupLoginForm},

    // Company internal links
    "/booking-overview": {side: bookingSchedulePage, needsLogin: true, onRender: loadBookingCalendar},
    "/inventar": {side: equipmentPage, needsLogin: false, onRender: loadEquipment},
    "/employees": {side: employeePage, needsLogin: true, onRender: loadEmployees},
    "/employeeShop": {side: employeeShop, needsLogin: true, onRender: loadProducts},
    "/products": {side: productAdmin, needsLogin: true, onRender: loadProducts},

    // Customer directed links
    "/booking": {side: bookingPage, needsLogin: false, onRender: renderBooking},
    "/customerShop": {side: customerShop, needsLogin: false, onRender: loadProducts}
};

// ROUTE HANDLING
function handleRoutes() {
    let currentPath = routes[location.pathname] ? location.pathname : "/";
    let route = routes[currentPath];

    if (route.needsLogin && !isLoggedIn()) {
        history.replaceState(null, "", "/login");
        currentPath = "/login";
        route = routes["/login"];
    }

    updateHeader(currentPath);
    updateNav();
    document.getElementById("app").innerHTML = route.side();
    route.onRender?.();
}

// LOGIN FORM
const isLoggedIn = () => sessionStorage.getItem("session") !== null;


async function login(employee) {
    const response = await fetch(BASE_API + "/api/login", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(employee)
    });

    if (!response.ok) {
        throw new Error("HTTP " + response.status)
    }

    return await response.json();
}

function navigate(path) {
    history.pushState(null, "", path);
    {
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
            navigate("/")
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

function logout() {
    sessionStorage.removeItem("session");
    navigate("/login");
}

document.addEventListener("click", (e) => {
    if (e.target.matches("[data-logout]")) {
        logout();
    }
});


// EMPLOYEE OVERVIEW
async function fetchAllEmployees() {
    const response = await fetch(BASE_API + "/api/employees");

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

        if (roleType === "MANAGER") {
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
    const result = await fetch(BASE_API + "/api/booking", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(booking)
    });

    if (!result.ok) {
        throw new Error("Http: " + result.status);
    }

    return await result.json();
}

async function loadStartTimes(activityId) {
    const startTimeSelect = document.getElementById("startTime");
    startTimeSelect.length = 1;

    if (!activityId) return;

    try {
        const response = await fetch("/api/booking/time-intervals?activityId=" + activityId)

        if (!response.ok) {
            throw new Error("Http " + response.status);
        }

        const timeIntervals = await response.json();

        timeIntervals.forEach((time) => {
            const hhmm = time.substring(0, 5);
            startTimeSelect.add(new Option(hhmm, hhmm));
        });
    } catch (err) {
        console.error(err);
    }
}

async function handleBookingSubmit(e) {
    e.preventDefault();
    const bookingForm = e.target;

    const dateVal = bookingForm.bookingDate.value;
    const startVal = bookingForm.startTime.value;
    const endVal = bookingForm.endTime.value;

    const booking = {
        contactEmail: bookingForm.contactEmail.value,
        contactNumber: bookingForm.contactNumber.value,
        numOfGuests: Number(bookingForm.numOfGuests.value),
        startTime: `${dateVal}T${startVal}`,
        endTime: `${dateVal}T${endVal}`,
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
        loadStartTimes("");

        const endTimeSelect = document.getElementById("endTime");
        if (endTimeSelect) {
            endTimeSelect.length = 1;
            endTimeSelect.disabled = true;
        }
    } catch (err) {
        result.className = "failure";
        result.textContent = "Kunne ikke oprette booking: " + err.message;
        result.style.display = "block";
        console.error(err);
    }
}

async function loadEndTimes(activityId, startTime) {
    const endTimeSelect = document.getElementById("endTime");
    endTimeSelect.length = 1;

    if (!activityId || !startTime) {
        endTimeSelect.disabled = true;
        return;
    }

    try {
        const response = await fetch(`/api/booking/end-time-intervals?activityId=${activityId}&startTime=${startTime}`)

        if (!response.ok) {
            throw new Error("Http " + response.status);
        }

        const timeIntervals = await response.json();

        timeIntervals.forEach((time) => {
            const hhmm = time.substring(0, 5);
            endTimeSelect.add(new Option(hhmm, hhmm));
        });

        endTimeSelect.disabled = false;
    } catch (err) {
        console.error("Could not load endtimes: " + err);
    }
}

function renderBooking() {
    const bookingForm = document.getElementById("bookingForm");
    const activitySelect = document.getElementById("activityTypeId");
    const startTimeSelect = document.getElementById("startTime");

    loadBookingForm();

    activitySelect.addEventListener("change", (e) => {
        loadStartTimes(e.target.value);
        const endTimeSelect = document.getElementById("endTime");
        if (endTimeSelect) {
            endTimeSelect.length = 1;
            endTimeSelect.disabled = true;
        }
    });

    startTimeSelect.addEventListener("change", (e) => {
        const selectedActivityId = document.getElementById("activityTypeId").value;
        const selectedStartTime = e.target.value;
        loadEndTimes(selectedActivityId, selectedStartTime);
    });

    bookingForm.removeEventListener("submit", handleBookingSubmit);
    bookingForm.addEventListener("submit", handleBookingSubmit);
}

async function loadBookingCalendar() {
    const container = document.getElementById("booking-calendar");
    const response = await fetch("/api/booking-overview",);

    const bookings = await response.json();

    const calendar = new calendarJs(container, {
        manualEditingEnabled: false,
        dragAndDropForEventsEnabled: false,
        autoRefreshTimerDelay: 0,
        allowHtmlInDisplay: false
    });

    calendar.setEvents(bookings.map(booking => ({
        id: String(booking.bookingId),
        from: new Date(booking.startTime),
        to: new Date(booking.endTime),
        title: booking.activityName + " · " + booking.employeeName,
        description:
            "Dato: " + booking.bookingDate +
            "\nTid: " + booking.startTime.substring(11, 16) +
            " – " + booking.endTime.substring(11, 16),
        group: booking.employeeName,
        isAllDay: false,
        repeatEvery: 0,
        showAlerts: false
    })));
}

//SHOP
//Henter aktive produkter både i customerShop, employeeShop & productAdmin
async function loadProducts() {
    const productList = document.getElementById("product-list");

    try {
        const response = await fetch("/api/products");
        const products = await response.json();

        productList.innerHTML = "";
        products.forEach(product => {
            const li = document.createElement("li");
            li.textContent = product.productName + " - " + product.price + " kr. ";

            //Kun salg side
            if (location.pathname === "/employeeShop") {
                const button = document.createElement("button");
                button.textContent = "Sælg";
                button.addEventListener("click", () => sellProduct(product));
                li.append(button);
            }

            //Admin side til redigering af produkter
            if (location.pathname === "/products") {
                const priceButton = document.createElement("button");
                priceButton.textContent = "Change price";
                priceButton.addEventListener("click", () => updatePrice(product));
                li.append(priceButton);

                const removeButton = document.createElement("button");
                removeButton.textContent = "Remove";
                removeButton.addEventListener("click", () => removeProduct(product));
                li.append(removeButton);
            }

            productList.append(li);
        });
    } catch (err) {
        productList.innerHTML = "Failed to load products";
    }
    if (location.pathname === "/products") {
        loadInactiveProducts();
    }
}

//feature til employeeShop delen
async function sellProduct(product) {
    const message = document.getElementById("shop-message");

    const response = await fetch("/api/sales?productId=" + product.productId + "&quantity=1", {
        method: "POST"
    });

    if (response.ok) {
        message.textContent = product.productName + " sold";
    } else {
        message.textContent = "Sale could not proceed"
    }
}

//feature til employeeShop delen
async function createProduct() {
    const message = document.getElementById("shop-message");

    const product = {
        productName: document.getElementById("new-name").value,
        price: Number(document.getElementById("new-price").value)
    };

    const response = await fetch("/api/products", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(product)
    });

    if (response.ok) {
        message.textContent = product.productName + " added";
        loadProducts();
    } else {
        message.textContent = "Product could not be added";
    }
}

//feature til employeeShop delen
async function updatePrice(product) {
    const message = document.getElementById("shop-message");

    const newPrice = prompt("New price for " + product.productName + ":");

    if (newPrice === null) {
        return;
    }

    const response = await fetch("/api/products/" + product.productId + "/price?price=" + newPrice, {
        method: "PUT"
    });

    if (response.ok) {
        message.textContent = product.productName + " new price " + newPrice + " kr.";
        loadProducts();
    } else {
        message.textContent = "Price could not be changed";
    }
}

//feature til employeeShop delen
async function removeProduct(product) {
    const message = document.getElementById("shop-message");

    if (!confirm("Remove " + product.productName + "?")) {
        return;
    }

    const response = await fetch("/api/products/" + product.productId + "/deactivate", {
        method: "PUT"
    });

    if (response.ok) {
        message.textContent = product.productName + " removed";
        loadProducts();
    } else {
        message.textContent = "Product could not be removed";
    }
}

//feature til Admin side af produkter
async function loadInactiveProducts() {
    const inactiveList = document.getElementById("inactive-list");

    const response = await fetch("/api/products/inactive");
    const products = await response.json();

    inactiveList.innerHTML = "";
    products.forEach(product => {
        const li = document.createElement("li");
        li.textContent = product.productName + " - " + product.price + " kr. ";

        const activateButton = document.createElement("button");
        activateButton.textContent = "Activate";
        activateButton.addEventListener("click", () => activateProduct(product));
        li.append(activateButton);
        inactiveList.append(li);
    });
}

//feature til Admin side af produkter: sætter inaktivt produkt tilbage i shoppen
async function activateProduct(product) {
    const message = document.getElementById("shop-message");

    const response = await fetch("/api/products/" + product.productId + "/activate", {
        method: "PUT"
    });

    if (response.ok) {
        message.textContent = product.productName + " added to Shop";
        loadProducts();
    } else {
        message.textContent = "Product could not be added";
    }
}

//EQUIPMENT
//Henter udstyr i drift og ude af drift på Admin Inventory
async function loadEquipment() {
    const equipmentList = document.getElementById("equipment-list");
    const outOfServiceList = document.getElementById("out-of-service-list");

    const response = await fetch("/api/equipment");
    const equipment = await response.json();

    equipmentList.innerHTML = "";
    equipment.forEach(item => {
        const li = document.createElement("li");
        li.textContent = item.equipmentName + " ";

        const button = document.createElement("button");
        button.textContent = "Out of service";
        button.addEventListener("click", () => markOutOfService(item));
        li.append(button);

        equipmentList.append(li);
    });

    const outResponse = await fetch("/api/equipment/out-of-service");
    const outOfService = await outResponse.json();

    outOfServiceList.innerHTML = "";
    outOfService.forEach(item => {
        const li = document.createElement("li");
        li.textContent = item.equipmentName + " ";

        const button = document.createElement("button");
        button.textContent = "Back in service";
        button.addEventListener("click", () => markInService(item));
        li.append(button);

        outOfServiceList.append(li);
    });
}

//Sætter udstyr ude af drift
async function markOutOfService(item) {
    const message = document.getElementById("equipment-message");

    const response = await fetch("/api/equipment/" + item.equipmentId + "/out-of-service", {
        method: "PUT"
    });

    if (response.ok) {
        message.textContent = item.equipmentName + " is out of service";
        loadEquipment();
    } else {
        message.textContent = "Equipment could not be updated";
    }
}

//Sætter udstyr i drift igen
async function markInService(item) {
    const message = document.getElementById("equipment-message");

    const response = await fetch("/api/equipment/" + item.equipmentId + "/in-service", {
        method: "PUT"
    });

    if (response.ok) {
        message.textContent = item.equipmentName + " is back in service";
        loadEquipment();
    } else {
        message.textContent = "Equipment could not be updated";
    }
}

window.onpopstate = handleRoutes;
handleRoutes();

