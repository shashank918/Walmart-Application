import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.sql.*;  


public class cartview extends HttpServlet{

   
    Integer cnt;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
    	StringBuilder s = new StringBuilder();
    	String catalinaHome = System.getProperty("catalina.home").replace("\\", "/");
        String imgpath = "images/";
        
        int item_id = Integer.parseInt(request.getParameter("item_id"));
        HttpSession sc = request.getSession();
        String username = (String)sc.getAttribute("username");
        
         try{
				// if(username==null )
		  //      {
				// response.sendRedirect("loginpage.html");
				// }
				// else{ 
	              

							Class.forName("oracle.jdbc.driver.OracleDriver");  
							Connection con=DriverManager.getConnection(  
										"jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11");    
				        	 PreparedStatement stmt=con.prepareStatement("delete from tempcart where id = ?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);  
				   			// stmt.setInt(1,item_id);
						   ResultSet rs = stmt.executeQuery();

						   System.out.println("Resultstatement length:" + rs);
						   	Integer size = 0;
						   if (rs != null)   
							{  
							  rs.beforeFirst();  
							  rs.last();  
							  size = rs.getRow();  
							}

							rs.beforeFirst();

				       
				        if (size == 0)

						{
							String message="<h1>Cart is Empty </h1>"+"<tr>"+"<td>"+"</td>"+"</tr>";
							Utilities utility = new Utilities(printWriter,request);
					    utility.printHtml("header.html"," ");
				        utility.printHtml("sidebar.html"," ");
				        utility.printHtml("cart",message);
						utility.printHtml("footer.html"," "); 
					
						}

						else{
        			     			        	
				        System.out.println("size in cartview   "+size);
				 		    String path="";
							s.append("<div id=\"content\">");
						
							System.out.println(size);
							int p;
							  int i=0;
							Integer total=0;
							  while(rs.next()){

							  	i++;
							  	System.out.println("current item id   " +rs.getInt("id")+"\n");
				               	path=imgpath+rs.getString("imgpath");
							    p = rs.getInt("price") * rs.getInt("quantity");
							  	 total+=p;
							  	 System.out.println(total);
							  	 System.out.println("Hi");
							  	 
							  	 s.append("<table  class=\"table table-hover table-condensed cart\">\n"
							  	 	     // +"<form action=\"\" method=\"get\">\n"
									    +"<thead>\n"
									    +"<tr>\n"
									        +"<th style=\"width:50%\">\n"
									            +"Product\n"
									        +"</th>\n"
									        +"<th style=\"width:10%\">\n"
									            +"Price\n"
									        +"</th>\n"
									        +"<th style=\"width:8%\">\n"
									           +" Quantity\n"
									        +"</th>\n"
									       
									        +"<th style=\"width:10%\">\n"
				                               +" Subtotal\n"
									        +"</th>\n"
									        +"<th style=\"width:6%\">\n"
				                               +" actions\n"
									        +"</th>\n"
									    +"</tr>\n"
									    +"</thead>\n"
									    +"<tbody>\n"
									    +"<tr>\n"
									        +"<td data-th=\"Product\">\n"
									            +"<div class=\"row\">\n"
									                +"<div class=\"col-sm-2 hidden-xs\">\n"
									                    +"<img src=\""+path+"\" alt=\"...\" class=\"img-responsive\"/>\n"
									                +"</div>\n"
									                +"<div class=\"col-sm-10\">\n"
									                    +"<h4 class=\"nomargin\">\n"
									                        +""+rs.getString("name")+"\n"
									                    +"</h4>\n"
									                    +"<p>\n"
									                       +" Here comes the Product description if any.\n"
									                        +"This is just a sample description.\n"
									                    +"</p>\n"
									                +"</div>\n"
									            +"</div>\n"
									        +"</td>\n"
									        +"<td data-th=\"Price\">\n"
									            +"$"+rs.getInt("price")+"\n"
									        +"</td>\n"
									          +"<form action=\"HomeServlet\" method=\"get\">\n"
									        +"<td data-th=\"Quantity\">\n"
									             +""+rs.getInt("quantity")+"\n"
									        // +"<input type=\"number\" class=\"form-control text-center\" value=\"1\">\n"
							                +"</td>\n"
							                
									        +"<td data-th=\"Subtotal\" class=\"text-center\">\n"
									            +"&nbsp&nbsp$"+p +"\n"
									        +"</td>\n"
									        +" <td class=\"actions\" data-th=\"actions\">\n"

									        +"<a type=\"button\" class=\"btn btn-info btn-sm\" href=\"cartcontroller?item_id="+rs.getInt("id")+"&action=remove&category="+"&quantity=1\">Remove</a>\n"
									        // cartcontroller?item_id="+items.get(key).get(4)+"&action=remove&category="+items.get(key).get(5)+"&quantity=1
									
									         // +"</form>"
									         +" </td>\n"
									    +"</tr>\n"
									    +"</tbody>\n"
						              +"</form>\n");
							  	 if(i!=size || i==size-1){
									s.append("</table>\n");
								}





							  }
							  s.append("<tfoot>\n"
							        +"<tr class=\"visible-xs\">\n"
							        +"<td class=\"text-center\"><strong>Total </strong></td>\n"
							        +"</tr>\n"
							        +"<tr>\n"
							        +"<td><a href=\"HomeServlet\" class=\"btn btn-warning\"><i class=\"fa fa-angle-left\"></i> Continue Shopping</a></td>\n"
							        +"<td colspan=\"2\" class=\"hidden-xs\"></td>\n"
							        +"<td class=\"hidden-xs text-center\"><strong>Total $"+total+"</strong></td>\n"
							        +"<td><a href=\"addressdetails\" class=\"btn btn-success btn-block\">Checkout <i class=\"fa fa-angle-right\"></i></a></td>\n"
							        +"</tr>\n"
							        +"</tfoot>\n"
							        );
							  if(i==size){
									s.append("</table>\n");
								}
							  s.append("</div>\n");
							  sc.setAttribute("totalprice",total);

				     

							//   if (rs != null)   
							// {  
							//   rs.beforeFirst(); 
							//   rs.last();  
							//   size = rs.getRow();  
							// }

							// int lastprodid = rs.getInt("id");

							// // rs.beforeFirst();

							// System.out.println("Testing size : "+ size+"Id : "+ lastprodid);

							//  //  Class.forName("com.mysql.jdbc.Driver");  
							// 	// Connection con=DriverManager.getConnection(  
							// 	// "jdbc:mysql://localhost:3306/trail","root","1234");  
				   //      	 PreparedStatement stm=con.prepareStatement("select * from accessorydetails where prod_id=?");
				   //      	 stm.setInt(1,lastprodid);
				   //      	 ResultSet accrs = stm.executeQuery();  

				   //        s.append("<div class=\"container\">");
				  
				   //        s.append("<fieldset> <legend>you may also like</legend>");
							// s.append("<div id=\"carousel-example-generic\" class=\"carousel slide\" data-ride=\"carousel\">\n"
				       
				   //      +"<ol class=\"carousel-indicators\">\n"
				   //          +"<li data-target=\"#carousel-example-generic\" data-slide-to=\"0\" class=\"active\"></li>\n"
				   //          +"<li data-target=\"#carousel-example-generic\" data-slide-to=\"1\"></li>\n"
				   //          +"<li data-target=\"#carousel-example-generic\" data-slide-to=\"2\"></li>\n"
				   //      +"</ol>\n"
				   //     +"<div class=\"carousel-inner\">\n");
			    //                cnt = 0;
		                  			                 
				   //                 // ArrayList<Integer> acc1 = refmap.get(item_id).getAccessory();
				   //                 //  Iterator<Integer> itr1=acc1.iterator();  
				   //                while(accrs.next()){  
				   //                	PreparedStatement stmt1=con.prepareStatement("select * from productdetails where id=?");  
				   //                	stmt1.setInt(1,accrs.getInt("acc_id"));
				   //                	ResultSet r = stmt1.executeQuery();
				   //                	r.next();
				   //                    // if(accessory.containsKey(ic)){

				   //                    	 int message = accrs.getInt("acc_id");
				   //                    	 System.out.println(message);
				   //                		if(cnt==0 ){
				   //                       s.append("<div class=\"row item active\">\n");
				   //                		}
				   //                		if(cnt==3){
				   //                			 s.append("<div class=\"row item\">\n");
				   //                		}
				   //                    	s.append("<div class=\" col-md-4 col-sm-4 col-xs-4 col-lg-4\">\n"
						 //                    +"<div class=\"thumbnail bg-primary\">\n"
						 //                        +"<img src=\"images/"+r.getString("imgpath")+"\" alt=\"First slide\">\n"
						 //                        +"<!-- Static Header -->\n"
						 //                        +"<div style=\"text-align: center\">\n"
					  //                      		    +"<b class=\"text-primary\" style=\"font-size: 22px;margin-left: 25px\">"+r.getString("name")+"</b><br>\n"
					  //                       	    +"<ul>\n"
						 //                                +"<li><a type=\"button\" class=\"btn btn-default disabled\" style=\"margin-bottom:5px;\" href=\"#\">$"+ r.getInt("price")+"</a><br></li>\n"
							// 			                +" <li><a type=\"button\" class=\"btn btn-primary\" style=\"margin-left: 5px\" href=\"cartcontroller?item_id="+r.getInt("id")+"&action=add&quantity=1\">Buy Now</a></li>\n"
							// 			                +"<li><a type=\"button\" class=\"btn btn-primary\" href=\"reviewform?action=read&name="+r.getString("name")+"\">Reviews</a></li>\n"
							// 			                +"<li><a type=\"button\" class=\"btn btn-primary\" href=\"reviewform?productid="+r.getInt("id")+"&name="+r.getString("name")+"&category="+r.getString("category")+"&price="+r.getString("price")+"&action=write\">Write Review</a></li>\n"
						 //                            +"</ul>\n"
						 //                        +"</div>\n"
				                     
				   //           	       +"</div>\n"
				   //            	      +"</div>\n");
				   //                     cnt++;
				   //                     System.out.println("Count value"+cnt);

				   //                     if(cnt%3==0){
				   //                     	s.append("</div>\n");
				   //                     }
				                     



				   //                       // }
				   //                   }

				   //                   s.append("</div>\n");

				   //                    s.append("<a class=\"left carousel-control\" href=\"#carousel-example-generic\" data-slide=\"prev\">\n"
							// 	           +"<span class=\"glyphicon glyphicon-chevron-left\"></span>\n"
							// 	       +"</a>\n"
							// 	       +"<a class=\"right carousel-control\" href=\"#carousel-example-generic\" data-slide=\"next\">\n"
							// 	           +"<span class=\"glyphicon glyphicon-chevron-right\"></span>\n"
							// 	       +"</a>\n");
				   //                    s.append("</div>\n");
				   //                    s.append("</fieldset>\n");
				   //                    s.append("</div>");

				                      

				            
					Utilities utility = new Utilities(printWriter,request);
				    utility.printHtml("header.html"," ");
			        utility.printHtml("sidebar.html"," ");
			        utility.printHtml("cart",s.toString());
					utility.printHtml("footer.html"," "); 
				
						}
				       
                   	 // }ending else
                    }catch(Exception e){
						System.out.println("In cartview Exception"  + e.getMessage());
					}
					
				}
}

































		









// s.append("<fieldset> <legend>Related items</legend>");
// 							s.append("<div id=\"carousel-example-generic\" class=\"carousel slide\" data-ride=\"carousel\">\n"
				       
// 				        +"<ol class=\"carousel-indicators\">\n"
// 				            +"<li data-target=\"#carousel-example-generic\" data-slide-to=\"0\" class=\"active\"></li>\n"
// 				            +"<li data-target=\"#carousel-example-generic\" data-slide-to=\"1\"></li>\n"
// 				            +"<li data-target=\"#carousel-example-generic\" data-slide-to=\"2\"></li>\n"
// 				        +"</ol>\n"
// 				       +"<div class=\"carousel-inner\">\n");
// 			                   cnt = 0;
		                  			                 
// 				                   // ArrayList<Integer> acc1 = refmap.get(item_id).getAccessory();
// 				                   //  Iterator<Integer> itr1=acc1.iterator();  
// 				                  while(accrs.next()){  
// 				                  	PreparedStatement stmt1=con.prepareStatement("select * from productdetails where id=?");  
// 				                  	stmt1.setInt(1,accrs.getInt("acc_id"));
// 				                  	ResultSet r = stmt1.executeQuery();
// 				                  	r.next();
// 				                      // if(accessory.containsKey(ic)){

// 				                      	// message += i+"  "+"  "+accessory.get(i).getImg()+"  "+accessory.get(i).getName()+"  " +accessory.get(i).getCondition()+"  "+"  "+accessory.get(i).getPrice()+"<br>";
// 				                  		if(cnt==0 ){
// 				                         s.append("<div class=\"row item active\">\n");
// 				                  		}
// 				                  		if(cnt==3){
// 				                  			 s.append("<div class=\"row item\">\n");
// 				                  		}
// 				                      	s.append("<div class=\" col-md-4 col-sm-4 col-xs-4 col-lg-4\">\n"
// 						                    +"<div class=\"thumbnail bg-primary\">\n"
// 						                        +"<img src=\"images/"+r.getString("imgpath")+"\" alt=\"First slide\">\n"
// 						                        +"<!-- Static Header -->\n"
// 						                        +"<div style=\"text-align: center\">\n"
// 					                       		    +"<b class=\"text-primary\" style=\"font-size: 22px;margin-left: 25px\">"+r.getString("name")+"</b><br>\n"
// 					                        	    +"<ul>\n"
// 						                                +"<li><a type=\"button\" class=\"btn btn-default disabled\" style=\"margin-bottom:5px;\" href=\"#\">$"+ r.getInt("price")+"</a><br></li>\n"
// 										                +" <li><a type=\"button\" class=\"btn btn-primary\" style=\"margin-left: 5px\" href=\"cartcontroller?item_id="+r.getInt("id")+"&action=add&quantity=1\">Buy Now</a></li>\n"
// 										                +"<li><a type=\"button\" class=\"btn btn-primary\" href=\"#\">Reviews</a></li>\n"
// 										                +"<li><a type=\"button\" class=\"btn btn-primary\" href=\"#\">Write Review</a></li>\n"
// 						                            +"</ul>\n"
// 						                        +"</div>\n"
				                     
// 				             	       +"</div>\n"
// 				              	      +"</div>\n");
// 				                       cnt++;
// 				                       System.out.println("Count value"+cnt);

// 				                       if(cnt%3==0){
// 				                       	s.append("</div>\n");
// 				                       }
				                     



// 				                         // }
// 				                     }

// 				                     s.append("</div>\n");

// 				                      s.append("<a class=\"left carousel-control\" href=\"#carousel-example-generic\" data-slide=\"prev\">\n"
// 								           +"<span class=\"glyphicon glyphicon-chevron-left\"></span>\n"
// 								       +"</a>\n"
// 								       +"<a class=\"right carousel-control\" href=\"#carousel-example-generic\" data-slide=\"next\">\n"
// 								           +"<span class=\"glyphicon glyphicon-chevron-right\"></span>\n"
// 								       +"</a>\n");
// 				                      s.append("</div>\n");
// 				                      s.append("</fieldset>\n");
// 				                      s.append("</div>");

















