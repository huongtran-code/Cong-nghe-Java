<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh Sách Sinh Viên</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container">
        <h2>🎓 Quản Lý Danh Sách Sinh Viên</h2>
        
        <div class="alert" style="background: rgba(99, 102, 241, 0.1); border: 1px solid rgba(99, 102, 241, 0.2); margin-bottom: 20px; display: flex; justify-content: space-between; align-items: center;">
            <span>👤 Tài khoản: <strong>${sessionScope.username}</strong> | Vai trò hiện tại: <strong style="color: var(--primary-color);">${sessionScope.role}</strong></span>
            <a href="${pageContext.request.contextPath}/switch-role" class="btn btn-outline" style="font-size: 0.85rem; padding: 6px 12px;">
                🔄 Đổi sang quyền ${sessionScope.role == 'ADMIN' ? 'USER' : 'ADMIN'}
            </a>
        </div>

        <div class="action-links">
            <c:if test="${sessionScope.role == 'ADMIN'}">
                <a href="${pageContext.request.contextPath}/student-form.jsp" class="btn">➕ Thêm sinh viên mới</a>
                <a href="${pageContext.request.contextPath}/users" class="btn btn-outline" style="border-color: var(--primary-color);">⚙️ Phân quyền người dùng</a>
            </c:if>
            <a href="${pageContext.request.contextPath}/welcome" class="btn btn-outline">⬅️ Về trang tổng quan</a>
        </div>

        <!-- Form tìm kiếm sinh viên -->
        <form action="${pageContext.request.contextPath}/students" method="get" class="search-form">
            <input type="text" name="search" value="${search}" placeholder="🔍 Nhập họ tên cần tìm...">
            <div class="btn-group">
                <button type="submit">Tìm kiếm</button>
                <a href="${pageContext.request.contextPath}/students" class="btn btn-outline">Hủy tìm</a>
            </div>
        </form>

        <c:if test="${empty students}">
            <div class="alert alert-error">Không tìm thấy sinh viên nào phù hợp!</div>
        </c:if>

        <c:if test="${not empty students}">
            <table>
                <tr>
                    <th>Mã SV</th>
                    <th>Họ tên</th>
                    <th>Lớp</th>
                    <th>Email</th>
                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        <th>Hành động</th>
                    </c:if>
                </tr>
                <c:forEach var="sv" items="${students}">
                    <tr>
                        <td><strong>${sv.id}</strong></td>
                        <td>${sv.name}</td>
                        <td><span style="background: rgba(99,102,241,0.1); padding: 4px 8px; border-radius: 6px; font-size: 0.85rem; color: var(--primary-color);">${sv.className}</span></td>
                        <td>${sv.email}</td>
                        <c:if test="${sessionScope.role == 'ADMIN'}">
                            <td class="table-actions">
                                <a href="${pageContext.request.contextPath}/students/update?id=${sv.id}" class="edit-link">✏️ Sửa</a>
                                <a href="${pageContext.request.contextPath}/students/delete?id=${sv.id}" onclick="return confirm('Bạn có chắc muốn xóa sinh viên ${sv.name} không?');" class="delete-link">🗑️ Xóa</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>
