<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="${sv != null ? 'Chỉnh sửa sinh viên' : 'Thêm sinh viên mới'}" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container" style="max-width: 600px;">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">${sv != null ? '✏️ Chỉnh sửa Sinh Viên' : '➕ Thêm Sinh Viên Mới'}</h2>
        </div>

        <form method="post" action="${pageContext.request.contextPath}/admin/sinh-vien">
            <input type="hidden" name="id" value="${sv.id}">

            <div class="form-group">
                <label for="maSinhVien">Mã Sinh Viên (*)</label>
                <input type="text" id="maSinhVien" name="maSinhVien" value="${sv.maSinhVien}" class="form-control" placeholder="Ví dụ: 20240001" required>
            </div>

            <div class="form-group">
                <label for="hoTen">Họ và Tên (*)</label>
                <input type="text" id="hoTen" name="hoTen" value="${sv.hoTen}" class="form-control" placeholder="Nhập họ và tên sinh viên" required>
            </div>

            <div class="form-group">
                <label for="email">Địa chỉ Email</label>
                <input type="email" id="email" name="email" value="${sv.email}" class="form-control" placeholder="example@gmail.com">
            </div>

            <div class="form-group">
                <label for="lop">Lớp học</label>
                <input type="text" id="lop" name="lop" value="${sv.lop}" class="form-control" placeholder="Ví dụ: DCCNTT15.10.1">
            </div>

            <div style="display: flex; gap: 12px; margin-top: 24px;">
                <button type="submit" class="btn btn-primary">💾 Lưu thông tin</button>
                <a href="${pageContext.request.contextPath}/sinh-vien" class="btn btn-secondary">Hủy bỏ</a>
            </div>
        </form>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
