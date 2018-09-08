import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.sql.*;

public class addressdetails extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		Utilities utility = new Utilities(printWriter,request);
        HttpSession sc = request.getSession();
         String user = (String)sc.getAttribute("username");
         if(user==null )
         {
            response.sendRedirect("loginpage.html");
         }
         else{
                    StringBuilder s =new StringBuilder();

                        try
                        {

                            Class.forName("oracle.jdbc.driver.OracleDriver");  
                            Connection con=DriverManager.getConnection(  
                                "jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11"); 


                            PreparedStatement stmt=con.prepareStatement("select * from addressmiddlelayer where username ='"+user+"'",ResultSet.TYPE_SCROLL_SENSITIVE, 
                                                     ResultSet.CONCUR_UPDATABLE);   
                             ResultSet rs = stmt.executeQuery();
                           if(rs == null)
                           {
                             response.sendRedirect("myaccount.html");
                           }
                            int i= 1;
                             s.append("<fieldset style=\"width: 400px;margin-left:300px\">\n"
                                +"<legend>\n"
                                +"Address Details\n"
                                +"</legend>"
                                +"<span class=\"glyphicon glyphicon-plus text-primary\"></span><a href=\"myaccount.html\">Add new address</a>");
                            while(rs.next())
                            {
                                int locationid = rs.getInt("locationid");
                             stmt=con.prepareStatement("select * from address  where locationid="+rs.getInt("locationid")+"");  

                             ResultSet res = stmt.executeQuery();
                             res.next();
                            System.out.println("Executed second query records: " + res);
                               
                                s.append("<div style=\"width: 400px; border: 1px solid #f1f1f1;overflow-y: scroll;margin-top:5px \">\n"
                                    +"<h3 style=\"margin-left: 30px;\">\n"
                                    +"Address "+i+"\n"
                                    +"</h3>\n"

                                    +"<p style=\"margin-left: 30px;\">\n"
                                        +""+res.getString("addressline")+" <br>\n"
                                        +""+res.getString("city")+"  <br>\n"
                                        +""+res.getString("state")+"  <br>\n"
                                        +""+res.getString("zipcode")+"  <br>\n"
                                        +""+res.getString("country")+"  <br>\n"

                                    +"</p>\n"
                                    +"<a type=\"button\" href=\"paymentscreen?locationid="+locationid+"\" class=\"btn btn-success pull-right\" value=\"Select\">Pay</a>\n"

                                +"</div>\n");
                               
                               
                                i++;

                            }

                             s.append("</fieldset>\n");

                                    
                                    utility.printHtml("header.html","");
                                    utility.printHtml("sidebar.html","");
                                    utility.printHtml("cart",s.toString());
                                    utility.printHtml("footer.html","");

                        }
                        catch(Exception e)
                        {
                            System.out.println("In addrressdetails Exception " + e);
                        }
         }
        
       
	}
}
























































// +"<fieldset style=\"width: 400px;\">\n"
//     +"<legend>\n"
//     +"Address's\n"
//     +"</legend>\n"
//     +"<div style=\"width: 400px; border: 1px solid #f1f1f1;overflow-y: scroll;margin-top:5px \">\n"
//         +"<h3 style=\"margin-left: 30px;\">\n"
//         +"Address 1\n"
//         +"</h3>\n"

//         +"<p style=\"margin-left: 30px;\">\n"
//             +"2851 south king drive <br>\n"
//             +"Chicago <br>\n"
//             +"Illinois <br>\n"
//             +"60616 <br>\n"

//         +"</p>\n"
//         +"<input type=\"submit\" class=\"btn btn-success pull-right\" value=\"Select\">\n"

//     +"</div>\n"
   
//    +"</fieldset>\n"
