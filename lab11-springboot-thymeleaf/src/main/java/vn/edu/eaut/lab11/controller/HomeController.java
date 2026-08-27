package vn.edu.eaut.lab11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Hệ thống Quản lý Sinh viên & Khóa học");
        model.addAttribute("message", "Chào mừng đến với ứng dụng Web Spring Boot & Thymeleaf!");
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("course", "Công nghệ Java (IT3242)");
        model.addAttribute("chapter", "Chương 4 - Phát triển ứng dụng với Spring Framework");
        model.addAttribute("description", "Lab 11 hướng dẫn khởi tạo dự án Spring Boot, làm quen với cấu trúc Maven, Spring MVC Controller và render giao diện HTML động bằng Thymeleaf.");
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("department", "Khoa Công nghệ Thông tin - Trường Đại học Công nghệ Đông Á");
        model.addAttribute("address", "Tòa nhà Polyco, Đường Trịnh Văn Bô, Nam Từ Liêm, Hà Nội");
        model.addAttribute("phone", "024.6262.7796");
        model.addAttribute("email", "cntt@eaut.edu.vn");
        model.addAttribute("workingHours", "Thứ 2 - Thứ 6: 08:00 - 17:00");
        return "contact";
    }
}
