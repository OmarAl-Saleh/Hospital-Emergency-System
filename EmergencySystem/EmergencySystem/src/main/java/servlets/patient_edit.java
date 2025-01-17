package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Patient;

/**
 * Servlet implementation class patient_edit
 */
@WebServlet("/patient_edit")
public class patient_edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public patient_edit() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Patient patient = (Patient) session.getAttribute("patient_signIn");
		if (request.getParameter("cancelCase") != null) {
			// patient.setStatusCanceled();
		}

		else if (request.getParameter("changeEmail") != null) {
			patient.setEmail(request.getParameter("email"));
		}

		else if (request.getParameter("changeNumber") != null) {
			patient.setPhoneNumber(request.getParameter("phoneNumber"));
		}

		else if (request.getParameter("changeFirstName") != null) {
			patient.setFirstName(request.getParameter("firstName"));
		}

		else if (request.getParameter("changeLastName") != null) {
			patient.setLastName(request.getParameter("lastName"));
		}

		else if (request.getParameter("changeDob") != null) {
			patient.setDob(request.getParameter("dob"));
		}

		else if (request.getParameter("changeHistory") != null) {
			patient.setMedicalHistory(request.getParameterValues("medicalHistory"));
		}

		else if (request.getParameter("changeChronic") != null) {
			patient.setChronicDisease(request.getParameterValues("chronicDiseases"));

		}

		else if (request.getParameter("changeAllergy") != null) {
			patient.setAllergy(request.getParameterValues("allergies"));

		}

		else if (request.getParameter("changeSub") != null) {
			String[] symptom = request.getParameterValues("symptoms[]");
			boolean isInjured = request.getParameter("injured") != null;
			patient.setSubPatient(request.getParameter("sub_patient_name"),
					request.getParameter("sub_patient_relation"), symptom,
					isInjured, request.getParameter("injury_name"));

		}
		
		
		RequestDispatcher returnBack = request.getRequestDispatcher("patient.jsp");
		returnBack.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
