<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Comment</title>
</head>
<body>
    <h1>Add Comment</h1>
    
    <form action="comment" method="post">
        <input type="hidden" name="newsId" value="${newsId}">
        Comment: <input type="text" name="comment" required><br>
        <input type="submit" value="Add Comment">
    </form>
    
    <br>
    
    <a href="index.jsp">Back to Home</a>
</body>
</html>
