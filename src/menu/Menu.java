package menu;

import database.DatabaseConnection;
import database.DatabaseHelper;
import person.Person;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    DatabaseHelper databaseHelper;
    DatabaseConnection databaseConnection;
    Scanner scan;

    public Menu() {
        scan = new Scanner(System.in);
        databaseHelper = new DatabaseHelper();
        databaseConnection = new DatabaseConnection();
        databaseConnection.makeConnection();
    }

    public int showChoice() {
        System.out.println("Welcome to Tarun's Contact List App\n" +
                "Press 1 to add a new contact\n" +
                "Press 2 to view all contacts\n" +
                "Press 3 to search for a contact\n" +
                "Press 4 to delete a contact\n" +
                "Press 5 to exit program");
        return scan.nextInt();
    }

    public void addChoice() {
        Person person = new Person();
        String name;
        System.out.println("You have chosen to add a new contact: \n" +
                "Please enter the name of the Person\n");
        while (true) {
            System.out.print("First Name: ");
            name = scan.nextLine().trim();
            if (checkName(name)) {
                person.setFirstName(name);
                break;
            } else {
                System.out.println("Enter Correctly");
            }
        }

        while (true) {
            System.out.print("Last Name: ");
            name = scan.nextLine().trim();
            if (checkName(name)) {
                person.setLastName(name);
                break;
            } else {
                System.out.println("Enter Correctly");
            }
        }

        String number;
        while (true) {
            System.out.print("Contact Number: ");
            number = scan.nextLine().trim();
            if (checkNumber(number)) {
                person.setPhoneNumbers(number);
                break;
            } else {
                System.out.println("Enter Correctly");
            }
        }

        char c;
        System.out.print("Would you like to add another contact number? (y/n): ");
        c = scan.nextLine().trim().charAt(0);
        while (c == 'y') {
            number = scan.nextLine().trim();
            if (checkNumber(number)) {
                person.setPhoneNumbers(number);
                break;
            } else {
                System.out.println("Enter Correctly");
            }
            System.out.print("Would you like to add another contact number? (y/n): ");
            c = scan.nextLine().trim().charAt(0);
        }
        String email = null;
        System.out.print("Would you like to add email address? (y/n): ");
        c = scan.nextLine().trim().charAt(0);
        if (c == 'y') {
            while (true) {
                System.out.print("Email Address: ");
                email = scan.nextLine().trim();
                if (checkEmail(email)) {
                    break;
                } else {
                    System.out.println("Enter correctly");
                }
            }
        }
        person.setEmailID(email);
        databaseHelper.enterData(databaseConnection.getConnection(), person);
    }

    private boolean checkName(String name) {
        boolean flag = false;
        final String regex = "^\\w*$";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(name);

        return matcher.matches();
    }

    private boolean checkEmail(String name) {
        boolean flag = false;
        return flag;
    }

    private boolean checkNumber(String number) {
        boolean flag = false;
        final String regex = "^\\d{10}$";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

}
