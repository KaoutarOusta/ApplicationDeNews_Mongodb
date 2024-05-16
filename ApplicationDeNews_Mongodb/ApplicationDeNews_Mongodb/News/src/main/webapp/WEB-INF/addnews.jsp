<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un Nouvel Article</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        
        h1 {
            color: #333;
        }
        
        form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        
        label {
            display: block;
            margin-top: 10px;
        }
        
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin: 6px 0;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        
        button {
            background-color: #3498db;
            color: #fff;
            border: none;
            padding: 10px 20px;
            margin-top: 20px;
            border-radius: 3px;
            cursor: pointer;
        }
        
        button:hover {
            background-color: #2a7da4;
        }
    </style>
</head>
<body>
    <h1>Ajouter un Nouvel Article</h1>
    <form action="AddNewsServlet" method="post">
        <label for="title">Titre:</label>
        <input type="text" name="title" required>
        
        <label for="author">Auteur:</label>
        <input type="text" name="author" required>
        
        <label for="url">URL:</label>
        <input type="text" name="url" required>
        
        <button type="submit">Ajouter</button>
    </form>
</body>
</html>

