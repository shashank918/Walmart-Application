import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.*;
import java.util.*;

public class logincheck extends HttpServlet implements Serializable{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        if(role.equals("customer"))
        {
         int userExists = OracleDB.customerExists(username);        
         if(userExists == 1)
            {
              HashMap<String,String> map = OracleDB.getCustomerLogin(username);
              if(password.equals(map.get(username)))
              {
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                session.setAttribute("salesOrderSeqValue",null);
                response.sendRedirect("HomeServlet");
              }
              else{
                response.sendRedirect("loginpage.html");
              }
            }
            else{
              response.sendRedirect("loginpage.html");
            }
        }
        else if(role.equals("employee"))
        {
         int userExists = OracleDB.empExists(username);     
         if(userExists == 1)
            {
              HashMap<String,String> map = OracleDB.getEmpLogin(username);
              if(password.equals(map.get(username)))
              {
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                session.setAttribute("seqValue",null);
             


                response.sendRedirect("updateProducts");
              }
              else{
                 response.sendRedirect("loginpage.html");
              }
            }
             else{
              response.sendRedirect("loginpage.html");
            }
        }
    }
}