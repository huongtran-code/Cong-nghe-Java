package vn.edu.eaut.lab6.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/welcome", "/students", "/student-form.jsp", "/student-edit-form.jsp", "/welcome.jsp", "/students/delete", "/students/update", "/switch-role", "/users", "/users/change-role", "/user-list.jsp"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("AuthFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("username") != null;

        if (loggedIn) {
            String role = (String) session.getAttribute("role");
            String uri = req.getRequestURI();
            String method = req.getMethod();

            // Kiểm tra các chức năng quản trị dành riêng cho ADMIN
            boolean isAdminAction = uri.contains("/student-form.jsp") ||
                                    uri.contains("/student-edit-form.jsp") ||
                                    uri.contains("/students/delete") ||
                                    uri.contains("/students/update") ||
                                    uri.contains("/users") ||
                                    uri.contains("/user-list.jsp") ||
                                    (uri.endsWith("/students") && "POST".equalsIgnoreCase(method));

            if (isAdminAction && !"ADMIN".equals(role)) {
                resp.sendRedirect(req.getContextPath() + "/403.jsp");
                return;
            }

            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {
        System.out.println("AuthFilter destroyed");
    }
}
