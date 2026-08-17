<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>403 - Truy Cập Bị Từ Chối</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container container-sm" style="text-align: center;">
        <h1 style="font-size: 3.5rem; margin-bottom: 10px; color: var(--danger-color);">403</h1>
        <h2>⚠️ Truy Cập Bị Từ Chối</h2>
        <div class="alert alert-error" style="margin: 20px 0;">
            Bạn không có quyền thực hiện chức năng quản trị này! Chỉ có tài khoản <strong>ADMIN</strong> mới có quyền Thêm, Sửa hoặc Xóa sinh viên.
        </div>
        <div class="btn-group" style="justify-content: center; margin-top: 20px;">
            <a href="${pageContext.request.contextPath}/students" class="btn">👥 Về trang danh sách</a>
            <a href="${pageContext.request.contextPath}/welcome" class="btn btn-outline">🏠 Về trang chủ</a>
        </div>
    </div>
</body>
</html>
