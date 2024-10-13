<h2>Frond end</h2>
<li>Fetch API POST when user sign up &#9989;</li>
<li>Fetch API POST when user login, set token into localstorage &#9989;</li>
<li>Fetch API GET for user details, display on profile section (name, email, coin) &#9989;</li>
<li>Fetch API GET for all not redeemed vouchers, display on Rewards section &#9989;</li>
<li>Fetch API GET for the user's redeemed vouchers, display on My Rewards section &#9989;</li>
<li>Fetch API POST when the user clicks on "Redeem", get the voucher Id pass into param (Store userId, voucherId into Redeemed table) &#9989;</li>
<li>Fetch API POST when the user clicks on "Use now", get the voucher Id pass into param (Set used to true, set date to current date, decrease coin amount) &#9989;</li>
<li>How to display barcode?? &#10060;</li>
<li>How to increase user coin when the game end? &#10060;</li>

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

<li>Get the barcode random generate digit, voucher image, voucher desc, exp date &#10060;</li>
<li>Unit testing &#10060;</li>
<li>Change User.java to Users.java, and update all project files that contains "User", will it affect the DB? &#10060;</li>

<hr>

<h2>Postman</h2>
<li><b>POST Sign up</b> localhost:8080/auth/api/signup (username, email, password, role) </li>
<li><b>POST Sign in</b> localhost:8080/auth/api/signin (email, password) </li>
<li><b>GET Get available vouchers</b> localhost:8080/user/api/voucher </li>
<li><b>GET Get user profile</b> localhost:8080/user/view </li>
<li><b>POST Redeem voucher</b> localhost:8080/user/api/voucher/redeem/{voucherId} </li>
