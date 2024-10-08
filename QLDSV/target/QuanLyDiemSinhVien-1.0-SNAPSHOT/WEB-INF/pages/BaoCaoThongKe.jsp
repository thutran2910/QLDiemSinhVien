<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Báo cáo thống kê</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .chart-container {
              margin: 20px 40px;
            }

            h2 {
                text-align: center;
            }

            .small-table {
                width: 50%;
                margin: 0 auto;
                text-align: center;
                border-collapse: collapse;
            }
             .small-table th, .small-table td {
                border: 1px solid #dee2e6; 
                padding: 8px; 
            }

            .small-table thead th {
                background-color: #f8f9fa; 
            }

            .small-table tbody tr:nth-child(odd) {
                background-color: #f2f2f2;
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
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/'/>">Trang chủ</a>
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

        <div class="container mt-4">
            <h2 class="mb-4">BÁO CÁO THỐNG KÊ</h2>

            <form action="${pageContext.request.contextPath}/bctk" method="get" class="mb-4">
                <div class="form-group">
                    <label for="monHocId"><strong>Chọn môn học: </strong></label>
                    <select name="monHocId" id="monHocId" class="form-control" onchange="this.form.submit()">
                        <option value=""> --- Môn học ---</option>
                        <c:forEach var="monHoc" items="${monHocs}">
                            <option value="${monHoc.id}" <c:if test="${monHoc.id == selectedMonHocId}">selected</c:if>>
                                ${monHoc.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </form>

            <!-- Table to display detailed report -->
            <c:if test="${not empty lopHocScores}">
                <table class="table table-striped small-table mt-2">
                    <thead>
                        <tr>
                            <th>Lớp học</th>
                            <th>Điểm trung bình</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="entry" items="${lopHocScores}">
                            <tr>
                                <td>${entry.key}</td>
                                <td>${entry.value}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${empty lopHocScores}">
                <p>Không có dữ liệu để hiển thị.</p>
            </c:if>

            <c:if test="${not empty lopHocScores}">
                <div class="chart-container">
                    <canvas id="myChart" width="300" height="150"></canvas>
                </div>
                <script>
                    var ctx = document.getElementById('myChart').getContext('2d');                
                    var colors = [];
                    var dataCount = ${lopHocScores.size()};
                    for (var i = 0; i < dataCount; i++) {
                    colors.push(i % 2 === 0 ? 'rgba(255, 240, 245, 0.5)' : 'rgba(191, 239, 255, 0.5)');
                    }

                    var borderColors = [];
                    for (var i = 0; i < dataCount; i++) {
                    borderColors.push(i % 2 === 0 ? 'rgba(240, 128, 128, 1)' : 'rgba(99, 184, 255, 1)');
                    }

                    var myChart = new Chart(ctx, {
                    type: 'bar',
                            data: {
                            labels: [
                    <c:forEach var="entry" items="${lopHocScores}" varStatus="entryStatus">
                            "${entry.key}"<c:if test="${not entryStatus.last}">,</c:if>
                    </c:forEach>
                            ],
                                    datasets: [{
                                    label: 'Điểm trung bình cao nhất',
                                            data: [
                    <c:forEach var="entry" items="${lopHocScores}" varStatus="entryStatus">
                        ${entry.value}<c:if test="${not entryStatus.last}">,</c:if>
                    </c:forEach>
                                            ],
                                            backgroundColor: colors,
                                            borderColor: borderColors,
                                            borderWidth: 1
                                    }]
                            },
                            options: {
                            scales: {
                            y: {
                            beginAtZero: true
                            }
                            }
                            }
                    });
                </script>
            </c:if>

        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
