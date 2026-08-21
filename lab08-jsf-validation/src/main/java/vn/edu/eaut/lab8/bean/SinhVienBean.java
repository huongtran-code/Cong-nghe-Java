package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import vn.edu.eaut.lab8.model.SinhVien;
import vn.edu.eaut.lab8.repository.SinhVienRepository;

import java.io.Serializable;
import java.util.List;

/**
 * SinhVienBean - Managed Bean (CDI) xử lý logic form sinh viên JSF.
 *
 * @Named("sinhVienBean") → EL expression #{sinhVienBean} trong trang .xhtml
 * @SessionScoped        → Bean tồn tại suốt phiên làm việc của người dùng
 *
 * Cơ chế binding:
 *   JSF dùng Expression Language (EL) #{sinhVienBean.sinhVien.hoTen}
 *   → JSF tự gọi getSinhVien().setHoTen(value) khi submit form (Apply Request Values)
 *   → rồi validate (Process Validations) → rồi gọi action method save()
 */
@Named("sinhVienBean")
@SessionScoped
public class SinhVienBean implements Serializable {

    private SinhVien sinhVien = new SinhVien();        // form binding
    private final SinhVienRepository repo = new SinhVienRepository();

    // ====== Bài 9: sửa sinh viên ======
    private boolean editMode = false;

    // ====== Bài 10: tìm kiếm ======
    private String keyword = "";

    // ===================== Actions =====================

    /**
     * Bài 3 & 4: Lưu sinh viên mới hoặc cập nhật sinh viên đang sửa.
     * Trả về null để ở lại cùng trang; trả về "sinhvien-list" để chuyển trang.
     */
    public String save() {
        if (editMode) {
            repo.update(sinhVien);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Cập nhật thành công", "Đã cập nhật sinh viên: " + sinhVien.getHoTen()));
            editMode = false;
        } else {
            repo.add(sinhVien);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Thêm thành công", "Đã lưu sinh viên: " + sinhVien.getHoTen()));
        }
        sinhVien = new SinhVien(); // reset form
        return null; // ở lại trang hiện tại
    }

    /**
     * Bài 5: Xóa sinh viên theo ID, hiển thị FacesMessage thông báo thành công.
     */
    public void delete(int id) {
        SinhVien found = repo.findById(id);
        repo.delete(id);
        String ten = (found != null) ? found.getHoTen() : "ID=" + id;
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Đã xóa", "Đã xóa sinh viên: " + ten));
    }

    /**
     * Bài 9: Chọn sinh viên để sửa - đưa dữ liệu lên form.
     */
    public String edit(int id) {
        SinhVien found = repo.findById(id);
        if (found != null) {
            // Tạo bản sao để chỉnh sửa
            sinhVien = new SinhVien(found.getId(), found.getMaSinhVien(),
                found.getHoTen(), found.getEmail(), found.getLop());
            editMode = true;
        }
        return "sinhvien-form"; // điều hướng đến form
    }

    /** Bài 9: Hủy chỉnh sửa, quay về danh sách */
    public String cancelEdit() {
        sinhVien = new SinhVien();
        editMode = false;
        return "sinhvien-list";
    }

    /**
     * Bài 10: Tìm kiếm sinh viên theo từ khóa.
     * Lọc danh sách theo họ tên, lớp hoặc mã SV.
     */
    public List<SinhVien> getDsSinhVienFiltered() {
        return repo.search(keyword);
    }

    // ===================== Getters & Setters =====================

    /** Bài 5: Lấy toàn bộ danh sách sinh viên */
    public List<SinhVien> getDsSinhVien() {
        return repo.findAll();
    }

    public SinhVien getSinhVien() { return sinhVien; }
    public void setSinhVien(SinhVien sinhVien) { this.sinhVien = sinhVien; }

    public boolean isEditMode() { return editMode; }
    public void setEditMode(boolean editMode) { this.editMode = editMode; }

    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
}
