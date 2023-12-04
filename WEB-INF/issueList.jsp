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
                <a href="/Group18_FinalProject/IssueList" class='active-page'>View Issues</a>
                <a href="/Group18_FinalProject/KBase">Knowledge Base</a>
                <a href="/Group18_FinalProject/CreateIssue">Open New Issues</a>
                <a href="/Group18_FinalProject/MainPage">Log Out</a>
			</div>
		</div>
	</head>

	<body id="body">
				<br />
                <c:choose>
                    <c:when test='${sessionScope.person.roleInSystem == "User"}'>
                        <h2>Issues Created by You</h2>
                    </c:when>
                    <c:otherwise>
                        <h2>All Issues in The System</h2>
                    </c:otherwise>
                </c:choose>
                <form action='IssueList' method='POST'>
                    <label for="category">Select a category to sort by:</label>
                    <select id="category" name="category">
                        <option value="non" ${"non" == sessionScope.category ? 'selected':''}>Show All</option>
                        <option value="Network" ${"Network" == sessionScope.category ? 'selected':''}>Network</option>
                        <option value="Software" ${"Software" == sessionScope.category ? 'selected':''}>Software</option>
                        <option value="Hardware"  ${"Hardware" == sessionScope.category ? 'selected':''}>Hardware</option>
                        <option value="Email" ${"Email" == sessionScope.category ? 'selected':''}>Email</option>
                        <option value="Account" ${"Account" == sessionScope.category ? 'selected':''}>Account</option>
                    </select>
                    <br>
                    <br>
                    <label for="status">Select an issue status to sort by:</label>
                    <select id="status" name="status">
                        <option value="non" ${"non" == sessionScope.status ? 'selected':''}>Show All</option>
                        <option value="New" ${"New" == sessionScope.status ? 'selected':''}>New</option>
                        <option value="in progress" ${"in progress" == sessionScope.status ? 'selected':''}>In Progress</option>
                        <option value="completed"  ${"completed" == sessionScope.status ? 'selected':''}>Completed</option>
                        <option value="resolved" ${"resolved" == sessionScope.status ? 'selected':''}>Resolved</option>
                    </select><br>
                    <input type="submit" value="Sort">
                        <c:forEach var='issue' items='${sessionScope.issues}'>
                            <%-- ${sessionScope.status}<br>
                            ${issue.issueState}<br> --%>
                            <c:choose>
                                <c:when test='${sessionScope.category != "non" && sessionScope.status != "non"}'>
                                    <c:if test='${sessionScope.category == issue.category && sessionScope.status == issue.issueState}'>
                                        <div class="issue">
                                            <h3>Issue Title: ${issue.title}</h3>
                                            <p><strong>Category: </strong> ${issue.category}</p>
                                            <p><strong>Sub-Category: </strong> ${issue.subCategory}</p>
                                            <p><strong>Issue Description: </strong> ${issue.description}</p>
                                            <p><strong>Current Status: </strong>${issue.issueState}</p>
                                            <p><strong>Date Reported: </strong> ${issue.dateReported}</p>
                                            <p><strong>Time Reported: </strong> ${issue.timeReported}</p>
                                            <c:if test='${issue.issueState == "resolved" || ssue.issueState == "completed"}'>
                                                <p><strong>Resolution Details: </strong> ${issue.reslotionDetails}</p>
                                                <p><strong>Date Resolved: </strong> ${issue.dateSolved}</p>
                                            </c:if>
                                            <button name="issueID" type="submit" value="${issue.issueId}">Go to Issue Page</button>
                                            <br>
                                        </div>
                                    </c:if>
                                </c:when>
                                <c:when test='${sessionScope.category != "non" && sessionScope.status == "non"}'>
                                    <c:if test='${sessionScope.category == issue.category}'>
                                        <div class="issue">
                                            <h3>Issue Title: ${issue.title}</h3>
                                            <p><strong>Category: </strong> ${issue.category}</p>
                                            <p><strong>Sub-Category: </strong> ${issue.subCategory}</p>
                                            <p><strong>Issue Description: </strong> ${issue.description}</p>
                                            <p><strong>Current Status: </strong>${issue.issueState}</p>
                                            <p><strong>Date Reported: </strong> ${issue.dateReported}</p>
                                            <p><strong>Time Reported: </strong> ${issue.timeReported}</p>
                                            <c:if test='${issue.issueState == "resolved" || ssue.issueState == "completed"}'>
                                                <p><strong>Resolution Details: </strong> ${issue.reslotionDetails}</p>
                                                <p><strong>Date Resolved: </strong> ${issue.dateSolved}</p>
                                            </c:if>
                                            <button name="issueID" type="submit" value="${issue.issueId}">Go to Issue Page</button>
                                            <br>
                                        </div>
                                    </c:if>
                                </c:when>
                                <c:when test='${sessionScope.category == "non" && sessionScope.status != "non"}'>
                                    <c:if test='${sessionScope.status == issue.issueState}'>
                                        <div class="issue">
                                            <h3>Issue Title: ${issue.title}</h3>
                                            <p><strong>Category: </strong> ${issue.category}</p>
                                            <p><strong>Sub-Category: </strong> ${issue.subCategory}</p>
                                            <p><strong>Issue Description: </strong> ${issue.description}</p>
                                            <p><strong>Current Status: </strong>${issue.issueState}</p>
                                            <p><strong>Date Reported: </strong> ${issue.dateReported}</p>
                                            <p><strong>Time Reported: </strong> ${issue.timeReported}</p>
                                            <c:if test='${issue.issueState == "resolved" || ssue.issueState == "completed"}'>
                                                <p><strong>Resolution Details: </strong> ${issue.reslotionDetails}</p>
                                                <p><strong>Date Resolved: </strong> ${issue.dateSolved}</p>
                                            </c:if>
                                            <button name="issueID" type="submit" value="${issue.issueId}">Go to Issue Page</button>
                                            <br>
                                        </div>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <div class="issue">
                                        <h3>Issue Title: ${issue.title}</h3>
                                        <p><strong>Category: </strong> ${issue.category}</p>
                                        <p><strong>Sub-Category: </strong> ${issue.subCategory}</p>
                                        <p><strong>Issue Description: </strong> ${issue.description}</p>
                                        <p><strong>Current Status: </strong>${issue.issueState}</p>
                                        <p><strong>Date Reported: </strong> ${issue.dateReported}</p>
                                        <p><strong>Time Reported: </strong> ${issue.timeReported}</p>
                                        <c:if test='${issue.issueState == "resolved" || ssue.issueState == "completed"}'>
                                            <p><strong>Resolution Details: </strong> ${issue.reslotionDetails}</p>
                                            <p><strong>Date Resolved: </strong> ${issue.dateSolved}</p>
                                        </c:if>
                                        <button name="issueID" type="submit" value="${issue.issueId}">Go to Issue Page</button>
                                        <br>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                </form>
            
                <%-- <h3>Issue Title${sessionScope.Issues}</h3> --%>
    </body>
         <footer id="footer">
                    <p><b>Company: Group 18 <br>
                        Email: Group18@something.com.au </b>
                    <br>  Copyright &copy; 2021 </p>
        </footer>
</html>
