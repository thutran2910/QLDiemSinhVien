<%-- 
    Document   : DangNhap
    Created on : Aug 15, 2024, 10:22:49 PM
    Author     : HP
--%>

<form action="${pageContext.request.contextPath}/login" method="post">
    <label>Username:</label>
    <input type="text" name="username" />
    <br />
    <label>Password:</label>
    <input type="password" name="password" />
    <br />
    <input type="submit" value="Login" />
</form>