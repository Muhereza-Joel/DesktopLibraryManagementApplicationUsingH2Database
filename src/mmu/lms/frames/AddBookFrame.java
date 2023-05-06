/*
 * Created by JFormDesigner on Tue Sep 20 15:59:59 EAT 2022
 */

package mmu.lms.frames;

import com.formdev.flatlaf.FlatLightLaf;
import mmu.lms.classes.Book;
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
public class AddBookFrame extends JDialog {
    public AddBookFrame() {
        initComponents();
        setTitle("Create New Book");
        setResizable(false);
        this.setModal(true);
        setMinimumSize(new Dimension(600,350));
        setLocationRelativeTo(this);
        fillBookCategoryComboBox();
        fillFacultyComboBox();

        setVisible(true);
    }
    private void fillBookCategoryComboBox(){
        String sql = "SELECT categoryName FROM category";
        ResultSet resultSet = new Core().getRows(sql);
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                bookLibraryCategory.addItem(resultSet.getString("categoryName"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void fillFacultyComboBox(){
        String sql = "SELECT facultyName FROM faculty";
        ResultSet resultSet = new Core().getRows(sql);
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
               universityFaculty.addItem(resultSet.getString("facultyName"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void cancelAddBookAction(ActionEvent e) {
        // TODO add your code here
        dispose();
    }

    private void addBookToDatabase(ActionEvent e){
            String title = bookTitle.getText();
            String author = bookAuthor.getText();
            String isbn = bookISBN.getText();
            String edition = editionTextField.getText();
            String bookshelfNumber = shelfNumber.getText();
            String category = bookLibraryCategory.getSelectedItem().toString();
            String department = universityDepartment.getSelectedItem().toString();
            String faculty = universityFaculty.getSelectedItem().toString();
            String copies = numberOfBooks.getValue().toString();
            String availableCopies = copies;

            if(title.isEmpty() || author.isEmpty() || isbn.isEmpty()
                    || edition.isEmpty() || bookshelfNumber.isEmpty()
                    || category.isEmpty() || department.isEmpty() || faculty.isEmpty()){
                JOptionPane.showMessageDialog(this,
                        "Please fill All Fields, Cannot Create Book With Empty Fiels",
                        "All Fields Are Required",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            

            String[] tableData = new String[10];
            tableData[0] = title;
            tableData[1] = author;
            tableData[2] = isbn;
            tableData[3] = edition;
            tableData[4] = bookshelfNumber;
            tableData[5] = category;
            tableData[6] = department;
            tableData[7] = faculty;
            tableData[8] = copies;
            tableData[9] = availableCopies;

            Book book = new Book();
            book.addBookData(tableData);

        bookTitle.setText("");
        bookAuthor.setText("");
        bookISBN.setText("");
        editionTextField.setText("");
        shelfNumber.setText("");
        bookLibraryCategory.setSelectedItem("Not Selected");
        universityDepartment.setSelectedItem("Not Selected");
        universityFaculty.setSelectedItem("Not Selected");
        numberOfBooks.setValue(1);
    }

    private void populateUniversityDepartmentComboBox(ActionEvent e) {
        // TODO add your code here
        String selectedValue = universityFaculty.getSelectedItem().toString();
        String sql = "SELECT departmentName from department WHERE facultyName LIKE" + "'%" + selectedValue + "%'";
        ResultSet resultSet = new Core().getRows(sql);
        universityDepartment.removeAllItems();
        universityDepartment.addItem("Not Selected");
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            try {
                if (universityFaculty.getSelectedItem().toString() != "Not Selected") {
                    universityDepartment.addItem(resultSet.getString("departmentName"));
                }else if (universityFaculty.getSelectedItem().toString() == "Not Selected"){
                    universityDepartment.removeAllItems();
                    universityDepartment.addItem("Not Selected");
                }


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void universityFacultyItemStateChanged(ItemEvent e) {
        // TODO add your code here
        universityDepartment.removeAllItems();
        universityDepartment.addItem("Not Selected");
    }

    private void universityFaculty(ActionEvent e) {
        // TODO add your code here
    }
    




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        addBookFrame = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        bookTitle = new JTextField();
        bookAuthor = new JTextField();
        bookISBN = new JTextField();
        numberOfBooks = new JSpinner();
        label8 = new JLabel();
        label9 = new JLabel();
        editionTextField = new JTextField();
        shelfNumber = new JTextField();
        universityFaculty = new JComboBox<>();
        bookLibraryCategory = new JComboBox<>();
        universityDepartment = new JComboBox<>();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelAddBookFrameButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== addBookFrame ========
        {
            addBookFrame.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //======== panel1 ========
                {
                    panel1.setLayout(null);

                    //---- label1 ----
                    label1.setText("Book Title");
                    label1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                    label1.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel1.add(label1);
                    label1.setBounds(15, 15, 160, 35);

                    //---- label2 ----
                    label2.setText("Author");
                    label2.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                    label2.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel1.add(label2);
                    label2.setBounds(15, 60, 160, 35);

                    //---- label3 ----
                    label3.setText("ISBN No");
                    label3.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                    label3.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel1.add(label3);
                    label3.setBounds(15, 100, 160, 35);

                    //---- label4 ----
                    label4.setText("Category");
                    label4.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                    label4.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel1.add(label4);
                    label4.setBounds(15, 215, 160, 35);

                    //---- label5 ----
                    label5.setText("Department");
                    label5.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                    label5.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel1.add(label5);
                    label5.setBounds(15, 295, 160, 35);

                    //---- label6 ----
                    label6.setText("Faculty");
                    label6.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                    label6.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel1.add(label6);
                    label6.setBounds(15, 255, 160, 35);

                    //---- label7 ----
                    label7.setText("Number of Copies");
                    label7.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                    label7.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel1.add(label7);
                    label7.setBounds(15, 340, 160, 35);

                    //---- bookTitle ----
                    bookTitle.setBackground(Color.white);
                    panel1.add(bookTitle);
                    bookTitle.setBounds(185, 15, 405, 35);

                    //---- bookAuthor ----
                    bookAuthor.setBackground(Color.white);
                    panel1.add(bookAuthor);
                    bookAuthor.setBounds(185, 55, 405, 35);

                    //---- bookISBN ----
                    bookISBN.setBackground(Color.white);
                    panel1.add(bookISBN);
                    bookISBN.setBounds(185, 95, 405, 35);

                    //---- numberOfBooks ----
                    numberOfBooks.setModel(new SpinnerNumberModel(1, 1, null, 1));
                    panel1.add(numberOfBooks);
                    numberOfBooks.setBounds(185, 335, 60, 35);

                    //---- label8 ----
                    label8.setText("Edition");
                    label8.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                    label8.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel1.add(label8);
                    label8.setBounds(20, 145, 157, 30);

                    //---- label9 ----
                    label9.setText("Shelf Number");
                    label9.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                    label9.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel1.add(label9);
                    label9.setBounds(25, 180, 155, 30);

                    //---- editionTextField ----
                    editionTextField.setBackground(Color.white);
                    panel1.add(editionTextField);
                    editionTextField.setBounds(185, 135, 405, 35);

                    //---- shelfNumber ----
                    shelfNumber.setBackground(Color.white);
                    panel1.add(shelfNumber);
                    shelfNumber.setBounds(185, 175, 405, 35);

                    //---- universityFaculty ----
                    universityFaculty.setModel(new DefaultComboBoxModel<>(new String[] {
                        "Not Selected"
                    }));
                    universityFaculty.addItemListener(e -> universityFacultyItemStateChanged(e));
                    universityFaculty.addActionListener(e -> populateUniversityDepartmentComboBox(e));
                    panel1.add(universityFaculty);
                    universityFaculty.setBounds(185, 255, 405, 35);

                    //---- bookLibraryCategory ----
                    bookLibraryCategory.setModel(new DefaultComboBoxModel<>(new String[] {
                        "Not Selected"
                    }));
                    panel1.add(bookLibraryCategory);
                    bookLibraryCategory.setBounds(185, 215, 405, 35);

                    //---- universityDepartment ----
                    universityDepartment.setModel(new DefaultComboBoxModel<>(new String[] {
                        "Not Selected"
                    }));
                    panel1.add(universityDepartment);
                    universityDepartment.setBounds(185, 295, 405, 35);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel1.getComponentCount(); i++) {
                            Rectangle bounds = panel1.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel1.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel1.setMinimumSize(preferredSize);
                        panel1.setPreferredSize(preferredSize);
                    }
                }
                contentPanel.add(panel1);
                panel1.setBounds(0, 0, 615, 380);

                //======== buttonBar ========
                {
                    buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                    buttonBar.setLayout(new GridBagLayout());
                    ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                    ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                    //---- okButton ----
                    okButton.setText("Add Book");
                    okButton.setFocusable(false);
                    okButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                    okButton.setBackground(UIManager.getColor("ActionButton.focusedBorderColor"));
                    okButton.addActionListener(e -> addBookToDatabase(e));
                    buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //---- cancelAddBookFrameButton ----
                    cancelAddBookFrameButton.setText("Cancel");
                    cancelAddBookFrameButton.setFocusable(false);
                    cancelAddBookFrameButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                    cancelAddBookFrameButton.setBackground(Color.white);
                    cancelAddBookFrameButton.addActionListener(e -> cancelAddBookAction(e));
                    buttonBar.add(cancelAddBookFrameButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                contentPanel.add(buttonBar);
                buttonBar.setBounds(0, 375, 613, buttonBar.getPreferredSize().height);

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
            addBookFrame.add(contentPanel, BorderLayout.CENTER);
        }
        contentPane.add(addBookFrame, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel addBookFrame;
    private JPanel contentPanel;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField bookTitle;
    private JTextField bookAuthor;
    private JTextField bookISBN;
    private JSpinner numberOfBooks;
    private JLabel label8;
    private JLabel label9;
    private JTextField editionTextField;
    private JTextField shelfNumber;
    private JComboBox<String> universityFaculty;
    private JComboBox<String> bookLibraryCategory;
    private JComboBox<String> universityDepartment;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelAddBookFrameButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {

        new AddBookFrame();
    }
}
