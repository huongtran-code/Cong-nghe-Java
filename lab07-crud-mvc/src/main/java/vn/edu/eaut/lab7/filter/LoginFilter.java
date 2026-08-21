package vn.edu.eaut.lab7.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter("/admin/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            req.setAttribute("error", "Bạn cần đăng nhập tài khoản Quản trị để thực hiện thao tác này!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        chain.doFilter(request, response);
    }
}
