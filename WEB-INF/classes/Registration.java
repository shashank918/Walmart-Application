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

public class Registration extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
	    // String confirmpwd = request.getParameter("confirmpwd");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		 try{
            
            System.out.println("In Registration page...");
        	 String message="";
             int i =0;
             String path="";
              Class.forName("oracle.jdbc.driver.OracleDriver");  
			 Connection con=DriverManager.getConnection(  
								"jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11");  
			  String sql = "select address_sequence.nextVal from dual";
 			  PreparedStatement stmt = con.prepareStatement(sql);
 			  // stmt.setString(1, category); 
 			  ResultSet rs=stmt.executeQuery(); 
 			  rs.next();
 			 int address_sequence = rs.getInt(1);
 			 
 			 stmt= con.prepareStatement("Insert into customerlogins values('"+email+"','"+pwd+"','"+name+"')");  

 			  stmt.executeUpdate();

 			 stmt= con.prepareStatement("Insert into address values('"+address_sequence+"','"+address+"','"+city+"','"+state+"','"+zipcode+"','"+country+"')");  

 			 stmt.executeUpdate(); 
 			 
 			 stmt= con.prepareStatement("Insert into addressmiddlelayer values('"+email+"','"+address_sequence+"')");  

 			 stmt.executeUpdate();

           }
           catch(Exception e){
           	System.out.println("In uitilitites Exception " + e.getMessage());
           }

	}
}