<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient sign in page</title>
<style>
   body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        form {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: auto;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
   
    input[type="text"], input[type="email"], input[type="tel"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    input[type="checkbox"] {
        margin-bottom: 15px;
    }

    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 10px 20px;
        cursor: pointer;
        border-radius: 4px;
    }

    input[type="submit"]:hover {
        background-color: #45a049;
    }

    .hidden-section {
        display: none;
    }
    .add-button {
            background-color: #008CBA;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 4px;
            margin-bottom: 15px;
        }
    
</style>

</head>
<body>
<form action="patient" method="get">
    <h1>Patient Sign in </h1>
    <input type="hidden" name="source" value="sign-in"/>
    <label>Enter your email</label>
    <input type="email" name="email" required/>
    <br>
    <label>Enter your Phone Number</label>
    <input type="tel" name="phoneNumber" required/>
   
    <br>
    <input type="submit" value="Submit" />
</form>
</body>
</html>