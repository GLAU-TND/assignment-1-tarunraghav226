package database;

import person.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            preparedStatementForPhoneBook.executeUpdate();

            String phoneNumber = person.getPhoneNumber();
            while (phoneNumber != null) {
                preparedStatementForPhoneNumbers = connection.prepareStatement("insert into phoneNumbers values (?,?)");
                preparedStatementForPhoneNumbers.setInt(1, person.getPersonID());
                preparedStatementForPhoneNumbers.setString(2, phoneNumber);
                phoneNumber = person.getPhoneNumber();
                preparedStatementForPhoneNumbers.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> getAllPhoneRecord(Connection connection) {
        ArrayList<Person> arrayList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select * from phoneBook");
            getRecords(connection, arrayList, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public ArrayList<Person> searchRecord(Connection connection, String firstName) {
        ArrayList<Person> arrayList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select * from phoneBook where firstName= ?");
            preparedStatement.setString(1, firstName);
            getRecords(connection, arrayList, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private void getRecords(Connection connection, ArrayList<Person> arrayList, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Person person = new Person();
            person.setFirstName(resultSet.getNString("firstName"));
            person.setLastName(resultSet.getNString("lastName"));
            person.setEmailID(resultSet.getNString("emailID"));
            person.setPersonID(resultSet.getInt("primarykey"));
            PreparedStatement preparedStatement1 = connection.prepareStatement("select * from phoneNumbers where primarykey = ?");
            preparedStatement1.setInt(1, person.getPersonID());
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                person.setPhoneNumbers(resultSet1.getNString("phonenumber"));
            }
            arrayList.add(person);
        }
    }

}
