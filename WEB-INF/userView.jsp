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
				<a href="/Group18_FinalProject/UserMenu" class="active-page"> <h1>IT Services Portal</h1> </a>
				<c:if test='${sessionScope.person.roleInSystem == "Admin"}'>
				<a href="/Group18_FinalProject/UserManagement">User Management</a>
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
				<h2>Welcome to IT Portal ${sessionScope.person.fname}</h2>
				<c:choose>
                    <c:when test='${sessionScope.person.roleInSystem == "User"}'>
                        <h3>The following issues are issues you have reported and are now marked 
						as completed. You can review the solution and aprrove or decline it.</h3>
                    </c:when>
                    <c:otherwise>
                        <h3>This is a list of new and in progress issues in the system that you can work on.</h3>
                    </c:otherwise>
                </c:choose>
				<form action='UserMenu' method='POST'>
					<c:forEach var='issue' items='${sessionScope.issues}'>
                        <div class="issue">
							<h3>Issue Title: ${issue.title}</h3>
							<p><strong>Category: </strong> ${issue.category}</p>
							<p><strong>Sub-Category: </strong> ${issue.subCategory}</p>
							<p><strong>Issue Description: </strong> ${issue.description}</p>
							<p><strong>Current Status: </strong>${issue.issueState}</p>
							<p><strong>Date Reported: </strong> ${issue.dateReported}</p>
							<p><strong>Time Reported: </strong> ${issue.timeReported}</p>
							<button name="issueID" type="submit" value="${issue.issueId}">Go to Issue Page</button>
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
