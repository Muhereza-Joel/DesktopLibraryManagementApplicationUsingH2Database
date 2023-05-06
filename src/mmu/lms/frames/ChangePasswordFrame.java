/*
 * Created by JFormDesigner on Fri Sep 23 12:46:45 EAT 2022
 */

package mmu.lms.frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Muhereza Joel
 */
public class ChangePasswordFrame extends JDialog {
    public ChangePasswordFrame() {
        initComponents();
        setTitle("Change Login Password");
        this.setModal(true);
        setMinimumSize(new Dimension(400,200));
        setLocationRelativeTo(this);
        setVisible(true);
    }

    private void cancelChangePassword(ActionEvent e) {
        // TODO add your code here
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        passwordField1 = new JPasswordField();
        passwordField2 = new JPasswordField();
        label3 = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- label1 ----
                label1.setText("Current Password");
                label1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label1.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label1);
                label1.setBounds(0, 10, 145, 35);

                //---- label2 ----
                label2.setText("New Password");
                label2.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label2.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label2);
                label2.setBounds(0, 55, 145, 35);

                //---- textField1 ----
                textField1.setEditable(false);
                textField1.setText("password");
                contentPanel.add(textField1);
                textField1.setBounds(155, 10, 255, 35);
                contentPanel.add(passwordField1);
                passwordField1.setBounds(155, 55, 255, 35);
                contentPanel.add(passwordField2);
                passwordField2.setBounds(155, 100, 255, 35);

                //---- label3 ----
                label3.setText("Confirm Password");
                label3.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                contentPanel.add(label3);
                label3.setBounds(new Rectangle(new Point(10, 100), label3.getPreferredSize()));

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < contentPanel.getComponentCount(); i++) {
                        Rectangle bounds = contentPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = contentPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("Change Password");
                okButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                okButton.setFocusable(false);
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                cancelButton.setFocusable(false);
                cancelButton.setBackground(Color.white);
                cancelButton.addActionListener(e -> cancelChangePassword(e));
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel label3;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new ChangePasswordFrame();
    }
}
