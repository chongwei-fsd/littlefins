class NavController {

    constructor(element){
        this.siteMenu = document.getElementById(element);
        this.navbar = document.createElement("ul");
        this.navItems = [
            {"title":_HOME_TITLE, "url": _HOME_URL}, 
            {"title":_ABOUT_TITLE, "url": _ABOUT_URL}, 
            {"title":_PARENTS_TITLE, "url": _PARENTS_URL}, 
            {"title":_PARTNER_TITLE, "url": _PARTNER_URL},
            {"title":_LOGIN_TITLE, "url": _LOGIN_URL},
            {"title":_LOGOUT_TITLE, "url": _LOGOUT_URL}
        ];
    }

    displayNav(){

        this.navbar.className = "navbar-nav ms-auto mb-lg-0";
        this.siteMenu.appendChild(this.navbar);

        
        const token = isAuthenticated();                                                                        // Obtain the token of the authenticated user
        
        this.navItems.forEach((item) => {                                                                       // Populate the navbar, and done conditionally for Login or Logout
            
            const underLinedLink = "nav-link fw-bolder text-decoration-underline";                              // Used for links that require emphasis (join/log in, logout, useremail)
            
            switch (true) {
                case (item.title.toLowerCase() !== _LOGIN_TITLE && item.title.toLowerCase() !== _LOGOUT_TITLE):
                    this.displayNavItem(item);                                                                  // Display menu items not used for authentication
                    break;
                case (!token && item.title.toLowerCase() === _LOGIN_TITLE):
                    this.displayNavItem(item, underLinedLink);                                                  // Display the link to login for UNauthenticated users
                    break;
                case (token && item.title.toLowerCase() === _LOGOUT_TITLE):             
                    this.displayNavItem(item, underLinedLink)                                                   // Display the link to logout for authenticated users
                    break;
                default:                                                                                        // For anything else, include statements to create additional links
                    break;
            }

        });
        
        if(token){
            const user = decodeUser(token);
            const userItem = {"title": user.email, "url": _PROFILE_URL};
            this.displayNavProfile(userItem);
        }

        return;
    }

    displayNavItem(item, underLinedLink = null){
        
        const navItem = document.createElement("li");                                                           // Append menu item as list item
        this.navbar.appendChild(navItem);               
        navItem.className = "nav-item text-nowrap"; 
        
        const navLink = document.createElement("a");                                                            // Append link to menu item
        navItem.appendChild(navLink);                   
        
        if(underLinedLink !== null)                                                                             // Change the navLinks's class if parameter underLinedLink is NOT equals to null 
            navLink.className = underLinedLink;
        else
            navLink.className = "nav-link";

        navLink.textContent = item.title.charAt(0).toUpperCase() + item.title.slice(1);                         // Set the text and the link
        
        if(item.title === _LOGOUT_TITLE){                                                                       // If title is 'logout', 
            navLink.href = "#";                                                                                 // Apply a placeholder anchor (#)
            navLink.addEventListener("click", (event) => {                                                      // add eventListener 
                console.log("logging out");                                                                     
                logout();                                                                                       // call function logout()                                                                        
            })
        }else{
            navLink.href = item.url;                                                                            // Otherwise, apply item URL
        }
    }

    displayNavProfile(item){
        
        const navItem = document.createElement("li");                                                           // Append menu item as list item
        this.navbar.appendChild(navItem);               
        navItem.className = "nav-item text-nowrap"; 
        
        const navLink = document.createElement("a");                                                            // Append link to menu item
        navItem.appendChild(navLink);  
        navLink.className = "nav-link";
        navLink.setAttribute("data-bs-toggle", "tooltip");
        navLink.setAttribute("data-bs-title", item.title);
        
        const iconElement = document.createElement('i');                                                        // Set the ICON and the link
        iconElement.className = "fs-3 fa fa-user-circle px-3";
        navLink.appendChild(iconElement);
        navLink.href = item.url;

        const tooltipProifleLink = navLink;                                                                     // Set a new instance of the tooltip for the profile
        const tooltipProfile =  new bootstrap.Tooltip(tooltipProifleLink);
    }
}