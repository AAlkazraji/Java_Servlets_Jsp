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
				<a href="/Group18_FinalProject/UserManagement">User Management</a>
				</c:if>
                <a href="/Group18_FinalProject/IssueList">View Issues</a>
                <a href="/Group18_FinalProject/KBase">Knowledge Base</a>
                <a href="/Group18_FinalProject/CreateIssue" class='active-page'>Open New Issues</a>
                <a href="/Group18_FinalProject/MainPage">Log Out</a>
			</div>
		</div>
	</head>

	<body>
		<div class="body">
				<br />
				<h2>Please Describe The Issue in Detail, ${sessionScope.person.fname}</h2>
                    <div class="issueForm">
                        <div class="error">
                            <p> ${sessionScope.error} </p>
                        </div>
                        <div class="success">
                            <p> ${sessionScope.success} </p>
                        </div>
                        <form action='CreateIssue' method='POST'>
                            <label for="category">Category of Issue:</label>
                            <select id="category" name="category" onchange="this.form.submit()">
                                <c:forEach var='category' items='${sessionScope.category}'>
                                    <option value="${category}" ${category == sessionScope.selected ? 'selected':''}>${category}</option>
                                </c:forEach>
                            </select><br>
                            <label for="subCategory">Sub-Category of Issue:</label>
                            <select id="subCategory" name="subCategory">
                                <c:forEach var='subCat' items='${sessionScope.subCat}'>
                                    <option value="${subCat}">${subCat}</option>
                                </c:forEach>
                            </select><br>
                            <label for="title">Add The Title for this Issue:</label><br>
                            <textarea name="title" rows="10" cols="40"></textarea><br>
                            <label for="description">Add The Description for this Issue:</label><br>
                            <textarea name="description" rows="10" cols="40"></textarea><br>
                            <p>Current Date: ${sessionScope.date}</p>
                            <p>Current Time: ${sessionScope.time}</p>
                            <button name="sbmt" type="submit" value="submit">Submit Issue</button>
                        </form>
                    </div>
                        
		</div>
	</body>
	<footer id="footer">
		<p><b>Company: Group 18 <br>
			Email: Group18@something.com.au </b>
		<br>  Copyright &copy; 2021 </p>
	</footer>
</html>
