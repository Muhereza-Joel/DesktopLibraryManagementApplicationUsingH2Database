/*
 * Created by JFormDesigner on Tue Oct 18 14:16:06 EAT 2022
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
public class AddCategoryFrame extends JDialog {
    public AddCategoryFrame() {
        initComponents();
        setTitle("Create New Category");
        setResizable(false);
        this.setModal(true);
        setMinimumSize(new Dimension(400,100));
        setLocationRelativeTo(this);
        setVisible(true);
    }

    private void cancelAddCategoryFrame(ActionEvent e) {
        // TODO add your code here
        dispose();
    }

    private void addCategoryToDatabase(ActionEvent e) {
        // TODO add your code here
        String categoryName = categoryNameField.getText();
        String [] tableColumns = {"categoryName"};
        String [] tableData = {categoryName};

        if (categoryName.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please Fill All Fields\n Cannot Submit Empty Fields.",
                    "Fill all form Fields",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        String sqlQuery = new Core().prepareInsertStatement("category", tableColumns);
        int returnValue = new Core().insertRow(sqlQuery,tableData);

        if (returnValue == 1){
            categoryNameField.setText("");

            JOptionPane.showMessageDialog(this,
                    "Category Created Successfully",
                    "Category Created",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        categoryNameField = new JTextField();
        label2 = new JLabel();
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
                label1.setText("Category Name");
                label1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label1.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label1);
                label1.setBounds(0, 35, 120, 25);
                contentPanel.add(categoryNameField);
                categoryNameField.setBounds(130, 30, 240, 35);

                //---- label2 ----
                label2.setText("Note: Categories can be textbooks, e books, journals etc");
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                label2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                label2.setForeground(new Color(0x3366ff));
                contentPanel.add(label2);
                label2.setBounds(5, 5, 365, label2.getPreferredSize().height);

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
                okButton.setText("Add Category");
                okButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                okButton.setFocusable(false);
                okButton.addActionListener(e -> addCategoryToDatabase(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                cancelButton.setFocusable(false);
                cancelButton.setBackground(Color.white);
                cancelButton.addActionListener(e -> cancelAddCategoryFrame(e));
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
    private JTextField categoryNameField;
    private JLabel label2;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
