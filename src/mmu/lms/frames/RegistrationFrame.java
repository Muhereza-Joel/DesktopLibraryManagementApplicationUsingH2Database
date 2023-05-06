/*
 * Created by JFormDesigner on Mon Sep 19 23:16:28 EAT 2022
 */

package mmu.lms.frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Muhereza Joel
 */
public class RegistrationFrame extends JFrame {
    public RegistrationFrame() {
        initComponents();
        setTitle("Mountains of the Moon University LMS");
        setMinimumSize(new Dimension(600,475));
        setResizable(false);
        setLocationRelativeTo(this);
        setVisible(true);
        panel1.setBorder(null);

    }

    private void registerUser(ActionEvent e) {
        // TODO add your code here
    }

    private void cancelUserRegistration(ActionEvent e) {
        // TODO add your code here
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        userNameTextField = new JTextField();
        emailTextField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
        signUpButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x0c0c56));
            panel1.setForeground(Color.white);
            panel1.setLayout(null);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/mmulogosmall.png")));
            panel1.add(label1);
            label1.setBounds(270, 15, 75, 80);

            //---- label2 ----
            label2.setText("Mountains of the Moon University");
            label2.setForeground(Color.white);
            label2.setFont(new Font("Segoe UI Light", Font.BOLD, 36));
            panel1.add(label2);
            label2.setBounds(45, 90, label2.getPreferredSize().width, 45);

            //---- label3 ----
            label3.setText("Library Management System");
            label3.setForeground(Color.white);
            label3.setFont(new Font("Segoe UI Light", Font.PLAIN, 28));
            panel1.add(label3);
            label3.setBounds(new Rectangle(new Point(140, 140), label3.getPreferredSize()));

            //---- label4 ----
            label4.setText("Create Your Account");
            label4.setForeground(Color.white);
            label4.setFont(new Font("Segoe UI Light", Font.BOLD | Font.ITALIC, 22));
            panel1.add(label4);
            label4.setBounds(new Rectangle(new Point(215, 185), label4.getPreferredSize()));

            //---- label5 ----
            label5.setText("Username");
            label5.setForeground(Color.white);
            label5.setFont(new Font("Segoe UI", Font.BOLD, 20));
            label5.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label5);
            label5.setBounds(70, 230, 170, label5.getPreferredSize().height);

            //---- label6 ----
            label6.setText("Email Address");
            label6.setForeground(Color.white);
            label6.setFont(new Font("Segoe UI", Font.BOLD, 20));
            label6.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label6);
            label6.setBounds(70, 260, 170, label6.getPreferredSize().height);

            //---- label7 ----
            label7.setText("Password");
            label7.setForeground(Color.white);
            label7.setFont(new Font("Segoe UI", Font.BOLD, 20));
            label7.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label7);
            label7.setBounds(70, 295, 170, label7.getPreferredSize().height);

            //---- label8 ----
            label8.setText("Confirm Password");
            label8.setForeground(Color.white);
            label8.setFont(new Font("Segoe UI", Font.BOLD, 20));
            label8.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label8);
            label8.setBounds(new Rectangle(new Point(70, 325), label8.getPreferredSize()));

            //---- userNameTextField ----
            userNameTextField.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
            panel1.add(userNameTextField);
            userNameTextField.setBounds(255, 225, 250, 30);

            //---- emailTextField ----
            emailTextField.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
            panel1.add(emailTextField);
            emailTextField.setBounds(255, 260, 250, 30);

            //---- passwordField ----
            passwordField.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
            panel1.add(passwordField);
            passwordField.setBounds(255, 295, 250, 30);

            //---- confirmPasswordField ----
            confirmPasswordField.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
            panel1.add(confirmPasswordField);
            confirmPasswordField.setBounds(255, 330, 250, 30);

            //---- signUpButton ----
            signUpButton.setText("SIGN UP");
            signUpButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            signUpButton.setFocusable(false);
            signUpButton.addActionListener(e -> registerUser(e));
            panel1.add(signUpButton);
            signUpButton.setBounds(new Rectangle(new Point(260, 380), signUpButton.getPreferredSize()));

            //---- cancelButton ----
            cancelButton.setText("CANCEL");
            cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            cancelButton.setFocusable(false);
            cancelButton.setBackground(new Color(0xb81212));
            cancelButton.setForeground(Color.white);
            cancelButton.addActionListener(e -> cancelUserRegistration(e));
            panel1.add(cancelButton);
            cancelButton.setBounds(new Rectangle(new Point(375, 380), cancelButton.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 615, 455);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JTextField userNameTextField;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton signUpButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new RegistrationFrame();
    }
}
