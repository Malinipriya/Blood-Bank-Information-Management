import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
 

 
public class purchaserdetails extends HttpServlet {

Connection connection = null;

public void init()
{
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Where is your MySQL JDBC Driver?");
		e.printStackTrace();
		return;
	}
 
	System.out.println("MySQL JDBC Driver Registered!");
	
 
	try {
		connection = DriverManager
		.getConnection("jdbc:mysql://localhost:3306/bb","root", "password");
 
	} catch (SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return;
	}
 
	if (connection != null) {
		System.out.println("You made it, take control your database now!");
	} else {
		System.out.println("Failed to make connection!");
               }
} 


   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws IOException, ServletException {
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
 
      try {
         
		 
		 
	if(request.getParameter("purchaser")!=null)
	{

		out.println("<!DOCTYPE html>");
				out.println("<html><head>");
				out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
         out.println("<title>Donor Servlet</title></head>");
         out.println("<body><div id='header1' style='width:1350px;'>");
		 out.println("<h1>");
		 out.println("<font  face='Algerian' color='Black'><u>CrimsonBloodBank<img src='http://localhost:8080/oad/bb1.jpg' alt='blood drop' width='25' height='25'>Com</u></font>");
		 out.println("</h1></div>");
		 out.println("<div id='header2' style='width:1350pX;background-color:darkred'>");
		out.println("<h3>");
		out.println("<font  face='Algerian' color='white' size='4'><body link='white'><body vlink='orange'><a href='register.html' target='_blank'>                                      Register free      </a><a href'donate.html' target='_blank'>Why Donate Blood?     </a><a href='tips.html' target='_blank'>Blood Tips     </a><a href='facts.html' target='_blank'>Blood Facts     </a><a href='contact.html' target='_blank'>Contact Us</a></font>");
		out.println("</h3>");
		out.println("</div>");
		 out.println("<font  face='Algerian' color='Green'  size='4'>");
		 out.println("<h2>PURCHASER DETAILS</h3><br>");
		 
	PreparedStatement ps;
			ResultSet rs;
			int count=0;
			String uname,name,age,bg,mobile,email,place;
			String sql= "select * from purchaser";
		out.println("<font  face='Algerian' color='Blue'  size='3'>");
		ps=connection.prepareStatement(sql);
			rs=ps.executeQuery();
			out.println("<table border='1'>");
			out.println("<tr><pre>");
			out.println("<td> USER NAME");
			out.println("<td>NAME");
			out.println("<td>AGE");
			out.println("<td>BLOOD<br>GROUP");
			out.println("<td>MOBILE");
			out.println("<td>EMAIL");
			out.println("<td>PLACE");
			out.println("</tr></pre>");
			
		while(rs.next())
		{
			uname=rs.getString(1);
			name=rs.getString(2);
			age=rs.getString(5);
			bg=rs.getString(6);
			mobile=rs.getString(7);
			email=rs.getString(8);
			place=rs.getString(9);
			
		 out.println("<tr><pre>");
		 out.println("<td>" +uname);
		 out.println("<td>" +name);
		 out.println("<td>" +age);
		 out.println("<td>" +bg);
		 out.println("<td>" +mobile);
		 out.println("<td>" +email);
		 out.println("<td>" +place);
		 out.println("</tr></pre>");
		 
	
	 }
		out.println("</font>");
		out.println("</table>");
		out.println("<font  face='Algerian' color='Green'  size='4'>");
		 out.println("<h3>Click here to go to home <a href='http://localhost:8080/oad/bb.html'><img border='0' alt='home' src='http://localhost:8080/oad/home.jpg' width='50' height='50'></a></h3>");		
		 out.println("</font>");
		 out.println("</body></html>");
	}
	
      } 
	  
	catch(SQLException e2)
	{ out.println(e2); }
	  
	  
	  finally {
         out.close();  
      }
   }
 
   
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws IOException, ServletException {
      doGet(request, response);
   }
 }