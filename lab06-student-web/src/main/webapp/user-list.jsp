<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản Lý Người Dùng & Phân Quyền</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container">
        <h2>⚙️ Quản Lý Người Dùng & Phân Quyền Hệ Thống</h2>
        
        <div class="action-links">
            <a href="${pageContext.request.contextPath}/welcome" class="btn btn-outline">⬅️ Về trang tổng quan</a>
            <a href="${pageContext.request.contextPath}/students" class="btn">🎓 Đến Quản lý sinh viên</a>
        </div>

        <div class="alert" style="background: rgba(99, 102, 241, 0.1); border: 1px solid rgba(99, 102, 241, 0.2); margin-bottom: 20px;">
            Đang đăng nhập dưới tài khoản: <strong>${sessionScope.username}</strong> | Quyền hiện tại: <strong>${sessionScope.role}</strong>
        </div>

        <table>
            <tr>
                <th>Tên Đăng Nhập</th>
                <th>Họ Và Tên</th>
                <th>Vai Trò Hiện Tại</th>
                <th>Hành Động Phân Quyền</th>
            </tr>
            <c:forEach var="u" items="${userList}">
                <tr>
                    <td><strong>${u.username}</strong></td>
                    <td>${u.fullName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${u.role == 'ADMIN'}">
                                <span style="background: rgba(16, 185, 129, 0.15); color: var(--success-color); padding: 4px 10px; border-radius: 6px; font-weight: bold; font-size: 0.85rem;">
                                    🛡️ ADMIN
                                </span>
                            </c:when>
                            <c:otherwise>
                                <span style="background: rgba(100, 116, 139, 0.15); color: #94a3b8; padding: 4px 10px; border-radius: 6px; font-weight: bold; font-size: 0.85rem;">
                                    👤 USER
                                </span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="table-actions">
                        <c:choose>
                            <c:when test="${u.role == 'USER'}">
                                <a href="${pageContext.request.contextPath}/users/change-role?username=${u.username}&newRole=ADMIN" 
                                   class="btn" 
                                   style="font-size: 0.8rem; padding: 6px 12px; background: var(--success-color);"
                                   onclick="return confirm('Bạn có chắc muốn THĂNG CẤP tài khoản ${u.username} thành ADMIN không?');">
                                    ⚡ Thăng cấp lên ADMIN
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/users/change-role?username=${u.username}&newRole=USER" 
                                   class="btn btn-outline" 
                                   style="font-size: 0.8rem; padding: 6px 12px; color: var(--danger-color); border-color: var(--danger-color);"
                                   onclick="return confirm('Bạn có chắc muốn GIÁNG CẤP tài khoản ${u.username} xuống USER không?');">
                                    🔻 Giáng cấp xuống USER
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
