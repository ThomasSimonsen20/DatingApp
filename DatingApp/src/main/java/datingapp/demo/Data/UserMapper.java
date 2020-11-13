package datingapp.demo.Data;

import com.mysql.cj.protocol.Resultset;
import datingapp.demo.domain.LoginSampleException;
import datingapp.demo.domain.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserMapper {




    public User login(String email, String password) throws LoginSampleException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT idUsers, FirstName, LastName, TelephoneNumber, IsAdmin, isWoman, Birthday FROM users "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            ArrayList<Integer> favorites = new ArrayList<>();

            if (rs.next()) {
                int id = rs.getInt("idUsers");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                int telephoneNumber = rs.getInt("TelephoneNumber");
                Boolean isAdmin = rs.getBoolean("IsAdmin");
                Boolean isWoman = rs.getBoolean("isWoman");
                String birthday = rs.getString("Birthday");


                /*------ Laver select til at oprette favorites-listen------- */

                String SQLFavorites = "SELECT * FROM favorites WHERE idUsers = ?";
                PreparedStatement psFavorites = con.prepareStatement(SQLFavorites);
                psFavorites.setInt(1, id);
                ResultSet rsFavorites = psFavorites.executeQuery();

                /*------ Laver loop til at oprette favorites-listen------- */

                while (rsFavorites.next()) {
                    favorites.add(rsFavorites.getInt("idUsersFavorite"));
                }

                User user = new User(firstName, lastName, telephoneNumber, email, password, isAdmin, isWoman, birthday, favorites);
                user.setId(id);
                return user;
            } else {
                throw new LoginSampleException("Could not validate user");
            }
        } catch (SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public ArrayList<User> getAllUserDataFromDB() {
        ArrayList<User> userArrayList = new ArrayList<>();
        ArrayList<Integer> favorites = new ArrayList<>();
        int currentUserId;
        try {

            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM users";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {

                User user = new User();
                currentUserId = rs.getInt("idUsers");
                user.setId(currentUserId);
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setTelephoneNumber(rs.getInt("TelephoneNumber"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setAdmin(rs.getBoolean("IsAdmin"));
                user.setWoman(rs.getBoolean("isWoman"));
                user.setBirthday(rs.getString("Birthday"));

                /*------ Laver select til at oprette favorites-liste  ------- */

                String SQLFavorites = "SELECT * FROM favorites WHERE idUsers = ?";
                PreparedStatement psFavorites = con.prepareStatement(SQLFavorites);
                psFavorites.setInt(1, currentUserId);
                ResultSet rsFavorites = psFavorites.executeQuery();

                /*------ Laver loop til at oprette favorites-listen ------- */

                while (rsFavorites.next()) {
                    favorites.add(rsFavorites.getInt("idUsersFavorite"));
                }

                user.setFavorites(favorites);

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
            String SQL = "REPLACE INTO users (idUsers,FirstName,LastName, TelephoneNumber,Email, Password, IsAdmin, isWoman, Birthday) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setInt(4, user.getTelephoneNumber());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPassword());
            ps.setBoolean(7, user.isAdmin());
            ps.setBoolean(8, user.isWoman());
            ps.setString(9, user.getBirthday());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public void deleteUser(int id) throws LoginSampleException {
        try {
        Connection con = DBManager.getConnection();
        String SQL = "DELETE FROM users where idUsers like ?";
        PreparedStatement ps = con.prepareStatement(SQL);
        ps.setInt(1, id);
        ps.executeUpdate();
        } catch (SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public void addUserToFavorites(int idUser, int idFavorite) throws LoginSampleException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO favorites (idUsers, idUsersFavorite) VALUES (?, ?);";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, idUser);
            ps.setInt(2, idFavorite);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    public ArrayList<User> getFavorites(int id) {
        ArrayList<User> favoritesList = new ArrayList<>();
        try {

            Connection con = DBManager.getConnection();

            /*------ Laver select til at oprette favorites-liste  ------- */

            String SQLFavorites = "SELECT * FROM favorites WHERE idUsers = ?";
            PreparedStatement psFavorites = con.prepareStatement(SQLFavorites);
            psFavorites.setInt(1, id);
            ResultSet rsFavorites = psFavorites.executeQuery();

            while (rsFavorites.next()) {

                /*------ Laver loop til at oprette favorites-listen ------- */

                while (rsFavorites.next()) {
                    favoritesList.add(getAllUserDataFromDB().get(rsFavorites.getInt("idUsersFavorite") -1));
                }
            }

        } catch (SQLException e) {
            e.getMessage();
        }

        return favoritesList;
    }
}
