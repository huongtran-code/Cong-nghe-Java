package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import vn.edu.eaut.lab8.model.Sach;
import vn.edu.eaut.lab8.repository.SachRepository;

import java.io.Serializable;
import java.util.List;

/**
 * SachBean (Bài 6) - Managed Bean xử lý form Sách JSF.
 * Validate: tên sách không rỗng, tác giả không rỗng, năm xuất bản hợp lệ.
 */
@Named("sachBean")
@SessionScoped
public class SachBean implements Serializable {

    private Sach sach = new Sach();
    private final SachRepository repo = new SachRepository();

    /** Lưu sách mới */
    public String save() {
        repo.add(sach);
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Thêm thành công", "Đã lưu sách: " + sach.getTenSach()));
        sach = new Sach(); // reset form
        return null;
    }

    /** Xóa sách theo ID */
    public void delete(int id) {
        repo.delete(id);
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Đã xóa", "Đã xóa sách ID=" + id));
    }

    public List<Sach> getDsSach() { return repo.findAll(); }
    public Sach getSach() { return sach; }
    public void setSach(Sach sach) { this.sach = sach; }
}
