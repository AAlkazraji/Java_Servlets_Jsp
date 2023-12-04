<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
				<c:if test='${sessionScope.person.roleInSystem == "Admin"}'>
				<a href="/Group18_FinalProject/UserManagement" class="active-page">User Management</a>
				</c:if>
                <a href="/Group18_FinalProject/IssueList">View Issues</a>
                <a href="/Group18_FinalProject/KBase">Knowledge Base</a>
                <a href="/Group18_FinalProject/CreateIssue">Open New Issues</a>
                <a href="/Group18_FinalProject/MainPage">Log Out</a>
			</div>
		</div>
	</head>

	<body>
		<div class="body">
				<br />
				<h2>User Management</h2>
                <h3>Current users in the system. You can change their privileges and delete them.</h3>
                <div class="success">
                    ${sessionScope.message}
                </div>
				<form action='UserManagement' method='POST'>
					<c:forEach var='user' items='${sessionScope.users}'>
                        <div class="issue">
                            <br>
							<strong>Name: </strong> ${user.fname} ${user.lname}&emsp;
                            <strong>Email: </strong> ${user.email}&emsp;
                            <strong>Phone number: </strong> ${user.phoneNo}&emsp;
                            <strong>Role: </strong> ${user.roleInSystem}&emsp;
                            <br><br>
							<c:if test='${user.personID != sessionScope.person.personID}'>	
								<label for="role">Change user role</label>
                                <select id="role" name="role">
                                    <option value="Admin" ${"Admin" == user.roleInSystem ? 'selected':''}>Admin</option>
                                    <option value="User" ${"User" == user.roleInSystem ? 'selected':''}>User</option>
                                </select><br><br>
								<button name="update" type="submit" value="${user.personID}">Update role</button>
								&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
								<button name="delete" type="submit" value="${user.personID}">Delete user</button>
							</c:if>
							<br>
						</div>
                    </c:forEach>
				</form>
		</div>
	</body>
	<footer id="footer">
		<p><b>Company: Group 18 <br>
			Email: Group18@something.com.au </b>
		<br>  Copyright &copy; 2021 </p>
	</footer>
</html>
