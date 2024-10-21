class AdminController{
    constructor(element) {
        this.container = document.getElementById(element);
    }

    displayVouchers(data) {
        // console.log(data)
        this.container.innerHTML = ''; // Clear previous vouchers
        data.forEach(voucher => {
            this.container.appendChild(this.getVouchers(voucher));
        });
    }

    getVouchers(voucher) {
        const div1 = document.createElement("div");
        div1.className = "col-12 d-flex p-3 align-items-center";

        const div2 = document.createElement("div");
        div2.className = "col-3 text-center";

        const voucherImg = document.createElement("img");
        voucherImg.className = "sponsor-img";
        voucherImg.src = "images/" + voucher.image;

        const div3 = document.createElement("div");
        div3.className = "col-3 text-center";

        const voucherDesc = document.createElement("div");
        voucherDesc.className = "sponsor-desc";
        voucherDesc.innerText = voucher.description;

        const expDate=document.createElement('span')
        expDate.className = "sponsor-exp-date";
        expDate.innerText="Valid: "+voucher.expDate

        const div4 = document.createElement("div");
        div4.className = "col-3 text-center d-flex justify-content-center";

        const voucherValue = document.createElement("p");
        voucherValue.className = "rewards-circle";
        voucherValue.innerText = voucher.value;

        const div5=document.createElement("div");
        div5.className = "col-3 text-center d-flex justify-content-center flex-md-row flex-column";

        const editBtn = document.createElement("btn");
        editBtn.className = "orange-bg p-2 p-md-3 rounded-4 m-2 cursor-pointer";
        editBtn.innerText='Edit'
        editBtn.addEventListener('click',function(){
            console.log(voucher.id)
            getVoucherById(voucher.id)
        })

        const deleteBtn = document.createElement("btn");
        deleteBtn.className = "bg-primary p-md-3 p-2 rounded-4 m-2 cursor-pointer";
        deleteBtn.innerText='Delete'
        deleteBtn.addEventListener('click',function(){
            deleteVoucher(voucher.id)
        })
    
        div1.append(div2, div3, div4, div5);
        div2.appendChild(voucherImg);
        div3.append(voucherDesc,expDate);
        div4.appendChild(voucherValue);
        div5.append(editBtn,deleteBtn)
        return div1;
    }



}