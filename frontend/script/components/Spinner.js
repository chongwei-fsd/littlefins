

                    // <div id="testSpinner" class="spinner-border spinner-border-sm d-none" role="status">
                    //   </div>
class Spinner{
    constructor(){
        this.element =null;
        this.spinner =null;
    }

    createSpinner(element){
        this.element=document.getElementById(element);
        this.spinner =document.createElement("div");
        this.spinner.className = "spinner-border spinner-border-sm d-none";
        this.spinner.setAttribute ("role","status");
        this.element.prepend(this.spinner);
    }

    displaySpinner(status=false){
        if(status)
            this.spinner.classList.remove("d-none");
        else
        this.spinner.classList.add("d-none");
    return;
    }
}