
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
public class cartcontroller extends HttpServlet{

   
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
     response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		HttpSession sc = request.getSession();
		String username = (String) sc.getAttribute("username");
		if(username == null)
		{	

				response.sendRedirect("loginpage.html");

		}

		else{
			 String req_item="";


		 Integer item_id = Integer.parseInt(request.getParameter("item_id"));
		 // String category = request.getParameter("category");
		 // System.out.println("category in cart controller"+category);
		 String action = request.getParameter("action");
		 int quantity = Integer.parseInt(request.getParameter("quantity"));
		 

		// HttpSession sc = request.getSession();
	 //  String username = (String)sc.getAttribute("username");
	 //    // System.out.println((String)sc.getAttribute("username"));
		// 		if(username==null )
		//        {
		// 		response.sendRedirect("loginpage.html");
		// 		}
		// 		else{

					if(action.equals("add"))
					{
								 try{
					                      
					                //here sonoo is database name, root is username and password  
					             	Class.forName("oracle.jdbc.driver.OracleDriver");  
							Connection con=DriverManager.getConnection(  
										"jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11");    
				        	 // PreparedStatement stmt2=con.prepareStatement("select * from tempcart",ResultSet.TYPE_SCROLL_SENSITIVE, 
              //           ResultSet.CONCUR_UPDATABLE);  
					               PreparedStatement stmt2=con.prepareStatement("select * from tempcart where id = ?");  
					               stmt2.setInt(1,item_id);
					               ResultSet res = stmt2.executeQuery();
					               if(res.next()){
					                int q = res.getInt("quantity") + 1;
					                stmt2 = con.prepareStatement("update tempcart SET quantity=? where id = ?");
					                stmt2.setInt(1,q);
					                stmt2.setInt(2,item_id);
					                stmt2.executeUpdate();
					                System.out.println(" Inside if"); 
					               }
					               else{
					                     PreparedStatement retdetails=con.prepareStatement("select * from products where id = ?");  
					                      retdetails.setInt(1,item_id);
					                      ResultSet rs = retdetails.executeQuery();
					                      if(rs.next()){
					                        PreparedStatement stmt1=con.prepareStatement("insert into tempcart values(?,?,?,?,?,?,?,?,?,?)");
					                          stmt1.setInt(1,rs.getInt("id"));
					                          stmt1.setString(2,rs.getString("name"));
					                          stmt1.setString(3,rs.getString("uom"));
					                          stmt1.setInt(4,rs.getInt("brand_id"));
					                          stmt1.setInt(5,rs.getInt("vendorid"));
					                          stmt1.setInt(6,rs.getInt("storeid"));
					                          stmt1.setInt(7,rs.getInt("inventory_count"));
					                          stmt1.setString(8,rs.getString("imgpath"));
					                          stmt1.setInt(9,rs.getInt("price"));
					                          stmt1.setInt(10,1);

					                          int i=stmt1.executeUpdate();  
					                          System.out.println(i+" records inserted"); 
					                      }

					              
					                System.out.println(" Inside else" + rs.getString("name"));  
					                             
					               }
					                
					                con.close();
					                  response.sendRedirect("cartview?item_id="+item_id+"");
					             }
					             catch(Exception e)
					             {
					                   System.out.println("In Insert statement Exception " + e.getMessage());
				                  }

					}
					else if(action.equals("remove")){

						// OracleDB ob = new OracleDB(printWriter,request,response);
      //  					 ob.deletefromtempCart(item_id);
						 try{
           						Class.forName("oracle.jdbc.driver.OracleDriver");  
									Connection con=DriverManager.getConnection(  
												"jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11");    
						   	 PreparedStatement stmt3=con.prepareStatement("delete from tempcart where id = ?",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                        ResultSet.CONCUR_UPDATABLE);  
				             stmt3.setInt(1,item_id);
				             stmt3.executeUpdate();

				             response.sendRedirect("cartview?item_id="+item_id+"");
				            }catch(Exception e){
				              System.out.println("Inside cartcontroller remove block: " + e.getMessage());
				            }

					}
		}
		
				// } ending else
  
 		       


	}

}


