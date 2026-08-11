package vn.edu.eaut.lab3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Bai08QuanLySinhVien extends JFrame {
    private final JTextField txtId = new JTextField(10);
    private final JTextField txtName = new JTextField(15);
    private final JTextField txtScore = new JTextField(5);
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new String[] { "Mã SV", "Họ Tên", "ĐTB", "Xếp Loại" }, 0);
    private final JTable table = new JTable(tableModel);

    public Bai08QuanLySinhVien() {
        setTitle("Bài 8 - Quản Lý Sinh Viên");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
        form.add(new JLabel("Mã SV:"));
        form.add(txtId);
        form.add(new JLabel("Họ tên:"));
        form.add(txtName);
        form.add(new JLabel("Điểm TB:"));
        form.add(txtScore);

        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnClear = new JButton("Làm mới");
        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(form, BorderLayout.CENTER);
        topPanel.add(btnPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnAdd.addActionListener(e -> addStudent());
        btnEdit.addActionListener(e -> editStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearForm());

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                txtId.setText(tableModel.getValueAt(row, 0).toString());
                txtName.setText(tableModel.getValueAt(row, 1).toString());
                txtScore.setText(tableModel.getValueAt(row, 2).toString());
            }
        });

        setSize(550, 400);
        setLocationRelativeTo(null);
    }

    private void addStudent() {
        try {
            double score = Double.parseDouble(txtScore.getText().trim());
            Student st = new Student(txtId.getText().trim(), txtName.getText().trim(), score);
            tableModel.addRow(new Object[] { st.getId(), st.getName(), st.getScore(), st.getGrade() });
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Điểm phải là số hợp lệ!");
        }
    }

    private void editStudent() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            try {
                double score = Double.parseDouble(txtScore.getText().trim());
                Student st = new Student(txtId.getText().trim(), txtName.getText().trim(), score);
                tableModel.setValueAt(st.getId(), row, 0);
                tableModel.setValueAt(st.getName(), row, 1);
                tableModel.setValueAt(st.getScore(), row, 2);
                tableModel.setValueAt(st.getGrade(), row, 3);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Điểm phải là số hợp lệ!");
            }
        }
    }

    private void deleteStudent() {
        int row = table.getSelectedRow();
        if (row >= 0)
            tableModel.removeRow(row);
    }

    private void clearForm() {
        txtId.setText("");
        txtName.setText("");
        txtScore.setText("");
        table.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai08QuanLySinhVien().setVisible(true));
    }
}