package datingapp.demo.Controller;

import java.sql.*;

public class JDBCWriter {

    private Connection connection;

    public boolean setConnection() {
        //Input which database to connect to
        final String url = "jdbc:mysql://localhost:3306/DatingAppData?serverTimeZone=UTC";

        //default connection result is false
        boolean gotCon = false;

        try {
            //Attempt to establish connection, pass user details
            connection = DriverManager.getConnection(url, "test", "test");

            gotCon = true;
            System.out.println("Connection established");
        } catch (SQLException throwables) {
            System.out.println("Connection failed = " + throwables.getMessage());
        }

        //return result of connection
        return gotCon;
    }
}
