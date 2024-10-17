const storedBalance = localStorage.getItem('balance2');
console.log(storedBalance)
const balance=parseFloat(storedBalance)

let totalBalance=balance;// Initialize total balance
const playDecision=0;
const workDecision=10;

const coinDiv = document.getElementById('coinDiv');
const playBtn = document.getElementById('playBtn');
const workBtn = document.getElementById('workBtn');
const displayBalance = document.getElementById('displayBalance');
const circle = document.getElementById('circle');

let decisionFlag=false

const orangeBgWhiteText = 'fs-5 text-decoration-none fw-bold orange-border rounded-5 px-md-5 px-4 py-2 text-white orange-bg';
const whiteBgOrangText = 'fs-5 text-decoration-none fw-bold orange-border rounded-5 px-md-5 px-4 py-2 orange-text';

const nextBtn=document.getElementById('nextBtn')
const prevBtn=document.getElementById('prevBtn')

const arrowEffect=document.getElementById('arrowEffect')

updateCoin();

function playBtnClicked() {
    playBtn.className = orangeBgWhiteText;
    workBtn.className = whiteBgOrangText;
    totalBalance = balance - playDecision;
    updateCoin();
    circle.innerText = `${playDecision}`;
    decisionFlag=true
    arrowEffect.className='fa-solid fa-chevron-right arrowEffect'
}

function workBtnClicked() {
    workBtn.className = orangeBgWhiteText;
    playBtn.className = whiteBgOrangText;
    totalBalance = balance + workDecision;
    updateCoin();
    circle.innerText = `+${workDecision}`;
    decisionFlag=true
    arrowEffect.className='fa-solid fa-chevron-right arrowEffect'
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


playBtn.addEventListener('click', playBtnClicked);
workBtn.addEventListener('click', workBtnClicked);

prevBtn.addEventListener('click',function(){
    window.location=_GAME2_URL
})

nextBtn.addEventListener('click',function(){
    if(!decisionFlag){
        return decisionAlert()
    }
    if(totalBalance>15){
        window.location=_GAME_CONGRA_URL
    }
    else{
        window.location=_GAME_TRY_AGAIN_URL
    }
    localStorage.setItem('balance3',totalBalance)
    
})