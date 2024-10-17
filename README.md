<h2>Frond end</h2>
<li>User end game at congra page, click sign in/signup, pass the balance coin to his account &#10060;</li>
<li>Static pages (about, parents, partners) &#10060;</li>
<li>admin page (CRUD) &#10060;</li>
<li>Once user redeems the voucher, send an email &#10060;</li>
<li>Game pages' title &#10060;</li>
<li>API POST when user sign up &#9989;</li>
<li>API POST when user login, set token into localstorage &#9989;</li>
<li>API GET for user details, display on profile section (name, email, coin) &#9989;</li>
<li>API GET for all not redeemed vouchers, display on Rewards section &#9989;</li>
<li>API GET for the user's redeemed vouchers, display on My Rewards section &#9989;</li>
<li>API POST when the user clicks on "Redeem", get the voucher Id pass into param (Store userId, voucherId into Redeemed table) &#9989;</li>
<li>API POST when the user clicks on "Use now", get the voucher Id pass into param (Set used to true, set date to current date, decrease coin amount) &#9989;</li>
<li>UI design - Create pop up modal for barcode when user clicks use now button &#9989;</li>
<li>API GET - when the user clicks on "Use now", get the voucher img, desc, exp date, ramdom digit display on barcode &#9989;</li>

<hr>

<h2>Back end</h2>
<li>Sign up (store user details, hashed pwd) &#9989;</li>
<li>Login (token) &#9989;</li>
<li>User update profile (set username, email) &#9989;</li>
<li>User redeem voucher (store user id & voucher id into redeem table) &#9989;</li>
<br/>

<li>Once user has redeemed the specific voucher, when user clicks "use now" button:</li>
<ul>
  <li>Set used attribute to true &#9989;</li>
  <li>Set date attribute to current date &#9989;</li>
  <li>Decrease coin amount based on voucher value &#9989;</li>
</ul>

<li>Get the barcode random generate digit, voucher image, voucher desc, exp date &#9989;</li>
<li>Unit testing &#10060;</li>
<li>Change User.java to Users.java, and update all project files that contains "User", will it affect the DB? &#10060;</li>

<hr>

<h2>Postman</h2>
<li><b>1. POST Sign up</b> localhost:8080/auth/api/signup <mark>(body inputs: email, username, password, dob) (return: message, user object)</mark></li>
<li><b>2. POST Sign in</b> localhost:8080/auth/api/signin <mark>(body input: email, password) (return: message, token, refreshToken, expirationTime)</mark></li>
<li><b>3. GET Get available vouchers</b> localhost:8080/user/api/voucher <mark>(header input: token) (return: id, image, description, value, expDate)</mark></li>
<li><b>4. GET Get user profile</b> localhost:8080/user/view <mark>(header input: token) (return: username, email, password, coin)</mark></li>
<li><b>5. POST Redeem voucher</b> localhost:8080/user/api/voucher/redeem <mark>(header input: token) (param input: id) (return: message, username, email, role, coin, voucher object)</mark></li>
<li><b>6. GET Get redeemed vouchers</b> localhost:8080/user/api/voucher/redeem <mark>(header input: token) (return: id, user object, voucher object)</mark></li>
<li><b>7. POST User updates coin</b> localhost:8080/user/updatecoin <mark>(header input: token) (body input: coin) (return: message, user object)</mark></li>
<li><b>8. POST User uses voucher</b> localhost:8080/user/api/voucher/redeem/use <mark>(header input: token) (param input: id) (return: voucher object, randomNum, dateUsed)</mark></li>
