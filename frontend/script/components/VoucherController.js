class VoucherController{
    constructor(element) {
        this.container=document.getElementById(element);
    }

    displayVouchers(data){
        // console.log(data)
        data.forEach(voucher => {
            this.container.appendChild(this.addVoucher(voucher))
        });
    }

    addVoucher(data){
        const div1=document.createElement("div")
        div1.className="col-12 d-flex p-3 align-items-center"

        const div2=document.createElement("div")
        div2.className="col-3 text-center"

        const voucherImg=document.createElement("img")
        voucherImg.className="sponsor-img"
        voucherImg.src="images/"+data.image

        const div3=document.createElement("div")
        div3.className="col-3 text-center"

        const voucherDesc=document.createElement("span")
        voucherDesc.className="sponsor-desc"
        voucherDesc.innerText=data.description

        const div4=document.createElement("div")
        div4.className="col-3 text-center d-flex justify-content-center"

        const voucherValue=document.createElement("p")
        voucherValue.className="rewards-circle"
        voucherValue.innerText=data.value

        const div5=document.createElement("div")
        div5.className="col-3 text-center"

        const redeemBtn=document.createElement("a")
        redeemBtn.className="redeem-btn cursorPointer"
        redeemBtn.id=data.id
        redeemBtn.innerText="Redeem"
        redeemBtn.onclick=()=>addVoucher(2,data.id); //userid,voucherid
 
        const nextLine=document.createElement("p")

        const redeemedView=document.createElement("a")
        redeemedView.className="redeemed-view"
        // redeemedView.innerText="View"

        div1.append(div2,div3,div4,div5)
        div2.appendChild(voucherImg)
        div3.appendChild(voucherDesc)
        div4.appendChild(voucherValue)
        div5.append(redeemBtn,nextLine,redeemedView)
        return div1

    }

    
    


}