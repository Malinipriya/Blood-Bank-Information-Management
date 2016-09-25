import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
 

 
public class updateServlet extends HttpServlet {

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
			/*PreparedStatement ps;
			ResultSet rs;
			String dtime;
			int int_dtime;
			String sql= "select dtime from donor where uname=?";

			ps=connection.prepareStatement(sql);
			rs=ps.executeQuery();
			
		if(rs.next())
		{
			dtime=rs.getString(1);
			int_dtime=Integer.parseInt(dtime);
			}*/
			
	if(request.getParameter("submit")!=null)
	{
		 String name=request.getParameter("name");
		 String password = request.getParameter("password");
         String cnfpwd = request.getParameter("cnfpwd");
         String age = request.getParameter("age");
         String mobile = request.getParameter("mobile");
         String weight = request.getParameter("weight");
         String bg = request.getParameter("bg");
		 String email = request.getParameter("email");
         String area = request.getParameter("area");
		 String hcount = request.getParameter("hcount");
       
	   int int_weight=0,int_age=0,int_hcount=0;
	   int flag1=0,flag2=0,flag3=0;
	   
	
	   if(!age.equals(""))
	   {
	   int_age = Integer.parseInt(age);
		if( int_age<18 || int_age>55)
			flag1=1;
			}
		if(!weight.equals(""))
		{
		int_weight = Integer.parseInt(weight);
		if(int_weight<50)
			flag2=1;
			}
		if(!hcount.equals(""))
		{
		int_hcount = Integer.parseInt(hcount);
		if(int_hcount<12)
			flag3=1;
			}
	 int i2=0,i3=0,i4=0,i5=0,i6=0,i7=0,i8=0,i9=0,i10=0,i11=0;
	 
	 if(!name.equals(""))
	 {
		//out.println("name: " + name);
		
		String s1="update donor set name=? where uname=?";
		PreparedStatement p1=connection.prepareStatement(s1);
		
		p1.setString(1,name);
		p1.setString(2,uname);
	 
		i2=p1.executeUpdate();
	}
	
	if(!password.equals(""))
	 {
		String s1="update donor set pwd=? where uname=?";
		PreparedStatement p1=connection.prepareStatement(s1);
		
		p1.setString(1,password);
		p1.setString(2,uname);
	 
		i3=p1.executeUpdate();
	}
	
	if(!cnfpwd.equals(""))
	 {
		String s1="update donor set cpwd=? where uname=?";
		PreparedStatement p1=connection.prepareStatement(s1);
		
		p1.setString(1,cnfpwd);
		p1.setString(2,uname);
	 
		i4=p1.executeUpdate();
	}
	
	if(!age.equals("") && flag1==0)
	 {
		String s1="update donor set age=? where uname=?";
		PreparedStatement p1=connection.prepareStatement(s1);
		
		p1.setString(1,age);
		p1.setString(2,uname);
	 
		i5=p1.executeUpdate();
	}
	
	if(!bg.equals(""))
	 {
		String s1="update donor set bg=? where uname=?";
		PreparedStatement p1=connection.prepareStatement(s1);
		
		p1.setString(1,bg);
		p1.setString(2,uname);
	 
		i6=p1.executeUpdate();
	}
	
	if(!mobile.equals(""))
	 {
		
	 //out.println("mobile not empty");
	 
		String s1="update donor set mobile=? where uname=?";
		PreparedStatement p1=connection.prepareStatement(s1);
		
		p1.setString(1,mobile);
		p1.setString(2,uname);
	 
		i7=p1.executeUpdate();
	}
	
	if(!weight.equals("") && flag2==0)
	 {
		String s1="update donor set weight=? where uname=?";
		PreparedStatement p1=connection.prepareStatement(s1);
		
		p1.setString(1,weight);
		p1.setString(2,uname);
	 
		i8=p1.executeUpdate();
	}
	
	if(!email.equals(""))
	 {
		String s1="update donor set email=? where uname=?";
		PreparedStatement p1=connection.prepareStatement(s1);
		
		p1.setString(1,email);
		p1.setString(2,uname);
	 
		i9=p1.executeUpdate();
	}
	
	if(!area.equals(""))
	 {
		String s1="update donor set place=? where uname=?";
		PreparedStatement p1=connection.prepareStatement(s1);
		
		p1.setString(1,area);
		p1.setString(2,uname);
	 
		i10=p1.executeUpdate();
	}
	
	if(!hcount.equals("") && flag3==0)
	 {
		String s1="update donor set hcount=? where uname=?";
		PreparedStatement p1=connection.prepareStatement(s1);
		
		p1.setString(1,hcount);
		p1.setString(2,uname);
	 
		i11=p1.executeUpdate();
	}
	
	// && (a==0 && w==0 && h==0))
	
	
	 if((i2!=0 || i3!=0 || i4!=0 || i5!=0 || i6!=0 || i7!=0 || i8!=0 || i9!=0 || i10!=0 || i11!=0) && (flag1==0 && flag2==0 && flag3==0))
	 {
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id='header1' style='width:1350px;'>");
		out.println("<h2>");
		out.println("<font  face='Algerian' color='Black'>CrimsonBloodBank");
		out.println("<img src='bb1.jpg' alt='blood drop' width='25' height='25'>Com");
		out.println("</font>");
		out.println("<a href='bb.html'>");
		out.println("<img border='0' alt='home' src='home.jpg' width='50' height='50' align='right'>");
		out.println("</a>");
		out.println("</h2>");
		out.println("</div>");
		out.println("<div id='header2' style='width:1350pX;background-color:darkred'>");
		out.println("<h3>");
		out.println("<font  face='Algerian' color='white' size='4'><body link='white'><body vlink='orange'><a href='register.html' target='_blank'>                                      Register free      </a><a href'donate.html' target='_blank'>Why Donate Blood?     </a><a href='tips.html' target='_blank'>Blood Tips     </a><a href='facts.html' target='_blank'>Blood Facts     </a><a href='contact.html' target='_blank'>Contact Us</a></font>");
		out.println("</h3>");
		out.println("</div>");
		out.println("</pre>");
		out.println("</div>");
		out.println("<font  face='Algerian' color='Green'  size='4'>");
		out.println("<body><h2>Thank you for Updating!</h2>");
        out.println("<h5>Record has been updated</h5>");
		out.println("</font>");
		out.println("<h3><a href='http://localhost:8080/oad/donor.html'>BACK</a></h3>");
		out.println("<div id='picture' style='width:500px;float:right'>");
		out.println("<img src='bb3.jpg' alt='bb' width='500' height='500'>");
		out.println("</body>");
		out.println("</html>");
		
        }
     else
	 {
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id='header1' style='width:1350px;'>");
		out.println("<h2>");
		out.println("<font  face='Algerian' color='Black'>CrimsonBloodBank");
		out.println("<img src='bb1.jpg' alt='blood drop' width='25' height='25'>Com");
		out.println("</font>");
		out.println("<a href='bb.html'>");
		out.println("<img border='0' alt='home' src='home.jpg' width='50' height='50' align='right'>");
		out.println("</a>");
		out.println("</h2>");
		out.println("</div>");
		out.println("<div id='header2' style='width:1350pX;background-color:darkred'>");
		out.println("<h3>");
		out.println("<font  face='Algerian' color='white' size='4'><body link='white'><body vlink='orange'><a href='register.html' target='_blank'>                                      Register free      </a><a href'donate.html' target='_blank'>Why Donate Blood?     </a><a href='tips.html' target='_blank'>Blood Tips     </a><a href='facts.html' target='_blank'>Blood Facts     </a><a href='contact.html' target='_blank'>Contact Us</a></font>");
		out.println("</h3>");
		out.println("</div>");
		out.println("</pre>");
		out.println("</div>");
	   out.println("<font  face='Algerian' color='Green'  size='4'>");
       out.println("<h5>Failed to update the data. Check your weight, age or haemoglobin count!</h5>");
	   out.println("</font>");
	   out.println("<h3><a href='http://localhost:8080/oad/update.html'>BACK</a></h3>");	
		out.println("<div id='picture' style='width:500px;float:right'>");
		out.println("<img src='bb3.jpg' alt='bb' width='500' height='500'>");
		out.println("</body>");
		out.println("</html>");
       }
	}
	}
	catch(SQLException ex)
	{ out.println(ex); }


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