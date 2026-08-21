package vn.edu.eaut.lab7.repository;

import vn.edu.eaut.lab7.model.Sach;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SachRepository {
    private static final List<Sach> data = new ArrayList<>();
    private static int autoId = 7;

    static {
        data.add(new Sach(1, "S001", "Lập trình Java Căn Bản", "Nguyễn Văn Hùng", "NXB Giáo Dục", 2022));
        data.add(new Sach(2, "S002", "Jakarta EE Enterprise Architecture", "Trần Minh Khoa", "NXB Thống Kê", 2023));
        data.add(new Sach(3, "S003", "Cấu trúc dữ liệu và Giải thuật", "Lê Thị Mai", "NXB Đại học Quốc gia", 2021));
        data.add(new Sach(4, "S004", "Thiết kế Web Responsive với CSS3", "Phạm Hoàng Nam", "NXB Lao Động", 2023));
        data.add(new Sach(5, "S005", "Phát triển Web với Servlet & JSP", "Đỗ Tuấn Anh", "NXB Thông tin & Truyền thông", 2024));
        data.add(new Sach(6, "S006", "Spring Boot & Microservices Practical Guide", "Vũ Bảo Ngọc", "NXB Thế Giới", 2024));
    }

    public synchronized List<Sach> findAll() {
        return new ArrayList<>(data);
    }

    public synchronized Sach findById(int id) {
        return data.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public synchronized void add(Sach s) {
        s.setId(autoId++);
        data.add(s);
    }

    public synchronized void update(Sach s) {
        Sach old = findById(s.getId());
        if (old != null) {
            old.setMaSach(s.getMaSach());
            old.setTenSach(s.getTenSach());
            old.setTacGia(s.getTacGia());
            old.setNhaXuatBan(s.getNhaXuatBan());
            old.setNamXuatBan(s.getNamXuatBan());
        }
    }

    public synchronized void delete(int id) {
        data.removeIf(x -> x.getId() == id);
    }

    public synchronized List<Sach> search(String key) {
        if (key == null || key.trim().isEmpty()) return findAll();
        String k = key.toLowerCase().trim();
        return data.stream().filter(x -> 
            x.getTenSach().toLowerCase().contains(k) ||
            x.getTacGia().toLowerCase().contains(k) ||
            x.getMaSach().toLowerCase().contains(k)
        ).collect(Collectors.toList());
    }

    public synchronized List<Sach> searchPaginated(String key, int page, int pageSize) {
        List<Sach> filtered = search(key);
        int fromIndex = (page - 1) * pageSize;
        if (fromIndex >= filtered.size()) return new ArrayList<>();
        int toIndex = Math.min(fromIndex + pageSize, filtered.size());
        return filtered.subList(fromIndex, toIndex);
    }

    public synchronized int getTotalPages(String key, int pageSize) {
        List<Sach> filtered = search(key);
        return (int) Math.ceil((double) filtered.size() / pageSize);
    }
}
