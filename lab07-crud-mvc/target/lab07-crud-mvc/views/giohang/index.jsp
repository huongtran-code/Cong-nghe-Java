<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="pageTitle" value="Giỏ hàng của bạn - Lab 7" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">🛒 Giỏ Hàng Mua Sắm (HttpSession)</h2>
            <c:if test="${not empty sessionScope.gioHang && not empty sessionScope.gioHang.items}">
                <a href="${pageContext.request.contextPath}/gio-hang?action=clear" 
                   onclick="return confirm('Bạn có muốn xóa toàn bộ giỏ hàng không?');" 
                   class="btn btn-danger btn-sm">
                    🗑️ Xóa toàn bộ giỏ hàng
                </a>
            </c:if>
        </div>

        <c:choose>
            <c:when test="${not empty sessionScope.gioHang && not empty sessionScope.gioHang.items}">
                <div class="table-responsive">
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Mã SP</th>
                                <th>Tên Sản Phẩm</th>
                                <th>Đơn Giá</th>
                                <th style="width: 160px; text-align: center;">Số Lượng</th>
                                <th>Thành Tiền</th>
                                <th style="text-align: center;">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${sessionScope.gioHang.items}" varStatus="loop">
                                <tr>
                                    <td>${loop.index + 1}</td>
                                    <td><strong>${item.sanPham.maSanPham}</strong></td>
                                    <td>${item.sanPham.tenSanPham}</td>
                                    <td style="color: var(--accent-amber);">
                                        <fmt:formatNumber value="${item.sanPham.gia}" type="number" pattern="#,##0" /> ₫
                                    </td>
                                    <td style="text-align: center;">
                                        <form action="${pageContext.request.contextPath}/gio-hang" method="post" style="display: flex; gap: 4px; justify-content: center;">
                                            <input type="hidden" name="action" value="update">
                                            <input type="hidden" name="id" value="${item.sanPham.id}">
                                            <input type="number" name="soLuong" value="${item.soLuong}" min="1" max="99" class="form-control" style="width: 70px; padding: 4px 8px; text-align: center;">
                                            <button type="submit" class="btn btn-secondary btn-sm" title="Cập nhật">🔄</button>
                                        </form>
                                    </td>
                                    <td style="color: var(--accent-emerald); font-weight: 700;">
                                        <fmt:formatNumber value="${item.thanhTien}" type="number" pattern="#,##0" /> ₫
                                    </td>
                                    <td style="text-align: center;">
                                        <a href="${pageContext.request.contextPath}/gio-hang?action=remove&id=${item.sanPham.id}" class="btn btn-danger btn-sm">Xóa</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 24px; padding-top: 16px; border-top: 1px solid var(--border-color); flex-wrap: wrap; gap: 16px;">
                    <div>
                        <a href="${pageContext.request.contextPath}/san-pham" class="btn btn-secondary">
                            ‹ Tiếp tục mua sắm
                        </a>
                    </div>
                    <div style="display: flex; align-items: center; gap: 20px;">
                        <span style="font-size: 1.1rem; color: var(--text-muted);">
                            Tổng số lượng: <strong>${sessionScope.gioHang.tongSoLuong}</strong> món
                        </span>
                        <span style="font-size: 1.3rem;">
                            Tổng tiền: <strong style="color: var(--accent-emerald);">
                                <fmt:formatNumber value="${sessionScope.gioHang.tongTien}" type="number" pattern="#,##0" /> VNĐ
                            </strong>
                        </span>
                        <button onclick="alert('Cảm ơn bạn đã trải nghiệm mô phỏng Thanh toán trong Lab 7!');" class="btn btn-primary">
                            💳 Thanh toán ngay
                        </button>
                    </div>
                </div>
            </c:when>

            <c:otherwise>
                <div style="text-align: center; padding: 40px 20px;">
                    <div style="font-size: 3rem; margin-bottom: 12px;">🛒</div>
                    <h3 style="margin-bottom: 8px;">Giỏ hàng của bạn đang trống!</h3>
                    <p style="color: var(--text-muted); margin-bottom: 20px;">Hãy chọn các sản phẩm từ danh mục để thêm vào giỏ hàng.</p>
                    <a href="${pageContext.request.contextPath}/san-pham" class="btn btn-primary">
                        👉 Xem danh sách sản phẩm
                    </a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
