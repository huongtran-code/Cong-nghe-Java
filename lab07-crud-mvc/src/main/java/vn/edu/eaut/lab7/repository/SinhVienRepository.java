package vn.edu.eaut.lab7.repository;

import vn.edu.eaut.lab7.model.SinhVien;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SinhVienRepository {
    private static final List<SinhVien> data = new ArrayList<>();
    private static int autoId = 7;

    static {
        data.add(new SinhVien(1, "20240001", "Nguyễn Văn An", "an@gmail.com", "DCCNTT15.10.1"));
        data.add(new SinhVien(2, "20240002", "Trần Thị Bình", "binh@gmail.com", "DCCNTT15.10.2"));
        data.add(new SinhVien(3, "20240003", "Lê Hoàng Cường", "cuong@gmail.com", "DCCNTT15.10.1"));
        data.add(new SinhVien(4, "20240004", "Phạm Minh Đức", "duc@gmail.com", "DCCNTT15.10.3"));
        data.add(new SinhVien(5, "20240005", "Đỗ Thu Giang", "giang@gmail.com", "DCCNTT15.10.2"));
        data.add(new SinhVien(6, "20240006", "Vũ Huy Hoàng", "hoang@gmail.com", "DCCNTT15.10.1"));
    }

    public synchronized List<SinhVien> findAll() {
        return new ArrayList<>(data);
    }

    public synchronized SinhVien findById(int id) {
        return data.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public synchronized void add(SinhVien sv) {
        sv.setId(autoId++);
        data.add(sv);
    }

    public synchronized void update(SinhVien sv) {
        SinhVien old = findById(sv.getId());
        if (old != null) {
            old.setMaSinhVien(sv.getMaSinhVien());
            old.setHoTen(sv.getHoTen());
            old.setEmail(sv.getEmail());
            old.setLop(sv.getLop());
        }
    }

    public synchronized void delete(int id) {
        data.removeIf(x -> x.getId() == id);
    }

    public synchronized List<SinhVien> search(String key) {
        if (key == null || key.trim().isEmpty()) return findAll();
        String k = key.toLowerCase().trim();
        return data.stream().filter(x -> 
            x.getHoTen().toLowerCase().contains(k) ||
            x.getLop().toLowerCase().contains(k) ||
            x.getMaSinhVien().toLowerCase().contains(k)
        ).collect(Collectors.toList());
    }

    public synchronized List<SinhVien> searchPaginated(String key, int page, int pageSize) {
        List<SinhVien> filtered = search(key);
        int fromIndex = (page - 1) * pageSize;
        if (fromIndex >= filtered.size()) return new ArrayList<>();
        int toIndex = Math.min(fromIndex + pageSize, filtered.size());
        return filtered.subList(fromIndex, toIndex);
    }

    public synchronized int getTotalPages(String key, int pageSize) {
        List<SinhVien> filtered = search(key);
        return (int) Math.ceil((double) filtered.size() / pageSize);
    }
}
