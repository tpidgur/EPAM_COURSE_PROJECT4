package DAO;

import Entities.Application;
import Entities.Category;
import Entities.Client;
import Util.ConnectionConfig;
import Util.DatesTransformation;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */
public class JdbcBookingApplic implements GenericDao<Application> {
   static JdbcPersonDao personDao = new JdbcPersonDao();

    /**
     * creates initial application table if not exists
     */
    @Override
    public void createTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionConfig.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS application (id int auto_increment," +
                    "id_client int NOT NULL, persons int, children int, " +
                    "category enum('DELUXE', 'SEMIDELUXE', 'ECONOM'),  arrival DATE, departure DATE," +
                    "desiderates varchar(250), PRIMARY KEY (id), FOREIGN KEY (id_client) REFERENCES client (id))");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(statement);
            ConnectionConfig.closeConnection(connection);
        }
    }

    /**
     * insert new Application object into application table
     *
     * @param application - object, fields of which are inserted into table
     */
    @Override
    public void insert(Application application) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("INSERT INTO application (id_client, persons, children," +
                    " category, arrival, departure,  desiderates) VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, application.getClient().getId());
            preparedStatement.setInt(2, application.getPersons());
            preparedStatement.setInt(3, application.getChildren());
            preparedStatement.setString(4, application.getCategory().toString());
            preparedStatement.setDate(5, DatesTransformation.convertLocalDateToSQL(application.getArrival()));
            preparedStatement.setDate(6, DatesTransformation.convertLocalDateToSQL(application.getDeparture()));
            preparedStatement.setString(7, application.getDesiderates());
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO application (id_client, persons, children," +
                    " category, arrival, departure,  desiderates) VALUES(?,?,?,?,?,?,?)");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
    }

    /**
     * Updates the Application instance parameters on id
     *
     * @param application - object to be updated
     * @param id          - id of the above mentioned object
     * @return - true if the table has been updated
     */
    @Override
    public boolean update(Application application, int id) {
        boolean res = false;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("UPDATE application SET id_client=?, persons=?, children=?, " +
                    "category=?, arrival=?, departure=?, desiderates=?  WHERE id=?");
            preparedStatement.setInt(1, application.getClient().getId());
            preparedStatement.setInt(2, application.getPersons());
            preparedStatement.setInt(3, application.getChildren());
            preparedStatement.setString(4, application.getCategory().toString());
            preparedStatement.setDate(5, DatesTransformation.convertLocalDateToSQL(application.getArrival()));
            preparedStatement.setDate(6, DatesTransformation.convertLocalDateToSQL(application.getDeparture()));
            preparedStatement.setString(7, application.getDesiderates());
            preparedStatement.setInt(8, id);
            preparedStatement.executeUpdate();
            res = true;
            System.out.println("UPDATE application SET id_client=?, persons=?, children=?, " +
                    "category=?, arrival=?, departure=?, desiderates=?  WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
        return res;
    }

    /**
     * deletes the Application object on its id
     *
     * @param id - id of the application
     * @return true if the application instance has been deleted from database
     */
    @Override
    public boolean delete(int id) {
        boolean res = false;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("DELETE FROM application WHERE id=?");
            preparedStatement.setInt(1, id);
            res = true;
            preparedStatement.executeUpdate();
            System.out.println("DELETE FROM application WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
        return res;
    }

    /**
     * finds Application object on its id
     *
     * @param id - int id of the Application
     * @return the found Application object
     */
    @Override
    public Application find(int id) {
        Application application = null;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("SELECT * FROM application WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                application = new Application(personDao.find(resultSet.getInt("id_client")),
                        resultSet.getString("desiderates"), resultSet.getInt("persons"), resultSet.getInt("children"),
                        Category.valueOf(resultSet.getString("category")),
                        DatesTransformation.convertSQLToLocalDate(resultSet.getDate("arrival")),
                        DatesTransformation.convertSQLToLocalDate(resultSet.getDate("departure")));
                application.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
        return application;
    }

    /**
     * @return the list of applications from table application
     */
    @Override
    public List<Application> findAll() {
        List<Application> applications = new LinkedList<>();
        Application application = null;
        Connection con = null;
        Statement statement = null;
        con = ConnectionConfig.getConnection();
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM application");
            while (resultSet.next()) {
                application = new Application(personDao.find(resultSet.getInt("id_client")),
                        resultSet.getString("desiderates"), resultSet.getInt("persons"), resultSet.getInt("children"),
                        Category.valueOf(resultSet.getString("category")),
                        DatesTransformation.convertSQLToLocalDate(resultSet.getDate("arrival")),
                        DatesTransformation.convertSQLToLocalDate(resultSet.getDate("departure")));
                application.setId(resultSet.getInt("id"));
                applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(statement);
            ConnectionConfig.closeConnection(con);
        }
        return applications;
    }


}
