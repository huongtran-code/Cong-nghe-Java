<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Quản Lý Môn Học</title>
            <%@ include file="../common/header.jsp" %>
        </head>

        <body class="bg-light">
            <div class="container">
                <h2 class="mb-3">Quản Lý Môn Học</h2>

                <!-- Form tìm kiếm -->
                <form class="row g-2 mb-3" action="${pageContext.request.contextPath}/mon-hoc" method="get">
                    <div class="col-auto">
                        <input type="text" name="keyword" value="${keyword}" class="form-control"
                            placeholder="Tìm theo tên/mã môn...">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-secondary">Tìm kiếm</button>
                    </div>
                </form>

                <!-- Form thêm mới -->
                <div class="card mb-4 shadow-sm">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/mon-hoc" method="post" class="row g-2">
                            <div class="col-md-3">
                                <input type="text" name="maMon" class="form-control" placeholder="Mã môn..." required>
                            </div>
                            <div class="col-md-5">
                                <input type="text" name="tenMon" class="form-control" placeholder="Tên môn học..."
                                    required>
                            </div>
                            <div class="col-md-2">
                                <input type="number" name="soTinChi" class="form-control" placeholder="Tín chỉ"
                                    value="3" required>
                            </div>
                            <div class="col-md-2">
                                <button type="submit" class="btn btn-success w-100">+ Thêm Môn</button>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- Bảng danh sách -->
                <table class="table table-bordered bg-white shadow-sm align-middle">
                    <thead class="table-primary text-center">
                        <tr>
                            <th>ID</th>
                            <th>Mã Môn</th>
                            <th>Tên Môn Học</th>
                            <th>Số Tín Chỉ</th>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="mh" items="${dsMonHoc}">
                            <tr>
                                <td class="text-center">${mh.id}</td>
                                <td>${mh.maMon}</td>
                                <td>${mh.tenMon}</td>
                                <td class="text-center">${mh.soTinChi}</td>
                                <td class="text-center">
                                    <button type="button" class="btn btn-sm btn-warning me-1" data-bs-toggle="modal"
                                        data-bs-target="#editModal${mh.id}">Sửa</button>
                                    <a href="${pageContext.request.contextPath}/mon-hoc?action=delete&id=${mh.id}"
                                        class="btn btn-sm btn-danger" onclick="return confirm('Xóa môn này?')">Xóa</a>

                                    <!-- Modal Sửa Môn Học -->
                                    <div class="modal fade" id="editModal${mh.id}" tabindex="-1">
                                        <div class="modal-dialog">
                                            <div class="modal-content text-start">
                                                <form action="${pageContext.request.contextPath}/mon-hoc" method="post">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title">Cập Nhật Môn Học</h5>
                                                        <button type="button" class="btn-close"
                                                            data-bs-dismiss="modal"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <input type="hidden" name="id" value="${mh.id}">
                                                        <div class="mb-3">
                                                            <label class="form-label">Mã Môn</label>
                                                            <input type="text" name="maMon" class="form-control"
                                                                value="${mh.maMon}" required>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Tên Môn Học</label>
                                                            <input type="text" name="tenMon" class="form-control"
                                                                value="${mh.tenMon}" required>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Số Tín Chỉ</label>
                                                            <input type="number" name="soTinChi" class="form-control"
                                                                value="${mh.soTinChi}" required>
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
                                    href="${pageContext.request.contextPath}/mon-hoc?page=${i}&keyword=${keyword}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </body>

        </html>