package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class News {
    private String id;
    private String url;
    private String author;
    private Date dateAdded;
    private String title;
    private int likes;
    private int dislikes;
    private List<String> comments;

    public News() {
        // Initialize comments as an empty list
        comments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", author='" + author + '\'' +
                ", dateAdded=" + dateAdded +
                ", title='" + title + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", comments=" + comments +
                '}';
    }
}
