<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>News Portal - Add News</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            // Votre code JavaScript ici
        });
    </script>
    <!-- Ajouter les liens vers les fichiers Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    
</head>
<body >
    <div class="container">
        <div class="row">
            <div class="col-8">

                <h2>Publier des Nouvelles</h2>
                <form action="addnews" method="post">
                    <div class="mb-3">
                        <label for="title" class="form-label">Titre:</label>
                        <input type="text" class="form-control" id="title" name="title" required >
                    </div>
                    <div class="mb-3">
                        <label for="url" class="form-label">URL:</label>
                        <input type="text" class="form-control" id="url" name="url" required>
                    </div>
                    <div class="mb-3">
                        <label for="author" class="form-label">Auteur:</label>
                        <input type="text" class="form-control" id="author" name="author" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                    <br><br>
				
                </form>
            </div>
            <div class="col-4">
                <h2>News Récents</h2>

<ul class="list-unstyled">
    <c:forEach items="${newsList}" var="news">
        <li class="mb-4" style="border: 1px solid #ddd; padding: 10px;">
            <h2 class="text-center text-black font-weight-bold">${news.title}</h2>
            <p><strong>Auteur:</strong> ${news.author}</p>
            <p><strong>Date d'Ajout:</strong> ${news.dateAdded}</p>
            <c:choose>
                <c:when test="${not empty news.url}">
                    <p><strong>URL:</strong> <a href="${news.url}" target="_blank">${news.url}</a></p>
                </c:when>
                <c:otherwise>
                    <p><strong>Aucune URL disponible</strong></p>
                </c:otherwise>
            </c:choose>
            
            

<form class="like-dislike-form" action="addnews" method="post">
    <input type="hidden" name="newsId" value="${news.id}">
    <input type="hidden" name="action" value="Like">
    <button type="submit" class="btn btn-primary btn-sm">
        <i class="far fa-thumbs-up"></i>
        ${news.likes} Likes
    </button>
    
    <input type="hidden" name="newsId" value="${news.id}">
    <input type="hidden" name="action" value="Dislike">
    <button type="submit" class="btn btn-danger btn-sm">
        <i class="far fa-thumbs-down"></i>
        ${news.dislikes} Dislikes
    </button>
</form>


            <form class="comment-form" action="addnews" method="post">
                <input type="hidden" name="newsId" value="${news.id}">
                <textarea id="comment" name="comment" rows="1" class="form-control mb-2" placeholder="Ajouter un commentaire"></textarea>
                <input type="hidden" name="action" value="AddComment">
                <button type="submit" class="btn btn-secondary btn-sm">Ajouter Commentaire</button>
            </form>

            <ul class="list-unstyled mt-2">
                <c:forEach items="${news.comments}" var="comment">
                    <li>${comment}</li>
                </c:forEach>
            </ul>
        </li>
    </c:forEach>
</ul>


                <br>

                <a href="addnews" class="btn btn-primary">Ajouter des Nouvelles</a>
            </div>
        </div>
    </div>
</body>
</html>
