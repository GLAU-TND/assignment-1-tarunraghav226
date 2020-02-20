package database;

import person.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {

    public int getMaxId(Connection connection) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;
        try {
            preparedStatement = connection.prepareStatement("select max(primarykey) from phoneBook;");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void enterData(Connection connection, Person person) {
        PreparedStatement preparedStatementForPhoneBook = null;
        PreparedStatement preparedStatementForPhoneNumbers = null;
        try {
            preparedStatementForPhoneBook = connection.prepareStatement("insert into phoneBook values (?,?,?,?)");
            preparedStatementForPhoneBook.setInt(1, person.getPersonID());
            preparedStatementForPhoneBook.setString(2, person.getFirstName());
            preparedStatementForPhoneBook.setString(3, person.getLastName());
            preparedStatementForPhoneBook.setString(4, person.getEmailID());

            String phoneNumber = person.getPhoneNumber();
            while (phoneNumber != null) {
                preparedStatementForPhoneNumbers = connection.prepareStatement("insert into phoneNumbers values (?,?)");
                preparedStatementForPhoneNumbers.setInt(1, person.getPersonID());
                preparedStatementForPhoneNumbers.setString(2, phoneNumber);
                phoneNumber = person.getPhoneNumber();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
