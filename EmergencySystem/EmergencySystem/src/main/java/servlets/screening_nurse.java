package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Admission_Officer;
import models.Case;
import models.Screening_Nurse;

/**
 * Servlet implementation class screening_nurse
 */
@WebServlet("/screening_nurse")
public class screening_nurse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public screening_nurse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				PrintWriter out = response.getWriter();
			
		        // Sign in process
				String Name = request.getParameter("name");
		        String password = request.getParameter("password");
		        RequestDispatcher success = request.getRequestDispatcher("screening_nurse_manage_cases.jsp");
		        RequestDispatcher failed = request.getRequestDispatcher("officer/sign-in.jsp");
		        Case[] cases=null;
		        
		        Screening_Nurse nurse = null;
				try {
//					off_signIn = Admission_Officer.selectAdmissionOfficer(Name, password);
//					 cases =Case.selectOfficerCases();
					nurse = Screening_Nurse.selectScreeningNurse(Name, password);
					cases = Case.selectAdmittedCases();
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(nurse!=null)
				{
		        	out.println("successfully sign in ");
		        	nurse.setCases(cases);
		        	
		        	
		        	// Create a session and set the off_signIn object in session scope
		            HttpSession session = request.getSession();
		            session.setAttribute("nurse", nurse);

		            // Forward to the sign-in JSP page
		            success.forward(request, response);
		        	
				}
				else {
		            // Sign in failed, generate alert and include the sign-in JSP
		            out.println("<script type=\"text/javascript\">");
		            out.println("alert('Wrong User Name or Passowrd');");
		            out.println("document.getElementById('name').value = '" + Name + "';");
		            out.println("document.getElementById('password').value = '" + password + "';");
		            out.println("</script>");
		            failed.include(request, response);
		        }
				 
		        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//doGet(request, response);
				
		        String Name = request.getParameter("name");
		        String password = request.getParameter("password");
		        RequestDispatcher dispatcher=request.getRequestDispatcher("screening_nurse/sign-in.jsp");
		       // Admission_Officer Off = new Admission_Officer(Name,password);
		          Screening_Nurse nurse= new Screening_Nurse(Name,password);
		        try {
					nurse.insertScreeningNurse();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        dispatcher.forward(request, response);
	}

}
