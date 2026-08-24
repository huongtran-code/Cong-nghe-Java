package vn.edu.eaut.lab9.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "lop_hoc")
public class LopHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_lop", nullable = false, length = 50)
    private String tenLop;

    @OneToMany(mappedBy = "lopHoc", cascade = CascadeType.ALL)
    private List<SinhVien> dsSinhVien;

    public LopHoc() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public List<SinhVien> getDsSinhVien() {
        return dsSinhVien;
    }

    public void setDsSinhVien(List<SinhVien> dsSinhVien) {
        this.dsSinhVien = dsSinhVien;
    }
}