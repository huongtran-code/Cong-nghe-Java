package vn.edu.eaut.lab8.repository;

import vn.edu.eaut.lab8.model.SanPham;
import java.util.ArrayList;
import java.util.List;

/**
 * SanPhamRepository - Lưu trữ dữ liệu sản phẩm trong bộ nhớ (Bài 7).
 */
public class SanPhamRepository {

    private static final List<SanPham> data = new ArrayList<>();
    private static int autoId = 3;

    static {
        data.add(new SanPham(1, "Laptop Dell XPS 15", "Điện tử", 25000000.0, 10));
        data.add(new SanPham(2, "Chuột Logitech MX3", "Phụ kiện", 1500000.0, 50));
    }

    public List<SanPham> findAll() { return new ArrayList<>(data); }

    public void add(SanPham sp) {
        sp.setId(autoId++);
        data.add(sp);
    }

    public void delete(int id) {
        data.removeIf(sp -> sp.getId() == id);
    }
}
