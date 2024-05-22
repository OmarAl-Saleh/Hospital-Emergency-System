package models;

public class Screening_Nurse {
	

	private String name;
	private String password;
	private Patient [] patients;
	
	public Screening_Nurse(String name, String password, Patient[] patients) {
		super();
		this.name = name;
		this.password = password;
		this.patients = patients;
	}
	
public Patient[] getDepartmentPatients(String dep)	
{
	
	int len=0;
	for(int i=0;i<patients.length;i++)
	{
		if(patients[i].getCaseDepartment()==dep)
			len++;
	}
	
	Patient[] admittedPatients= new Patient[len];
	int index=0;
	
	for (int i = 0; i < patients.length; i++) {
        if (patients[i].getCaseDepartment()==dep) {
            admittedPatients[index] = patients[i];
            index++;
        }
    }
	
return admittedPatients;

}
	
	
	public void SetCasePriority(String number , String priority) {
		  
		  for(int i=0 ; i<patients.length;i++)
		  {
			  if(number == patients[i].getCaseNumber())
				  patients[i].setCasePriority(priority);	
		  }
		  InitialAssessmentCase(number);
	  }
	
	public void InitialAssessmentCase(String number) {
		  
		  for(int i=0 ; i<patients.length;i++)
		  {
			  if(number == patients[i].getCaseNumber())
				  patients[i].setStatusInitialAssessment();
		  }
	  }
	
	public void AssignedCase(String number) {
		  
		  for(int i=0 ; i<patients.length;i++)
		  {
			  if(number == patients[i].getCaseNumber())
				  patients[i].setStatusAsigned();
		  }
	  }
	
	public void TransferredCase(String number) {
		  
		  for(int i=0 ; i<patients.length;i++)
		  {
			  if(number == patients[i].getCaseNumber())
				  patients[i].setStatusTransferred();
		  }
	  }
	
	public void setCaseDepartment(String number,String Dep) {
		  
		  for(int i=0 ; i<patients.length;i++)
		  {
			  if(number == patients[i].getCaseNumber())
				  patients[i].setCaseDepartment(Dep);
			  	  AssignedCase(number);
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

	public Patient[] getPatients() {
		return patients;
	}

	public void setPatients(Patient[] patients) {
		this.patients = patients;
	}

	
	
}

