package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.eaut.lab7.model.Sach;
import vn.edu.eaut.lab7.repository.SachRepository;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/sach", "/admin/sach"})
public class SachController extends HttpServlet {
    private final SachRepository repo = new SachRepository();
    private static final int PAGE_SIZE = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/views/sach/form.jsp").forward(req, resp);
                break;

            case "edit":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    Sach sach = repo.findById(id);
                    req.setAttribute("sach", sach);
                    req.getRequestDispatcher("/views/sach/form.jsp").forward(req, resp);
                } catch (NumberFormatException e) {
                    resp.sendRedirect(req.getContextPath() + "/sach");
                }
                break;

            case "detail":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    Sach sach = repo.findById(id);
                    req.setAttribute("sach", sach);
                    req.getRequestDispatcher("/views/sach/detail.jsp").forward(req, resp);
                } catch (NumberFormatException e) {
                    resp.sendRedirect(req.getContextPath() + "/sach");
                }
                break;

            case "delete":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    repo.delete(id);
                } catch (NumberFormatException ignored) {}
                resp.sendRedirect(req.getContextPath() + "/sach");
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

                List<Sach> list = repo.searchPaginated(keyword, page, PAGE_SIZE);
                int totalPages = repo.getTotalPages(keyword, PAGE_SIZE);

                req.setAttribute("dsSach", list);
                req.setAttribute("keyword", keyword);
                req.setAttribute("currentPage", page);
                req.setAttribute("totalPages", Math.max(1, totalPages));
                req.getRequestDispatcher("/views/sach/list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String maSach = req.getParameter("maSach");
        String tenSach = req.getParameter("tenSach");
        String tacGia = req.getParameter("tacGia");
        String nhaXuatBan = req.getParameter("nhaXuatBan");
        String namStr = req.getParameter("namXuatBan");

        int id = 0;
        if (idStr != null && !idStr.isBlank()) {
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException ignored) {}
        }

        int namXuatBan = 2024;
        if (namStr != null && !namStr.isBlank()) {
            try {
                namXuatBan = Integer.parseInt(namStr);
            } catch (NumberFormatException ignored) {}
        }

        Sach s = new Sach(id, maSach, tenSach, tacGia, nhaXuatBan, namXuatBan);
        if (id == 0) {
            repo.add(s);
        } else {
            repo.update(s);
        }

        resp.sendRedirect(req.getContextPath() + "/sach");
    }
}
