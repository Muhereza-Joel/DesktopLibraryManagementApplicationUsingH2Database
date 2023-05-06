/*
 * Created by JFormDesigner on Tue Nov 01 20:56:16 EAT 2022
 */

package mmu.lms.frames;

import javax.swing.event.*;
import mmu.lms.classes.Core;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Muhereza Joel
 */
public class EditFacultyFrame extends JDialog {
    ResultSet resultset = null;
    public EditFacultyFrame() {
        initComponents();
        setTitle("Edit Faculty");
        setResizable(false);
        this.setModal(true);
        setMinimumSize(new Dimension(300,250));
        setLocationRelativeTo(this);
        setVisible(true);
    }

    private void searchFaculty(ActionEvent e) {
        // TODO add your code here
        int count = 0;
        String searchText = searchField.getText();
        if (searchText.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "   No search String Specified !! \n " +
                            "\"Please specify the faculty name you are looking for\"",
                    "Please provide search string"
                    ,
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        resultset = new Core().searchFaculty("FACULTY", "FACULTYNAME", searchText);

        try {

            while (resultset.next()) {
                facultyNameField.setText(resultset.getString("facultyName"));
                facultyLocationField.setText(resultset.getString("facultyLocation"));
                facultyID.setText(resultset.getString("facultyID"));
                count ++;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        if (count == 0){
            JOptionPane.showMessageDialog(this, "No Record Found", "Try Again", JOptionPane.INFORMATION_MESSAGE    );
        }

    }

    private void cancelEditFacultyFrame(ActionEvent e) {
        // TODO add your code here
        dispose();
    }

    private void updateFaculty(ActionEvent e) {
        // TODO add your code here
        String facultyName = facultyNameField.getText();
        String facultyLocation = facultyLocationField.getText();
        String id = facultyID.getText();

        if (facultyName.isEmpty() || facultyLocation.isEmpty() || id.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "All Fields Are Required",
                             "Try Again",
                              JOptionPane.ERROR_MESSAGE);
            return;
        }

        String [] tableColumns =  {"facultyName", "facultyLocation"};
        String [] tableData = {facultyName, facultyLocation};
        String sqlQuery = new Core().prepareUpdateQuery("faculty", "facultyID", id, tableColumns);
        int returnValue = new Core().updateRecord(sqlQuery, tableData);

        if (returnValue == 1){
            JOptionPane.showMessageDialog(this,
                    "Faculty Updated Successfully",
                            "Faculty Updated",
                             JOptionPane.INFORMATION_MESSAGE);

            facultyID.setText("");
            facultyNameField.setText("");
            facultyLocationField.setText("");
            searchField.setText("");

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        searchField = new JTextField();
        separator1 = new JSeparator();
        label2 = new JLabel();
        label3 = new JLabel();
        facultyNameField = new JTextField();
        facultyLocationField = new JTextField();
        label1 = new JLabel();
        button1 = new JButton();
        buttonBar = new JPanel();
        facultyID = new JTextField();
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
                contentPanel.add(searchField);
                searchField.setBounds(25, 40, 345, 35);
                contentPanel.add(separator1);
                separator1.setBounds(25, 85, 445, 3);

                //---- label2 ----
                label2.setText("Faculty Name");
                label2.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label2.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label2);
                label2.setBounds(25, 100, 135, 25);

                //---- label3 ----
                label3.setText("Faculty Location");
                label3.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label3.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label3);
                label3.setBounds(25, 140, 135, 25);
                contentPanel.add(facultyNameField);
                facultyNameField.setBounds(160, 95, 310, 35);
                contentPanel.add(facultyLocationField);
                facultyLocationField.setBounds(160, 135, 310, 35);

                //---- label1 ----
                label1.setText("Enter Faculty Name Here");
                label1.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
                contentPanel.add(label1);
                label1.setBounds(25, 15, 315, label1.getPreferredSize().height);

                //---- button1 ----
                button1.setText("Search");
                button1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                button1.addActionListener(e -> searchFaculty(e));
                contentPanel.add(button1);
                button1.setBounds(375, 40, 95, 35);

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

                //---- facultyID ----
                facultyID.setVisible(false);
                buttonBar.add(facultyID, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- okButton ----
                okButton.setText("Save Changes");
                okButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                okButton.addActionListener(e -> updateFaculty(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                cancelButton.addActionListener(e -> cancelEditFacultyFrame(e));
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
    private JTextField searchField;
    private JSeparator separator1;
    private JLabel label2;
    private JLabel label3;
    private JTextField facultyNameField;
    private JTextField facultyLocationField;
    private JLabel label1;
    private JButton button1;
    private JPanel buttonBar;
    private JTextField facultyID;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
