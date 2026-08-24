<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Thêm Sinh Viên</title>
            <%@ include file="../common/header.jsp" %>
        </head>

        <body class="bg-light">
            <div class="container col-md-6">
                <div class="card shadow">
                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0">Thêm Mới Sinh Viên</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>
                        <form action="${pageContext.request.contextPath}/sinh-vien" method="post">
                            <div class="mb-3">
                                <label class="form-label">Mã Sinh Viên</label>
                                <input type="text" name="maSinhVien" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Họ và Tên</label>
                                <input type="text" name="hoTen" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Email</label>
                                <input type="email" name="email" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Lớp Học</label>
                                <select name="lopId" class="form-select" required>
                                    <c:forEach var="lop" items="${dsLop}">
                                        <option value="${lop.id}">${lop.tenLop}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-success">Lưu Sinh Viên</button>
                            <a href="${pageContext.request.contextPath}/sinh-vien" class="btn btn-secondary">Hủy</a>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>