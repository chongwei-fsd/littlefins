class UserController{
    constructor(element) {
        this.container=document.getElementById(element);
    }
    
    displayUser(data){
        // console.log(data)
        this.container.innerHTML=''
        // this.container.append(this.userProfile(data),this.piggyBankCoins(data),this.myActivity())
        this.container.append(this.myActivity(),this.piggyBankCoins(data),this.userProfile(data))
    
    }

    userProfile(data){
        const div1=document.createElement("div")
        div1.className="col-md-5 col-6"

        const div2=document.createElement("div")
        div2.className="d-md-flex d-flex flex-column align-items-center bg-white rounded-5 p-3 h-100"

        const userName=document.createElement("p")
        userName.className="brown-text fs-5 fw-bold"
        userName.id='user-name'
        userName.innerText="Hello, "+data.name+"!"

        const userImg=document.createElement("i")
        userImg.className="fa fa-circle-user fa-5x"

        const edit=document.createElement("i")
        edit.className="fas fa-edit cursor-pointer brown-text"
        edit.addEventListener('click',function(){
            document.getElementById('updateName').value=data.name
            document.getElementById('updateEmail').value=data.email
            document.getElementById('updatePassword').value=data.password
            const modal = new bootstrap.Modal(document.getElementById('updateUserModal'));
            modal.show();
        })

        const userEmail=document.createElement("div")
        userEmail.className="brown-text small fw-bold py-2 emailTextSize"
        userEmail.id='user-email'
        userEmail.innerText=data.email

        const playBtn=document.createElement("a")
        playBtn.className="orange-bg text-white px-md-5 px-4 py-3 rounded-4 text-decoration-none fw-bold"
        playBtn.innerText="Play Now"
        playBtn.href="game1.html"

        div1.appendChild(div2)
        div2.append(edit,userName,userImg,userEmail,playBtn)
        return div1
    }

    piggyBankCoins(data){
        const div1=document.createElement("div")
        div1.className="col-md-3 col-6"

        const div2=document.createElement("div")
        div2.className="d-md-flex d-flex flex-column align-items-center bg-white rounded-5 p-3 h-100"

        const myCoins=document.createElement("p")
        myCoins.className="brown-text fs-md-5 fs-5 fw-bold"
        myCoins.innerText="My Coins"

        const coinsBalance=document.createElement("p")
        coinsBalance.className="circle fw-bold"
        coinsBalance.id='user-coin'
        coinsBalance.innerText=data.coin

        const piggyImg=document.createElement("img")
        piggyImg.src='./images/piggybanklarge.png'
        piggyImg.width='90'

        div1.appendChild(div2)
        div2.append(myCoins,coinsBalance,piggyImg)
        return div1;
    }

    myActivity(){
        const div1=document.createElement("div")
        div1.className="col-md-4 d-none d-sm-block"

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
