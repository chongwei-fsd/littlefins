const rewardsTab=document.getElementById('rewardsTab')
const myRewardsTab=document.getElementById('myRewardsTab')
const rewardsContent=document.getElementById('rewardsContent')
const myRewardsContent=document.getElementById('myRewardsContent')
// const redeemBtn=document.getElementById('redeemBtn')
// const redeemedView=document.getElementById('redeemedView')
const useNowBtn=document.getElementById('useNowBtn')
const viewMyRewards=document.getElementById('viewMyRewards')
rewardsTab.addEventListener('click',rewardsClick)
myRewardsTab.addEventListener('click',myRewardsClick)
// redeemBtn.addEventListener('click',redeemBtnClick)
// redeemedView.addEventListener('click',myRewardsClick)
useNowBtn.addEventListener('click',useNowClick)
viewMyRewards.addEventListener('click',myRewardsClick)


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

// function redeemBtnClick(){
//     profileRedeemModal()
//     redeemBtn.classList.remove('redeem-btn')
//     redeemBtn.classList.add('redeemed-grey-btn')
//     redeemBtn.removeAttribute('href')
//     redeemBtn.innerText="Redeemed"
//     redeemedView.innerText='View'
//     redeemBtn.removeEventListener('click',redeemBtnClick)
// }

function useNowClick(event){
    event.preventDefault();
    useNowBtn.classList.remove('redeem-btn')
    useNowBtn.classList.add('redeemed-grey-btn')
    useNowBtn.innerText='Used'
}

function profileRedeemModal() {
    const modalElement = document.getElementById('profileRedeemModalContent');
    const modal = new bootstrap.Modal(modalElement);
    modal.show();
}