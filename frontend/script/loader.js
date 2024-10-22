// EventListener to instantiate the navController
let spinner= null;

document.addEventListener("DOMContentLoaded", (event) => {


    // Instantiate an instance of the siteMenu
    const navController = new NavController("navbarNav");
    navController.displayNav();

    spinner = new Spinner();

    const token = isAuthenticated();  
    const role = window.localStorage.getItem(_USERROLE);

    if (window.location.pathname.includes(_PROFILE_URL)) {
        if(!token){
            window.location = _LOGIN_URL
        }
        if(role==="ADMIN"){
            window.location = _ADMIN_URL
        }
    }

    if (window.location.pathname.includes(_LOGIN_URL)) {
        if(token){
            window.location = _PROFILE_URL
        }
    }

    if (window.location.pathname.includes(_REGISTER_URL)) {
        if(token){
            window.location = _PROFILE_URL
        }
    }

    if (window.location.pathname.includes(_ADMIN_URL)) {
        if(!token){
            window.location = _LOGIN_URL
        }
        if(role==="USER"){
            window.location = _PROFILE_URL
        }
    }






});
