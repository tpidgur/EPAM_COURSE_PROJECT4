package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */
public class ConnectionConfig {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/epam4_2", "root", "Fastyfastyfas89");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    /**
     * closes the connection
     *
     * @param connection - the one to be closed
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * closes statement
     *
     * @param statement - the one to be closed
     */
    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
