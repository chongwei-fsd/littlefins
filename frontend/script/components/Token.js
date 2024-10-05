class Token{
    constructor(token){
        this.token=token
    }
    isValidProfileUrl(){
        if(!this.token) window.location = _LOGIN_URL
        
        // if(this.token){
        //     const user = decodeUser(this.token);
        //     const profileEmail = document.getElementById("useremail");
        //     const profileUsername = document.getElementById("username");
        //     profileUsername.classList.add("fw-bold");
        //     profileEmail.innerText = user.email;
        //     profileUsername.innerText = user.uname;
        // }    
    }


}