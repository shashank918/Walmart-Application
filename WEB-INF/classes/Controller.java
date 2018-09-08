import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Controller extends HttpServlet{

	 public void init(){

	 

    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		String s = request.getParameter("redirect");

		if(s.equals("registration"))
		{
		Utilities utility = new Utilities(printWriter,request);
        utility.printHtml("header.html"," ");
        utility.printHtml("sidebar.html"," ");
        utility.printHtml("customerRegistration.html"," ");
        utility.printHtml("content.html","");
    }
		// response.sendRedirect("HomeServlet");
	}
}