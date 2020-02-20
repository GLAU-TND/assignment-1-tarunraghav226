package database;

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

}
