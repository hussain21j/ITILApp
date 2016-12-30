<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>new ticket</title>
	<!-- <script type="text/javascript" src="/js/jquery-3.1.1.min.js"></script> -->
	
	
	<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
	
	</script>
</head>
<body>
	<h1><spring:message code= "ticket.title" /></h1>
	 <div id="bodyDiv">
	   
			<table border="1">
			<tr>
				<th>Incident Id</th>
				<th>Incident raised on</th>
				<th>Incident category</th>
				<th>Incident sub category</th>
				<th>Institution Type</th>
				<th>Institution Name</th>
				<th>Incident priority</th>
				<th>Incident Severity</th>
			</tr>
			<c:forEach  var = "incidentDetails" items="${listIncidentDetails}">
				<tr>
					<td> <a href="${pageContext.request.contextPath}/ticket/getTicketDetails?ticketId=${incidentDetails.incidentDetailId.incidentId}"+ title="manage transaction"><c:out value="${incidentDetails.incidentDetailId.incidentId}"></c:out></a></td>	
					<td><c:out value="${incidentDetails.incidentRaisedOn}"></c:out> </td>
					<td><c:out value="${incidentDetails.incidentCategory.categoryCodeDesc}"></c:out> </td>
					<td><c:out value="${incidentDetails.incidentSubCategory.subCategoryCodeDesc}"></c:out> </td>
					<td><c:out value="${incidentDetails.instituteType.institutionTypeCodeDesc}"></c:out> </td>
					<td><c:out value="${incidentDetails.institutionMapping.institutionName}"></c:out> </td>
					<td><c:out value="${incidentDetails.incidentPriority.prirityDesc}"></c:out> </td>
					<td><c:out value="${incidentDetails.incidentSeverity.severityDesc}"></c:out> </td>
				</tr>
			</c:forEach>
			</table>
	</div> <!--  bodyDiv ends here-->
</body>
</html>