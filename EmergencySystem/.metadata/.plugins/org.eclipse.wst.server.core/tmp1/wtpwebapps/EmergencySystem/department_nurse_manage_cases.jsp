<%@ page import="java.util.*, models.Department_Nurse, models.Case" %>
<%@ page session="true" %>
<%
    Department_Nurse nurse = (Department_Nurse) session.getAttribute("department_nurse");

    if (nurse == null) {
        response.sendRedirect("department_nurse/sign-in.jsp");
        return;
    }

    Case[] cases = nurse.getCases();

    String caseNumber = request.getParameter("caseNumber");
    String action = request.getParameter("action");
    String updateMessage = "";

    if (caseNumber != null && action != null) {
        switch (action) {
            case "close":
                String treatment = request.getParameter("treatment");
                nurse.ClosedCase(caseNumber, treatment);
                updateMessage = "Case " + caseNumber + " has been closed.";
                break;
            case "transfer":
                nurse.TransferredCase(caseNumber);
                updateMessage = "Case " + caseNumber + " has been transferred.";
                break;
           
            case "follow":
            	String doctorName=request.getParameter("doctorName");
            	nurse.setCaseFollowDoctor(caseNumber, doctorName);
            	updateMessage = "Case " + caseNumber + " Will followed by "+doctorName;
            	break;
            	
            case "sign_out":
            	response.sendRedirect("index.jsp");
            	return;
        }
    }

    cases = nurse.getCases();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
    </style>
</head>
<body>
<div class="container">
    <h1>Manage Cases</h1>
    <table>
        <tr>
            <th>Case Number</th>
            <th>Status</th>
            <th>Action</th>
            <th>Treatment</th>
        </tr>
        <% if (cases != null) { %>
            <% for (Case c : cases) { %>
                <tr>
                    <td><%= c.getCaseNumber() %></td>
                    <td><%= c.getStatus() %></td>
                    <td>
                        <form action="department_nurse_manage_cases.jsp" method="post" class="action-form">
                            <input type="hidden" name="caseNumber" value="<%= c.getCaseNumber() %>"/>
                            <input type="hidden" name="action" value="close"/>
                            <input type="text" name="treatment" placeholder="Enter treatment"/>
                            <button type="submit" class="action-button">Close</button>
                        </form>
                        
                        <form action="department_nurse_manage_cases.jsp" method="post" class="action-form">
                            <input type="hidden" name="caseNumber" value="<%= c.getCaseNumber() %>"/>
                            <input type="hidden" name="action" value="transfer"/>
                            <button type="submit" class="action-button">Transfer</button>
                        </form>
                    </td>
                    <td><%= c.getTreatment() %></td>
                    <td>
                        <form action="department_nurse_manage_cases.jsp" method="post" class="action-form">
                            <input type="hidden" name="caseNumber" value="<%= c.getCaseNumber() %>"/>
                            <input type="hidden" name="action" value="follow"/>
                            <label for="follow">Select Doctor To Follow With:</label>
                            <select name="doctorName" id="doctorName">
                                <option value="Dr. OMAR">Dr. OMAR</option>
                                <option value="Dr. HASHEM">Dr. HASHEM</option>
                                <option value="Dr. MOYYAD">Dr. MOYYAD</option>
                                <!-- Add more options as needed -->
                            </select>
                            <button type="submit" class="action-button">Assign Doctor</button>
                        </form>
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
