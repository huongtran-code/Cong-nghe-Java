package vn.edu.eaut.lab8.model;

import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * Model SanPham (Bài 7) - đại diện cho thực thể Sản Phẩm.
 * Validate: tên không rỗng, giá > 0, số lượng >= 0.
 */
public class SanPham implements Serializable {

    private int id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 2, max = 200, message = "Tên sản phẩm phải có 2–200 ký tự")
    private String tenSanPham;

    @NotBlank(message = "Danh mục không được để trống")
    private String danhMuc;

    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "0.01", message = "Giá phải lớn hơn 0")
    private Double gia;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng phải >= 0")
    private Integer soLuong;

    // ===================== Constructors =====================
    public SanPham() {}

    public SanPham(int id, String tenSanPham, String danhMuc, Double gia, Integer soLuong) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.danhMuc = danhMuc;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    // ===================== Getters & Setters =====================
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }

    public String getDanhMuc() { return danhMuc; }
    public void setDanhMuc(String danhMuc) { this.danhMuc = danhMuc; }

    public Double getGia() { return gia; }
    public void setGia(Double gia) { this.gia = gia; }

    public Integer getSoLuong() { return soLuong; }
    public void setSoLuong(Integer soLuong) { this.soLuong = soLuong; }
}
