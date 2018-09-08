import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.*;

public class receipt extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		HttpSession sc = request.getSession();
		int locationid = Integer.parseInt(request.getParameter("locationid"));
		StringBuilder s = new StringBuilder();
		try{
					 Class.forName("oracle.jdbc.driver.OracleDriver");  
					  Connection con=DriverManager.getConnection(  
			            "jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11"); 
					  	PreparedStatement stmt=con.prepareStatement("select * from address where locationid="+locationid+"",ResultSet.TYPE_SCROLL_SENSITIVE, 
			                                 ResultSet.CONCUR_UPDATABLE);   
			         ResultSet rs = stmt.executeQuery();
			         rs.next();
			          int ordernum = (int)sc.getAttribute("orderNum");
			          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
						LocalDate localDate = LocalDate.now();
						// System.out.println(dtf.format(localDate)); //2016/11/16
			   //        Random rnd = new Random();
			          // float randomNum = 100000 + (int)(Math.random() * 900000); 
			          // int randomNum = 100000 + rnd.nextInt(900000);
			          Utilities utility = new Utilities(printWriter,request);
			          //  cartitems ecart;
			          //  ecart = (cartitems) sc.getAttribute("cart");
			          //  HashMap<Integer, List<String>> items = ecart.getItems();

			          //   for(Integer key : items.keySet()){
			          //   	System.out.println(items.get(key).get(0));
			          //   }
			     

			     s.append("<div class=\"container\" style=\"margin-top: 10px\">\n"
							+"<div class=\"row\">\n"
							    +"<div class=\"well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3\">\n"
							        +"<div class=\"row\">\n"
							            +"<div class=\"col-xs-6 col-sm-6 col-md-6\">\n"
							                +"<address>\n"
							                    +"<strong>"+rs.getString("addressline")+"</strong>\n"
							                   +"<br>\n"
							                     +"\n"
							                   +"<br>\n"
							                    +""+rs.getString("city")+", "+rs.getString("state")+" "+rs.getString("zipcode")+"\n"
							                   +"<br>\n"
							                   +"<abbr title=\"Phone\"></abbr>\n"
							               +"</address>\n"
							           +"</div>\n"
							           +"<div class=\"col-xs-6 col-sm-6 col-md-6 text-right\">\n"
							               +"<p>\n"
							                   +"<em>Date: "+dtf.format(localDate)+"</em>\n"
							               +"</p>\n"
							               +"<p>\n"
							                   +"<em>Order #: "+ordernum+"</em>\n"
							              +"</p>\n"
							          +"</div>\n"
							      +"</div>\n"
							      +"<div class=\"row\">\n"
							          +"<div class=\"text-center\">\n"
							              +"<h1>Receipt</h1>\n"
							          +"</div>\n"
							          +"</span>\n"
							          +"<table class=\"table table-hover\">\n"
							              
							               +"<tr>\n"
							                  +"<td></td>\n"
							                  +"<td></td>\n"
							                  
							                  +"<td class=\"text-center\">\n"
							                    
							                  +"You order will be delivered to above address in 2 weeks\n"
							                       +"</p></td>\n"
							              +"</tr>\n"
							               +"<tr>\n"
							                  +"<td></td>\n"
							                  +"<td></td>\n"
							                  // +"<td class=\"text-right\"><h4><strong>Total:</strong></h4></td>\n"
							                  // +"<td class=\"text-center text-danger\"><h4><strong>$"++"</strong></h4></td>\n"
							              +"</tr>\n"
							               +"</tbody>\n"
							           +"</table>\n"
							           +"<a type=\"button\" href=\"HomeServlet\" class=\"btn btn-success btn-lg pull-right\" onclick=\"window.print()\">\n"
							                +"Print <span class=\"glyphicon glyphicon-print\"></span>\n"
							           +"</a></td>\n"
							       +"</div>\n"
							   +"</div>\n"
							+"</div>\n"
							+"</div>");

					System.out.println(sc.getAttribute("addressline1"));


			        utility.printHtml("header.html","");
			        utility.printHtml("sidebar.html","");
			        utility.printHtml("cart",s.toString());
			        utility.printHtml("footer.html","");
			    }
			    catch(Exception e)
			    {
			    	System.out.println("In receipt Exception : "+ e);
			    }

        // response.sendRedirect("HomeServlet");
	}
}


















































