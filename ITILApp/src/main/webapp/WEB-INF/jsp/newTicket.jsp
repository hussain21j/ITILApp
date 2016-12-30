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
	// A $( document ).ready() block.
	$(document).ready(function() {
		$('#idInstitutionType').change(
			    function() {
			    	getInstitutionList();
			  	});
	});
	
	function getInstitutionList(){
		$.ajax({
			type: "POST",
			url: "institutionListAjax?institutionTypeId="+$('#idInstitutionType').val(),
			cache: false,				
			success: function(response){
				var json = JSON.parse(response);
				console.log("hello response :"+json.length);
				
				var html = '<option value="">Select Institution</option>';
				
				$.each(json, function () {
				    // 'this' is the Object you are iterating over
				    console.log("id :"+this.institutionCode +"name :"+this.institutionName);
				    html += '<option value="' + this.institutionCode + '">'
	    	           + this.institutionName + '</option>';
				});
				 html += '</option>';
				 $('#idInstitutionCode').html(html);
			},
			error: function(xhr, status, error){						
				console.log('status :'+xhr.status  +' xhr :'+xhr+'  status:'+status+'  error:'+error);
			}
		});
	}
	
	</script>
</head>
<body>
	<h1><spring:message code= "ticket.title" /></h1>
	 <div id="bodyDiv">
	   <form:form method="post" action="raiseNewTicket" commandName="incidentDetails" >
			<table border="1">
				<tr>
					<td><spring:message code= "ticket.originationMedium"/></td>	
					<td>
						<form:select path = "origMedium">
							<form:options items = "${originationMediumList}"  itemValue="originationMediumCode" itemLabel="originationMediumDesc"/>
						</form:select>
						
						
					</td>
				</tr>
				<tr>
					<td><spring:message code= "ticket.incidentCategory"/></td>
					<td>
						<form:select path = "category">
							<form:options items = "${incidentCategoryList}"  itemValue="categoryCode" itemLabel="categoryCodeDesc"/>
						</form:select>
						<form:errors path="incidentCategory"/>
					</td>
				</tr>
				<tr>
					<td><spring:message code= "ticket.incidentSubCategory"/></td>
					<td>
						<form:select path = "subCategory">
							<form:options items = "${incidentSubCategoryList}"  itemValue="subCategoryCode" itemLabel="subCategoryCodeDesc"/>
						</form:select>
						<form:errors path="incidentCategory"/>
					</td>
				</tr>
				<tr>
					<td><spring:message code= "ticket.institutitonType"/></td>
					<td>
						<form:select path = "institutionType" id="idInstitutionType">
							<form:option value="null" label="--Select--"/>
							<form:options items = "${institutionTypeList}"  itemValue="instiTypeCode" itemLabel="institutionTypeCodeDesc"/>
						</form:select>
						<form:errors path="institutionType"/>
					</td>
				</tr>
				
				<tr>
					<td><spring:message code= "ticket.institutionName"/></td>
					<td>
						<form:select path = "institutionCode" id="idInstitutionCode">
						</form:select>
						<form:errors path="institutionName"/>
					</td>
				</tr>
				
				<%-- <tr>
					<td><spring:message code= "ticket.institutionCode"/></td>
					<td><form:input path = "institutionCode" /></td>
				</tr> --%>
				
				<tr>
					<td><spring:message code= "ticket.UserName"/></td>
					<td>
						<form:input path = "userName" />
						<form:errors path="userName"/>
					</td>
				</tr>
				<tr>
					<td><spring:message code= "ticket.regfisteredUserId"/></td>
					<td>
						<form:input path = "registeredUserId" />
						<form:errors path="registeredUserId"/>
					</td>
				</tr>
				<tr>
					<td><spring:message code= "ticket.registeredUserEmailId"/></td>
					<td>
						<form:input type="email" path = "userEmailId" />
						<form:errors path="userEmailId"/>
					</td>
				</tr>
				<tr>
					<td><spring:message code= "ticket.userContactNumber"/></td>
					<td>
						<form:input path = "userContactNumber" />
						<form:errors path="userContactNumber"/>
					</td>
				</tr>
				<tr>
					<td><spring:message code= "ticket.incidentSummary"/></td>
					<td>
						<form:textarea path="incidentSumnmary" rows="5" cols="30" />
						<form:errors path="incidentSumnmary"/>
					</td>
				</tr>
				<tr>
					<td><spring:message code= "ticket.incidentSolution"/></td>
					<td>
						<form:textarea path="incidentSolution" rows="5" cols="30" />
						<form:errors path="incidentSolution"/>
					</td>
				</tr>
				
				<tr>
					<td><spring:message code= "ticket.incidentPriority"/></td>
					<td>
						<form:select path = "priority">
							<form:option value="null" label="--Select--"/>
							<form:options items = "${incidentPriorityList}"  itemValue="priorityCode" itemLabel="prirityDesc"/>
						</form:select>
						<form:errors path="incidentPriority"/>
					</td>  
				</tr>
				
				<tr>
					<td><spring:message code= "ticket.incidentSeverity"/></td>
					<td>
						<form:select path = "severity">
							<form:option value="null" label="--Select--"/>
							<form:options items = "${incidentSeverityList}"  itemValue="severityCode" itemLabel="severityDesc"/>
						</form:select>
						<form:errors path="incidentSeverity"/>
					</td>  
				</tr>
				
				<tr>
					<td><spring:message code= "ticket.incidentRaisedOn"/></td>
					<td>
						<form:input  type="date" path = "incidentRaisedOn" />
						<form:errors path="incidentRaisedOn"/>
					</td>
				</tr>
				<tr>
					<td><spring:message code= "ticket.incidentClosedOn"/></td>
					<td>
						<form:input type="date" path = "incidentClosedOn" />
						<form:errors path="incidentClosedOn"/>
					</td>
				</tr>
				<tr>
					<td><spring:message code= "ticket.incidentStatus"/></td>
					<td>
						<form:select path = "status">
							<form:option value="null" label="--Select--"/>
							<form:options items = "${incidentStatusList}"  itemValue="statusCode" itemLabel="statusCodeDesc"/>
						</form:select>
						<form:errors path="status"/>
					</td>  
				</tr>
				<tr>
					<td colspan="2" align="center">
                    	<input type="submit" value="Save" />
                        <input type="reset" value="Clear"/>
                  	</td>
				</tr>
			</table>
		</form:form>
	</div> <!--  bodyDiv ends here-->
</body>
</html>