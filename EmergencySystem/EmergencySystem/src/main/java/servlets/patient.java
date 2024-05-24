package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Patient;
import oracle.net.aso.e;

/**
 * Servlet implementation class patient
 */
@WebServlet("/patient")
public class patient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public patient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String phoneNumber= request.getParameter("phoneNumber");
		boolean onBehalf=false;
		
		if(request.getParameter("sub_patient")!=null)
			onBehalf=true;
		
		out.println("sub patient case is "+ onBehalf);
		out.println("the source page "+request.getParameter("source"));
		out.println("the email is "+email);
		out.println("the phone is "+ phoneNumber);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String source = request.getParameter("source");
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
        patient.generateNewCase();
        try {
			patient.insertPatient();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println("exception"+e.getMessage());
			e.printStackTrace();
		}
//        out.println("<html><body>");
//        out.println("<h1>Patient Profile</h1>");
//        
//        out.println("<p>Source: " + source + "</p>");
//        out.println("<p>Email: " + email + "</p>");
//        out.println("<p>Phone Number: " + phoneNumber + "</p>");
//        out.println("<p>First Name: " + firstName + "</p>");
//        out.println("<p>Last Name: " + lastName + "</p>");
//        out.println("<p>Date of Birth: " + dob + "</p>");
//        out.println("<p>Address: " + address + "</p>");
//
//        if (medicalHistory != null) {
//            out.println("<h2>Medical History:</h2>");
//            for (String history : medicalHistory) {
//                out.println("<p>" + history + "</p>");
//            }
//        } else {
//            out.println("<p>No Medical History provided</p>");
//        }
//
//        if (chronicDiseases != null) {
//            out.println("<h2>Chronic Diseases:</h2>");
//            for (String disease : chronicDiseases) {
//                out.println("<p>" + disease + "</p>");
//            }
//        } else {
//            out.println("<p>No Chronic Diseases provided</p>");
//        }
//
//        if (allergies != null) {
//            out.println("<h2>Allergies:</h2>");
//            for (String allergy : allergies) {
//                out.println("<p>" + allergy + "</p>");
//            }
//        } else {
//            out.println("<p>No Allergies provided</p>");
//        }
//
//        out.println("</body></html>");
        
        
    }
	}


