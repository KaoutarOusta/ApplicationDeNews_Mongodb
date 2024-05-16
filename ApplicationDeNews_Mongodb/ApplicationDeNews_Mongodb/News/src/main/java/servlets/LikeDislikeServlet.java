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
import java.util.List;

@WebServlet("/likedislike")
public class LikeDislikeServlet extends HttpServlet {
    private NewsDao newsDao;

    @Override
    public void init() throws ServletException {
        // Initialize the NewsDao with the MongoDB connection
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        newsDao = new NewsDao(mongoDBConnection.getDatabase());
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newsId = request.getParameter("newsId");
        String action = request.getParameter("action"); // "like" or "dislike"

        News news = newsDao.findNewsById(newsId);

        if (news != null) {
            if ("like".equals(action)) {
                news.setLikes(news.getLikes() + 1);
            } else if ("dislike".equals(action)) {
                news.setDislikes(news.getDislikes() + 1);
            }

            // Update the news in the database
            newsDao.updateNews(news);
        }

        response.sendRedirect("index.jsp");
    }

    @Override
    public void destroy() {

    }
}
