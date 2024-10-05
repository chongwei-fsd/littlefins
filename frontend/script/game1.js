let balance=5;
let totalBalance=balance;// Initialize total balance
const noDecision=0;
const yesDecision=5;

const coinDiv = document.getElementById('coinDiv');
const noBtn = document.getElementById('noBtn');
const yesBtn = document.getElementById('yesBtn');
const displayBalance = document.getElementById('displayBalance');
const circle = document.getElementById('circle');

let decisionFlag=false

const orangeBgWhiteText = 'fs-5 text-decoration-none fw-bold orange-border rounded-5 px-md-5 px-4 py-2 text-white orange-bg';
const whiteBgOrangText = 'fs-5 text-decoration-none fw-bold orange-border rounded-5 px-md-5 px-4 py-2 orange-text';

const nextBtn=document.getElementById('nextBtn')
const prevBtn=document.getElementById('prevBtn')
prevBtn.style.visibility='hidden'

const arrowEffect=document.getElementById('arrowEffect')

updateCoin();

function noBtnClicked() {
    noBtn.className = orangeBgWhiteText;
    yesBtn.className = whiteBgOrangText;
    totalBalance = balance + noDecision;
    updateCoin();
    circle.innerText = `${noDecision}`;
    arrowEffect.className='fa-solid fa-chevron-right arrowEffect'
    decisionFlag=true
}

function yesBtnClicked() {
    yesBtn.className = orangeBgWhiteText;
    noBtn.className = whiteBgOrangText;
    totalBalance = balance + yesDecision;
    updateCoin();
    circle.innerText = `+${yesDecision}`;
    arrowEffect.className='fa-solid fa-chevron-right arrowEffect'
    decisionFlag=true
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

noBtn.addEventListener('click', noBtnClicked);
yesBtn.addEventListener('click', yesBtnClicked);

nextBtn.addEventListener('click',function(){
    if(!decisionFlag){
        return decisionAlert()
    }
    localStorage.setItem('balance1',totalBalance)
    window.location=_GAME2_URL
})