import menu.Menu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        boolean flag = true;
        Menu menu = new Menu();
        while (flag) {
            int n = menu.showChoice();
            switch (n) {
                case 1:
                    menu.addChoice();
                    break;
                case 2:
                    menu.viewChoice();
                    break;
                case 3:
                    menu.searchChoice();
                    break;
                case 4:
                    menu.deleteChoice();
                    break;
                case 5:
                    menu.exitChoice();
                    flag = false;
                    break;
            }
        }
    }
}
