package DAO;

import java.sql.*;

/**
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */


public class Main {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/epam4", "root", "Fastyfastyfas89");
        Statement query = cn.createStatement();
        ResultSet rs = query.executeQuery("SELECT id from apartment");

        while (rs.next()) {
            System.out.println(rs.getInt("id"));
        }
        query.close();


//        System.out.println("==============prepared===================");
//        PreparedStatement ps = cn.prepareStatement("SELECT * FROM bouqet WHERE id = ?");
//        ps.setInt(1, 3);
//        rs = ps.executeQuery();
//        while (rs.next()) {
//            System.out.println(rs.getInt("id") + rs.getString("name") + rs.getInt("price"));
//        }
//        ps.close();
//
//        cn.setAutoCommit(false);
//        try {
//            query = cn.createStatement();
//            query.execute("INSERT INTO bouqet (name , price) "
//                    + " VALUES('test' , 1999)", Statement.RETURN_GENERATED_KEYS);
//            ResultSet reId = query.getGeneratedKeys();
//            if (reId.next()) {
//                System.out.println(reId.getInt(1));
//            }
//            query.close();
//
//            query = cn.createStatement();
//            query.execute("INSERT INTO bouqet (id ,name , price) "
//                    + " VALUES(6 ,'test' , 1999)", Statement.RETURN_GENERATED_KEYS);
//            reId = query.getGeneratedKeys();
//            if (reId.next()) {
//                System.out.println(reId.getInt(1));
//            }
//            query.close();
//        } catch (SQLException ex) {
//            System.out.println(ex.getErrorCode());
//            System.out.println(ex);
//            cn.rollback();
//        }


        cn.close();

    }
}


