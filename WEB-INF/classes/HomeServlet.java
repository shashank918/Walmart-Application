import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HomeServlet extends HttpServlet{

	 public void init(){

	 

    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
        String req_category="";

        if(request.getParameter("category")==null){
        	req_category = "Watches";
            System.out.println("Inside if"+req_category);
        }
        else{
               req_category = request.getParameter("category");
               System.out.println("Inside else"+req_category);
        }

		Utilities utility = new Utilities(printWriter,request);
        utility.printHtml("header.html"," ");
        utility.printHtml("sidebar.html"," ");
        // utility.printHtml("customerRegistration.html"," ");
        utility.printHtml("content.html",req_category);
        utility.printHtml("content.html","");

		utility.printHtml("footer.html"," "); 
         
  //    String s = "false";
		//  loginChec lo = new loginChec();
	 //  	s = lo.flag;

		// if(s == "true" || s=="false"){
		// 	utility.printHtml("trail1.html");
		// }
//		utility.printHtml("LeftNavigationBar.html"); 
//		utility.printHtml("Content.html"); 
//		utility.printHtml("Footer.html"); 
	}

}