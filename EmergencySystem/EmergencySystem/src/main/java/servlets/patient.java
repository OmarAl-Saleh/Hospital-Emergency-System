package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import models.Admission_Officer;
import models.Case;
import models.Patient;
import models.subPatient;

@WebServlet("/patient")
@MultipartConfig
public class patient extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public patient() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	
        // Sign in process
		String Name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        RequestDispatcher success = request.getRequestDispatcher("patient_manage_cases.jsp");
        RequestDispatcher failed = request.getRequestDispatcher("patient/sign-in.jsp");
        Patient patient=null;

		try {
			  patient=Patient.selectPatient(phoneNumber);
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(patient!=null)
		{
        	System.out.println("successfully sign in ");
        	
        	
        	// Create a session and set the off_signIn object in session scope
            HttpSession session = request.getSession();
            session.setAttribute("patient_signIn", patient);
            session.setMaxInactiveInterval(10*60);
            
            // Forward to the sign-in JSP page
            success.forward(request, response);
        	
		}
		else {
            // Sign in failed, generate alert and include the sign-in JSP
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Wrong User Name or phoneNumber');");
            out.println("document.getElementById('name').value = '" + Name + "';");
            out.println("document.getElementById('phoneNumber').value = '" + phoneNumber + "';");
            out.println("</script>");
            failed.include(request, response);
        }
		 
        
        
	}

    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String[] medicalHistory = request.getParameterValues("medicalHistory[]");
        String[] chronicDiseases = request.getParameterValues("chronicDiseases[]");
        String[] allergies = request.getParameterValues("allergies[]");

        Patient patient = new Patient(email, phoneNumber, firstName, lastName, dob, address, medicalHistory, chronicDiseases, allergies);

        RequestDispatcher dispatcher = request.getRequestDispatcher("patient/sign-in.jsp");

        try {
            patient.insertPatient();

            if (request.getParameter("sub_patient") != null) {
                String subPatientName = request.getParameter("sub_patient_name");
                String subPatientRelation = request.getParameter("sub_patient_relation");
                String[] symptoms = request.getParameterValues("symptoms[]");
                boolean injured = request.getParameter("injured") != null;
                String injuryName = request.getParameter("injury_name");
                Part filePart = request.getPart("img");

                byte[] image = null;
                if (filePart != null && filePart.getSize() > 0) {
                    try (InputStream inputStream = filePart.getInputStream();
                         ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                        int bytesRead;
                        byte[] data = new byte[1024];
                        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                            buffer.write(data, 0, bytesRead);
                        }
                        image = buffer.toByteArray();
                    }
                }

//                subPatient subPatient = new subPatient(subPatientName, subPatientRelation, symptoms, injured, injuryName, image);
//                subPatient.insertSubPatient(patient.getPatientId());
                patient.setSubPatient(subPatientName, subPatientRelation, symptoms, injured, injuryName);
                if(injured)
                patient.setSubPatientImage(image);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        dispatcher.forward(request, response);
    }
}
