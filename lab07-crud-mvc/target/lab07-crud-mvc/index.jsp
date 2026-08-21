<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="Trang chủ - Lab 7 CRUD MVC" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">🚀 Lab 7 - Xây dựng CRUD bằng Servlet + JSP, dùng MVC đơn giản</h2>
        </div>
        <p style="color: var(--text-muted); font-size: 1rem; line-height: 1.6;">
            Chào mừng bạn đến với hệ thống quản lý tích hợp Mô hình MVC (Model - View - Controller). 
            Hệ thống cung cấp đầy đủ các module quản lý dữ liệu, xác thực phân quyền với <strong>Filter</strong>, và quản lý giỏ hàng bằng <strong>HttpSession</strong>.
        </p>

        <div class="dashboard-grid">
            <a href="${pageContext.request.contextPath}/sinh-vien" class="stat-card">
                <div class="icon">🎓</div>
                <div class="count">Sinh viên</div>
                <p style="color: var(--text-muted); font-size: 0.88rem;">Quản lý danh sách sinh viên, lớp học, thông tin cá nhân & phân trang.</p>
            </a>

            <a href="${pageContext.request.contextPath}/sach" class="stat-card">
                <div class="icon">📚</div>
                <div class="count">Quản lý Sách</div>
                <p style="color: var(--text-muted); font-size: 0.88rem;">Tra cứu sách theo tên, tác giả, nhà xuất bản, năm xuất bản.</p>
            </a>

            <a href="${pageContext.request.contextPath}/san-pham" class="stat-card">
                <div class="icon">📦</div>
                <div class="count">Sản phẩm</div>
                <p style="color: var(--text-muted); font-size: 0.88rem;">Quản lý kho hàng, kiểm định dữ liệu (Giá &gt; 0, Số lượng &ge; 0).</p>
            </a>

            <a href="${pageContext.request.contextPath}/lop-hoc" class="stat-card">
                <div class="icon">🏫</div>
                <div class="count">Lớp học</div>
                <p style="color: var(--text-muted); font-size: 0.88rem;">Quản lý danh sách lớp học, cố vấn học tập và sĩ số sinh viên.</p>
            </a>

            <a href="${pageContext.request.contextPath}/diem-sinh-vien" class="stat-card">
                <div class="icon">📊</div>
                <div class="count">Điểm số</div>
                <p style="color: var(--text-muted); font-size: 0.88rem;">Nhập điểm chuyên cần, giữa kỳ, cuối kỳ; tự động xếp loại A/B/C/D/F.</p>
            </a>

            <a href="${pageContext.request.contextPath}/gio-hang" class="stat-card">
                <div class="icon">🛒</div>
                <div class="count">Giỏ hàng</div>
                <p style="color: var(--text-muted); font-size: 0.88rem;">Quản lý giỏ hàng mua sắm sử dụng Session, cập nhật số lượng.</p>
            </a>
        </div>
    </div>

    <div class="card">
        <h3 style="margin-bottom: 12px; font-size: 1.15rem;">🔒 Khu vực Quản trị & Bảo mật (Filter)</h3>
        <p style="color: var(--text-muted); font-size: 0.92rem; margin-bottom: 16px;">
            Các thao tác chỉnh sửa/xóa nâng cao ở đường dẫn <code>/admin/*</code> sẽ được bảo vệ bởi <code>LoginFilter</code>. 
            Bạn có thể thử truy cập trực tiếp hoặc đăng nhập tài khoản demo.
        </p>
        <div style="display: flex; gap: 12px; align-items: center;">
            <c:choose>
                <c:when test="${not empty sessionScope.username}">
                    <span class="alert alert-success" style="margin-bottom: 0;">
                        ✓ Bạn đang đăng nhập với quyền: <strong>${sessionScope.username}</strong>
                    </span>
                    <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Đăng xuất</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login.jsp" class="btn btn-primary">Đăng nhập Quản trị</a>
                    <span style="color: var(--text-muted); font-size: 0.88rem;">(Tài khoản mẫu: <strong>admin</strong> / <strong>123456</strong>)</span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
