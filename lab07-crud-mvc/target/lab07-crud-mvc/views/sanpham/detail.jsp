<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="pageTitle" value="Chi tiết sản phẩm - ${sp.tenSanPham}" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container" style="max-width: 650px;">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">🔍 Chi Tiết Sản Phẩm</h2>
        </div>

        <c:choose>
            <c:when test="${not empty sp}">
                <div style="display: flex; flex-direction: column; gap: 16px; font-size: 1rem;">
                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Mã Hệ Thống:</span>
                        <strong>#${sp.id}</strong>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Mã Sản Phẩm:</span>
                        <strong>${sp.maSanPham}</strong>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Tên Sản Phẩm:</span>
                        <strong style="color: var(--accent-blue); font-size: 1.1rem;">${sp.tenSanPham}</strong>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Mô Tả:</span>
                        <span>${sp.moTa != null && not empty sp.moTa ? sp.moTa : 'Chưa có mô tả'}</span>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Giá Bán:</span>
                        <strong style="color: var(--accent-amber); font-size: 1.2rem;">
                            <fmt:formatNumber value="${sp.gia}" type="number" pattern="#,##0" /> VNĐ
                        </strong>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Số Lượng Kho:</span>
                        <span class="badge badge-grade-A">${sp.soLuong} sản phẩm</span>
                    </div>
                </div>

                <div style="display: flex; gap: 12px; margin-top: 24px;">
                    <a href="${pageContext.request.contextPath}/gio-hang?action=add&id=${sp.id}" class="btn btn-primary">🛒 Thêm vào giỏ hàng</a>
                    <a href="${pageContext.request.contextPath}/admin/san-pham?action=edit&id=${sp.id}" class="btn btn-warning">✏️ Chỉnh sửa</a>
                    <a href="${pageContext.request.contextPath}/san-pham" class="btn btn-secondary">Quay lại danh sách</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-danger">❌ Không tìm thấy thông tin sản phẩm yêu cầu.</div>
                <a href="${pageContext.request.contextPath}/san-pham" class="btn btn-secondary">Quay lại danh sách</a>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
