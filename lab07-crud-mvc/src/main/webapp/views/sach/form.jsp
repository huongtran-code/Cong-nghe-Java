<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="${sach != null ? 'Chỉnh sửa thông tin sách' : 'Thêm sách mới'}" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container" style="max-width: 600px;">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">${sach != null ? '✏️ Chỉnh sửa Thông Tin Sách' : '➕ Thêm Sách Mới'}</h2>
        </div>

        <form method="post" action="${pageContext.request.contextPath}/admin/sach">
            <input type="hidden" name="id" value="${sach.id}">

            <div class="form-group">
                <label for="maSach">Mã Sách (*)</label>
                <input type="text" id="maSach" name="maSach" value="${sach.maSach}" class="form-control" placeholder="Ví dụ: S001" required>
            </div>

            <div class="form-group">
                <label for="tenSach">Tên Sách (*)</label>
                <input type="text" id="tenSach" name="tenSach" value="${sach.tenSach}" class="form-control" placeholder="Nhập tên sách" required>
            </div>

            <div class="form-group">
                <label for="tacGia">Tác Giả (*)</label>
                <input type="text" id="tacGia" name="tacGia" value="${sach.tacGia}" class="form-control" placeholder="Nhập tên tác giả" required>
            </div>

            <div class="form-group">
                <label for="nhaXuatBan">Nhà Xuất Bản</label>
                <input type="text" id="nhaXuatBan" name="nhaXuatBan" value="${sach.nhaXuatBan}" class="form-control" placeholder="Ví dụ: NXB Giáo Dục">
            </div>

            <div class="form-group">
                <label for="namXuatBan">Năm Xuất Bản</label>
                <input type="number" id="namXuatBan" name="namXuatBan" value="${sach.namXuatBan != 0 ? sach.namXuatBan : 2024}" class="form-control" placeholder="2024">
            </div>

            <div style="display: flex; gap: 12px; margin-top: 24px;">
                <button type="submit" class="btn btn-primary">💾 Lưu sách</button>
                <a href="${pageContext.request.contextPath}/sach" class="btn btn-secondary">Hủy bỏ</a>
            </div>
        </form>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
