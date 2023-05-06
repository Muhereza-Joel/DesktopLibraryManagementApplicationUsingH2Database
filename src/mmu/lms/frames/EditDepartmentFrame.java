/*
 * Created by JFormDesigner on Sat Nov 05 15:54:44 EAT 2022
 */

package mmu.lms.frames;

import mmu.lms.classes.Core;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;
import javax.swing.border.*;
import java.sql.SQLException;

/**
 * @author Muhereza Joel
 */
public class EditDepartmentFrame extends JDialog {
    public EditDepartmentFrame() {
        initComponents();
        setTitle("Edit Department");
        setResizable(false);
        this.setModal(true);
        setLocationRelativeTo(this.getOwner());
        setVisible(true);
    }

    private void cancelEditDepartmentFrame(ActionEvent e) {
        // TODO add your code here
        dispose();
    }


    private void searchDepartment(ActionEvent e) {
        // TODO add your code here
        int count = 0;
        String searchText = searchTextField.getText();
        if (searchText.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please Specify The Department Name \n " +
                            "You Are Looling For",
                    "Provide Search String",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        ResultSet resultSet = new Core().searchDepartment("department", "departmentName", searchText);
        while (true) {
                    try {
                        if (!resultSet.next()) {
                            break;}

                        departmentNameTextField.setText(resultSet.getString("departmentName"));
                        facultyComboBox.setSelectedItem(resultSet.getString("facultyName"));
                        departmentIDTextField.setText(resultSet.getString("departmentID"));
                         count ++;

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

        if (count == 0){
            JOptionPane.showMessageDialog(this, "No Record Found", "Try Again", JOptionPane.INFORMATION_MESSAGE    );
        }

    }


    private void updateDepartment(ActionEvent e) {
        // TODO add your code here
        String departmentName = departmentNameTextField.getText();
        String facultyName = facultyComboBox.getSelectedItem().toString();
        String selectedID = departmentIDTextField.getText();

        if (departmentName.isEmpty() || facultyName.isEmpty() || selectedID.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please Fill All Fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        String [] tableColumns = {"departmentName", "facultyName"};
        String [] tableData = {departmentName, facultyName};
        String sqlQuery = new Core().prepareUpdateQuery("department", "departmentID", selectedID, tableColumns);

        int returnValue = new Core().updateRecord(sqlQuery, tableData);

        if (returnValue == 1){
            JOptionPane.showMessageDialog(this,
                    "Department Updated Successfully",
                    "Department Updated",
                    JOptionPane.INFORMATION_MESSAGE);

            searchTextField.setText("");
            departmentIDTextField.setText("");
            departmentNameTextField.setText("");
            facultyComboBox.setSelectedItem("Not Selected");
        }
    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        String sql = "SELECT * FROM faculty";
        ResultSet resultSet = new Core().getRows(sql);

        while (true){
            try {
                if (!resultSet.next()) break;
                facultyComboBox.addItem(resultSet.getString("facultyName"));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        searchTextField = new JTextField();
        button1 = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        departmentNameTextField = new JTextField();
        facultyComboBox = new JComboBox<>();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();
        departmentIDTextField = new JTextField();
        textArea1 = new JTextArea();

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
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
                label1.setText("Enter Department Name");
                label1.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(20, 0), label1.getPreferredSize()));
                contentPanel.add(searchTextField);
                searchTextField.setBounds(15, 25, 275, 35);

                //---- button1 ----
                button1.setText("Search");
                button1.addActionListener(e -> searchDepartment(e));
                contentPanel.add(button1);
                button1.setBounds(290, 25, button1.getPreferredSize().width, 35);

                //---- label2 ----
                label2.setText("Department Name");
                label2.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(20, 65), label2.getPreferredSize()));

                //---- label3 ----
                label3.setText("Faculty");
                label3.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                contentPanel.add(label3);
                label3.setBounds(new Rectangle(new Point(20, 120), label3.getPreferredSize()));
                contentPanel.add(departmentNameTextField);
                departmentNameTextField.setBounds(15, 85, 355, 35);

                //---- facultyComboBox ----
                facultyComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Not Selected"
                }));
                contentPanel.add(facultyComboBox);
                facultyComboBox.setBounds(15, 140, 355, 35);

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
            dialogPane.add(contentPanel, BorderLayout.WEST);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(null);

                //---- okButton ----
                okButton.setText("Save Changes");
                okButton.addActionListener(e -> updateDepartment(e));
                buttonBar.add(okButton);
                okButton.setBounds(182, 10, okButton.getPreferredSize().width, 32);

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancelEditDepartmentFrame(e));
                buttonBar.add(cancelButton);
                cancelButton.setBounds(294, 10, 80, 32);

                //---- departmentIDTextField ----
                departmentIDTextField.setVisible(false);
                buttonBar.add(departmentIDTextField);
                departmentIDTextField.setBounds(20, 5, 75, departmentIDTextField.getPreferredSize().height);

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
    private JTextField searchTextField;
    private JButton button1;
    private JLabel label2;
    private JLabel label3;
    private JTextField departmentNameTextField;
    private JComboBox<String> facultyComboBox;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField departmentIDTextField;
    private JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    public static void main(String[] args) {
        new EditDepartmentFrame();
    }
}
