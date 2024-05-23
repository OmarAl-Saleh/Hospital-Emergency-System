package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","Omr_20021129");
		Statement s=c.createStatement();
		// Create the students table
     //  s.executeUpdate("create table students (name varchar(50), id varchar(10), email varchar(50), phone varchar(15), primary key(id))");

        // Insert a sample student record
      //  s.executeUpdate("insert into students values ('John Doe', 'S123456789', 'john.doe@example.com', '123-456-7890')");
      //  s.executeUpdate("insert into students values ('omar', 'faddfafda', 'johdddd@example.com', '553-456-7890')");
        ResultSet rs=s.executeQuery("select name from omar");
        if(rs.next())
        out.println( rs.getString("name"));
        
	//	s.executeUpdate("create table employee (ename varchar(25),ssn varchar(9),primary key(ssn))");
	//	s.executeUpdate("insert into employee values(wddd,hjhbjv)");
		//PreparedStatement ps=c.prepareStatement("insert into employee values(?,?,?)");
		//ps.setString(1, "the life is difficult");
		//ps.setDouble(2, 3);
		//ps.setLong(3, 4434343);
		//ps.execute();
		}
		catch(ClassNotFoundException e) {
            System.err.println("Oracle JDBC Driver not found. Ensure the driver is in your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed. Check the database status, credentials, and URL.");
            e.printStackTrace();
            }
		// TODO Auto-generated method stub
	//	PrintWriter out = response.getWriter();
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
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String phoneNumber= request.getParameter("phoneNumber");
		String [] diseases=request.getParameterValues("chronicDiseases[]");
		
		out.println("disease lengh is "+diseases.length);
		
		out.println("the source page "+request.getParameter("source"));
		out.println("the email is "+email);
		out.println("the phone is "+ phoneNumber);
	}

}
