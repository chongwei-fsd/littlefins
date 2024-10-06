const userContainerName = "userContainer";
const rewardsContainerName = "rewardsContainer";
const redeemedContainerName = "redeemedContainer";


document.addEventListener("DOMContentLoaded", (event) => {
    event.preventDefault();
    fetchUserById(2); //userid2
});

document.getElementById('myRewardsTab').addEventListener('click', myRewardsClick, fetchRedeemedById(2))
document.getElementById('rewardsTab').addEventListener('click', rewardsClick, fetchAllVouchers())


async function fetchUserById(id) {
    const userContainer = document.getElementById(userContainerName);
    const userSpinner = createAndShowSpinner(userContainer);

    try {
        const response = await fetch(`http://localhost:8080/api/user/${id}`);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        const userController = new UserController(userContainerName);
        userController.displayUser(data);
    } catch (error) {
        console.error('Error fetching user data:', error);
    } finally {
        removeSpinner(userContainer, userSpinner);
    }
}


async function fetchAllVouchers() {
    const rewardsContainer = document.getElementById(rewardsContainerName);
    const rewardsSpinner = createAndShowSpinner(rewardsContainer);

    try {
        const response = await fetch(`http://localhost:8080/api/voucher`);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        const voucherController = new VoucherController(rewardsContainerName);
        voucherController.displayVouchers(data);
    } catch (error) {
        console.error('Error fetching vouchers:', error);
    } finally {
        removeSpinner(rewardsContainer, rewardsSpinner);
    }
}


async function addVoucher(userId, voucherId) {
    const requestData = { userId, voucherId };

    try {
        const response = await fetch(`http://localhost:8080/api/redeemed/${userId}/${voucherId}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(requestData)
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        console.log(data)

        myRewardsClick();
         const redeemedController = new RedeemedController(redeemedContainerName);
        redeemedController.displayRedeemedVouchers([data]);
        
    } catch (error) {
        console.error('Error adding voucher:', error);
    }
}


async function fetchRedeemedById(userId) {
    const redeemedContainer = document.getElementById(redeemedContainerName);
    const redeemedSpinner = createAndShowSpinner(redeemedContainer);

    try {
        const response = await fetch(`http://localhost:8080/api/redeemed/${userId}`);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        const redeemedController = new RedeemedController(redeemedContainerName);
        redeemedController.displayRedeemedVouchers(data);
    } catch (error) {
        console.error('Error fetching redeemed vouchers:', error);
    } finally {
        removeSpinner(redeemedContainer, redeemedSpinner);
    }
}


//spinner create
function createAndShowSpinner(container) {
    let spinnerContainer = container.querySelector('.spinner');
    if (!spinnerContainer) {
        spinnerContainer = document.createElement('div');
        spinnerContainer.className = 'spinner text-center brown-text fw-light mx-auto w-25 pt-4';
        spinnerContainer.innerText = ' Loading...';
        container.append(spinnerContainer);
    }
    return spinnerContainer;
}

//spinner remove
function removeSpinner(container, spinner) {
    if (spinner) {
        container.removeChild(spinner);
    }
}


function rewardsClick(){
    myRewardsContent.classList='col-12 d-none'
    rewardsContent.classList='col-12'

    rewardsTab.classList.remove('bg-trans-white')
    rewardsTab.classList.add('bg-white','fw-bold')

    myRewardsTab.classList.remove('bg-white','fw-bold')
    myRewardsTab.classList.add('bg-trans-white')  
}

function myRewardsClick(){
    rewardsContent.classList='col-12 d-none'
    myRewardsContent.classList='col-12'
    
    myRewardsTab.classList.remove('bg-trans-white')
    myRewardsTab.classList.add('bg-white','fw-bold')

    rewardsTab.classList.remove('bg-white','fw-bold')
    rewardsTab.classList.add('bg-trans-white')     
}





