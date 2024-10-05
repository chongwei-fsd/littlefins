class UserController{
    constructor(element) {
        this.container=document.getElementById(element);
    }
    
    displayUser(data){
        // console.log(data)
        this.container.append(this.userProfile(data),this.piggyBankCoins(data),this.myActivity())
    
    }

    userProfile(data){
        const div1=document.createElement("div")
        div1.className="col-md-3 col-6"

        const div2=document.createElement("div")
        div2.className="d-md-flex d-flex flex-column align-items-center bg-white rounded-5 p-3 h-100"

        const userName=document.createElement("p")
        userName.className="brown-text fs-5 fw-bold"
        userName.innerText="Hello, "+data.username

        const userImg=document.createElement("i")
        userImg.className="fa fa-circle-user fa-5x"

        const userEmail=document.createElement("div")
        userEmail.className="brown-text small fw-bold py-2 emailTextSize"
        userEmail.innerText=data.email

        const playBtn=document.createElement("a")
        playBtn.className="orange-bg text-white px-5 py-md-2 py-3 rounded-4 text-decoration-none fw-bold"
        playBtn.innerText="Play"
        playBtn.href="game1.html"

        div1.appendChild(div2)
        div2.append(userName,userImg,userEmail,playBtn)
        return div1
    }

    piggyBankCoins(data){
        const div1=document.createElement("div")
        div1.className="col-md-3 col-6"

        const div2=document.createElement("div")
        div2.className="d-md-flex d-flex flex-column align-items-center bg-white rounded-5 p-3 h-100"

        const piggyBank=document.createElement("p")
        piggyBank.className="brown-text fs-md-5 fs-5 fw-bold"
        piggyBank.innerText="Piggy Bank"

        const coinsBalance=document.createElement("p")
        coinsBalance.className="circle fw-bold"
        coinsBalance.innerText=data.coin

        div1.appendChild(div2)
        div2.append(piggyBank,coinsBalance)
        return div1;
    }

    myActivity(){
        const div1=document.createElement("div")
        div1.className="col-md-6 d-none d-sm-block"

        const div2=document.createElement("div")
        div2.className="d-md-flex d-flex flex-column align-items-center bg-white rounded-5 p-3 h-100"

        const myActivity=document.createElement("p")
        myActivity.className="brown-text fs-md-5 fs-5 fw-bold"
        myActivity.innerText="My Activity"

        const activityImg=document.createElement("img")
        activityImg.src="images/activity.png"
        activityImg.width="200"

        div1.appendChild(div2)
        div2.append(myActivity,activityImg)
        return div1

    }


}