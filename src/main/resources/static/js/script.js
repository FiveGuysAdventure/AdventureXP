function homePage() {return 'Homepage';}
function bookingPage() {return 'Booking';}
function reservationPage() {return 'Reservationer';}
function equipmentPage() {return 'Inventory';}
function employeePage() {return 'Medarbejdere';}

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

const routes = {
    // Homepage and Log-in routing
    "/": {side: homePage, needsLogin: false},
    "/login": {side: employeeLogin, needsLogin: false, onRender: setupLoginForm},

    // Company internal links
    "/booking": {side: bookingPage, needsLogin: true},
    "/reservationer": {side: reservationPage, needsLogin: true},
    "/inventar": {side: equipmentPage, needsLogin: true},
    "/medarbejdere": {side: employeePage, needsLogin: true},

    // Customer directed links
    "/booking-overview": {side: customerBookingPage, needsLogin: false}
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

window.onpopstate = handleRoutes;
handleRoutes();
