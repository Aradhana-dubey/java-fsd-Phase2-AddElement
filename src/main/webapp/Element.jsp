<!-- Element.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
    <h2>Add Product</h2>
    <form action="AddElementServlet" method="post">
        Name: <input type="text" name="productName" required><br>
        Price: <input type="text" name="productPrice" required><br>
        <input type="submit" value="Add Product">
    </form>
</body>
</html>
