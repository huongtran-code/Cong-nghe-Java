<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="Quản lý lớp học - Lab 7" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">🏫 Danh sách Lớp Học</h2>
            <a href="${pageContext.request.contextPath}/admin/lop-hoc?action=new" class="btn btn-primary">
                ➕ Thêm lớp học mới
            </a>
        </div>

        <div class="toolbar">
            <form method="get" action="${pageContext.request.contextPath}/lop-hoc" class="search-box">
                <input type="text" name="keyword" value="${keyword}" class="form-control" placeholder="Tìm theo mã lớp, tên lớp hoặc cố vấn...">
                <button type="submit" class="btn btn-secondary">🔍 Tìm kiếm</button>
                <c:if test="${not empty keyword}">
                    <a href="${pageContext.request.contextPath}/lop-hoc" class="btn btn-secondary btn-sm">Xóa bộ lọc</a>
                </c:if>
            </form>
        </div>

        <div class="table-responsive">
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Mã Lớp</th>
                        <th>Tên Lớp Học</th>
                        <th>Cố Vấn Học Tập</th>
                        <th>Sĩ Số Sinh Viên</th>
                        <th style="text-align: center;">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty dsLopHoc}">
                            <c:forEach var="lh" items="${dsLopHoc}">
                                <tr>
                                    <td>${lh.id}</td>
                                    <td><strong>${lh.maLop}</strong></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/lop-hoc?action=detail&id=${lh.id}">
                                            ${lh.tenLop}
                                        </a>
                                    </td>
                                    <td>${lh.coVanHocTap}</td>
                                    <td><span class="badge badge-grade-A">${lh.soLuongSinhVien} SV</span></td>
                                    <td style="text-align: center;">
                                        <a href="${pageContext.request.contextPath}/lop-hoc?action=detail&id=${lh.id}" class="btn btn-secondary btn-sm">Xem</a>
                                        <a href="${pageContext.request.contextPath}/admin/lop-hoc?action=edit&id=${lh.id}" class="btn btn-warning btn-sm">Sửa</a>
                                        <a href="${pageContext.request.contextPath}/admin/lop-hoc?action=delete&id=${lh.id}" 
                                           onclick="return confirm('Bạn có chắc chắn muốn xóa lớp học ${lh.tenLop} không?');" 
                                           class="btn btn-danger btn-sm">Xóa</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6" style="text-align: center; color: var(--text-muted); padding: 24px;">
                                    Không tìm thấy lớp học phù hợp.
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
