import database.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        if (databaseConnection.makeConnection())
            System.out.println("Connection Started");
        else
            System.out.println("connection not started");
        if (databaseConnection.closeConnection())
            System.out.println("connection closed");
        else
            System.out.println("error");
    }
}
