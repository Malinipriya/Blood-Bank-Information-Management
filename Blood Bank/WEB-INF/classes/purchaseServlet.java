import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
 
public class purchaseServlet extends HttpServlet {

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
	  
	     String uname=request.getParameter("purchaserlogin");
		 try
		 {
		 ResultSet res;
		String s3="select dtime,bg,place from donor where uname=?";
		PreparedStatement p3=connection.prepareStatement(s3);
		p3.setString(1,uname);
		res=p3.executeQuery();
		res.next();
		String dtime=res.getString(1);
		String bg=res.getString(2);
		String place=res.getString(3);
		int dnew=Integer.parseInt(dtime);
		dnew=dnew-1;
		String dtime2=Integer.toString(dnew);
		 
         String s1="update donor set dtime=? where uname=?";
		PreparedStatement p1=connection.prepareStatement(s1);
		
		p1.setString(1,dtime2);
		p1.setString(2,uname);
	
		int i=p1.executeUpdate();
		  out.println("<!DOCTYPE html>");
         out.println("<html><head>");
         out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
         out.println("<title>Donor Servlet</title></head>");
         out.println("<body><div id='header1' style='width:1350px;'>");
		 out.println("<h1>");
		 out.println("<font  face='Algerian' color='Black'><u>CrimsonBloodBank<img src='bb1.jpg' alt='blood drop' width='25' height='25'>Com</u></font>");
		 out.println("</h1></div>");
		 out.println("<div id='header2' style='width:1350pX;background-color:darkred'>");
		out.println("<h3>");
		out.println("<font  face='Algerian' color='white' size='4'><body link='white'><body vlink='orange'><a href='register.html' target='_blank'>                                      Register free      </a><a href'donate.html' target='_blank'>Why Donate Blood?     </a><a href='tips.html' target='_blank'>Blood Tips     </a><a href='facts.html' target='_blank'>Blood Facts     </a><a href='contact.html' target='_blank'>Contact Us</a></font>");
		out.println("</h3>");
		out.println("</div>");
		 out.println("<div id='picture' style='width:500px;float:right'>");
         out.println("<img src='bb3.jpg' alt='bb' width='500' height='500'>");
         out.println("</div>");
		 out.println("<font  face='Algerian' color='Green'  size='4'>");
		 out.println("<h2>Thank you for purchasing!</h2><br>");
		 out.println("<pre><h3>--------BILL--------<br>Blood group :"+bg+"<br>Blood Bank  :"+place+"<br>Amount      :Rs.1000/-</h3><br></pre>");
		 out.println("<h3>Click here to go to home <a href='bb.html'><img border='0' alt='home' src='home.jpg' width='50' height='50'></a></h3>");		
		 out.println("</font>");
		 out.println("</body></html>");
		 }
		 catch(SQLException e3)
	{ out.println(e3); }
		 
	}
	catch(Exception e2)
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