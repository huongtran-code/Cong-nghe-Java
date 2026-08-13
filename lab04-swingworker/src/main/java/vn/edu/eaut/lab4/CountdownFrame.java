package vn.edu.eaut.lab4;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CountdownFrame extends JFrame {
    private final JTextField txtSeconds = new JTextField(10);
    private final JButton btnStart = new JButton("Bắt đầu");
    private final JLabel lblTime = new JLabel("Thời gian còn lại: ", SwingConstants.CENTER);

    public CountdownFrame() {
        setTitle("Bài 1 - Đồng hồ đếm ngược");
        setSize(400, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblTime.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.add(txtSeconds);
        panel.add(btnStart);
        panel.add(lblTime);
        add(panel);

        btnStart.addActionListener(e -> startCountdown());
    }

    private void startCountdown() {
        int seconds;
        try {
            seconds = Integer.parseInt(txtSeconds.getText().trim());
            if (seconds <= 0) {
                JOptionPane.showMessageDialog(this, "Số giây phải lớn hơn 0");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên hợp lệ");
            return;
        }

        btnStart.setEnabled(false);

        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = seconds; i > 0; i--) {
                    publish(i);
                    Thread.sleep(1000);
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int value = chunks.get(chunks.size() - 1);
                lblTime.setText("Thời gian còn lại: " + value + " giây");
            }

            @Override
            protected void done() {
                btnStart.setEnabled(true);
                lblTime.setText("Thời gian còn lại: 0 giây");
                JOptionPane.showMessageDialog(CountdownFrame.this, "Hoàn thành!");
            }
        };

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CountdownFrame().setVisible(true));
    }
}