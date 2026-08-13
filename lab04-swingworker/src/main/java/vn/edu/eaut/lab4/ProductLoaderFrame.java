package vn.edu.eaut.lab4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductLoaderFrame extends JFrame {
    private final JButton btnLoad = new JButton("Tải sản phẩm");
    private final DefaultTableModel tableModel = new DefaultTableModel(new String[] { "Mã SP", "Tên SP", "Đơn Giá" },
            0);
    private final JTable table = new JTable(tableModel);
    private final JProgressBar progressBar = new JProgressBar(0, 100);
    private final JLabel lblStatus = new JLabel("Sẵn sàng", SwingConstants.CENTER);

    public ProductLoaderFrame() {
        setTitle("Bài 9 - Mô phỏng tải sản phẩm");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        progressBar.setStringPainted(true);

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(btnLoad);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.add(progressBar);
        bottomPanel.add(lblStatus);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        btnLoad.addActionListener(e -> loadProducts());
    }

    private void loadProducts() {
        btnLoad.setEnabled(false);
        tableModel.setRowCount(0);
        lblStatus.setText("Đang tải danh sách sản phẩm...");

        SwingWorker<Void, Object[]> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                Object[][] mockData = {
                        { "SP01", "Bàn phím", 250000 },
                        { "SP02", "Chuột", 150000 },
                        { "SP03", "Màn hình", 2500000 },
                        { "SP04", "Tai nghe", 450000 },
                        { "SP05", "Loa Bluetooth", 650000 }
                };

                for (int i = 0; i < mockData.length; i++) {
                    Thread.sleep(600); // Mô phỏng trễ mạng/CSDL
                    publish(mockData[i]);
                    setProgress((i + 1) * 100 / mockData.length);
                }
                return null;
            }

            @Override
            protected void process(List<Object[]> chunks) {
                for (Object[] row : chunks) {
                    tableModel.addRow(row);
                }
            }

            @Override
            protected void done() {
                lblStatus.setText("Tải danh sách sản phẩm hoàn tất!");
                btnLoad.setEnabled(true);
            }
        };

        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((int) evt.getNewValue());
            }
        });

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductLoaderFrame().setVisible(true));
    }
}