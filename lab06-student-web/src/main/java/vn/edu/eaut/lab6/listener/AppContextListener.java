package vn.edu.eaut.lab6.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import vn.edu.eaut.lab6.model.Student;
import vn.edu.eaut.lab6.store.StudentStore;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Ung dung Lab 6 da khoi dong");
        
        // Bai 12: Khoi tao du lieu mau
        StudentStore.add(new Student("SV001", "Nguyen Van An", "DCCNTT12", "an@example.com"));
        StudentStore.add(new Student("SV002", "Tran Thi Binh", "DCCNTT12", "binh@example.com"));
        StudentStore.add(new Student("SV003", "Le Van Cuong", "DCCNTT12", "cuong@example.com"));
        StudentStore.add(new Student("SV004", "Pham Thi Dung", "DCCNTT13", "dung@example.com"));
        StudentStore.add(new Student("SV005", "Hoang Van Em", "DCCNTT13", "em@example.com"));
        
        // Luu vao ServletContext (mac du da co StudentStore) de dap ung yeu cau de bai
        sce.getServletContext().setAttribute("studentsInitData", StudentStore.findAll());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Ung dung Lab 6 da dung");
        System.out.println("So luong sinh vien hien tai la: " + StudentStore.findAll().size());
    }
}
