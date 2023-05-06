/*
 * Created by JFormDesigner on Sat Oct 01 10:17:57 EAT 2022
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
public class EditBookFrame extends JDialog {
    ResultSet resultSet = null;
    int initialCopies = 0;
    int initialAvailableCopies = 0;
    public EditBookFrame() {
        
        initComponents();
        setTitle("Edit Book");
        setResizable(false);
        this.setModal(true);
        System.out.println(this.getOwner());
        setMinimumSize(new Dimension(500,450));
        setLocationRelativeTo(this);
        fillFacultyComboBox();
        fillDepartmentComboBox();
        fillCategoryComboxBox();
        setVisible(true);


    }
    private void fillFacultyComboBox(){
        String sql  = "SELECT facultyName FROM faculty";
        ResultSet resultSet = new Core().getRows(sql);
        while (true) {
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
    private void fillDepartmentComboBox(){
        String selectedValue = facultyComboBox.getSelectedItem().toString();
        String sql  = "SELECT departmentName FROM department WHERE facultyName LIKE" + "'%" + selectedValue + "%'";
        ResultSet resultSet = new Core().getRows(sql);
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                departmentComboBox.addItem(resultSet.getString("departmentName"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void fillCategoryComboxBox(){
        String sql = "SELECT categoryName FROM category";
        ResultSet resultSet = new Core().getRows(sql);
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                comboBox1.addItem(resultSet.getString("categoryName"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void cancelEditBookFrame(ActionEvent e) {
        // TODO add your code here
        dispose();
    }

    private void searchBook(ActionEvent e) {
        // TODO add your code here
        String searchString = "";
        int count = 0;
        if (e.getSource() == searchBookByName) {
            searchString = searchBookNameField.getText();

            if (searchString.isEmpty()){
                JOptionPane.showMessageDialog(this,
                        "   No search String Specified !! \n " +
                                "\"Please specify the book name You are looking for\"",
                        "Please provide search string"
                        ,
                        JOptionPane.ERROR_MESSAGE);

                return;
            }
            resultSet = new Core().searchBookExpanded("books", "title", searchString);
        }
        
        if (e.getSource() == searchBookByAuthor){
            searchString = searchBookByAuthorTextField.getText();
            if (searchString.isEmpty()){
                JOptionPane.showMessageDialog(this,
                        "   No search String Specified !! \n " +
                                "\"Please specify the author of book you are looking for\"",
                        "Please provide search string"
                        ,
                        JOptionPane.ERROR_MESSAGE);

                return;
            }
          resultSet = new Core().searchBookExpanded("books", "author", searchString);
        }
        while (true){
            try {
                if (!resultSet.next()) break;
                idTextField.setText(resultSet.getString("BOOKID"));
                bookTitleTextField.setText(resultSet.getString("TITLE"));
                bookISBNTextField.setText(resultSet.getString("ISBN"));
                shelfNumberTextField.setText(resultSet.getString("SHELFNUMBER"));
                authorTextField.setText(resultSet.getString("AUTHOR"));
                editionTextField.setText(resultSet.getString("EDITION"));
                facultyComboBox.setSelectedItem(resultSet.getString("FACULTY"));
                comboBox1.setSelectedItem(resultSet.getString("CATEGORY"));
                departmentComboBox.setSelectedItem(resultSet.getString("DEPARTMENT"));
                numberOfCopies.setText(resultSet.getString("COPIES"));

                initialCopies = Integer.parseInt(resultSet.getString("COPIES"));
                initialAvailableCopies = Integer.parseInt(resultSet.getString("AVAILABLE"));
                count ++;

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
        if (count == 0){
            JOptionPane.showMessageDialog(this, "No Record Found", "Try Again", JOptionPane.INFORMATION_MESSAGE    );
        }
    }

    private void populateDepartmentConboBox(ActionEvent e) {
        // TODO add your code here
        String selectedValue = facultyComboBox.getSelectedItem().toString();
        String sql = "SELECT departmentName from department WHERE facultyName LIKE" + "'%" + selectedValue + "%'";
        ResultSet resultSet = new Core().getRows(sql);
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            try {
                if (facultyComboBox.getSelectedItem().toString() != "Not Selected") {
                    departmentComboBox.addItem(resultSet.getString("departmentName"));
                }else if (facultyComboBox.getSelectedItem().toString() == "Not Selected"){
                    departmentComboBox.removeAllItems();
                    departmentComboBox.addItem("Not Selected");
                }


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void facultyComboBoxItemStateChanged(ItemEvent e) {
        // TODO add your code here
        departmentComboBox.removeAllItems();
        departmentComboBox.addItem("Not Selected");
    }


    private void searchBookNameFieldFocusGained(FocusEvent e) {
        // TODO add your code here
        searchBookByAuthorTextField.setText("");
    }

    private void searchBookByAuthorTextFieldFocusGained(FocusEvent e) {
        // TODO add your code here
        searchBookNameField.setText("");
    }

    private void saveBookToDB(ActionEvent e) {
        // TODO add your code here
        String title = bookTitleTextField.getText();
        String author = authorTextField.getText();
        String isbn = bookISBNTextField.getText();
        String edition = editionTextField.getText();
        String shelfNumber = shelfNumberTextField.getText();
        String category = comboBox1.getSelectedItem().toString();
        String department = departmentComboBox.getSelectedItem().toString();
        String faculty = facultyComboBox.getSelectedItem().toString();
        String copies = numberOfCopies.getText();
        String selectedPrimaryKey = idTextField.getText();

        if (selectedPrimaryKey.isEmpty() || title.isEmpty() || author.isEmpty() || isbn.isEmpty() || edition.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Cannot Start The Update Operation \n" +
                            "Try to search for any book",
                    "Try Searching Again",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int copies1 = Integer.parseInt(copies);
        int difference = copies1 - initialCopies;
        if (initialAvailableCopies == 0){
            initialAvailableCopies = copies1;
        }

        if (initialAvailableCopies > 0) {
            initialAvailableCopies += difference;
        }



        String [] tableData = {title, author, isbn, edition, shelfNumber, category, department, faculty, copies, String.valueOf(initialAvailableCopies)};
        String [] tableColumns = {"title", "author", "isbn", "edition", "shelfNumber", "category", "department", "faculty", "copies", "available"};
        String sqlQuery = new Core().prepareUpdateQuery("books", "bookID", selectedPrimaryKey, tableColumns);
        int returnValue = new Core().updateRecord(sqlQuery, tableData);

        if (returnValue == 1){
            JOptionPane.showMessageDialog(this,
                    "Book Details Updated Successfully",
                    "Update Successful",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        bookTitleTextField.setText("");
        authorTextField.setText("");
        bookISBNTextField.setText("");
        shelfNumberTextField.setText("");
        editionTextField.setText("");
        comboBox1.setSelectedItem("Not Selected");
        departmentComboBox.setSelectedItem("Not Selected");
        facultyComboBox.setSelectedItem("Not Selected");
        numberOfCopies.setText("");
        searchBookByAuthorTextField.setText("");
        searchBookNameField.setText("");
        idTextField.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        searchBookNameField = new JTextField();
        searchBookByAuthorTextField = new JTextField();
        searchBookByName = new JButton();
        searchBookByAuthor = new JButton();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        bookTitleTextField = new JTextField();
        authorTextField = new JTextField();
        bookISBNTextField = new JTextField();
        editionTextField = new JTextField();
        shelfNumberTextField = new JTextField();
        comboBox1 = new JComboBox<>();
        departmentComboBox = new JComboBox<>();
        facultyComboBox = new JComboBox<>();
        numberOfCopies = new JTextField();
        idTextField = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setAutoRequestFocus(false);
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
                label1.setBounds(15, 5, 180, 25);

                //---- label2 ----
                label2.setText("Search By Author");
                label2.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
                contentPanel.add(label2);
                label2.setBounds(430, 5, 160, 25);

                //---- searchBookNameField ----
                searchBookNameField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        searchBookNameFieldFocusGained(e);
                    }
                });
                contentPanel.add(searchBookNameField);
                searchBookNameField.setBounds(10, 35, 225, 35);

                //---- searchBookByAuthorTextField ----
                searchBookByAuthorTextField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        searchBookByAuthorTextFieldFocusGained(e);
                    }
                });
                contentPanel.add(searchBookByAuthorTextField);
                searchBookByAuthorTextField.setBounds(430, 35, 240, 35);

                //---- searchBookByName ----
                searchBookByName.setText("Search Book");
                searchBookByName.setBackground(Color.white);
                searchBookByName.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
                searchBookByName.setFocusable(false);
                searchBookByName.addActionListener(e -> searchBook(e));
                contentPanel.add(searchBookByName);
                searchBookByName.setBounds(250, 35, searchBookByName.getPreferredSize().width, 35);

                //---- searchBookByAuthor ----
                searchBookByAuthor.setText("Search Book");
                searchBookByAuthor.setBackground(Color.white);
                searchBookByAuthor.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
                searchBookByAuthor.setFocusable(false);
                searchBookByAuthor.addActionListener(e -> searchBook(e));
                contentPanel.add(searchBookByAuthor);
                searchBookByAuthor.setBounds(685, 35, searchBookByAuthor.getPreferredSize().width, 35);

                //---- label3 ----
                label3.setText("Book Title");
                label3.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label3.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label3);
                label3.setBounds(0, 125, 100, label3.getPreferredSize().height);

                //---- label4 ----
                label4.setText("Author");
                label4.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label4.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label4);
                label4.setBounds(395, 125, 70, label4.getPreferredSize().height);

                //---- label5 ----
                label5.setText("ISBN No");
                label5.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label5.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label5);
                label5.setBounds(0, 170, 100, label5.getPreferredSize().height);

                //---- label6 ----
                label6.setText("Edition");
                label6.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label6.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label6);
                label6.setBounds(395, 170, 70, label6.getPreferredSize().height);

                //---- label7 ----
                label7.setText("Shelf Number");
                label7.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label7.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label7);
                label7.setBounds(-5, 215, 110, label7.getPreferredSize().height);

                //---- label8 ----
                label8.setText("Category");
                label8.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label8.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label8);
                label8.setBounds(395, 215, 70, label8.getPreferredSize().height);

                //---- label9 ----
                label9.setText("Department");
                label9.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label9.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label9);
                label9.setBounds(370, 250, 95, 30);

                //---- label10 ----
                label10.setText("Faculty");
                label10.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label10.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label10);
                label10.setBounds(25, 250, 75, label10.getPreferredSize().height);

                //---- label11 ----
                label11.setText("Number of Copies");
                label11.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label11.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label11);
                label11.setBounds(new Rectangle(new Point(330, 300), label11.getPreferredSize()));
                contentPanel.add(bookTitleTextField);
                bookTitleTextField.setBounds(105, 120, 260, 35);
                contentPanel.add(authorTextField);
                authorTextField.setBounds(470, 120, 320, 35);
                contentPanel.add(bookISBNTextField);
                bookISBNTextField.setBounds(105, 165, 260, 35);
                contentPanel.add(editionTextField);
                editionTextField.setBounds(470, 165, 320, 35);
                contentPanel.add(shelfNumberTextField);
                shelfNumberTextField.setBounds(105, 210, 260, 35);

                //---- comboBox1 ----
                comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Not Selected"
                }));
                contentPanel.add(comboBox1);
                comboBox1.setBounds(470, 210, 320, 35);

                //---- departmentComboBox ----
                departmentComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Not Selected"
                }));
                contentPanel.add(departmentComboBox);
                departmentComboBox.setBounds(470, 250, 320, 35);

                //---- facultyComboBox ----
                facultyComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Not Selected"
                }));
                facultyComboBox.addActionListener(e -> populateDepartmentConboBox(e));
                facultyComboBox.addItemListener(e -> facultyComboBoxItemStateChanged(e));
                contentPanel.add(facultyComboBox);
                facultyComboBox.setBounds(105, 250, 260, 35);
                contentPanel.add(numberOfCopies);
                numberOfCopies.setBounds(470, 295, 105, 35);

                //---- idTextField ----
                idTextField.setVisible(false);
                contentPanel.add(idTextField);
                idTextField.setBounds(680, 295, 55, 35);

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
                okButton.setText("Save Changes");
                okButton.setBackground(UIManager.getColor("ActionButton.pressedBackground"));
                okButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                okButton.addActionListener(e -> saveBookToDB(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.setBackground(Color.white);
                cancelButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                cancelButton.addActionListener(e -> cancelEditBookFrame(e));
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
    private JTextField searchBookNameField;
    private JTextField searchBookByAuthorTextField;
    private JButton searchBookByName;
    private JButton searchBookByAuthor;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JTextField bookTitleTextField;
    private JTextField authorTextField;
    private JTextField bookISBNTextField;
    private JTextField editionTextField;
    private JTextField shelfNumberTextField;
    private JComboBox<String> comboBox1;
    private JComboBox<String> departmentComboBox;
    private JComboBox<String> facultyComboBox;
    private JTextField numberOfCopies;
    private JTextField idTextField;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new EditBookFrame();
    }
}
