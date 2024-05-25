package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Admission_Officer;
import models.Case;
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
     
      //  Patient patient = new Patient(email, phoneNumber, firstName, lastName, dob, address, medicalHistory, chronicDiseases, allergies);
        
        Admission_Officer off = new Admission_Officer(firstName,phoneNumber);
        
        
        try {
			
        	Case [] c=Case.selectOfficerCases();
        	off.setCases(c);
        	off.rejectCase("C00000001");
        	off.insertAdmissionOfficer();
        	Admission_Officer ad = Admission_Officer.selectAdmissionOfficer(firstName, phoneNumber);
        	out.println(ad.getName());
        	
            out.println("done");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println("exception"+e.getMessage());
			e.printStackTrace();
		}
        
        

        
    }
	}


