package models;

import java.util.Arrays;
import java.util.Date;

public class Patient {
	private String email;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private Date dob;
	private String address;
	private String[] medicalHistory;
	private String[] chronicDisease;
	private String[] allergy;
	private Case  cases;
	private boolean present;
	public subPatient patient;

	
	public Patient(String email, String phoneNumber, String firstName, String lastName, Date dob, String address,
			String[] medicalHistory, String[] chronicDisease, String[] allergy) {
		super();
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.address = address;
		this.medicalHistory = medicalHistory;
		this.chronicDisease = chronicDisease;
		this.allergy = allergy;
		this.cases= new Case();
		this.present=false;
	} 
	
	public void setCaseDepartment(String department) {
		cases.setDepartment(department);
	}
	
	public String getCaseDepartment() {
		return cases.getDepartment();
	}
	
	public void setCasePriority(String priority) {
		cases.setPriority(priority);
	}
	
	public String getCasePriority() {
		return cases.getPriority();
	}
	
	public void setCaseTreatment(String treatment) {
		cases.setTreatment(treatment);
	}
	
	public String getCaseTreatment() {
		return cases.getTreatment();
	}
	
	public String getCaseNumber() {
		return cases.getCaseNumber();}
	
	public boolean isPresent() {
		return present;
	}

	public void setPresent() {
		this.present = true;
	}
	
	public String getStatus() {
		return cases.getStatus();
	}

	public void setStatusNew()
	{
		cases.setStatus("New");
	}
	
	public void setStatusCanceled()
	{
		if(present==true)
		cases.setStatus("Canceled");
	}
	
	public void setStatusRejected()
	{
		cases.setStatus("Rejected");
	}
	
	public void setStatusInitialAssessment()
	{
		cases.setStatus("Initial Assessment");
		
	}
	
	public void setStatusAsigned()
	{
		cases.setStatus("Asigned");
	}
	
	public void setStatusClosed()
	{
		cases.setStatus("Closed");
	}
	
	public void setStatusTransferred()
	{
		cases.setStatus("Transferred");
	}
	
	public void setSubPatient(String name, String relationship, String[] symptoms, boolean injured, String injuredKind) {
		patient=new subPatient(name,relationship,symptoms,injured,injuredKind);
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String[] getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(String[] medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	
	public String[] getChronicDisease() {
		return chronicDisease;
	}
	public void setChronicDisease(String[] chronicDisease) {
		this.chronicDisease = chronicDisease;
	}
	
	public String[] getAllergy() {
		return allergy;
	}
	public void setAllergy(String[] allergy) {
		this.allergy = allergy;
	}
	
	
	
	
    public String toString() {
        return "Patient{" +
                "email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dob +
                ", address='" + address + '\'' +
                ", medicalHistory='" + Arrays.toString(medicalHistory) + '\'' +
                ", chronicDiseases='" + Arrays.toString(chronicDisease) + '\'' +
                ", allergies='" + Arrays.toString(allergy) + '\'' +
                ",present="+present+
                '}';
    }
	
}