<%@ page import="java.util.*, models.Admission_Officer, models.Case" %>
<%@ page session="true" %>
<%
    // Retrieve the Admission_Officer object from the session
    Admission_Officer officer = (Admission_Officer) session.getAttribute("off_signIn");

    if (officer == null) {
        response.sendRedirect("officer/sign-in.jsp");
        return;
    }

    // Get the list of cases
    Case[] cases = officer.getCases();

    // Check if a case number is provided for actions
    String caseNumber = request.getParameter("caseNumber");
    String action = request.getParameter("action");

    String updateMessage = "";

    if (caseNumber != null && action != null) {
        switch (action) {
            case "cancel":
                officer.cancelCase(caseNumber);
                updateMessage = "Case " + caseNumber + " has been canceled.";
                break;
            case "reopen":
                officer.RenewCase(caseNumber);
                updateMessage = "Case " + caseNumber + " has been reopened.";
                break;
            case "admit":
                officer.admitPatient(caseNumber);
                updateMessage = "Case " + caseNumber + " has been admitted.";
                break;
            case "reject":
                officer.rejectCase(caseNumber);
                updateMessage = "Case " + caseNumber + " has been rejected.";
                break;
            case "hour":
                boolean exceeded = officer.hourValidate(caseNumber);
                updateMessage = "Case " + caseNumber + " exceeded one hour: " + exceeded;
                break;
            case "sign_out":
            	response.sendRedirect("index.jsp");
            	return;
        }
    }

    // Refresh the cases list after actions
    cases = officer.getCases();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Manage Cases</title>
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

        input[type="text"], input[type="password"], input[type="email"], input[type="tel"] {
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
            margin-left:10px;
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
    <div class="container">
        <h1>Manage Cases</h1>
        <table>
            <tr>
                <th>Case Number</th>
                <th>Status</th>
                <th>Actions</th>
                <th>Patient is present</th>
            </tr>
            <% if (cases != null) { %>
                <% for (Case c : cases) { %>
                    <tr>
                        <td><%= c.getCaseNumber() %></td>
                        <td><%= c.getStatus() %></td>
                        <td>
                            <% if (!"Canceled".equals(c.getStatus())) { %>
                                <form action="officer_manage_cases.jsp" method="post" class="action-form" >
                                    <input type="hidden" name="caseNumber" value="<%= c.getCaseNumber() %>"/>
                                    <input type="hidden" name="action" value="cancel"/>
                                    <button type="submit" class="action-button">Cancel</button>
                                </form>
                            <% } else { %>
                                <form action="officer_manage_cases.jsp" method="post" class="action-form" >
                                    <input type="hidden" name="caseNumber" value="<%= c.getCaseNumber() %>"/>
                                    <input type="hidden" name="action" value="reopen"/>
                                    <button type="submit" class="action-button">Reopen</button>
                                </form>
                            <% } %>
                            <form action="officer_manage_cases.jsp" method="post" class="action-form" >
                                <input type="hidden" name="caseNumber" value="<%= c.getCaseNumber() %>"/>
                                <input type="hidden" name="action" value="admit"/>
                                <button type="submit" class="action-button">Admit</button>
                            </form>
                            <form action="officer_manage_cases.jsp" method="post" class="action-form" >
                                <input type="hidden" name="caseNumber" value="<%= c.getCaseNumber() %>"/>
                                <input type="hidden" name="action" value="reject"/>
                                <button type="submit" class="action-button">Reject</button>
                            </form>
                            <form action="officer_manage_cases.jsp" method="post" class="action-form" >
                                <input type="hidden" name="caseNumber" value="<%= c.getCaseNumber() %>"/>
                                <input type="hidden" name="action" value="hour"/>
                                <button type="submit" class="action-button">Exceed one hour</button>
                            </form>
                        </td>
                        <td>
                            <input type="checkbox" disabled <%= c.isPresent() ? "checked" : "" %>/>
                        </td>
                    </tr>
                <% } %>
            <% } else { %>
                <tr>
                    <td colspan="4">No cases available</td>
                </tr>
            <% } %>
        </table>
        <form action="patient_manage_cases.jsp" method="post" class="action-form">
                        <input type="hidden" name="caseNumber" value="sign_out"/>
                        <input type="hidden" name="action" value="sign_out"/>
                        <button type="submit" class="action-button"> Sign out </button>
                    </form>
        <% if (!updateMessage.isEmpty()) { %>
            <div class="update-box">
                <%= updateMessage %>
            </div>
        <% } %>
    </div>
</body>
</html>
