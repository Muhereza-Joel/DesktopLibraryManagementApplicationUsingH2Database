/*
 * Created by JFormDesigner on Tue Oct 18 13:51:49 EAT 2022
 */

package mmu.lms.frames;

import mmu.lms.classes.Core;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Muhereza Joel
 */
public class AddFacultyFrame extends JDialog {
    public AddFacultyFrame() {
        initComponents();
        setTitle("Create New Faculty");
        setResizable(false);
        this.setModal(true);
        setMinimumSize(new Dimension(400,200));
        setLocationRelativeTo(this);
        setVisible(true);
    }

    private void cancelAddFacultyFrame(ActionEvent e) {
        // TODO add your code here
        dispose();
    }

    private void addFacultyToDatabase(ActionEvent e) {
        // TODO add your code here
        String facultyName = facultyNameField.getText();
        String facultyLocation = facultyLocationField.getText();

        if (facultyName.isEmpty() || facultyLocation.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please Fill All Fields\n Cannot Submit Empty Fields.",
                     "Fill all form Fields",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }
        String [] tableColumns = {"facultyName", "facultyLocation"};
        String [] tableData = {facultyName, facultyLocation};
        String sqlQuery = new Core().prepareInsertStatement("faculty", tableColumns);
        int returnValue = new Core().insertRow(sqlQuery, tableData);

        if (returnValue == 1){
            facultyNameField.setText("");
            facultyLocationField.setText("");

            JOptionPane.showMessageDialog(this,
                    "Faculty Created Successfully",
                    "Faculty Created",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        facultyNameField = new JTextField();
        facultyLocationField = new JTextField();
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
                label1.setText("Faculty Name");
                label1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label1.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label1);
                label1.setBounds(10, 25, 120, label1.getPreferredSize().height);

                //---- label2 ----
                label2.setText("Faculty Location");
                label2.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label2.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label2);
                label2.setBounds(new Rectangle(new Point(15, 65), label2.getPreferredSize()));
                contentPanel.add(facultyNameField);
                facultyNameField.setBounds(140, 15, 230, 35);
                contentPanel.add(facultyLocationField);
                facultyLocationField.setBounds(140, 55, 230, 35);

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
                okButton.setText("Add Faculty");
                okButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                okButton.setFocusable(false);
                okButton.addActionListener(e -> addFacultyToDatabase(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                cancelButton.setFocusable(false);
                cancelButton.setBackground(Color.white);
                cancelButton.addActionListener(e -> cancelAddFacultyFrame(e));
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
    private JTextField facultyNameField;
    private JTextField facultyLocationField;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
