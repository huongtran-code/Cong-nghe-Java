package vn.edu.eaut.lab4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class ProductManagerFrame extends JFrame {
    private final JTextField txtId = new JTextField(8);
    private final JTextField txtName = new JTextField(12);
    private final JTextField txtPrice = new JTextField(8);

    private final DefaultTableModel tableModel = new DefaultTableModel(new String[] { "Mã SP", "Tên SP", "Đơn Giá" },
            0);
    private final JTable table = new JTable(tableModel);

    private final JButton btnAdd = new JButton("Thêm");
    private final JButton btnEdit = new JButton("Sửa");
    private final JButton btnDelete = new JButton("Xóa");
    private final JButton btnSave = new JButton("Lưu CSV");
    private final JButton btnLoad = new JButton("Đọc CSV");

    public ProductManagerFrame() {
        setTitle("Bài 10 - Quản lý sản phẩm CSV (SwingWorker)");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Mã:"));
        inputPanel.add(txtId);
        inputPanel.add(new JLabel("Tên:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Giá:"));
        inputPanel.add(txtPrice);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        btnPanel.add(btnSave);
        btnPanel.add(btnLoad);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(btnPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnAdd.addActionListener(e -> addProduct());
        btnEdit.addActionListener(e -> editProduct());
        btnDelete.addActionListener(e -> deleteProduct());
        btnSave.addActionListener(e -> saveCsv());
        btnLoad.addActionListener(e -> loadCsv());

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                txtId.setText(tableModel.getValueAt(row, 0).toString());
                txtName.setText(tableModel.getValueAt(row, 1).toString());
                txtPrice.setText(tableModel.getValueAt(row, 2).toString());
            }
        });
    }

    private void addProduct() {
        if (txtId.getText().isEmpty() || txtName.getText().isEmpty())
            return;
        tableModel.addRow(new Object[] { txtId.getText().trim(), txtName.getText().trim(), txtPrice.getText().trim() });
        clearInput();
    }

    private void editProduct() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            tableModel.setValueAt(txtId.getText().trim(), row, 0);
            tableModel.setValueAt(txtName.getText().trim(), row, 1);
            tableModel.setValueAt(txtPrice.getText().trim(), row, 2);
        }
    }

    private void deleteProduct() {
        int row = table.getSelectedRow();
        if (row >= 0)
            tableModel.removeRow(row);
    }

    private void clearInput() {
        txtId.setText("");
        txtName.setText("");
        txtPrice.setText("");
    }

    private void saveCsv() {
        JFileChooser chooser = new JFileChooser();

        // Gợi ý sẵn tên file mặc định
        chooser.setSelectedFile(new File("danhsach_sanpham.csv"));

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            // Tự động thêm đuôi .csv nếu người dùng quên gõ
            if (!file.getName().toLowerCase().endsWith(".csv")) {
                file = new File(file.getAbsolutePath() + ".csv");
            }

            final File finalFile = file; // Biến final để dùng trong SwingWorker

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    try (BufferedWriter writer = Files.newBufferedWriter(finalFile.toPath(), StandardCharsets.UTF_8)) {
                        writer.write("MaSP,TenSP,DonGia\n");
                        for (int i = 0; i < tableModel.getRowCount(); i++) {
                            writer.write(String.format("%s,%s,%s\n",
                                    tableModel.getValueAt(i, 0),
                                    tableModel.getValueAt(i, 1),
                                    tableModel.getValueAt(i, 2)));
                        }
                    }
                    return null;
                }

                @Override
                protected void done() {
                    try {
                        get(); // Kiểm tra nếu doInBackground có quăng lỗi gì không
                        JOptionPane.showMessageDialog(ProductManagerFrame.this,
                                "Lưu file thành công tại:\n" + finalFile.getAbsolutePath());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ProductManagerFrame.this,
                                "Lỗi khi lưu file: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            };
            worker.execute();
        }
    }

    private void loadCsv() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            tableModel.setRowCount(0);

            SwingWorker<Void, String[]> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
                        String line;
                        boolean isHeader = true;
                        while ((line = reader.readLine()) != null) {
                            if (isHeader) {
                                isHeader = false;
                                continue;
                            }
                            String[] parts = line.split(",");
                            if (parts.length >= 3)
                                publish(parts);
                        }
                    }
                    return null;
                }

                @Override
                protected void process(List<String[]> chunks) {
                    for (String[] row : chunks)
                        tableModel.addRow(row);
                }

                @Override
                protected void done() {
                    JOptionPane.showMessageDialog(ProductManagerFrame.this, "Đọc CSV thành công!");
                }
            };
            worker.execute();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductManagerFrame().setVisible(true));
    }
}