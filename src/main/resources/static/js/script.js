function homePage() {
    return 'Homepage';
}

function bookingPage() {
    return document.getElementById("booking-template").innerHTML;
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
function customerShop() {return 'Overview of snacks and beers'}

const routes = {
    // Homepage and Log-in routing
    "/": {side: homePage, needsLogin: false},
    "/login": {side: employeeLogin, needsLogin: false, onRender: setupLoginForm},

    // Company internal links
    //"/booking": {side: bookingPage, needsLogin: true, onRender: setupBookingForm},
    "/reservationer": {side: reservationPage, needsLogin: true},
    "/inventar": {side: equipmentPage, needsLogin: true},
    "/employees": {side: employeePage, needsLogin: false, onRender: loadEmployees},

    // Customer directed links
    "/booking-overview": {side: customerBookingPage, needsLogin: false},
    "/shop": {side: customerShop, needsLogin: false}

};

// ROUTE HANDLING
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

// LOGIN FORM
const LOGIN_URL = "http://localhost:8080";
const isLoggedIn = () => sessionStorage.getItem("session") !== null;

function renderApp(html) {
    document.getElementById("app").innerHTML = html;
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

window.onpopstate = handleRoutes;
handleRoutes();