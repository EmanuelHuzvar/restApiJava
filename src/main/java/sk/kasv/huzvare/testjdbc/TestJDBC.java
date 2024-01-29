package sk.kasv.huzvare.testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/project_login";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Marek2023!";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(URL,USERNAME, PASSWORD);
            if(conn!=null){
                System.out.println("Connection successfuly ! ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
