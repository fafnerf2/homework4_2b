/**
 *
 * @author Betsey McCarthy and Colin Hiriak 2015
 */
        

 
package sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author emccarthy3
 */
@WebServlet("/BookServlet")
public class SqlGatewayServlet extends HttpServlet {

    /**
     * Gets parameters and sets attributes from HTTPServletRequest
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
      
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
         }

    
    
    protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {        
      String url = "index.jsp";        
      String action = request.getParameter("action");  
      
      //System.out.println(action);
      
      if(action.equals("goToCheckout")) {
        url = "checkout.jsp";
      }        
      else if(action != null && action.equals("goToBooks")) {            
        ArrayList<Book> books = BookDB.selectBooks();            
        request.setAttribute("books", books);            
        url = "manage.jsp";
      }        
      else if(action != null && action.equals("goToIndex")) {
        url = "index.jsp";
      }
      else if(action != null && action.equals("Checkout")) {
        url = "library.jsp";
      }
      
      if(action != null && action.equals("doCheckout")) {                
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String bookTitle = request.getParameter("bookTitle");
        //Date dueDate = request.getParameter("dueDate");
        Book book = new Book(firstName, lastName, email, bookTitle);
        BookDB.insert(book);
        
        getServletContext().getRequestDispatcher(url)
          .forward(request, response);
        }
    }
}