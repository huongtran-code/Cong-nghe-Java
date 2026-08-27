package vn.edu.eaut.lab10.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.eaut.lab10.model.Product;
import vn.edu.eaut.lab10.repository.ProductRepository;

import java.io.IOException;
import java.util.List;

@WebServlet("/staff/products")
public class ProductController extends HttpServlet {
    private final ProductRepository productRepository = new ProductRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "list";

        switch (action) {
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                Product p = productRepository.findById(id);
                request.setAttribute("editProduct", p);
                listProducts(request, response);
                break;
            case "delete":
                int deleteId = Integer.parseInt(request.getParameter("id"));
                productRepository.delete(deleteId);
                response.sendRedirect(request.getContextPath() + "/staff/products");
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));

        Product p = new Product(name, price, quantity);
        if (idStr != null && !idStr.isEmpty()) {
            p.setId(Integer.parseInt(idStr));
        }

        productRepository.save(p);
        response.sendRedirect(request.getContextPath() + "/staff/products");
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> list = productRepository.findAll();
        request.setAttribute("products", list);
        request.getRequestDispatcher("/staff/work.jsp").forward(request, response);
    }
}