<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Quản Lý Người Dùng - ADMIN</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        </head>

        <body>
            <div class="container">
                <h2>Khu vực Quản trị - Quản lý người dùng</h2>
                <p>Chỉ dành riêng cho người dùng có quyền ADMIN.</p>

                <!-- FORM THÊM / SỬA USER -->
                <div class="form-box">
                    <h3>${not empty editUser ? 'Cập nhật Người Dùng' : 'Thêm Người Dùng Mới'}</h3>
                    <form action="${pageContext.request.contextPath}/admin/users" method="post">
                        <c:if test="${not empty editUser}">
                            <input type="hidden" name="id" value="${editUser.id}">
                        </c:if>

                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" name="email" value="${editUser.email}" ${not empty editUser
                                ? 'readonly style="background:#e9ecef"' : 'required' }>
                        </div>

                        <div class="form-group">
                            <label>Mật khẩu ${not empty editUser ? '<small style="font-weight:normal">(Để trống nếu
                                    không đổi)</small>' : ''}</label>
                            <input type="password" name="password" ${empty editUser ? 'required' : '' }>
                        </div>

                        <div class="form-group">
                            <label>Họ và Tên</label>
                            <input type="text" name="fullName" value="${editUser.fullName}" required>
                        </div>

                        <div class="form-group">
                            <label>Vai trò</label>
                            <select name="role">
                                <option value="USER" ${editUser.role=='USER' ? 'selected' : '' }>USER</option>
                                <option value="STAFF" ${editUser.role=='STAFF' ? 'selected' : '' }>STAFF</option>
                                <option value="ADMIN" ${editUser.role=='ADMIN' ? 'selected' : '' }>ADMIN</option>
                            </select>
                        </div>

                        <div class="form-group" style="display: flex; align-items: center; gap: 8px;">
                            <input type="checkbox" name="active" id="active" ${empty editUser || editUser.active
                                ? 'checked' : '' } style="width: auto;">
                            <label for="active" style="margin-bottom: 0;">Kích hoạt tài khoản</label>
                        </div>

                        <button type="submit">${not empty editUser ? 'Lưu thay đổi' : 'Thêm mới'}</button>
                        <c:if test="${not empty editUser}">
                            <a href="${pageContext.request.contextPath}/admin/users" class="btn"
                                style="background: #6c757d; color: white;">Hủy bỏ</a>
                        </c:if>
                    </form>
                </div>

                <!-- TIM KIEM -->
                <div class="form-box" style="padding: 15px;">
                    <form action="${pageContext.request.contextPath}/admin/users" method="get"
                        style="display: flex; gap: 10px;">
                        <input type="text" name="keyword" value="${keyword}"
                            placeholder="Nhập email hoặc họ tên để tìm...">
                        <button type="submit" style="white-space: nowrap;">Tìm kiếm</button>
                        <a href="${pageContext.request.contextPath}/admin/users" class="btn"
                            style="background: #e9ecef; color: #333; white-space: nowrap;">Xóa lọc</a>
                    </form>
                </div>

                <!-- BANG DANH SACH -->
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Họ và Tên</th>
                            <th>Email</th>
                            <th>Vai trò</th>
                            <th>Trạng thái</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="u" items="${users}">
                            <tr>
                                <td>${u.id}</td>
                                <td>${u.fullName}</td>
                                <td>${u.email}</td>
                                <td><b>${u.role}</b></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${u.active}">
                                            <span class="active">Hoạt động</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="inactive">Bị khóa</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/users?action=edit&id=${u.id}"
                                        class="btn btn-edit">Sửa</a>
                                    <c:if test="${u.email != currentUser.email}">
                                        <a href="${pageContext.request.contextPath}/admin/users?action=delete&id=${u.id}"
                                            class="btn btn-delete"
                                            onclick="return confirm('Bạn có chắc muốn xóa tài khoản này?');">Xóa</a>
                                    </c:if>
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