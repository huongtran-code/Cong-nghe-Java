package vn.edu.eaut.lab4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class CsvStudentFrame extends JFrame {
    private final JButton btnChoose = new JButton("Chọn file CSV");
    private final DefaultTableModel tableModel = new DefaultTableModel(new String[] { "Mã SV", "Họ Tên", "Điểm" }, 0);
    private final JTable table = new JTable(tableModel);
    private final JLabel lblStats = new JLabel("Điểm TB: 0.0 | Max: N/A", SwingConstants.CENTER);

    public CsvStudentFrame() {
        setTitle("Bài 8 - Đọc file CSV & Thống kê điểm");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(btnChoose);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(lblStats, BorderLayout.SOUTH);

        btnChoose.addActionListener(e -> loadCsv());
    }

    private void loadCsv() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            tableModel.setRowCount(0);

            SwingWorker<Void, String[]> worker = new SwingWorker<>() {
                private double sum = 0;
                private int count = 0;
                private double maxScore = -1;
                private String maxName = "";

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
                            if (parts.length >= 3) {
                                String id = parts[0].trim();
                                String name = parts[1].trim();
                                double score = Double.parseDouble(parts[2].trim());

                                sum += score;
                                count++;
                                if (score > maxScore) {
                                    maxScore = score;
                                    maxName = name;
                                }
                                publish(new String[] { id, name, String.valueOf(score) });
                            }
                        }
                    }
                    return null;
                }

                @Override
                protected void process(List<String[]> chunks) {
                    for (String[] row : chunks) {
                        tableModel.addRow(row);
                    }
                }

                @Override
                protected void done() {
                    if (count > 0) {
                        double avg = sum / count;
                        lblStats.setText(String.format("ĐTB: %.2f | Cao nhất: %s (%.1f)", avg, maxName, maxScore));
                    }
                }
            };

            worker.execute();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CsvStudentFrame().setVisible(true));
    }
}