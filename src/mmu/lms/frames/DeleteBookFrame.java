/*
 * Created by JFormDesigner on Sat Oct 01 11:42:33 EAT 2022
 */

package mmu.lms.frames;

import mmu.lms.classes.Core;
import mmu.lms.classes.UIRefresher;
import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;


/**
 * @author Muhereza Joel
 */
public class DeleteBookFrame extends JDialog {

    private JTable table;
    private ResultSet resultSet = null;

    public DeleteBookFrame() {

        initComponents();
        setTitle("Delete Book");
        setResizable(false);
        setLocationRelativeTo(this.getOwner());
        this.setModal(true);
        setMinimumSize(new Dimension(500,450));
        setVisible(true);

        validateButtons();

    }
    private void validateButtons(){
        if(searchBookByNameButton.getText() == "")
            searchBookByNameButton.setEnabled(true);
    }
    private void showBooks(ResultSet resultSet){
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        table = new JTable();
        table.setRowHeight(25);
        table.setModel(DbUtils.resultSetToTableModel(resultSet));
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



    private void cancelMoveToTrash(ActionEvent e) {
        // TODO add your code here
        dispose();
    }

    private ResultSet searchBook(ActionEvent e) {
        // TODO add your code here
//        ResultSet resultSet = null;
        if ( e.getSource() == searchBookByNameButton){
            String searchText = searchBookByNameField.getText();

            if (searchText.isEmpty()){
                JOptionPane.showMessageDialog(this,
                        "   No search String Specified !! \n " +
                                "\"Please specify the book name You are looking for\"",
                        "Please provide search string"
                                ,
                        JOptionPane.ERROR_MESSAGE);

                return resultSet;
            }

            if (!(searchText.isEmpty())){
                okButton.setEnabled(true);
            }
            resultSet = new Core().searchBook("books", "title", searchText);
            showBooks(resultSet);
            
        }
        
        if (e.getSource() == searchBookByAuthorButton){
            String searchText = searchBookByAuthorField.getText();
            if (searchText.isEmpty()){
                JOptionPane.showMessageDialog(this,
                        "   No search String Specified !! \n " +
                                "\"Please specify the author of book you are looking for\"",
                        "Please provide search string"
                        ,
                        JOptionPane.ERROR_MESSAGE);

                return resultSet;
            }
            if (!(searchText.isEmpty())){
                okButton.setEnabled(true);
            }

            resultSet = new Core().searchBook("books", "author", searchText);
            showBooks(resultSet);
            
        }
        
        return resultSet;
    }

    private void refreshDeleteBooksTable(ResultSet resultSet){
        table.setModel(DbUtils.resultSetToTableModel(resultSet));

    }



    private void removeSelectedBook(ActionEvent e) {
        // TODO add your code here
        String searchText = "";
//       if (table.isRowSelected(0)){
//           okButton.setEnabled(true);
//       }
       int bookID = (int) table.getValueAt(table.getSelectedRow(), 0);
       int retunedValue = 1;
       if (bookID > 0){
           retunedValue = JOptionPane.showConfirmDialog(this, "Are You Sure You Want " +
                   "To Delete this book");

       }

       if (retunedValue == 0) {
           new Core().deleteRecord("books", "bookID", bookID);
           resultSet = new Core().searchBook("books", "author", searchText);
           refreshDeleteBooksTable(resultSet);
       }
    }

    private void searchBookByAuthorFieldFocusGained(FocusEvent e) {
        // TODO add your code here
        searchBookByNameField.setText("");
    }

    private void searchBookByNameFieldFocusGained(FocusEvent e) {
        // TODO add your code here
        searchBookByAuthorField.setText("");
    }
    
       

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        searchBookByNameField = new JTextField();
        searchBookByAuthorField = new JTextField();
        searchBookByAuthorButton = new JButton();
        searchBookByNameButton = new JButton();
        scrollPane1 = new JScrollPane();
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
                label1.setText("Search By Book Name");
                label1.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
                contentPanel.add(label1);
                label1.setBounds(10, 10, 185, 25);

                //---- label2 ----
                label2.setText("Search Book By Author");
                label2.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
                contentPanel.add(label2);
                label2.setBounds(230, 10, 190, 25);

                //---- searchBookByNameField ----
                searchBookByNameField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        searchBookByNameFieldFocusGained(e);
                    }
                });
                contentPanel.add(searchBookByNameField);
                searchBookByNameField.setBounds(10, 40, 190, 35);

                //---- searchBookByAuthorField ----
                searchBookByAuthorField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        searchBookByAuthorFieldFocusGained(e);
                    }
                });
                contentPanel.add(searchBookByAuthorField);
                searchBookByAuthorField.setBounds(230, 40, 190, 35);

                //---- searchBookByAuthorButton ----
                searchBookByAuthorButton.setText("Search");
                searchBookByAuthorButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
                searchBookByAuthorButton.setBackground(Color.white);
                searchBookByAuthorButton.setFocusable(false);
                searchBookByAuthorButton.addActionListener(e -> searchBook(e));
                contentPanel.add(searchBookByAuthorButton);
                searchBookByAuthorButton.setBounds(230, 80, searchBookByAuthorButton.getPreferredSize().width, 35);

                //---- searchBookByNameButton ----
                searchBookByNameButton.setText("Search");
                searchBookByNameButton.setBackground(Color.white);
                searchBookByNameButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
                searchBookByNameButton.setFocusable(false);
                searchBookByNameButton.addActionListener(e -> searchBook(e));
                contentPanel.add(searchBookByNameButton);
                searchBookByNameButton.setBounds(10, 80, searchBookByNameButton.getPreferredSize().width, 35);
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(10, 180, 415, 215);

                //---- label3 ----
                label3.setText("Please Select book from search results table to delete the book");
                label3.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label3);
                label3.setBounds(15, 130, 405, 30);

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
                okButton.setText("Move To Trash");
                okButton.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
                okButton.setFocusable(false);
                okButton.setEnabled(false);
                okButton.addActionListener(e -> removeSelectedBook(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                cancelButton.setFocusable(false);
                cancelButton.setBackground(Color.white);
                cancelButton.addActionListener(e -> cancelMoveToTrash(e));
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
    private JTextField searchBookByNameField;
    private JTextField searchBookByAuthorField;
    private JButton searchBookByAuthorButton;
    private JButton searchBookByNameButton;
    private JScrollPane scrollPane1;
    private JLabel label3;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
//        new DeleteBookFrame();
    }
}
