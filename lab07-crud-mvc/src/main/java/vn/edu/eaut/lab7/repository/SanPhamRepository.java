package vn.edu.eaut.lab7.repository;

import vn.edu.eaut.lab7.model.SanPham;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SanPhamRepository {
    private static final List<SanPham> data = new ArrayList<>();
    private static int autoId = 7;

    static {
        data.add(new SanPham(1, "SP001", "Laptop Dell XPS 15", "Laptop cao cấp màn hình OLED 4K Touch", 38500000.0, 12));
        data.add(new SanPham(2, "SP002", "Bàn phím cơ Keychron K2", "Bàn phím không dây switch Gateron Brown", 1950000.0, 35));
        data.add(new SanPham(3, "SP003", "Chuột Logitech MX Master 3S", "Chuột không dây công nông học chống ồn", 2490000.0, 20));
        data.add(new SanPham(4, "SP004", "Màn hình LG UltraFine 27 inch", "Màn hình 4K IPS chuyên đồ họa 99% DCI-P3", 11200000.0, 8));
        data.add(new SanPham(5, "SP005", "Tai nghe Sony WH-1000XM5", "Tai nghe chống ồn chủ động cao cấp", 7990000.0, 15));
        data.add(new SanPham(6, "SP006", "Ổ cứng SSD NVMe Samsung 1TB", "Tốc độ đọc 7000MB/s chuẩn PCIe 4.0", 2350000.0, 50));
    }

    public synchronized List<SanPham> findAll() {
        return new ArrayList<>(data);
    }

    public synchronized SanPham findById(int id) {
        return data.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public synchronized void add(SanPham sp) {
        sp.setId(autoId++);
        data.add(sp);
    }

    public synchronized void update(SanPham sp) {
        SanPham old = findById(sp.getId());
        if (old != null) {
            old.setMaSanPham(sp.getMaSanPham());
            old.setTenSanPham(sp.getTenSanPham());
            old.setMoTa(sp.getMoTa());
            old.setGia(sp.getGia());
            old.setSoLuong(sp.getSoLuong());
        }
    }

    public synchronized void delete(int id) {
        data.removeIf(x -> x.getId() == id);
    }

    public synchronized List<SanPham> search(String key) {
        if (key == null || key.trim().isEmpty()) return findAll();
        String k = key.toLowerCase().trim();
        return data.stream().filter(x -> 
            x.getTenSanPham().toLowerCase().contains(k) ||
            x.getMaSanPham().toLowerCase().contains(k) ||
            (x.getMoTa() != null && x.getMoTa().toLowerCase().contains(k))
        ).collect(Collectors.toList());
    }

    public synchronized List<SanPham> searchPaginated(String key, int page, int pageSize) {
        List<SanPham> filtered = search(key);
        int fromIndex = (page - 1) * pageSize;
        if (fromIndex >= filtered.size()) return new ArrayList<>();
        int toIndex = Math.min(fromIndex + pageSize, filtered.size());
        return filtered.subList(fromIndex, toIndex);
    }

    public synchronized int getTotalPages(String key, int pageSize) {
        List<SanPham> filtered = search(key);
        return (int) Math.ceil((double) filtered.size() / pageSize);
    }
}
