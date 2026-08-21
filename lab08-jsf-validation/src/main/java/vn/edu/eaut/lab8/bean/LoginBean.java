package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;

/**
 * LoginBean (Bài 8) - Managed Bean xử lý form đăng nhập JSF.
 *
 * Logic:
 *  - Đúng tài khoản → điều hướng đến index.xhtml
 *  - Sai tài khoản  → hiển thị FacesMessage lỗi, ở lại trang login
 *
 * Dùng @RequestScoped vì login là thao tác một lần, không cần giữ trạng thái.
 */
@Named("loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;

    // Tài khoản hợp lệ (hardcode cho mục đích demo Lab 8)
    private static final String VALID_USER = "admin";
    private static final String VALID_PASS = "123456";

    /**
     * Xử lý đăng nhập:
     *  - Đúng → trả về "index" (điều hướng đến index.xhtml)
     *  - Sai  → thêm FacesMessage lỗi, trả về null (ở lại login.xhtml)
     */
    public String login() {
        if (VALID_USER.equals(username) && VALID_PASS.equals(password)) {
            // Đăng nhập thành công → chuyển đến trang chủ
            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Chào mừng", "Đăng nhập thành công! Xin chào " + username));
            return "index?faces-redirect=true";
        } else {
            // Đăng nhập thất bại → hiển thị lỗi
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Đăng nhập thất bại",
                    "Tài khoản hoặc mật khẩu không đúng. Vui lòng thử lại."));
            return null; // ở lại trang login
        }
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
