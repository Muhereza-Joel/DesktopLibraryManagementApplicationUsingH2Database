/*
 * Created by JFormDesigner on Wed Sep 21 19:31:47 EAT 2022
 */

package mmu.lms.frames;

import mmu.lms.classes.Core;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.ResultSet;
import java.util.Date;

/**
 * @author Muhereza Joel
 */
public class GenerateCardFrame extends JDialog {
    ResultSet resultSet = null;
    int available = 0;
    int newAvailableCount = 0;
    int borrowedBookID = 0;
    Date date = new Date();
    public GenerateCardFrame() {
        initComponents();
        setTitle("Lend Book");
        setResizable(false);
        this.setModal(true);
        setLocationRelativeTo(this);
        setVisible(true);

        Calendar c = Calendar.getInstance();

    }


    private void cancelGenerateCard(ActionEvent e) {
        // TODO add your code here
        dispose();
    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        String sql = "SELECT facultyName FROM faculty";
        ResultSet resultSet =  new Core().getRows(sql);
        while (true){
            try {
                if (!resultSet.next()) break;
                facultyComboBox.addItem(resultSet.getString("facultyName"));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = simpleDateFormat.format(date);
        issueDateTextField.setText(currentDate);

    }

    private void facultyComboBoxItemStateChanged(ItemEvent e) {
        // TODO add your code here
        String searchText = (String) facultyComboBox.getSelectedItem();
        ResultSet resultSet = new Core().searchDepartment("department","facultyName", searchText);
        departmentComboBox.removeAllItems();
        departmentComboBox.addItem("Not Selected");
        while (true){
            try {
                if (!resultSet.next()) break;
                departmentComboBox.addItem(resultSet.getString("departmentName"));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    private void bookTitleTextFieldFocusGained(FocusEvent e) {
        // TODO add your code here
        bookAuthorTextField.setText("");
    }

    private void bookAuthorTextFieldFocusGained(FocusEvent e) {
        // TODO add your code here
        bookTitleTextField.setText("");
    }

    private void searchBook(ActionEvent e) {
        // TODO add your code here
        int count = 0;
        if (e.getSource() == searchBookByTitleButton){
            String searchText = bookTitleTextField.getText();
            if (searchText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please Provide The Title\n of the Book",
                        "Try Again",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            resultSet = new Core().searchBookExpanded("books", "title", searchText);
        }

        if (e.getSource() == searchBookByAuthorButton){
            String searchText = bookAuthorTextField.getText();

            if (searchText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please Provide The Author\n of the Book",
                        "Try Again",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            resultSet = new Core().searchBookExpanded("books", "author", searchText);
        }

        while (true){
            try {
                if (!resultSet.next()) break;
                available = resultSet.getInt("available");

                if (available == 0){
                    JOptionPane.showMessageDialog(this,
                            "This Book Is Not Available for Lending \n" +
                                    "Try returning if from borrowed books \n" +
                                    "and try again",
                            "Try Again",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if (available != 0){
                    newAvailableCount =  available - 1;
                }
                String number = regNoTextField.getText();
                String title = resultSet.getString("title");
                String author1 = resultSet.getString("author");
                ResultSet resultSet1 = new Core().checkBookTitleDetails(number, title, author1);
                if (resultSet1.next()) {
                    if (String.valueOf(resultSet1.getString("regNumber")).equals(number)){
                        JOptionPane.showMessageDialog(this,
                                        resultSet1.getString("studentName") +
                                                "\nAlready has this book, The Student\n" +
                                                "Should first return it!",
                                "Double Check in Borrowed Books",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }


                borrowedBookID = resultSet.getInt("bookID");
                foundBookTitleTextField.setText(resultSet.getString("title"));
                foundBookAuthorTextField.setText(resultSet.getString("author"));

                count ++;

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }

        if (count == 0){
            JOptionPane.showMessageDialog(this,"No Book Found!", "Try Again", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void saveBorrowDetails(ActionEvent e) {
        // TODO add your code here
        String name = studentNameTextField.getText();
        String regNo = regNoTextField.getText();
        String faculty = (String) facultyComboBox.getSelectedItem();
        String department = (String) departmentComboBox.getSelectedItem();
        String title = foundBookTitleTextField.getText();
        String author = foundBookAuthorTextField.getText();
        String issueDate = issueDateTextField.getText();
        String returnDate = returnDateTextField.getText();

        if (name.isEmpty() || regNo.isEmpty() || faculty.isEmpty() || department.isEmpty() || title.isEmpty() || author.isEmpty() || issueDate.isEmpty() || returnDate.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please Fill All Fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date d1 = simpleDateFormat.parse(issueDate);
            Date d2 = simpleDateFormat.parse(returnDate);
            if (d1.compareTo(d2) > 0){
                JOptionPane.showMessageDialog(this,
                        "Please Choose a date after today",
                        "Try Again",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this,
                    "Plesase Separate date with '/'",
                    "Try Again",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }


        String [] tableColumns = {"studentName", "regNumber","faculty", "department", "bookTitle", "author", "issueDate","returnDate"};
        String [] tableData = {name, regNo, faculty, department, title, author, issueDate, returnDate};

        String sqlQuey = new Core().prepareInsertStatement("borrowed_books", tableColumns);
        int returnValue = new Core().insertRow(sqlQuey, tableData);

       if (returnValue == 1){
           JOptionPane.showMessageDialog(this,
                   "Record Saved Successfully",
                   "Record Saved",
                   JOptionPane.INFORMATION_MESSAGE);
       }
       String id = String.valueOf(borrowedBookID);
       String [] tableColumns1 = {"available"};
       String [] tableData2 = {String.valueOf(newAvailableCount)};
       String sqlQuery2 = new Core().prepareUpdateQuery("books", "bookID", id, tableColumns1);
       new Core().updateRecord(sqlQuery2, tableData2);

       studentNameTextField.setText("");
       regNoTextField.setText("");
       facultyComboBox.setSelectedItem("Not Selected");
       departmentComboBox.setSelectedItem("Not Selected");
       bookTitleTextField.setText("");
       bookAuthorTextField.setText("");
       foundBookTitleTextField.setText("");
       foundBookAuthorTextField.setText("");
       issueDateTextField.setText("");
       returnDateTextField.setText("");
       available = 0;
       newAvailableCount = 0;
       borrowedBookID = 0;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        studentNameTextField = new JTextField();
        regNoTextField = new JTextField();
        facultyComboBox = new JComboBox<>();
        foundBookTitleTextField = new JTextField();
        foundBookAuthorTextField = new JTextField();
        bookTitleTextField = new JTextField();
        searchBookByTitleButton = new JButton();
        bookAuthorTextField = new JTextField();
        searchBookByAuthorButton = new JButton();
        departmentComboBox = new JComboBox<>();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        issueDateTextField = new JTextField();
        returnDateTextField = new JTextField();
        label14 = new JLabel();
        label15 = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

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
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(10, 10), label1.getPreferredSize()));

                //---- label2 ----
                label2.setText("Personal Identification");
                label2.setFont(new Font("Segoe UI Light", Font.ITALIC, 12));
                contentPanel.add(label2);
                label2.setBounds(190, 15, 130, label2.getPreferredSize().height);

                //---- label3 ----
                label3.setText("Student Name");
                label3.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label3.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label3);
                label3.setBounds(15, 50, 160, label3.getPreferredSize().height);

                //---- label4 ----
                label4.setText("Registration Number");
                label4.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label4.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label4);
                label4.setBounds(15, 85, 160, label4.getPreferredSize().height);

                //---- label5 ----
                label5.setText("Faculty");
                label5.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label5.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label5);
                label5.setBounds(15, 115, 160, label5.getPreferredSize().height);

                //---- label6 ----
                label6.setText("Book Details");
                label6.setFont(new Font("Segoe UI Light", Font.ITALIC, 12));
                contentPanel.add(label6);
                label6.setBounds(190, 310, 135, label6.getPreferredSize().height);

                //---- label7 ----
                label7.setText("Book Title");
                label7.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label7.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label7);
                label7.setBounds(15, 345, 160, label7.getPreferredSize().height);

                //---- label8 ----
                label8.setText("Author");
                label8.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label8.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label8);
                label8.setBounds(15, 380, 160, label8.getPreferredSize().height);
                contentPanel.add(label9);
                label9.setBounds(new Rectangle(new Point(25, 205), label9.getPreferredSize()));
                contentPanel.add(studentNameTextField);
                studentNameTextField.setBounds(190, 40, 255, 35);
                contentPanel.add(regNoTextField);
                regNoTextField.setBounds(190, 80, 255, 30);

                //---- facultyComboBox ----
                facultyComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Not Selected"
                }));
                facultyComboBox.addItemListener(e -> facultyComboBoxItemStateChanged(e));
                contentPanel.add(facultyComboBox);
                facultyComboBox.setBounds(190, 115, 255, 35);

                //---- foundBookTitleTextField ----
                foundBookTitleTextField.setEditable(false);
                foundBookTitleTextField.setFocusable(false);
                contentPanel.add(foundBookTitleTextField);
                foundBookTitleTextField.setBounds(190, 335, 255, 35);

                //---- foundBookAuthorTextField ----
                foundBookAuthorTextField.setEditable(false);
                foundBookAuthorTextField.setFocusable(false);
                contentPanel.add(foundBookAuthorTextField);
                foundBookAuthorTextField.setBounds(190, 375, 255, 35);

                //---- bookTitleTextField ----
                bookTitleTextField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        bookTitleTextFieldFocusGained(e);
                    }
                });
                contentPanel.add(bookTitleTextField);
                bookTitleTextField.setBounds(190, 220, 255, 35);

                //---- searchBookByTitleButton ----
                searchBookByTitleButton.setText("Search");
                searchBookByTitleButton.addActionListener(e -> searchBook(e));
                contentPanel.add(searchBookByTitleButton);
                searchBookByTitleButton.setBounds(450, 220, searchBookByTitleButton.getPreferredSize().width, 35);

                //---- bookAuthorTextField ----
                bookAuthorTextField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        bookAuthorTextFieldFocusGained(e);
                    }
                });
                contentPanel.add(bookAuthorTextField);
                bookAuthorTextField.setBounds(190, 260, 255, 35);

                //---- searchBookByAuthorButton ----
                searchBookByAuthorButton.setText("Search");
                searchBookByAuthorButton.addActionListener(e -> searchBook(e));
                contentPanel.add(searchBookByAuthorButton);
                searchBookByAuthorButton.setBounds(450, 260, searchBookByAuthorButton.getPreferredSize().width, 35);

                //---- departmentComboBox ----
                departmentComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Not Selected"
                }));
                contentPanel.add(departmentComboBox);
                departmentComboBox.setBounds(190, 155, 255, 35);

                //---- label10 ----
                label10.setText("Department");
                label10.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label10.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label10);
                label10.setBounds(15, 160, 158, 21);

                //---- label11 ----
                label11.setText("Search By Title");
                label11.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label11.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label11);
                label11.setBounds(20, 225, 156, 25);

                //---- label12 ----
                label12.setText("Search By Author");
                label12.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label12.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label12);
                label12.setBounds(15, 265, 161, 25);

                //---- label13 ----
                label13.setText("Search Procedure");
                label13.setFont(new Font("Segoe UI Light", Font.ITALIC, 12));
                contentPanel.add(label13);
                label13.setBounds(new Rectangle(new Point(185, 200), label13.getPreferredSize()));

                //---- issueDateTextField ----
                issueDateTextField.setEditable(false);
                issueDateTextField.setFocusable(false);
                contentPanel.add(issueDateTextField);
                issueDateTextField.setBounds(190, 415, 255, 35);
                contentPanel.add(returnDateTextField);
                returnDateTextField.setBounds(190, 455, 255, 35);

                //---- label14 ----
                label14.setText("Issue Date");
                label14.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label14.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label14);
                label14.setBounds(15, 420, 158, 21);

                //---- label15 ----
                label15.setText("Return Date");
                label15.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
                label15.setHorizontalAlignment(SwingConstants.RIGHT);
                contentPanel.add(label15);
                label15.setBounds(10, 460, 162, 25);

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
                okButton.setText("Save Record");
                okButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                okButton.setFocusable(false);
                okButton.addActionListener(e -> saveBorrowDetails(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                cancelButton.setFocusable(false);
                cancelButton.setBackground(Color.white);
                cancelButton.addActionListener(e -> cancelGenerateCard(e));
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
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JTextField studentNameTextField;
    private JTextField regNoTextField;
    private JComboBox<String> facultyComboBox;
    private JTextField foundBookTitleTextField;
    private JTextField foundBookAuthorTextField;
    private JTextField bookTitleTextField;
    private JButton searchBookByTitleButton;
    private JTextField bookAuthorTextField;
    private JButton searchBookByAuthorButton;
    private JComboBox<String> departmentComboBox;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JTextField issueDateTextField;
    private JTextField returnDateTextField;
    private JLabel label14;
    private JLabel label15;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new GenerateCardFrame();
    }
}
