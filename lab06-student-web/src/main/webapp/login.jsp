<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng Nhập - Quản Lý Sinh Viên</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container container-sm">
        <h2>🔒 Đăng nhập hệ thống</h2>
        <p style="text-align: center; margin-bottom: 20px; color: var(--text-muted);">
            Tài khoản mẫu: <strong>admin/123456</strong> (Admin) hoặc <strong>user/123456</strong> (User)
        </p>
        
        <% 
            String error = (String) request.getAttribute("error");
            if (error != null) { 
        %>
            <div class="alert alert-error"><%= error %></div>
        <% } %>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label>Tên đăng nhập</label>
                <input type="text" name="username" placeholder="Nhập tên đăng nhập..." required autofocus>
            </div>
            
            <div class="form-group" style="margin-bottom: 30px;">
                <label>Mật khẩu</label>
                <input type="password" name="password" placeholder="Nhập mật khẩu..." required>
            </div>
            
            <button type="submit" style="width: 100%;">Đăng Nhập</button>
        </form>
    </div>
</body>
</html>
