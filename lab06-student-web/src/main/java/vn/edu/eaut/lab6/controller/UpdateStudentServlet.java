package vn.edu.eaut.lab6.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.eaut.lab6.model.Student;
import vn.edu.eaut.lab6.store.StudentStore;

import java.io.IOException;

@WebServlet("/students/update")
public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Student student = StudentStore.findById(id);
        if (student != null) {
            request.setAttribute("student", student);
            request.getRequestDispatcher("/student-edit-form.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/students");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id"); // read-only from form
        String name = request.getParameter("name");
        String className = request.getParameter("className");
        String email = request.getParameter("email");

        Student updatedStudent = new Student(id, name, className, email);
        StudentStore.update(updatedStudent);

        response.sendRedirect(request.getContextPath() + "/students");
    }
}
