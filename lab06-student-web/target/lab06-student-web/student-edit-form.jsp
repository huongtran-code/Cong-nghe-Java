<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sửa Thông Tin Sinh Viên</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container container-sm">
        <h2>✏️ Cập Nhật Thông Tin</h2>
        <form action="${pageContext.request.contextPath}/students/update" method="post">
            <div class="form-group">
                <label>Mã sinh viên (Không thể sửa):</label>
                <input type="text" name="id" value="${student.id}" readonly style="background-color: rgba(255,255,255,0.3); border-color: transparent; cursor: not-allowed; color: var(--text-muted);">
            </div>
            
            <div class="form-group">
                <label>Họ tên:</label>
                <input type="text" name="name" value="${student.name}" required autofocus>
            </div>
            
            <div class="form-group">
                <label>Lớp:</label>
                <input type="text" name="className" value="${student.className}" required>
            </div>
            
            <div class="form-group" style="margin-bottom: 30px;">
                <label>Email:</label>
                <input type="email" name="email" value="${student.email}" required>
            </div>
            
            <div style="display: flex; gap: 15px;">
                <button type="submit" style="flex: 1;">💾 Cập nhật</button>
                <a href="${pageContext.request.contextPath}/students" class="btn btn-outline" style="flex: 1;">⬅️ Hủy bỏ</a>
            </div>
        </form>
    </div>
</body>
</html>
