<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Sinh Viên Mới</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container container-sm">
        <h2>✨ Thêm Sinh Viên Mới</h2>
        <form action="${pageContext.request.contextPath}/students" method="post">
            <div class="form-group">
                <label>Mã sinh viên:</label>
                <input type="text" name="id" placeholder="VD: SV001" required autofocus>
            </div>
            
            <div class="form-group">
                <label>Họ tên:</label>
                <input type="text" name="name" placeholder="Nhập họ tên sinh viên" required>
            </div>
            
            <div class="form-group">
                <label>Lớp:</label>
                <input type="text" name="className" placeholder="VD: CNTT1" required>
            </div>
            
            <div class="form-group" style="margin-bottom: 30px;">
                <label>Email:</label>
                <input type="email" name="email" placeholder="sv@eaut.edu.vn" required>
            </div>
            
            <div style="display: flex; gap: 15px;">
                <button type="submit" style="flex: 1;">💾 Lưu sinh viên</button>
                <a href="${pageContext.request.contextPath}/students" class="btn btn-outline" style="flex: 1;">⬅️ Hủy bỏ</a>
            </div>
        </form>
    </div>
</body>
</html>
