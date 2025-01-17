package models;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Case {

    


	private static int caseCounter = 0;
    private String caseNumber;
    private String status;
    private String treatment;
    private Date submitDate;
    private String priority;
    private String department;
    private String phoneNumber; // to link with the patient
    private String followDoctor;
    private boolean present=false;

  

    public Case() throws SQLException {
        super();
        this.caseNumber = generateCaseNumber();
        this.status = "New";
        this.treatment = "";
        this.priority = "";
        this.followDoctor="";
       // this.submitDate = new Date();
    }
    
    public Case(String phoneNumber) throws SQLException {
        super();
        this.caseNumber = generateCaseNumber();
        this.phoneNumber=phoneNumber;
        this.status = "New";
        this.treatment = "";
        this.priority = "";
        this.followDoctor="";
       // this.submitDate = new Date();
    }

    public Case(String status, String treatment, String priority, String department, String phoneNumber,boolean present,String follow_doctor) throws SQLException {
        this.caseNumber = generateCaseNumber();
        this.status = status;
        this.treatment = treatment;
        this.priority = priority;
        this.department = department;
        this.present=present;
        this.phoneNumber = phoneNumber;
        this.followDoctor=follow_doctor;
    }
    
    
    public boolean hour(Date officer)
    {
    	long difference=officer.getTime()-this.submitDate.getTime();
    	
    	difference= difference/(60*60*1000);
    	
    	return difference>1;
    	
    }
    

	public String getFollowDoctor() {
		return followDoctor;
	}

	public void setFollowDoctor(String followDoctor) {
		this.followDoctor = followDoctor;
	}

    
    public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

//    private static synchronized String generateCaseNumber() {
//        caseCounter++;
//        DecimalFormat df = new DecimalFormat("C00000000");
//        return df.format(caseCounter);
//    }
    
    private static synchronized String generateCaseNumber() throws SQLException {
        // Query to count the number of rows in the case table
        String countQuery = "SELECT COUNT(*) FROM case";
        
        // Connect to the database and execute the query
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(countQuery);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                caseCounter = rs.getInt(1); // Get the row count
            }
        }
        
        // Increment the counter
        caseCounter++;
        
        // Format the case number
        DecimalFormat df = new DecimalFormat("C00000000");
        return df.format(caseCounter);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setStatusNew()
	{
		this.setStatus("New");
	}
	
//	public void setStatusCanceled()
//	{
//		if(present==true)
//		cases.setStatus("Canceled");
//	}
//	
	public void setStatusRejected()
	{
		this.setStatus("Rejected");
	}
	
	public void setStatusInitialAssessment()
	{
		this.setStatus("Initial Assessment");
		
	}
	
	public void setStatusAsigned()
	{
		this.setStatus("Asigned");
	}
	
	public void setStatusClosed()
	{
		this.setStatus("Closed");
	}
	
	public void setStatusCanceled()
	{
		this.setStatus("Canceled");
	}
	
	
	public void setStatusTransferred()
	{
		this.setStatus("Transferred");
	}

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

 
 // Method to insert a new case into the database
    public void insertCase() throws SQLException {
        String caseSql = "INSERT INTO case (case_number, status, treatment, priority, department, phone_number, present, follow_doctor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = database.connect();
             PreparedStatement caseStmt = conn.prepareStatement(caseSql)) {

            caseStmt.setString(1, this.caseNumber);
            caseStmt.setString(2, this.status);
            caseStmt.setString(3, this.treatment);
            caseStmt.setString(4, this.priority);
            caseStmt.setString(5, this.department);
            caseStmt.setString(6, this.phoneNumber);
            caseStmt.setBoolean(7, this.present);
            caseStmt.setString(8, this.followDoctor); // Add the follow_doctor field
            caseStmt.executeUpdate();
        }
    }

    
    
    // Methods to update the Case 
    
 // Method to update the status of the case
    public void updateStatus() throws SQLException {
        String updateSql = "UPDATE case SET status = ? WHERE case_number = ?";
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(updateSql)) {

            stmt.setString(1, this.status);
            stmt.setString(2, this.caseNumber);
            stmt.executeUpdate();
        }
    }

    // Method to update the treatment of the case
    public void updateTreatment() throws SQLException {
        String updateSql = "UPDATE case SET treatment = ? WHERE case_number = ?";
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(updateSql)) {

            stmt.setString(1, this.treatment);
            stmt.setString(2, this.caseNumber);
            stmt.executeUpdate();
        }
    }

    // Method to update the department of the case
    public void updateDepartment() throws SQLException {
        String updateSql = "UPDATE case SET department = ? WHERE case_number = ?";
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(updateSql)) {

            stmt.setString(1, this.department);
            stmt.setString(2, this.caseNumber);
            stmt.executeUpdate();
        }
    }

    // Method to update the priority of the case
    public void updatePriority() throws SQLException {
        String updateSql = "UPDATE case SET priority = ? WHERE case_number = ?";
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(updateSql)) {

            stmt.setString(1, this.priority);
            stmt.setString(2, this.caseNumber);
            stmt.executeUpdate();
        }
    }
    
 // Method to update the priority of the case
    public void updateFollow_Doctor() throws SQLException {
        String updateSql = "UPDATE case SET follow_doctor = ? WHERE case_number = ?";
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(updateSql)) {

            stmt.setString(1, this.followDoctor);
            stmt.setString(2, this.caseNumber);
            stmt.executeUpdate();
        }
    }


    // Method to update the present status of the case
    public void updatePresent() throws SQLException {
        String updateSql = "UPDATE case SET present = ? WHERE case_number = ?";
        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(updateSql)) {

            stmt.setBoolean(1, this.present);
            stmt.setString(2, this.caseNumber);
            stmt.executeUpdate();
        }
    }

 // Method to retrieve a case from the database by case number
    public static Case selectCase(String caseNumber) throws SQLException {
        String caseSql = "SELECT * FROM case WHERE case_number = ?";
        Case c = null;

        try (Connection conn = database.connect();
             PreparedStatement caseStmt = conn.prepareStatement(caseSql)) {

            caseStmt.setString(1, caseNumber);
            ResultSet rs = caseStmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString("status");
                String treatment = rs.getString("treatment");
                Timestamp submitDate = rs.getTimestamp("submit_date");
                String priority = rs.getString("priority");
                String department = rs.getString("department");
                String phoneNumber = rs.getString("phone_number");
                boolean present = rs.getBoolean("present");
                String followDoctor = rs.getString("follow_doctor");

                c = new Case(status, treatment, priority, department, phoneNumber, present, followDoctor);
                c.caseNumber = caseNumber;
                c.submitDate = new Date(submitDate.getTime());
            }
        }
        return c;
    }


 // Method to retrieve cases from the database by phone number
    public static Case[] selectCasesByPhoneNumber(String phoneNumber) throws SQLException {
        String caseSql = "SELECT * FROM case WHERE phone_number = ? ";
        List<Case> caseList = new ArrayList<>();

        try (Connection conn = database.connect();
             PreparedStatement caseStmt = conn.prepareStatement(caseSql)) {

            caseStmt.setString(1, phoneNumber);
            ResultSet rs = caseStmt.executeQuery();

            while (rs.next()) {
                String caseNumber = rs.getString("case_number");
                String status = rs.getString("status");
                String treatment = rs.getString("treatment");
                Timestamp submitDate = rs.getTimestamp("submit_date");
                String priority = rs.getString("priority");
                String department = rs.getString("department");
                boolean present = rs.getBoolean("present");
                String followDoctor = rs.getString("follow_doctor");

                Case c = new Case(status, treatment, priority, department, phoneNumber, present, followDoctor);
                c.caseNumber = caseNumber;
                c.submitDate = new Date(submitDate.getTime());

                caseList.add(c);
            }
        }

        // Convert list to array
        return caseList.toArray(new Case[0]);
    }

    
 // Method to retrieve cases from the database that are present and not closed
    public static Case[] selectAdmittedCases() throws SQLException {
        String caseSql = "SELECT * FROM case WHERE present = 1 AND status <> 'Closed'";
        List<Case> casesList = new ArrayList<>();

        try (Connection conn = database.connect();
             PreparedStatement caseStmt = conn.prepareStatement(caseSql)) {

            ResultSet rs = caseStmt.executeQuery();

            while (rs.next()) {
                String caseNumber = rs.getString("case_number");
                String status = rs.getString("status");
                String treatment = rs.getString("treatment");
                Timestamp submitDate = rs.getTimestamp("submit_date");
                String priority = rs.getString("priority");
                String department = rs.getString("department");
                String phoneNumber = rs.getString("phone_number");
                boolean present = rs.getBoolean("present");
                String followDoctor = rs.getString("follow_doctor");

                Case c = new Case(status, treatment, priority, department, phoneNumber, present, followDoctor);
                c.caseNumber = caseNumber;
                c.submitDate = new Date(submitDate.getTime());

                casesList.add(c);
            }
        }

        return casesList.toArray(new Case[0]);
    }

    
    
 // Method to retrieve cases from the database by department name that are present and not closed
    public static Case[] selectDepartmentCases(String departmentName) throws SQLException {
        String caseSql = "SELECT * FROM case WHERE present = 1 AND status <> 'Closed' AND department = ?";
        List<Case> casesList = new ArrayList<>();

        try (Connection conn = database.connect();
             PreparedStatement caseStmt = conn.prepareStatement(caseSql)) {

            caseStmt.setString(1, departmentName);
            ResultSet rs = caseStmt.executeQuery();

            while (rs.next()) {
                String caseNumber = rs.getString("case_number");
                String status = rs.getString("status");
                String treatment = rs.getString("treatment");
                Timestamp submitDate = rs.getTimestamp("submit_date");
                String priority = rs.getString("priority");
                String department = rs.getString("department");
                String phoneNumber = rs.getString("phone_number");
                boolean present = rs.getBoolean("present");
                String followDoctor = rs.getString("follow_doctor");

                Case c = new Case(status, treatment, priority, department, phoneNumber, present, followDoctor);
                c.caseNumber = caseNumber;
                c.submitDate = new Date(submitDate.getTime());

                casesList.add(c);
            }
        }

        return casesList.toArray(new Case[0]);
    }


    
    
 // Method to retrieve cases from the database with status not equal to "Closed"
    public static Case[] selectOfficerCases() throws SQLException {
        String caseSql = "SELECT * FROM case WHERE status <> 'Closed'";
        List<Case> casesList = new ArrayList<>();

        try (Connection conn = database.connect();
             PreparedStatement caseStmt = conn.prepareStatement(caseSql)) {

            ResultSet rs = caseStmt.executeQuery();

            while (rs.next()) {
                String caseNumber = rs.getString("case_number");
                String status = rs.getString("status");
                String treatment = rs.getString("treatment");
                Timestamp submitDate = rs.getTimestamp("submit_date");
                String priority = rs.getString("priority");
                String department = rs.getString("department");
                String phoneNumber = rs.getString("phone_number");
                boolean present = rs.getBoolean("present");
                String followDoctor = rs.getString("follow_doctor");

                Case c = new Case(status, treatment, priority, department, phoneNumber, present, followDoctor);
                c.caseNumber = caseNumber;
                c.submitDate = new Date(submitDate.getTime());

                casesList.add(c);
            }
        }

        // Convert the list to an array
        return casesList.toArray(new Case[0]);
    }


    @Override
    public String toString() {
        return "Case{" +
                "caseNumber='" + caseNumber + '\'' +
                ", status='" + status + '\'' +
                ", treatment='" + treatment + '\'' +
                ", submitDate=" + submitDate +
                ", priority='" + priority + '\'' +
                ", department='" + department + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
