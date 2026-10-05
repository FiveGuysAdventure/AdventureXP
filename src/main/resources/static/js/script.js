function homePage() {
    return 'Homepage';
}

function bookingPage() {
    return 'Booking';
}

function reservationPage() {
    return 'Reservationer';
}

function equipmentPage() {
    return 'Inventory';
}

function employeePage() {
    return 'Medarbejdere';
}

function employeeLogin() {
    return`
        <form id="login-form">
            <div class="container">
                <label for="username"><b>Username</b></label>
                <input type="text" id="username" placeholder="Enter Username" required>

                <label for="password"><b>Password</b></label>
                <input type="password" id="password" placeholder="Enter Password" required>

                <button type="submit">Login</button>
            </div>
        </form>
    `;
}

function customerBookingPage() {
    return 'BOOKING OVERVIEW CUSTOMERS'
}

const routes = {
    // Homepage and Log-in routing
    "/": {side: homePage, needsLogin: false},
    "/login": {side: employeeLogin, needsLogin: false},

    // Company internal links
    "/booking": {side: bookingPage, needsLogin: true},
    "/reservationer": {side: reservationPage, needsLogin: true},
    "/inventar": {side: equipmentPage, needsLogin: true},
    "/medarbejdere": {side: employeePage, needsLogin: true},

    // Customer directed links
    "/booking-overview": {side: customerBookingPage, needsLogin: false}
};

function renderApp(html) {
    document.getElementById("app").innerHTML = html;
}

function handleRoute() {
    const path = location.pathname;
    const page = routes[path] || homePage;
    renderApp(page());
}

document.addEventListener("click", (e) => {
    if (e.target.matches("[data-link]")) {
        e.preventDefault();

        const href = e.target.getAttribute("href");
        history.pushState(null, "", href);
        handleRoute();
    }
});



window.onpopstate = handleRoute;