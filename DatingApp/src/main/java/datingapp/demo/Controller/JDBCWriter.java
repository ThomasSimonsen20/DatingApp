package datingapp.demo.Controller;

import java.sql.*;

public class JDBCWriter {

    private Connection connection;

    public boolean setConnection() {
        //Input which database to connect to
        final String url = "jdbc:mysql://127.0.0.1:3306?serverTimezone=UTC";

        //default connection result is false
        boolean gotCon = false;

        try {
            //Attempt to establish connection, pass user details
            connection = DriverManager.getConnection(url, "MikkelVase", "1");
           // connection = DriverManager.getConnection(url, "Thomas", "1");
            gotCon = true;
            System.out.println("Connection established");
        } catch (SQLException throwables) {
            System.out.println("Connection failed = " + throwables.getMessage());
        }

        //return result of connection
        return gotCon;
    }
}
