package datingapp.demo.Data;

import datingapp.demo.domain.LoginSampleException;
import datingapp.demo.domain.User;

import java.sql.*;

public class UserMapper {


    public User login(String email, String password) throws LoginSampleException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT idUsers, IsAdmin FROM users "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Boolean isAdmin = rs.getBoolean("IsAdmin");
                int id = rs.getInt("idUsers");
                User user = new User(email, password, isAdmin);
                user.setId(id);
                return user;
            } else {
                throw new LoginSampleException("Could not validate user");
            }
        } catch (SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

}
