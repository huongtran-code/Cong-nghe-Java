<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="${sp != null && sp.id != 0 ? 'Chỉnh sửa sản phẩm' : 'Thêm sản phẩm mới'}" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container" style="max-width: 600px;">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">${sp != null && sp.id != 0 ? '✏️ Chỉnh sửa Sản Phẩm' : '➕ Thêm Sản Phẩm Mới'}</h2>
        </div>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                ⚠️ ${error}
            </div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/admin/san-pham">
            <input type="hidden" name="id" value="${sp.id}">

            <div class="form-group">
                <label for="maSanPham">Mã Sản Phẩm (*)</label>
                <input type="text" id="maSanPham" name="maSanPham" value="${sp.maSanPham}" class="form-control" placeholder="Ví dụ: SP001" required>
            </div>

            <div class="form-group">
                <label for="tenSanPham">Tên Sản Phẩm (*)</label>
                <input type="text" id="tenSanPham" name="tenSanPham" value="${sp.tenSanPham}" class="form-control" placeholder="Nhập tên sản phẩm" required>
            </div>

            <div class="form-group">
                <label for="moTa">Mô Tả Sản Phẩm</label>
                <textarea id="moTa" name="moTa" class="form-control" placeholder="Mô tả thông số chi tiết sản phẩm...">${sp.moTa}</textarea>
            </div>

            <div class="form-group">
                <label for="gia">Đơn Giá (VNĐ) (* Validation: Giá > 0)</label>
                <input type="number" step="1000" id="gia" name="gia" value="${sp.gia}" class="form-control" placeholder="Nhập giá bán (> 0)" required>
            </div>

            <div class="form-group">
                <label for="soLuong">Số Lượng Kho (* Validation: Số lượng ≥ 0)</label>
                <input type="number" id="soLuong" name="soLuong" value="${sp.soLuong}" class="form-control" placeholder="Nhập số lượng tồn kho (≥ 0)" required>
            </div>

            <div style="display: flex; gap: 12px; margin-top: 24px;">
                <button type="submit" class="btn btn-primary">💾 Lưu sản phẩm</button>
                <a href="${pageContext.request.contextPath}/san-pham" class="btn btn-secondary">Hủy bỏ</a>
            </div>
        </form>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
