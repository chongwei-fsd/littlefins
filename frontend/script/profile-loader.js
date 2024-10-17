const userContainerName = "userContainer";
const rewardsContainerName = "rewardsContainer";
const redeemedContainerName = "redeemedContainer";


document.addEventListener("DOMContentLoaded", (event) => {
    event.preventDefault();
    showUserProfile();
    showVouchers();
    showRedeemedVouchers();
});

document.getElementById('myRewardsTab').addEventListener('click', myRewardsClick);
document.getElementById('rewardsTab').addEventListener('click', rewardsClick);

async function showUserProfile() {
    const token = localStorage.getItem(_USERTOKEN);

    try {
        const response = await fetch(`http://localhost:8080/user/view`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`, 
                'Content-Type': 'application/json',
            },
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        const userController = new UserController(userContainerName);
        userController.displayUser(data);
    } catch (error) {
        console.error('Error fetching user data:', error);
    }
}


async function showVouchers() {
    const token = localStorage.getItem(_USERTOKEN);

    try {
        const response = await fetch(`http://localhost:8080/user/api/voucher`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`, 
                'Content-Type': 'application/json',
            },
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        const voucherController = new VoucherController(rewardsContainerName);
        voucherController.displayVouchers(data);
    } catch (error) {
        console.error('Error fetching vouchers:', error);
    }

}



async function redeemVoucher(voucherId, redeemBtn) {
    const requestData = { voucherId };
    const token = localStorage.getItem(_USERTOKEN);

    try {
        const response = await fetch(`http://localhost:8080/user/api/voucher/redeem/${voucherId}`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`, 
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestData)
        });
        if (!response.ok) {
            return alert('Not enough coin to redeem the voucher!');
        }
        const data = await response.json();

        // delete the click voucher
        redeemBtn.closest('div.col-12').remove();

        // modal pop up
        const modalElement = document.getElementById('profileRedeemModalContent');
        const modal = new bootstrap.Modal(modalElement);
        modal.show();

        // update profile coin
        showUserProfile();
        // switch to my rewards tab
        myRewardsClick();
        const redeemedController = new RedeemedController(redeemedContainerName);
        redeemedController.displayRedeemedVouchers([data]);

    } catch (error) {
        console.error('Error adding voucher:', error);
    }
}


async function showRedeemedVouchers() {
    const token = localStorage.getItem(_USERTOKEN);
    try {
        const response = await fetch(`http://localhost:8080/user/api/voucher/redeem`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`, 
                'Content-Type': 'application/json',
            },
        });
        if (!response.ok) {
            return

        }
        const data = await response.json();
        const redeemedController = new RedeemedController(redeemedContainerName);
        redeemedController.displayRedeemedVouchers(data);
        return
    } catch (error) {
        console.error('Error fetching redeemed vouchers:', error);
    }
}



async function useNowBarcode(voucherId) {
    const requestData = { voucherId };
    const token = localStorage.getItem(_USERTOKEN);

    try {
        const response = await fetch(`http://localhost:8080/user/api/voucher/redeem/use/${voucherId}`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`, 
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestData)
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        console.log(data);
        document.getElementById('barcodeNum1').innerText = data.randomNum;
        document.getElementById('barcodeNum2').innerText = data.randomNum;
        document.getElementById('barcodeVoucherImg').src = "images/" + data.voucher.image;
        document.getElementById('barcodeVoucherDesc').innerText = data.voucher.description;
        document.getElementById('barcodeVoucherExpDate').innerText = "Use by: " + data.voucher.expDate;

        // pop up barcode modal 
        const modalElement = document.getElementById('useVoucherBarcodeModal');
        const modal = new bootstrap.Modal(modalElement);
        modal.show();

    } catch (error) {
        console.error('Error fetching redeemed vouchers:', error);
    }
}

document.getElementById('formUpdateUser').addEventListener('submit', async (e) => {
    e.preventDefault();
    const name = document.getElementById('updateName').value;
    const email = document.getElementById('updateEmail').value;
    const password = document.getElementById('updatePassword').value;
    const formData = { name: name, email: email, password: password };
    await updateUserProfile(formData);

});

async function updateUserProfile(formData = {}) {
    if (Object.entries(formData).length === 0) return;

    const token = localStorage.getItem(_USERTOKEN);

    try {
        const response = await fetch(`http://localhost:8080/user/updateprofile`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`, 
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        });
        if (response.ok) {
            const data = await response.json();
            console.log(data);

            const newToken = data.token;
            window.localStorage.removeItem(_USERTOKEN);
            window.localStorage.setItem(_USERTOKEN, newToken);

            //refresh page
            const path = window.location.href.split('?')[0];
            const url = path + "?updated=true";
            window.location = url;
        }
        return;
    } catch (error) {
        console.error('Error update user profile', error);
    }
}



function rewardsClick() {
    myRewardsContent.classList = 'col-12 d-none';
    rewardsContent.classList = 'col-12';

    rewardsTab.classList.remove('bg-trans-white');
    rewardsTab.classList.add('bg-white', 'fw-bold');

    myRewardsTab.classList.remove('bg-white', 'fw-bold');
    myRewardsTab.classList.add('bg-trans-white');
}

function myRewardsClick() {
    rewardsContent.classList = 'col-12 d-none';
    myRewardsContent.classList = 'col-12';

    myRewardsTab.classList.remove('bg-trans-white');
    myRewardsTab.classList.add('bg-white', 'fw-bold');

    rewardsTab.classList.remove('bg-white', 'fw-bold');
    rewardsTab.classList.add('bg-trans-white');
}
