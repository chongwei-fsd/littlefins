// EventListener to instantiate the navController
let spinner= null;

document.addEventListener("DOMContentLoaded", (event) => {
    
    // Instantiate an instance of the siteMenu
    const navController = new NavController("navbarNav");
    navController.displayNav();

    spinner = new Spinner();

    const token = isAuthenticated();  

    if (window.location.pathname.includes(_PROFILE_URL)) {
        if(!token){
            window.location = _LOGIN_URL
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






});
