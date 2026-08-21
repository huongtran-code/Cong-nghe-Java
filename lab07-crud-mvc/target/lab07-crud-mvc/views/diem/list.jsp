<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="pageTitle" value="Quản lý điểm sinh viên - Lab 7" />
<jsp:include page="/views/common/header.jsp" />
<jsp:include page="/views/common/navbar.jsp" />

<main class="app-container">
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">📊 Bảng Điểm & Xếp Loại Sinh Viên</h2>
            <a href="${pageContext.request.contextPath}/admin/diem-sinh-vien?action=new" class="btn btn-primary">
                ➕ Nhập điểm sinh viên
            </a>
        </div>

        <div class="toolbar">
            <form method="get" action="${pageContext.request.contextPath}/diem-sinh-vien" class="search-box">
                <input type="text" name="keyword" value="${keyword}" class="form-control" placeholder="Tìm theo Mã SV hoặc Họ tên sinh viên...">
                <button type="submit" class="btn btn-secondary">🔍 Tìm kiếm</button>
                <c:if test="${not empty keyword}">
                    <a href="${pageContext.request.contextPath}/diem-sinh-vien" class="btn btn-secondary btn-sm">Xóa bộ lọc</a>
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
                        <th>Chuyên Cần (10%)</th>
                        <th>Giữa Kỳ (30%)</th>
                        <th>Cuối Kỳ (60%)</th>
                        <th>Tổng Kết</th>
                        <th>Xếp Loại</th>
                        <th style="text-align: center;">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty dsDiem}">
                            <c:forEach var="d" items="${dsDiem}">
                                <tr>
                                    <td>${d.id}</td>
                                    <td><strong>${d.maSV}</strong></td>
                                    <td>${d.hoTen}</td>
                                    <td>${d.diemChuyenCan}</td>
                                    <td>${d.diemGiuaKy}</td>
                                    <td>${d.diemCuoiKy}</td>
                                    <td style="font-weight: 700; color: var(--accent-blue);">${d.diemTongKet}</td>
                                    <td>
                                        <span class="badge badge-grade-${d.xepLoai}">
                                            Hạng ${d.xepLoai}
                                        </span>
                                    </td>
                                    <td style="text-align: center;">
                                        <a href="${pageContext.request.contextPath}/admin/diem-sinh-vien?action=edit&id=${d.id}" class="btn btn-warning btn-sm">Cập nhật</a>
                                        <a href="${pageContext.request.contextPath}/admin/diem-sinh-vien?action=delete&id=${d.id}" 
                                           onclick="return confirm('Bạn có chắc muốn xóa điểm của ${d.hoTen}?');" 
                                           class="btn btn-danger btn-sm">Xóa</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="9" style="text-align: center; color: var(--text-muted); padding: 24px;">
                                    Chưa có dữ liệu điểm sinh viên.
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>

        <div style="margin-top: 20px; font-size: 0.85rem; color: var(--text-muted); display: flex; gap: 16px; flex-wrap: wrap;">
            <span>📌 <strong>Quy tắc xếp loại:</strong></span>
            <span><strong style="color: #34d399;">A</strong>: &ge; 8.5</span>
            <span><strong style="color: #38bdf8;">B</strong>: 7.0 – 8.4</span>
            <span><strong style="color: #fbbf24;">C</strong>: 5.5 – 6.9</span>
            <span><strong style="color: #fb923c;">D</strong>: 4.0 – 5.4</span>
            <span><strong style="color: #f87171;">F</strong>: &lt; 4.0</span>
        </div>
    </div>
</main>

<jsp:include page="/views/common/footer.jsp" />
