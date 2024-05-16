package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.util.List;
import model.News;
import dao.MongoDBConnection;
import dao.NewsDao;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        MongoDatabase database = mongoDBConnection.getDatabase();

        NewsDao newsDao = new NewsDao(database);
        
        List<News> newsList = newsDao.getAllNews();
        
        request.setAttribute("newsList", newsList);
        
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request,response);
        
        
        // Ne pas oublier de fermer la connexion lorsque vous avez fini d'utiliser la base de données
        mongoDBConnection.closeConnection();
    }
}
