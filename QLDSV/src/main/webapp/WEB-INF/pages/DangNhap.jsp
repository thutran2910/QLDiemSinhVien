<%-- 
    Document   : DangNhap
    Created on : Aug 15, 2024, 10:22:49 PM
    Author     : HP
--%>

<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><c:out value="${title}" /></title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="text-center my-4"><c:out value="${heading}" /></h1>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="username"><c:out value="${usernameLabel}" /></label>
                <input type="text" id="username" name="username" class="form-control" required />
            </div>
            <div class="form-group">
                <label for="password"><c:out value="${passwordLabel}" /></label>
                <input type="password" id="password" name="password" class="form-control" required />
            </div>
            <button type="submit" class="btn btn-primary"><c:out value="${loginButtonText}" /></button>
            <a href="${pageContext.request.contextPath}/dangky" class="btn btn-secondary ml-2"><c:out value="${registerButtonText}" /></a>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
