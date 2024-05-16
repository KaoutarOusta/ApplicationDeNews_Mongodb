package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import model.User;

public class UserDao {
    private MongoCollection<Document> userCollection;

    public UserDao(MongoDatabase database) {
        userCollection = database.getCollection("users");
    }

    public void addUser(User user) {
        Document userDoc = new Document("login", user.getLogin())
                .append("firstName", user.getFirstName())
                .append("lastName", user.getLastName());

        userCollection.insertOne(userDoc);
    }
    
    

	public User findUserByLogin(String login) {
        Document query = new Document("login", login);
        Document result = userCollection.find(query).first();
        return documentToUser(result);
    }

    private User documentToUser(Document doc) {
        User user = new User();
        user.setLogin(doc.getString("login"));
        user.setFirstName(doc.getString("firstName"));
        user.setLastName(doc.getString("lastName"));
        return user;
    }
}
