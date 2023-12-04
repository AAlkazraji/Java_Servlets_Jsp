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
                <a href="/Group18_FinalProject/CreateIssue">Open New Issues</a>
                <a href="/Group18_FinalProject/MainPage">Log Out</a>
			</div>
		</div>
	</head>

	<body id="body">
				<br />
                    <form action='IssuePage' method='POST'>
                        <div class="issue">
                            <h2>Title: ${sessionScope.issue.title}</h2>
                            <p><strong>Reported By: </strong>${sessionScope.issue.personName}</p>
                            <p><strong>Category: </strong>${sessionScope.issue.category}</p>
                            <p><strong>Sub-Category: </strong>${sessionScope.issue.subCategory}</p>
                            <p><strong>Current Status: </strong>${sessionScope.issue.issueState}</p>
                            <p><strong>Issue Description: </strong> ${issue.description}</p>
                            <c:if test='${sessionScope.state != null}'>
                                <label for="state">Change the issue status</label>
                                <select id="state" name="state">
                                <c:forEach var='state' items='${sessionScope.state}'>
                                        <option value="${state}"${state == sessionScope.issue.issueState ? 'selected':''}>${state}</option>
                                </c:forEach>
                                </select>
                            </c:if>
                            <%-- <p> the issue state is ${sessionScope.issue.issueState}</P> --%>
                            <%-- <p><strong>Resolution Details: </strong> ${issue.reslotionDetails}</p> --%>
                            <%-- <c:set var="state" value='${sessionScope.issue.issueState}'/> --%>
                            <c:if test='${sessionScope.issue.issueState == "resolved" || sessionScope.issue.issueState == "in progress" || sessionScope.issue.issueState == "completed"}'>
                                <p><strong>Resolution Details: </strong> ${issue.reslotionDetails}</p>
                            </c:if>
                            <c:if test='${sessionScope.person.roleInSystem == "Admin" && sessionScope.issue.issueState != "resolved" && sessionScope.issue.issueState != "completed"}'>
                                <label for="reslotionDetails">Please enter the resolution details</label><br>
                                <textarea name="reslotionDetails" rows="5" cols="30">${issue.reslotionDetails}</textarea>
                            </c:if>
                            <c:forEach var='comment' items='${sessionScope.issue.comments}'>
                                <div class="comment">
                                     <p><strong>Comment by: </strong>${comment.personName}</p>
                                     <hr>
                                     <p>${comment.description}</P>
                                </div>
                            </c:forEach><br>
                            <label for="comment">Comment on this issue:</label><br>
                            <textarea name="comment" rows="5" cols="30"></textarea><br>
                            <button name="submit" type="submit">Update Issue Details</button>
                        </div>
                    </form>
    </body>
         <footer id="footer">
                    <p><b>Company: Group 18 <br>
                        Email: Group18@something.com.au </b>
                    <br>  Copyright &copy; 2021 </p>
        </footer>
</html>
