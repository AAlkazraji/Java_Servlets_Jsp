<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">

		<title>IT Services Portal</title>

		<link rel="stylesheet" href="Style.css" type="text/css">

		<script> type="text/javascript"; src="validate.js"</script>

		<div class="header">
			<div class=NavBar>
				<a href="/Group18_FinalProject/UserMenu"> <h1>IT Services Portal</h1> </a>
			</div>
		</div>
	</head>

	<body>
		<div class="body">
				<br />
				<h3>Please fill up all the required details to be able to register with us</h3>
				<div class="error">
					<p> ${sessionScope.ERROR} </p>
				</div>
				<form method = "post" action="CreateAccountServlet" onSubmit="checkInputs();">
					<label>First name:</label><br/>
					<input type="text" name ="firstName" required></input><br/><br/>

					<label>Last name:</label><br/>
					<input type="text" name ="lastName" required></input><br/><br/>

					<label>Phone Number:</label><br/>
					<input type="text" name ="phoneNumber" required></input><br/><br/>

					<label>Email:</label><br/>
					<input type="text" name ="email" required></input><br/><br/>

					<label>Password: </label><br/>
					<input type="password" name ="password" required></input><br/><br/>



					<button type="submit" name ="button" value="submit" >Submit</button><br/>
				</form>
		</div>
	</body>
	  <footer id="footer">
                    <p><b>Company: Group 18 <br>
                        Email: Group18@something.com.au </b>
                    <br>  Copyright &copy; 2021 </p>
        </footer>
</html>
