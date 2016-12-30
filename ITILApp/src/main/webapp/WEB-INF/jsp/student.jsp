<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>student managenmnt</title>
</head>
<body>
<br>
<sec:authentication property="name"/> <sec:authentication property="authorities"/>
<br>
<a href="logout">Logout</a>
<br>
<h1>Students Data</h1>
<sec:authorize access="hasRole('Admin')">
<form:form action="student.do" method="POST" commandName="student">
	<table>
		<tr>
			<td>Student ID</td>
			<td><form:input path="studentId" /></td>
		</tr>
		<tr>
			<td>Name</td>
			<td><form:input path="studentName" /></td>
		</tr>
		<tr>
			<td>Year Level</td>
			<td><form:input path="studentYear" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" name="action" value="Add" />
			</td>
		</tr>
	</table>
</form:form>
</sec:authorize>
<br>
<table border="1">
	<th>ID</th>
	<th>First name</th>
	<th>Year level</th>
	<c:forEach items="${studentList}" var="student">
		<tr>
			<td>${student.studentId}</td>
			<td>${student.studentName}</td>
			<td>${student.studentYear}</td>
		</tr>
	</c:forEach>
</table>
click <a href="test"> here</a> to go to test link <br>
click <a href="ticket/getNewTicket"> here</a>to raise new ticket<br>
click <a href="ticket/getUserWorkList"> here</a>to get worklist
</body>
</html>