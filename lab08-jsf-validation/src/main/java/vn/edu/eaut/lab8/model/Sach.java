package vn.edu.eaut.lab8.model;

import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * Model Sach (Bài 6) - đại diện cho thực thể Sách.
 * Sử dụng Bean Validation để kiểm tra dữ liệu đầu vào.
 */
public class Sach implements Serializable {

    private int id;

    @NotBlank(message = "Tên sách không được để trống")
    @Size(min = 2, max = 200, message = "Tên sách phải có 2–200 ký tự")
    private String tenSach;

    @NotBlank(message = "Tác giả không được để trống")
    @Size(min = 3, message = "Tên tác giả phải có ít nhất 3 ký tự")
    private String tacGia;

    @NotNull(message = "Năm xuất bản không được để trống")
    @Min(value = 1000, message = "Năm xuất bản phải từ 1000 trở lên")
    @Max(value = 2100, message = "Năm xuất bản không được vượt quá 2100")
    private Integer namXuatBan;

    @NotBlank(message = "Nhà xuất bản không được để trống")
    private String nhaXuatBan;

    // ===================== Constructors =====================
    public Sach() {}

    public Sach(int id, String tenSach, String tacGia, Integer namXuatBan, String nhaXuatBan) {
        this.id = id;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.nhaXuatBan = nhaXuatBan;
    }

    // ===================== Getters & Setters =====================
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTenSach() { return tenSach; }
    public void setTenSach(String tenSach) { this.tenSach = tenSach; }

    public String getTacGia() { return tacGia; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }

    public Integer getNamXuatBan() { return namXuatBan; }
    public void setNamXuatBan(Integer namXuatBan) { this.namXuatBan = namXuatBan; }

    public String getNhaXuatBan() { return nhaXuatBan; }
    public void setNhaXuatBan(String nhaXuatBan) { this.nhaXuatBan = nhaXuatBan; }
}
