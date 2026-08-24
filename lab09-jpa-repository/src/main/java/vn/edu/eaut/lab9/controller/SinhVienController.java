package vn.edu.eaut.lab9.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.eaut.lab9.model.LopHoc;
import vn.edu.eaut.lab9.model.SinhVien;
import vn.edu.eaut.lab9.repository.LopHocRepository;
import vn.edu.eaut.lab9.repository.SinhVienRepository;

import java.io.IOException;

@WebServlet("/sinh-vien")
public class SinhVienController extends HttpServlet {
    private final SinhVienRepository svRepo = new SinhVienRepository();
    private final LopHocRepository lopRepo = new LopHocRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = action == null ? "list" : action;

        if ("delete".equals(action)) {
            svRepo.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/sinh-vien?message=Deleted successfully");
            return;
        } else if ("new".equals(action)) {
            req.setAttribute("dsLop", lopRepo.findAll());
            req.getRequestDispatcher("/views/sinhvien/form.jsp").forward(req, resp);
            return;
        }

        String kw = req.getParameter("keyword");
        int page = 1;
        int pageSize = 5;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (Exception ignored) {
        }

        req.setAttribute("dsSinhVien", svRepo.searchAndPaginate(kw, page, pageSize));
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", (int) Math.ceil((double) svRepo.countSearch(kw) / pageSize));
        req.setAttribute("keyword", kw);
        req.getRequestDispatcher("/views/sinhvien/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String maSV = req.getParameter("maSinhVien");
        String hoTen = req.getParameter("hoTen");
        String email = req.getParameter("email");
        Integer lopId = Integer.parseInt(req.getParameter("lopId"));

        try {
            LopHoc lop = lopRepo.findById(lopId);
            SinhVien sv = new SinhVien(maSV, hoTen, email, lop);
            svRepo.save(sv);
            resp.sendRedirect(req.getContextPath() + "/sinh-vien?message=Success");
        } catch (Exception e) {
            req.setAttribute("error", "Lỗi: Mã sinh viên đã tồn tại hoặc dữ liệu không hợp lệ!");
            req.setAttribute("dsLop", lopRepo.findAll());
            req.getRequestDispatcher("/views/sinhvien/form.jsp").forward(req, resp);
        }
    }
}