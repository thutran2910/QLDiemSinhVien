<%-- 
    Document   : DTB
    Created on : Aug 17, 2024, 8:33:51 PM
    Author     : HP
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Điểm Trung Bình</title>
</head>
<body>
    <h1>Điểm Trung Bình</h1>
    <form action="${pageContext.request.contextPath}/dtb" method="get">
        <label for="sinhVienId">Sinh Viên ID:</label>
        <input type="number" id="sinhVienId" name="sinhVienId" value="${sinhVienId}" />
        <br/>
        
        <label for="monHocId">Môn Học ID:</label>
        <input type="number" id="monHocId" name="monHocId" value="${monHocId}" />
        <br/>
        
        <label for="lopHocId">Lớp Học ID:</label>
        <input type="number" id="lopHocId" name="lopHocId" value="${lopHocId}" />
        <br/>
        
        <input type="submit" value="Tính Điểm Trung Bình" />
    </form>

    <c:if test="${averageScore != null}">
        <h2>Kết Quả:</h2>
        <p>Điểm Trung Bình: ${averageScore}</p>
    </c:if>
</body>
</html>
