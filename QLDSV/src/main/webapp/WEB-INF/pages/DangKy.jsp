<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Đăng ký tài khoản sinh viên</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="text-center my-4">Đăng ký tài khoản sinh viên</h1>
        <form action="${pageContext.request.contextPath}/dangky" method="post">
            <c:if test="${not empty emailError}">
                <div class="alert alert-danger">
                    ${emailError}
                </div>
            </c:if>

            <div class="form-group">
                <label for="name">Họ và tên:</label>
                <input type="text" id="name" name="name" class="form-control" value="${sinhVien.name}" required />
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" class="form-control" value="${sinhVien.email}" required />
            </div>

            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" class="form-control" required />
            </div>

            <div class="form-group">
                <label for="ngaySinh">Ngày sinh:</label>
                <input type="date" id="ngaySinh" name="ngaySinh" class="form-control" value="${sinhVien.ngaySinh}" required />
            </div>

            <div class="form-group">
                <label for="queQuan">Quê quán:</label>
                <input type="text" id="queQuan" name="queQuan" class="form-control" value="${sinhVien.queQuan}" required />
            </div>

            <div class="form-group">
                <label for="gioiTinh">Giới tính:</label>
                <select id="gioiTinh" name="gioiTinh" class="form-control" required>
                    <option value="Nam" ${sinhVien.gioiTinh == 'Nam' ? 'selected' : ''}>Nam</option>
                    <option value="Nữ" ${sinhVien.gioiTinh == 'Nữ' ? 'selected' : ''}>Nữ</option>
                    <option value="Khác" ${sinhVien.gioiTinh == 'Khác' ? 'selected' : ''}>Khác</option>
                </select>
            </div>

            <div class="form-group">
                <label for="lopHoc">Lớp học:</label>
                <select id="lopHoc" name="lopHoc.id" class="form-control">
                    <c:forEach var="lopHoc" items="${lopHocs}">
                        <option value="${lopHoc.id}" ${sinhVien.lopHoc != null && sinhVien.lopHoc.id == lopHoc.id ? 'selected' : ''}>${lopHoc.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="khoa">Khoa:</label>
                <select id="khoa" name="khoa.id" class="form-control">
                    <c:forEach var="khoa" items="${khoas}">
                        <option value="${khoa.id}" ${sinhVien.khoa != null && sinhVien.khoa.id == khoa.id ? 'selected' : ''}>${khoa.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="nganhDaoTao">Ngành đào tạo:</label>
                <select id="nganhDaoTao" name="nganhDaoTao.id" class="form-control">
                    <c:forEach var="nganhDaoTao" items="${nganhDaoTaos}">
                        <option value="${nganhDaoTao.id}" ${sinhVien.nganhDaoTao != null && sinhVien.nganhDaoTao.id == nganhDaoTao.id ? 'selected' : ''}>${nganhDaoTao.name}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Đăng ký</button>
            <a href="${pageContext.request.contextPath}/dangnhap" class="btn btn-secondary ml-2">Quay lại Đăng nhập</a>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
