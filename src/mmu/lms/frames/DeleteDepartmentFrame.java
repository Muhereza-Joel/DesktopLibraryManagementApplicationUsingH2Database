/*
 * Created by JFormDesigner on Sat Nov 05 16:19:10 EAT 2022
 */

package mmu.lms.frames;

import mmu.lms.classes.Core;
import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;
import javax.swing.border.*;

/**
 * @author Muhereza Joel
 */
public class DeleteDepartmentFrame extends JDialog {
    JTable table = null;
    ResultSet resulset = null;
    String searchText;
    public DeleteDepartmentFrame() {
        initComponents();
        setTitle("Delete Department");
        setResizable(false);
        this.setModal(true);
        setLocationRelativeTo(this.getOwner());
        setVisible(true);
    }

    private void searchDepartment(ActionEvent e) {
        // TODO add your code here
        searchText = searchTextField.getText();
        if (searchText.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please Specify The Department Name \n " +
                            "You Are Looling For",
                    "Provide Search String",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        resulset =new Core().searchDepartment("department", "departmentName", searchText);
        table = new JTable();
        table.setModel(DbUtils.resultSetToTableModel(resulset));
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

    private void deleteSelectedDepartment(ActionEvent e) {
        // TODO add your code here
        String searchText = searchTextField.getText();
        if (table == null){
            JOptionPane.showMessageDialog(this,
                    "Please Select An Item From The Table",
                    "Try Again",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int id = (int) table.getValueAt(table.getSelectedRow(), 0);


        if (id > 0){
            int returnValue = JOptionPane.showConfirmDialog(this,
                    "Are You Sure You Want To\n" +
                            "Delete This Department",
                    "Comfirm Delete",
                    JOptionPane.INFORMATION_MESSAGE);

            if (returnValue == 0){
               new Core().deleteRecord("department", "departmentID", id);
               resulset =new Core().searchDepartment("department", "departmentName", searchText);
                table.setModel(DbUtils.resultSetToTableModel(resulset));
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
                label1.setText("Enter Department Name Here");
                label1.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(15, 10), label1.getPreferredSize()));
                contentPanel.add(searchTextField);
                searchTextField.setBounds(10, 35, 330, 35);

                //---- button1 ----
                button1.setText("Search");
                button1.addActionListener(e -> searchDepartment(e));
                contentPanel.add(button1);
                button1.setBounds(340, 35, 95, 35);
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(15, 75, 415, 230);

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
                okButton.setText("Delete Department");
                okButton.addActionListener(e -> deleteSelectedDepartment(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
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
    private JTextField searchTextField;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public static void main(String[] args) {
        new DeleteDepartmentFrame();
    }
}
