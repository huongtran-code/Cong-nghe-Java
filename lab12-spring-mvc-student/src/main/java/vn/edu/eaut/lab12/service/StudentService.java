package vn.edu.eaut.lab12.service;

import org.springframework.stereotype.Service;
import vn.edu.eaut.lab12.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private long nextId = 1;

    public StudentService() {
        // Dữ liệu mẫu ban đầu
        save(new Student(null, "SV001", "Nguyễn Văn A", "nva@gmail.com", "CNTT1"));
        save(new Student(null, "SV002", "Trần Thị B", "ttb@gmail.com", "CNTT2"));
    }

    // Lấy toàn bộ danh sách hoặc tìm kiếm theo keyword (Bài 9)
    public List<Student> findAll(String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return students.stream()
                    .filter(s -> s.getFullName().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return students;
    }

    // Tìm sinh viên theo ID (Bài 6)
    public Optional<Student> findById(Long id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    // Kiểm tra trùng Mã sinh viên (Bài 10)
    public boolean existsByStudentCode(String studentCode, Long currentId) {
        return students.stream().anyMatch(s -> s.getStudentCode().equalsIgnoreCase(studentCode) &&
                (currentId == null || !s.getId().equals(currentId)));
    }

    // Thêm mới hoặc Cập nhật sinh viên (Bài 2, 7)
    public void save(Student student) {
        if (student.getId() == null) {
            student.setId(nextId++);
            students.add(student);
        } else {
            // Cập nhật thông tin sinh viên hiện tại
            findById(student.getId()).ifPresent(existing -> {
                existing.setStudentCode(student.getStudentCode());
                existing.setFullName(student.getFullName());
                existing.setEmail(student.getEmail());
                existing.setClassName(student.getClassName());
            });
        }
    }

    // Xóa sinh viên (Bài 8)
    public void deleteById(Long id) {
        students.removeIf(s -> s.getId().equals(id));
    }
}