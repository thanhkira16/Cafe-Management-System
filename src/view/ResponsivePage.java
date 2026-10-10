package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/** Shared responsive building blocks for the modernized Swing pages. */
final class ResponsivePage {
    private ResponsivePage() { }

    static JPanel root(String title, String description) {
        JPanel page = new JPanel(new BorderLayout(0, 18));
        page.setBackground(UiTheme.CREAM);
        page.setBorder(new EmptyBorder(8, 8, 12, 8));

        JPanel heading = new JPanel(new BorderLayout(0, 5));
        heading.setOpaque(false);
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 25));
        titleLabel.setForeground(UiTheme.INK);
        JLabel subtitle = new JLabel(description);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(UiTheme.MUTED);
        heading.add(titleLabel, BorderLayout.NORTH);
        heading.add(subtitle, BorderLayout.CENTER);
        page.add(heading, BorderLayout.NORTH);
        return page;
    }

    static JPanel card() {
        JPanel card = new JPanel(new BorderLayout(0, 14));
        card.setBackground(UiTheme.PAPER);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiTheme.LINE), new EmptyBorder(16, 16, 16, 16)));
        return card;
    }

    static JPanel card(LayoutManager layout) {
        JPanel card = new JPanel(layout);
        card.setBackground(UiTheme.PAPER);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiTheme.LINE), new EmptyBorder(16, 16, 16, 16)));
        return card;
    }

    static JLabel section(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        label.setForeground(UiTheme.INK);
        return label;
    }

    static JLabel fieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(UiTheme.MUTED);
        return label;
    }

    static JPanel field(String label, JComponent input) {
        JPanel wrapper = new JPanel(new BorderLayout(0, 6));
        wrapper.setOpaque(false);
        wrapper.add(fieldLabel(label), BorderLayout.NORTH);
        styleInput(input);
        wrapper.add(input, BorderLayout.CENTER);
        return wrapper;
    }

    static void styleInput(JComponent input) {
        input.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        input.setForeground(UiTheme.INK);
        input.setBackground(Color.WHITE);
        input.setMinimumSize(new Dimension(60, 38));
        if (input instanceof JTextField text) UiTheme.input(text);
        else input.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiTheme.LINE), new EmptyBorder(5, 9, 5, 9)));
        if (input instanceof JComboBox<?> combo) combo.setEditable(false);
        if (input instanceof JSpinner spinner) spinner.setOpaque(true);
    }

    static JScrollPane table(JTable table) {
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setForeground(UiTheme.INK);
        table.setBackground(Color.WHITE);
        table.setGridColor(UiTheme.LINE);
        table.setRowHeight(31);
        table.setSelectionBackground(new Color(242, 229, 213));
        table.setSelectionForeground(UiTheme.INK);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setForeground(UiTheme.INK);
        table.getTableHeader().setBackground(new Color(245, 239, 230));
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(BorderFactory.createLineBorder(UiTheme.LINE));
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    static void install(JFrame frame, JPanel page) {
        frame.setContentPane(page);
        frame.getRootPane().putClientProperty("cafe.responsivePage", Boolean.TRUE);
    }

}
