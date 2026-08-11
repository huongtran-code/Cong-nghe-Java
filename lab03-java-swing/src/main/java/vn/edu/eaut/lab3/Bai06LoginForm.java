package vn.edu.eaut.lab3;

import javax.swing.*;
import java.awt.*;

public class Bai06LoginForm extends JFrame {
    private final JTextField txtUser = new JTextField(15);
    private final JPasswordField txtPass = new JPasswordField(15);
    private final JComboBox<String> cbRole = new JComboBox<>(new String[] { "Admin", "User" });
    private final JCheckBox chkShow = new JCheckBox("Hiển thị mật khẩu");

    public Bai06LoginForm() {
        setTitle("Bài 6 - Form Đăng Nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 8, 8));

        add(new JLabel(" Tên đăng nhập:"));
        add(txtUser);
        add(new JLabel(" Mật khẩu:"));
        add(txtPass);
        add(new JLabel(" Vai trò:"));
        add(cbRole);
        add(new JLabel(""));
        add(chkShow);

        JButton btnLogin = new JButton("Đăng nhập");
        JButton btnExit = new JButton("Thoát");
        add(btnLogin);
        add(btnExit);

        chkShow.addActionListener(e -> {
            if (chkShow.isSelected())
                txtPass.setEchoChar((char) 0);
            else
                txtPass.setEchoChar('•');
        });

        btnLogin.addActionListener(e -> handleLogin());
        btnExit.addActionListener(e -> System.exit(0));

        pack();
        setLocationRelativeTo(null);
    }

    private void handleLogin() {
        String user = txtUser.getText().trim();
        String pass = new String(txtPass.getPassword());
        String role = (String) cbRole.getSelectedItem();

        if (("Admin".equals(role) && "admin".equals(user) && "123456".equals(pass)) ||
                ("User".equals(role) && "user".equals(user) && "123456".equals(pass))) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công với quyền " + role + "!");
        } else {
            JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không chính xác!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai06LoginForm().setVisible(true));
    }
}