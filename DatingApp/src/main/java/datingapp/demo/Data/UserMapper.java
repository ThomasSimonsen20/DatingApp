package datingapp.demo.Data;

import datingapp.demo.domain.LoginSampleException;
import datingapp.demo.domain.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserMapper {

    public User login(String email, String password) throws LoginSampleException {
        try {
            // Opretter forbindelse til vores database via vores DB Manager klasse
            Connection con = DBManager.getConnection();
            String SQL = "SELECT idUsers, FirstName, LastName, TelephoneNumber, IsAdmin, isWoman FROM users "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            // linje 26
            // Kigger på om der er en email og password der passer på det brugeren har indtastet.
            ps.setString(1, email);
            ps.setString(2, password);

            // Laver et resultset med email og password.
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("idUsers");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                int telephoneNumber = rs.getInt("TelephoneNumber");
                Boolean isAdmin = rs.getBoolean("IsAdmin");
                Boolean isWoman = rs.getBoolean("isWoman");

                User user = new User(firstName, lastName, telephoneNumber, email, password, isAdmin, isWoman);
                user.setId(id);
                return user;
            } else {
                throw new LoginSampleException("Could not validate user");
            }
        } catch (SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    // Opretter brugerne ved at sætte deres variabler med data fra MySQL
    // Gemmer alle brugere fra vores SQL database i en Arraylist

    public ArrayList<User> getAllUserDataFromDB() {
        ArrayList<User> userArrayList = new ArrayList<>();
        try {

            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM users";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                User user = new User();
                user.setId(rs.getInt("idUsers"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setTelephoneNumber(rs.getInt("TelephoneNumber"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setAdmin(rs.getBoolean("IsAdmin"));
                user.setWoman(rs.getBoolean("isWoman"));
                user.setBirthday(rs.getString("Birthday"));

                userArrayList.add(user);

            }

        } catch (SQLException e) {
            e.getMessage();
        }

        return userArrayList;
    }

    public void updateUser(User user) throws LoginSampleException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "REPLACE INTO users (idUsers,FirstName,LastName, TelephoneNumber,Email, Password, IsAdmin, isWoman) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setInt(4, user.getTelephoneNumber());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPassword());
            ps.setBoolean(7, user.isAdmin());
            ps.setBoolean(8, user.isWoman());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }





}
