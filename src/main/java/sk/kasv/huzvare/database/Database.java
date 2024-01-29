package sk.kasv.huzvare.database;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/project_login";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Marek2023!";
    private static final String SELECT_USER = "SELECT * FROM user " +
            " WHERE name = ? AND passwd = ?";
    private static final String INSERT_LOG = " INSERT INTO log ( idu, status ) " +
            " VALUES ( (SELECT id FROM user WHERE name LIKE ? ), ?) ";

    public boolean checkCredential(String name, String password){
        Connection conn;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(SELECT_USER);
            ps.setString(1, name);
            String passwdMD5= DigestUtils.md5Hex(password);
            ps.setString(2, passwdMD5);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                conn.close();
                insertLog(name,'S');
                System.out.println("User "+name +" is logged successfully");
                return true;
            }
            else{
                conn.close();
                System.out.println("User "+name +" isn't logged");
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void insertLog(String name, char status){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(INSERT_LOG);
            ps.setString(1, name);
            ps.setString(2, String.valueOf(status));
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
