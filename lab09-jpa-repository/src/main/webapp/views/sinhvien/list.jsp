<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Danh Sách Sinh Viên</title>
            <%@ include file="../common/header.jsp" %>
        </head>

        <body class="bg-light">
            <div class="container">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h2>Danh Sách Sinh Viên</h2>
                    <a href="${pageContext.request.contextPath}/sinh-vien?action=new" class="btn btn-success">+ Thêm
                        Sinh Viên</a>
                </div>

                <c:if test="${not empty param.message}">
                    <div class="alert alert-success">${param.message}</div>
                </c:if>

                <form class="row g-2 mb-3" action="${pageContext.request.contextPath}/sinh-vien" method="get">
                    <div class="col-auto">
                        <input type="text" name="keyword" value="${keyword}" class="form-control"
                            placeholder="Tìm theo tên/mã SV...">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-secondary">Tìm kiếm</button>
                    </div>
                </form>

                <table class="table table-bordered table-hover align-middle bg-white shadow-sm">
                    <thead class="table-primary text-center">
                        <tr>
                            <th style="width: 5%;">ID</th>
                            <th style="width: 15%;">Mã SV</th>
                            <th style="width: 25%;">Họ Tên</th>
                            <th style="width: 25%;">Email</th>
                            <th style="width: 15%;">Lớp</th>
                            <th style="width: 15%;">Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sv" items="${dsSinhVien}">
                            <tr>
                                <td class="text-center">${sv.id}</td>
                                <td>${sv.maSinhVien}</td>
                                <td>${sv.hoTen}</td>
                                <td>${sv.email}</td>
                                <td>${sv.lopHoc.tenLop}</td>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/sinh-vien?action=edit&id=${sv.id}"
                                        class="btn btn-sm btn-warning me-1">Sửa</a>
                                    <a href="${pageContext.request.contextPath}/sinh-vien?action=delete&id=${sv.id}"
                                        class="btn btn-sm btn-danger"
                                        onclick="return confirm('Xóa sinh viên này?')">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <nav>
                    <ul class="pagination">
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                <a class="page-link"
                                    href="${pageContext.request.contextPath}/sinh-vien?page=${i}&keyword=${keyword}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </body>

        </html>