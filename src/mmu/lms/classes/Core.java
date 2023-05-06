package mmu.lms.classes;

import org.h2.tools.Server;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class Core extends Component {

    private static Connection connection;

    private void connectToDB(){

        try {
            Server server = Server.createTcpServer("-webAllowOthers").start();
            String url = "jdbc:h2:" +server.getURL()+"/~/AppData/Roaming/MMU/mmu";
            String username = "sa";
            String password = "1234";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Failed To Connect To The Database\n" +
                    "Please Turn On Your Database Server", "Try Connecting To The Database", JOptionPane.ERROR_MESSAGE);
          e.printStackTrace();
        }

    }

    public ResultSet loginUser(String table, String [] tableData){
        connectToDB();
        ResultSet resultSet = null;
        String sql = "SELECT * FROM " + table + " WHERE username = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 1; i <= tableData.length; i++){
                preparedStatement.setString(i, tableData[i - 1]);
            }

            resultSet =preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     return resultSet;
    }


    public void updatedComments(){
        String sql = "SELECT * FROM Borrowed_Books";
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(sql);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate1 = simpleDateFormat.format(date);
            Date d1 = simpleDateFormat.parse(currentDate1);
            while (resultSet.next()){
                try {
                    Date d2 = simpleDateFormat.parse(resultSet.getString("returnDate"));
                    System.out.println(d2);
                    if (d1.compareTo(d2) > 0){
                        resultSet.updateString("comments", "Retun Date\n passed!!");
                        resultSet.updateRow();
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    public String prepareInsertStatement(String tableName, String [] tableColumns){
        String sqlQuery;
        String stringOne = "INSERT INTO " + tableName + "(" ;
        String stringTwo = "VALUES(";

        for (int i = 0; i < tableColumns.length; i++){
            stringOne += tableColumns[i];
            if (i < tableColumns.length - 1) // Separate table columns with commas
                stringOne += ",";

            stringTwo += "?";
            if (i < tableColumns.length - 1) // Separate the values to insert with commas
                stringTwo += ",";

            if (i == tableColumns.length - 1) // Close braces for insert into and values statement
            {
                stringOne += ")";
                stringTwo += ")";
            }
        }
        sqlQuery = stringOne + " " + stringTwo;
        return sqlQuery;
    }

    public int insertRow(String sqlQuery, String[] tableData){
        int returnValue = 0;
       this.connectToDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            for (int i = 1; i <= tableData.length; i++)
                preparedStatement.setString(i, tableData[i - 1]);

            returnValue = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
           e.printStackTrace();
        }

        return returnValue;
    }

    public String prepareUpdateQuery(String tableName, String tablePrimaryKey,String selectedPrimaryKey, String [] tableColumns ){
        String stringOne = "UPDATE " + tableName + " SET ";
        String stringTwo = "WHERE " + tablePrimaryKey + " = " + selectedPrimaryKey;

        for (int i = 0; i < tableColumns.length; i++){
            stringOne += tableColumns[i] + " = ?";

            if (i < tableColumns.length - 1) // Separate table columns with commas
                stringOne += ",";
        }

        String sqlQuery = stringOne + " " + stringTwo;
        return sqlQuery;
    }


    public  int updateRecord(String sqlQuery, String [] tableData){
        int returnValue = 0;
        connectToDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            for (int i = 1; i <= tableData.length; i++)
                preparedStatement.setString(i, tableData[i - 1]);
            returnValue = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return returnValue;
    }


    public int deleteRecord(String tableName, String tablePrimaryKey,int keyValue){
        int returnValue = 0;
        connectToDB();
        String sqlQuery = "DELETE FROM " + tableName + " WHERE " + tablePrimaryKey + " = " + "?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
//            preparedStatement.setString(1, keyValue);
            preparedStatement.setInt(1, keyValue);
            returnValue = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return returnValue;
    }

    public  ResultSet searchBook(String tableName, String columnName, String regEx){
        ResultSet resultSet = null;
        connectToDB();
        String sqlQuery = "SELECT bookID, title, author, edition FROM " + tableName + " WHERE " + columnName + " LIKE " + "?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, "%" + regEx + "%");
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public  ResultSet searchBookExpanded(String tableName, String columnName, String regEx){
        ResultSet resultSet = null;
        connectToDB();
        String sqlQuery = "SELECT * FROM " + tableName + " WHERE " + columnName + " LIKE " + "?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, "%" + regEx + "%");
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public  ResultSet searchBorrowedBook(String tableName, String columnName, String regEx){
        ResultSet resultSet = null;
        connectToDB();
        String sqlQuery = "SELECT studentName,regNumber,bookTitle,author,issueDate,returnDate FROM " + tableName + " WHERE " + columnName + " LIKE " + "?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, "%" + regEx + "%");
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public  ResultSet searchFaculty(String tableName, String columnName, String regEx){
        ResultSet resultSet = null;
        connectToDB();
        String sqlQuery = "SELECT * FROM " + tableName + " WHERE " + columnName + " LIKE " + "?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, "%" + regEx + "%");
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public ResultSet checkBookTitleDetails(String regNo, String bookTitle, String author){
        ResultSet resultSet = null;
        String sql = "SELECT * FROM borrowed_books WHERE regNumber = ? AND bookTitle = ? OR author = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, regNo);
            preparedStatement.setString(2, bookTitle);
            preparedStatement.setString(3, author);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       return resultSet;
    }

    public  ResultSet searchDepartment(String tableName, String columnName, String regEx){
        ResultSet resultSet = null;
        connectToDB();
        String sqlQuery = "SELECT * FROM " + tableName + " WHERE " + columnName + " LIKE " + "?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, "%" + regEx + "%");
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public int returnBook(String regNo, String bookTitle){
        int returnValue = 0;
        String sql = "DELETE FROM borrowed_books WHERE regNumber = ? AND bookTitle = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, regNo);
            preparedStatement.setString(2, bookTitle);
            returnValue = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return returnValue;
    }

    public int updateAvailableBookCount(String title, String author){
        int returnValue = 0;
        ResultSet resultSet = null;
        int available = 0;
        String bookTitle = "";
        String bookAuthor = "";

        String sql = "SELECT available, title, author FROM books WHERE title = ? AND author = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                available = resultSet.getInt("available") + 1;
                bookTitle = resultSet.getString("title");
                bookAuthor = resultSet.getString("author");
            }
            String sql1 = "UPDATE books SET available = ? WHERE title = ? AND author = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setString(1, String.valueOf(available));
            preparedStatement1.setString(2, bookTitle);
            preparedStatement1.setString(3,bookAuthor);
            returnValue = preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return returnValue;
    }


    public  ResultSet getRows(String query){
        connectToDB();
        ResultSet resultSet = null;
        try {
            Statement statement = makeStatement();
            resultSet = statement.executeQuery(query);
        }catch (SQLException queryFail){

        }
        return resultSet;
    }

    private static Statement makeStatement(){
        Statement statement = null;
        try {
            statement = connection.createStatement();
        }catch (SQLException e){}
        return statement;
    }

}
