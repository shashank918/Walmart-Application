import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.sql.*;
public class storeorders extends HttpServlet {

	



    public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
	try
	 {

					HttpSession sc = request.getSession();
					String username = (String)sc.getAttribute("username");
					int locationid = Integer.parseInt(request.getParameter("locationid"));
					System.out.println("Location id " + locationid);
					String orderseqval = (String) sc.getAttribute("salesOrderSeqValue");
					System.out.println("salesOrderSeqValue" + orderseqval);
					 Class.forName("oracle.jdbc.driver.OracleDriver");  
					  Connection con=DriverManager.getConnection(  
                        "jdbc:oracle:thin:@localhost:1521:xe","sumanthgouru","$umanth11"); 
					 int seqval = 0;
                    
					if(orderseqval == null)
					{
						System.out.println("Inside if ");
						
						System.out.println("Seqval "+ seqval);
						PreparedStatement stmt=con.prepareStatement("select sales_header_sequence.nextVal from dual",ResultSet.TYPE_SCROLL_SENSITIVE, 
                                             ResultSet.CONCUR_UPDATABLE);   
                     ResultSet rs = stmt.executeQuery();
                     rs.next();
                     seqval = rs.getInt(1);
                     // seqval = rs.getInt(1);
                    }
                    System.out.println("seqval :" + seqval +"Username :" +username+"Locationid :"+locationid);
                   

                   PreparedStatement stmt1=con.prepareStatement("Insert into sales_orders_header values("+seqval+",'"+username+"',"+locationid+")");   
                     stmt1.executeUpdate();

                   stmt1=con.prepareStatement("select * from tempcart",ResultSet.TYPE_SCROLL_SENSITIVE, 
                                             ResultSet.CONCUR_UPDATABLE);   
                     ResultSet rs1 = stmt1.executeQuery();  

                     while(rs1.next())
                     {
                     	int t = rs1.getInt("quantity") * rs1.getInt("price");
                     	 stmt1=con.prepareStatement("insert into sales_orders_lines values(sales_lines_sequence.nextVal,"+seqval+","+rs1.getInt("id")+","+rs1.getInt("quantity")+",'"+rs1.getString("uom")+"',"+rs1.getInt("price")+","+t+")");   
                    		  stmt1.executeUpdate();


                		   stmt1=con.prepareStatement("update products set inventory_count=inventory_count-"+rs1.getInt("quantity")+" where id="+rs1.getInt("id")+"");   
                    		  stmt1.executeUpdate();


                     }

                     sc.setAttribute("orderNum", seqval);
                     stmt1=con.prepareStatement("delete from tempcart");   
                    		  stmt1.executeUpdate();



                     response.sendRedirect("receipt?locationid="+locationid+"");



		} 
		catch (Exception e) 
		{

			System.out.println("In storeorders Exception : " + e);

		} 
     
    }   

}














































// import java.io.*;
// import javax.servlet.*;
// import javax.servlet.http.*;
// import java.io.IOException;
// import java.util.*;


// public class storeorders extends HttpServlet{


// 	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

// 		response.setContentType("text/html");
// 		PrintWriter printWriter = response.getWriter();
// 		String catalinaHome = System.getProperty("catalina.home").replace("\\", "/");
//         String FilePath = catalinaHome+"/webapps/trail/"+"storeorders.txt";
// 		 HttpSession sc = request.getSession();

//         cartitems ecart;
//         ecart = (cartitems) sc.getAttribute("cart");

//         try{

//         	 File file = new File(FilePath);
//         	    if (!file.exists()){
//                         file.createNewFile();
//                    }

        	 
//               FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
//                BufferedWriter bw = new BufferedWriter(fw);
//                bw.write("userid");
//                bw.write("password");
//                response.sendRedirect("HomeServlet");

//         }catch (IOException e) 
//            {
//             e.printStackTrace();
        
//             }

// 	}

// }