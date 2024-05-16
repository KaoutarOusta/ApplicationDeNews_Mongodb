package dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static final String MONGODB_URI = "mongodb://localhost:27017"; // Replace with your MongoDB URI
    private static final String DATABASE_NAME = "NEWS"; // Replace with your database name

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBConnection() {
        MongoClientURI connectionString = new MongoClientURI(MONGODB_URI);
        mongoClient = new MongoClient(connectionString);
        database = mongoClient.getDatabase(DATABASE_NAME);
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void closeConnection() {
        mongoClient.close();
    }
}
