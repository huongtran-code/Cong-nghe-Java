package vn.edu.eaut.lab6.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.eaut.lab6.model.Student;
import vn.edu.eaut.lab6.store.StudentStore;

import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        // Bai 6: Tim kiem
        String search = request.getParameter("search");
        List<Student> list;
        if (search != null && !search.trim().isEmpty()) {
            list = StudentStore.searchByName(search);
        } else {
            list = StudentStore.findAll();
        }
        
        request.setAttribute("students", list);
        request.setAttribute("search", search);
        request.getRequestDispatcher("/student-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String className = request.getParameter("className");
        String email = request.getParameter("email");

        Student student = new Student(id, name, className, email);
        StudentStore.add(student);

        response.sendRedirect(request.getContextPath() + "/students");
    }
}
