package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.eaut.lab7.model.LopHoc;
import vn.edu.eaut.lab7.repository.LopHocRepository;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/lop-hoc", "/admin/lop-hoc"})
public class LopHocController extends HttpServlet {
    private final LopHocRepository repo = new LopHocRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/views/lophoc/form.jsp").forward(req, resp);
                break;

            case "edit":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    LopHoc lh = repo.findById(id);
                    req.setAttribute("lh", lh);
                    req.getRequestDispatcher("/views/lophoc/form.jsp").forward(req, resp);
                } catch (NumberFormatException e) {
                    resp.sendRedirect(req.getContextPath() + "/lop-hoc");
                }
                break;

            case "detail":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    LopHoc lh = repo.findById(id);
                    req.setAttribute("lh", lh);
                    req.getRequestDispatcher("/views/lophoc/detail.jsp").forward(req, resp);
                } catch (NumberFormatException e) {
                    resp.sendRedirect(req.getContextPath() + "/lop-hoc");
                }
                break;

            case "delete":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    repo.delete(id);
                } catch (NumberFormatException ignored) {}
                resp.sendRedirect(req.getContextPath() + "/lop-hoc");
                break;

            case "list":
            default:
                String keyword = req.getParameter("keyword");
                List<LopHoc> list = repo.search(keyword);
                req.setAttribute("dsLopHoc", list);
                req.setAttribute("keyword", keyword);
                req.getRequestDispatcher("/views/lophoc/list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String maLop = req.getParameter("maLop");
        String tenLop = req.getParameter("tenLop");
        String coVanHocTap = req.getParameter("coVanHocTap");
        String soLuongStr = req.getParameter("soLuongSinhVien");

        int id = 0;
        if (idStr != null && !idStr.isBlank()) {
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException ignored) {}
        }

        int soLuong = 0;
        if (soLuongStr != null && !soLuongStr.isBlank()) {
            try {
                soLuong = Integer.parseInt(soLuongStr);
            } catch (NumberFormatException ignored) {}
        }

        LopHoc lh = new LopHoc(id, maLop, tenLop, coVanHocTap, soLuong);
        if (id == 0) {
            repo.add(lh);
        } else {
            repo.update(lh);
        }

        resp.sendRedirect(req.getContextPath() + "/lop-hoc");
    }
}
