/*
 * Created by JFormDesigner on Thu Nov 17 14:01:04 EAT 2022
 */

package mmu.lms.frames;

import mmu.lms.classes.Core;
import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Muhereza Joel
 */
public class ReturnBookFrame extends JDialog {
    JTable table = null;
    public ReturnBookFrame() {
        initComponents();
        setTitle("Return Book");
        setModal(true);
        setLocationRelativeTo(this.getOwner());
        setVisible(true);

    }

    private void cancelReturnBookFrame(ActionEvent e) {
        // TODO add your code here
        dispose();
    }

    private void searchBorrowedBook(ActionEvent e) {
        // TODO add your code here
        String regNo = regNoTextField.getText();
        ResultSet resultset = new Core().searchBorrowedBook("borrowed_books", "regNumber", regNo);
        table = new JTable();
        table.setRowHeight(35);
        table.setModel(DbUtils.resultSetToTableModel(resultset));

        if (table.getRowCount() == 0){
            scrollPane1.setLayout(null);
            JLabel label = new JLabel("No Record Found");
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.TOP);
            label.setFont(new Font("Tahoma", Font.PLAIN, 18));
            scrollPane1.setViewportView(label);
        }
        if (table.getRowCount() > 0) {
            scrollPane1.setViewportView(table);
        }
    }

    public void returnSelectedBook(ActionEvent e) {
        // TODO add your code here
        if (table == null){
            JOptionPane.showMessageDialog(this, "You Cannot Remove a book\n" +
                            "you haven't selected",
                    "Try Again",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        String regNo = (String) table.getValueAt(table.getSelectedRow(), 1);
        String bookTitle = (String) table.getValueAt(table.getSelectedRow(), 2);
        String bookAuthor = (String) table.getValueAt(table.getSelectedRow(), 3);

        int returnValue = new Core().returnBook(regNo, bookTitle);
        if (returnValue == 1){
            JOptionPane.showMessageDialog(this,
                    "Book returned to successfully",
                    "Return Successful", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            int returnValue1 = new Core().updateAvailableBookCount(bookTitle, bookAuthor);

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        regNoTextField = new JTextField();
        button1 = new JButton();
        separator1 = new JSeparator();
        scrollPane1 = new JScrollPane();
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
                label1.setText("Student Reg No.");
                label1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label1.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label1);
                label1.setBounds(10, 15, 135, 25);
                contentPanel.add(regNoTextField);
                regNoTextField.setBounds(150, 10, 330, 35);

                //---- button1 ----
                button1.setText("Search");
                button1.addActionListener(e -> searchBorrowedBook(e));
                contentPanel.add(button1);
                button1.setBounds(400, 50, button1.getPreferredSize().width, 35);
                contentPanel.add(separator1);
                separator1.setBounds(10, 90, 465, 5);
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(15, 105, 465, 250);

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
                buttonBar.setLayout(null);

                //---- okButton ----
                okButton.setText("Return Book");
                okButton.addActionListener(e -> returnSelectedBook(e));
                buttonBar.add(okButton);
                okButton.setBounds(300, 5, okButton.getPreferredSize().width, 37);

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancelReturnBookFrame(e));
                buttonBar.add(cancelButton);
                cancelButton.setBounds(404, 5, 80, 37);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < buttonBar.getComponentCount(); i++) {
                        Rectangle bounds = buttonBar.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = buttonBar.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    buttonBar.setMinimumSize(preferredSize);
                    buttonBar.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JTextField regNoTextField;
    private JButton button1;
    private JSeparator separator1;
    private JScrollPane scrollPane1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
