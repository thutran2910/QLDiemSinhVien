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
    <title>Đăng Nhập</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Quản Lý Điểm Sinh Viên</a>
    </nav>
    <div class="container">
        <h1 class="text-center my-4">ĐĂNG NHẬP</h1>
        <div class="row justify-content-center">
            <div class="col-md-6">
                <form action="<c:url value='/login'/>" method="post">
                    <div class="form-group">
                        <label for="username">Tên người dùng</label>
                        <input type="text" class="form-control" id="username" name="username" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Mật khẩu</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Đăng nhập</button>
                    <c:if test="${param.error}">
                        <div class="alert alert-danger mt-2">Tên người dùng hoặc mật khẩu không đúng.</div>
                    </c:if>
                </form>
                <p class="mt-3">Bạn chưa có tài khoản? <a href="<c:url value='/register'/>">Đăng ký tại đây</a></p>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
