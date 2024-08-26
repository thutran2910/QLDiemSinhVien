<%-- 
    Document   : SinhVienTimThay
    Created on : Aug 22, 2024, 10:55:22 AM
    Author     : HP
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <title>T?m Ki?m Sinh Vi?n</title>
</head>
<body>
    <h1>T?m Ki?m Sinh Vi?n</h1>
    <form action="${pageContext.request.contextPath}/search" method="get">
        <label for="searchTerm">Nh?p m? ho?c t?n sinh vi?n:</label>
        <input type="text" id="searchTerm" name="searchTerm" required/>
        <button type="submit">T?m Ki?m</button>
    </form>

    <c:if test="${not empty sinhViens}">
        <h2>K?t qu? t?m ki?m:</h2>
        <ul>
            <c:forEach var="sinhVien" items="${sinhViens}">
                <li>
                    <a href="${pageContext.request.contextPath}/monhoc/${sinhVien.id}">
                        ${sinhVien.name}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</body>
</html>
