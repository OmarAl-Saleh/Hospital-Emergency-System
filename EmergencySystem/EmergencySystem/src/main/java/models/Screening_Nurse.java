package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Screening_Nurse {
	

	private String name;
	private String password;
	private Case [] cases;
	
	
	public Screening_Nurse(String name, String password, Case[] cases) {
		super();
		this.name = name;
		this.password = password;
		this.cases = cases;
	}
	
	
	
	public Screening_Nurse(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	
	public void SetCasePriority(String number , String priority) {
		for(int i=0;i<this.cases.length;i++)
	      {
	        	if (cases[i].getCaseNumber().equals(number)) {
	        		cases[i].setPriority(priority);
	        		InitialAssessmentCase(number);
	        		try {
						cases[i].updatePriority();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        	
	      }
		  
	  }
	
	public void InitialAssessmentCase(String number) {
		  
		for(int i=0;i<this.cases.length;i++)
	      {
	        	if (cases[i].getCaseNumber().equals(number)) {
	        		cases[i].setStatusInitialAssessment();
	        		try {
						cases[i].updateStatus();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        
	      }
	  }
	
	public void AssignedCase(String number) {
		  
		for(int i=0;i<this.cases.length;i++)
	      {
	        	if (cases[i].getCaseNumber().equals(number)) {
	        		cases[i].setStatusAsigned();
	        		try {
						cases[i].updateStatus();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        
	      }
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
	
	public void setCaseDepartment(String number,String Dep) {
		 
		for(int i=0;i<this.cases.length;i++)
	      {
	        	if (cases[i].getCaseNumber().equals(number)) {
	        		cases[i].setDepartment(Dep);
	        		AssignedCase(number);
	        		try {
						cases[i].updateStatus();
						cases[i].updateDepartment();
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

	public Case[] getCases() {
		return cases;
	}

	public void setCases(Case[] cases) {
		this.cases = cases;
	}

	// Method to insert a new screening nurse into the database
    public void insertScreeningNurse() throws SQLException {
        String insertSql = "INSERT INTO screening_nurse (name, password) VALUES (?, ?)";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(insertSql)) {

            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.executeUpdate();
        }
    }
    
 // Method to retrieve a screening nurse from the database by name and password
    public static Screening_Nurse selectScreeningNurse(String name, String password) throws SQLException {
        String selectSql = "SELECT * FROM screening_nurse WHERE name = ? AND password = ?";
        Screening_Nurse nurse = null;

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(selectSql)) {

            stmt.setString(1, name);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Nurse found, create a new instance
                    nurse = new Screening_Nurse(name, password, null); // Assuming cases array is not needed here
                }
            }
        }
        return nurse;
    }
    
   public static String getPriorityColor(String priority) {
        if (priority == null) {
            return "transparent";
        }
        switch (priority) {
            case "Emergency":
                return "red";
            case "Very urgent":
                return "orange";
            case "Urgent":
                return "yellow";
            case "Less urgent":
                return "green";
            case "Non-urgent":
                return "blue";
            default:
                return "transparent";
        }
    }
	
}

