package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

/** One application window with a persistent sidebar and replaceable page content. */
final class AppShell {
    private static AppShell instance;

    private final JFrame window = new JFrame("Cafe House | Management");
    private final JPanel pages = new JPanel(new CardLayout());
    private final Map<String, Component> pageComponents = new HashMap<>();
    private final String email;
    private final String status;

    private AppShell(String email, String status) {
        this.email = email;
        this.status = status;
        buildWindow();
    }

    static void start(Home dashboard, String email, String status) {
        if (instance != null) instance.window.dispose();
        instance = new AppShell(email, status);
        instance.install(dashboard, Home.class.getName());
        instance.window.setVisible(true);
    }

    static void open(JFrame page) {
        if (instance == null) {
            page.setVisible(true);
            return;
        }
        instance.prepareAndInstall(page);
    }

    static void showDashboard() {
        if (instance == null) return;
        String key = Home.class.getName();
        if (instance.pageComponents.containsKey(key)) {
            instance.show(key);
        } else {
            Home home = new Home(instance.status);
            home.email = instance.email;
            instance.install(home, key);
        }
    }

    static void back(JFrame page) {
        if (instance == null) page.setVisible(false);
        else showDashboard();
    }

    static void logout() {
        if (instance != null) {
            instance.window.dispose();
            instance = null;
        }
        new Login().setVisible(true);
    }

    private void buildWindow() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Rectangle desktop = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int initialWidth = Math.min(1280, desktop.width);
        int initialHeight = Math.min(800, desktop.height);
        int minWidth = Math.min(960, initialWidth);
        int minHeight = Math.min(620, initialHeight);
        window.setMinimumSize(new Dimension(minWidth, minHeight));
        window.setSize(initialWidth, initialHeight);
        window.setLocationRelativeTo(null);
        window.getContentPane().setLayout(new BorderLayout());
        window.getContentPane().setBackground(UiTheme.CREAM);
        window.getContentPane().add(buildSidebar(), BorderLayout.WEST);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(UiTheme.CREAM);
        content.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        pages.setBackground(UiTheme.CREAM);
        content.add(pages, BorderLayout.CENTER);
        window.getContentPane().add(content, BorderLayout.CENTER);
        window.addWindowListener(new WindowAdapter() {
            @Override public void windowClosed(WindowEvent event) {
                if (instance != null && instance.window == window) instance = null;
            }
        });
    }

    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(UiTheme.INK);
        sidebar.setPreferredSize(new Dimension(238, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(28, 16, 22, 16));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel brand = new JLabel("C  /  CAFE HOUSE");
        brand.setFont(new Font("Segoe UI", Font.BOLD, 16));
        brand.setForeground(new Color(230, 192, 151));
        JLabel section = new JLabel("WORKSPACE");
        section.setFont(new Font("Segoe UI", Font.BOLD, 10));
        section.setForeground(new Color(173, 161, 146));
        section.setBorder(BorderFactory.createEmptyBorder(38, 10, 10, 0));
        sidebar.add(brand);
        sidebar.add(section);
        sidebar.add(navItem("Dashboard", () -> showDashboard()));
        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(navItem("Place an order", () -> open(new PlaceOrder(email))));
        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(navItem("Orders & bills", () -> open(new ViewBillOrderPlacedDetails())));

        if ("true".equalsIgnoreCase(status)) {
            JLabel adminSection = new JLabel("MENU MANAGEMENT");
            adminSection.setFont(new Font("Segoe UI", Font.BOLD, 10));
            adminSection.setForeground(new Color(173, 161, 146));
            adminSection.setBorder(BorderFactory.createEmptyBorder(28, 10, 10, 0));
            sidebar.add(adminSection);
            sidebar.add(navItem("Products", () -> open(new ViewEditDeleteProduct())));
            sidebar.add(Box.createVerticalStrut(6));
            sidebar.add(navItem("Add product", () -> open(new NewProduct())));
            sidebar.add(Box.createVerticalStrut(6));
            sidebar.add(navItem("Categories", () -> open(new ManageCategory())));
            sidebar.add(Box.createVerticalStrut(6));
            sidebar.add(navItem("Staff access", () -> open(new VerifyUser())));
        }

        sidebar.add(Box.createVerticalGlue());
        JLabel account = new JLabel("SIGNED IN AS");
        account.setFont(new Font("Segoe UI", Font.BOLD, 9));
        account.setForeground(new Color(173, 161, 146));
        JLabel accountName = new JLabel(email == null ? "Cafe user" : email);
        accountName.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        accountName.setForeground(Color.WHITE);
        accountName.setBorder(BorderFactory.createEmptyBorder(4, 10, 18, 0));
        sidebar.add(account);
        sidebar.add(accountName);
        sidebar.add(navItem("Settings", () -> open(new Settings(email))));
        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(navItem("Sign out", AppShell::logout));
        return sidebar;
    }

    private AbstractButton navItem(String text, Runnable action) {
        javax.swing.JButton button = new javax.swing.JButton(text);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(60, 51, 44));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 13, 10, 13));
        button.setOpaque(true);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(event -> action.run());
        return button;
    }

    private void prepareAndInstall(JFrame page) {
        String key = page.getClass().getName();
        // Preserve legacy componentShown data loading without flashing a second window.
        ComponentEvent event = new ComponentEvent(page, ComponentEvent.COMPONENT_SHOWN);
        for (ComponentListener listener : page.getComponentListeners()) listener.componentShown(event);
        install(page, key);
    }

    private void install(JFrame page, String key) {
        Container content = page.getContentPane();
        if (!(page instanceof Home)
                && !Boolean.TRUE.equals(page.getRootPane().getClientProperty("cafe.responsivePage"))) {
            polish(content);
        }
        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(UiTheme.CREAM);
        holder.add(content, BorderLayout.CENTER);
        JScrollPane scroll = new JScrollPane(holder,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(UiTheme.CREAM);
        scroll.getVerticalScrollBar().setUnitIncrement(18);
        scroll.getHorizontalScrollBar().setUnitIncrement(18);
        scroll.setWheelScrollingEnabled(true);
        Component previous = pageComponents.put(key, scroll);
        if (previous != null) pages.remove(previous);
        pages.add(scroll, key);
        page.setContentPane(new JPanel());
        page.dispose();
        pages.revalidate();
        pages.repaint();
        show(key);
    }

    private void show(String key) {
        CardLayout layout = (CardLayout) pages.getLayout();
        layout.show(pages, key);
    }

    private void polish(Container root) {
        root.setBackground(UiTheme.CREAM);
        for (Component component : root.getComponents()) {
            if (component instanceof JLabel label && isFullPageArtwork(label, root)) {
                root.remove(component);
                continue;
            }
            if (component instanceof AbstractButton button) {
                if (button.getText() == null || button.getText().isBlank()) button.setText("×");
                button.setIcon(null);
                button.setFont(new Font("Segoe UI", Font.BOLD, 13));
                button.setForeground(UiTheme.COFFEE);
                button.setBackground(Color.WHITE);
                button.setFocusPainted(false);
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(UiTheme.LINE), BorderFactory.createEmptyBorder(9, 13, 9, 13)));
                button.setOpaque(true);
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                if (button.getText().toLowerCase().contains("save")
                        || button.getText().toLowerCase().contains("generate bill")) {
                    button.setForeground(Color.WHITE);
                    button.setBackground(UiTheme.COFFEE);
                }
            } else if (component instanceof JTextField field) {
                UiTheme.input(field);
            } else if (component instanceof JComboBox<?> combo) {
                combo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                combo.setForeground(UiTheme.INK);
                combo.setBackground(Color.WHITE);
                combo.setBorder(BorderFactory.createLineBorder(UiTheme.LINE));
            } else if (component instanceof JSpinner spinner) {
                spinner.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                spinner.setBorder(BorderFactory.createLineBorder(UiTheme.LINE));
            } else if (component instanceof JTextArea area) {
                area.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                area.setForeground(UiTheme.INK);
                area.setBackground(Color.WHITE);
                area.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
            } else if (component instanceof JTable table) {
                table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                table.setForeground(UiTheme.INK);
                table.setBackground(Color.WHITE);
                table.setGridColor(UiTheme.LINE);
                table.setRowHeight(30);
                table.setSelectionBackground(new Color(239, 226, 211));
                table.setSelectionForeground(UiTheme.INK);
                table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
                table.getTableHeader().setBackground(new Color(241, 236, 228));
                table.getTableHeader().setForeground(UiTheme.INK);
            } else if (component instanceof JLabel label && label.getIcon() == null) {
                label.setForeground(UiTheme.INK);
                label.setFont(new Font("Segoe UI", label.getFont().getStyle(), Math.max(12, label.getFont().getSize())));
            }
            if (component instanceof Container nested) {
                if (nested instanceof JScrollPane scrollPane) {
                    scrollPane.setBackground(Color.WHITE);
                    scrollPane.setBorder(BorderFactory.createLineBorder(UiTheme.LINE));
                    scrollPane.getViewport().setBackground(Color.WHITE);
                } else {
                    nested.setBackground(UiTheme.CREAM);
                }
                polish(nested);
            }
        }
        root.revalidate();
        root.repaint();
    }

    private boolean isFullPageArtwork(JLabel label, Container parent) {
        Icon icon = label.getIcon();
        if (!(icon instanceof ImageIcon)) return false;
        return icon.getIconWidth() >= parent.getWidth() * 0.58
                && icon.getIconHeight() >= parent.getHeight() * 0.48;
    }
}
