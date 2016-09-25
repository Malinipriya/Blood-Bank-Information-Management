import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
 

 
public class donorServlet extends HttpServlet {

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
         
		 
		 
	if(request.getParameter("submit")!=null)
	{

	 String uname=request.getParameter("username");
	
	 try
	 {
		String s4="delete from donor where uname=?";
		PreparedStatement p4=connection.prepareStatement(s4);
		p4.setString(1,uname);
		int i=p4.executeUpdate();
		/*if(i!=0){
		out.println("<h4>deletion done</h4>");
				}
		else{
			out.println("<h4>deletion not done</h4>");
			}*/
	 }
	 catch(SQLException e)
	 { out.println(e); }

 	
         String name = request.getParameter("name");
         String password = request.getParameter("password");
         String cnfpwd = request.getParameter("cnfpwd");
         String age = request.getParameter("age");
         String mobile = request.getParameter("mobile");
         String weight = request.getParameter("weight");
         String bg = request.getParameter("bg");
	     String email = request.getParameter("email");
         String area = request.getParameter("area");
		 String hcount = request.getParameter("hcount");
		
		int int_weight = Integer.parseInt(weight);
		int int_age = Integer.parseInt(age);
		int int_hcount = Integer.parseInt(hcount);
		int flag=0;
	
		if( int_age<18 || int_weight<50 || int_age>55 || int_hcount<12 )
		{ 
			flag=1;
		}
	
	if( flag==0 )
	{
	 String s1="insert into donor values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 PreparedStatement p1=connection.prepareStatement(s1);
	 p1.setString(1,uname);
	 p1.setString(2,name);
	 p1.setString(3,password);
	 p1.setString(4,cnfpwd);
	 p1.setString(5,age);
	 p1.setString(6,bg);
	 p1.setString(7,mobile);
	 p1.setString(8,weight);
	 p1.setString(9,email);
	 p1.setString(10,area);
	 p1.setString(11,hcount);
	 p1.setString(12,null);
	 p1.setString(13,"0");
	 int i2=p1.executeUpdate();
	 /*if(i2!=0){
        out.println("<h5>Record has been inserted</h5>");
        }
     else{
       out.println("<h5>failed to insert the data</h5>");
       }*/
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
		 out.println("<h2>Thank you for your noble gesture!<br> You have gifted Life to someone!</h3><br>");
		 out.println("<h3>Click here to go to home <a href='bb.html'><img border='0' alt='home' src='home.jpg' width='50' height='50'></a></h3>");		
		 out.println("</font>");
		 out.println("</body></html>");
	}
	else
	{
		out.println("<!DOCTYPE html>");
         out.println("<html><head>");
         out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
         out.println("<title>Donor Servlet</title></head>");
         out.println("<body><div id='header1' style='width:1350px;'>");
		 out.println("<h1>");
		 out.println("<font  face='Algerian' color='Black'><u>CrimsonBloodBank<img src='http://localhost:8080/oad/bb1.jpg' alt='blood drop' width='25' height='25'>Com</u></font>");
		 out.println("</h1></div>");
		 out.println("<div id='picture' style='width:500px;float:right'>");
         out.println("<img src='http://localhost:8080/oad/bb3.jpg' alt='bb' width='500' height='500'>");
         out.println("</div>");
		 out.println("<font  face='Algerian' color='Green'  size='4'>");
		 out.println("<h2>Sorry, you are not eligible for donation!</h2>");
		 out.println("<br><h3>Eligibility Requirements:<br> Weight: min 50kgs <br> Age: min 18yrs max 55yrs <br> Haemoglobin Count: min 12gms<br>");
		 out.println("Click here to go to home <a href='http://localhost:8080/oad/bb.html'><img border='0' alt='home' src='http://localhost:8080/oad/home.jpg' width='50' height='50'></a></h3>");		
		 out.println("</font>");
		 out.println("</body></html>");
	}
	

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