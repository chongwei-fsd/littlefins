const userContainerName = "userContainer";
const rewardsContainerName = "rewardsContainer";
const redeemedContainerName = "redeemedContainer";

const spinnerContainerName = "spinnerContainer";

const spinnerContainer = document.createElement("div");
spinnerContainer.setAttribute("id", spinnerContainerName);
spinnerContainer.className = "text-center brown-text fw-light mx-auto w-25 pt-4";
spinnerContainer.innerText = " Loading...";

// let userController = null;

document.addEventListener("DOMContentLoaded", (event) => {
    event.preventDefault();
    fetchUserById(2); //userid2
    fetchAllVouchers();
    fetchRedeemedById(2); //userid2
});

function fetchUserById(id) {
    const userContainer = document.getElementById(userContainerName);
    userContainer.append(spinnerContainer);
    const userSpinner = new Spinner();
    userSpinner.createSpinner(spinnerContainerName);
    userSpinner.displaySpinner(true);

    fetch(`http://localhost:8080/api/user/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse JSON response
        })
        .then(data => {
            // console.log(data); 
            const userController = new UserController(userContainerName);
            userController.displayUser(data);

            userSpinner.displaySpinner(false);
            userContainer.removeChild(spinnerContainer);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}


function fetchAllVouchers() {
    const rewardsContainer = document.getElementById(rewardsContainerName);
    rewardsContainer.append(spinnerContainer);
    const rewardsSpinner = new Spinner();
    rewardsSpinner.createSpinner(spinnerContainerName);
    rewardsSpinner.displaySpinner(true);

    fetch(`http://localhost:8080/api/voucher`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse JSON response
        })
        .then(data => {
            // console.log(data); 
            const voucherController = new VoucherController(rewardsContainerName);
            voucherController.displayVouchers(data);

            rewardsSpinner.displaySpinner(false);
            rewardsContainer.removeChild(spinnerContainer);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}


function addVoucher(userId, voucherId) {
    const requestData = {
        userId: userId,
        voucherId: voucherId
    };

    fetch(`http://localhost:8080/api/redeemed/${userId}/${voucherId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(requestData) //convert js obj to json
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse JSON response
        })
        .then(data => {
            console.log(data);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}

function fetchRedeemedById(userId) {
    const redeemedContainer = document.getElementById(redeemedContainerName);
    redeemedContainer.append(spinnerContainer);
    const redeemedSpinner = new Spinner();
    redeemedSpinner.createSpinner(spinnerContainerName);
    redeemedSpinner.displaySpinner(true);

    fetch(`http://localhost:8080/api/redeemed/${userId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse JSON response
        })
        .then(data => {
            // console.log(data.voucher);
            const redeemedController = new RedeemedController(redeemedContainerName);
            redeemedController.displayRedeemedVouchers(data)
            
            redeemedSpinner.displaySpinner(false);
            redeemedContainer.removeChild(spinnerContainer);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}


