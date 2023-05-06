/*
 * Created by JFormDesigner on Mon Sep 19 22:11:47 EAT 2022
 */

package mmu.lms.frames;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatLightOwlIJTheme;
import mmu.lms.classes.Core;
import mmu.lms.classes.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Muhereza Joel
 */
public class LoginFrame extends JFrame {
    User user = null;
    public LoginFrame() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        initComponents();
        ImageIcon icon = new ImageIcon("images/mmu logo.PNG");
        setIconImage(icon.getImage());
        setTitle("Mountains of the Moon University LMS");
        setSize(455, 420);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
        setVisible(true);
    }

    private void loginUser(ActionEvent e) {
        // TODO add your code here
        String username = usernameTextField.getText();
        String password = String.valueOf(passwordTextField.getPassword());

        if (username.isEmpty() || password.isEmpty()){
            errorsTextField.setText("Please Fill All Form Fields");
            return;
        }

        String [] tableData = {username, password};
        ResultSet resultSet =  new Core().loginUser("users", tableData);

        try {
            if (resultSet.next()){
                user = new User();
                user.username = resultSet.getString("username");
                user.password = resultSet.getString("password");

                dispose();
                new AdminDashboard();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        if (user == null){
            errorsTextField.setText("You Are Not Registerd");
        }
    }

    private void usernameTextFieldFocusGained(FocusEvent e) {
        // TODO add your code here
        errorsTextField.setText("");
    }

    private void passwordTextFieldFocusGained(FocusEvent e) {
        // TODO add your code here
        errorsTextField.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        errorsTextField = new JTextField();
        button1 = new JButton();
        usernameTextField = new JTextField();
        label4 = new JLabel();
        label5 = new JLabel();
        passwordTextField = new JPasswordField();

        //======== this ========
        setBackground(new Color(0x18115e));
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(UIManager.getColor("Table.selectionBackground"));
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("MOUNTAINS OF THE MOON UNIVERSITY");
            label1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            label1.setForeground(Color.white);
            panel1.add(label1);
            label1.setBounds(new Rectangle(new Point(45, 100), label1.getPreferredSize()));

            //---- label2 ----
            label2.setText("Library Management System");
            label2.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            label2.setForeground(Color.white);
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(135, 135), label2.getPreferredSize()));

            //---- label3 ----
            label3.setIcon(new ImageIcon(getClass().getResource("/mmulogosmall.png")));
            panel1.add(label3);
            label3.setBounds(new Rectangle(new Point(190, 25), label3.getPreferredSize()));

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
        panel1.setBounds(0, 0, 455, 185);

        //---- errorsTextField ----
        errorsTextField.setBackground(new Color(0xf2f2f2));
        errorsTextField.setBorder(null);
        errorsTextField.setEditable(false);
        contentPane.add(errorsTextField);
        errorsTextField.setBounds(135, 290, 255, 25);

        //---- button1 ----
        button1.setText("Login");
        button1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button1.addActionListener(e -> loginUser(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(130, 315), button1.getPreferredSize()));

        //---- usernameTextField ----
        usernameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usernameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                usernameTextFieldFocusGained(e);
            }
        });
        contentPane.add(usernameTextField);
        usernameTextField.setBounds(130, 215, 260, 35);

        //---- label4 ----
        label4.setText("Username");
        label4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(50, 225), label4.getPreferredSize()));

        //---- label5 ----
        label5.setText("Password");
        label5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        contentPane.add(label5);
        label5.setBounds(55, 260, 75, label5.getPreferredSize().height);

        //---- passwordTextField ----
        passwordTextField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        passwordTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordTextFieldFocusGained(e);
            }
        });
        contentPane.add(passwordTextField);
        passwordTextField.setBounds(130, 255, 260, 35);

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
    private JTextField errorsTextField;
    private JButton button1;
    private JTextField usernameTextField;
    private JLabel label4;
    private JLabel label5;
    private JPasswordField passwordTextField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public static void main(String[] args) {
         new LoginFrame();
    }
}
