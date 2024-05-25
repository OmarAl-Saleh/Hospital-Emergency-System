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
import models.Case;
import models.Patient;

@WebServlet("/patient")
public class patient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public patient() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String phone = request.getParameter("phoneNumber");
		boolean onBehalf = false;

		if (request.getParameter("sub_patient") != null)
			onBehalf = true;
		RequestDispatcher success = request.getRequestDispatcher("patient.jsp");
		RequestDispatcher failed = request.getRequestDispatcher("patient/sign-in.jsp");

		Patient patient_signIn = null;
		try {
			patient_signIn = Patient.selectPatient(phone);
			if (onBehalf) {
				String[] symptom = request.getParameterValues("symptoms[]");
				boolean isInjured = request.getParameter("injured") != null;
				patient_signIn.setSubPatient(request.getParameter("sub_patient_name"),
						request.getParameter("sub_patient_relation"), symptom, isInjured,
						request.getParameter("injury_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (patient_signIn != null) {
			out.println("successfully sign in ");
			HttpSession session = request.getSession();
			session.setAttribute("patient_signIn", patient_signIn);
			success.forward(request, response);

		} else {
			// Sign in failed, generate alert and include the sign-in JSP
			out.println("<script type=\"text/javascript\">");
			out.println("alert('wrong email or phone number');");
			out.println("document.getElementById('email').value = '" + email + "';");
			out.println("document.getElementById('phoneNumber').value = '" + phone + "';");
			out.println("</script>");
			failed.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

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

		Patient patient = new Patient(email, phoneNumber, firstName, lastName, dob, address, medicalHistory,
				chronicDiseases, allergies);

		RequestDispatcher dispatcher = request.getRequestDispatcher("patient/sign-in.jsp");

		try {
			patient.insertPatient();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		dispatcher.forward(request, response);

	}
}