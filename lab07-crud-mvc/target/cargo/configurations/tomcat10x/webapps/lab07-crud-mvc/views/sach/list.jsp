<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="Quản lý sách - Lab 7" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">📚 Danh sách sách & Tài liệu</h2>
            <a href="${pageContext.request.contextPath}/admin/sach?action=new" class="btn btn-primary">
                ➕ Thêm sách mới
            </a>
        </div>

        <div class="toolbar">
            <form method="get" action="${pageContext.request.contextPath}/sach" class="search-box">
                <input type="text" name="keyword" value="${keyword}" class="form-control" placeholder="Tìm theo tên sách, tác giả hoặc mã sách...">
                <button type="submit" class="btn btn-secondary">🔍 Tìm kiếm</button>
                <c:if test="${not empty keyword}">
                    <a href="${pageContext.request.contextPath}/sach" class="btn btn-secondary btn-sm">Xóa bộ lọc</a>
                </c:if>
            </form>
        </div>

        <div class="table-responsive">
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Mã Sách</th>
                        <th>Tên Sách</th>
                        <th>Tác Giả</th>
                        <th>Nhà Xuất Bản</th>
                        <th>Năm XB</th>
                        <th style="text-align: center;">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty dsSach}">
                            <c:forEach var="s" items="${dsSach}">
                                <tr>
                                    <td>${s.id}</td>
                                    <td><strong>${s.maSach}</strong></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/sach?action=detail&id=${s.id}">
                                            ${s.tenSach}
                                        </a>
                                    </td>
                                    <td>${s.tacGia}</td>
                                    <td>${s.nhaXuatBan}</td>
                                    <td><span class="badge badge-grade-B">${s.namXuatBan}</span></td>
                                    <td style="text-align: center;">
                                        <a href="${pageContext.request.contextPath}/sach?action=detail&id=${s.id}" class="btn btn-secondary btn-sm">Xem</a>
                                        <a href="${pageContext.request.contextPath}/admin/sach?action=edit&id=${s.id}" class="btn btn-warning btn-sm">Sửa</a>
                                        <a href="${pageContext.request.contextPath}/admin/sach?action=delete&id=${s.id}" 
                                           onclick="return confirm('Bạn có chắc chắn muốn xóa sách ${s.tenSach} không?');" 
                                           class="btn btn-danger btn-sm">Xóa</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="7" style="text-align: center; color: var(--text-muted); padding: 24px;">
                                    Không tìm thấy sách phù hợp.
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
                    <a href="${pageContext.request.contextPath}/sach?keyword=${keyword}&page=${currentPage - 1}">‹ Trước</a>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <c:choose>
                        <c:when test="${i == currentPage}">
                            <span class="active">${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/sach?keyword=${keyword}&page=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <a href="${pageContext.request.contextPath}/sach?keyword=${keyword}&page=${currentPage + 1}">Sau ›</a>
                </c:if>
            </div>
        </c:if>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
