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

import models.Admission_Officer;
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
        out.println("screening nurse get servlet");
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
