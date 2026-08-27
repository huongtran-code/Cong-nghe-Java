package vn.edu.eaut.lab11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.eaut.lab11.model.Course;

import java.util.List;

@Controller
public class CourseController {

    @GetMapping("/courses")
    public String listCourses(Model model) {
        List<Course> courses = List.of(
            new Course("IT3242", "Công nghệ Java", 3, "Bắt buộc"),
            new Course("IT3100", "Cấu trúc dữ liệu và giải thuật", 3, "Bắt buộc"),
            new Course("IT3020", "Cơ sở dữ liệu", 3, "Bắt buộc"),
            new Course("IT3040", "Lập trình Web với Spring Boot", 4, "Tự chọn"),
            new Course("IT3080", "Quản lý dự án Phần mềm", 2, "Tự chọn")
        );
        model.addAttribute("courses", courses);
        model.addAttribute("totalCourses", courses.size());
        return "courses";
    }
}
