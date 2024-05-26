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
<script>
    function togglePatientInfo() {
        var checkbox = document.querySelector('input[name="sub_patient"]');
        var patientInfoDiv = document.getElementById('patient-info');
        patientInfoDiv.style.display = checkbox.checked ? 'block' : 'none';
    }
	
    function toggleInjuryInfo() {
        var checkbox = document.querySelector('input[name="injured"]');
        var patientInfoDiv = document.getElementById('injury');
        patientInfoDiv.style.display = checkbox.checked ? 'block' : 'none';
    }
    
    window.onload = function() {
        var checkbox = document.querySelector('input[name="sub_patient"]');
        checkbox.addEventListener('change', togglePatientInfo);
        togglePatientInfo(); // Initial check to set the correct state on page load
        
        var checkbox2 = document.querySelector('input[name="injured"]');
        checkbox2.addEventListener('change', toggleInjuryInfo);
        toggleInjuryInfo();
    }
    
    function toggleInjuryInfo() {
        var checkbox = document.querySelector('input[name="injured"]');
        var patientInfoDiv = document.getElementById('injury');
        patientInfoDiv.style.display = checkbox.checked ? 'block' : 'none';
    }
    
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
<form action="patient" method="get">
    <h1>Patient Sign in </h1>
    <input type="hidden" name="source" value="sign-in"/>
    <label>Enter your email</label>
    <input type="email" name="email" required/>
    <br>
    <label>Enter your Phone Number</label>
    <input type="tel" name="phoneNumber" required/>
    <br>
    <label>Check if you are submitting this case on behalf of someone else</label>
    <input type="checkbox" name="sub_patient" />
    <div id="patient-info" class="hidden-section">
        <br>
        <label>Enter Patient Name</label>
        <input type="text" name="sub_patient_name"/>
        <br>
        <label>Enter Patient Relation</label>
        <input type="text" name="sub_patient_relation"/>
        
        <br>
        <label for="symptoms">symptoms</label>
        <br>
        <div id="subsymptomscontainer">
            <textarea name="symptoms[]"></textarea>
        </div>
        <br>
        <button type="button" class="add-button" onclick="addField('subsymptomscontainer', 'symptoms')">Add Another symptom</button>
    <label>is the patient injured?</label>
    <input type="checkbox" name="injured" />  
    <div id="injury" class="hidden-section">
    	<br>
        <label>Enter injury</label>
        <input type="text" name="injury_name"/>
        <br>
        <label for="img">injury image:</label>
  		<input type="file" id="img" name="img" accept="image/*">
 	 	<br>
    </div> 
    </div>
    <br>
    <input type="submit" value="Submit" />
</form>
</body>
</html>