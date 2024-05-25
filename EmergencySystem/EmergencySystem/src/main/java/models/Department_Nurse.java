package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Department_Nurse {


	

	private String name;
	private String password;
	private String DoctorName; 
	private String DepartmentName;
	private Case [] cases;
	
	
    
	public Department_Nurse(String name, String password, String doctorName,String DepartmentName, Case[] cases) {
		super();
		this.name = name;
		this.password = password;
		DoctorName = doctorName;
		this.DepartmentName= DepartmentName;
		this.cases = cases;
	}
	
	public Department_Nurse(String name, String password, String doctorName,String DepartmentName) {
		super();
		this.name = name;
		this.password = password;
		DoctorName = doctorName;
		this.DepartmentName= DepartmentName;
		
	}
	
	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	
	public void TransferredCase(String number) {
		for(int i=0;i<this.cases.length;i++)
	    {
	      	if (cases[i].getCaseNumber().equals(number)) {
	      		cases[i].setStatusTransferred();
	      		try {
					cases[i].updateStatus();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      	}
	      
	    }
	  }
	
	public void ClosedCase(String number,String treatment) {
		  
		setCaseTreatment(number,treatment);
		for(int i=0;i<this.cases.length;i++)
	    {
	      	if (cases[i].getCaseNumber().equals(number)) {
	      		cases[i].setStatusClosed();
	      		try {
					cases[i].updateStatus();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      	}
	      
	    }
	  }
	
	private void setCaseTreatment(String number,String treatment) {
		  
		for(int i=0 ; i<this.cases.length;i++)
		  {
			  if(cases[i].getCaseNumber().equals(number))
				  cases[i].setTreatment(treatment);
			  try {
					cases[i].updateTreatment();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
	  }
	
	public void setCaseFollowDoctor(String number,String doctorName) {
		 
		for(int i=0;i<this.cases.length;i++)
	      {
	        	if (cases[i].getCaseNumber().equals(number)) {
	        		cases[i].setFollowDoctor(doctorName);
	        		try {
						
						cases[i].updateFollow_Doctor();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        	
	      }
	  }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDoctorName() {
		return DoctorName;
	}
	public void setDoctorName(String doctorName) {
		DoctorName = doctorName;
	}
	public Case[] getCases() {
		return cases;
	}

	public void setCases(Case[] cases) {
		this.cases = cases;
	}
	
	 // Method to insert a new department nurse into the database
    public void insertDepartmentNurse() throws SQLException {
        String insertSql = "INSERT INTO department_nurse (name, password, doctor_name ,department_name) VALUES (?, ?, ?,?)";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(insertSql)) {

            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setString(3, DoctorName);
            stmt.setString(4, DepartmentName);
            stmt.executeUpdate();
        }
    }

    // Method to retrieve a department nurse from the database by name and password
    public static Department_Nurse selectDepartmentNurse(String name, String password) throws SQLException {
        String selectSql = "SELECT * FROM department_nurse WHERE name = ? AND password = ?";
        Department_Nurse nurse = null;

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(selectSql)) {

            stmt.setString(1, name);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Nurse found, create a new instance
                    String doctorName = rs.getString("doctor_name");
                    String departmentName=rs.getString("department_name");
                    nurse = new Department_Nurse(name, password, doctorName,departmentName, null); // Assuming cases array is not needed here
                }
            }
        }
        return nurse;
    }
	
}
