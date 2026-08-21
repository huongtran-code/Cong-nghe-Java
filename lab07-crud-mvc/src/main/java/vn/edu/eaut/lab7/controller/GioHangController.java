package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.eaut.lab7.model.GioHang;
import vn.edu.eaut.lab7.model.SanPham;
import vn.edu.eaut.lab7.repository.SanPhamRepository;
import java.io.IOException;

@WebServlet("/gio-hang")
public class GioHangController extends HttpServlet {
    private final SanPhamRepository spRepo = new SanPhamRepository();

    private GioHang getGioHang(HttpSession session) {
        GioHang cart = (GioHang) session.getAttribute("gioHang");
        if (cart == null) {
            cart = new GioHang();
            session.setAttribute("gioHang", cart);
        }
        return cart;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession(true);
        GioHang cart = getGioHang(session);

        if ("add".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                SanPham sp = spRepo.findById(id);
                if (sp != null) {
                    int qty = 1;
                    String q = req.getParameter("soLuong");
                    if (q != null && !q.isBlank()) {
                        qty = Integer.parseInt(q);
                    }
                    cart.addItem(sp, qty);
                }
            } catch (NumberFormatException ignored) {}
            resp.sendRedirect(req.getContextPath() + "/gio-hang");
            return;

        } else if ("remove".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                cart.removeItem(id);
            } catch (NumberFormatException ignored) {}
            resp.sendRedirect(req.getContextPath() + "/gio-hang");
            return;

        } else if ("clear".equals(action)) {
            cart.clear();
            resp.sendRedirect(req.getContextPath() + "/gio-hang");
            return;
        }

        req.getRequestDispatcher("/views/giohang/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession(true);
        GioHang cart = getGioHang(session);

        if ("update".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                int qty = Integer.parseInt(req.getParameter("soLuong"));
                cart.updateItem(id, qty);
            } catch (NumberFormatException ignored) {}
        }

        resp.sendRedirect(req.getContextPath() + "/gio-hang");
    }
}
