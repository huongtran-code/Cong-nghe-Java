package vn.edu.eaut.lab8.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 * Model SinhVien - đại diện cho thực thể Sinh Viên trong hệ thống.
 * Dùng Bean Validation annotations để khai báo ràng buộc dữ liệu.
 */
public class SinhVien implements Serializable {

    private int id;

    @NotBlank(message = "Mã sinh viên không được để trống")
    @Size(min = 8, max = 12, message = "Mã sinh viên phải có 8–12 ký tự")
    private String maSinhVien;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(min = 5, max = 100, message = "Họ tên phải có ít nhất 5 ký tự")
    private String hoTen;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng (ví dụ: abc@gmail.com)")
    private String email;

    @NotBlank(message = "Lớp không được để trống")
    private String lop;

    // ===================== Constructors =====================
    public SinhVien() {}

    public SinhVien(int id, String maSinhVien, String hoTen, String email, String lop) {
        this.id = id;
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.email = email;
        this.lop = lop;
    }

    // ===================== Getters & Setters =====================
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMaSinhVien() { return maSinhVien; }
    public void setMaSinhVien(String maSinhVien) { this.maSinhVien = maSinhVien; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getLop() { return lop; }
    public void setLop(String lop) { this.lop = lop; }

    @Override
    public String toString() {
        return "SinhVien{id=" + id + ", maSV='" + maSinhVien + "', hoTen='" + hoTen + "'}";
    }
}
