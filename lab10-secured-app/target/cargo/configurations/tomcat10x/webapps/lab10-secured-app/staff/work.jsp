<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Nghiệp Vụ Sản Phẩm - STAFF</title>
                <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
            </head>

            <body>
                <div class="container">
                    <h2>Khu vực Nghiệp vụ - Quản lý Sản phẩm</h2>
                    <p>Dành cho các tài khoản có quyền <b>STAFF</b> hoặc <b>ADMIN</b>.</p>

                    <!-- FORM THEM/SUA SAN PHAM -->
                    <div class="form-box">
                        <h3>${not empty editProduct ? 'Cập nhật Sản phẩm' : 'Thêm Sản phẩm mới'}</h3>
                        <form action="${pageContext.request.contextPath}/staff/products" method="post">
                            <c:if test="${not empty editProduct}">
                                <input type="hidden" name="id" value="${editProduct.id}">
                            </c:if>

                            <div class="form-group">
                                <label>Tên sản phẩm</label>
                                <input type="text" name="name" value="${editProduct.name}" required>
                            </div>

                            <div class="form-group">
                                <label>Đơn giá (VNĐ)</label>
                                <input type="number" step="1000" name="price" value="${editProduct.price}" required>
                            </div>

                            <div class="form-group">
                                <label>Số lượng trong kho</label>
                                <input type="number" name="quantity" value="${editProduct.quantity}" required>
                            </div>

                            <button type="submit">${not empty editProduct ? 'Lưu cập nhật' : 'Thêm mới'}</button>
                            <c:if test="${not empty editProduct}">
                                <a href="${pageContext.request.contextPath}/staff/products" class="btn"
                                    style="background: #6c757d; color: white;">Hủy</a>
                            </c:if>
                        </form>
                    </div>

                    <!-- BANG DANH SACH SAN PHAM -->
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Tên sản phẩm</th>
                                <th>Đơn giá</th>
                                <th>Số lượng</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${products}">
                                <tr>
                                    <td>${p.id}</td>
                                    <td><b>${p.name}</b></td>
                                    <td>
                                        <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="VNĐ" />
                                    </td>
                                    <td>${p.quantity}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/staff/products?action=edit&id=${p.id}"
                                            class="btn btn-edit">Sửa</a>
                                        <a href="${pageContext.request.contextPath}/staff/products?action=delete&id=${p.id}"
                                            class="btn btn-delete"
                                            onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này?');">Xóa</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <br>
                    <a href="${pageContext.request.contextPath}/dashboard.jsp" class="btn-link">← Quay lại Dashboard</a>
                </div>
            </body>

            </html>