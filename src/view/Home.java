
package view;


import controller.Client;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.awt.*;

import model.User;
       
/**
 *
 * @author Admin
 */
public class Home extends javax.swing.JFrame {
    public String email;
    /**
     * Creates new form Home
     */
    
     public Home(String status) {
        setUndecorated(false);
        initComponents();
        applyModernLayout();
        if(status.equals("false")){
            btnAdmin.setEnabled(false);
        }else if(status.equals("true")){
            btnAdmin.setEnabled(true);
        }
    }

    private void applyModernLayout() {
        setTitle("Cafe House | Dashboard");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1040, 680));
        setSize(1180, 760);
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(UiTheme.CREAM);

        JPanel sidebar = new JPanel();
        sidebar.setBackground(UiTheme.INK);
        sidebar.setPreferredSize(new Dimension(240, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(30, 18, 24, 18));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        JLabel brand = new JLabel("C  /  CAFE HOUSE");
        brand.setFont(new Font("Segoe UI", Font.BOLD, 17));
        brand.setForeground(new Color(230, 192, 151));
        JLabel menuLabel = new JLabel("WORKSPACE");
        menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        menuLabel.setForeground(new Color(173, 161, 146));
        menuLabel.setBorder(BorderFactory.createEmptyBorder(44, 10, 12, 0));
        sidebar.add(brand);
        sidebar.add(menuLabel);
        sidebar.add(navLink("Place an order", btnPlaceOrder));
        sidebar.add(Box.createVerticalStrut(8));
        sidebar.add(navLink("Orders & bills", btnViewBill));
        sidebar.add(Box.createVerticalStrut(8));
        sidebar.add(navLink("Manage cafe", btnAdmin));
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(navLink("Settings", btnExit2));
        sidebar.add(Box.createVerticalStrut(8));
        sidebar.add(navLink("Sign out", btnLogout));

        JPanel body = new JPanel(new BorderLayout(0, 24));
        body.setBackground(UiTheme.CREAM);
        body.setBorder(BorderFactory.createEmptyBorder(28, 36, 34, 36));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        JPanel titleGroup = new JPanel();
        titleGroup.setOpaque(false);
        titleGroup.setLayout(new BoxLayout(titleGroup, BoxLayout.Y_AXIS));
        JLabel eyebrow = new JLabel("CAFE MANAGEMENT");
        eyebrow.setFont(new Font("Segoe UI", Font.BOLD, 10));
        eyebrow.setForeground(UiTheme.CARAMEL);
        JLabel title = new JLabel("Good day. Ready to serve?");
        title.setFont(new Font("Georgia", Font.BOLD, 28));
        title.setForeground(UiTheme.INK);
        JLabel subtitle = new JLabel("Manage today's orders, menu and cafe settings.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(UiTheme.MUTED);
        titleGroup.add(eyebrow);
        titleGroup.add(Box.createVerticalStrut(8));
        titleGroup.add(title);
        titleGroup.add(Box.createVerticalStrut(6));
        titleGroup.add(subtitle);
        JLabel badge = new JLabel("  MANAGEMENT  ");
        badge.setOpaque(true);
        badge.setBackground(new Color(229, 238, 228));
        badge.setForeground(UiTheme.GREEN);
        badge.setFont(new Font("Segoe UI", Font.BOLD, 11));
        badge.setBorder(BorderFactory.createEmptyBorder(8, 6, 8, 6));
        top.add(titleGroup, BorderLayout.CENTER);
        top.add(badge, BorderLayout.EAST);

        JPanel content = new JPanel(new BorderLayout(0, 14));
        content.setOpaque(false);
        JLabel section = new JLabel("Quick actions");
        section.setFont(new Font("Segoe UI", Font.BOLD, 17));
        section.setForeground(UiTheme.INK);
        content.add(section, BorderLayout.NORTH);

        JPanel actions = new JPanel(new GridLayout(2, 2, 16, 16));
        actions.setOpaque(false);
        actions.add(actionCard("01", "Place an order", "Create a customer order and prepare a bill.", btnPlaceOrder));
        actions.add(actionCard("02", "Orders & bills", "Review order history and printed bills.", btnViewBill));
        actions.add(actionCard("03", "Manage cafe", "Update products, categories and staff access.", btnAdmin));
        actions.add(actionCard("04", "Preferences", "Change your password and security question.", btnExit2));
        content.add(actions, BorderLayout.CENTER);

        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(UiTheme.PAPER);
        footer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiTheme.LINE), BorderFactory.createEmptyBorder(17, 20, 17, 20)));
        JLabel footerTitle = new JLabel("A calmer way to run your cafe");
        footerTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        footerTitle.setForeground(UiTheme.INK);
        JLabel footerText = new JLabel("Keep your menu organized and your service moving.");
        footerText.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerText.setForeground(UiTheme.MUTED);
        JPanel footerCopy = new JPanel();
        footerCopy.setOpaque(false);
        footerCopy.setLayout(new BoxLayout(footerCopy, BoxLayout.Y_AXIS));
        footerCopy.add(footerTitle);
        footerCopy.add(Box.createVerticalStrut(4));
        footerCopy.add(footerText);
        footer.add(footerCopy, BorderLayout.CENTER);
        JLabel mark = new JLabel("BREW  ·  SERVE  ·  REPEAT");
        mark.setFont(new Font("Segoe UI", Font.BOLD, 10));
        mark.setForeground(UiTheme.CARAMEL);
        footer.add(mark, BorderLayout.EAST);

        body.add(top, BorderLayout.NORTH);
        body.add(content, BorderLayout.CENTER);
        body.add(footer, BorderLayout.SOUTH);
        getContentPane().add(body, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    private javax.swing.JButton navLink(String text, javax.swing.JButton action) {
        javax.swing.JButton button = new javax.swing.JButton(text);
        button.addActionListener(event -> action.doClick());
        button.setText(text);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(60, 51, 44));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));
        button.setOpaque(true);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setEnabled(action.isEnabled());
        return button;
    }

    private JPanel actionCard(String number, String heading, String description, javax.swing.JButton action) {
        JPanel card = new JPanel(new BorderLayout(0, 10));
        card.setBackground(UiTheme.PAPER);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiTheme.LINE), BorderFactory.createEmptyBorder(18, 19, 16, 19)));
        JLabel index = new JLabel(number);
        index.setFont(new Font("Segoe UI", Font.BOLD, 11));
        index.setForeground(UiTheme.CARAMEL);
        JLabel label = new JLabel(heading);
        label.setFont(new Font("Segoe UI", Font.BOLD, 17));
        label.setForeground(UiTheme.INK);
        JLabel detail = new JLabel("<html><div style='width:220px'>" + description + "</div></html>");
        detail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        detail.setForeground(UiTheme.MUTED);
        action.setText("Open  →");
        action.setIcon(null);
        action.setHorizontalAlignment(SwingConstants.LEFT);
        action.setFont(new Font("Segoe UI", Font.BOLD, 12));
        action.setForeground(UiTheme.COFFEE);
        action.setBackground(UiTheme.CREAM);
        action.setFocusPainted(false);
        action.setBorder(BorderFactory.createEmptyBorder(9, 12, 9, 12));
        action.setOpaque(true);
        JPanel headingBlock = new JPanel();
        headingBlock.setOpaque(false);
        headingBlock.setLayout(new BoxLayout(headingBlock, BoxLayout.Y_AXIS));
        headingBlock.add(index);
        headingBlock.add(Box.createVerticalStrut(4));
        headingBlock.add(label);
        headingBlock.add(Box.createVerticalStrut(4));
        headingBlock.add(detail);
        card.add(headingBlock, BorderLayout.CENTER);
        card.add(action, BorderLayout.SOUTH);
        return card;
    }
    public void exit(){
        this.setVisible(false);
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnPlaceOrder = new javax.swing.JButton();
        btnViewBill = new javax.swing.JButton();
        btnAdmin = new javax.swing.JButton();
        btnExit2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnChat = new javax.swing.JButton();
        txtMessage = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hmoe1.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hmoe1.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogout.setBackground(new java.awt.Color(102, 102, 102));
        btnLogout.setFont(new java.awt.Font("VNI-Aptima", 1, 18)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(-140, 30, -1, -1));

        btnPlaceOrder.setBackground(new java.awt.Color(102, 102, 102));
        btnPlaceOrder.setFont(new java.awt.Font("VNI-Aptima", 1, 18)); // NOI18N
        btnPlaceOrder.setForeground(new java.awt.Color(255, 255, 255));
        btnPlaceOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/place order.png"))); // NOI18N
        btnPlaceOrder.setText("Place Order");
        btnPlaceOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlaceOrderActionPerformed(evt);
            }
        });
        getContentPane().add(btnPlaceOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 400, 50));

        btnViewBill.setBackground(new java.awt.Color(102, 102, 102));
        btnViewBill.setFont(new java.awt.Font("VNI-Aptima", 1, 18)); // NOI18N
        btnViewBill.setForeground(new java.awt.Color(255, 255, 255));
        btnViewBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/View Bills & Order Placed Details.png"))); // NOI18N
        btnViewBill.setText("View Bill & Order Placed Details");
        btnViewBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewBillActionPerformed(evt);
            }
        });
        getContentPane().add(btnViewBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 400, 50));

        btnAdmin.setBackground(new java.awt.Color(102, 102, 102));
        btnAdmin.setFont(new java.awt.Font("VNI-Aptima", 1, 18)); // NOI18N
        btnAdmin.setForeground(new java.awt.Color(255, 255, 255));
        btnAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admion.png"))); // NOI18N
        btnAdmin.setText("Admin");
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 400, 50));

        btnExit2.setBackground(new java.awt.Color(255, 204, 153));
        btnExit2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnExit2.setText("Settings");
        btnExit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 90, 20));

        jButton1.setBackground(new java.awt.Color(255, 204, 153));
        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 110, 20));

        btnChat.setBackground(new java.awt.Color(0, 255, 204));
        btnChat.setText("send");
        btnChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatActionPerformed(evt);
            }
        });
        getContentPane().add(btnChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 380, 70, 40));
        getContentPane().add(txtMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 320, 40));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 400, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/full-page-background.PNG"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-80, -10, 640, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
         int a = JOptionPane.showConfirmDialog(null, "Do you want to logout?", "Selecct",  JOptionPane.YES_NO_OPTION);
        if(a==0){
            setVisible(false);
            AppShell.logout();
        }

    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnPlaceOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlaceOrderActionPerformed
        // TODO add your handling code here:
        AppShell.open(new PlaceOrder(email));
    }//GEN-LAST:event_btnPlaceOrderActionPerformed

    private void btnViewBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewBillActionPerformed
        // TODO add your handling code here:
        AppShell.open(new ViewBillOrderPlacedDetails());
    }//GEN-LAST:event_btnViewBillActionPerformed

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
        // TODO add your handling code here:
        AppShell.open(new Admin());
    }//GEN-LAST:event_btnAdminActionPerformed

    private void btnExit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit2ActionPerformed
        // TODO add your handling code here:
        AppShell.open(new Settings(email));
    }//GEN-LAST:event_btnExit2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        AppShell.logout();
    }//GEN-LAST:event_jButton1ActionPerformed
     public void addMessage(String message){
        String temp = jTextArea1.getText();
        temp+=message+"\n";
        jTextArea1.setText(temp);
        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
    }
    private void sendMessage(){
        try {
            if (txtMessage.getText().isEmpty()) {
                throw new Exception("Vui lòng nhập nội dung tin nhắn");
            }
            String temp = jTextArea1.getText();
            temp += "Tôi: " + txtMessage.getText() + "\n";
            jTextArea1.setText(temp);
            Client.socketHandle.write("chat-server," + txtMessage.getText());
            txtMessage.setText("");
            jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }
    
    private void btnChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatActionPerformed
        sendMessage();
    }//GEN-LAST:event_btnChatActionPerformed
    
    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnChat;
    private javax.swing.JButton btnExit2;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPlaceOrder;
    private javax.swing.JButton btnViewBill;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtMessage;
    // End of variables declaration//GEN-END:variables
}
