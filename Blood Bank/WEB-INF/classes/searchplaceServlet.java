import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
 

 
public class searchplaceServlet extends HttpServlet {

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
         
		 
		 
	if(request.getParameter("search")!=null)
	{

	 String uname=request.getParameter("username");
	
	 try
	 {
		String s4="delete from purchaser where uname=?";
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
         String place = request.getParameter("area");
	     String email = request.getParameter("email");
		 String bg="NULL";
	
	 String s1="insert into purchaser values(?,?,?,?,?,?,?,?,?)";
	 PreparedStatement p1=connection.prepareStatement(s1);
	 p1.setString(1,uname);
	 p1.setString(2,name);
	 p1.setString(3,password);
	 p1.setString(4,cnfpwd);
	 p1.setString(5,age);
	 p1.setString(6,bg);
	 p1.setString(7,mobile);
	 p1.setString(8,email);
	 p1.setString(9,place);
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
         out.println("<title>Search By Place Servlet</title></head>");
         out.println("<body><div id='header1' style='width:1350px;'>");
		 out.println("<h1>");
		 out.println("<font  face='Algerian' color='Black'><u>CrimsonBloodBank<img src='bb1.jpg' alt='blood drop' width='25' height='25'>Com</u></font>");
		 out.println("</h1></div>");
		 out.println("<div id='picture' style='width:500px;float:right'>");
         out.println("<img src='bb3.jpg' alt='bb' width='500' height='500'>");
         out.println("</div>");
		 out.println("<font  face='Algerian' color='Green'  size='4'>");
		 out.println("<h2>Thank you for Registering</h2><br>");
		 
	   ResultSet res;
		String s3="select * from donor where place=?";
		PreparedStatement p3=connection.prepareStatement(s3);
		p3.setString(1,place);
		res=p3.executeQuery();
		out.println("<h3><u>Search Results:<br></u></h3></font>");
		res.next();
	try
	{
	 do
	 {
		String name2=res.getString(2);
		String mobile2=res.getString(7);
		String email2=res.getString(9);
		String bg2=res.getString(6);
	   
		 out.println("<font  face='Algerian' color='Green'  size='4'>");
		  out.println("<form name='purchase' method='get' action='http://localhost:8080/oad/payment.html' >");
		 out.println("<h4><pre>Name           :"+name2);
		 out.println("Mobile Number  :"+mobile2);
		 out.println("E mail id      :"+email2);
		 out.println("Blood Group    :"+bg2+"<br><hr>");
		 
	 }while(res.next());
	 out.println("Enter name      :<input type='text' name='name' id='nameid'> ");
		 out.println("<br><input type='submit' value='Buy' name='search' style='background-color:red;color:white; '></form>");
		 out.println("</pre></h4></font><hr>");
	     out.println("<font  face='Algerian' color='Green'  size='4'>");
		 out.println("<h3>Click here to go to home <a href='http://localhost:8080/oad/bb.html'><img border='0' alt='home' src='http://localhost:8080/oad/home.jpg' width='50' height='50'></a></h3>");		
		 out.println("</font>");
		 out.println("</body></html>");
	     out.println("<font  face='Algerian' color='Green'  size='4'>");
		 out.println("<h3>Click here to go to home <a href='bb.html'><img border='0' alt='home' src='home.jpg' width='50' height='50'></a></h3>");		
		 out.println("</font>");
		 out.println("</body></html>");
	}
	 catch(SQLException s)
	 {
	  out.println("<h4>No results found!<br></h4>");
	  out.println("<h3>Click here to go to home <a href='bb.html'><img border='0' alt='home' src='home.jpg' width='50' height='50'></a></h3>");		
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