import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AboutUs extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		// HttpSession sc = request.getSession();
	    // System.out.println((String)sc.getAttribute("username"));
	 //    String username = (String)sc.getAttribute("username");
		// if(username==null )
  //      {
		// response.sendRedirect("loginpage.html");
		// }
		// else{

			Utilities utility = new Utilities(printWriter,request);
        utility.printHtml("header.html","");
        utility.printHtml("sidebar.html","");
        utility.printHtml("aboutusContent.html","");
        utility.printHtml("footer.html","");

   	 // }
	}

}