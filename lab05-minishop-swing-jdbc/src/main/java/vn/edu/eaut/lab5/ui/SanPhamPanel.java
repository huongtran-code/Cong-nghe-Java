package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.bus.SanPhamBUS;
import vn.edu.eaut.lab5.model.SanPham;
import vn.edu.eaut.lab5.util.MessageUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class SanPhamPanel extends JPanel {
    private final SanPhamBUS bus = new SanPhamBUS();

    private JTextField txtMa, txtTen, txtDonGia, txtSoLuong, txtTimKiem;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnTimKiem;

    public SanPhamPanel() {
        setLayout(new BorderLayout(0, 0));
        setBackground(UITheme.BG_PANEL);
        initComponents();
        loadData();
    }

    private void initComponents() {
        // ── Top: Search Bar ─────────────────────────────────────────
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlSearch.setBackground(UITheme.BG_DARK);
        pnlSearch.setBorder(new EmptyBorder(6, 10, 6, 10));

        JLabel lblFind = UITheme.createLabel("🔍  Tìm tên sản phẩm:");
        txtTimKiem = UITheme.createTextField(22);
        btnTimKiem = UITheme.createInfoButton("Tìm kiếm");

        pnlSearch.add(lblFind);
        pnlSearch.add(txtTimKiem);
        pnlSearch.add(btnTimKiem);

        // ── Left: Form Card ─────────────────────────────────────────
        JPanel pnlForm = new JPanel(new GridBagLayout());
        pnlForm.setBackground(UITheme.BG_CARD);
        pnlForm.setBorder(UITheme.sectionBorder("Thông tin Sản Phẩm"));
        pnlForm.setPreferredSize(new Dimension(290, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 6, 7, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtMa      = UITheme.createTextField(); txtMa.setEditable(false);
        txtTen     = UITheme.createTextField();
        txtDonGia  = UITheme.createTextField();
        txtSoLuong = UITheme.createTextField();
        txtMa.setForeground(UITheme.TEXT_MUTED);

        String[] labels = {"Mã SP", "Tên sản phẩm", "Đơn giá (VNĐ)", "Số lượng"};
        JTextField[] fields = {txtMa, txtTen, txtDonGia, txtSoLuong};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i; gbc.weightx = 0;
            pnlForm.add(UITheme.createLabel(labels[i]), gbc);
            gbc.gridx = 1; gbc.weightx = 1.0;
            pnlForm.add(fields[i], gbc);
        }

        // Spacer
        gbc.gridx = 0; gbc.gridy = labels.length; gbc.weighty = 1.0; gbc.gridwidth = 2;
        pnlForm.add(Box.createGlue(), gbc);

        // ── Button Panel ────────────────────────────────────────────
        JPanel pnlButtons = new JPanel(new GridLayout(2, 2, 8, 8));
        pnlButtons.setBackground(UITheme.BG_CARD);
        pnlButtons.setBorder(new EmptyBorder(10, 10, 14, 10));

        btnThem   = UITheme.createSuccessButton("✚  Thêm mới");
        btnSua    = UITheme.createPrimaryButton("✎  Cập nhật");
        btnXoa    = UITheme.createDangerButton("✖  Xóa");
        btnLamMoi = UITheme.createSecondaryButton("↺  Làm mới");

        pnlButtons.add(btnThem);
        pnlButtons.add(btnSua);
        pnlButtons.add(btnXoa);
        pnlButtons.add(btnLamMoi);

        JPanel pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setBackground(UITheme.BG_CARD);
        pnlLeft.add(pnlForm, BorderLayout.CENTER);
        pnlLeft.add(pnlButtons, BorderLayout.SOUTH);

        // ── Center: Table ────────────────────────────────────────────
        String[] columns = {"Mã SP", "Tên sản phẩm", "Đơn giá (VNĐ)", "Số lượng"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(tableModel);
        UITheme.styleTable(table);

        JScrollPane scroll = new JScrollPane(table);
        UITheme.styleScrollPane(scroll);

        JPanel pnlTableWrapper = new JPanel(new BorderLayout());
        pnlTableWrapper.setBackground(UITheme.BG_PANEL);
        pnlTableWrapper.setBorder(new EmptyBorder(8, 8, 8, 8));

        JLabel lblTableTitle = new JLabel("  Danh sách sản phẩm");
        lblTableTitle.setFont(UITheme.FONT_SUBTITLE);
        lblTableTitle.setForeground(UITheme.ACCENT_INFO);
        lblTableTitle.setBorder(new EmptyBorder(0, 0, 6, 0));

        pnlTableWrapper.add(lblTableTitle, BorderLayout.NORTH);
        pnlTableWrapper.add(scroll, BorderLayout.CENTER);

        // ── Layout Assembly ──────────────────────────────────────────
        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.setBackground(UITheme.BG_DARK);
        pnlTop.add(pnlSearch, BorderLayout.CENTER);

        add(pnlTop, BorderLayout.NORTH);
        add(pnlLeft, BorderLayout.WEST);
        add(pnlTableWrapper, BorderLayout.CENTER);

        // ── Events ───────────────────────────────────────────────────
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                txtMa.setText(table.getValueAt(row, 0).toString());
                txtTen.setText(table.getValueAt(row, 1).toString());
                txtDonGia.setText(table.getValueAt(row, 2).toString());
                txtSoLuong.setText(table.getValueAt(row, 3).toString());
            }
        });

        btnThem.addActionListener(e -> saveAction());
        btnSua.addActionListener(e -> saveAction());
        btnXoa.addActionListener(e -> deleteAction());
        btnLamMoi.addActionListener(e -> clearForm());
        btnTimKiem.addActionListener(e -> searchAction());
        txtTimKiem.addActionListener(e -> searchAction()); // search on Enter
    }

    private void loadData() {
        try { fillTable(bus.findAll()); }
        catch (Exception ex) { MessageUtil.showError(this, ex.getMessage()); }
    }

    private void fillTable(List<SanPham> list) {
        tableModel.setRowCount(0);
        for (SanPham sp : list) {
            tableModel.addRow(new Object[]{
                sp.getMaSp(), sp.getTenSp(), sp.getDonGia(), sp.getSoLuong()
            });
        }
    }

    private void saveAction() {
        try {
            SanPham sp = new SanPham();
            String maText = txtMa.getText().trim();
            if (!maText.isEmpty()) sp.setMaSp(Integer.parseInt(maText));
            sp.setTenSp(txtTen.getText().trim());
            try { sp.setDonGia(new BigDecimal(txtDonGia.getText().trim())); }
            catch (NumberFormatException ex) { throw new IllegalArgumentException("Đơn giá không hợp lệ"); }
            try { sp.setSoLuong(Integer.parseInt(txtSoLuong.getText().trim())); }
            catch (NumberFormatException ex) { throw new IllegalArgumentException("Số lượng không hợp lệ"); }

            if (bus.save(sp)) {
                MessageUtil.showInfo(this, "Lưu thành công!");
                loadData(); clearForm();
            } else {
                MessageUtil.showError(this, "Lưu thất bại!");
            }
        } catch (Exception ex) { MessageUtil.showError(this, ex.getMessage()); }
    }

    private void deleteAction() {
        if (table.getSelectedRow() == -1) {
            MessageUtil.showError(this, "Chọn sản phẩm cần xóa!"); return;
        }
        if (MessageUtil.showConfirm(this, "Bạn có chắc muốn xóa sản phẩm này?")) {
            try {
                if (bus.delete(Integer.parseInt(txtMa.getText()))) {
                    MessageUtil.showInfo(this, "Xóa thành công!");
                    loadData(); clearForm();
                } else { MessageUtil.showError(this, "Xóa thất bại!"); }
            } catch (Exception ex) { MessageUtil.showError(this, ex.getMessage()); }
        }
    }

    private void searchAction() {
        try { fillTable(bus.searchByName(txtTimKiem.getText().trim())); }
        catch (Exception ex) { MessageUtil.showError(this, ex.getMessage()); }
    }

    private void clearForm() {
        txtMa.setText(""); txtTen.setText("");
        txtDonGia.setText(""); txtSoLuong.setText("");
        table.clearSelection();
        if (txtTimKiem.getText().isEmpty()) loadData();
    }
}
