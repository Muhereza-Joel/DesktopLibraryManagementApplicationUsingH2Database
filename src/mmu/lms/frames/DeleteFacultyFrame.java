/*
 * Created by JFormDesigner on Wed Nov 02 18:55:01 EAT 2022
 */

package mmu.lms.frames;

import mmu.lms.classes.Core;
import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import  java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Muhereza Joel
 */
public class DeleteFacultyFrame extends JDialog {
    JTable table = null;
    ResultSet resultset = null;
    public DeleteFacultyFrame() {
        initComponents();
        setModal(true);
        setTitle("Delete Faculty Frame");
        setVisible(true);
    }

    private void searchFaculty(ActionEvent e) {
        // TODO add your code here
        int count = 0;
        table = new JTable();
        String searchText = searchField.getText();

        if (searchText.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please Provide Search String",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        resultset = new Core().searchFaculty("faculty", "facultyName", searchText);
        table.setModel(DbUtils.resultSetToTableModel(resultset));
        if (table.getRowCount() == 0){
            scrollPane1.setLayout(null);
            JLabel label = new JLabel("No Record Found");
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.TOP);
            label.setFont(new Font("Tahoma", Font.PLAIN, 18));
            scrollPane1.setViewportView(label);
        } else {
            scrollPane1.setViewportView(table);
        }

    }

    private void cancelDeleteFacultyFrame(ActionEvent e) {
        // TODO add your code here
        dispose();
    }

    private void deleteSelectedFaculty(ActionEvent e) {
        // TODO add your code here
        String searchText = "";
        int facultyID = (int) table.getValueAt(table.getSelectedRow(), 0);
        int retunedValue = 1;
        if (facultyID > 0){
            retunedValue = JOptionPane.showConfirmDialog(this, "Are You Sure You Want " +
                    "To Delete this Faculty");

        }

        if (retunedValue == 0) {
            new Core().deleteRecord("faculty", "facultyID", facultyID);
            resultset = new Core().searchFaculty("faculty", "facultyName", searchText);
            table.setModel(DbUtils.resultSetToTableModel(resultset));
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        contentPanel2 = new JPanel();
        searchField = new JTextField();
        separator1 = new JSeparator();
        label1 = new JLabel();
        button1 = new JButton();
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

                //======== contentPanel2 ========
                {
                    contentPanel2.setLayout(null);
                    contentPanel2.add(searchField);
                    searchField.setBounds(15, 40, 355, 35);
                    contentPanel2.add(separator1);
                    separator1.setBounds(10, 85, 455, 5);

                    //---- label1 ----
                    label1.setText("Enter Faculty Name Here");
                    label1.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                    contentPanel2.add(label1);
                    label1.setBounds(15, 15, 315, label1.getPreferredSize().height);

                    //---- button1 ----
                    button1.setText("Search");
                    button1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                    button1.addActionListener(e -> searchFaculty(e));
                    contentPanel2.add(button1);
                    button1.setBounds(375, 40, 95, 35);
                    contentPanel2.add(scrollPane1);
                    scrollPane1.setBounds(15, 100, 450, 240);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < contentPanel2.getComponentCount(); i++) {
                            Rectangle bounds = contentPanel2.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = contentPanel2.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        contentPanel2.setMinimumSize(preferredSize);
                        contentPanel2.setPreferredSize(preferredSize);
                    }
                }
                contentPanel.add(contentPanel2);
                contentPanel2.setBounds(0, 0, 479, 355);

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
                okButton.setText("Delete Faculty");
                okButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                okButton.addActionListener(e -> deleteSelectedFaculty(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                cancelButton.addActionListener(e -> cancelDeleteFacultyFrame(e));
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
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
    private JPanel contentPanel2;
    private JTextField searchField;
    private JSeparator separator1;
    private JLabel label1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
