<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="Chi tiết sinh viên - ${sv.hoTen}" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container" style="max-width: 650px;">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">👤 Thông Tin Chi Tiết Sinh Viên</h2>
        </div>

        <c:choose>
            <c:when test="${not empty sv}">
                <div style="display: flex; flex-direction: column; gap: 16px; font-size: 1rem;">
                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Mã Hệ Thống (ID):</span>
                        <strong>#${sv.id}</strong>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Mã Sinh Viên:</span>
                        <strong>${sv.maSinhVien}</strong>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Họ và Tên:</span>
                        <strong style="color: var(--accent-blue); font-size: 1.1rem;">${sv.hoTen}</strong>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Email Liên Hệ:</span>
                        <span>${sv.email}</span>
                    </div>

                    <div style="display: flex; border-bottom: 1px solid var(--border-color); padding-bottom: 8px;">
                        <span style="width: 140px; color: var(--text-muted);">Lớp Sinh Hoạt:</span>
                        <span class="badge badge-grade-B">${sv.lop}</span>
                    </div>
                </div>

                <div style="display: flex; gap: 12px; margin-top: 24px;">
                    <a href="${pageContext.request.contextPath}/admin/sinh-vien?action=edit&id=${sv.id}" class="btn btn-warning">✏️ Chỉnh sửa</a>
                    <a href="${pageContext.request.contextPath}/sinh-vien" class="btn btn-secondary">Quay lại danh sách</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-danger">❌ Không tìm thấy thông tin sinh viên yêu cầu.</div>
                <a href="${pageContext.request.contextPath}/sinh-vien" class="btn btn-secondary">Quay lại danh sách</a>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
