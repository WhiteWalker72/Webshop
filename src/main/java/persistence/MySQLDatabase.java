package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabase extends SQLDatabase {

    MySQLDatabase(String address, int port, String databaseName, String username, String password) {
        super(address, port, databaseName, username, password);
    }

    @Override
    Connection getConnection(String address, int port, String databaseName, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                DriverManager.setLoginTimeout(6);
                return DriverManager.getConnection("jdbc:mysql://" + address + ":"
                        + port + "/" + databaseName, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
