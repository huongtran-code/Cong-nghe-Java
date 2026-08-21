<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="${lh != null ? 'Chỉnh sửa thông tin lớp' : 'Thêm lớp học mới'}" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container" style="max-width: 600px;">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">${lh != null ? '✏️ Chỉnh sửa Thông Tin Lớp Học' : '➕ Thêm Lớp Học Mới'}</h2>
        </div>

        <form method="post" action="${pageContext.request.contextPath}/admin/lop-hoc">
            <input type="hidden" name="id" value="${lh.id}">

            <div class="form-group">
                <label for="maLop">Mã Lớp (*)</label>
                <input type="text" id="maLop" name="maLop" value="${lh.maLop}" class="form-control" placeholder="Ví dụ: DCCNTT15.10.1" required>
            </div>

            <div class="form-group">
                <label for="tenLop">Tên Lớp Học (*)</label>
                <input type="text" id="tenLop" name="tenLop" value="${lh.tenLop}" class="form-control" placeholder="Ví dụ: Công nghệ Thông tin 1" required>
            </div>

            <div class="form-group">
                <label for="coVanHocTap">Cố Vấn Học Tập (*)</label>
                <input type="text" id="coVanHocTap" name="coVanHocTap" value="${lh.coVanHocTap}" class="form-control" placeholder="Nhập tên giảng viên cố vấn" required>
            </div>

            <div class="form-group">
                <label for="soLuongSinhVien">Sĩ Số Sinh Viên</label>
                <input type="number" id="soLuongSinhVien" name="soLuongSinhVien" value="${lh.soLuongSinhVien}" class="form-control" placeholder="Ví dụ: 45">
            </div>

            <div style="display: flex; gap: 12px; margin-top: 24px;">
                <button type="submit" class="btn btn-primary">💾 Lưu lớp học</button>
                <a href="${pageContext.request.contextPath}/lop-hoc" class="btn btn-secondary">Hủy bỏ</a>
            </div>
        </form>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
