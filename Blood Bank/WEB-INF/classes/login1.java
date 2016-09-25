
 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
//import java.lang.string;
 

 
public class login1 extends HttpServlet {

Connection connection = null;
PreparedStatement ps;
ResultSet rs;

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
      // Set the response message's MIME type
      response.setContentType("text/html; charset=UTF-8");
      // Allocate a output writer to write the response message into the network socket
     PrintWriter o= response.getWriter();

  try
  {
         int flag=0;
	if(request.getParameter("submit")!=null)
	{
     String donorlogin=request.getParameter("donorlogin");
	 String pwd=request.getParameter("password");

	 if(donorlogin.equals("admin") && pwd.equals("ssn"))
	 {
		flag=1;
		String str="http://localhost:8080/oad/ddetails.html";
		response.sendRedirect(str);
	}

else
{
String sql= " select pwd from donor where uname='"+donorlogin+"' ";

ps=connection.prepareStatement(sql);
rs=ps.executeQuery();
try
{

while(rs.next())
{
	o.println("Inside while");
	String validpwd=rs.getString(1) ;
	o.println("<html>");
	o.println("<body>");
	

					if(pwd.equals(validpwd))
					{
							//o.println("Inside while");
							flag=1;
							String str="http://localhost:8080/oad/donor.html?donorlogin="+donorlogin;
							response.sendRedirect(str);
					}
					 else
					 {
						o.println("<h1>");
						o.println("<font  face='Algerian' color='Black'><u>CrimsonBloodBank<img src='http://localhost:8080/oad/bb1.jpg' alt='blood drop' width='25' height='25'>Com</u></font>");
						o.println("</h1></div>");
						o.println("<div id='header2' style='width:1350pX;background-color:darkred'>");
						o.println("<h3>");
						o.println("<font  face='Algerian' color='white' size='4'><body link='white'><body vlink='orange'><a href='register.html' target='_blank'>                                      Register free      </a><a href'donate.html' target='_blank'>Why Donate Blood?     </a><a href='tips.html' target='_blank'>Blood Tips     </a><a href='facts.html' target='_blank'>Blood Facts     </a><a href='contact.html' target='_blank'>Contact Us</a></font>");
						o.println("</h3>");
						o.println("</div>");
						o.println("<div id='picture' style='width:500px;float:right'>");
						o.println("<img src='http://localhost:8080/oad/bb3.jpg' alt='bb' width='500' height='500'>");
						o.println("</div>");
						o.println("<font  face='Algerian' color='Green'  size='4'>");
						o.println("<h2>ENTER PROPER USERNAME(MAIL ID) AND PASSWORD!!</h>");
						o.println("<h3>Click here to go to home <a href='http://localhost:8080/oad/bb.html'><img border='0' alt='home' src='http://localhost:8080/oad/home.jpg' width='50' height='50'></a></h3>");		
						o.println("</font>");
						
						o.println("</body></html>");
					}
                   // response.sendRedirect("login.html");
                  
} 	
}
catch (Exception e)
               {
				o.println("exception: " + e );
				}

	if(flag==0)
	{
		String sql1= " select pwd from purchaser where uname='"+donorlogin+"' ";

ps=connection.prepareStatement(sql1);
rs=ps.executeQuery();
try
{

while(rs.next())
{
	//o.println("Inside while");
	String validpwd=rs.getString(1) ;
	o.println("<html>");
	o.println("<body>");
	

					if(pwd.equals(validpwd))
					{
							
							String str="http://localhost:8080/oad/purchaser.html?donorlogin="+donorlogin;
							response.sendRedirect(str);
					}
					 else
					 {
						o.println("<h1>");
						o.println("<font  face='Algerian' color='Black'><u>CrimsonBloodBank<img src='http://localhost:8080/oad/bb1.jpg' alt='blood drop' width='25' height='25'>Com</u></font>");
						o.println("</h1></div>");
						o.println("<div id='header2' style='width:1350pX;background-color:darkred'>");
						o.println("<h3>");
						o.println("<font  face='Algerian' color='white' size='4'><body link='white'><body vlink='orange'><a href='register.html' target='_blank'>                                      Register free      </a><a href'donate.html' target='_blank'>Why Donate Blood?     </a><a href='tips.html' target='_blank'>Blood Tips     </a><a href='facts.html' target='_blank'>Blood Facts     </a><a href='contact.html' target='_blank'>Contact Us</a></font>");
						o.println("</h3>");
						o.println("</div>");
						o.println("<div id='picture' style='width:500px;float:right'>");
						o.println("<img src='http://localhost:8080/oad/bb3.jpg' alt='bb' width='500' height='500'>");
						o.println("</div>");
						o.println("<font  face='Algerian' color='Green'  size='4'>");
						o.println("<h2>ENTER PROPER USERNAME(MAIL ID) AND PASSWORD!!</h>");
						o.println("<h3>Click here to go to home <a href='http://localhost:8080/oad/bb.html'><img border='0' alt='home' src='http://localhost:8080/oad/home.jpg' width='50' height='50'></a></h3>");		
						o.println("</font>");
						
						o.println("</body></html>");
					}

}
}
				catch (Exception e)
               {
				o.println("exception: " + e );
				}


}//if close
}//else close
}
}
			catch(Exception enew2)
               {
				o.println(enew2);
				}
				

finally {
         o.close();  
      }
}
      
   public void doPost(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException
     {
            doGet(request, response);
         }
  
    }                