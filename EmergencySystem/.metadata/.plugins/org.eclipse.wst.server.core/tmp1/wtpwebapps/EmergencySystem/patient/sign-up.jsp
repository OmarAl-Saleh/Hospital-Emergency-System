<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Patient Profile Form</title>
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

        input[type="text"], input[type="date"], input[type="email"], input[type="tel"], textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        textarea {
            height: 100px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 4px;
            width: 100%;
            margin-top: 20px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
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

        .add-button:hover {
            background-color: #007bb5;
        }
    </style>
    <script>
        function addField(containerId, fieldName) {
            const container = document.getElementById(containerId);
            const textarea = document.createElement('textarea');
            textarea.name = fieldName + '[]';
            textarea.required = true;
            container.appendChild(textarea);
        }
    </script>
</head>
<body>
    <form action="patient" method="post">
        <h1>Patient Profile</h1>
        
        <input type="hidden" name="source" value="sign-up"/>
        
        <label for="email">Enter your email</label>
        <input type="email" id="email" name="email" required/>
        
        <label for="phoneNumber">Enter your Phone Number</label>
        <input type="tel" id="phoneNumber" name="phoneNumber" required/>
        
        <label for="firstName">First Name</label>
        <input type="text" id="firstName" name="firstName" required/>
        
        <label for="lastName">Last Name</label>
        <input type="text" id="lastName" name="lastName" required/>
        
        <label for="dob">Date of Birth</label>
        <input type="date" id="dob" name="dob" required/>
        
        <label for="address">Address</label>
        <input type="text" id="address" name="address" required/>
        
        <label for="medicalHistory">Medical History</label>
        <div id="medicalHistoryContainer">
            <textarea name="medicalHistory[]" required></textarea>
        </div>
        <button type="button" class="add-button" onclick="addField('medicalHistoryContainer', 'medicalHistory')">Add Another Medical History</button>
        
        <label for="chronicDiseases">Chronic Diseases</label>
        <div id="chronicDiseasesContainer">
            <textarea name="chronicDiseases[]" required></textarea>
        </div>
        <button type="button" class="add-button" onclick="addField('chronicDiseasesContainer', 'chronicDiseases')">Add Another Chronic Disease</button>
        
        <label for="allergies">Allergies</label>
        <div id="allergiesContainer">
            <textarea name="allergies[]" required></textarea>
        </div>
        <button type="button" class="add-button" onclick="addField('allergiesContainer', 'allergies')">Add Another Allergy</button>
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>
