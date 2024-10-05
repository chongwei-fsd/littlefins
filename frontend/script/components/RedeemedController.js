class RedeemedController {
    constructor(element) {
        this.container = document.getElementById(element);
    }

    displayRedeemedVouchers(data) {
        // console.log(data)
        data.forEach(v => {
            // console.log(v.voucher)
            this.container.appendChild(this.addRedeemedVoucher(v.voucher))
        });
    }

    addRedeemedVoucher(voucher){
        const div1=document.createElement("div")
        div1.className="col-12 d-flex p-3 align-items-center"

        const div2=document.createElement("div") 
        div2.className="col-3 text-center"

        const voucherImg=document.createElement("img")
        voucherImg.className="sponsor-img"
        voucherImg.src="images/"+voucher.image

        const div3=document.createElement("div") 
        div3.className="col-3 text-center"

        const voucherDesc=document.createElement("span")
        voucherDesc.className="sponsor-desc"
        voucherDesc.innerText=voucher.description

        const div4=document.createElement("div") 
        div4.className="col-3 text-center"

        const expDate=document.createElement("span") 
        expDate.className="col-3 text-center"
        expDate.innerText=voucher.expDate

        const div5=document.createElement("div") 
        div5.className="col-3 text-center"

        const useNowBtn=document.createElement("a") 
        useNowBtn.className="redeem-btn"
        useNowBtn.innerText="Use now"

        div1.append(div2,div3,div4,div5)
        div2.appendChild(voucherImg)
        div3.appendChild(voucherDesc)
        div4.appendChild(expDate)
        div5.appendChild(useNowBtn)
        return div1
    }

}