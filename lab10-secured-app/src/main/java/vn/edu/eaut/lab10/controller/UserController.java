package vn.edu.eaut.lab10.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.eaut.lab10.model.Role;
import vn.edu.eaut.lab10.model.User;
import vn.edu.eaut.lab10.repository.UserRepository;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class UserController extends HttpServlet {
    private final UserRepository userRepository = new UserRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "list";

        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            default:
                listUsers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String idStr = request.getParameter("id");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String roleStr = request.getParameter("role");
        boolean active = "on".equals(request.getParameter("active")) || "true".equals(request.getParameter("active"));

        User user;
        if (idStr != null && !idStr.isEmpty()) {
            user = userRepository.findById(Integer.parseInt(idStr));
            if (user != null) {
                user.setFullName(fullName);
                user.setRole(Role.valueOf(roleStr));
                user.setActive(active);
                if (password != null && !password.trim().isEmpty()) {
                    user.setPassword(password);
                }
            }
        } else {
            user = new User(email, password, fullName, Role.valueOf(roleStr), active);
        }

        userRepository.save(user);
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<User> userList = userRepository.search(keyword);
        request.setAttribute("users", userList);
        request.setAttribute("keyword", keyword);
        request.getRequestDispatcher("/admin/manage.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userRepository.findById(id);
        request.setAttribute("editUser", user);
        listUsers(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userRepository.delete(id);
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}