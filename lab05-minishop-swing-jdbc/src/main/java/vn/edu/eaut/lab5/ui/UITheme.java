package vn.edu.eaut.lab5.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Centralized UI Design System for MiniShop.
 * Provides a modern, vibrant color palette with clean component factories.
 */
public class UITheme {

    // ─── Color Palette ───────────────────────────────────────────────
    public static final Color BG_DARK       = new Color(18, 18, 35);       // Deep navy background
    public static final Color BG_PANEL      = new Color(28, 30, 55);       // Panel background
    public static final Color BG_CARD       = new Color(38, 42, 74);       // Card/form background
    public static final Color BG_TABLE_ROW  = new Color(33, 37, 65);       // Table row
    public static final Color BG_TABLE_ALT  = new Color(40, 44, 78);       // Alternating row
    public static final Color BG_TABLE_SEL  = new Color(99, 102, 241);     // Selected row (indigo)
    public static final Color BG_HEADER     = new Color(49, 54, 99);       // Table header

    public static final Color ACCENT_PRIMARY   = new Color(99,  102, 241); // Indigo
    public static final Color ACCENT_SUCCESS   = new Color(34,  197, 94);  // Green
    public static final Color ACCENT_DANGER    = new Color(239, 68,  68);  // Red
    public static final Color ACCENT_WARNING   = new Color(251, 191, 36);  // Amber
    public static final Color ACCENT_INFO      = new Color(56,  189, 248); // Sky blue
    public static final Color ACCENT_PURPLE    = new Color(168, 85,  247); // Purple

    public static final Color TEXT_PRIMARY     = new Color(240, 240, 255);
    public static final Color TEXT_SECONDARY   = new Color(148, 163, 184);
    public static final Color TEXT_MUTED       = new Color(100, 116, 139);
    public static final Color BORDER_COLOR     = new Color(55,  60,  100);

    // ─── Fonts ───────────────────────────────────────────────────────
    public static final Font FONT_TITLE    = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_BODY     = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_SMALL    = new Font("Segoe UI", Font.PLAIN, 11);
    public static final Font FONT_MONO     = new Font("Consolas", Font.PLAIN, 13);
    public static final Font FONT_BTN      = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font FONT_TABLE_H  = new Font("Segoe UI", Font.BOLD, 12);

    // ─── Borders ─────────────────────────────────────────────────────
    public static final int ARC = 12;

    public static Border cardBorder() {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            new EmptyBorder(10, 14, 10, 14)
        );
    }

    public static Border sectionBorder(String title) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1),
                "  " + title + "  ",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                FONT_SUBTITLE,
                ACCENT_INFO
            ),
            new EmptyBorder(8, 8, 8, 8)
        );
    }

    // ─── Component Factories ─────────────────────────────────────────

    /** Creates a styled primary button (indigo). */
    public static JButton createPrimaryButton(String text) {
        return createStyledButton(text, ACCENT_PRIMARY);
    }

    /** Creates a styled success button (green). */
    public static JButton createSuccessButton(String text) {
        return createStyledButton(text, ACCENT_SUCCESS);
    }

    /** Creates a styled danger button (red). */
    public static JButton createDangerButton(String text) {
        return createStyledButton(text, ACCENT_DANGER);
    }

    /** Creates a styled secondary button (muted). */
    public static JButton createSecondaryButton(String text) {
        return createStyledButton(text, BG_HEADER);
    }

    /** Creates a styled info button (sky blue). */
    public static JButton createInfoButton(String text) {
        return createStyledButton(text, ACCENT_INFO);
    }

    private static JButton createStyledButton(String text, Color baseColor) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = getBackground();
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(FONT_BTN);
        btn.setForeground(Color.WHITE);
        btn.setBackground(baseColor);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 18, 8, 18));

        // Hover effect
        Color hoverColor = baseColor.brighter();
        Color pressColor = baseColor.darker();
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(hoverColor); btn.repaint(); }
            @Override public void mouseExited(MouseEvent e)  { btn.setBackground(baseColor);  btn.repaint(); }
            @Override public void mousePressed(MouseEvent e) { btn.setBackground(pressColor); btn.repaint(); }
            @Override public void mouseReleased(MouseEvent e){ btn.setBackground(hoverColor); btn.repaint(); }
        });
        return btn;
    }

    /** Creates a modern styled text field. */
    public static JTextField createTextField() {
        JTextField tf = new JTextField();
        styleTextField(tf);
        return tf;
    }

    public static JTextField createTextField(int cols) {
        JTextField tf = new JTextField(cols);
        styleTextField(tf);
        return tf;
    }

    private static void styleTextField(JTextField tf) {
        tf.setFont(FONT_BODY);
        tf.setForeground(TEXT_PRIMARY);
        tf.setBackground(BG_DARK);
        tf.setCaretColor(ACCENT_PRIMARY);
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            new EmptyBorder(6, 10, 6, 10)
        ));
    }

    /** Creates a styled label. */
    public static JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(FONT_BODY);
        lbl.setForeground(TEXT_SECONDARY);
        return lbl;
    }

    /** Creates a bold value label. */
    public static JLabel createValueLabel(String text, Color color) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lbl.setForeground(color);
        return lbl;
    }

    /** Applies dark theme styling to a JTable. */
    public static void styleTable(JTable table) {
        table.setFont(FONT_BODY);
        table.setForeground(TEXT_PRIMARY);
        table.setBackground(BG_TABLE_ROW);
        table.setGridColor(BORDER_COLOR);
        table.setRowHeight(32);
        table.setSelectionBackground(BG_TABLE_SEL);
        table.setSelectionForeground(Color.WHITE);
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 1));

        // Header
        table.getTableHeader().setFont(FONT_TABLE_H);
        table.getTableHeader().setForeground(TEXT_PRIMARY);
        table.getTableHeader().setBackground(BG_HEADER);
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ACCENT_PRIMARY));
        table.getTableHeader().setPreferredSize(new Dimension(0, 38));

        // Alternating rows
        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, col);
                setFont(FONT_BODY);
                setBorder(new EmptyBorder(0, 12, 0, 12));
                if (isSelected) {
                    setBackground(BG_TABLE_SEL);
                    setForeground(Color.WHITE);
                } else {
                    setBackground(row % 2 == 0 ? BG_TABLE_ROW : BG_TABLE_ALT);
                    setForeground(TEXT_PRIMARY);
                }
                return this;
            }
        });
    }

    /** Applies dark styling to a JScrollPane. */
    public static void styleScrollPane(JScrollPane sp) {
        sp.setBackground(BG_PANEL);
        sp.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        sp.getViewport().setBackground(BG_TABLE_ROW);
        styleScrollBar(sp.getVerticalScrollBar());
        styleScrollBar(sp.getHorizontalScrollBar());
    }

    private static void styleScrollBar(JScrollBar sb) {
        sb.setBackground(BG_PANEL);
        sb.setUI(new BasicScrollBarUI() {
            @Override protected void configureScrollBarColors() {
                thumbColor = ACCENT_PRIMARY.darker();
                trackColor = BG_PANEL;
            }
            @Override protected JButton createDecreaseButton(int o) { return makeZeroButton(); }
            @Override protected JButton createIncreaseButton(int o) { return makeZeroButton(); }
            private JButton makeZeroButton() {
                JButton b = new JButton();
                b.setPreferredSize(new Dimension(0,0));
                b.setMinimumSize(new Dimension(0,0));
                b.setMaximumSize(new Dimension(0,0));
                return b;
            }
        });
    }

    /** Styles a JComboBox with the dark theme. */
    @SuppressWarnings("rawtypes")
    public static <T> JComboBox<T> createComboBox() {
        JComboBox<T> cb = new JComboBox<>();
        cb.setFont(FONT_BODY);
        cb.setForeground(TEXT_PRIMARY);
        cb.setBackground(BG_DARK);
        cb.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        cb.setRenderer(new javax.swing.plaf.basic.BasicComboBoxRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setFont(FONT_BODY);
                setForeground(isSelected ? Color.WHITE : TEXT_PRIMARY);
                setBackground(isSelected ? ACCENT_PRIMARY : BG_CARD);
                setBorder(new EmptyBorder(6, 10, 6, 10));
                return this;
            }
        });
        return cb;
    }

    /** Applies global dark Look & Feel hints. Call once before creating any frames. */
    public static void applyGlobalDefaults() {
        UIManager.put("Panel.background", BG_PANEL);
        UIManager.put("OptionPane.background", BG_CARD);
        UIManager.put("OptionPane.messageForeground", TEXT_PRIMARY);
        UIManager.put("OptionPane.messageFont", FONT_BODY);
        UIManager.put("OptionPane.buttonFont", FONT_BTN);
        UIManager.put("Label.foreground", TEXT_SECONDARY);
        UIManager.put("Label.font", FONT_BODY);
        UIManager.put("TextField.background", BG_DARK);
        UIManager.put("TextField.foreground", TEXT_PRIMARY);
        UIManager.put("TextField.caretForeground", ACCENT_PRIMARY);
        UIManager.put("ComboBox.background", BG_DARK);
        UIManager.put("ComboBox.foreground", TEXT_PRIMARY);
        UIManager.put("ScrollPane.background", BG_PANEL);
        UIManager.put("Viewport.background", BG_PANEL);
        UIManager.put("TabbedPane.background", BG_PANEL);
        UIManager.put("TabbedPane.foreground", TEXT_PRIMARY);
        UIManager.put("TabbedPane.selected", BG_CARD);
        UIManager.put("TabbedPane.focus", ACCENT_PRIMARY);
        UIManager.put("TabbedPane.contentAreaColor", BG_PANEL);
        UIManager.put("TabbedPane.tabAreaBackground", BG_DARK);
        UIManager.put("Button.background", ACCENT_PRIMARY);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("PopupMenu.background", BG_CARD);
        UIManager.put("MenuItem.background", BG_CARD);
        UIManager.put("MenuItem.foreground", TEXT_PRIMARY);
    }
}
