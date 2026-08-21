<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="Chi tiết sách - ${sach.tenSach}" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container" style="max-width: 650px;">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">📖 Chi Tiết Sách</h2>
        </div>

        <c:choose>
            <c:when test="${not empty sach}">
                <div style="display: flex; flex-direction: column; gap: 16px; font-size: 1rem;">
                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Mã Hệ Thống:</span>
                        <strong>#${sach.id}</strong>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Mã Sách:</span>
                        <strong>${sach.maSach}</strong>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Tên Sách:</span>
                        <strong style="color: var(--accent-blue); font-size: 1.1rem;">${sach.tenSach}</strong>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Tác Giả:</span>
                        <span>${sach.tacGia}</span>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Nhà Xuất Bản:</span>
                        <span>${sach.nhaXuatBan}</span>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Năm Xuất Bản:</span>
                        <span class="badge badge-grade-B">${sach.namXuatBan}</span>
                    </div>
                </div>

                <div style="display: flex; gap: 12px; margin-top: 24px;">
                    <a href="${pageContext.request.contextPath}/admin/sach?action=edit&id=${sach.id}" class="btn btn-warning">✏️ Chỉnh sửa</a>
                    <a href="${pageContext.request.contextPath}/sach" class="btn btn-secondary">Quay lại danh sách</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-danger">❌ Không tìm thấy thông tin sách yêu cầu.</div>
                <a href="${pageContext.request.contextPath}/sach" class="btn btn-secondary">Quay lại danh sách</a>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
