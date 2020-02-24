package database;

import dataStructures.MyLinkedList;
import node.Node;
import person.Person;

import java.sql.*;
import java.util.Scanner;

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

        } catch (SQLIntegrityConstraintViolationException e) {
            try {
                preparedStatementForPhoneBook = connection.prepareStatement("delete from phoneBook where primarykey = ?");
                preparedStatementForPhoneBook.setInt(1, person.getPersonID());
                preparedStatementForPhoneBook.executeUpdate();
            } catch (SQLException ea) {
                ea.printStackTrace();
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MyLinkedList<Person> getAllPhoneRecord(Connection connection) {
        MyLinkedList<Person> linkedList = new MyLinkedList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select * from phoneBook");
            getRecords(connection, linkedList, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linkedList;
    }

    public MyLinkedList<Person> searchRecord(Connection connection, String firstName) {
        MyLinkedList<Person> linkedList = new MyLinkedList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select * from phoneBook where firstName= ?");
            preparedStatement.setString(1, firstName);
            getRecords(connection, linkedList, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linkedList;
    }

    private void getRecords(Connection connection, MyLinkedList<Person> linkedList, PreparedStatement preparedStatement) throws SQLException {
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
            Node<Person> node = new Node<>();
            node.setData(person);
            linkedList.insert(node);
        }
    }

    public void deleteRecords(Connection connection) throws SQLException {
        Scanner scan = new Scanner(System.in);
        MyLinkedList<Person> linkedList = new MyLinkedList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from phoneBook");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Here are all your contacts:");
        getRecords(connection, linkedList, preparedStatement);

        int i = 1;
        while (true) {
            Node<Person> node = linkedList.getObject();
            if (node == null)
                break;
            System.out.println(i + ". " + node.getData().getFirstName() + " " + node.getData().getLastName());
            i++;
        }
        System.out.print("Press the number against the contact to delete it: ");
        int position = scan.nextInt();
        Person person = linkedList.delete(position).getData();

        preparedStatement = connection.prepareStatement("delete from phoneBook where primarykey = ?");
        preparedStatement.setInt(1, person.getPersonID());
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("delete  from phoneNumbers where primarykey = ?");
        preparedStatement.setInt(1, person.getPersonID());
        preparedStatement.executeUpdate();


        System.out.println(person.getFirstName() + " " + person.getLastName() + "'s contact deleted from list!");
    }
}
