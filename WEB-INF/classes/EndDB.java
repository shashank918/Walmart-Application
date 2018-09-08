import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.*;
import java.util.*;

public class EndDB extends HttpServlet implements Serializable{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		String id = request.getParameter("prodid");
		PrintWriter printWriter = response.getWriter();
		Utilities utility = new Utilities(printWriter,request);
		
		OracleDB odb = new OracleDB();
		HttpSession ses = request.getSession();
		ses.setAttribute("seqValue", null);
		
	    		
		 utility.printHtml("header.html","");
        utility.printHtml("sidebar.html","");
        utility.printHtml("cart","<br><h2> Updated </h2><br>");
        utility.printHtml("footer.html","");
	}
}