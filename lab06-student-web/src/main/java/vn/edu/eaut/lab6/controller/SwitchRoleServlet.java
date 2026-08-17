package vn.edu.eaut.lab6.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/switch-role")
public class SwitchRoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            String targetRole = request.getParameter("role");
            String currentRole = (String) session.getAttribute("role");
            
            if (targetRole != null && (targetRole.equalsIgnoreCase("ADMIN") || targetRole.equalsIgnoreCase("USER"))) {
                session.setAttribute("role", targetRole.toUpperCase());
            } else {
                // Tự động đảo vai trò nếu không truyền tham số role
                if ("ADMIN".equals(currentRole)) {
                    session.setAttribute("role", "USER");
                } else {
                    session.setAttribute("role", "ADMIN");
                }
            }
        }
        
        // Quay lại trang trước đó hoặc trang danh sách
        String referer = request.getHeader("Referer");
        if (referer != null && !referer.isEmpty() && !referer.contains("/403.jsp")) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect(request.getContextPath() + "/students");
        }
    }
}
