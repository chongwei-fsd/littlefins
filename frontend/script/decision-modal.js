let decisionModal=`    
        <div class="modal-dialog modal-dialog-centered modal-sm px-3">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <h4 class="brown-text fw-bold">Ooops!
                    </h4>
                    <p class="fw-light fs-5 brown-text">Please make a decision!</p>
                    <div class="d-flex justify-content-center align-items-center py-3">
                            <a href="" class="text-decoration-none text-white px-5 py-2 rounded-5 orange-bg"
                                data-bs-dismiss="modal">OK</a>
                    </div>
                </div>
            </div>
        </div>`


function decisionAlert(){
    const decisionAlert = document.getElementById('decisionAlert')
    decisionAlert.innerHTML=decisionModal 
    const modal=new bootstrap.Modal(decisionAlert);
    modal.show();      
}