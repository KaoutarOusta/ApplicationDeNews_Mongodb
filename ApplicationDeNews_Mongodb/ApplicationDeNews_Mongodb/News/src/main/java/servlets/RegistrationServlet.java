package servlets;

import dao.MongoDBConnection;
import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        // Initialize the UserDao with the MongoDB connection
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        userDao = new UserDao(mongoDBConnection.getDatabase());
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);

		
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // Create a User object
        User user = new User();
        user.setLogin(login);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        // Add the user to the database
        userDao.addUser(user);

        response.sendRedirect("addnews");
    }

    @Override
    public void destroy() {

    }
}
