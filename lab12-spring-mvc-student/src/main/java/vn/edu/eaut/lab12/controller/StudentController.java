package vn.edu.eaut.lab12.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.eaut.lab12.model.Student;
import vn.edu.eaut.lab12.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Bài 3 & Bài 9: Hiển thị danh sách sinh viên & Tìm kiếm theo họ tên
    @GetMapping
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("students", studentService.findAll(keyword));
        model.addAttribute("keyword", keyword);
        return "students/list";
    }

    // Bài 6: Xem chi tiết sinh viên theo ID
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Student student = studentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sinh viên ID: " + id));
        model.addAttribute("student", student);
        return "students/detail";
    }

    // Bài 4: Hiển thị form thêm mới
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    // Bài 7: Hiển thị form chỉnh sửa sinh viên
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Student student = studentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sinh viên ID: " + id));
        model.addAttribute("student", student);
        return "students/form";
    }

    // Bài 4, Bài 5 & Bài 10: Xử lý lưu form, Validation & Check trùng mã sinh viên
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        // Bài 10: Validation kiểm tra trùng mã sinh viên
        if (studentService.existsByStudentCode(student.getStudentCode(), student.getId())) {
            result.rejectValue("studentCode", "error.student", "Mã sinh viên đã tồn tại trong hệ thống");
        }

        // Bài 5: Kiểm tra lỗi validation
        if (result.hasErrors()) {
            return "students/form";
        }

        studentService.save(student);
        return "redirect:/students";
    }

    // Bài 8: Xóa sinh viên
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }
}