package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import vn.edu.eaut.lab8.model.SanPham;
import vn.edu.eaut.lab8.repository.SanPhamRepository;

import java.io.Serializable;
import java.util.List;

/**
 * SanPhamBean (Bài 7) - Managed Bean xử lý form Sản Phẩm JSF.
 * Validate: tên không rỗng, giá > 0, số lượng >= 0.
 */
@Named("sanPhamBean")
@SessionScoped
public class SanPhamBean implements Serializable {

    private SanPham sanPham = new SanPham();
    private final SanPhamRepository repo = new SanPhamRepository();

    /** Lưu sản phẩm mới */
    public String save() {
        repo.add(sanPham);
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Thêm thành công",
                "Đã lưu sản phẩm: " + sanPham.getTenSanPham()
                    + " | Giá: " + String.format("%,.0f", sanPham.getGia()) + " đ"));
        sanPham = new SanPham(); // reset form
        return null;
    }

    /** Xóa sản phẩm theo ID */
    public void delete(int id) {
        repo.delete(id);
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Đã xóa", "Đã xóa sản phẩm ID=" + id));
    }

    public List<SanPham> getDsSanPham() { return repo.findAll(); }
    public SanPham getSanPham() { return sanPham; }
    public void setSanPham(SanPham sanPham) { this.sanPham = sanPham; }
}
