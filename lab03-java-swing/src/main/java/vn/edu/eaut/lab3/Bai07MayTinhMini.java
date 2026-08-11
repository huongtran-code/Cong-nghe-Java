package vn.edu.eaut.lab3;

import javax.swing.*;
import java.awt.*;

public class Bai07MayTinhMini extends JFrame {
    private final JTextField txtNum1 = new JTextField(8);
    private final JTextField txtNum2 = new JTextField(8);
    private final JLabel lblResult = new JLabel("Kết quả: ");
    private final JTextArea txtHistory = new JTextArea(8, 30);

    public Bai07MayTinhMini() {
        setTitle("Bài 7 - Máy Tính Mini");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        topPanel.add(new JLabel("Số thứ nhất:"));
        topPanel.add(txtNum1);
        topPanel.add(new JLabel("Số thứ hai:"));
        topPanel.add(txtNum2);

        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("+");
        JButton btnSub = new JButton("-");
        JButton btnMul = new JButton("*");
        JButton btnDiv = new JButton("/");
        JButton btnClear = new JButton("Clear");
        btnPanel.add(btnAdd);
        btnPanel.add(btnSub);
        btnPanel.add(btnMul);
        btnPanel.add(btnDiv);
        btnPanel.add(btnClear);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(btnPanel, BorderLayout.NORTH);
        centerPanel.add(lblResult, BorderLayout.SOUTH);

        txtHistory.setEditable(false);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(new JScrollPane(txtHistory), BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> calculate("+"));
        btnSub.addActionListener(e -> calculate("-"));
        btnMul.addActionListener(e -> calculate("*"));
        btnDiv.addActionListener(e -> calculate("/"));
        btnClear.addActionListener(e -> {
            txtNum1.setText("");
            txtNum2.setText("");
            lblResult.setText("Kết quả: ");
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void calculate(String op) {
        try {
            double n1 = Double.parseDouble(txtNum1.getText().trim());
            double n2 = Double.parseDouble(txtNum2.getText().trim());
            double res = 0;
            if (op.equals("/") && Math.abs(n2) < 1e-9) {
                JOptionPane.showMessageDialog(this, "Lỗi: Không thể chia cho 0!");
                return;
            }
            switch (op) {
                case "+":
                    res = n1 + n2;
                    break;
                case "-":
                    res = n1 - n2;
                    break;
                case "*":
                    res = n1 * n2;
                    break;
                case "/":
                    res = n1 / n2;
                    break;
            }
            String log = String.format("%.2f %s %.2f = %.2f\n", n1, op, n2, res);
            lblResult.setText("Kết quả: " + res);
            txtHistory.append(log);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai07MayTinhMini().setVisible(true));
    }
}