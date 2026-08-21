package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.eaut.lab7.model.SanPham;
import vn.edu.eaut.lab7.repository.SanPhamRepository;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/san-pham", "/admin/san-pham"})
public class SanPhamController extends HttpServlet {
    private final SanPhamRepository repo = new SanPhamRepository();
    private static final int PAGE_SIZE = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/views/sanpham/form.jsp").forward(req, resp);
                break;

            case "edit":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    SanPham sp = repo.findById(id);
                    req.setAttribute("sp", sp);
                    req.getRequestDispatcher("/views/sanpham/form.jsp").forward(req, resp);
                } catch (NumberFormatException e) {
                    resp.sendRedirect(req.getContextPath() + "/san-pham");
                }
                break;

            case "detail":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    SanPham sp = repo.findById(id);
                    req.setAttribute("sp", sp);
                    req.getRequestDispatcher("/views/sanpham/detail.jsp").forward(req, resp);
                } catch (NumberFormatException e) {
                    resp.sendRedirect(req.getContextPath() + "/san-pham");
                }
                break;

            case "delete":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    repo.delete(id);
                } catch (NumberFormatException ignored) {}
                resp.sendRedirect(req.getContextPath() + "/san-pham");
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

                List<SanPham> list = repo.searchPaginated(keyword, page, PAGE_SIZE);
                int totalPages = repo.getTotalPages(keyword, PAGE_SIZE);

                req.setAttribute("dsSanPham", list);
                req.setAttribute("keyword", keyword);
                req.setAttribute("currentPage", page);
                req.setAttribute("totalPages", Math.max(1, totalPages));
                req.getRequestDispatcher("/views/sanpham/list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String maSanPham = req.getParameter("maSanPham");
        String tenSanPham = req.getParameter("tenSanPham");
        String moTa = req.getParameter("moTa");
        String giaStr = req.getParameter("gia");
        String soLuongStr = req.getParameter("soLuong");

        int id = 0;
        if (idStr != null && !idStr.isBlank()) {
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException ignored) {}
        }

        double gia = 0;
        int soLuong = -1;

        try {
            gia = Double.parseDouble(giaStr);
        } catch (Exception ignored) {}

        try {
            soLuong = Integer.parseInt(soLuongStr);
        } catch (Exception ignored) {}

        // Validation for Bài 7: giá > 0 và số lượng >= 0
        if (gia <= 0 || soLuong < 0) {
            SanPham sp = new SanPham(id, maSanPham, tenSanPham, moTa, gia, soLuong);
            req.setAttribute("sp", sp);
            req.setAttribute("error", "Lỗi dữ liệu: Giá sản phẩm phải lớn hơn 0 và Số lượng phải lớn hơn hoặc bằng 0!");
            req.getRequestDispatcher("/views/sanpham/form.jsp").forward(req, resp);
            return;
        }

        SanPham sp = new SanPham(id, maSanPham, tenSanPham, moTa, gia, soLuong);
        if (id == 0) {
            repo.add(sp);
        } else {
            repo.update(sp);
        }

        resp.sendRedirect(req.getContextPath() + "/san-pham");
    }
}
