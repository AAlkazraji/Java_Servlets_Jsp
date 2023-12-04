<%@page import="java.util.*" %>
<%@page import="beans.IssuesBean"%>
<%@page import="beans.IssueList"%>
<%
	IssueList list = new IssueList();
	ArrayList<IssuesBean> issues = list.getIssues();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>User Issues</title>
		<link rel="stylesheet" href="Style.css" type="text/css">
		<script src="sortTable.js"></script>
		<div class="header">
			<div class=NavBar>
				<a href="/Group18_FinalProject/UserMenu"> <h1>IT Services Portal</h1> </a>
			</div>
			<br>
			<h1>User Issues</h1>
		</div>
	</head>

	<body>
		<div class="body">
			<h3>Below is a list of current user issues, click on the desired issue to be taken to its dedicated page</h3>
			<table id="table">
				<tr>
					<th onClick = 'sortTable(0)'>Issue Number</th>
					<th onClick = 'sortTable(1)'>Date</th>
					<th>Title</th>
					<th onClick = 'sortTable(3);'>Category</th>
					<th onClick = 'sortTable(4)'>Status</th>
				</tr>
                <%
					for(int i = 0; i < issues.size(); i++){
						%>
						<tr onClick="location.href = 'issue.jsp?id=<%= issues.get(i).getIssueId()%>';">
							<td><%= issues.get(i).getIssueId()%></td>
							<td><%= issues.get(i).getDateReported()%></td>
							<td><%= issues.get(i).getTitle()%></td>
							<td><%= issues.get(i).getCategory()%></td>
							<td><%= issues.get(i).getIssesState()%></td>
						</tr>
				<%	}%>
			</table>
		</div>
	</body>
	<footer>
		<p class="footer" >&copy; Group number 18</p><br/>
		<p class="email" >Group-number-18@uon.edu.au</p>
	</footer>
</html>
