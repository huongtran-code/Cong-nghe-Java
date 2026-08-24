package vn.edu.eaut.lab9.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.eaut.lab9.model.MonHoc;
import vn.edu.eaut.lab9.repository.MonHocRepository;
import java.io.IOException;

@WebServlet("/mon-hoc")
public class MonHocController extends HttpServlet {
    private final MonHocRepository monRepo = new MonHocRepository();
    private static final int PAGE_SIZE = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("delete".equals(action)) {
            monRepo.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/mon-hoc?message=Xoa thanh cong");
            return;
        }

        String keyword = req.getParameter("keyword");
        int page = 1;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignored) {
        }

        long totalItems = monRepo.countTotal(keyword);
        int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);

        req.setAttribute("dsMonHoc", monRepo.findByPage(keyword, page, PAGE_SIZE));
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("keyword", keyword != null ? keyword : "");

        req.getRequestDispatcher("/views/monhoc/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String idStr = req.getParameter("id");

        MonHoc mh = new MonHoc();
        if (idStr != null && !idStr.isEmpty()) {
            mh.setId(Integer.parseInt(idStr));
        }
        mh.setMaMon(req.getParameter("maMon"));
        mh.setTenMon(req.getParameter("tenMon"));
        mh.setSoTinChi(Integer.parseInt(req.getParameter("soTinChi")));
        monRepo.save(mh);
        resp.sendRedirect(req.getContextPath() + "/mon-hoc?message=Thanh cong");
    }
}