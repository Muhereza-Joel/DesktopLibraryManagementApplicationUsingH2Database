/*
 * Created by JFormDesigner on Tue Oct 18 14:08:13 EAT 2022
 */

package mmu.lms.frames;

import mmu.lms.classes.Core;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Muhereza Joel
 */
public class AddDepartmentFrame extends JDialog {
    public AddDepartmentFrame() {
        initComponents();
        setTitle("Create New Department");
        setResizable(false);
        this.setModal(true);
        setMinimumSize(new Dimension(400,250));
        setLocationRelativeTo(this);
        fillComboBox();

        setVisible(true);
    }

    private void fillComboBox(){
        String sql = "SELECT facultyName FROM faculty";
        ResultSet resultSet = new Core().getRows(sql);
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                facultyComboBox.addItem(resultSet.getString("facultyName"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void cancelAddDepartmentFrame(ActionEvent e) {
        // TODO add your code here
        dispose();
    }

    private void addDepartmentToDatabase(ActionEvent e) {
        // TODO add your code here
        String departmentName = departmentNameField.getText();
        String facultyName = facultyComboBox.getSelectedItem().toString();
        String [] tableData = {departmentName, facultyName};
        String [] tableColumns = {"departmentName", "facultyName"};

        if (departmentName.isEmpty() || facultyName.equals("Not Selected")){
            JOptionPane.showMessageDialog(this,
                    "Please Fill All Fields\n Cannot Submit Empty Fields.",
                    "Fill all form Fields",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        String sqlQuery = new Core().prepareInsertStatement("department", tableColumns);
        int returnValue = new Core().insertRow(sqlQuery, tableData);
        if (returnValue == 1){
            facultyComboBox.setSelectedItem("Not Selected");
            departmentNameField.setText("");

            JOptionPane.showMessageDialog(this,
                    "Department Created Successfully",
                    "Department Created",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        departmentNameField = new JTextField();
        facultyComboBox = new JComboBox<>();
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
                label1.setText("Deparment Name");
                label1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label1.setHorizontalAlignment(SwingConstants.LEFT);
                contentPanel.add(label1);
                label1.setBounds(30, 35, 155, 25);

                //---- label2 ----
                label2.setText("Faculty");
                label2.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label2.setHorizontalAlignment(SwingConstants.LEFT);
                contentPanel.add(label2);
                label2.setBounds(30, 90, 136, 25);
                contentPanel.add(departmentNameField);
                departmentNameField.setBounds(25, 60, 335, 35);

                //---- facultyComboBox ----
                facultyComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Not Selected"
                }));
                contentPanel.add(facultyComboBox);
                facultyComboBox.setBounds(25, 115, 335, 35);

                //---- label3 ----
                label3.setText("Note: Enter the Department Name and Then Attach it Faculty");
                label3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                label3.setForeground(new Color(0x3333ff));
                contentPanel.add(label3);
                label3.setBounds(5, 5, 360, label3.getPreferredSize().height);

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
            dialogPane.add(contentPanel, BorderLayout.NORTH);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("Add Department");
                okButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                okButton.setFocusable(false);
                okButton.addActionListener(e -> addDepartmentToDatabase(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                cancelButton.setFocusable(false);
                cancelButton.setBackground(Color.white);
                cancelButton.addActionListener(e -> cancelAddDepartmentFrame(e));
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
    private JLabel label1;
    private JLabel label2;
    private JTextField departmentNameField;
    private JComboBox<String> facultyComboBox;
    private JLabel label3;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
