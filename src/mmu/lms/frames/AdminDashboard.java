/*
 * Created by JFormDesigner on Mon Sep 19 16:22:57 EAT 2022
 */

package mmu.lms.frames;

import com.formdev.flatlaf.FlatIntelliJLaf;
import mmu.lms.classes.Core;
import mmu.lms.classes.UIRefresher;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;


/**
 * @author Muhereza Joel
 */
public class AdminDashboard extends JFrame {
    public JTable getBooksTable() {
        return booksTable;
    }
    JTable booksTable = new JTable();
    JTable facultiesTable = new JTable();
    JTable departmentsTable = new JTable();
    JTable borrowedBooksTable = new JTable();
    double screenHeight =  Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public AdminDashboard() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        initComponents();
        ImageIcon icon = new ImageIcon("images/mmu logo.PNG");
//        setTitle("MMU - LMS");
        setIconImage(icon.getImage());
        setMinimumSize(new Dimension(600,475));
//        sidePanel.setSize(460, (int) screenHeight);
        scrollPane2.setSize((int) screenWidth, (int) screenHeight);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(this);
        setVisible(true);

        booksTable.setRowHeight(40);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setAlignmentX(JLabel.LEFT);
        rightRenderer.setForeground(Color.white);
        rightRenderer.setForeground(new Color(0x1450A9));
        rightRenderer.setPreferredSize(new Dimension(100, 50));
        booksTable.setBackground(new Color(242, 242, 242));
        booksTable.setGridColor( new Color(0xF1E9E9));
        booksTable.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        booksTable.getTableHeader().setDefaultRenderer(rightRenderer);
//        booksTable.setAutoCreateRowSorter(true);
        scrollPane2.setViewportView(booksTable);


    }


    private void addBook(ActionEvent e) {
        // TODO add your code here
          AddBookFrame frame =  new AddBookFrame();
    }

    private void borrowedBook(ActionEvent e) {
        // TODO add your code here
        String sql = "SELECT studentName, regNumber, faculty, department, bookTitle, author, issueDate, returnDate, comments FROM Borrowed_Books";
        new Core().updatedComments();
        ResultSet resultSet = new Core().getRows(sql);

        borrowedBooksTable.setBackground(new Color(242, 242, 242));
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setAlignmentX(JLabel.LEFT);
        rightRenderer.setForeground(Color.white);
        rightRenderer.setForeground(new Color(0x1450A9));
        rightRenderer.setPreferredSize(new Dimension(100, 50));
        borrowedBooksTable.setRowHeight(40);
        borrowedBooksTable.setGridColor(new Color(0xF1E9E9));
        borrowedBooksTable.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        borrowedBooksTable.getTableHeader().setDefaultRenderer(rightRenderer);

        borrowedBooksTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        if (borrowedBooksTable.getRowCount() == 0){
            scrollPane2.setLayout(null);
            JLabel label = new JLabel("");
            contentLable.setText("You have No Lent Any Book Books Currently");
            label.setBackground(Color.blue);
            label.setHorizontalAlignment(JLabel.LEFT);
            label.setVerticalAlignment(JLabel.TOP);
            label.setFont(new Font("Tahoma", Font.PLAIN, 18));
            scrollPane2.setViewportView(label);
        }
        if (borrowedBooksTable.getRowCount() > 0) {
            contentLable.setText("Showing All Currently Borrowed Books ");
            scrollPane2.setViewportView(borrowedBooksTable);
        }

    }

    private void generateCard(ActionEvent e) {
        // TODO add your code here
        new GenerateCardFrame();
    }

    private void generateReports(ActionEvent e) {
        // TODO add your code here
    }

    private void showCreateNewBookFrame(ActionEvent e) {
        // TODO add your code here
        new AddBookFrame();
    }



    private void showChangeUserName(ActionEvent e) {
        // TODO add your code here
        new ChangeUsernameFrame();
    }

    private void showChangePasswordFrame(ActionEvent e) {
        // TODO add your code here
        new ChangePasswordFrame();
    }

    private void showEditExistingBookFrame(ActionEvent e) {
        new EditBookFrame();
    }

    private void showDeleteBooksFrames(ActionEvent e) {
        // TODO add your code here
        new DeleteBookFrame();
    }


    private void showDeleteBooksFrame(ActionEvent e) {
        // TODO add your code here
        new DeleteBookFrame();
    }


    private void thisWindowGainedFocus(WindowEvent e) {
        new UIRefresher().showAllBooks(booksTable);
    }

    private void showAddFacultyFrame(ActionEvent e) {
        // TODO add your code here
        new AddFacultyFrame();
    }

    private void showAddDepartmentFrame(ActionEvent e) {
        // TODO add your code here
        new AddDepartmentFrame();
    }

    private void showAddCategoryFrame(ActionEvent e) {
        // TODO add your code here
        new AddCategoryFrame();
    }

    private void showEditFacultyFrame(ActionEvent e) {
        // TODO add your code here
        new EditFacultyFrame();
    }

    private void showDeleteMenuFrame(ActionEvent e) {
        // TODO add your code here
        new DeleteFacultyFrame();
    }

    private void fetchAllFuculties(ActionEvent e) {
        // TODO add your code here
        String sql = "SELECT facultyName, facultyLocation FROM faculty";
        ResultSet resultSet = new Core().getRows(sql);
        facultiesTable.setBackground(new Color(242, 242, 242));
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setAlignmentX(JLabel.LEFT);
        rightRenderer.setForeground(Color.white);
        rightRenderer.setForeground(new Color(0x1450A9));
        rightRenderer.setPreferredSize(new Dimension(100, 50));

        facultiesTable.setRowHeight(40);
        facultiesTable.setGridColor( new Color(0xF1E9E9));
        facultiesTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        facultiesTable.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        facultiesTable.getTableHeader().setDefaultRenderer(rightRenderer);
        contentLable.setText("Showing all Faculties");
        scrollPane2.setViewportView(facultiesTable);
    }

    private void showAllBooks(ActionEvent e) {
        // TODO add your code here
        scrollPane2.setViewportView(booksTable);
        contentLable.setText("Showing All Books In Library");
    }

    private void fetchAllDepartments(ActionEvent e) {
        // TODO add your code here
        String sql = "SELECT departmentName, facultyName FROM department";
        ResultSet resultSet = new Core().getRows(sql);
        departmentsTable.setBackground(new Color(242, 242, 242));
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setAlignmentX(JLabel.LEFT);
        rightRenderer.setForeground(Color.white);
        rightRenderer.setForeground(new Color(0x1450A9));
        rightRenderer.setPreferredSize(new Dimension(100, 50));
//        departmentsTable.setSelectionBackground(new Color(0xDFDFE3));
        departmentsTable.setRowHeight(40);
        departmentsTable.setGridColor( new Color(0xF1E9E9));
        departmentsTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        departmentsTable.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        departmentsTable.getTableHeader().setDefaultRenderer(rightRenderer);
        contentLable.setText("Showing all Departments Attached To The Faculty");
        scrollPane2.setViewportView(departmentsTable);
    }

    private void showEditDepartmentFrame(ActionEvent e) {
        // TODO add your code here
        new EditDepartmentFrame();
    }

    private void showDeleteDepartmentFrame(ActionEvent e) {
        // TODO add your code here
        new DeleteDepartmentFrame();
    }

    private void showReturnBooksFrame(ActionEvent e) {
        // TODO add your code here
        new ReturnBookFrame();
    }

    private void searchLibraryTextFieldFocusLost(FocusEvent e) {
        // TODO add your code here
    }

    private void searchLibraryTextFieldFocusGained(FocusEvent e) {
        // TODO add your code here
    }





    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu4 = new JMenu();
        menu11 = new JMenu();
        menuItem5 = new JMenuItem();
        menuItem8 = new JMenuItem();
        createNewBookMenuItem = new JMenuItem();
        menuItem2 = new JMenuItem();
        menu10 = new JMenu();
        menuItem7 = new JMenuItem();
        menuItem10 = new JMenuItem();
        deleteBooksMenuItem = new JMenuItem();
        menuItem4 = new JMenuItem();
        menu5 = new JMenu();
        menuItem6 = new JMenuItem();
        menuItem9 = new JMenuItem();
        editExistingBookMenuItem = new JMenuItem();
        menuItem3 = new JMenuItem();
        viewMenu = new JMenu();
        menuItem12 = new JMenuItem();
        menuItem13 = new JMenuItem();
        menuItem11 = new JMenuItem();
        vSpacer1 = new JPanel(null);
        fullScreenCheckBoxMenuItem = new JCheckBoxMenuItem();
        minimizedScreenCheckBoxMenuItem = new JCheckBoxMenuItem();
        settingsMenu = new JMenu();
        changeUserNameMenuItem = new JMenuItem();
        changePasswordMenuItem = new JMenuItem();
        reportsMenu = new JMenu();
        printReportMenuItem = new JMenuItem();
        contentLable = new JLabel();
        panel1 = new JPanel();
        scrollPane2 = new JScrollPane();
        separator1 = new JSeparator();
        tabbedPane3 = new JTabbedPane();
        panel2 = new JPanel();
        button6 = new JButton();
        button8 = new JButton();
        button9 = new JButton();
        button13 = new JButton();
        panel3 = new JPanel();
        button7 = new JButton();
        button10 = new JButton();
        button11 = new JButton();
        button12 = new JButton();
        panel4 = new JPanel();
        addBookButton = new JButton();
        generateCardButton = new JButton();
        borrowedBookButton = new JButton();
        generateReportsButton2 = new JButton();
        searchLibraryTextField = new JTextField();
        scrollPane1 = new JScrollPane();
        tree1 = new JTree();

        //======== this ========
        setBackground(UIManager.getColor("ColorPalette.grey15"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                thisWindowGainedFocus(e);
            }
        });
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {
            menuBar1.setAlignmentX(-0.5F);
            menuBar1.setBackground(Color.white);

            //======== menu4 ========
            {
                menu4.setText("File");
                menu4.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));

                //======== menu11 ========
                {
                    menu11.setText("New");
                    menu11.setFont(new Font("Segoe UI", Font.PLAIN, 16));

                    //---- menuItem5 ----
                    menuItem5.setText("Faculty");
                    menuItem5.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                    menuItem5.addActionListener(e -> showAddFacultyFrame(e));
                    menu11.add(menuItem5);

                    //---- menuItem8 ----
                    menuItem8.setText("Department");
                    menuItem8.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                    menuItem8.addActionListener(e -> showAddDepartmentFrame(e));
                    menu11.add(menuItem8);

                    //---- createNewBookMenuItem ----
                    createNewBookMenuItem.setText("Book");
                    createNewBookMenuItem.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                    createNewBookMenuItem.addActionListener(e -> showCreateNewBookFrame(e));
                    menu11.add(createNewBookMenuItem);

                    //---- menuItem2 ----
                    menuItem2.setText("Book Category");
                    menuItem2.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                    menuItem2.addActionListener(e -> showAddCategoryFrame(e));
                    menu11.add(menuItem2);
                }
                menu4.add(menu11);
                menu4.addSeparator();

                //======== menu10 ========
                {
                    menu10.setText("Delete");
                    menu10.setFont(new Font("Segoe UI", Font.PLAIN, 16));

                    //---- menuItem7 ----
                    menuItem7.setText("Delete Faculty");
                    menuItem7.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                    menuItem7.addActionListener(e -> showDeleteMenuFrame(e));
                    menu10.add(menuItem7);

                    //---- menuItem10 ----
                    menuItem10.setText("Delete Department");
                    menuItem10.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                    menuItem10.addActionListener(e -> showDeleteDepartmentFrame(e));
                    menu10.add(menuItem10);

                    //---- deleteBooksMenuItem ----
                    deleteBooksMenuItem.setText("Delete Books");
                    deleteBooksMenuItem.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                    deleteBooksMenuItem.addActionListener(e -> showDeleteBooksFrame(e));
                    menu10.add(deleteBooksMenuItem);

                    //---- menuItem4 ----
                    menuItem4.setText("Delete Category");
                    menuItem4.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                    menu10.add(menuItem4);
                }
                menu4.add(menu10);
            }
            menuBar1.add(menu4);

            //======== menu5 ========
            {
                menu5.setText("Edit");
                menu5.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));

                //---- menuItem6 ----
                menuItem6.setText("Edit Faculty");
                menuItem6.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                menuItem6.addActionListener(e -> showEditFacultyFrame(e));
                menu5.add(menuItem6);

                //---- menuItem9 ----
                menuItem9.setText("Edit Department");
                menuItem9.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                menuItem9.addActionListener(e -> showEditDepartmentFrame(e));
                menu5.add(menuItem9);

                //---- editExistingBookMenuItem ----
                editExistingBookMenuItem.setText("Edit Existing Book");
                editExistingBookMenuItem.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                editExistingBookMenuItem.addActionListener(e -> showEditExistingBookFrame(e));
                menu5.add(editExistingBookMenuItem);

                //---- menuItem3 ----
                menuItem3.setText("Edit Category");
                menuItem3.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                menu5.add(menuItem3);
            }
            menuBar1.add(menu5);

            //======== viewMenu ========
            {
                viewMenu.setText("View");
                viewMenu.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                viewMenu.setBackground(Color.white);

                //---- menuItem12 ----
                menuItem12.setText("View Books");
                menuItem12.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                menuItem12.setMnemonic('B');
                menuItem12.addActionListener(e -> showAllBooks(e));
                viewMenu.add(menuItem12);

                //---- menuItem13 ----
                menuItem13.setText("View Departments");
                menuItem13.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                menuItem13.addActionListener(e -> fetchAllDepartments(e));
                viewMenu.add(menuItem13);

                //---- menuItem11 ----
                menuItem11.setText("View Faculties");
                menuItem11.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                menuItem11.addActionListener(e -> fetchAllFuculties(e));
                viewMenu.add(menuItem11);
                viewMenu.addSeparator();

                //---- vSpacer1 ----
                vSpacer1.setMinimumSize(new Dimension(12, 20));
                vSpacer1.setMaximumSize(new Dimension(32767, 30));
                viewMenu.add(vSpacer1);

                //---- fullScreenCheckBoxMenuItem ----
                fullScreenCheckBoxMenuItem.setText("Fullscreen Window");
                fullScreenCheckBoxMenuItem.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                fullScreenCheckBoxMenuItem.setSelected(true);
                viewMenu.add(fullScreenCheckBoxMenuItem);

                //---- minimizedScreenCheckBoxMenuItem ----
                minimizedScreenCheckBoxMenuItem.setText("Minimized Window");
                minimizedScreenCheckBoxMenuItem.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                viewMenu.add(minimizedScreenCheckBoxMenuItem);
            }
            menuBar1.add(viewMenu);

            //======== settingsMenu ========
            {
                settingsMenu.setText("Settings");
                settingsMenu.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                settingsMenu.setHorizontalAlignment(SwingConstants.LEFT);
                settingsMenu.setBackground(Color.white);

                //---- changeUserNameMenuItem ----
                changeUserNameMenuItem.setText("Change Username");
                changeUserNameMenuItem.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                changeUserNameMenuItem.addActionListener(e -> showChangeUserName(e));
                settingsMenu.add(changeUserNameMenuItem);

                //---- changePasswordMenuItem ----
                changePasswordMenuItem.setText("Change Password");
                changePasswordMenuItem.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                changePasswordMenuItem.addActionListener(e -> showChangePasswordFrame(e));
                settingsMenu.add(changePasswordMenuItem);
            }
            menuBar1.add(settingsMenu);

            //======== reportsMenu ========
            {
                reportsMenu.setText("Reports");
                reportsMenu.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                reportsMenu.setHorizontalAlignment(SwingConstants.LEFT);
                reportsMenu.setBackground(Color.white);

                //---- printReportMenuItem ----
                printReportMenuItem.setText("Print report");
                printReportMenuItem.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
                reportsMenu.add(printReportMenuItem);
            }
            menuBar1.add(reportsMenu);
        }
        setJMenuBar(menuBar1);

        //---- contentLable ----
        contentLable.setText("Showing All Books In Library");
        contentLable.setFont(new Font("Segoe UI Light", Font.BOLD, 18));

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout(1, 2));
        }

        //======== scrollPane2 ========
        {
            scrollPane2.setBackground(Color.white);
            scrollPane2.setBorder(new LineBorder(new Color(0xf7eeee)));
            scrollPane2.setOpaque(false);
            scrollPane2.setPreferredSize(new Dimension(12, 3000));

            //---- separator1 ----
            separator1.setOpaque(true);
            scrollPane2.setViewportView(separator1);
        }

        //======== tabbedPane3 ========
        {
            tabbedPane3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            tabbedPane3.setOpaque(true);
            tabbedPane3.setRequestFocusEnabled(false);
            tabbedPane3.setFocusable(false);

            //======== panel2 ========
            {
                panel2.setMaximumSize(new Dimension(32767, 40));
                panel2.setLayout(null);

                //---- button6 ----
                button6.setText("Add Faculty");
                button6.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                button6.setFocusable(false);
                button6.addActionListener(e -> showAddFacultyFrame(e));
                panel2.add(button6);
                button6.setBounds(5, 0, button6.getPreferredSize().width, 35);

                //---- button8 ----
                button8.setText("View Faculties");
                button8.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                button8.setFocusable(false);
                button8.addActionListener(e -> fetchAllFuculties(e));
                panel2.add(button8);
                button8.setBounds(115, 0, button8.getPreferredSize().width, 35);

                //---- button9 ----
                button9.setText("Edit Faculty");
                button9.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                button9.setFocusable(false);
                button9.addActionListener(e -> showEditFacultyFrame(e));
                panel2.add(button9);
                button9.setBounds(235, 0, button9.getPreferredSize().width, 35);

                //---- button13 ----
                button13.setText("Delete Faculty");
                button13.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                button13.setFocusable(false);
                button13.addActionListener(e -> showDeleteMenuFrame(e));
                panel2.add(button13);
                button13.setBounds(340, 0, button13.getPreferredSize().width, 35);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel2.getComponentCount(); i++) {
                        Rectangle bounds = panel2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel2.setMinimumSize(preferredSize);
                    panel2.setPreferredSize(preferredSize);
                }
            }
            tabbedPane3.addTab("Faculties", panel2);

            //======== panel3 ========
            {
                panel3.setLayout(null);

                //---- button7 ----
                button7.setText("Add Faculty");
                button7.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                button7.setFocusable(false);
                button7.addActionListener(e -> showAddDepartmentFrame(e));
                panel3.add(button7);
                button7.setBounds(0, 0, button7.getPreferredSize().width, 35);

                //---- button10 ----
                button10.setText("View Departments");
                button10.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                button10.setFocusable(false);
                button10.addActionListener(e -> fetchAllDepartments(e));
                panel3.add(button10);
                button10.setBounds(110, 0, button10.getPreferredSize().width, 35);

                //---- button11 ----
                button11.setText("Edit Departmrnt");
                button11.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                button11.setFocusable(false);
                button11.addActionListener(e -> showEditDepartmentFrame(e));
                panel3.add(button11);
                button11.setBounds(260, 0, button11.getPreferredSize().width, 35);

                //---- button12 ----
                button12.setText("Delete Department");
                button12.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                button12.setFocusable(false);
                button12.addActionListener(e -> showDeleteDepartmentFrame(e));
                panel3.add(button12);
                button12.setBounds(395, 0, button12.getPreferredSize().width, 35);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel3.getComponentCount(); i++) {
                        Rectangle bounds = panel3.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel3.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel3.setMinimumSize(preferredSize);
                    panel3.setPreferredSize(preferredSize);
                }
            }
            tabbedPane3.addTab("Departments", panel3);

            //======== panel4 ========
            {
                panel4.setLayout(null);

                //---- addBookButton ----
                addBookButton.setText("Add Book");
                addBookButton.setFocusable(false);
                addBookButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                addBookButton.setToolTipText("Add a new book to the library");
                addBookButton.addActionListener(e -> addBook(e));
                panel4.add(addBookButton);
                addBookButton.setBounds(5, 0, 110, 35);

                //---- generateCardButton ----
                generateCardButton.setText("Lend Book");
                generateCardButton.setFocusable(false);
                generateCardButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                generateCardButton.addActionListener(e -> generateCard(e));
                panel4.add(generateCardButton);
                generateCardButton.setBounds(115, 0, 110, 35);

                //---- borrowedBookButton ----
                borrowedBookButton.setText("Borrowed Books");
                borrowedBookButton.setFocusable(false);
                borrowedBookButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                borrowedBookButton.addActionListener(e -> borrowedBook(e));
                panel4.add(borrowedBookButton);
                borrowedBookButton.setBounds(230, 0, 135, 35);

                //---- generateReportsButton2 ----
                generateReportsButton2.setText("Return Book");
                generateReportsButton2.setFocusable(false);
                generateReportsButton2.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
                generateReportsButton2.addActionListener(e -> showReturnBooksFrame(e));
                panel4.add(generateReportsButton2);
                generateReportsButton2.setBounds(370, 0, 110, 35);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel4.getComponentCount(); i++) {
                        Rectangle bounds = panel4.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel4.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel4.setMinimumSize(preferredSize);
                    panel4.setPreferredSize(preferredSize);
                }
            }
            tabbedPane3.addTab("Books", panel4);

            //---- searchLibraryTextField ----
            searchLibraryTextField.setText("Search Library....");
            searchLibraryTextField.setMargin(new Insets(0, 16, 0, 16));
            searchLibraryTextField.setMinimumSize(new Dimension(49, 50));
            searchLibraryTextField.setPreferredSize(new Dimension(105, 35));
            searchLibraryTextField.setBorder(null);
            searchLibraryTextField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    searchLibraryTextFieldFocusGained(e);
                }
                @Override
                public void focusLost(FocusEvent e) {
                    searchLibraryTextFieldFocusLost(e);
                }
            });
            tabbedPane3.addTab("Search", searchLibraryTextField);
        }

        //======== scrollPane1 ========
        {

            //---- tree1 ----
            tree1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            tree1.setModel(new DefaultTreeModel(
                new DefaultMutableTreeNode("LMS") {
                    {
                        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("Faculties\t");
                            node1.add(new DefaultMutableTreeNode("Departments"));
                        add(node1);
                        node1 = new DefaultMutableTreeNode("Books");
                            node1.add(new DefaultMutableTreeNode("Categories"));
                        add(node1);
                        node1 = new DefaultMutableTreeNode("Records");
                            node1.add(new DefaultMutableTreeNode("Reports"));
                        add(node1);
                    }
                }));
            tree1.setRequestFocusEnabled(false);
            tree1.setShowsRootHandles(false);
            tree1.setFocusable(false);
            tree1.setRowHeight(25);
            scrollPane1.setViewportView(tree1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tabbedPane3, GroupLayout.Alignment.LEADING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(contentLable, GroupLayout.PREFERRED_SIZE, 1218, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 1190, GroupLayout.PREFERRED_SIZE))))))
                    .addContainerGap(13, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(tabbedPane3, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(contentLable, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 546, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 595, GroupLayout.PREFERRED_SIZE))
                    .addGap(78, 78, 78)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu4;
    private JMenu menu11;
    private JMenuItem menuItem5;
    private JMenuItem menuItem8;
    private JMenuItem createNewBookMenuItem;
    private JMenuItem menuItem2;
    private JMenu menu10;
    private JMenuItem menuItem7;
    private JMenuItem menuItem10;
    private JMenuItem deleteBooksMenuItem;
    private JMenuItem menuItem4;
    private JMenu menu5;
    private JMenuItem menuItem6;
    private JMenuItem menuItem9;
    private JMenuItem editExistingBookMenuItem;
    private JMenuItem menuItem3;
    private JMenu viewMenu;
    private JMenuItem menuItem12;
    private JMenuItem menuItem13;
    private JMenuItem menuItem11;
    private JPanel vSpacer1;
    private JCheckBoxMenuItem fullScreenCheckBoxMenuItem;
    private JCheckBoxMenuItem minimizedScreenCheckBoxMenuItem;
    private JMenu settingsMenu;
    private JMenuItem changeUserNameMenuItem;
    private JMenuItem changePasswordMenuItem;
    private JMenu reportsMenu;
    private JMenuItem printReportMenuItem;
    private JLabel contentLable;
    private JPanel panel1;
    private JScrollPane scrollPane2;
    private JSeparator separator1;
    private JTabbedPane tabbedPane3;
    private JPanel panel2;
    private JButton button6;
    private JButton button8;
    private JButton button9;
    private JButton button13;
    private JPanel panel3;
    private JButton button7;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JPanel panel4;
    private JButton addBookButton;
    private JButton generateCardButton;
    private JButton borrowedBookButton;
    private JButton generateReportsButton2;
    private JTextField searchLibraryTextField;
    private JScrollPane scrollPane1;
    private JTree tree1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new AdminDashboard();
    }
}
