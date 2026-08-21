<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header class="main-navbar">
    <div class="navbar-container">
        <a href="${pageContext.request.contextPath}/index.jsp" class="brand-logo">
            ⚡ Lab 7 MVC <span class="badge-tech">Jakarta EE</span>
        </a>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a></li>
            <li><a href="${pageContext.request.contextPath}/sinh-vien">Sinh viên</a></li>
            <li><a href="${pageContext.request.contextPath}/sach">Sách</a></li>
            <li><a href="${pageContext.request.contextPath}/san-pham">Sản phẩm</a></li>
            <li><a href="${pageContext.request.contextPath}/lop-hoc">Lớp học</a></li>
            <li><a href="${pageContext.request.contextPath}/diem-sinh-vien">Điểm số</a></li>
            <li><a href="${pageContext.request.contextPath}/gio-hang">🛒 Giỏ hàng</a></li>
        </ul>
        <div class="user-status">
            <c:choose>
                <c:when test="${not empty sessionScope.username}">
                    <span class="user-badge">👤 ${sessionScope.username}</span>
                    <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary btn-sm">Đăng xuất</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login.jsp" class="btn btn-primary btn-sm">Đăng nhập</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>
