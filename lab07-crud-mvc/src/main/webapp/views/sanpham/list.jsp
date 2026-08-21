<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="pageTitle" value="Quản lý sản phẩm - Lab 7" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">📦 Danh sách Sản phẩm</h2>
            <a href="${pageContext.request.contextPath}/admin/san-pham?action=new" class="btn btn-primary">
                ➕ Thêm sản phẩm mới
            </a>
        </div>

        <div class="toolbar">
            <form method="get" action="${pageContext.request.contextPath}/san-pham" class="search-box">
                <input type="text" name="keyword" value="${keyword}" class="form-control" placeholder="Tìm theo tên sản phẩm hoặc mã SP...">
                <button type="submit" class="btn btn-secondary">🔍 Tìm kiếm</button>
                <c:if test="${not empty keyword}">
                    <a href="${pageContext.request.contextPath}/san-pham" class="btn btn-secondary btn-sm">Xóa bộ lọc</a>
                </c:if>
            </form>
        </div>

        <div class="table-responsive">
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Mã SP</th>
                        <th>Tên Sản Phẩm</th>
                        <th>Đơn Giá (VNĐ)</th>
                        <th>Số Lượng Kho</th>
                        <th style="text-align: center;">Giỏ Hàng</th>
                        <th style="text-align: center;">Thao tác Quản trị</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty dsSanPham}">
                            <c:forEach var="sp" items="${dsSanPham}">
                                <tr>
                                    <td>${sp.id}</td>
                                    <td><strong>${sp.maSanPham}</strong></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/san-pham?action=detail&id=${sp.id}">
                                            ${sp.tenSanPham}
                                        </a>
                                    </td>
                                    <td style="color: var(--accent-amber); font-weight: 600;">
                                        <fmt:formatNumber value="${sp.gia}" type="number" pattern="#,##0" /> ₫
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${sp.soLuong > 0}">
                                                <span class="badge badge-grade-A">${sp.soLuong} cái</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge badge-grade-F">Hết hàng</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td style="text-align: center;">
                                        <a href="${pageContext.request.contextPath}/gio-hang?action=add&id=${sp.id}" class="btn btn-primary btn-sm">
                                            🛒 +1 VÀO GIỎ
                                        </a>
                                    </td>
                                    <td style="text-align: center;">
                                        <a href="${pageContext.request.contextPath}/san-pham?action=detail&id=${sp.id}" class="btn btn-secondary btn-sm">Xem</a>
                                        <a href="${pageContext.request.contextPath}/admin/san-pham?action=edit&id=${sp.id}" class="btn btn-warning btn-sm">Sửa</a>
                                        <a href="${pageContext.request.contextPath}/admin/san-pham?action=delete&id=${sp.id}" 
                                           onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm ${sp.tenSanPham} không?');" 
                                           class="btn btn-danger btn-sm">Xóa</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="7" style="text-align: center; color: var(--text-muted); padding: 24px;">
                                    Không tìm thấy sản phẩm phù hợp.
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>

        <!-- Phân trang -->
        <c:if test="${totalPages > 1}">
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="${pageContext.request.contextPath}/san-pham?keyword=${keyword}&page=${currentPage - 1}">‹ Trước</a>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <c:choose>
                        <c:when test="${i == currentPage}">
                            <span class="active">${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/san-pham?keyword=${keyword}&page=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <a href="${pageContext.request.contextPath}/san-pham?keyword=${keyword}&page=${currentPage + 1}">Sau ›</a>
                </c:if>
            </div>
        </c:if>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
