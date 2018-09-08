import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.sql.*;
public class paymentscreen extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		Utilities utility = new Utilities(printWriter,request);
        HttpSession sc = request.getSession();
         String user = (String)sc.getAttribute("username");
         int locationid =Integer.parseInt( request.getParameter("locationid"));
         if(user==null )
         {
            response.sendRedirect("loginpage.html");
         }
         else
         {
                     StringBuilder s =new StringBuilder();

        // style=\"margin-left: 20%;margin-top: 50px;\"

                   try
                        {

                            Class.forName("oracle.jdbc.driver.OracleDriver");  
                            Connection con=DriverManager.getConnection(  
                                "jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11"); 


                            PreparedStatement stmt=con.prepareStatement("select * from carddetails where username ='"+user+"'",ResultSet.TYPE_SCROLL_SENSITIVE, 
                                                     ResultSet.CONCUR_UPDATABLE);   
                             ResultSet rs = stmt.executeQuery();
                           // if(rs == null)
                           // {
                           //   response.sendRedirect("myaccount.html");
                           // }

                            int i= 1;
                             s.append("<fieldset style=\"width: 400px;margin-left:300px\">\n"
                                +"<legend>\n"
                                +"Card Details\n"
                                +"</legend>"
                                +"<span class=\"glyphicon glyphicon-plus text-primary\"></span><a href=\"myaccount.html\">Add new card</a>");
                            while(rs.next())
                            {
                             //    int locationid = rs.getInt("locationid");
                             // stmt=con.prepareStatement("select * from carddetails  where locationid="+rs.getInt("locationid")+"");  

                             // ResultSet res = stmt.executeQuery();
                             // res.next();
                            System.out.println("Executed query records: " + rs);
                               
                                s.append("<div style=\"width: 400px; border: 1px solid #f1f1f1;overflow-y: scroll;margin-top:5px \">\n"
                                    +"<h3 style=\"margin-left: 30px;\">\n"
                                    +"Card "+i+"\n"
                                    +"</h3>\n"

                                    +"<p style=\"margin-left: 30px;\">\n"
                                        +"cardnumber: "+rs.getString("cardnumber")+" <br>\n"
                                        +"Exp:"+rs.getString("expmonth")+"\n"
                                        +"/"+rs.getString("expyear")+"  <br>\n"
                                       

                                    +"</p>\n"
                                    +"<a type=\"button\" href=\"storeorders?locationid="+locationid+"\" class=\"btn btn-success pull-right\" >Select</a>\n"

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
                            System.out.println("In paymentscreen Exception " + e);
                        }
         }
       
    }
}

































    

























