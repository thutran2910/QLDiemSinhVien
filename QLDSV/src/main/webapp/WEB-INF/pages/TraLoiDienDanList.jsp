<%-- 
    Document   : TraLoiDienDanList
    Created on : Aug 26, 2024, 9:43:58 AM
    Author     : HP
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh Sách Câu Trả Lời</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .navbar {
            margin-bottom: 20px;
        }
        .card {
            margin-bottom: 20px;
        }
        .card-header {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }
        .card-body {
            background-color: #ffffff;
        }
        .form-control {
            border-radius: 0;
        }
        .btn-primary {
            border-radius: 0;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .btn-secondary {
            border-radius: 0;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Quản Lý Điểm Sinh Viên</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value='/'/>">Trang chủ <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/dssv'/>">Sinh viên</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/dslop'/>">Lớp</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/diem'/>">Điểm</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/bctk'/>">Báo cáo thống kê</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/logout'/>">Đăng xuất</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container mt-5">
        <h1>Câu Trả Lời Diễn Đàn</h1>
        <div class="row">
            <c:forEach var="traLoi" items="${traLoiDienDans}">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            Sinh Viên ID: ${traLoi.sinhVien.id}
                        </div>
                        <div class="card-body">
                            <p class="card-text">${traLoi.content}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        
        <div class="mt-4">
            <h4>Gửi Trả Lời</h4>
            <form action="<c:url value='/diendan/${dienDanId}/tra-loi'/>" method="post">
                <div class="form-group">
                    <label for="content">Nội dung trả lời</label>
                    <textarea class="form-control" id="content" name="content" rows="4" required></textarea>
                </div>
                <input type="hidden" name="sinhVienId" value="${sessionScope.sinhVienId}"/>
                <button type="submit" class="btn btn-primary">Gửi Trả Lời</button>
            </form>
        </div>
        
        <div class="mt-3">
            <a href="<c:url value='/diendan/${dienDanId}'/>" class="btn btn-secondary">Trở Về</a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
