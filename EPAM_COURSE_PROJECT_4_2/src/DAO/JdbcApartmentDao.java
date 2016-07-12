package DAO;

import Entities.Apartment;
import Entities.Category;
import Entities.Client;
import Util.ConnectionConfig;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */
public class JdbcApartmentDao implements GenericDao<Apartment> {
    /**
     * creates initial apartments table if not exists
     */
    @Override
    public void createTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionConfig.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS apartments (id int primary key unique auto_increment," +
                    "category enum('DELUXE', 'SEMIDELUXE', 'ECONOM') NOT NULL DEFAULT 'DELUXE', price double NOT NULL, "
                    + "hasToilet boolean default true, beds int NOT NULL, rooms int NOT NULL, " +
                    "windowsToStreet boolean default true, hasConditioner boolean default true)");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(statement);
            ConnectionConfig.closeConnection(connection);
        }
    }

    /**
     * inserts new apartment into Apartments table
     *
     * @param apartment object to be inserted into the table
     */
    @Override
    public void insert(Apartment apartment) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("INSERT INTO apartments (category, price, hasToilet, beds, rooms, " +
                    "windowsToStreet,hasConditioner) VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setString(1, apartment.getCategory().toString());
            preparedStatement.setDouble(2, apartment.getPrice());
            preparedStatement.setBoolean(3, apartment.includesToilet());
            preparedStatement.setInt(4, apartment.getBeds());
            preparedStatement.setInt(5, apartment.getNumberOfRooms());
            preparedStatement.setBoolean(6, apartment.windowsToStreet());
            preparedStatement.setBoolean(7, apartment.hasConditioner());
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO apartments (category, price, hasToilet, beds, rooms, " +
                    "windowsToStreet,hasConditioner) VALUES(?,?,?,?,?,?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
    }

    /**
     * Updates the Apartments instance parameters on id
     *
     * @param apartment - object to be updated
     * @param id        - id of the above mentioned object
     * @return - true if the table has been updated
     */
    @Override
    public boolean update(Apartment apartment, int id) {
        boolean res = false;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("UPDATE apartments SET category=?, price=?, hasToilet=?, " +
                    "beds=?, rooms=?, windowsToStreet=?, hasConditioner=?  WHERE id=?");
            preparedStatement.setString(1, apartment.getCategory().toString());
            preparedStatement.setDouble(2, apartment.getPrice());
            preparedStatement.setBoolean(3, apartment.includesToilet());
            preparedStatement.setInt(4, apartment.getBeds());
            preparedStatement.setInt(5, apartment.getNumberOfRooms());
            preparedStatement.setBoolean(6, apartment.windowsToStreet());
            preparedStatement.setBoolean(7, apartment.hasConditioner());
            preparedStatement.setInt(8, id);
            preparedStatement.executeUpdate();
            res = true;
            System.out.println("UPDATE apartments SET category=?, price=?, hasToilet=?, " +
                    "beds=?, rooms=?, windowsToStreet=?, hasConditioner=?  WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
        return res;
    }

    /**
     * deletes the Apartment object on its id
     *
     * @param id - id of the Apartment
     * @return true if the Apartment instance has been deleted from database
     */
    @Override
    public boolean delete(int id) {
        boolean res = false;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("DELETE FROM apartments WHERE id=?");
            preparedStatement.setInt(1, id);
            res = true;
            preparedStatement.executeUpdate();
            System.out.println("DELETE FROM apartments WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
        return res;
    }

    /**
     * finds Apartment object on its id
     *
     * @param id - int id of the Apartment
     * @return the found Apartment object
     */
    @Override
    public Apartment find(int id) {
        Apartment apartment = new Apartment();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("SELECT * FROM apartments WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                apartment = new Apartment(Category.valueOf(resultSet.getString("category")), resultSet.getDouble("price"),
                        resultSet.getBoolean("hasToilet"), resultSet.getInt("beds"), resultSet.getInt("rooms"),
                        resultSet.getBoolean("windowsToStreet"), resultSet.getBoolean("hasConditioner"));
                apartment.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
        return apartment;
    }

    /**
     *
     * @return the list of Apartments from table apartments
     */
    @Override
    public List<Apartment> findAll() {
        List<Apartment> apartments = new LinkedList<>();
        Apartment apartment = null;
        Connection con = null;
        Statement statement = null;
        con = ConnectionConfig.getConnection();
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM apartments");
            while (resultSet.next()) {
                apartment = new Apartment(Category.valueOf(resultSet.getString("category")), resultSet.getDouble("price"),
                        resultSet.getBoolean("hasToilet"), resultSet.getInt("beds"), resultSet.getInt("rooms"),
                        resultSet.getBoolean("windowsToStreet"), resultSet.getBoolean("hasConditioner"));
                apartment.setId(resultSet.getInt("id"));
                apartments.add(apartment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(statement);
            ConnectionConfig.closeConnection(con);
        }
        return apartments;
    }
}
