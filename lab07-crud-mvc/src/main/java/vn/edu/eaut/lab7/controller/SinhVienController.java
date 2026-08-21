package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.eaut.lab7.model.SinhVien;
import vn.edu.eaut.lab7.repository.SinhVienRepository;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/sinh-vien", "/admin/sinh-vien"})
public class SinhVienController extends HttpServlet {
    private final SinhVienRepository repo = new SinhVienRepository();
    private static final int PAGE_SIZE = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/views/sinhvien/form.jsp").forward(req, resp);
                break;

            case "edit":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    SinhVien sv = repo.findById(id);
                    req.setAttribute("sv", sv);
                    req.getRequestDispatcher("/views/sinhvien/form.jsp").forward(req, resp);
                } catch (NumberFormatException e) {
                    resp.sendRedirect(req.getContextPath() + "/sinh-vien");
                }
                break;

            case "detail":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    SinhVien sv = repo.findById(id);
                    req.setAttribute("sv", sv);
                    req.getRequestDispatcher("/views/sinhvien/detail.jsp").forward(req, resp);
                } catch (NumberFormatException e) {
                    resp.sendRedirect(req.getContextPath() + "/sinh-vien");
                }
                break;

            case "delete":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    repo.delete(id);
                } catch (NumberFormatException ignored) {}
                resp.sendRedirect(req.getContextPath() + "/sinh-vien");
                break;

            case "list":
            default:
                String keyword = req.getParameter("keyword");
                int page = 1;
                try {
                    String pageStr = req.getParameter("page");
                    if (pageStr != null && !pageStr.isBlank()) {
                        page = Integer.parseInt(pageStr);
                    }
                } catch (NumberFormatException ignored) {}

                List<SinhVien> list = repo.searchPaginated(keyword, page, PAGE_SIZE);
                int totalPages = repo.getTotalPages(keyword, PAGE_SIZE);

                req.setAttribute("dsSinhVien", list);
                req.setAttribute("keyword", keyword);
                req.setAttribute("currentPage", page);
                req.setAttribute("totalPages", Math.max(1, totalPages));
                req.getRequestDispatcher("/views/sinhvien/list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String maSinhVien = req.getParameter("maSinhVien");
        String hoTen = req.getParameter("hoTen");
        String email = req.getParameter("email");
        String lop = req.getParameter("lop");

        int id = 0;
        if (idStr != null && !idStr.isBlank()) {
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException ignored) {}
        }

        SinhVien sv = new SinhVien(id, maSinhVien, hoTen, email, lop);
        if (id == 0) {
            repo.add(sv);
        } else {
            repo.update(sv);
        }

        resp.sendRedirect(req.getContextPath() + "/sinh-vien");
    }
}
