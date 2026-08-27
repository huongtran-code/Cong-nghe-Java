<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Dashboard - Bảng điều khiển</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        </head>

        <body>
            <div class="container">
                <h2>Xin chào, ${currentUser.fullName}</h2>
                <p>Vai trò hiện tại: <span class="active" style="font-size: 14px;">${currentUser.role}</span></p>
                <hr>

                <h3 style="margin-bottom: 15px; color: #495057; font-size: 18px;">Danh mục chức năng</h3>
                <ul class="nav-menu">
                    <c:if test="${currentUser.role == 'ADMIN'}">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/users">
                                🔑 Quản lý người dùng <small>(Dành riêng cho ADMIN)</small>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${currentUser.role == 'ADMIN' || currentUser.role == 'STAFF'}">
                        <li>
                            <a href="${pageContext.request.contextPath}/staff/products">
                                📦 Quản lý nghiệp vụ sản phẩm <small>(STAFF & ADMIN)</small>
                            </a>
                        </li>
                    </c:if>
                    <li>
                        <a href="${pageContext.request.contextPath}/user/profile.jsp">
                            👤 Hồ sơ cá nhân & Đổi mật khẩu
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/auth?action=logout"
                            style="color: #dc3545; background: #fff5f5; border-color: #ffc9c9;">
                            🚪 Đăng xuất hệ thống
                        </a>
                    </li>
                </ul>
            </div>
        </body>

        </html>