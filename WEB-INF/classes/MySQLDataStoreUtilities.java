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


public class MySQLDataStoreUtilities extends HttpServlet{
	private PrintWriter printWriter;
	private HttpServletRequest request;
	private HttpServletResponse response;
  Connection con;
  Utilities utility;
  StringBuilder s = new StringBuilder();
  String path = "images/";
  String message;

	public MySQLDataStoreUtilities(PrintWriter printWriter, HttpServletRequest request,HttpServletResponse response){
		this.printWriter = printWriter;
		this.request = request;
		this.response = response;
    utility = new Utilities(printWriter,request);
    try{
     Class.forName("com.mysql.jdbc.Driver");
             con=DriverManager.getConnection(  
              "jdbc:mysql://localhost:3306/trail?autoReconnect=true&useSSL=false","root","1234");
    }catch(Exception e){
      System.out.println("In MySQLDataStoreUtilities Constructor : "+ e.getMessage());
    }
	
	}

	public void signup(String userid,String password,String type){


		try {

          
            
              PreparedStatement stmt=con.prepareStatement("select * from logindetails where username = ?");  
               stmt.setString(1,userid);

               ResultSet rs = stmt.executeQuery();
               if(rs.next()){

                System.out.println("User name alreadyy exists");
                response.setContentType("text/html");
                response.sendRedirect("signup.html");
               }

               else{

                 PreparedStatement stmt1=con.prepareStatement("insert into logindetails values(?,?,?)");
                stmt1.setString(1,userid);
                stmt1.setString(2,password);
                stmt1.setString(3,type);
                

                int i=stmt1.executeUpdate();  

                //  stmt.setInt(1,1);
                // stmt.setInt(2,61);

                // stmt.executeUpdate(); 
                System.out.println(i+" records inserted into logindetails");  
                 response.sendRedirect("loginpage.html");

              }

            


	       }  
           catch (Exception e) 
           {
            System.out.println("In Registration page Exception " + e.getMessage());
        
            }

	}

	public void login(String username,String password){

		    try {

              PreparedStatement stmt=con.prepareStatement("select * from logindetails where username = ? and password= ?");  
               stmt.setString(1,username);
               stmt.setString(2,password);

               ResultSet rs = stmt.executeQuery();
            if(rs.next()){
               // System.out.println("Success");
              HttpSession session = request.getSession();
              session.setAttribute("username",rs.getString("username"));
              session.setAttribute("usertype",rs.getString("role"));
              System.out.println(rs.getString("role")+"logged in");
              // response.setContentType("text/html");
             

              if(rs.getString("role").equalsIgnoreCase("salesman")){

                response.sendRedirect("salesmanui.html");

              }
              else if(rs.getString("role").equalsIgnoreCase("user") || rs.getString("role").equalsIgnoreCase("manager") ){
                response.sendRedirect("HomeServlet");
              }
               
          
            }
            else{
                   response.setContentType("text/html");
                   response.sendRedirect("loginpage.html");

            }
            // reader.close();
 
        } 
        catch (Exception e) {
            e.printStackTrace();
        
         }

	}

  public void addtotempCart(int item_id,int quantity){

           try{
                      
                //here sonoo is database name, root is username and password  
              Connection con = createConnection()
               PreparedStatement stmt=con.prepareStatement("select * from tempcart where id = ?");  
               stmt.setInt(1,item_id);
               ResultSet res = stmt.executeQuery();
               if(res.next()){
                int q = res.getInt("quantity") + 1;
                stmt = con.prepareStatement("update tempcart SET quantity=? where id = ?");
                stmt.setInt(1,q);
                stmt.setInt(2,item_id);
                stmt.executeUpdate();
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
                          stmt1.setString(4,rs.getInt("brand_id"));
                          stmt1.setInt(5,rs.getInt("vendorid"));
                          stmt1.setString(6,rs.getInt("storeid"));
                          stmt1.setString(7,rs.getInt("inventory_count"));
                          stmt1.setInt(8,rs.getString("imgpath"));
                          stmt1.setInt(9,rs.getString("price"));
                          stmt1.setInt(10,rs.getInt("quantity"));

                          int i=stmt1.executeUpdate();  
                          System.out.println(i+" records inserted"); 
                      }

              
                System.out.println(" Inside else" + rs.getString("name"));  
                             
               }
                
                  response.sendRedirect("cartview?item_id="+item_id+"");
             }catch(Exception e){
                   System.out.println("In Insert statement Exception " + e.getMessage());
                  }

               
  }
  public void deletefromtempCart(int item_id){
        try{
           
             PreparedStatement removeprod=con.prepareStatement("delete from tempcart where id = ?");  
             removeprod.setInt(1,item_id);
             removeprod.executeUpdate();

             response.sendRedirect("cartview?item_id="+item_id+"");
            }catch(Exception e){
              System.out.println("Inside cartcontroller remove block: " + e.getMessage());
            }
  }


  public void vieworders(){

    s.append("<div id=\"content\">");
        s.append("<fieldset><legend>Orders History</legend>");
          try {
            //  Class.forName("com.mysql.jdbc.Driver");  
            // Connection con=DriverManager.getConnection(  
            // "jdbc:mysql://localhost:3306/trail","root","1234"); 
            PreparedStatement vo=con.prepareStatement("select * from ordershistory");
             
               ResultSet rsvo = vo.executeQuery();
               while (rsvo.next()) {
             
                        message = rsvo.getString("name") +"    "+ rsvo.getString("name") + "        "+rsvo.getString("imgpath")+"<br>";
                        System.out.println(message);
                        s.append("<table  class=\"table table-hover table-condensed cart\">\n"
                       
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
                                      +"<img src=\""+path+rsvo.getString("imgpath")+"\" alt=\"...\" class=\"img-responsive\"/>\n"
                                  +"</div>\n"
                                  +"<div class=\"col-sm-10\">\n"
                                      +"<h4 class=\"nomargin\">\n"
                                          +""+rsvo.getString("name")+"\n"
                                      +"</h4>\n"
                                      +"<p>\n"
                                         +" Here comes the Product description if any.\n"
                                          +"This is just a sample description.\n"
                                      +"</p>\n"
                                  +"</div>\n"
                              +"</div>\n"
                          +"</td>\n"
                          +"<td data-th=\"Price\">\n"
                              +"$"+rsvo.getString("price")+"\n"
                          +"</td>\n"
                            +"<form action=\"HomeServlet\" method=\"get\">\n"
                          +"<td data-th=\"Quantity\">\n"
                               // +""+rsvo.getInt("quantity")+"\n"
                          +"<input type=\"number\" class=\"form-control text-center\" value=\"1\">\n"
                              +"</td>\n"
                              
                          +"<td data-th=\"Subtotal\" class=\"text-center\">\n"
                              +"&nbsp&nbsp$"+rsvo.getInt("price")*rsvo.getInt("quantity") +"\n"
                          +"</td>\n"
                          +" <td class=\"actions\" data-th=\"actions\">\n"
                                // +" <a type =\"button\" href=\"manageOrders?ordernumber="+rsvo.getInt("orderid")+"&itemid="+rsvo.getInt("id")+"&action=vieworders\" class=\"btn btn-info btn-sm\"><i class=\"fa fa-refresh\"></i></a>\n"
                                // +" <a type=\"button\" href=\"manageOrders?ordernumber="+rsvo.getInt("orderid")+"&itemid="+rsvo.getInt("id")+"&action=delete\" class=\"btn btn-danger btn-sm\"><i class=\"fa fa-trash-o\"></i></a>\n"
                         
                           +" </td>\n"
                      +"</tr>\n"
                      +"</tbody>\n"
                          +"</form>\n");
                  
                  s.append("</table>\n");
                
               }
               s.append("</fieldset>");
               s.append("</div>");

             
                 utility.printHtml("header.html","");
                utility.printHtml("sidebar.html","");
                utility.printHtml("cart",s.toString());
                utility.printHtml("footer.html","");



          } catch (Exception e) {
                System.out.println("In Vieworders Exception: " + e.getMessage());
            
             }

  }


  public void manipulateProducts(int id,String productname,int price,String imgpath,String category,String action){
    try{

        if(action.equals("Add")){

          System.out.println("Action requested"+action);
            Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/trail?autoReconnect=true&useSSL=false","root","1234");

                PreparedStatement stmt=con.prepareStatement("insert into productdetails values(?,?,?,?,?)");

                stmt=con.prepareStatement("insert into productdetails values(?,?,?,?,?)");
                
                stmt.setInt(1,id);
                stmt.setString(2,productname);
                stmt.setInt(3,price);
                stmt.setString(4,category);
                stmt.setString(5,imgpath);
                stmt.executeUpdate();
                System.out.println("Records inserted");
                }
           else if(action.equals("Update")){

            System.out.println("Action requested"+action + " id requested" + id);
            System.out.println("message "+id + " " + productname+" " + price+" " + category+" " + imgpath);
            Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/trail?autoReconnect=true&useSSL=false","root","1234");


            PreparedStatement update=con.prepareStatement("update productdetails SET id = ?, name=?, price = ?,category = ? ,imgpath = ? where id = ? ");


                // update=con.prepareStatement("update productdetails set id = ?, name=?,price = ?,category = ? ,imgpath = ? where id = ? ");
                
                update.setInt(1,id);
                update.setString(2,productname);
                update.setInt(3,price);
                update.setString(4,category);
                update.setString(5,imgpath);
                update.setInt(6,id);

                update.executeUpdate();
                
           }

             else if(action.equals("Delete")){
              Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/trail?autoReconnect=true&useSSL=false","root","1234");
              System.out.println("Action requested"+action);
             PreparedStatement delete=con.prepareStatement("delete from productdetails where id = ?");
             delete.setInt(1,id);
             delete.executeUpdate();
                
           }
           response.sendRedirect("HomeServlet");

        }
        catch(Exception e){
        System.out.println("In Add Products Exception: "+ e.getMessage());
        }
  }

  

}
























































<div id="content">
        "<fieldset><legend>Orders History</legend>
         
             
              
                        <table  class="table table-hover table-condensed cart">

                     <thead>
                     <tr>
                         <th style="width:50%">
                             Product
                         </th>
                         <th style="width:10%">
                             Price
                         </th>
                         <th style="width:8%">
                             Quantity
                         </th>

                         <th style="width:10%">
                                           Subtotal
                             </th>
                             <th style="width:6%">
                                           actions
                             </th>
                         </tr>
                         </thead>
                         <tbody>
                         <tr>
                             <td data-th="Product">
                                 <div class="row">
                                     <div class="col-sm-2 hidden-xs">
                                         <img src=""+path+rsvo.getString("imgpath")+"" alt="..." class="img-responsive"/>
                                     </div>
                                     <div class="col-sm-10">
                                         <h4 class="nomargin">
                                             "+rsvo.getString("name")
                                         </h4>
                                         <p>
                                             Here comes the Product description if any.
                                             This is just a sample description.
                                         </p>
                                     </div>
                                 </div>
                             </td>
                             <td data-th="Price">
                                 $"+rsvo.getString("price")
                             </td>
                               <form action="HomeServlet" method="get">
                             <td data-th="Quantity">
                                   //"+rsvo.getInt("quantity")
                             <input type="number" class="form-control text-center" value="1">
                                 </td>

                         <td data-th="Subtotal" class="text-center">
                                 &nbsp&nbsp$"+rsvo.getInt("price")*rsvo.getInt("quantity")
                             </td>
                              <td class="actions" data-th="actions">
                                    // <a type ="button" href="manageOrders?ordernumber="+rsvo.getInt("orderid")+"&itemid="+rsvo.getInt("id")+"&action=vieworders" class="btn btn-info btn-sm"><i class="fa fa-refresh"></i></a>
                                    // <a type="button" href="manageOrders?ordernumber="+rsvo.getInt("orderid")+"&itemid="+rsvo.getInt("id")+"&action=delete" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></a>

                           </td>
                           </tr>
                           </tbody>
                               </form>
                  </table>
                             
               </fieldset>
               </div>