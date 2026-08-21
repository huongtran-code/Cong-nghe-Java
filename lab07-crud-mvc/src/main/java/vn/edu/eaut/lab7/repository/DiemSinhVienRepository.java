package vn.edu.eaut.lab7.repository;

import vn.edu.eaut.lab7.model.DiemSinhVien;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiemSinhVienRepository {
    private static final List<DiemSinhVien> data = new ArrayList<>();
    private static int autoId = 6;

    static {
        data.add(new DiemSinhVien(1, "20240001", "Nguyễn Văn An", 9.0, 8.5, 9.0));
        data.add(new DiemSinhVien(2, "20240002", "Trần Thị Bình", 8.0, 7.5, 7.0));
        data.add(new DiemSinhVien(3, "20240003", "Lê Hoàng Cường", 7.0, 6.0, 6.0));
        data.add(new DiemSinhVien(4, "20240004", "Phạm Minh Đức", 6.0, 5.0, 4.5));
        data.add(new DiemSinhVien(5, "20240005", "Đỗ Thu Giang", 4.0, 3.5, 3.0));
    }

    public synchronized List<DiemSinhVien> findAll() {
        return new ArrayList<>(data);
    }

    public synchronized DiemSinhVien findById(int id) {
        return data.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public synchronized void add(DiemSinhVien d) {
        d.setId(autoId++);
        d.tinhTongKet();
        data.add(d);
    }

    public synchronized void update(DiemSinhVien d) {
        DiemSinhVien old = findById(d.getId());
        if (old != null) {
            old.setMaSV(d.getMaSV());
            old.setHoTen(d.getHoTen());
            old.setDiemChuyenCan(d.getDiemChuyenCan());
            old.setDiemGiuaKy(d.getDiemGiuaKy());
            old.setDiemCuoiKy(d.getDiemCuoiKy());
            old.tinhTongKet();
        }
    }

    public synchronized void delete(int id) {
        data.removeIf(x -> x.getId() == id);
    }

    public synchronized List<DiemSinhVien> search(String key) {
        if (key == null || key.trim().isEmpty()) return findAll();
        String k = key.toLowerCase().trim();
        return data.stream().filter(x -> 
            x.getMaSV().toLowerCase().contains(k) ||
            x.getHoTen().toLowerCase().contains(k)
        ).collect(Collectors.toList());
    }
}
