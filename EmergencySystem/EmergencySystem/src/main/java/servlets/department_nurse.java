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
import models.Department_Nurse;

/**
 * Servlet implementation class department_nurse
 */
@WebServlet("/department_nurse")
public class department_nurse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public department_nurse() {
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
		        RequestDispatcher success = request.getRequestDispatcher("department_nurse_manage_cases.jsp");
		        RequestDispatcher failed = request.getRequestDispatcher("department_nurse/sign-in.jsp");
		        Case[] cases=null;
		        
		        Department_Nurse nurse = null;
				try {
					nurse= Department_Nurse.selectDepartmentNurse(Name, password);
					 cases =Case.selectDepartmentCases(nurse.getDepartmentName());
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(nurse!=null)
				{
		        	nurse.setCases(cases);
		        	
		        	
		        	// Create a session and set the off_signIn object in session scope
		            HttpSession session = request.getSession();
		            session.setAttribute("department_nurse", nurse);

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
		        String doctorName = request.getParameter("doctor");// I reflex them to solve bug
		        String departmentName = request.getParameter("department");
		        RequestDispatcher dispatcher=request.getRequestDispatcher("department_nurse/sign-in.jsp");
		        Department_Nurse nurse = new Department_Nurse(Name,password,doctorName,departmentName);
		        try {
					nurse.insertDepartmentNurse();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        dispatcher.forward(request, response);
		        
		       
			}
	

}
