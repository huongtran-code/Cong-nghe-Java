package vn.edu.eaut.lab7.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AppEventListener implements ServletContextListener, HttpSessionListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[APP LOG] ===== 🚀 Ứng dụng Lab 7 MVC khởi động thành công! =====");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[APP LOG] ===== 🛑 Ứng dụng Lab 7 MVC đã dừng. =====");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("[SESSION LOG] ➕ Session mới được khởi tạo ID: " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("[SESSION LOG] ➖ Session đã bị hủy ID: " + se.getSession().getId());
    }
}
