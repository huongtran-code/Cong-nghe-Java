<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="Quản lý sinh viên - Lab 7" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">🎓 Danh sách sinh viên</h2>
            <a href="${pageContext.request.contextPath}/admin/sinh-vien?action=new" class="btn btn-primary">
                ➕ Thêm sinh viên mới
            </a>
        </div>

        <div class="toolbar">
            <form method="get" action="${pageContext.request.contextPath}/sinh-vien" class="search-box">
                <input type="text" name="keyword" value="${keyword}" class="form-control" placeholder="Tìm theo tên, lớp hoặc mã SV...">
                <button type="submit" class="btn btn-secondary">🔍 Tìm kiếm</button>
                <c:if test="${not empty keyword}">
                    <a href="${pageContext.request.contextPath}/sinh-vien" class="btn btn-secondary btn-sm">Xóa bộ lọc</a>
                </c:if>
            </form>
        </div>

        <div class="table-responsive">
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Mã SV</th>
                        <th>Họ và Tên</th>
                        <th>Email</th>
                        <th>Lớp học</th>
                        <th style="text-align: center;">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty dsSinhVien}">
                            <c:forEach var="sv" items="${dsSinhVien}">
                                <tr>
                                    <td>${sv.id}</td>
                                    <td><strong>${sv.maSinhVien}</strong></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/sinh-vien?action=detail&id=${sv.id}">
                                            ${sv.hoTen}
                                        </a>
                                    </td>
                                    <td>${sv.email}</td>
                                    <td><span class="badge badge-grade-B">${sv.lop}</span></td>
                                    <td style="text-align: center;">
                                        <a href="${pageContext.request.contextPath}/sinh-vien?action=detail&id=${sv.id}" class="btn btn-secondary btn-sm">Xem</a>
                                        <a href="${pageContext.request.contextPath}/admin/sinh-vien?action=edit&id=${sv.id}" class="btn btn-warning btn-sm">Sửa</a>
                                        <a href="${pageContext.request.contextPath}/admin/sinh-vien?action=delete&id=${sv.id}" 
                                           onclick="return confirm('Bạn có chắc chắn muốn xóa sinh viên ${sv.hoTen} không?');" 
                                           class="btn btn-danger btn-sm">Xóa</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6" style="text-align: center; color: var(--text-muted); padding: 24px;">
                                    Không tìm thấy dữ liệu sinh viên phù hợp.
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>

        <!-- Phân trang (Bài 11) -->
        <c:if test="${totalPages > 1}">
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="${pageContext.request.contextPath}/sinh-vien?keyword=${keyword}&page=${currentPage - 1}">‹ Trước</a>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <c:choose>
                        <c:when test="${i == currentPage}">
                            <span class="active">${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/sinh-vien?keyword=${keyword}&page=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <a href="${pageContext.request.contextPath}/sinh-vien?keyword=${keyword}&page=${currentPage + 1}">Sau ›</a>
                </c:if>
            </div>
        </c:if>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
