package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MongoDBConnection;
import dao.NewsDao;
import model.News;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@WebServlet("/Servlet_Profil")
public class Servlet_Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private NewsDao newsDao;

    @Override
    public void init() throws ServletException {
        // Initialize the NewsDao with the MongoDB database connection
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        newsDao = new NewsDao(mongoDBConnection.getDatabase());
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
      /*  List<News> newsList = newsDao.getAllNews();
        request.setAttribute("newsList", newsList);
        request.getRequestDispatcher("/WEB-INF/Profil.jsp").forward(request, response);*/
	     response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();

	        // Récupérer le nom de l'auteur à partir des paramètres de la requête
	        String auteur = request.getParameter("auteur");

	        try {
	            // Rechercher et afficher les news de l'auteur
	          List<News> newsDeLAuteur = newsDao.rechercherNewsParAuteur(auteur);

	            // Afficher les résultats
	            out.println("<h1>News de l'auteur : " + auteur + "</h1>");
	            for (News news : newsDeLAuteur) {
	                out.println("<p>Titre : " + news.getTitle() + "</p>");
	                // Afficher d'autres détails de la news si nécessaire
	            }
	        } catch (Exception e) {
	            // Gérer les erreurs, par exemple, afficher un message d'erreur
	            out.println("Une erreur s'est produite lors de la recherche des news de l'auteur : " + e.getMessage());
	        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Handle different actions: Like/Dislike or Add News
        String action = request.getParameter("action");

        if ("Supprimer".equals(action))
        {
        	handleDeleteNews(request);
        }
        else
        if ("Like".equals(action) || "Dislike".equals(action)) {
            handleLikeDislike(request);
        } else if ("AddComment".equals(action)) {
            handleAddComment(request);
        }
        
        else {
            handleAddNews(request);
        }

        // Redirect back to the news list after processing the request
        response.sendRedirect(request.getContextPath() + "/NewProfile");
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
    
    private void handleDeleteNews(HttpServletRequest request) throws IOException {
    	ServletResponse response=null;
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    String newsId = request.getParameter("newsId");
    try {
        // Supprimer la news MongoDB
    	newsDao.deleteNews(newsId);

        out.println("News supprimée avec succès !");
    } catch (Exception e) {
        // Gérer les erreurs, par exemple, afficher un message d'erreur
        out.println("Une erreur s'est produite lors de la suppression de la news : " + e.getMessage());
    }
    }
   
    
    private void handleModifierNews(HttpServletRequest request) {
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
	

	
	
	
		

	    
	    

	   
}
