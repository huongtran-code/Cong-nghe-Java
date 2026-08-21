package vn.edu.eaut.lab7.repository;

import vn.edu.eaut.lab7.model.LopHoc;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LopHocRepository {
    private static final List<LopHoc> data = new ArrayList<>();
    private static int autoId = 5;

    static {
        data.add(new LopHoc(1, "DCCNTT15.10.1", "Công nghệ Thông tin 1", "TS. Nguyễn Văn Minh", 45));
        data.add(new LopHoc(2, "DCCNTT15.10.2", "Công nghệ Thông tin 2", "ThS. Trần Thị Hoa", 42));
        data.add(new LopHoc(3, "DCCNTT15.10.3", "Kỹ thuật Phần mềm 1", "PGS.TS. Lê Hoàng Nam", 38));
        data.add(new LopHoc(4, "DCATTT15.10.1", "An toàn Thông tin 1", "ThS. Phạm Quốc Bảo", 40));
    }

    public synchronized List<LopHoc> findAll() {
        return new ArrayList<>(data);
    }

    public synchronized LopHoc findById(int id) {
        return data.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public synchronized void add(LopHoc lh) {
        lh.setId(autoId++);
        data.add(lh);
    }

    public synchronized void update(LopHoc lh) {
        LopHoc old = findById(lh.getId());
        if (old != null) {
            old.setMaLop(lh.getMaLop());
            old.setTenLop(lh.getTenLop());
            old.setCoVanHocTap(lh.getCoVanHocTap());
            old.setSoLuongSinhVien(lh.getSoLuongSinhVien());
        }
    }

    public synchronized void delete(int id) {
        data.removeIf(x -> x.getId() == id);
    }

    public synchronized List<LopHoc> search(String key) {
        if (key == null || key.trim().isEmpty()) return findAll();
        String k = key.toLowerCase().trim();
        return data.stream().filter(x -> 
            x.getMaLop().toLowerCase().contains(k) ||
            x.getTenLop().toLowerCase().contains(k) ||
            x.getCoVanHocTap().toLowerCase().contains(k)
        ).collect(Collectors.toList());
    }
}
