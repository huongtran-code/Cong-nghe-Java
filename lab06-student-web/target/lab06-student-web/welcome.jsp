<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Bảng Điều Khiển Hệ Thống</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
        .dashboard-header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 25px;
            padding-bottom: 15px;
            border-bottom: 1px solid rgba(255, 255, 255, 0.4);
        }
        
        .dashboard-title {
            display: flex;
            align-items: center;
            gap: 12px;
            font-size: 1.8rem;
            color: var(--text-main);
            margin-bottom: 0;
            text-align: left;
        }

        .user-hero-card {
            background: linear-gradient(135deg, rgba(255, 255, 255, 0.85) 0%, rgba(238, 242, 255, 0.75) 100%);
            border: 1px solid rgba(255, 255, 255, 0.8);
            border-radius: 16px;
            padding: 24px;
            margin-bottom: 30px;
            box-shadow: 0 10px 25px -5px rgba(99, 102, 241, 0.1);
            display: flex;
            align-items: center;
            justify-content: space-between;
            flex-wrap: wrap;
            gap: 20px;
        }

        .user-profile {
            display: flex;
            align-items: center;
            gap: 16px;
        }

        .user-avatar {
            width: 56px;
            height: 56px;
            border-radius: 14px;
            background: linear-gradient(135deg, #6366f1 0%, #a855f7 100%);
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.8rem;
            box-shadow: 0 4px 12px rgba(99, 102, 241, 0.3);
        }

        .user-details h3 {
            margin: 0 0 4px 0;
            font-size: 1.25rem;
            text-align: left;
            color: var(--text-main);
        }

        .user-meta {
            display: flex;
            align-items: center;
            gap: 10px;
            font-size: 0.9rem;
            color: var(--text-muted);
        }

        .badge-role {
            padding: 4px 12px;
            border-radius: 20px;
            font-weight: 700;
            font-size: 0.8rem;
            letter-spacing: 0.5px;
        }

        .badge-role-admin {
            background: linear-gradient(135deg, #10b981 0%, #059669 100%);
            color: white;
            box-shadow: 0 2px 8px rgba(16, 185, 129, 0.3);
        }

        .badge-role-user {
            background: linear-gradient(135deg, #6366f1 0%, #4f46e5 100%);
            color: white;
            box-shadow: 0 2px 8px rgba(99, 102, 241, 0.3);
        }

        .login-time-badge {
            background: rgba(255, 255, 255, 0.8);
            border: 1px solid rgba(0, 0, 0, 0.05);
            padding: 8px 16px;
            border-radius: 10px;
            font-size: 0.88rem;
            font-weight: 500;
            display: flex;
            align-items: center;
            gap: 8px;
            color: var(--text-main);
        }

        .pulse-dot {
            width: 8px;
            height: 8px;
            background-color: #10b981;
            border-radius: 50%;
            box-shadow: 0 0 0 0 rgba(16, 185, 129, 0.7);
            animation: pulse 1.6s infinite;
        }

        @keyframes pulse {
            0% { transform: scale(0.95); box-shadow: 0 0 0 0 rgba(16, 185, 129, 0.7); }
            70% { transform: scale(1); box-shadow: 0 0 0 6px rgba(16, 185, 129, 0); }
            100% { transform: scale(0.95); box-shadow: 0 0 0 0 rgba(16, 185, 129, 0); }
        }

        /* Stats Grid */
        .stats-section-title {
            font-size: 1.1rem;
            font-weight: 600;
            color: var(--text-muted);
            margin-bottom: 16px;
            text-align: left;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
            gap: 20px;
            margin-bottom: 35px;
        }

        .stat-card {
            background: rgba(255, 255, 255, 0.8);
            border: 1px solid rgba(255, 255, 255, 0.9);
            border-radius: 16px;
            padding: 22px;
            transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
            position: relative;
            overflow: hidden;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.03);
        }

        .stat-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 12px 25px rgba(99, 102, 241, 0.15);
            background: rgba(255, 255, 255, 0.95);
        }

        .stat-card-header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 12px;
        }

        .stat-icon {
            width: 44px;
            height: 44px;
            border-radius: 12px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.4rem;
        }

        .icon-purple {
            background: rgba(99, 102, 241, 0.12);
            color: #6366f1;
        }

        .icon-emerald {
            background: rgba(16, 185, 129, 0.12);
            color: #10b981;
        }

        .icon-amber {
            background: rgba(245, 158, 11, 0.12);
            color: #f59e0b;
        }

        .stat-value {
            font-size: 2.4rem;
            font-weight: 700;
            color: var(--text-main);
            line-height: 1.1;
        }

        .stat-title {
            font-size: 0.9rem;
            color: var(--text-muted);
            font-weight: 500;
            margin-top: 4px;
        }

        /* Quick Action Cards Grid */
        .actions-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
            gap: 16px;
        }

        .action-card {
            background: rgba(255, 255, 255, 0.75);
            border: 1px solid rgba(255, 255, 255, 0.8);
            border-radius: 14px;
            padding: 20px;
            display: flex;
            align-items: center;
            gap: 16px;
            text-decoration: none;
            color: var(--text-main);
            transition: all 0.3s ease;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.02);
        }

        .action-card:hover {
            background: white;
            transform: translateY(-3px);
            box-shadow: 0 10px 20px rgba(99, 102, 241, 0.12);
            text-decoration: none;
        }

        .action-icon {
            font-size: 1.8rem;
            width: 48px;
            height: 48px;
            border-radius: 12px;
            background: rgba(99, 102, 241, 0.08);
            display: flex;
            align-items: center;
            justify-content: center;
            flex-shrink: 0;
        }

        .action-card:hover .action-icon {
            background: var(--primary-color);
            color: white;
        }

        .action-info h4 {
            margin: 0 0 4px 0;
            font-size: 1rem;
            font-weight: 600;
            color: var(--text-main);
            text-align: left;
        }

        .action-info p {
            margin: 0;
            font-size: 0.8rem;
            color: var(--text-muted);
            line-height: 1.3;
        }

        .action-card-danger:hover {
            border-color: rgba(239, 68, 68, 0.3);
        }
        
        .action-card-danger:hover .action-icon {
            background: var(--danger-color);
            color: white;
        }

        .action-card-danger h4 {
            color: var(--danger-color);
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Dashboard Header -->
        <div class="dashboard-header">
            <h2 class="dashboard-title">
                📊 Bảng Điều Khiển Hệ Thống
            </h2>
            <a href="${pageContext.request.contextPath}/students" class="btn">🚀 Vào quản lý sinh viên</a>
        </div>

        <!-- User Profile Hero Card -->
        <div class="user-hero-card">
            <div class="user-profile">
                <div class="user-avatar">
                    ${sessionScope.role == 'ADMIN' ? '🛡️' : '👤'}
                </div>
                <div class="user-details">
                    <h3>Xin chào, ${sessionScope.fullName != null ? sessionScope.fullName : sessionScope.username}!</h3>
                    <div class="user-meta">
                        <span>Tài khoản: <code>${sessionScope.username}</code></span>
                        <span>•</span>
                        <span class="badge-role ${sessionScope.role == 'ADMIN' ? 'badge-role-admin' : 'badge-role-user'}">
                            ${sessionScope.role}
                        </span>
                    </div>
                </div>
            </div>

            <div class="login-time-badge">
                <div class="pulse-dot"></div>
                <span>Đăng nhập: <strong>${sessionScope.loginTime != null ? sessionScope.loginTime : 'Vừa xong'}</strong></span>
            </div>
        </div>

        <!-- Statistics Grid Section -->
        <div class="stats-section-title">📈 Thống Kê Tổng Quan</div>
        <div class="stats-grid">
            <!-- Card 1: Total Students -->
            <div class="stat-card">
                <div class="stat-card-header">
                    <span class="stat-title">TỔNG SỐ SINH VIÊN</span>
                    <div class="stat-icon icon-purple">🎓</div>
                </div>
                <div class="stat-value">${totalStudents != null ? totalStudents : 0}</div>
            </div>

            <!-- Cards for Each Class -->
            <c:forEach var="entry" items="${classCounts}" varStatus="status">
                <div class="stat-card">
                    <div class="stat-card-header">
                        <span class="stat-title">LỚP ${entry.key}</span>
                        <div class="stat-icon ${status.index % 2 == 0 ? 'icon-emerald' : 'icon-amber'}">🏫</div>
                    </div>
                    <div class="stat-value">
                        ${entry.value} <span style="font-size: 1rem; color: var(--text-muted); font-weight: 500;">SV</span>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Quick Access Actions Grid -->
        <div class="stats-section-title">⚡ Thao Tác Nhanh</div>
        <div class="actions-grid">
            <a href="${pageContext.request.contextPath}/students" class="action-card">
                <div class="action-icon">👥</div>
                <div class="action-info">
                    <h4>Danh Sách Sinh Viên</h4>
                    <p>Xem, tìm kiếm, thêm, sửa và xóa thông tin sinh viên</p>
                </div>
            </a>

            <c:if test="${sessionScope.role == 'ADMIN'}">
                <a href="${pageContext.request.contextPath}/users" class="action-card">
                    <div class="action-icon">⚙️</div>
                    <div class="action-info">
                        <h4>Phân Quyền Người Dùng</h4>
                        <p>Thăng cấp ADMIN hoặc giáng cấp USER trực tiếp</p>
                    </div>
                </a>
            </c:if>

            <a href="${pageContext.request.contextPath}/switch-role" class="action-card">
                <div class="action-icon">🔄</div>
                <div class="action-info">
                    <h4>Đổi Vai Trò Linh Hoạt</h4>
                    <p>Chuyển đổi sang quyền ${sessionScope.role == 'ADMIN' ? 'USER' : 'ADMIN'}</p>
                </div>
            </a>

            <a href="${pageContext.request.contextPath}/logout" class="action-card action-card-danger">
                <div class="action-icon" style="background: rgba(239, 68, 68, 0.1); color: var(--danger-color);">🚪</div>
                <div class="action-info">
                    <h4>Đăng Xuất</h4>
                    <p>Kết thúc phiên làm việc an toàn</p>
                </div>
            </a>
        </div>
    </div>
</body>
</html>
