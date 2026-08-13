package vn.edu.eaut.lab4;

import javax.swing.*;
import java.awt.*;

public class CancelableTaskFrame extends JFrame {
    private final JButton btnStart = new JButton("Bắt đầu");
    private final JButton btnCancel = new JButton("Hủy");
    private final JProgressBar progressBar = new JProgressBar(0, 100);
    private final JLabel lblStatus = new JLabel("Trạng thái: Chưa chạy", SwingConstants.CENTER);
    private SwingWorker<Void, Integer> worker;

    public CancelableTaskFrame() {
        setTitle("Bài 6 - Hủy tác vụ với SwingWorker");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        progressBar.setStringPainted(true);
        btnCancel.setEnabled(false);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(btnStart);
        btnPanel.add(btnCancel);

        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
        panel.add(btnPanel);
        panel.add(progressBar);
        panel.add(lblStatus);
        add(panel);

        btnStart.addActionListener(e -> startTask());
        btnCancel.addActionListener(e -> {
            if (worker != null && !worker.isDone()) {
                worker.cancel(true);
            }
        });
    }

    private void startTask() {
        btnStart.setEnabled(false);
        btnCancel.setEnabled(true);
        progressBar.setValue(0);
        lblStatus.setText("Đang thực hiện...");

        worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 100; i += 5) {
                    if (isCancelled())
                        break;
                    setProgress(i);
                    Thread.sleep(300);
                }
                return null;
            }

            @Override
            protected void done() {
                if (isCancelled()) {
                    lblStatus.setText("Đã hủy tác vụ");
                } else {
                    progressBar.setValue(100);
                    lblStatus.setText("Hoàn thành tác vụ!");
                }
                btnStart.setEnabled(true);
                btnCancel.setEnabled(false);
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
        SwingUtilities.invokeLater(() -> new CancelableTaskFrame().setVisible(true));
    }
}