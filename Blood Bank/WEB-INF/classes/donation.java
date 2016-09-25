import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.text.*;

 
public class donation extends HttpServlet {

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
         
	String uname=request.getParameter("uname");
	
			PreparedStatement ps;
			ResultSet rs;
			String dtime;
			int int_dtime=0;
			String sql= "select dtime from donor where uname='"+uname+"'";

			ps=connection.prepareStatement(sql);
			rs=ps.executeQuery();
			
		if(rs.next())
		{
			dtime=rs.getString(1);
			int_dtime=Integer.parseInt(dtime);
			}
			
	if(request.getParameter("submit")!=null)
	{

			int i,j;
			String prevdate=null;
			String curdate=null;
			//out.println("username: " + uname);
			
			String first=request.getParameter("first");
			if(first.equals("yes"))
			i=1;
			else
			i=2;
			
			//out.println("first: " + first);
			
		 String day=request.getParameter("day");
		 String month=request.getParameter("month");
		 String year=request.getParameter("year");
		 curdate= day + "/" + month + "/" + year;
		 
		 if(i==1)
			{
			int_dtime++;
			String str_dtime=Integer.toString(int_dtime);
		  String s1="update donor set ddate=?,dtime=? where uname=?";
	 PreparedStatement p1=connection.prepareStatement(s1);
	 p1.setString(1,curdate);
	 p1.setString(2,str_dtime);
	 p1.setString(3,uname);
	 
	  int i2=p1.executeUpdate();
	  
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
		 out.println("<h2>Thank you for your noble gesture!<br> You have gifted Life to someone!</h3><br>");
		 out.println("<h3>Click here to go to home <a href='http://localhost:8080/oad/bb.html/name=?'+uname+''><img border='0' alt='home' src='http://localhost:8080/oad/home.jpg' width='50' height='50'></a></h3>");		
		 out.println("</font>");
		 out.println("</body></html>");
	  }
		 
			
			if(i==2)
			{
				ResultSet rs2=null;
				String sql2="select ddate from donor where uname=?";
				PreparedStatement pstmt2=connection.prepareStatement(sql2);
				pstmt2.setString(1,uname);
				rs2=pstmt2.executeQuery();
					while(rs2.next())
					{
						prevdate=rs2.getString("ddate");
					}
					
			}
				//out.println("prevdate: "+ prevdate);
				
				String[] prev1=prevdate.split("/");
			
		 
		 int dy=0;
		 int dy1=0;
		 int mon=0;
		 int mon1=0;
		 int yr=0;
		 int yr1=0;
		 
		 if(i==2)
		 {
		 dy=Integer.parseInt(day);
		 dy1=Integer.parseInt(prev1[0]);
		 mon=Integer.parseInt(month);
		 mon1=Integer.parseInt(prev1[1]);
		 yr=Integer.parseInt(year);
		 yr1=Integer.parseInt(prev1[2]);
		 }
		 
		 int noDays=0;
		 int noYears=0;
		 
		 
			int[] months=new int[13];
			
				months[1]=31;
				months[2]=28;
				months[3]=31;
				months[4]=30;
				months[5]=31;
				months[6]=30;
				months[7]=31;
				months[8]=31;
				months[9]=30;
				months[10]=31;
				months[11]=30;
				months[12]=31;
				
			if(i==2)
			{
				noYears=yr-yr1;
				
				if(noYears==0)
				{
					int m=mon1;
					noDays=months[m]-dy1;
					
					for(m=mon1+1;m<mon;m++)
					{
						noDays+=months[m];
					}
					noDays+=dy;
					
				
				}
				
				if(noYears!=0)
				{
					int m=mon1;
					noDays=months[m]-dy1;
					//out.println("noDays: " + noDays);
					for(m=mon1+1;m<=12;m++)
					{
						noDays+=months[m];
					}
					//out.println("noDays: " + noDays);
					for(m=1;m<mon;m++)
					{
						noDays+=months[m];
					}
					noDays+=dy;
					//out.println("noDays: " + noDays);
					
					int nn=(noYears-1)*365;
					
					noDays+= nn;
				}
			
			}
			
			if(i==2)
			{
				if(noDays>90)
				{
				int_dtime++;
				String str_dtime=Integer.toString(int_dtime);
				String s2="update donor set ddate=?,dtime=? where uname=?";
				PreparedStatement p2=connection.prepareStatement(s2);
				p2.setString(1,curdate);
				p2.setString(2,str_dtime);
				p2.setString(3,uname);
				int i3=p2.executeUpdate();
				
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
		 out.println("<div id='picture' style='width:500px;float:right'>");
         out.println("<img src='http://localhost:8080/oad/bb3.jpg' alt='bb' width='500' height='500'>");
         out.println("</div>");
		 out.println("<font  face='Algerian' color='Green'  size='4'>");
		 out.println("<h2>Thank you for your noble gesture!<br> You have gifted Life to someone!</h3><br>");
		 out.println("Your donation date: " + curdate);
		 out.println("<h3>Click here to go to home <a href='http://localhost:8080/oad/bb.html'><img border='0' alt='home' src='http://localhost:8080/oad/home.jpg' width='50' height='50'></a></h3>");		
		 out.println("</font>");
		 //out.println(curdate);
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
				out.println("<div id='header2' style='width:1350pX;background-color:darkred'>");
				out.println("<h3>");
				out.println("<font  face='Algerian' color='white' size='4'><body link='white'><body vlink='orange'><a href='register.html' target='_blank'>                                      Register free      </a><a href'donate.html' target='_blank'>Why Donate Blood?     </a><a href='tips.html' target='_blank'>Blood Tips     </a><a href='facts.html' target='_blank'>Blood Facts     </a><a href='contact.html' target='_blank'>Contact Us</a></font>");
				out.println("</h3>");
				out.println("</div>");
				out.println("<div id='picture' style='width:500px;float:right'>");
				out.println("<img src='http://localhost:8080/oad/bb3.jpg' alt='bb' width='500' height='500'>");
				out.println("</div>");
				out.println("<font  face='Algerian' color='Green'  size='4'>");
				out.println("<h2>Sorry, you are not eligible for donation!</h2>");
				out.println("<br><h3>Eligibility Requirements:<br> *Weight: min 50kgs <br> *Age: min 18yrs max 55yrs <br> *Haemoglobin Count: min 12gms<br> *Must donate only after 60 days of previous donation<br>");
				out.println("Click here to go to home <a href='http://localhost:8080/oad/bb.html'><img border='0' alt='home' src='http://localhost:8080/oad/home.jpg' width='50' height='50'></a></h3>");		
				out.println("</font>");
				out.println("</body></html>");
				}
	
				
				}
		 
		 
	}
	

	}
	
		catch(SQLException e1)
	{ 
		//out.println(name);
		out.println(e1);
		}
     
	  
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