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

public class Utilities extends HttpServlet{

	private PrintWriter printWriter;
	private HttpServletRequest request;
	String s;
	String flag="file";
  // HashMap <Integer, Account> smartwatches; 
  //  HashMap <Integer, Account> speakers; 
  //  HashMap <Integer, Account> headphones; 
  //  HashMap <Integer, Account> phones; 
  //  HashMap <Integer, Account> laptops; 
  //  HashMap <Integer, Account> externalstorage; 
  //  HashMap <Integer, Account> accessory; 
   // ReadXMLFileUsingSaxparser obj = new ReadXMLFileUsingSaxparser(); 
   //  HashMap <Integer,Account> genericmap;
   //  HashMap <Integer,Account> refmap;
    String cat;
   String catalinaHome = System.getProperty("catalina.home").replace("\\", "/");

   String imgpath = "images/";

	public Utilities(PrintWriter printWriter, HttpServletRequest request){
		this.printWriter = printWriter;
		this.request = request;
	
	}

	public void printHtml(String fileName,String category){

        String htmlFilePath = catalinaHome+"/webapps/walmartapplication/"+fileName;
		cat = category;

		if(fileName.equals("content.html")&&category!=null && category!="cart"){
          
       

			printWriter.print(contentToString("content.html",category));
		}
		else if(fileName.equals("cart")){

			printWriter.print(cartlogic(category));

		}
		else{

			printWriter.print(htmlToString(htmlFilePath));
		}
		// String cont;
		
		// if(fileName=="Header.html"){
		// 	//result="<html><head><title>SmartPortables</title></head></html>";
		// }
		// if(fileName=="LeftNavigationBar.html"){

		// }
	
	}


	private String htmlToString(String fileName) {
		StringBuilder htmlContentBuilder = new StringBuilder();

		
			try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String str;
			while ((str = in.readLine()) != null) {
				htmlContentBuilder.append(str);
			}
			in.close();
			} catch (IOException e) {
			}
		
		
		return htmlContentBuilder.toString();
	}


	private String contentToString(String filename,String category){

		 StringBuilder cont = new StringBuilder();
       
       try{
            
            
        	
        	 String message="";
             int i =0;
             String path="";
           
 			
 			  ResultSet rs= OracleDB.getproducts(category);
		             
             // cont.append("<h3>"+imgpath+"</h3>");
             cont.append("<div id=\"content\">");

              // for(Integer key : genericmap.keySet()){
              //   System.out.println("Inside sax  parser");
              //   message += key+"  "+"  "+genericmap.get(key).getImg()+"  "+genericmap.get(key).getName()+"  " +genericmap.get(key).getCondition()+"  "+"  "+genericmap.get(key).getPrice()+"<br>";
              while(rs.next()) { 

              	// System.out.println("Inside contentToString method");
                 if((i==0) || (i%3 == 0)){
                 cont.append("<div class=\"row \">\n");
                 }
                 path = imgpath+rs.getString("imgpath");

		           cont.append("<div class=\"col-md-4 col-sm-4 col-xs-4 col-lg-4\">\n"
		          +"<div class=\"thumbnail bg-primary\" >\n"
		              
                        // style=\"text-align: center\"
		           // class=\"caption\"
		              +"<img src=\""+path+"\" alt=\"Image\" >\n"
		              +"<div  style=\"text-align: center\">\n"
		                  +"<b class=\"text-primary\" style=\"font-size: 22px;margin-left: 25px\">"+rs.getString("name")+"</b><br>\n"
		                  +"<ul>"
		                   //+"<li><a type=\"button\" class=\"btn btn-primary\" href=\"#\">$"+ rs.getInt("price")+"</a></li>\n"
		                   //+" <li><a type=\"button\" class=\"btn btn-primary\" href=\"cartcontroller?item_id="+rs.getInt("id")+"&category="+cat+"&action=add&quantity=1\">Add to cart</a></li>\n"
		                   //+"<li><a type=\"button\" class=\"btn btn-primary\" href=\"#\">Review</a></li>\n"
		                   +"<li><a type=\"button\" class=\"btn btn-default disabled\" style=\"margin-bottom:5px;\" href=\"#\">$"+ rs.getInt("price")+"</a><br></li>\n"
		                  +" <li><a type=\"button\" class=\"btn btn-primary\" style=\"margin-left: 5px\" href=\"cartcontroller?item_id="+rs.getInt("id")+"&action=add&quantity=1\">Buy Now</a></li>\n"
		                  +"<li><a type=\"button\" class=\"btn btn-primary\" href=\"reviewform?action=read&name="+rs.getString("name")+"\">Reviews</a></li>\n"
		                  +"<li><a type=\"button\" class=\"btn btn-primary\" href=\"reviewform?productid="+rs.getInt("id")+"&name="+rs.getString("name")+"&category="+rs.getString("category")+"&price="+rs.getString("price")+"&action=write\">Write Review</a></li>\n"
		                  +"</ul>"
		              +"</div>\n"
		        
		          +"</div>\n"
		        +"</div>");
		           i++;
             	   if((i%3 == 0)){
                   cont.append("</div>");
            
              	  }
              

              }

               cont.append("</div>");


           }
           catch(Exception e){
           	System.out.println("In uitilitites Exception " + e.getMessage());
           }

           return cont.toString();
		// if(filename.equals("content.html")){
		
           
		// }

		
	}


	private String cartlogic(String message){

		 StringBuilder conte = new StringBuilder();
		 conte.append(message);

		 return conte.toString();
         
	}

}