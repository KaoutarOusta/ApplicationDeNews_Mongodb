package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import model.News;
import java.util.ArrayList;
import java.util.List;

public class NewsDao {
    private MongoCollection<Document> newsCollection;

    public NewsDao(MongoDatabase database) {
        newsCollection = database.getCollection("news");
    }

    //ajouter
    public void addNews(News news) {
        Document newsDoc = new Document("url", news.getUrl())
                .append("author", news.getAuthor())
                .append("dateAdded", news.getDateAdded())
                .append("title", news.getTitle())
                .append("likes", news.getLikes())
                .append("dislikes", news.getDislikes())
                .append("comments", news.getComments());

        newsCollection.insertOne(newsDoc);
    }

    // liste
    public List<News> getAllNews() {
        List<News> newsList = new ArrayList<>();
        for (Document doc : newsCollection.find()) {
            News news = documentToNews(doc);
            newsList.add(news);
        }
        return newsList;
    }

    public News findNewsById(String newsId) {
        Document query = new Document("_id", new ObjectId(newsId));
        Document result = newsCollection.find(query).first();
        return documentToNews(result);
    }

    
    public void updateNews(News news) {
        Document query = new Document("_id", new ObjectId(news.getId()));
        
        Document update = new Document();
        update.append("$set", new Document()
                .append("likes", news.getLikes())
                .append("dislikes", news.getDislikes())
                .append("comments", news.getComments()));
        
        newsCollection.updateOne(query, update);
    }

    // Supprimer
    public void deleteNews(String newsId) {
       /* Document query = new Document("_id", new ObjectId(newsId));
        newsCollection.deleteOne(query);*/
    	try {
            // Convertir l'ID de news en ObjectId
            ObjectId objectId = new ObjectId(newsId);

            // Créer un filtre pour rechercher la news par son ID
            Document filter = new Document("_id", objectId);

            // Supprimer le document correspondant dans la collection
            newsCollection.deleteOne(filter);
        } catch (Exception e) {
            // Gérer les erreurs, par exemple, en lançant une exception personnalisée
            throw new RuntimeException("Erreur lors de la suppression de la news : " + e.getMessage());
        }
    
    }
    

    private News documentToNews(Document doc) {
        News news = new News();
        news.setId(doc.getObjectId("_id").toString());
        news.setUrl(doc.getString("url"));
        news.setAuthor(doc.getString("author"));
        news.setDateAdded(doc.getDate("dateAdded"));
        news.setTitle(doc.getString("title"));
        news.setLikes(doc.getInteger("likes"));
        news.setDislikes(doc.getInteger("dislikes"));
        news.setComments((List<String>) doc.get("comments"));
        return news;
    }
    

    public List<News> rechercherNewsParAuteur(String auteur) {
        List<News> newsDeLAuteur = new ArrayList<>();

        try {
            Document query = new Document("auteur", auteur);
            FindIterable<Document> result = newsCollection.find(query);

            for (Document doc : result) {

                News news = documentToNews(doc);
                newsDeLAuteur.add(news);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche des news de l'auteur : " + e.getMessage());
        }

        return newsDeLAuteur;
    }
    
 // Méthode pour modifier une news
 private void modifierNews(String newsId,String nouveauURL, String nouveauTitre,String nouveauAuteur, String nouvelleDateAjout) {
     try {
         // Convertir l'ID de news en ObjectId
         ObjectId objectId = new ObjectId(newsId);

         // Créer un filtre pour rechercher la news par son ID
         Document filter = new Document("_id", objectId);

         // Créer un document avec les nouvelles valeurs
         Document update = new Document("$set", new Document("title", nouveauTitre)
                 
                 .append("author", nouveauAuteur)
                 .append("dateAdded", nouvelleDateAjout)
                 .append("url", nouveauURL));

         // Effectuer la mise à jour dans la collection
         newsCollection.updateOne(filter, update);

     } catch (Exception e) {
         // Gérer les erreurs, par exemple, en affichant un message d'erreur
         e.printStackTrace();
     }
 }
    

}
