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
    
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h1>Bienvenue sur le portail des nouvelles</h1>

                <h2>Ajouter des Nouvelles</h2>
                <form action="NewProfile" method="post">
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
                     <button type="submit" class="btn btn-warning">Supprimer</button>
                      <button type="submit" class="btn btn-success">Modifier</button>
                </form>
            </div>
            <div class="col-md-6">
                <h2>Dernières nouvelles</h2>
                <h1>Portail de nouvelles</h1>

                <ul class="list-unstyled">
                    <c:forEach items="${newsList}" var="news">
                        <li class="mb-4">
                            <h2>${news.title}</h2>
                            <p>Auteur: ${news.author}</p>
                            <p>Date d'Ajout: ${news.dateAdded}</p>
                            <c:choose>
                                <c:when test="${not empty news.url}">
                                    <p>URL: <a href="${news.url}" target="_blank">${news.url}</a></p>
                                </c:when>
                                <c:otherwise>
                                    <p>Aucune URL disponible</p>
                                </c:otherwise>
                            </c:choose>
                            <p id="likes-${news.id}" class="mb-1">${news.likes} Likes</p>
                            <p id="dislikes-${news.id}" class="mb-1">${news.dislikes} Dislikes</p>

                            <form class="like-form" action="NewProfile" method="post">
                                <input type="hidden" name="newsId" value="${news.id}">
                                <input type="hidden" name="action" value="Like">
                                <button type="submit" class="btn btn-primary btn-sm">Like</button>
                            </form>
                            
                            <form class="dislike-form" action="NewProfile" method="post">
                                <input type="hidden" name="newsId" value="${news.id}">
                                <input type="hidden" name="action" value="Dislike">
                                <button type="submit" class="btn btn-danger btn-sm">Dislike</button>
                            </form>
                            <form class="delete-form" action="NewProfile" method="post">
                                <input type="hidden" name="newsId" value="${news.id}">
                                <input type="hidden" name="action" value="Supprimer">
                                <button type="submit" class="btn btn-secondary btn-sm">Supprimer</button>
                            </form>
                             <br>
                            <form class="comment-form" action="NewProfile" method="post">
                                <input type="hidden" name="newsId" value="${news.id}">
                                <textarea id="comment" name="comment" rows="1" class="form-control mb-2"></textarea>
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

                <a href="NewProfile" class="btn btn-primary">Ajouter des Nouvelles</a>
                <div class="text-center">
 					<a href="Servlet_Profil">Mon Profil</a>
				</div>
            </div>
        </div>
    </div>
</body>
</html>
