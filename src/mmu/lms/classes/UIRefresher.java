package mmu.lms.classes;

import mmu.lms.frames.EditBookFrame;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class UIRefresher {

    public void showAllBooks(JTable booksTable){
        String query = "SELECT title,author,edition,shelfNumber,category,department,faculty,copies, available FROM books";
        ResultSet resultSet = new Core().getRows(query);
        booksTable.setModel(DbUtils.resultSetToTableModel(resultSet));

        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem1 = new JMenuItem("Edit");
        JMenuItem jMenuItem2 = new JMenuItem("View Details");
        JMenuItem jMenuItem3 = new JMenuItem("Delete");

        JMenuItem jMenuItem4 = new JMenuItem("Lend this Book");
        JMenuItem jMenuItem5 = new JMenuItem("Print Details");

        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditBookFrame();
            }
        });

        JSeparator jSeparator = new JSeparator();

        jPopupMenu.add(jMenuItem1);
        jPopupMenu.add(jMenuItem2);
        jPopupMenu.add(jMenuItem3);
        jPopupMenu.add(jSeparator);
        jPopupMenu.add(jMenuItem4);
        jPopupMenu.add(jMenuItem5);


        booksTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if (e.isPopupTrigger()){
                    int rowSelected = booksTable.rowAtPoint(e.getPoint());
                    if (rowSelected >= 0 && rowSelected < booksTable.getRowCount()){
                        booksTable.setRowSelectionInterval(rowSelected, rowSelected);
                    } else {
                        booksTable.clearSelection();
                    }

                    jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if (e.isPopupTrigger()) {
                    int row = booksTable.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < booksTable.getRowCount()) {
                        booksTable.setRowSelectionInterval(row, row);
                    } else {
                        booksTable.clearSelection();
                    }
                    jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }


}
