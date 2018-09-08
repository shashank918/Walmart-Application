import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;
public class loginservlet extends HttpServlet{
    
    
    public void init(){
       
    }

    
     protected void showPage(HttpServletResponse response,String msg)throws ServletException, IOException 
     {
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World whatsupp!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>"+msg+"</h1>");
        out.println("</body>");
        out.println("</html>");
         
     }
     public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

       response.setContentType("text/html");
       PrintWriter printWriter = response.getWriter();
      HttpSession sc = request.getSession(false);
        // System.out.println((String)sc.getAttribute("username"));
        if(sc==null )
       {
        response.sendRedirect("loginpage.html");
        }
        else{
            response.sendRedirect("logout.html");
        }
     
    }
}

