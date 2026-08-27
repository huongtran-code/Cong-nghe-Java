<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Đăng nhập - Lab10</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        </head>

        <body>
            <div class="container" style="max-width: 420px; margin-top: 40px;">
                <h2>Đăng Nhập Hệ Thống</h2>
                <p>Vui lòng nhập tài khoản để tiếp tục</p>

                <c:if test="${not empty error}">
                    <p
                        style="color: #dc3545; background: #f8d7da; padding: 10px; border-radius: 6px; font-size: 14px; border: 1px solid #f5c6cb;">
                        ${error}
                    </p>
                </c:if>

                <form method="post" action="${pageContext.request.contextPath}/auth">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" name="email" placeholder="nhap@email.com" required>
                    </div>
                    <div class="form-group">
                        <label>Mật khẩu</label>
                        <input type="password" name="password" placeholder="••••••••" required>
                    </div>
                    <button type="submit" style="width: 100%; margin-top: 10px;">Đăng nhập</button>
                </form>

                <hr>
                <small style="color: #6c757d; line-height: 1.6;">
                    <b>Tài khoản thử nghiệm:</b><br>
                    • Admin: <code>admin@eaut.edu.vn</code> / <code>123456</code><br>
                    • Staff: <code>staff@eaut.edu.vn</code> / <code>123456</code><br>
                    • User: <code>user@eaut.edu.vn</code> / <code>123456</code>
                </small>
            </div>
        </body>

        </html>