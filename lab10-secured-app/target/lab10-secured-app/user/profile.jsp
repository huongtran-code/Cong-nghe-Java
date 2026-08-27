<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Hồ Sơ Cá Nhân</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        </head>

        <body>
            <div class="container" style="max-width: 600px;">
                <h2>Thông tin tài khoản</h2>
                <div
                    style="background: #f8f9fa; padding: 15px; border-radius: 6px; margin-bottom: 20px; border: 1px solid #e9ecef;">
                    <p style="margin-bottom: 8px;"><b>Họ và tên:</b> ${currentUser.fullName}</p>
                    <p style="margin-bottom: 8px;"><b>Email:</b> ${currentUser.email}</p>
                    <p style="margin-bottom: 0;"><b>Vai trò:</b> <span class="active">${currentUser.role}</span></p>
                </div>

                <hr>

                <div class="form-box">
                    <h3>Đổi mật khẩu</h3>
                    <c:if test="${not empty message}">
                        <p
                            style="color: #2b8a3e; background: #ebfbee; padding: 10px; border-radius: 6px; font-size: 14px; border: 1px solid #b2f2bb;">
                            ${message}
                        </p>
                    </c:if>
                    <c:if test="${not empty error}">
                        <p
                            style="color: #dc3545; background: #f8d7da; padding: 10px; border-radius: 6px; font-size: 14px; border: 1px solid #f5c6cb;">
                            ${error}
                        </p>
                    </c:if>

                    <form method="post" action="${pageContext.request.contextPath}/auth">
                        <input type="hidden" name="action" value="change-password">
                        <div class="form-group">
                            <label>Mật khẩu hiện tại</label>
                            <input type="password" name="oldPassword" required>
                        </div>
                        <div class="form-group">
                            <label>Mật khẩu mới</label>
                            <input type="password" name="newPassword" required>
                        </div>
                        <button type="submit">Cập nhật mật khẩu</button>
                    </form>
                </div>

                <a href="${pageContext.request.contextPath}/dashboard.jsp" class="btn-link">← Quay lại Dashboard</a>
            </div>
        </body>

        </html>