package servlets;

import dao.MongoDBConnection;
import dao.NewsDao;
import model.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/newsinfo")
public class NewsInfoServlet extends HttpServlet {
    private NewsDao newsDao;

    @Override
    public void init() throws ServletException {
        // Initialize the NewsDao with the MongoDB connection
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        newsDao = new NewsDao(mongoDBConnection.getDatabase());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newsId = request.getParameter("newsId");

        News news = newsDao.findNewsById(newsId);

        if (news != null) {
            request.setAttribute("news", news);
            request.getRequestDispatcher("newsinfo.jsp").forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
