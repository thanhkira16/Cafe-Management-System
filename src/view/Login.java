package view;

import controller.Client;
import dao.UserDao;
import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import model.User;

/**
 *
 * @author Admin
 */
public class Login extends javax.swing.JFrame {

    public String emailPattern = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+[.]+[a-zA-z0-9]+$";

    /**
     * Creates new form Login
     */
    public Login() {
        setUndecorated(false);
        initComponents();
        applyModernLayout();
        btnLogin.setEnabled(false);
    }

    private void applyModernLayout() {
        setTitle("Cafe Management System | Sign in");
        setMinimumSize(new Dimension(900, 570));
        setSize(980, 600);
        getContentPane().removeAll();
        getContentPane().setLayout(new GridLayout(1, 2));

        JPanel brandPanel = new JPanel();
        brandPanel.setBackground(UiTheme.INK);
        brandPanel.setBorder(BorderFactory.createEmptyBorder(54, 48, 42, 42));
        brandPanel.setLayout(new BoxLayout(brandPanel, BoxLayout.Y_AXIS));
        JLabel brand = new JLabel("C  /  CAFE HOUSE");
        brand.setFont(new Font("Segoe UI", Font.BOLD, 17));
        brand.setForeground(new Color(226, 190, 151));
        JLabel headline = new JLabel("<html>A better day,<br>one cup at a time.</html>");
        headline.setFont(new Font("Georgia", Font.BOLD, 36));
        headline.setForeground(Color.WHITE);
        JLabel description = new JLabel("Your cafe, thoughtfully managed.");
        description.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        description.setForeground(new Color(210, 201, 190));
        JLabel accent = new JLabel("✳");
        accent.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 98));
        accent.setForeground(UiTheme.CARAMEL);
        JLabel note = new JLabel("ORDERS  ·  MENU  ·  DAILY SERVICE");
        note.setFont(new Font("Segoe UI", Font.BOLD, 10));
        note.setForeground(new Color(183, 171, 157));
        brandPanel.add(brand);
        brandPanel.add(Box.createVerticalStrut(72));
        brandPanel.add(headline);
        brandPanel.add(Box.createVerticalStrut(16));
        brandPanel.add(description);
        brandPanel.add(Box.createVerticalGlue());
        accent.setAlignmentX(Component.LEFT_ALIGNMENT);
        brandPanel.add(accent);
        brandPanel.add(Box.createVerticalStrut(18));
        brandPanel.add(note);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(UiTheme.CREAM);
        JPanel form = new JPanel();
        form.setBackground(UiTheme.CREAM);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(34, 42, 34, 42));

        jLabel1.setText("Welcome back");
        jLabel1.setFont(new Font("Georgia", Font.BOLD, 30));
        jLabel1.setForeground(UiTheme.INK);
        jLabel2.setText("Email address");
        jLabel3.setText("Password");
        for (JLabel label : new JLabel[]{jLabel2, jLabel3}) {
            label.setFont(new Font("Segoe UI", Font.BOLD, 13));
            label.setForeground(UiTheme.INK);
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        jLabel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        txtPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        UiTheme.input(txtEmail);
        UiTheme.input(txtPassword);
        btnLogin.setText("Sign in");
        btnLogin.setIcon(null);
        btnLogin.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        UiTheme.primaryButton(btnLogin);
        btnSignup.setText("Create account");
        btnSignup.setIcon(null);
        UiTheme.secondaryButton(btnSignup);
        btnForgotPassword.setText("Forgot password?");
        btnForgotPassword.setBorderPainted(false);
        btnForgotPassword.setContentAreaFilled(false);
        btnForgotPassword.setForeground(UiTheme.COFFEE);
        btnForgotPassword.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnExit.setVisible(false);

        form.add(jLabel1);
        form.add(Box.createVerticalStrut(10));
        JLabel helper = new JLabel("Sign in to continue to your workspace.");
        helper.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        helper.setForeground(UiTheme.MUTED);
        helper.setAlignmentX(Component.LEFT_ALIGNMENT);
        form.add(helper);
        form.add(Box.createVerticalStrut(34));
        form.add(jLabel2);
        form.add(Box.createVerticalStrut(8));
        form.add(txtEmail);
        form.add(Box.createVerticalStrut(20));
        form.add(jLabel3);
        form.add(Box.createVerticalStrut(8));
        form.add(txtPassword);
        form.add(Box.createVerticalStrut(26));
        form.add(btnLogin);
        form.add(Box.createVerticalStrut(12));
        JPanel links = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        links.setOpaque(false);
        links.add(btnSignup);
        links.add(btnForgotPassword);
        form.add(links);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        formPanel.add(form, gbc);

        getContentPane().add(brandPanel);
        getContentPane().add(formPanel);
        getContentPane().revalidate();
        getContentPane().repaint();
        setLocationRelativeTo(null);
    }

    public void validateFileds() {
        String email = txtEmail.getText();
        String pass = txtPassword.getText();
        if (!email.equals("") && email.matches(emailPattern) && !pass.equals("")) {
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setEnabled(false);
        }

    }

    public void clear() {
        txtEmail.setText("");
        txtPassword.setText("");
        btnLogin.setEnabled(false);
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
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnForgotPassword = new javax.swing.JButton();
        btnSignup = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("VNI-Dom", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("Login");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, -10, 130, 70));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Email");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 82, 22));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Password");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 280, 30));

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 280, 30));

        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login.png"))); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 120, 30));

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 40, 31));

        btnForgotPassword.setText("Forgot Password");
        btnForgotPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForgotPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(btnForgotPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 120, 30));

        btnSignup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new product.png"))); // NOI18N
        btnSignup.setText("Singup");
        btnSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignupActionPerformed(evt);
            }
        });
        getContentPane().add(btnSignup, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 130, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/login.jpg"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed
     public static String encodePassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());

            // Convert the byte array to a signum representation
            BigInteger signum = new BigInteger(1, messageDigest);

            // Convert the signum representation to a hex string
            String hash = signum.toString(16);

            // Add leading zeros to the hash if needed
            while (hash.length() < 32) {
                hash = "0" + hash;
            }

            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Handle the exception accordingly in your code
        }
    }
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            String email = txtEmail.getText();
            model.User user = UserDao.login(email, txtPassword.getText());
            if (user == null) {
                showError("Incorrect email or password");
                return;
            }
            setVisible(false);
            dispose();
            view.Home home = new view.Home(user.getStatus());
            home.email = email;
            AppShell.start(home, email, user.getStatus());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }

    }//GEN-LAST:event_btnLoginActionPerformed

    public void showError(String message){
        JOptionPane.showMessageDialog(rootPane, message);
    }
    
    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        // TODO add your handling code here:
        validateFileds();

    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        // TODO add your handling code here:
        validateFileds();
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void btnSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignupActionPerformed
        setVisible(false);
        new Signup().setVisible(true);
    }//GEN-LAST:event_btnSignupActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        int a = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Selcet", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            System.exit(0);
        }

    }//GEN-LAST:event_btnExitActionPerformed

    private void btnForgotPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForgotPasswordActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new ForgotPassword().setVisible(true);
    }//GEN-LAST:event_btnForgotPasswordActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnForgotPassword;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
