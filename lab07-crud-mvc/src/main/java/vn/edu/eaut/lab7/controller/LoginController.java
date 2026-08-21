package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if ("/logout".equals(servletPath)) {
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            resp.sendRedirect(req.getContextPath() + "/login.jsp?message=logout_success");
            return;
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String u = req.getParameter("username");
        String p = req.getParameter("password");

        if ("admin".equals(u) && "123456".equals(p)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("username", u);
            session.setAttribute("role", "ADMIN");
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else if (u != null && !u.isBlank() && p != null && !p.isBlank()) {
            HttpSession session = req.getSession(true);
            session.setAttribute("username", u);
            session.setAttribute("role", "USER");
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
