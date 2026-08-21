package vn.edu.eaut.lab8.repository;

import vn.edu.eaut.lab8.model.Sach;
import java.util.ArrayList;
import java.util.List;

/**
 * SachRepository - Lưu trữ dữ liệu sách trong bộ nhớ (Bài 6).
 */
public class SachRepository {

    private static final List<Sach> data = new ArrayList<>();
    private static int autoId = 3;

    static {
        data.add(new Sach(1, "Lập trình Java", "Nguyễn Văn A", 2020, "NXB Giáo Dục"));
        data.add(new Sach(2, "Clean Code", "Robert C. Martin", 2008, "Prentice Hall"));
    }

    public List<Sach> findAll() { return new ArrayList<>(data); }

    public void add(Sach sach) {
        sach.setId(autoId++);
        data.add(sach);
    }

    public void delete(int id) {
        data.removeIf(s -> s.getId() == id);
    }
}
