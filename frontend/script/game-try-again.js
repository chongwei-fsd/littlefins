const storedBalance = localStorage.getItem('balance3');
console.log(storedBalance)
const balance=parseFloat(storedBalance)

let totalBalance=balance;// Initialize total balance

const coinDiv = document.getElementById('coinDiv');
const endBtn = document.getElementById('endBtn');
const tryAgainBtn = document.getElementById('tryAgainBtn');
const displayBalance = document.getElementById('displayBalance');

const modalLogin = document.getElementById('modalLogin');
const modalSignup = document.getElementById('modalSignup');

const orangeBgWhiteText = 'fs-5 text-decoration-none fw-bold orange-border rounded-5 px-md-5 px-4 py-2 text-white orange-bg';
const whiteBgOrangText = 'fs-5 text-decoration-none fw-bold orange-border rounded-5 px-md-5 px-4 py-2 orange-text';

prevBtn.style.visibility='hidden'
nextBtn.style.visibility='hidden'

updateCoin();
congraTryAgainModal()

function endBtnClicked() {
    endBtn.className = orangeBgWhiteText;
    tryAgainBtn.className = whiteBgOrangText;
    window.location=_HOME_URL
}

function tryAgainBtnClicked() {
    tryAgainBtn.className = orangeBgWhiteText;
    endBtn.className = whiteBgOrangText;
    window.location=_GAME1_URL
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

function congraTryAgainModal() {
    setTimeout(function () {
        const modal = new bootstrap.Modal(document.getElementById('congraTryAgainModal'));
        modal.show();
    }, 3000);
}

endBtn.addEventListener('click', endBtnClicked);
tryAgainBtn.addEventListener('click', tryAgainBtnClicked);

modalLogin.addEventListener('click', function(){
    window.location=_LOGIN_URL
});

modalSignup.addEventListener('click', function(){
    window.location=_REGISTER_URL
});