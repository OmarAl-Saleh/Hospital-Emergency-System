<%@ page import="java.util.*, models.Patient"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%
Patient patient = (Patient) session.getAttribute("patient_signIn");

if (patient == null) {
	response.sendRedirect("patient/sign-in.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 20px;
}

.container {
	background-color: white;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	max-width: 800px;
	margin: auto;
}

h1 {
	text-align: center;
	color: #333;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

table, th, td {
	border: 1px solid #ddd;
}

th, td {
	padding: 10px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

.form-group {
	margin-bottom: 15px;
}

label {
	display: block;
	margin-bottom: 10px;
	font-weight: bold;
	color: #555;
}

input[type="text"], input[type="password"], input[type="email"], input[type="tel"]
	{
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type="submit"], button {
	background-color: #4CAF50;
	color: white;
	border: none;
	padding: 10px 20px;
	cursor: pointer;
	border-radius: 4px;
	font-size: 16px;
	margin-right: 10px; /* Added margin for spacing */
}

input[type="submit"]:hover, button:hover {
	background-color: #45a049;
}

.action-button {
	margin-right: 10px;
	margin-top: 15px;
	margin-left: 10px;
}

.update-box {
	background-color: #e7f3fe;
	color: #31708f;
	border: 1px solid #bce8f1;
	padding: 15px;
	margin-top: 15px;
	border-radius: 4px;
}

.action-form {
	display: inline;
	margin-right: 10px; /* Added margin for spacing */
}
</style>
</head>
<body>
<h1>hello</h1>
</body>
</html>