package vn.edu.eaut.lab5.ui;

import vn.edu.eaut.lab5.bus.KhachHangBUS;
import vn.edu.eaut.lab5.model.KhachHang;
import vn.edu.eaut.lab5.util.MessageUtil;
import vn.edu.eaut.lab5.util.PhoneDocumentFilter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.util.List;

public class KhachHangPanel extends JPanel {
    private final KhachHangBUS bus = new KhachHangBUS();

    private JTextField txtMa, txtTen, txtSdt, txtDiaChi, txtTimKiem;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnTimKiem;

    public KhachHangPanel() {
        setLayout(new BorderLayout(0, 0));
        setBackground(UITheme.BG_PANEL);
        initComponents();
        loadData();
    }

    private void initComponents() {
        // ── Search Bar ───────────────────────────────────────────────
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlSearch.setBackground(UITheme.BG_DARK);
        pnlSearch.setBorder(new EmptyBorder(6, 10, 6, 10));

        txtTimKiem = UITheme.createTextField(22);
        btnTimKiem = UITheme.createInfoButton("Tìm kiếm");

        pnlSearch.add(UITheme.createLabel("🔍  Tìm tên / SĐT:"));
        pnlSearch.add(txtTimKiem);
        pnlSearch.add(btnTimKiem);

        // ── Left Form ────────────────────────────────────────────────
        JPanel pnlForm = new JPanel(new GridBagLayout());
        pnlForm.setBackground(UITheme.BG_CARD);
        pnlForm.setBorder(UITheme.sectionBorder("Thông tin Khách Hàng"));
        pnlForm.setPreferredSize(new Dimension(290, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 6, 7, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtMa     = UITheme.createTextField(); txtMa.setEditable(false); txtMa.setForeground(UITheme.TEXT_MUTED);
        txtTen    = UITheme.createTextField();
        txtSdt    = UITheme.createTextField();
        txtDiaChi = UITheme.createTextField();
        ((AbstractDocument) txtSdt.getDocument()).setDocumentFilter(new PhoneDocumentFilter());

        String[] labels = {"Mã KH", "Tên khách hàng", "Số điện thoại", "Địa chỉ"};
        JTextField[] fields = {txtMa, txtTen, txtSdt, txtDiaChi};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i; gbc.weightx = 0;
            pnlForm.add(UITheme.createLabel(labels[i]), gbc);
            gbc.gridx = 1; gbc.weightx = 1.0;
            pnlForm.add(fields[i], gbc);
        }

        // SDT hint
        JLabel lblHint = new JLabel("  ℹ Tối đa 10 chữ số");
        lblHint.setFont(UITheme.FONT_SMALL);
        lblHint.setForeground(UITheme.TEXT_MUTED);
        gbc.gridx = 1; gbc.gridy = labels.length; gbc.gridwidth = 1;
        pnlForm.add(lblHint, gbc);

        gbc.gridy = labels.length + 1; gbc.weighty = 1.0; gbc.gridwidth = 2;
        pnlForm.add(Box.createGlue(), gbc);

        // ── Buttons ──────────────────────────────────────────────────
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

        // ── Table ────────────────────────────────────────────────────
        String[] columns = {"Mã KH", "Tên khách hàng", "Số điện thoại", "Địa chỉ"};
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

        JLabel lblTitle = new JLabel("  Danh sách khách hàng");
        lblTitle.setFont(UITheme.FONT_SUBTITLE);
        lblTitle.setForeground(UITheme.ACCENT_INFO);
        lblTitle.setBorder(new EmptyBorder(0, 0, 6, 0));

        pnlTableWrapper.add(lblTitle, BorderLayout.NORTH);
        pnlTableWrapper.add(scroll, BorderLayout.CENTER);

        // ── Assembly ─────────────────────────────────────────────────
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
                txtSdt.setText(table.getValueAt(row, 2).toString());
                txtDiaChi.setText(table.getValueAt(row, 3) != null ? table.getValueAt(row, 3).toString() : "");
            }
        });

        btnThem.addActionListener(e -> saveAction());
        btnSua.addActionListener(e -> saveAction());
        btnXoa.addActionListener(e -> deleteAction());
        btnLamMoi.addActionListener(e -> clearForm());
        btnTimKiem.addActionListener(e -> searchAction());
        txtTimKiem.addActionListener(e -> searchAction());
    }

    private void loadData() {
        try { fillTable(bus.findAll()); }
        catch (Exception ex) { MessageUtil.showError(this, ex.getMessage()); }
    }

    private void fillTable(List<KhachHang> list) {
        tableModel.setRowCount(0);
        for (KhachHang kh : list)
            tableModel.addRow(new Object[]{kh.getMaKh(), kh.getTenKh(), kh.getSdt(), kh.getDiaChi()});
    }

    private void saveAction() {
        try {
            KhachHang kh = new KhachHang();
            String maText = txtMa.getText().trim();
            if (!maText.isEmpty()) kh.setMaKh(Integer.parseInt(maText));
            kh.setTenKh(txtTen.getText().trim());
            kh.setSdt(txtSdt.getText().trim());
            kh.setDiaChi(txtDiaChi.getText().trim());

            if (bus.save(kh)) {
                MessageUtil.showInfo(this, "Lưu thành công!");
                loadData(); clearForm();
            } else { MessageUtil.showError(this, "Lưu thất bại!"); }
        } catch (Exception ex) { MessageUtil.showError(this, ex.getMessage()); }
    }

    private void deleteAction() {
        if (table.getSelectedRow() == -1) {
            MessageUtil.showError(this, "Chọn khách hàng cần xóa!"); return;
        }
        if (MessageUtil.showConfirm(this, "Bạn có chắc muốn xóa khách hàng này?")) {
            try {
                if (bus.delete(Integer.parseInt(txtMa.getText()))) {
                    MessageUtil.showInfo(this, "Xóa thành công!");
                    loadData(); clearForm();
                } else { MessageUtil.showError(this, "Xóa thất bại!"); }
            } catch (Exception ex) { MessageUtil.showError(this, ex.getMessage()); }
        }
    }

    private void searchAction() {
        try { fillTable(bus.searchByKeyword(txtTimKiem.getText().trim())); }
        catch (Exception ex) { MessageUtil.showError(this, ex.getMessage()); }
    }

    private void clearForm() {
        txtMa.setText(""); txtTen.setText("");
        txtSdt.setText(""); txtDiaChi.setText("");
        table.clearSelection();
        if (txtTimKiem.getText().isEmpty()) loadData();
    }
}
