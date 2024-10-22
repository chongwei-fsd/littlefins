const adminVoucherName = 'adminVoucherContainer';
let currentVoucherId = null; // Track if a voucher is being updated or added

document.addEventListener("DOMContentLoaded", (event) => {
    event.preventDefault();
    getAllVouchers();

    // Handle form submission
    document.getElementById('voucherForm').addEventListener('submit', function (e) {
        e.preventDefault();
        if (currentVoucherId) {
            updateVoucher(currentVoucherId);
        } else {
            addVoucher();
        }
        const expDate = document.getElementById('formExpDate').value;
        const datePattern = /^\d{4}-\d{2}-\d{2}$/; // YYYY-MM-DD format

        if (!datePattern.test(expDate)) {
            alert('Invalid date format! Please enter the date in YYYY-MM-DD format.');
            return;
        }
    });
});

async function getAllVouchers() {
    const token = localStorage.getItem(_USERTOKEN);

    try {
        const response = await fetch(`http://localhost:8080/admin/vouchers`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        const adminController = new AdminController(adminVoucherName);
        adminController.displayVouchers(data);
    } catch (error) {
        console.error('Error fetching all vouchers:', error);
    }
}

async function getVoucherById(voucherId) {
    const token = localStorage.getItem(_USERTOKEN);

    try {
        const response = await fetch(`http://localhost:8080/admin/voucher/${voucherId}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        document.getElementById('formDesc').value = data.description;
        document.getElementById('formValue').value = data.value;
        document.getElementById('formImg').value = data.image;
        document.getElementById('formExpDate').value = data.expDate;
        document.getElementById('submitBtn').innerText = 'Update Voucher';
        currentVoucherId = voucherId; // Set current voucher ID for update

    } catch (error) {
        console.error('Error fetching user data:', error);
    }
}

async function addVoucher() {
    const token = localStorage.getItem(_USERTOKEN);
    const description = document.getElementById('formDesc').value;
    const value = document.getElementById('formValue').value;
    const image = document.getElementById('formImg').value;
    const expDate = document.getElementById('formExpDate').value;

    try {
        const response = await fetch(`http://localhost:8080/admin/voucher`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ description, value, image, expDate })
        });
        if (!response.ok) {
            throw new Error('Error adding voucher');
        }
        // Reset form after adding
        resetForm();
        getAllVouchers(); // Refresh voucher list
    } catch (error) {
        console.error('Error adding voucher:', error);
    }
}

function resetForm() {
    document.getElementById('formDesc').value = '';
    document.getElementById('formValue').value = '';
    document.getElementById('formImg').value = '';
    document.getElementById('formExpDate').value = '';
    document.getElementById('submitBtn').innerText = 'Add Voucher';
    currentVoucherId = null; // Reset the voucher ID
}

async function updateVoucher(voucherId) {
    const token = localStorage.getItem(_USERTOKEN);
    const description = document.getElementById('formDesc').value;
    const value = document.getElementById('formValue').value;
    const image = document.getElementById('formImg').value;
    const expDate = document.getElementById('formExpDate').value;

    try {
        const response = await fetch(`http://localhost:8080/admin/voucher/${voucherId}`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ description, value, image, expDate })
        });
        if (!response.ok) {
            throw new Error('Error updating voucher');
        }
        // Reset form after updating
        resetForm();
        getAllVouchers(); // Refresh voucher list
    } catch (error) {
        console.error('Error updating voucher:', error);
    }
}

async function deleteVoucher(voucherId) {
    const token = localStorage.getItem(_USERTOKEN);

    try {
        const response = await fetch(`http://localhost:8080/admin/voucher/${voucherId}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
        });
        if (!response.ok) {
            throw new Error('Error deleting voucher');
        }
        getAllVouchers(); // Refresh voucher list after deletion
    } catch (error) {
        console.error('Error deleting voucher:', error);
    }
}

document.getElementById('clearBtn').addEventListener('click', function () {
    resetForm(); // Clear the form
});