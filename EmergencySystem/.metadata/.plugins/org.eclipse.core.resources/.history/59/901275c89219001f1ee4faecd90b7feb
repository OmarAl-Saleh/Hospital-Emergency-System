package models;

public class Department_Nurse {


	private String name;
	private String password;
	private String DoctorName; 
	private Patient [] patients;
	public Department_Nurse(String name, String password, String doctorName, Patient[] patients) {
		super();
		this.name = name;
		this.password = password;
		DoctorName = doctorName;
		this.patients = patients;
	}
	
	public void TransferredCase(String number) {
		  
		  for(int i=0 ; i<patients.length;i++)
		  {
			  if(number == patients[i].getCaseNumber())
				  patients[i].setStatusTransferred();
		  }
	  }
	
	public void ClosedCase(String number,String treatment) {
		  
		setCaseTreatment(number,treatment);
		  for(int i=0 ; i<patients.length;i++)
		  {
			  if(number == patients[i].getCaseNumber())
				  patients[i].setStatusClosed();
		  }
	  }
	
	private void setCaseTreatment(String number,String treatment) {
		  
		  for(int i=0 ; i<patients.length;i++)
		  {
			  if(number == patients[i].getCaseNumber())
				  patients[i].setCaseTreatment(treatment);
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
	public Patient[] getPatients() {
		return patients;
	}
	public void setPatients(Patient[] patients) {
		this.patients = patients;
	}
	
}
