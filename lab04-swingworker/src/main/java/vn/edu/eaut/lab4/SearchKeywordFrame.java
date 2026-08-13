package vn.edu.eaut.lab4;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class SearchKeywordFrame extends JFrame {
    private final JTextField txtKeyword = new JTextField(15);
    private final JButton btnChoose = new JButton("Chọn File");
    private final JButton btnSearch = new JButton("Tìm kiếm");
    private final JTextArea txtArea = new JTextArea(10, 40);
    private final JLabel lblStatus = new JLabel("Chưa tìm kiếm", SwingConstants.CENTER);
    private File selectedFile;

    public SearchKeywordFrame() {
        setTitle("Bài 7 - Tìm kiếm từ khóa trong file");
        setSize(550, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        txtArea.setEditable(false);

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(btnChoose);
        topPanel.add(new JLabel("Từ khóa:"));
        topPanel.add(txtKeyword);
        topPanel.add(btnSearch);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(txtArea), BorderLayout.CENTER);
        add(lblStatus, BorderLayout.SOUTH);

        btnChoose.addActionListener(e -> chooseFile());
        btnSearch.addActionListener(e -> searchKeyword());
    }

    private void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            lblStatus.setText("File: " + selectedFile.getName());
        }
    }

    private void searchKeyword() {
        String keyword = txtKeyword.getText().trim().toLowerCase();
        if (selectedFile == null || keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn file và nhập từ khóa!");
            return;
        }

        txtArea.setText("");
        btnSearch.setEnabled(false);
        lblStatus.setText("Đang tìm kiếm...");

        SwingWorker<Integer, String> worker = new SwingWorker<>() {
            @Override
            protected Integer doInBackground() throws Exception {
                int matchCount = 0;
                int lineNum = 0;
                try (BufferedReader reader = Files.newBufferedReader(selectedFile.toPath(), StandardCharsets.UTF_8)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lineNum++;
                        if (line.toLowerCase().contains(keyword)) {
                            matchCount++;
                            publish("Dòng " + lineNum + ": " + line);
                        }
                    }
                }
                return matchCount;
            }

            @Override
            protected void process(List<String> chunks) {
                for (String text : chunks) {
                    txtArea.append(text + "\n");
                }
            }

            @Override
            protected void done() {
                try {
                    int count = get();
                    lblStatus.setText("Tìm thấy " + count + " dòng chứa từ khóa.");
                } catch (Exception e) {
                    lblStatus.setText("Lỗi khi tìm kiếm file!");
                }
                btnSearch.setEnabled(true);
            }
        };

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchKeywordFrame().setVisible(true));
    }
}