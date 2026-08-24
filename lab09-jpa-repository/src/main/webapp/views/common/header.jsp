<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
        <div class="container">
            <a class="navbar-brand fw-bold" href="#">LAB09</a>
            <div class="navbar-nav">
                <a class="nav-link text-white" href="${pageContext.request.contextPath}/sinh-vien">Quản lý Sinh Viên</a>
                <a class="nav-link text-white" href="${pageContext.request.contextPath}/lop-hoc">Quản lý Lớp Học</a>
                <a class="nav-link text-white" href="${pageContext.request.contextPath}/mon-hoc">Quản lý Môn Học</a>
            </div>
        </div>
    </nav>