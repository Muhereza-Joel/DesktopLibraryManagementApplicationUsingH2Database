package mmu.lms.classes;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private String edition;
    private String shelfNumber;
    private String category;
    private String department;
    private String faculty;
    private String copies;
    private String available;

    Librarian librarian = new Librarian();

    private String [] tableColumns = new String[10];
    private String [] rowData = new String[10];


    public void addBookData(String [] tableData){
        tableColumns[0] = "title";
        tableColumns[1] = "author";
        tableColumns[2] = "isbn";
        tableColumns[3] = "edition";
        tableColumns[4] = "shelfNumber";
        tableColumns[5] = "category";
        tableColumns[6] = "department";
        tableColumns[7] = "faculty";
        tableColumns[8] = "copies";
        tableColumns[9] = "available";

        librarian.addBook(tableColumns, tableData);
    }


}
