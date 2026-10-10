package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/** Shared visual tokens for the cafe desktop UI. */
final class UiTheme {
    static final Color INK = new Color(39, 32, 27);
    static final Color COFFEE = new Color(104, 67, 45);
    static final Color CARAMEL = new Color(188, 132, 83);
    static final Color CREAM = new Color(248, 245, 239);
    static final Color PAPER = Color.WHITE;
    static final Color MUTED = new Color(119, 111, 103);
    static final Color LINE = new Color(229, 223, 215);
    static final Color GREEN = new Color(67, 110, 81);

    private UiTheme() { }

    static void primaryButton(AbstractButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(COFFEE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
        button.setOpaque(true);
        button.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
    }

    static void secondaryButton(AbstractButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(COFFEE);
        button.setBackground(PAPER);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE), new EmptyBorder(10, 14, 10, 14)));
        button.setOpaque(true);
        button.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
    }

    static void input(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setForeground(INK);
        field.setBackground(PAPER);
        field.setCaretColor(COFFEE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE), new EmptyBorder(10, 12, 10, 12)));
    }
}
