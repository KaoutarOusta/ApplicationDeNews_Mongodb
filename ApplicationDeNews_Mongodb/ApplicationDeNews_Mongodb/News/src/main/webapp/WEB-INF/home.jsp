<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
    <h1>Welcome to the News Portal</h1>
    
    <!-- Display news articles -->
    <c:forEach items="${newsList}" var="news">
        <div>
            <h2>${news.title}</h2>
            <p>Author: ${news.author}</p>
            <p>${news.dateAdded}</p>
            <p>${news.likes} Likes | ${news.dislikes} Dislikes</p>
            <p>Comments:</p>
            <ul>
                <c:forEach items="${news.comments}" var="comment">
                    <li>${comment}</li>
                </c:forEach>
            </ul>
            
            <form action="LikeDislikeServlet" method="post">
                <input type="hidden" name="newsId" value="${news.id}">
                <button type="submit" name="action" value="like">Like</button>
                <button type="submit" name="action" value="dislike">Dislike</button>
                <button type="submit" name="action" value="delete">Supprimer</button>
                
            </form>
            
            <form action="CommentServlet" method="post">
                <input type="hidden" name="newsId" value="${news.id}">
                <input type="text" name="commentText" placeholder="Add a comment">
                <button type="submit">Comment</button>
            </form>
        </div>
    </c:forEach>
</body>
</html>
