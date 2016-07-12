package DAO;

import Entities.Client;
import Util.ConnectionConfig;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Defines methods for CRUD operations in client table
 *
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */
public class JdbcPersonDao implements GenericDao<Client> {

    /**
     * creates initial client table if not exists
     */
    @Override
    public void createTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionConfig.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS client (id int primary key unique auto_increment," +
                    "first_name varchar(55), last_name varchar(55), address varchar(300), telephone varchar (20), " +
                    "email varchar(50))");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(statement);
            ConnectionConfig.closeConnection(connection);
        }
    }


    /**
     * insert new Client object into client table
     *
     * @param client - object, fields of which are inserted into table
     */
    @Override
    public void insert(Client client) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("INSERT INTO client (first_name, last_name, address, telephone, email)" +
                    "VALUES(?,?,?,?,?)");
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setString(3, client.getAddress());
            preparedStatement.setString(4, client.getTelephone());
            preparedStatement.setString(5, client.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO client (first_name, last_name, address, telephone, email) VALUES(?,?,?,?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
    }

    /**
     * Updates the Client instance parameters on id
     *
     * @param client - object to be updated
     * @param id     - id of the above mentioned object
     * @return - true if the table has been updated
     */
    @Override
    public boolean update(Client client, int id) {
        boolean res = false;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("UPDATE client SET first_name=?,last_name=?, address=?, " +
                    "telephone=?, email=? WHERE id=?");
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setString(3, client.getAddress());
            preparedStatement.setString(4, client.getTelephone());
            preparedStatement.setString(5, client.getEmail());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
            res = true;
            System.out.println("UPDATE client SET first_name=?,last_name=?, address=?, " +
                    "telephone=?, email=? WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
        return res;
    }

    /**
     * deletes the Client object on its id
     *
     * @param id - id of the client
     * @return true if the Client instance has been deleted from database
     */
    @Override
    public boolean delete(int id) {
        boolean res = false;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("DELETE FROM client WHERE id=?");
            preparedStatement.setInt(1, id);
            res = true;
            preparedStatement.executeUpdate();
            System.out.println("DELETE FROM client WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
        return res;
    }

    /**
     * finds Client object on its id
     *
     * @param id - int id of the Client
     * @return the found Client object
     */
    @Override
    public Client find(int id) {
        Client client = new Client();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("SELECT * FROM client WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                client.setId(resultSet.getInt("id"));
                client.setFirstName(resultSet.getString("first_name"));
                client.setLastName(resultSet.getString("last_name"));
                client.setAddress(resultSet.getString("address"));
                client.setTelephone(resultSet.getString("telephone"));
                client.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
        return client;
    }

    /**
     * @return the list of Clients from table client
     */
    @Override
    public List<Client> findAll() {
        List<Client> clients = new LinkedList<>();
        Client client = null;
        Connection con = null;
        Statement statement = null;
        con = ConnectionConfig.getConnection();
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM client");
            while (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setFirstName(resultSet.getString("first_name"));
                client.setLastName(resultSet.getString("last_name"));
                client.setAddress(resultSet.getString("address"));
                client.setTelephone(resultSet.getString("telephone"));
                client.setEmail(resultSet.getString("email"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(statement);
            ConnectionConfig.closeConnection(con);
        }
        return clients;
    }
}
