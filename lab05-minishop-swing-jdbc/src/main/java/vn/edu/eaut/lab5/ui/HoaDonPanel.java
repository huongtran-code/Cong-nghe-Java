package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.bus.HoaDonBUS;
import vn.edu.eaut.lab5.bus.KhachHangBUS;
import vn.edu.eaut.lab5.bus.SanPhamBUS;
import vn.edu.eaut.lab5.model.ChiTietHoaDon;
import vn.edu.eaut.lab5.model.KhachHang;
import vn.edu.eaut.lab5.model.SanPham;
import vn.edu.eaut.lab5.util.MessageUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HoaDonPanel extends JPanel {
    private final HoaDonBUS hdBus   = new HoaDonBUS();
    private final KhachHangBUS khBus = new KhachHangBUS();
    private final SanPhamBUS spBus   = new SanPhamBUS();

    private JComboBox<KhachHang> cbKhachHang;
    private JComboBox<SanPham>   cbSanPham;
    private JTextField txtSoLuong;
    private JLabel lblTongTien;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnThemDong, btnXoaDong, btnLuuHoaDon, btnLamMoi;

    private final List<ChiTietHoaDon> chiTietList = new ArrayList<>();
    private static final NumberFormat VND_FMT = NumberFormat.getNumberInstance(new Locale("vi", "VN"));

    public HoaDonPanel() {
        setLayout(new BorderLayout(0, 0));
        setBackground(UITheme.BG_PANEL);
        initComponents();
        loadComboData();
    }

    private void initComponents() {
        // ── Left: Order Config Panel ─────────────────────────────────
        JPanel pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setBackground(UITheme.BG_CARD);
        pnlLeft.setPreferredSize(new Dimension(300, 0));

        // Customer selector
        JPanel pnlKH = new JPanel(new GridBagLayout());
        pnlKH.setBackground(UITheme.BG_CARD);
        pnlKH.setBorder(UITheme.sectionBorder("Khách Hàng"));

        cbKhachHang = UITheme.createComboBox();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0; gbc.gridy = 0;
        pnlKH.add(UITheme.createLabel("Chọn khách hàng:"), gbc);
        gbc.gridy = 1;
        pnlKH.add(cbKhachHang, gbc);

        // Product selector
        JPanel pnlSP = new JPanel(new GridBagLayout());
        pnlSP.setBackground(UITheme.BG_CARD);
        pnlSP.setBorder(UITheme.sectionBorder("Thêm Sản Phẩm"));

        cbSanPham = UITheme.createComboBox();
        txtSoLuong = UITheme.createTextField();
        txtSoLuong.setText("1");

        gbc.gridy = 0;
        pnlSP.add(UITheme.createLabel("Sản phẩm:"), gbc);
        gbc.gridy = 1;
        pnlSP.add(cbSanPham, gbc);
        gbc.gridy = 2;
        pnlSP.add(UITheme.createLabel("Số lượng:"), gbc);
        gbc.gridy = 3;
        pnlSP.add(txtSoLuong, gbc);

        // Add/remove row buttons
        JPanel pnlRowBtns = new JPanel(new GridLayout(1, 2, 8, 0));
        pnlRowBtns.setBackground(UITheme.BG_CARD);
        pnlRowBtns.setBorder(new EmptyBorder(8, 6, 8, 6));
        btnThemDong = UITheme.createSuccessButton("✚  Thêm dòng");
        btnXoaDong  = UITheme.createDangerButton("✖  Xóa dòng");
        pnlRowBtns.add(btnThemDong);
        pnlRowBtns.add(btnXoaDong);

        gbc.gridy = 4;
        pnlSP.add(pnlRowBtns, gbc);

        JPanel pnlConfig = new JPanel();
        pnlConfig.setLayout(new BoxLayout(pnlConfig, BoxLayout.Y_AXIS));
        pnlConfig.setBackground(UITheme.BG_CARD);
        pnlConfig.add(pnlKH);
        pnlConfig.add(pnlSP);

        // Save/reset buttons
        JPanel pnlActionBtns = new JPanel(new GridLayout(2, 1, 0, 8));
        pnlActionBtns.setBackground(UITheme.BG_CARD);
        pnlActionBtns.setBorder(new EmptyBorder(10, 10, 14, 10));
        btnLuuHoaDon = UITheme.createPrimaryButton("💾  Lưu Hóa Đơn");
        btnLamMoi    = UITheme.createSecondaryButton("↺  Làm Mới");
        pnlActionBtns.add(btnLuuHoaDon);
        pnlActionBtns.add(btnLamMoi);

        pnlLeft.add(pnlConfig, BorderLayout.CENTER);
        pnlLeft.add(pnlActionBtns, BorderLayout.SOUTH);

        // ── Center: Detail Table + Total ─────────────────────────────
        String[] cols = {"Mã SP", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        UITheme.styleTable(table);

        JScrollPane scroll = new JScrollPane(table);
        UITheme.styleScrollPane(scroll);

        // Total bar
        JPanel pnlTotal = new JPanel(new BorderLayout());
        pnlTotal.setBackground(UITheme.BG_DARK);
        pnlTotal.setBorder(new EmptyBorder(12, 16, 12, 16));

        lblTongTien = UITheme.createValueLabel("Tổng tiền:  0 VNĐ", UITheme.ACCENT_WARNING);
        lblTongTien.setFont(new Font("Segoe UI", Font.BOLD, 18));
        pnlTotal.add(lblTongTien, BorderLayout.WEST);

        JLabel lblHint = UITheme.createLabel("  Chọn dòng để xóa sản phẩm");
        lblHint.setFont(UITheme.FONT_SMALL);
        pnlTotal.add(lblHint, BorderLayout.EAST);

        JPanel pnlRight = new JPanel(new BorderLayout(0, 8));
        pnlRight.setBackground(UITheme.BG_PANEL);
        pnlRight.setBorder(new EmptyBorder(8, 8, 0, 8));

        JLabel lblTitle = new JLabel("  Chi tiết hóa đơn");
        lblTitle.setFont(UITheme.FONT_SUBTITLE);
        lblTitle.setForeground(UITheme.ACCENT_INFO);
        lblTitle.setBorder(new EmptyBorder(0, 0, 4, 0));

        pnlRight.add(lblTitle, BorderLayout.NORTH);
        pnlRight.add(scroll, BorderLayout.CENTER);
        pnlRight.add(pnlTotal, BorderLayout.SOUTH);

        add(pnlLeft, BorderLayout.WEST);
        add(pnlRight, BorderLayout.CENTER);

        // ── Events ───────────────────────────────────────────────────
        btnThemDong.addActionListener(e -> addDetailRow());
        btnXoaDong.addActionListener(e -> removeSelectedRow());
        btnLuuHoaDon.addActionListener(e -> saveHoaDon());
        btnLamMoi.addActionListener(e -> resetForm());
    }

    public void loadComboData() {
        try {
            cbKhachHang.removeAllItems();
            for (KhachHang kh : khBus.findAll()) cbKhachHang.addItem(kh);

            cbSanPham.removeAllItems();
            for (SanPham sp : spBus.findAll()) cbSanPham.addItem(sp);
        } catch (Exception e) {
            MessageUtil.showError(this, "Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    private void addDetailRow() {
        try {
            SanPham sp = (SanPham) cbSanPham.getSelectedItem();
            if (sp == null) return;

            int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
            if (soLuong <= 0) throw new NumberFormatException();

            boolean found = false;
            for (ChiTietHoaDon ct : chiTietList) {
                if (ct.getMaSp() == sp.getMaSp()) {
                    ct.setSoLuong(ct.getSoLuong() + soLuong);
                    found = true; break;
                }
            }
            if (!found) chiTietList.add(new ChiTietHoaDon(sp.getMaSp(), sp.getTenSp(), soLuong, sp.getDonGia()));

            refreshTable();
            txtSoLuong.setText("1");
        } catch (NumberFormatException e) {
            MessageUtil.showError(this, "Số lượng phải là số nguyên dương!");
        }
    }

    private void removeSelectedRow() {
        int row = table.getSelectedRow();
        if (row == -1) { MessageUtil.showError(this, "Chọn dòng cần xóa!"); return; }
        chiTietList.remove(row);
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        BigDecimal tongTien = BigDecimal.ZERO;
        for (ChiTietHoaDon ct : chiTietList) {
            tableModel.addRow(new Object[]{
                ct.getMaSp(), ct.getTenSp(), ct.getSoLuong(),
                ct.getDonGia(), ct.getThanhTien()
            });
            tongTien = tongTien.add(ct.getThanhTien());
        }
        lblTongTien.setText("Tổng tiền:  " + VND_FMT.format(tongTien) + " VNĐ");
    }

    private void saveHoaDon() {
        KhachHang kh = (KhachHang) cbKhachHang.getSelectedItem();
        if (kh == null) { MessageUtil.showError(this, "Vui lòng chọn khách hàng!"); return; }
        if (chiTietList.isEmpty()) { MessageUtil.showError(this, "Hóa đơn phải có ít nhất một sản phẩm!"); return; }

        try {
            int maHd = hdBus.lapHoaDon(kh.getMaKh(), chiTietList);
            MessageUtil.showInfo(this, "✅ Lưu hóa đơn thành công!\nMã hóa đơn: #" + maHd);
            resetForm();
        } catch (Exception e) { MessageUtil.showError(this, e.getMessage()); }
    }

    private void resetForm() {
        chiTietList.clear();
        refreshTable();
        txtSoLuong.setText("1");
        loadComboData();
    }
}
