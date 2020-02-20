import database.DatabaseConnection;
import database.DatabaseHelper;
import person.Person;

import java.util.ArrayList;

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
        person.setFirstName("fa");
        person.setLastName("lds");
        person.setEmailID("eqew");
        person.setPersonID(2);
        person.setPhoneNumbers("1123");
        person.setPhoneNumbers("1123423");
        person.setPhoneNumbers("11233453");
        databaseHelper.enterData(databaseConnection.getConnection(), person);
        ArrayList<Person> arrayList = databaseHelper.getAllPhoneRecord(databaseConnection.getConnection());
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        if (databaseConnection.closeConnection())
            System.out.println("connection closed");
        else
            System.out.println("error");
    }
}
