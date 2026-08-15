package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.bus.ThongKeBUS;
import vn.edu.eaut.lab5.util.DoanhThuWorker;
import vn.edu.eaut.lab5.util.MessageUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ThongKePanel extends JPanel {
    private final ThongKeBUS bus = new ThongKeBUS();
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private JTextField txtTuNgay, txtDenNgay;
    private JLabel lblKetQuaDoanhThu, lblKetQuaHoaDon, lblKetQuaSanPham;
    private JButton btnDoanhThu, btnHoaDonCaoNhat, btnSanPhamBanChay;

    public ThongKePanel() {
        setLayout(new BorderLayout());
        setBackground(UITheme.BG_PANEL);
        initComponents();
    }

    private void initComponents() {
        // ── Page Title ───────────────────────────────────────────────
        JPanel pnlTitle = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 12));
        pnlTitle.setBackground(UITheme.BG_DARK);
        JLabel lblTitle = new JLabel("📊  Thống Kê & Báo Cáo");
        lblTitle.setFont(UITheme.FONT_TITLE);
        lblTitle.setForeground(UITheme.TEXT_PRIMARY);
        pnlTitle.add(lblTitle);
        add(pnlTitle, BorderLayout.NORTH);

        // ── Cards Grid ────────────────────────────────────────────────
        JPanel pnlCards = new JPanel(new GridLayout(3, 1, 0, 16));
        pnlCards.setBackground(UITheme.BG_PANEL);
        pnlCards.setBorder(new EmptyBorder(16, 20, 20, 20));

        // Card 1: Revenue range
        pnlCards.add(buildRevenueCard());
        // Card 2: Highest invoice
        pnlCards.add(buildHighestInvoiceCard());
        // Card 3: Best-selling product
        pnlCards.add(buildBestSellerCard());

        add(pnlCards, BorderLayout.CENTER);

        // ── Events ───────────────────────────────────────────────────
        btnDoanhThu.addActionListener(e -> tinhDoanhThu());
        btnHoaDonCaoNhat.addActionListener(e -> xemHoaDonCaoNhat());
        btnSanPhamBanChay.addActionListener(e -> xemSanPhamBanChay());
    }

    /** Revenue by date range card */
    private JPanel buildRevenueCard() {
        JPanel card = createCard("💰  Thống Kê Doanh Thu Theo Khoảng Ngày", UITheme.ACCENT_SUCCESS);

        JPanel pnlContent = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 8));
        pnlContent.setOpaque(false);

        txtTuNgay = UITheme.createTextField(12);
        txtTuNgay.setText(LocalDate.now().withDayOfMonth(1).format(FMT));
        txtDenNgay = UITheme.createTextField(12);
        txtDenNgay.setText(LocalDate.now().format(FMT));

        btnDoanhThu = UITheme.createSuccessButton("▶  Tính Doanh Thu");
        lblKetQuaDoanhThu = UITheme.createValueLabel("—", UITheme.ACCENT_SUCCESS);

        pnlContent.add(UITheme.createLabel("Từ ngày (dd/MM/yyyy):"));
        pnlContent.add(txtTuNgay);
        pnlContent.add(UITheme.createLabel("Đến ngày:"));
        pnlContent.add(txtDenNgay);
        pnlContent.add(btnDoanhThu);

        JPanel pnlResult = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 4));
        pnlResult.setOpaque(false);
        pnlResult.add(UITheme.createLabel("Kết quả:"));
        pnlResult.add(lblKetQuaDoanhThu);

        card.add(pnlContent, BorderLayout.CENTER);
        card.add(pnlResult, BorderLayout.SOUTH);
        return card;
    }

    /** Highest value invoice card */
    private JPanel buildHighestInvoiceCard() {
        JPanel card = createCard("🏆  Hóa Đơn Có Giá Trị Cao Nhất", UITheme.ACCENT_WARNING);

        JPanel pnlContent = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 8));
        pnlContent.setOpaque(false);

        btnHoaDonCaoNhat = UITheme.createInfoButton("▶  Xem ngay");
        lblKetQuaHoaDon  = UITheme.createValueLabel("—  Chưa tra cứu", UITheme.ACCENT_WARNING);

        pnlContent.add(btnHoaDonCaoNhat);
        pnlContent.add(lblKetQuaHoaDon);

        card.add(pnlContent, BorderLayout.CENTER);
        return card;
    }

    /** Best-selling product card */
    private JPanel buildBestSellerCard() {
        JPanel card = createCard("🔥  Sản Phẩm Bán Chạy Nhất", UITheme.ACCENT_DANGER);

        JPanel pnlContent = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 8));
        pnlContent.setOpaque(false);

        btnSanPhamBanChay = UITheme.createDangerButton("▶  Xem ngay");
        lblKetQuaSanPham  = UITheme.createValueLabel("—  Chưa tra cứu", UITheme.ACCENT_DANGER);

        pnlContent.add(btnSanPhamBanChay);
        pnlContent.add(lblKetQuaSanPham);

        card.add(pnlContent, BorderLayout.CENTER);
        return card;
    }

    /** Creates a styled stat card with a colored left accent bar. */
    private JPanel createCard(String title, Color accentColor) {
        JPanel card = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(UITheme.BG_CARD);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                // Left accent bar
                g2.setColor(accentColor);
                g2.fillRoundRect(0, 0, 6, getHeight(), 6, 6);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UITheme.BORDER_COLOR, 1),
            new EmptyBorder(14, 20, 14, 20)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(UITheme.FONT_SUBTITLE);
        lblTitle.setForeground(accentColor);
        lblTitle.setBorder(new EmptyBorder(0, 8, 6, 0));
        card.add(lblTitle, BorderLayout.NORTH);

        return card;
    }

    // ── Actions ──────────────────────────────────────────────────────

    private void tinhDoanhThu() {
        try {
            LocalDate tuNgay  = LocalDate.parse(txtTuNgay.getText().trim(), FMT);
            LocalDate denNgay = LocalDate.parse(txtDenNgay.getText().trim(), FMT);
            if (tuNgay.isAfter(denNgay)) {
                MessageUtil.showError(this, "Ngày bắt đầu không được sau ngày kết thúc!");
                return;
            }
            lblKetQuaDoanhThu.setText("⏳  Đang tính toán...");
            lblKetQuaDoanhThu.setForeground(UITheme.TEXT_SECONDARY);
            DoanhThuWorker worker = new DoanhThuWorker(tuNgay, denNgay, bus, lblKetQuaDoanhThu);
            worker.execute();
        } catch (DateTimeParseException ex) {
            MessageUtil.showError(this, "Định dạng ngày không hợp lệ. Vui lòng dùng dd/MM/yyyy");
        }
    }

    private void xemHoaDonCaoNhat() {
        try {
            String result = bus.layHoaDonCaoNhat();
            lblKetQuaHoaDon.setText(result);
            lblKetQuaHoaDon.setForeground(UITheme.ACCENT_WARNING);
        } catch (Exception ex) { MessageUtil.showError(this, "Lỗi: " + ex.getMessage()); }
    }

    private void xemSanPhamBanChay() {
        try {
            String result = bus.laySanPhamBanChayNhat();
            lblKetQuaSanPham.setText(result);
            lblKetQuaSanPham.setForeground(UITheme.ACCENT_DANGER);
        } catch (Exception ex) { MessageUtil.showError(this, "Lỗi: " + ex.getMessage()); }
    }
}
