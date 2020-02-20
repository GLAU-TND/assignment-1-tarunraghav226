import database.DatabaseConnection;
import database.DatabaseHelper;
import person.Person;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        if (databaseConnection.makeConnection())
            System.out.println("Connection Started");
        else
            System.out.println("connection not started");
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.getMaxId(databaseConnection.getConnection());
        Person person = new Person();
        person.setFirstName("f");
        person.setLastName("l");
        person.setEmailID("e");
        person.setPersonID(1);
        person.setPhoneNumbers("123");
        person.setPhoneNumbers("123423");
        person.setPhoneNumbers("1233453");
        databaseHelper.enterData(databaseConnection.getConnection(), person);
        if (databaseConnection.closeConnection())
            System.out.println("connection closed");
        else
            System.out.println("error");
    }
}
