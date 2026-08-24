<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Quản Lý Lớp Học</title>
            <%@ include file="../common/header.jsp" %>
        </head>

        <body class="bg-light">
            <div class="container col-md-9">
                <h2 class="mb-3">Quản Lý Lớp Học</h2>

                <!-- Form tìm kiếm -->
                <form class="row g-2 mb-3" action="${pageContext.request.contextPath}/lop-hoc" method="get">
                    <div class="col-auto">
                        <input type="text" name="keyword" value="${keyword}" class="form-control"
                            placeholder="Tìm theo tên lớp...">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-secondary">Tìm kiếm</button>
                    </div>
                </form>

                <!-- Form thêm mới -->
                <div class="card mb-4 shadow-sm">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/lop-hoc" method="post" class="row g-2">
                            <div class="col-md-9">
                                <input type="text" name="tenLop" class="form-control" placeholder="Tên lớp mới..."
                                    required>
                            </div>
                            <div class="col-md-3">
                                <button type="submit" class="btn btn-success w-100">+ Thêm Lớp</button>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- Bảng danh sách -->
                <table class="table table-bordered bg-white shadow-sm align-middle">
                    <thead class="table-primary text-center">
                        <tr>
                            <th style="width: 15%;">ID</th>
                            <th style="width: 55%;">Tên Lớp</th>
                            <th style="width: 30%;">Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="lop" items="${dsLop}">
                            <tr>
                                <td class="text-center">${lop.id}</td>
                                <td>${lop.tenLop}</td>
                                <td class="text-center">
                                    <button type="button" class="btn btn-sm btn-warning me-1" data-bs-toggle="modal"
                                        data-bs-target="#editModal${lop.id}">Sửa</button>
                                    <a href="${pageContext.request.contextPath}/lop-hoc?action=delete&id=${lop.id}"
                                        class="btn btn-sm btn-danger" onclick="return confirm('Xóa lớp này?')">Xóa</a>

                                    <!-- Modal Sửa Lớp -->
                                    <div class="modal fade" id="editModal${lop.id}" tabindex="-1">
                                        <div class="modal-dialog">
                                            <div class="modal-content text-start">
                                                <form action="${pageContext.request.contextPath}/lop-hoc" method="post">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title">Cập Nhật Lớp Học</h5>
                                                        <button type="button" class="btn-close"
                                                            data-bs-dismiss="modal"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <input type="hidden" name="id" value="${lop.id}">
                                                        <div class="mb-3">
                                                            <label class="form-label">Tên Lớp</label>
                                                            <input type="text" name="tenLop" class="form-control"
                                                                value="${lop.tenLop}" required>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal">Hủy</button>
                                                        <button type="submit" class="btn btn-primary">Lưu thay
                                                            đổi</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Thanh phân trang -->
                <nav>
                    <ul class="pagination">
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                <a class="page-link"
                                    href="${pageContext.request.contextPath}/lop-hoc?page=${i}&keyword=${keyword}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </body>

        </html>