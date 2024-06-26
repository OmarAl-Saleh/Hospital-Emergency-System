<%@ page import="java.util.*, models.Patient, models.subPatient, models.Case,models.Screening_Nurse ,java.util.Base64" %>
<%@ page session="true" %>
<%
    Patient patient = (Patient) session.getAttribute("patient_signIn");
    RequestDispatcher failed = request.getRequestDispatcher("patient/sign-in.jsp");
    if (patient == null) {
        
        response.sendRedirect("index.jsp"); // Redirect to sign-in if patient is not in session
        return;
    }

    Case[] cases = patient.getCases();
    
    // Check if a case number is provided for actions
    String caseNumber = request.getParameter("caseNumber");
    String action = request.getParameter("action");

    String updateMessage = "";

    if (caseNumber != null && action != null) {
        switch (action) {
            case "cancel":
                patient.setStatusCanceled(caseNumber);
                updateMessage = "Case " + caseNumber + " has been canceled.";
                break;
            case "newCase":
                patient.generateNewCase();
                updateMessage = "Case " + caseNumber + " has been generated.";
                break;
            case "update":
                patient.setStatusNew(caseNumber);
                updateMessage = "Case " + caseNumber + " has been updated.";
                break;
                
            case "sign_out":
            	response.sendRedirect("index.jsp");
            	return;
    
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Patient Manage Cases</title>
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
            max-width: 1000px;
            margin: auto;
            overflow-x: auto; /* Ensure the container is scrollable if content overflows */
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
            padding: 15px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        select, input[type="text"], input[type="password"], input[type="email"], input[type="tel"] {
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
            width: calc(100% - 18px);
            margin-bottom: 10px;
        }

        .action-form {
            display: inline-block;
            width: 100%;
            margin-bottom: 10px;
        }

        .action-button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 4px;
            font-size: 16px;
            width: calc(100% - 10px);
            margin-right: 10px;
        }

        .action-button:hover {
            background-color: #45a049;
        }

        .update-box {
            background-color: #e7f3fe;
            color: #31708f;
            border: 1px solid #bce8f1;
            padding: 15px;
            margin-top: 15px;
            border-radius: 4px;
        }

        .patient-info {
            margin-bottom: 20px;
        }

        .patient-info h2 {
            margin-bottom: 10px;
        }

        .sub-patient-info {
            margin-top: 20px;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }

        .sub-patient-info h2 {
            margin-bottom: 10px;
        }

        .sub-patient-info img {
            max-width: 200px;
            height: auto;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 5px;
            background-color: #fff;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Patient Profile</h1>

    <div class="patient-info">
        <h2>Patient Information</h2>
        <p><strong>Email:</strong> <%= patient.getEmail() %></p>
        <p><strong>Phone Number:</strong> <%= patient.getPhoneNumber() %></p>
        <p><strong>Name:</strong> <%= patient.getFirstName() %> <%= patient.getLastName() %></p>
        <p><strong>Date of Birth:</strong> <%= patient.getDob() %></p>
        <p><strong>Address:</strong> <%= patient.getAddress() %></p>
    </div>

    <% if (patient.patient.getName() != null) { %>
        <div class="sub-patient-info">
            <h2>Sub-Patient Information</h2>
            <p><strong>Name:</strong> <%= patient.patient.getName() %></p>
            <p><strong>Relationship:</strong> <%= patient.patient.getRelationship() %></p>
            <p><strong>Symptoms:</strong> <%= Arrays.toString(patient.patient.getSymptoms()) %></p>
            <p><strong>Injured:</strong> <%= patient.patient.isInjured() ? "Yes" : "No" %></p>
            <p><strong>Injured Kind:</strong> <%= patient.patient.getInjuredKind() %></p>
            <% if (patient.patient.getInjuryImage() != null) { %>
                <strong>Injured image</strong>
                <img src="data:image/jpg;base64, <%= new String(Base64.getEncoder().encode(patient.patient.getInjuryImage())) %>" alt="Injury Image">
            <% } %>
        </div>
    <% } %>

   <table>
    <tr>
        <th>Case Number</th>
        <th>Status</th>
        <th>Action</th>
        <th>Present</th>
        <th>Department</th>
        <th>Treatment</th>
        <th>Priority</th>
        <th>Follow Doctor</th>
    </tr>
    <% if (cases != null) { %>
        <% for (Case c : cases) { %>
            <tr>
                <td><%= c.getCaseNumber() %></td>
                <td><%= c.getStatus() %></td>
                <td>
                    <form action="patient_manage_cases.jsp" method="post" class="action-form">
                        <input type="hidden" name="caseNumber" value="<%= c.getCaseNumber() %>"/>
                        <input type="hidden" name="action" value="cancel"/>
                        <button type="submit" class="action-button">Cancel</button>
                    </form>
                    <form action="patient_manage_cases.jsp" method="post" class="action-form">
                        <input type="hidden" name="caseNumber" value="<%= c.getCaseNumber() %>"/>
                        <input type="hidden" name="action" value="update"/>
                        <button type="submit" class="action-button">Update Case</button>
                    </form>
                </td>
               
                <td>
                            <input type="checkbox" disabled <%= c.isPresent() ? "checked" : "" %>/>
                        </td>
                <td>
                    <% if (c.getDepartment() != null) { %>
                        <%= c.getDepartment() %>
                    <% } else { %>
                        undefined
                    <% } %>
                </td>
                <td>
                    <% if (c.getTreatment() != null) { %>
                        <%= c.getTreatment() %>
                    <% } else { %>
                        undefined
                    <% } %>
                </td>
                
                    <% if (c.getPriority() != null) { %>
                        <td style="background-color: <%= Screening_Nurse.getPriorityColor(c.getPriority()) %> ;">
                        <%=c.getPriority()%>
                    <% } else { %>
                        undefined
                    <% } %>
                
                <td>
                    <% if (c.getFollowDoctor() != null) { %>
                        <%= c.getFollowDoctor() %>
                    <% } else { %>
                        undefined
                    <% } %>
                </td>
            </tr>
        <% } %>
    <% } else { %>
        <tr>
            <td colspan="9">No cases available</td>
        </tr>
    <% } %>
</table>
<form action="patient_manage_cases.jsp" method="post" class="action-form">
                        <input type="hidden" name="caseNumber" value="newCase"/>
                        <input type="hidden" name="action" value="newCase"/>
                        <button type="submit" class="action-button"> Generate New Case </button>
                    </form>
<form action="patient_manage_cases.jsp" method="post" class="action-form">
                        <input type="hidden" name="caseNumber" value="sign_out"/>
                        <input type="hidden" name="action" value="sign_out"/>
                        <button type="submit" class="action-button"> Sign out </button>
                    </form>
    <%-- Add more actions or information as needed --%>
</div>
<% if (!updateMessage.isEmpty()) { %>
    <div class="update-box">
        <%= updateMessage %>
    </div>
<% } %>
</body>
</html>
