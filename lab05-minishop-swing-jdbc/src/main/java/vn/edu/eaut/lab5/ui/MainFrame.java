package vn.edu.eaut.lab5.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        UITheme.applyGlobalDefaults();
        setTitle("🛒 MiniShop — Quản Lý Bán Hàng");
        setSize(1200, 760);
        setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(UITheme.BG_DARK);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // ── Header Bar ──────────────────────────────────────────────
        JPanel header = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Gradient header
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(63, 55, 201),
                    getWidth(), 0, new Color(147, 51, 234)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        header.setPreferredSize(new Dimension(0, 58));
        header.setBorder(new EmptyBorder(0, 20, 0, 20));

        JLabel lblTitle = new JLabel("🛒  MiniShop");
        lblTitle.setFont(UITheme.FONT_TITLE);
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle, BorderLayout.WEST);

        JLabel lblSub = new JLabel("Hệ thống quản lý bán hàng  •  v1.0");
        lblSub.setFont(UITheme.FONT_SMALL);
        lblSub.setForeground(new Color(200, 200, 255));
        header.add(lblSub, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // ── Tabbed Content ───────────────────────────────────────────
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP) {
            @Override protected void paintComponent(Graphics g) {
                g.setColor(UITheme.BG_PANEL);
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabs.setBackground(UITheme.BG_PANEL);
        tabs.setForeground(UITheme.TEXT_PRIMARY);
        tabs.setBorder(new EmptyBorder(0, 0, 0, 0));

        tabs.addTab("📦  Sản Phẩm",    new SanPhamPanel());
        tabs.addTab("👥  Khách Hàng",  new KhachHangPanel());
        tabs.addTab("🧾  Hóa Đơn",     new HoaDonPanel());
        tabs.addTab("📊  Thống Kê",    new ThongKePanel());

        // Tab icon colors via UIManager already set, style each tab
        for (int i = 0; i < tabs.getTabCount(); i++) {
            tabs.setBackgroundAt(i, UITheme.BG_DARK);
        }

        // Wrap tabs in a panel with padding
        JPanel contentWrapper = new JPanel(new BorderLayout());
        contentWrapper.setBackground(UITheme.BG_PANEL);
        contentWrapper.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentWrapper.add(tabs);

        add(contentWrapper, BorderLayout.CENTER);

        // ── Status Bar ───────────────────────────────────────────────
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBackground(UITheme.BG_DARK);
        statusBar.setBorder(new EmptyBorder(4, 16, 4, 16));
        statusBar.setPreferredSize(new Dimension(0, 28));

        JLabel lblStatus = new JLabel("● Kết nối CSDL: minishop_db  |  localhost:3306");
        lblStatus.setFont(UITheme.FONT_SMALL);
        lblStatus.setForeground(UITheme.ACCENT_SUCCESS);
        statusBar.add(lblStatus, BorderLayout.WEST);

        JLabel lblTime = new JLabel("Java Swing + JDBC  •  IT3242");
        lblTime.setFont(UITheme.FONT_SMALL);
        lblTime.setForeground(UITheme.TEXT_MUTED);
        statusBar.add(lblTime, BorderLayout.EAST);

        add(statusBar, BorderLayout.SOUTH);
    }
}
