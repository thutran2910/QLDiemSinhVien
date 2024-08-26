<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách Diễn Đàn</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card {
            text-align: center;
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
        <h1>Danh Sách Diễn Đàn</h1>
        <div class="row">
            <c:forEach var="dienDan" items="${dienDans}">
                <div class="col-md-4 mb-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${dienDan.name}</h5>
                            <a href="<c:url value='/diendan/${dienDan.id}/tra-loi'/>" class="btn btn-info">Xem Câu Trả Lời</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <a href="<c:url value='/sinhvien/monhoc?monHocId=${monHocId}'/>" class="btn btn-primary">Trở Về</a>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
