import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import java.sql.*;  
public class myaccount extends HttpServlet
{

	String username;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();

		String c = request.getParameter("method");
		HttpSession sc = request.getSession();
		 username = (String)sc.getAttribute("username");
		if(username==null )
		{
			response.sendRedirect("loginpage.html");
		}
		else
		{ 

			if(c.equals("addcard"))
			{         

				addcard(request,response);
			}
			if(c.equals("updatecard"))
			{

				updatecard(request,response);

			}
			if(c.equals("deletecard"))
			{

				deletecard(request,response);

			}
			if(c.equals("addaddress"))
			{

				addaddress(request,response);

			}
			if(c.equals("updateaddress"))
			{

				updateaddress(request,response);

			}
			if(c.equals("deleteaddress"))
			{

				deleteaddress(request,response);

			}

		}    

		
	}

	public void addcard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String cardnumber = request.getParameter("cardnumber");
		int expmonth = Integer.parseInt(request.getParameter("expmonth"));
		int expyear = Integer.parseInt(request.getParameter("expyear"));
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11"); 

			PreparedStatement stmt=con.prepareStatement("insert into carddetails values('"+username+"','"+cardnumber+"',"+expmonth+","+expyear+")");  
			stmt.executeUpdate();
			con.close();
			response.sendRedirect("myaccount.html"); 

		}	
		catch(Exception e){
			System.out.println("Exception in addcard" + e.getMessage());
		}
		
		
		
	}
	public void updatecard(HttpServletRequest request, HttpServletResponse response)
	{
		String cardnumber = request.getParameter("cardnumber");
		String prevcardnumber = request.getParameter("prevcardnumber");
		int expmonth = Integer.parseInt(request.getParameter("expmonth"));
		int expyear = Integer.parseInt(request.getParameter("expyear"));
		
		try
		{

			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11"); 
			PreparedStatement stmt=con.prepareStatement("update carddetails set  username='"+username+"',cardnumber='"+cardnumber+"',expmonth = "+expmonth+",expyear = "+expyear+" where username='"+username+"' and cardnumber='"+prevcardnumber+"'");  
			stmt.executeUpdate();
			con.close(); 
			response.sendRedirect("myaccount.html"); 
		}	
		catch(Exception e){
			System.out.println("Exception in addcard" + e.getMessage());
		}
	}
	public void deletecard(HttpServletRequest request, HttpServletResponse response) 
	{
		String cardnumber = request.getParameter("cardnumber");
		int expmonth = Integer.parseInt(request.getParameter("expmonth"));
		int expyear = Integer.parseInt(request.getParameter("expyear"));
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11");  
			PreparedStatement stmt=con.prepareStatement("delete from carddetails where username = '"+username+"' and cardnumber='"+cardnumber+"'");  
			stmt.executeUpdate();
			con.close();
			response.sendRedirect("myaccount.html"); 
		}	
		catch(Exception e){
			System.out.println("Exception in addcard" + e.getMessage());
		}
	}
	public void addaddress(HttpServletRequest request, HttpServletResponse response) 
	{
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		int zipcode = Integer.parseInt(request.getParameter("zipcode"));
		String country = request.getParameter("country");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11");
			int seqVal=0;
			PreparedStatement stmt=con.prepareStatement("select address_sequence.nextVal from dual");  
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				seqVal = rs.getInt(1);
			}
			stmt=con.prepareStatement("insert into address values("+seqVal+",'"+address+"','"+city+"','"+state+"',"+zipcode+",'"+country+"')");  
			stmt.executeUpdate();
			stmt=con.prepareStatement("insert into addressmiddlelayer values('"+username+"',"+seqVal+")");
			stmt.executeUpdate();
			con.close();
			response.sendRedirect("myaccount.html"); 
		}	
		catch(Exception e){
			System.out.println("Exception in addcard" + e.getMessage());
		}
	}
	public void updateaddress(HttpServletRequest request, HttpServletResponse response) 
	{
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		int zipcode = Integer.parseInt(request.getParameter("zipcode"));
		String country = request.getParameter("country");
		int locationid = Integer.parseInt(request.getParameter("locationid"));
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11");  
			PreparedStatement stmt=con.prepareStatement("update address set  addressline='"+address+"',city='"+city+"',state = '"+state+"',zipcode = "+zipcode+",country = '"+country+"' where locationid="+locationid+"");  
			stmt.executeUpdate();
			con.close(); 
			response.sendRedirect("myaccount.html"); 
		}	
		catch(Exception e){
			System.out.println("Exception in addcard" + e.getMessage());
		}
	}
	public void deleteaddress(HttpServletRequest request, HttpServletResponse response)
	{
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		int zipcode = Integer.parseInt(request.getParameter("zipcode"));
		String country = request.getParameter("country");
		int locationid = Integer.parseInt(request.getParameter("locationid"));
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11"); 


			PreparedStatement stmt=con.prepareStatement("delete from addressmiddlelayer where locationid="+locationid+" and username='"+username+"'");  
			stmt.executeUpdate();
			 stmt=con.prepareStatement("delete from address  where locationid="+locationid+"");  
			stmt.executeUpdate();
			
			con.close(); 
			response.sendRedirect("myaccount.html"); 
		}	
		catch(Exception e){
			System.out.println("Exception in addcard" + e.getMessage());
		}
	}

}
