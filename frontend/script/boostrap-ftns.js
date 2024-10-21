// bootstrap form validation (anonymous function)
(() => {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }

            form.classList.add('was-validated');
        }, false)
    });
})();

// Toast functionality for validation or form submission
function showToast({toastElement, toastBodyElement, bgColor, msg}){

    // Run BootStrap5's toast to show the activity is complete.
    const toastEl = toastElement;
    const toastBody = toastBodyElement;
    toastEl.classList.remove("bg-success");                 // Remove all known and used colors here first
    toastEl.classList.remove("bg-danger");                  // Remove all known and used colors here first
    toastEl.classList.add(`bg-${bgColor}`);
    toastEl.classList.add("text-white");
    toastBody.textContent = msg;
    const toast = new bootstrap.Toast(toastEl);
    toast.show();
    
}