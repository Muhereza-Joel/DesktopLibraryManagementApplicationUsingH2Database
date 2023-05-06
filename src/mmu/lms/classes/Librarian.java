package mmu.lms.classes;

import mmu.lms.frames.AddBookFrame;
import mmu.lms.frames.AdminDashboard;
import mmu.lms.frames.DeleteBookFrame;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Librarian extends Core{

    public Librarian(){

    }

    public void addBook(String [] tableColumns,String [] tableData){
        String sqlQuery = this.prepareInsertStatement("books", tableColumns);
        int retunedValue = this.insertRow(sqlQuery, tableData);

        if (retunedValue == 1){
            JOptionPane.showMessageDialog(new JDialog(), "New Book created succesfully", "Book Added", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void editBook(){

    }


}
