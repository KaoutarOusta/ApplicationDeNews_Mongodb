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

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
    private NewsDao newsDao;

    @Override
    public void init() throws ServletException {
        // Initialize the NewsDao with the MongoDB connection
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        newsDao = new NewsDao(mongoDBConnection.getDatabase());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newsId = request.getParameter("newsId");
        String comment = request.getParameter("comment");

        News news = newsDao.findNewsById(newsId);

        if (news != null) {
            List<String> comments = news.getComments();
            comments.add(comment);
            news.setComments(comments);

            // Update the news in the database
            newsDao.updateNews(news);
        }

        response.sendRedirect("news.jsp?newsId=" + newsId);
    }

    @Override
    public void destroy() {

    }
}
