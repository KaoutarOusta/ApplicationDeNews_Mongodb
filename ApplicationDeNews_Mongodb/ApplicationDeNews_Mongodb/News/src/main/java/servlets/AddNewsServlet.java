package servlets;

import dao.MongoDBConnection;
import dao.NewsDao;
import model.News;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/addnews")
public class AddNewsServlet extends HttpServlet {
    private NewsDao newsDao;

    @Override
    public void init() throws ServletException {
        // Initialize the NewsDao with the MongoDB database connection
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        newsDao = new NewsDao(mongoDBConnection.getDatabase());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all news and forward them to the index.jsp page
        List<News> newsList = newsDao.getAllNews();
        request.setAttribute("newsList", newsList);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle different actions: Like/Dislike or Add News
        String action = request.getParameter("action");
        
        
        if ("Like".equals(action) || "Dislike".equals(action)) {
            handleLikeDislike(request);
        } else if ("AddComment".equals(action)) {
            handleAddComment(request);
        }
        
        else {
            handleAddNews(request);
        }

        // Redirect back to the news list after processing the request
        response.sendRedirect(request.getContextPath() + "/addnews");
    }

    private void handleLikeDislike(HttpServletRequest request) {
        // Handle the Like/Dislike action and update news accordingly
        String newsId = request.getParameter("newsId");
        String action = request.getParameter("action");

        News news = newsDao.findNewsById(newsId);

        if (news != null) {
            if ("Like".equals(action)) {
                news.setLikes(news.getLikes() + 1);
            } else if ("Dislike".equals(action)) {
                news.setDislikes(news.getDislikes() + 1);
            }
            newsDao.updateNews(news);
        }
    }

    private void handleAddComment(HttpServletRequest request) {
        // Handle adding a new comment to a news item
        String newsId = request.getParameter("newsId");
        String commentText = request.getParameter("comment");

        News news = newsDao.findNewsById(newsId);

        if (news != null) {
            List<String> comments = news.getComments();
            comments.add(commentText);
            news.setComments(comments);
            newsDao.updateNews(news);
        }
    }

    private void handleAddNews(HttpServletRequest request) {
        // Handle adding a new news item
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        String author = request.getParameter("author");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateAdded = new Date();
        String dateAddedStr = sdf.format(dateAdded);

        News news = new News();
        news.setUrl(url);
        news.setAuthor(author);
        news.setDateAdded(dateAdded);
        news.setTitle(title);

        newsDao.addNews(news);
    }

    @Override
    public void destroy() {
        // Cleanup resources if needed
    }
   
    
    
    public static void calculerScoreFinal(MongoCollection<Document> collection) {
        String mapFunction = "function() {" +
                "var score = this.likes.length - this.dislikes.length;" +
                "emit(this._id, score);" +
                "}";

        String reduceFunction = "function(key, values) {" +
                "return Array.sum(values);" +
                "}";

        collection.mapReduce(mapFunction, reduceFunction)
                .collectionName("score_results")
                .toCollection();
    }
    
    
}
