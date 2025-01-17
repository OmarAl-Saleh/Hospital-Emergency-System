package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Admission_Officer {

	private String name;
	private String password;
	private Date submit_Date;
	

	private Case [] cases;
	

	
  public Admission_Officer(String name, String password, Case[] cases) {
		super();
		this.name = name;
		this.password = password;
		this.cases = cases;
	}
  
  public Admission_Officer(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
  
  
  
  public void cancelCase(String number) {
	  
	  for(int i=0;i<this.cases.length;i++)
      {
        	if (cases[i].getCaseNumber().equals(number)) {
        		cases[i].setStatusCanceled();
        		try {
					cases[i].updateStatus();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        
      }
  }
  
public void rejectCase(String number) {
	  
	for(int i=0;i<this.cases.length;i++)
    {
      	if (cases[i].getCaseNumber().equals(number)) {
      		cases[i].setStatusRejected();
      		try {
				cases[i].updateStatus();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      	}
      
    }
  }

public void RenewCase(String number) {
	  
	for(int i=0;i<this.cases.length;i++)
    {
      	if (cases[i].getCaseNumber().equals(number)) {
      		cases[i].setStatusNew();
      		try {
				cases[i].updateStatus();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      	}
      
    }
}

public void admitPatient(String number)
{
	 for(int i=0 ; i<this.cases.length;i++)
	  {
		  if(cases[i].getCaseNumber().equals(number))
			  cases[i].setPresent(true);
		  try {
				cases[i].updatePresent();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	  }
}

public boolean hourValidate(String number)
{
	for(int i=0 ; i<this.cases.length;i++)
	  {
		  if(cases[i].getCaseNumber().equals(number))
			  
			  
	      if(cases[i].hour(this.submit_Date))
	      {	  
	    	  this.cancelCase(number);
	    	  return true;
	      }
	  }
	return false;

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
	
	public Date getSubmit_Date() {
		return submit_Date;
	}

	public void setSubmit_Date(Date submit_Date) {
		this.submit_Date = submit_Date;
	}
	
	

	
	// Method to insert a new admission officer into the database
    public void insertAdmissionOfficer() throws SQLException {
        String insertSql = "INSERT INTO admission_officer (name, password) VALUES (?, ?)";

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(insertSql)) {

            stmt.setString(1, this.name);
            stmt.setString(2, this.password);

            stmt.executeUpdate();
        }
    }
    
    
 // Method to retrieve an admission officer from the database by name and password
    public static Admission_Officer selectAdmissionOfficer(String name, String password) throws SQLException {
        String selectSql = "SELECT submit_date FROM admission_officer WHERE name = ? AND password = ?";
        Admission_Officer officer = null;

        try (Connection conn = database.connect();
             PreparedStatement stmt = conn.prepareStatement(selectSql)) {

            stmt.setString(1, name);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Timestamp submitDate = rs.getTimestamp("submit_date");
                    officer = new Admission_Officer(name, password, null); // Assuming cases array is not needed here
                    // Set the submit date
                    officer.setSubmit_Date(new Date(submitDate.getTime()));
                }
            }
        }
        return officer;
    } 

}
