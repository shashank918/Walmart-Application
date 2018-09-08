import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.*;
import java.util.*;

public class updateProductsDB extends HttpServlet implements Serializable{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		try
		{
		String id = request.getParameter("prodid");
		String qtyup = request.getParameter("qtyup");
		OracleDB odb = new OracleDB();


		boolean check = odb.checkCapacity(qtyup);
		if(check==false)
		{
			
		HttpSession ses = request.getSession();
		String seqVal;
		seqVal = (String)ses.getAttribute("seqValue");
		if(seqVal == null){
		  int newSeq = odb.getPurchaseSeqValue();
          ses.setAttribute("seqValue", String.valueOf(newSeq));
		  seqVal = (String)ses.getAttribute("seqValue");
		  odb.updatePurchaseHeader(Integer.parseInt(id),Integer.parseInt(seqVal));
        }
		odb.updateProductInventory(id,qtyup);
		System.out.println("Sending id in updateProductsDB :"+Integer.parseInt(id));
		odb.updatePurchaseLines(Integer.parseInt(id),Integer.parseInt(qtyup),Integer.parseInt(seqVal));
		response.sendRedirect("updateProducts");
        }
        else
        {
     		StringBuilder s = new StringBuilder();	
     		s.append("<h2> Cannot add more products, Store capacity already full</h2>\n"
        +"<br>");
        Utilities utility = new Utilities(printWriter,request);
		 utility.printHtml("header.html","");
        utility.printHtml("sidebar.html","");
        utility.printHtml("cart",s.toString());
        utility.printHtml("footer.html","");
        }

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}