package vn.edu.eaut.lab8.repository;

import vn.edu.eaut.lab8.model.SinhVien;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SinhVienRepository - Lưu trữ dữ liệu sinh viên trong bộ nhớ (in-memory List).
 * Chưa dùng JPA/Database để tập trung vào JSF ở Lab 8.
 * Lab 9 sẽ tích hợp JPA và transaction.
 */
public class SinhVienRepository {

    // Dữ liệu dùng chung (static) cho toàn ứng dụng
    private static final List<SinhVien> data = new ArrayList<>();
    private static int autoId = 3;

    // Khởi tạo dữ liệu mẫu
    static {
        data.add(new SinhVien(1, "20240001", "Nguyễn Văn An", "an@gmail.com", "DCCNTT15.10.1"));
        data.add(new SinhVien(2, "20240002", "Trần Thị Bình", "binh@gmail.com", "DCCNTT15.10.2"));
    }

    /** Lấy toàn bộ danh sách sinh viên */
    public List<SinhVien> findAll() {
        return new ArrayList<>(data);
    }

    /** Thêm sinh viên mới; tự động gán ID */
    public void add(SinhVien sv) {
        sv.setId(autoId++);
        data.add(sv);
    }

    /** Xóa sinh viên theo ID */
    public void delete(int id) {
        data.removeIf(sv -> sv.getId() == id);
    }

    /** Tìm sinh viên theo ID */
    public SinhVien findById(int id) {
        return data.stream()
                .filter(sv -> sv.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /** Cập nhật sinh viên (Bài 9) */
    public void update(SinhVien updated) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == updated.getId()) {
                data.set(i, updated);
                return;
            }
        }
    }

    /** Tìm kiếm theo từ khóa (Bài 10): lọc theo họ tên hoặc lớp */
    public List<SinhVien> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return findAll();
        }
        String kw = keyword.trim().toLowerCase();
        return data.stream()
                .filter(sv -> sv.getHoTen().toLowerCase().contains(kw)
                           || sv.getLop().toLowerCase().contains(kw)
                           || sv.getMaSinhVien().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }
}
