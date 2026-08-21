<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="${diem != null ? 'Cập nhật điểm sinh viên' : 'Nhập điểm sinh viên mới'}" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container" style="max-width: 600px;">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">${diem != null ? '✏️ Cập Nhật Điểm Sinh Viên' : '➕ Nhập Điểm Sinh Viên Mới'}</h2>
        </div>

        <form method="post" action="${pageContext.request.contextPath}/admin/diem-sinh-vien">
            <input type="hidden" name="id" value="${diem.id}">

            <div class="form-group">
                <label for="maSV">Mã Sinh Viên (*)</label>
                <input type="text" id="maSV" name="maSV" value="${diem.maSV}" class="form-control" placeholder="Ví dụ: 20240001" required>
            </div>

            <div class="form-group">
                <label for="hoTen">Họ và Tên (*)</label>
                <input type="text" id="hoTen" name="hoTen" value="${diem.hoTen}" class="form-control" placeholder="Nhập họ và tên sinh viên" required>
            </div>

            <div class="form-group">
                <label for="diemChuyenCan">Điểm Chuyên Cần (Trọng số 10%) (0 - 10)</label>
                <input type="number" step="0.1" min="0" max="10" id="diemChuyenCan" name="diemChuyenCan" value="${diem.diemChuyenCan}" class="form-control" placeholder="Nhập điểm CC" required>
            </div>

            <div class="form-group">
                <label for="diemGiuaKy">Điểm Giữa Kỳ (Trọng số 30%) (0 - 10)</label>
                <input type="number" step="0.1" min="0" max="10" id="diemGiuaKy" name="diemGiuaKy" value="${diem.diemGiuaKy}" class="form-control" placeholder="Nhập điểm GK" required>
            </div>

            <div class="form-group">
                <label for="diemCuoiKy">Điểm Cuối Kỳ (Trọng số 60%) (0 - 10)</label>
                <input type="number" step="0.1" min="0" max="10" id="diemCuoiKy" name="diemCuoiKy" value="${diem.diemCuoiKy}" class="form-control" placeholder="Nhập điểm CK" required>
            </div>

            <div style="display: flex; gap: 12px; margin-top: 24px;">
                <button type="submit" class="btn btn-primary">💾 Tính điểm & Lưu</button>
                <a href="${pageContext.request.contextPath}/diem-sinh-vien" class="btn btn-secondary">Hủy bỏ</a>
            </div>
        </form>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
