<%-- 
    Document   : DanhSachDiem
    Created on : Aug 12, 2024, 11:35:04 AM
    Author     : HP
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Danh Sách Điểm Môn Học</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                text-align: center;
                padding: 8px;
            }
            th {
                background-color: #f2f2f2;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            h1 {
                text-align: center;
            }
            .btn-group {
                margin-bottom: 20px;
                width: 100%;
            }
            .btn-group .btn {
                margin-right: 20px;
                margin-left: 20px;
            }
            .form-control-plaintext {
                width: 100px;
                margin: 0 auto;
            }

        </style>

    </head>
    <body>
        <!-- Navbar Bootstrap -->
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
                </ul>
            </div>
        </nav>

        <div class="container mt-4">
            <h1 class="mb-4">DANH SÁCH ĐIỂM MÔN HỌC</h1>

            <!-- Form to select subject and class -->
            <form action="${pageContext.request.contextPath}/diem" method="get" class="mb-4">
                <div class="form-group">
                    <label for="monHocId"><strong>Môn học</strong></label>
                    <select id="monHocId" name="monHocId" class="form-control" onchange="this.form.submit()">
                        <c:forEach items="${monHocList}" var="monHoc">
                            <option value="${monHoc.id}" ${monHoc.id == selectedMonHocId ? 'selected' : ''}>
                                ${monHoc.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="lopHocId"><strong>Lớp học</strong></label>
                    <select id="lopHocId" name="lopHocId" class="form-control" onchange="this.form.submit()">
                        <c:forEach items="${lopHocList}" var="lopHoc">
                            <option value="${lopHoc.id}" ${lopHoc.id == selectedLopHocId ? 'selected' : ''}>
                                ${lopHoc.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </form>

            <!-- Table for Midterm Scores -->
            <h2 class="mb-4">ĐIỂM GIỮA KỲ</h2>
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Sinh viên</th>
                        <th>Điểm</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${diemList}" var="diem">
                        <c:choose>
                            <c:when test="${diem.loaiDiem.name == 'Diem giua ky'}">
                                <tr>
                                    <td>${diem.sinhVien.name}</td>
                                    <td>${diem.score}</td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/diem/delete" method="post">
                                            <input type="hidden" name="id" value="${diem.id}" />
                                            <button type="submit" class="btn btn-danger">Xóa</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Table for Final Scores -->
            <h2 class="mb-4">ĐIỂM CUỐI KỲ</h2>
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Sinh viên</th>
                        <th>Điểm</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${diemList}" var="diem">
                        <c:choose>
                            <c:when test="${diem.loaiDiem.name == 'Diem cuoi ky'}">
                                <tr>
                                    <td>${diem.sinhVien.name}</td>
                                    <td>${diem.score}</td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/diem/delete" method="post">
                                            <input type="hidden" name="id" value="${diem.id}" />
                                            <button type="submit" class="btn btn-danger">Xóa</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </tbody>
            </table>

            <div class="mb-4">
                <a href="<c:url value='/DanhSachDTB'/>">Xem điểm trung bình</a>
            </div>
            <!-- Navigation buttons -->
            <div class="btn-group" role="group">
                <a href="<c:url value='/'/>" class="btn btn-success">Trở về trang chủ</a>
                <a href="<c:url value='/diem/form'/>" class="btn btn-warning">Nhập điểm</a>
                <a href="<c:url value='/dtb'/>" class="btn btn-warning">DTB</a>
                <!-- Export Button -->
                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#exportModal">
                    Xuất bảng điểm
                </button>
            </div>

            <div class="modal fade" id="exportModal" tabindex="-1" role="dialog" aria-labelledby="exportModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exportModalLabel">Chọn định dạng xuất</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <a href="#" class="btn btn-info" onclick="xuatCSV()">Xuất CSV</a>
                            <a href="#" class="btn btn-danger" onclick="xuatPDF()">Xuất PDF</a>
                        </div>
                    </div>
                </div>
            </div>

            <script>
                function xuatCSV() {
                     const monHocId = '${selectedMonHocId}';
                     const lopHocId = '${selectedLopHocId}';
                     const url = `${pageContext.request.contextPath}/diem/export/csv?monHocId=${selectedMonHocId}&lopHocId=${selectedLopHocId}`;
                            window.location.href = url;
                        }

                        function xuatPDF() {
                            const monHocId = '${selectedMonHocId}';
                            const lopHocId = '${selectedLopHocId}';
                            const url = `${pageContext.request.contextPath}/diem/export/pdf?monHocId=${selectedMonHocId}&lopHocId=${selectedLopHocId}`;
                                    window.location.href = url;
                                }
            </script>

            <!-- Bootstrap JS and dependencies -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        </div>
    </body>
</html>