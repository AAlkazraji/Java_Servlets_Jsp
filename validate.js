var form;
var fName;
var lName;
var phone;
var username;
var email;
var password;

$( document ).ready(function() {
	fName = document.getElementById('firstName');
	lName = document.getElementById('lastName');
	phone = document.getElementById("phoneNumber");
	email = document.getElementById('email');
	password = document.getElementById('password');
	
	alert(fname);

	
	form.addEventListener('submit', function (e) {
		e.preventDefault();
		checkInputs();
	});
});

function checkInputs()
 {
		// trim to remove the whitespaces
		const fNameValue = fName.value.trim();
		const lNameValue = lName.value.trim();
		const phoneValue = phone.value.trim();
		const usernameValue = username.value.trim();
		const emailValue = email.value.trim();
		const passwordValue = password.value.trim();
		
		
		if(fNameValue === "") 
			{
				setErrorFor(username, 'First name cannot be empty');
				return;
			}
		else
			{
			 setSuccessFor(fName);
	 
			}
			
		if(lNameValue === "")
			{
				setErrorFor(username, 'last name cannot be empty');
				return;
			}
		else
			{
			 setSuccessFor(lName);
	 
			}
		
		if(phoneValue === "" || isNaN(phoneValue))
			{
				setErrorFor(username, 'contact method cannot be empty');
				return;
			}
		else
			{
			 setSuccessFor(phone);
			}
		
		if(usernameValue === "")
			{
				setErrorFor(username, 'Username cannot be empty');
				return;
			}
		else
			{
			 setSuccessFor(username);
			}
		
		if(emailValue === "")
			{
				setErrorFor(email, 'Email cannot be empty');
				return;
			}
		else if (!isEmail(emailValue))
			{
				setErrorFor(email, 'Not a valid email');
				return;
			}
		else
			{
			 setSuccessFor(email);
			}
		
		if(passwordValue === "")
			{
				setErrorFor(password, 'Password cannot be empty');
				return;
			} 
		else 
			{
			 setSuccessFor(password);
	 
			}
			
	form.submit();
}

function setErrorFor(input, message)
	{alert(message);}

function setSuccessFor(input)
 {
 const formControl = input.parentElement;
 formControl.className = 'form-control success';
 }
	
function isEmail(email) 
{
	return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}






