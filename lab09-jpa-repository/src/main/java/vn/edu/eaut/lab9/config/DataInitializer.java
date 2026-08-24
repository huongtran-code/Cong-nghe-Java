package vn.edu.eaut.lab9.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import vn.edu.eaut.lab9.model.LopHoc;
import vn.edu.eaut.lab9.repository.LopHocRepository;

@WebListener
public class DataInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LopHocRepository repo = new LopHocRepository();
        if (repo.findAll().isEmpty()) {
            LopHoc l1 = new LopHoc();
            l1.setTenLop("CNTT17-01");
            LopHoc l2 = new LopHoc();
            l2.setTenLop("CNTT17-02");
            repo.save(l1);
            repo.save(l2);
        }
    }
}