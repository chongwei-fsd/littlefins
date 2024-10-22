const storedBalance = localStorage.getItem('balance3');
console.log(storedBalance);
const balance = parseFloat(storedBalance);

let totalBalance = balance;// Initialize total balance

const coinDiv = document.getElementById('coinDiv');
const displayBalance = document.getElementById('displayBalance');

const orangeBgWhiteText = 'fs-5 text-decoration-none fw-bold orange-border rounded-5 px-md-5 px-4 py-2 text-white orange-bg';
const whiteBgOrangText = 'fs-5 text-decoration-none fw-bold orange-border rounded-5 px-md-5 px-4 py-2 orange-text';

prevBtn.style.visibility = 'hidden';
nextBtn.style.visibility = 'hidden';

updateCoin(); 

const authUser = isAuthenticated();
if (authUser) {
    document.getElementById('gameCoin').innerText = balance; //pass the value into modal display
    authUserModal(); //auth user modal
    document.getElementById('viewCoinBalance').addEventListener('click',function(){
         updateUserGameCoin(); 
    })
}
else {
    notAuthUserModal(); //not auth user modal
    document.getElementById('redeemLogin').addEventListener('click',function(){
        window.location = _LOGIN_URL;
    })
    document.getElementById('redeemSignup').addEventListener('click',function(){
        window.location = _REGISTER_URL;
    })
}

function updateCoin() {
    coinDiv.innerHTML = ''; // Clear existing coins
    for (let i = 0; i < totalBalance; i++) {
        let coin = document.createElement('i');
        coin.className = 'fas fa-dollar-sign coin-icon fs-2 rounded-circle px-3 py-2';
        coinDiv.appendChild(coin);
    }
    displayBalance.innerText = totalBalance; // Update the balance text
}

function notAuthUserModal() {
    setTimeout(function () {
        const modal = new bootstrap.Modal(document.getElementById('notAuthUserModal'));
        modal.show();
    }, 3500);
}

function authUserModal() {
    setTimeout(function () {
        const modal = new bootstrap.Modal(document.getElementById('authUserModal'));
        modal.show();
    }, 3500);
}

async function updateUserGameCoin() {
    const coin = localStorage.getItem("balance3");
    const requestData = { coin };
    const token = localStorage.getItem(_USERTOKEN);

    try {
        const response = await fetch(`http://localhost:8080/user/updatecoin`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`, // Include token in Authorization header
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestData)
        });
        if (response.ok) {
            // const data = await response.json();
            window.location = _PROFILE_URL;
            //refresh
            window.location.href.split('?')[0];
        }
        return;

    } catch (error) {
        console.error('Error adding voucher:', error);
    }
}

