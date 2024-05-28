Online Hospital Emergency System
Project Description
This is a web application developed for managing hospital emergency cases. The system allows patients to register, submit emergency cases, and get assigned to appropriate medical departments based on the severity of their conditions. Hospital staff can manage, review, and update the status of these cases.

Group Members
[Student 1]
[Student 2]
[Student 3]
Deadline
Sunday, May 26th, 2024

Technologies Used
Java Servlets
JSP (JavaServer Pages)
HTML, CSS, JavaScript
Oracle Database
MVC Architectural Pattern
Features
Patient Registration
Patients can register using their email address and phone number. The registration form includes the following required fields:

First Name
Last Name
Date of Birth
Address
Medical History
Chronic Diseases
Allergies
Case Submission
Patients can submit a case before arriving at the hospital with the following information:

Checkbox: "Are you submitting this case on behalf of someone else?"
If checked:
Enter patient’s name
Relationship to patient: Friend or Family
Symptoms: Description of the patient’s condition
When did it start: Text field
Is the patient injured? Yes/No
If Yes, what kind of injury
Upload an image of the injury
Case Management
Upon submission, the case status is set to "New".
Each case is assigned a unique case number in the format Cxxxxxxxx.
Patients can update or cancel their case within the same session, valid for 10 minutes.
The Admission Officer can review, cancel, or reopen cases based on provided information.
Case status can be updated by hospital staff through various stages: Initial Assessment, Assigned, Closed, Transferred, Rejected, Canceled.
Case Prioritization
The screening nurse assigns a priority level to each case based on severity:

Emergency (priority 1 - red): Immediate response
Very urgent (priority 2 - orange): Up to 1 hour
Urgent (priority 3 - yellow): 1-3 hours
Less urgent (priority 4 - green): 3-6 hours
Non-urgent (priority 5 - blue): 6-10 hours
Priority levels and waiting times are configurable in the web.xml file.

Department Assignment
The screening nurse assigns the case to a suitable department for treatment:

Triage
Treatment Rooms
Resuscitation Area
Diagnostic Imaging
Laboratory
Observation Area
Psychiatric Emergency Services
Follow-up and Closure
Nurses and doctors in each department can close a case after providing necessary treatment or transfer the patient to another hospital.
Patients may be assigned follow-up appointments with hospital doctors.
Database Schema
The system uses an Oracle Database to store all information, including:

Patient details
Case details
Department staff (nurses and doctors)
Case statuses and updates
