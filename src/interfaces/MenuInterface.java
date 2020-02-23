package interfaces;

import java.sql.SQLException;

public interface MenuInterface {

    /**
     * Method displays choices to user
     *
     * @return Returns the user choice
     */
    public int showChoice();

    /**
     * Method helps user to insert new data
     */
    public void addChoice();

    /**
     * Method displays all the data to user in sorted manner
     */
    public void viewChoice();

    /**
     * Method searches the data according to first name
     */
    public void searchChoice();

    /**
     * Method deletes the data which user wants to be
     */
    public void deleteChoice() throws SQLException;

    /**
     * Method closes connection to database
     */
    public void exitChoice();
}
