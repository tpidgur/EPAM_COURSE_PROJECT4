package DAO;

import Entities.*;
import Util.ConnectionConfig;
import Util.DatesTransformation;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */
public class JDBCReservation implements GenericDao<Reservation> {
    static JdbcPersonDao personDao = new JdbcPersonDao();
    static JdbcApartmentDao apartmentDao = new JdbcApartmentDao();
    /**
     * creates initial apartment reservation table if not exists
     */
    @Override
    public void createTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionConfig.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS reservation(id_reservation int auto_increment, " +
                    "id_apartment int NOT NULL, id_client int NOT NULL, arrival DATE, departure DATE," +
                    "payed boolean, PRIMARY KEY (id_reservation), FOREIGN KEY (id_apartment) REFERENCES " +
                    "apartments (id), FOREIGN KEY (id_client) REFERENCES client (id))");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(statement);
            ConnectionConfig.closeConnection(connection);
        }
    }

    /**
     * insert new Reservation object into reservation table
     * utility method.  Use addReservation() method instead of it.
     *
     * @param reservation - object, fields of which are inserted into table
     */
    @Override
    public void insert(Reservation reservation) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("INSERT INTO reservation (id_apartment, id_client, arrival," +
                    " departure, payed) VALUES(?,?,?,?,?)");
            preparedStatement.setInt(1, reservation.getApartment().getId());
            preparedStatement.setInt(2, reservation.getClient().getId());
            preparedStatement.setDate(3, DatesTransformation.convertLocalDateToSQL(reservation.getArrival()));
            preparedStatement.setDate(4, DatesTransformation.convertLocalDateToSQL(reservation.getDeparture()));
            preparedStatement.setBoolean(5, reservation.isPayed());
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO reservation (id_apartment, id_client, arrival," +
                    " departure, payed) VALUES(?,?,?,?,?)");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
    }

    /**
     * Selects the apartments' ids available for reservation according to the following criteria:
     * apartment has the number of beds no less than the requirement in the application,
     * the category is taken from the application,
     * apartment is free for the dates mentioned in the application
     *
     * @param applic_id
     * @return list of Integer of available apartments' ids
     */
    public List<Integer> getFreeApartmentsId(int applic_id) {
        List<Integer> apartmentIds = new LinkedList<>();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("select t3.id from(\n" +
                    "select t1.id_reservation,t1.id as apart_id,t1.category, t1.id_client,t1.arrival,t1.departure from \n" +
                    "(select * from  apartments as apart  join reservation as res  on apart.id=res.id_apartment )t1 \n" +
                    " join application as applic on  t1.category=applic.category \n" +
                    " WHERE ((applic.arrival>=t1.arrival and applic.departure<=t1.departure) OR\n" +
                    "         (applic.arrival<=t1.arrival and applic.departure>=t1.arrival) OR\n" +
                    "         (applic.arrival<=t1.departure and applic.departure>=t1.departure)) AND  applic.id=?)t2  right join \n" +
                    "\t(select apart.id,apart.category from apartments as apart join application as applic \n" +
                    "on apart.category=applic.category\n" +
                    "WHERE  apart.beds>=(select applic2.persons from application as applic2 where applic2.id=?)) as t3 on\n" +
                    " t2.apart_id=t3.id where t2.apart_id is NULL;");
            preparedStatement.setInt(1, applic_id);
            preparedStatement.setInt(2, applic_id);
            ResultSet result = preparedStatement.executeQuery();
            System.out.println("IDs of matching apartments:");
            while (result.next()) {
                apartmentIds.add(result.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
        return apartmentIds;
    }

    /**
     * adds the reservation taking into account the availability of the mentioned apartment and based on the
     * requirements mentioned in the application
     *
     * @param applic_id   int application id
     * @param reservation reservation of the apartment
     * @return true if the reservation is made
     */
    public boolean addReservation(int applic_id, Reservation reservation) {
        int apartmentId = reservation.getApartment().getId();
        List<Integer> freeApartIds = getFreeApartmentsId(applic_id);
        if (freeApartIds.contains(apartmentId)) {
            insert(reservation);
            return true;
        }
        return false;
    }

    /**
     * Updates the Reservation instance parameters on id
     * Use addUpdate() method instead of it
     *
     * @param reservation - object to be updated
     * @param id          - id of the above mentioned object
     * @return - true if the table has been updated
     */
    @Override
    public boolean update(Reservation reservation, int id) {
        boolean res = false;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("UPDATE reservation SET id_apartment=?, id_client=?, arrival=?, " +
                    "departure=?, payed=?  WHERE id=?");
            preparedStatement.setInt(1, reservation.getApartment().getId());
            preparedStatement.setInt(2, reservation.getClient().getId());
            preparedStatement.setDate(3, DatesTransformation.convertLocalDateToSQL(reservation.getArrival()));
            preparedStatement.setDate(4, DatesTransformation.convertLocalDateToSQL(reservation.getDeparture()));
            preparedStatement.setBoolean(5, reservation.isPayed());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
            res = true;
            System.out.println("UPDATE reservation SET id_apartment=?, id_client=?, arrival=?, " +
                    "departure=?, payed=?  WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
        return res;
    }

    /**
     * Before update checks whether the corresponding Reservation meets the requirements, such as number of beds,
     * category and is available for specified dates
     *
     * @param applic_id   id of the client's application
     * @param reserv_id   id of the reservation in DB to be updated
     * @param reservation the Reservation object which will be added instead of the existing reservation
     * @return true if update is successful
     */
    public boolean addUpdate(int applic_id, int reserv_id, Reservation reservation) {
        int apartmentId = reservation.getApartment().getId();
        List<Integer> freeApartIds = getFreeApartmentsId(applic_id);
        if (freeApartIds.contains(apartmentId)) {
            update(reservation, reserv_id);
            return true;
        }
        return false;
    }
    /**
     * deletes the Reservation object on its id
     *
     * @param id - id of the reservation
     * @return true if the reservation instance has been deleted from database
     */
    @Override
    public boolean delete(int id) {
        boolean res = false;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("DELETE FROM reservation WHERE id=?");
            preparedStatement.setInt(1, id);
            res = true;
            preparedStatement.executeUpdate();
            System.out.println("DELETE FROM reservation WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
        return res;
    }
    /**
     * finds Reservation object on its id
     *
     * @param id - int id of the Reservation
     * @return the found Reservation  object
     */
    @Override
    public Reservation find(int id) {
        Reservation reservation = null;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        con = ConnectionConfig.getConnection();
        try {
            preparedStatement = con.prepareStatement("SELECT * FROM reservation WHERE id_reservation=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservation = new Reservation(apartmentDao.find(resultSet.getInt("id_apartment")),
                        personDao.find(resultSet.getInt("id_client")),
                        DatesTransformation.convertSQLToLocalDate(resultSet.getDate("arrival")),
                        DatesTransformation.convertSQLToLocalDate(resultSet.getDate("departure")),
                        resultSet.getBoolean("payed"));
                reservation.setId(resultSet.getInt("id_reservation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(preparedStatement);
            ConnectionConfig.closeConnection(con);
        }
        return reservation;
    }
    /**
     * @return the list of reservations from table reservation
     */
    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new LinkedList<>();
        Reservation reservation = null;
        Connection con = null;
        Statement statement = null;
        con = ConnectionConfig.getConnection();
        try {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM reservation");
            while (resultSet.next()) {
                reservation = new Reservation(apartmentDao.find(resultSet.getInt("id_apartment")),
                        personDao.find(resultSet.getInt("id_client")),
                        DatesTransformation.convertSQLToLocalDate(resultSet.getDate("arrival")),
                        DatesTransformation.convertSQLToLocalDate(resultSet.getDate("departure")),
                        resultSet.getBoolean("payed"));
                reservation.setId(resultSet.getInt("id_reservation"));
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionConfig.closeStatement(statement);
            ConnectionConfig.closeConnection(con);
        }
        return reservations;
    }
}
