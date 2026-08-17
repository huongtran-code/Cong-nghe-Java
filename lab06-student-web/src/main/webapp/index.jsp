<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang Chủ - Quản Lý Sinh Viên</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container container-sm">
        <h2>👋 Chào mừng!</h2>
        <p style="text-align: center; margin-bottom: 20px; color: var(--text-muted);">Ứng dụng quản lý sinh viên - Lab 6</p>
        <ul class="menu-list">
            <li><a href="${pageContext.request.contextPath}/login.jsp">🔑 Đăng nhập hệ thống</a></li>
            <li><a href="${pageContext.request.contextPath}/hello">👋 Thử nghiệm Hello Servlet</a></li>
        </ul>
    </div>
</body>
</html>
