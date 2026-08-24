package vn.edu.eaut.lab9.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.eaut.lab9.model.LopHoc;
import vn.edu.eaut.lab9.repository.LopHocRepository;
import java.io.IOException;

@WebServlet("/lop-hoc")
public class LopHocController extends HttpServlet {
    private final LopHocRepository lopRepo = new LopHocRepository();
    private static final int PAGE_SIZE = 5; // Số dòng trên 1 trang

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("delete".equals(action)) {
            lopRepo.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/lop-hoc?message=Xoa thanh cong");
            return;
        }

        String keyword = req.getParameter("keyword");
        int page = 1;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignored) {
        }

        long totalItems = lopRepo.countTotal(keyword);
        int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);

        req.setAttribute("dsLop", lopRepo.findByPage(keyword, page, PAGE_SIZE));
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("keyword", keyword != null ? keyword : "");

        req.getRequestDispatcher("/views/lophoc/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String idStr = req.getParameter("id");
        String tenLop = req.getParameter("tenLop");

        LopHoc lop = new LopHoc();
        if (idStr != null && !idStr.isEmpty()) {
            lop.setId(Integer.parseInt(idStr));
        }
        lop.setTenLop(tenLop);
        lopRepo.save(lop);
        resp.sendRedirect(req.getContextPath() + "/lop-hoc?message=Thanh cong");
    }
}