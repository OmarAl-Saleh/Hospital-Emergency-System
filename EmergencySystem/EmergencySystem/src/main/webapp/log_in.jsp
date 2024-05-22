<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>sign in page</title>
</head>
<body>
<% 
String role=request.getParameter("you-are");
String log_in=request.getParameter("sign");

switch(role)
{
case "patient":
	if("sign-in".equals(log_in))
	{
		%><jsp:forward page="patient/sign-in.jsp"/>  <%
	}
	else
	{
		%><jsp:forward page="patient/sign-up.jsp"/>  <%
	}
	break;
case "officer":
	if("sign-in".equals(log_in))
	{
		%><jsp:forward page="officer/sign-in.jsp"/>  <%
	}
	else
	{
		%><jsp:forward page="officer/sign-up.jsp"/>  <%
	}
	break;
case "screening-nurse":
	if("sign-in".equals(log_in))
	{
		%><jsp:forward page="screening_nurse/sign-in.jsp"/>  <%
	}
	else
	{
		%><jsp:forward page="screening_nurse/sign-up.jsp"/>  <%
	}
	break;
case "department-nurse":
	if("sign-in".equals(log_in))
	{
		%><jsp:forward page="department_nurse/sign-in.jsp"/>  <%
	}
	else
	{
		%><jsp:forward page="department_nurse/sign-up.jsp"/>  <%
	}
	break;


}

//out.println("From sign.jsp You Are "+role);
//out.println("you want to log in by "+log_in);
%>
</body>
</html>