package vn.edu.eaut.lab10.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import vn.edu.eaut.lab10.config.JPAUtil;
import vn.edu.eaut.lab10.model.Product;
import vn.edu.eaut.lab10.model.Role;
import vn.edu.eaut.lab10.model.User;
import vn.edu.eaut.lab10.repository.ProductRepository;
import vn.edu.eaut.lab10.repository.UserRepository;

@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserRepository repo = new UserRepository();
        if (repo.count() == 0) {
            repo.save(new User("admin@eaut.edu.vn", "123456", "System Admin", Role.ADMIN, true));
            repo.save(new User("staff@eaut.edu.vn", "123456", "Staff Member", Role.STAFF, true));
            repo.save(new User("user@eaut.edu.vn", "123456", "Normal User", Role.USER, true));
        }

        // Tự tạo dữ liệu sản phẩm mẫu cho khu vực STAFF
        ProductRepository productRepo = new ProductRepository();
        if (productRepo.count() == 0) {
            productRepo.save(new Product("Laptop Dell XPS 13", 25000000.0, 10));
            productRepo.save(new Product("Màn hình Dell UltraSharp", 8500000.0, 15));
            productRepo.save(new Product("Bàn phím Cơ Keychron", 2200000.0, 30));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JPAUtil.shutdown();
    }
}