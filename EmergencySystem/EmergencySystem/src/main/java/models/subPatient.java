package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class subPatient {

    private String name;
    private String relationship;
    private String[] symptoms;
    private boolean injured;
    private String injuredKind;
    private byte[] injuryImage;
    private String phoneNumber; // to link with the patient

  

    public subPatient() {
        super();
    }

    public subPatient(String name, String relationship, String[] symptoms, boolean injured, String injuredKind, String phoneNumber) {
        super();
        this.name = name;
        this.relationship = relationship;
        this.symptoms = symptoms;
        this.injured = injured;
        this.injuredKind = injuredKind;
        this.phoneNumber = phoneNumber;
    }
    
   


    public byte[] getInjuryImage() {
        return injuryImage;
    }

    public void setInjuryImage(byte[] injuryImage) {
        this.injuryImage = injuryImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String[] getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String[] symptoms) {
        this.symptoms = symptoms;
    }

    public boolean isInjured() {
        return injured;
    }

    public void setInjured(boolean injured) {
        this.injured = injured;
    }

    public String getInjuredKind() {
        return injuredKind;
    }

    public void setInjuredKind(String injuredKind) {
        this.injuredKind = injuredKind;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

   
    // Method to insert a new sub_patient into the database
    public void insertSubPatient() throws SQLException {
        String subPatientSql = "INSERT INTO sub_patient (name, relationship, injured, injured_kind, injury_image, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        String symptomSql = "INSERT INTO sub_patient_symptom (injured_kind, symptom) VALUES (?, ?)";

        try (Connection conn = database.connect();
             PreparedStatement subPatientStmt = conn.prepareStatement(subPatientSql);
             PreparedStatement symptomStmt = conn.prepareStatement(symptomSql)) {

            conn.setAutoCommit(false);

            subPatientStmt.setString(1, this.name);
            subPatientStmt.setString(2, this.relationship);
            subPatientStmt.setBoolean(3, this.injured);
            subPatientStmt.setString(4, this.injuredKind);
            subPatientStmt.setBytes(5, this.injuryImage);
            subPatientStmt.setString(6, this.phoneNumber); // Add the phone_number field to match the patient

            subPatientStmt.executeUpdate();

            for (String symptom : this.symptoms) {
                symptomStmt.setString(1, this.injuredKind);
                symptomStmt.setString(2, symptom);
                symptomStmt.executeUpdate();
            }

            conn.commit();
        }
    }
    
 // Method to select a sub-patient from the database based on phoneNumber
    public static subPatient selectSubPatient(String phoneNumber) throws SQLException {
        String subPatientSql = "SELECT * FROM sub_patient WHERE phone_number = ?";
        String symptomSql = "SELECT symptom FROM sub_patient_symptom WHERE injured_kind = ?";
        subPatient subPatient = null;

        try (Connection conn = database.connect();
             PreparedStatement subPatientStmt = conn.prepareStatement(subPatientSql);
             PreparedStatement symptomStmt = conn.prepareStatement(symptomSql)) {

            subPatientStmt.setString(1, phoneNumber);
            ResultSet subPatientRs = subPatientStmt.executeQuery();

            if (subPatientRs.next()) {
                String name = subPatientRs.getString("name");
                String relationship = subPatientRs.getString("relationship");
                boolean injured = subPatientRs.getBoolean("injured");
                String injuredKind = subPatientRs.getString("injured_kind");
                byte[] injuryImage = subPatientRs.getBytes("injury_image");
 
                symptomStmt.setString(1, injuredKind);
                ResultSet symptomRs = symptomStmt.executeQuery();

                List<String> symptomList = new ArrayList<>();
                while (symptomRs.next()) {
                    symptomList.add(symptomRs.getString("symptom"));
                }

                String[] symptoms = symptomList.toArray(new String[0]);
                subPatient = new subPatient(name, relationship, symptoms, injured, injuredKind, phoneNumber);
                subPatient.setInjuryImage(injuryImage);
            }
        }
        return subPatient;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("subPatient Details:\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Relationship: ").append(relationship).append("\n");
        sb.append("Injured: ").append(injured).append("\n");
        sb.append("Injured Kind: ").append(injuredKind).append("\n");
        sb.append("Phone Number: ").append(phoneNumber).append("\n");
        sb.append("Symptoms: ");
        if (symptoms != null) {
            for (String symptom : symptoms) {
                sb.append(symptom).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length()); // Remove the trailing comma and space
        }
        return sb.toString();
    }


 
}
