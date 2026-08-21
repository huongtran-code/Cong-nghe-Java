<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="Đăng nhập hệ thống - Lab 7 MVC" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container" style="max-width: 480px; margin-top: 40px;">
    <div class="card">
        <div class="card-header" style="justify-content: center; text-align: center;">
            <h2 class="card-title">🔐 Đăng Nhập Hệ Thống</h2>
        </div>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                ⚠️ ${error}
            </div>
        </c:if>

        <c:if test="${param.message == 'logout_success'}">
            <div class="alert alert-info">
                ℹ️ Bạn đã đăng xuất thành công!
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="username">Tên đăng nhập</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Nhập tên đăng nhập (vd: admin)" required autofocus>
            </div>

            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Nhập mật khẩu (vd: 123456)" required>
            </div>

            <div style="margin-top: 24px;">
                <button type="submit" class="btn btn-primary" style="width: 100%;">Đăng nhập</button>
            </div>
        </form>

        <div style="margin-top: 20px; font-size: 0.85rem; color: var(--text-muted); text-align: center;">
            💡 Mẹo: Nhập <strong>admin</strong> / <strong>123456</strong> để đăng nhập với quyền Quản trị viên.
        </div>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
