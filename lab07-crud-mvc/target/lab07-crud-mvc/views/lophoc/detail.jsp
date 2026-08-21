<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="Chi tiết lớp học - ${lh.tenLop}" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container" style="max-width: 650px;">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">🏫 Chi Tiết Lớp Học</h2>
        </div>

        <c:choose>
            <c:when test="${not empty lh}">
                <div style="display: flex; flex-direction: column; gap: 16px; font-size: 1rem;">
                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Mã Hệ Thống:</span>
                        <strong>#${lh.id}</strong>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Mã Lớp:</span>
                        <strong>${lh.maLop}</strong>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Tên Lớp Học:</span>
                        <strong style="color: var(--accent-blue); font-size: 1.1rem;">${lh.tenLop}</strong>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Cố Vấn Học Tập:</span>
                        <span>${lh.coVanHocTap}</span>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Sĩ Số Sinh Viên:</span>
                        <span class="badge badge-grade-A">${lh.soLuongSinhVien} sinh viên</span>
                    </div>
                </div>

                <div style="display: flex; gap: 12px; margin-top: 24px;">
                    <a href="${pageContext.request.contextPath}/admin/lop-hoc?action=edit&id=${lh.id}" class="btn btn-warning">✏️ Chỉnh sửa</a>
                    <a href="${pageContext.request.contextPath}/lop-hoc" class="btn btn-secondary">Quay lại danh sách</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-danger">❌ Không tìm thấy thông tin lớp học yêu cầu.</div>
                <a href="${pageContext.request.contextPath}/lop-hoc" class="btn btn-secondary">Quay lại danh sách</a>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
