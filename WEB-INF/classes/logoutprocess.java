import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class logoutprocess extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		try{

		    HttpSession session=request.getSession();  
            session.invalidate();  

             Class.forName("oracle.jdbc.driver.OracleDriver");  
							Connection con=DriverManager.getConnection(  
										"jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11"); 
              //   //here sonoo is database name, root is username and password  
                PreparedStatement stmt=con.prepareStatement("delete from tempcart");
                stmt.executeUpdate();
                Utilities utility = new Utilities(printWriter,request);
		        utility.printHtml("header.html","");
		        utility.printHtml("sidebar.html","");
		        utility.printHtml("content.html","Watches");
		        utility.printHtml("footer.html","");

             }
             catch(Exception e){
             	System.out.println(e.getMessage());
             	             }
	    // System.out.println((String)sc.getAttribute("username"));
	

		
   	 
	}

}