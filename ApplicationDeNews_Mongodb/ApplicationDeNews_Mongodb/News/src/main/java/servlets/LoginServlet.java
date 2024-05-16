package servlets;

import dao.MongoDBConnection;
import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        userDao = new UserDao(mongoDBConnection.getDatabase());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String firstName = request.getParameter("password"); // Assuming this is the user's input for password

        // Retrieve the user from the database based on login
        User user = userDao.findUserByLogin(login);

        if (user != null && user.getFirstName().equals(firstName)) {
            // Valid login, store user in session and redirect to addnews.jsp
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("addnews");
        } else {
            // Invalid login, set error message and forward to login.jsp
            request.setAttribute("error", "Invalid login");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        // Close resources if needed
    }
}
