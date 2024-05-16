<!DOCTYPE html>
<html>
<head>
    <title>Add News</title>
</head>
<body>
    <h1>Ajouter un Nouvel Article</h1>
    <form action="Servlet_Profil" method="post">
        <label for="title">Titre:</label>
        <input type="text" name="title" required><br>
        
        <label for="author">Auteur:</label>
        <input type="text" name="author" required><br>
        
        <label for="url">URL:</label>
        <input type="text" name="url" required><br>
        
        <button type="submit">Ajouter</button>
    </form>
</body>
</html>
