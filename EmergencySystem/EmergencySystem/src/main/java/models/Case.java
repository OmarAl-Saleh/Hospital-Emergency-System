package models;

import java.text.DecimalFormat;
import java.util.Date;

public class Case {
	


	private static int caseCounter=0;
	private String caseNumber;
	private String status;
	private String treatment;
	private Date SubmitDate;
	private String priority;
	private String department;
	

	public Case() {
		super();
		this.caseNumber = generateCaseNumber();
		this.status = "New";
		this.treatment = "";
		this.priority="";
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


	private static synchronized String generateCaseNumber() {
	    caseCounter++;
	    DecimalFormat df = new DecimalFormat("C00000000");
	    return df.format(caseCounter);
	}
	
	
	public String getStatus() {
		return status;
	}






	public void setStatus(String status) {
		this.status = status;
	}






	public String getTreatment() {
		return treatment;
	}






	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}


	public Date getSubmitDate() {
		return SubmitDate;
	}


	public void setSubmitDate(Date submitDate) {
		SubmitDate = submitDate;
	}



}