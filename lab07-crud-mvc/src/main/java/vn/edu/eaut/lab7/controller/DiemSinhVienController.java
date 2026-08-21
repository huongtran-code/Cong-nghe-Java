package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.eaut.lab7.model.DiemSinhVien;
import vn.edu.eaut.lab7.repository.DiemSinhVienRepository;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/diem-sinh-vien", "/admin/diem-sinh-vien"})
public class DiemSinhVienController extends HttpServlet {
    private final DiemSinhVienRepository repo = new DiemSinhVienRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/views/diem/form.jsp").forward(req, resp);
                break;

            case "edit":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    DiemSinhVien diem = repo.findById(id);
                    req.setAttribute("diem", diem);
                    req.getRequestDispatcher("/views/diem/form.jsp").forward(req, resp);
                } catch (NumberFormatException e) {
                    resp.sendRedirect(req.getContextPath() + "/diem-sinh-vien");
                }
                break;

            case "delete":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    repo.delete(id);
                } catch (NumberFormatException ignored) {}
                resp.sendRedirect(req.getContextPath() + "/diem-sinh-vien");
                break;

            case "list":
            default:
                String keyword = req.getParameter("keyword");
                List<DiemSinhVien> list = repo.search(keyword);
                req.setAttribute("dsDiem", list);
                req.setAttribute("keyword", keyword);
                req.getRequestDispatcher("/views/diem/list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String maSV = req.getParameter("maSV");
        String hoTen = req.getParameter("hoTen");
        String ccStr = req.getParameter("diemChuyenCan");
        String gkStr = req.getParameter("diemGiuaKy");
        String ckStr = req.getParameter("diemCuoiKy");

        int id = 0;
        if (idStr != null && !idStr.isBlank()) {
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException ignored) {}
        }

        double cc = 0, gk = 0, ck = 0;
        try { cc = Double.parseDouble(ccStr); } catch (Exception ignored) {}
        try { gk = Double.parseDouble(gkStr); } catch (Exception ignored) {}
        try { ck = Double.parseDouble(ckStr); } catch (Exception ignored) {}

        DiemSinhVien d = new DiemSinhVien(id, maSV, hoTen, cc, gk, ck);
        if (id == 0) {
            repo.add(d);
        } else {
            repo.update(d);
        }

        resp.sendRedirect(req.getContextPath() + "/diem-sinh-vien");
    }
}
