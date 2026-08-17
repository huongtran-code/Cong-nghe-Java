package vn.edu.eaut.lab6.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.eaut.lab6.store.UserStore;

import java.io.IOException;

@WebServlet("/users/change-role")
public class ChangeRoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String targetUsername = request.getParameter("username");
        String newRole = request.getParameter("newRole");

        if (targetUsername != null && newRole != null) {
            UserStore.updateRole(targetUsername, newRole);

            // Nếu admin tự đổi quyền của chính mình thì cập nhật Session lập tức
            HttpSession session = request.getSession(false);
            if (session != null) {
                String currentLoggedInUser = (String) session.getAttribute("username");
                if (targetUsername.equalsIgnoreCase(currentLoggedInUser)) {
                    session.setAttribute("role", newRole.toUpperCase());
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/users");
    }
}
