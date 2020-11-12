package datingapp.demo.domain;

import java.util.ArrayList;

public interface DataFacade {

    public User login(String email, String password) throws LoginSampleException;
    public ArrayList<User> getAllUserDataFromDB();
    public User updateUser(User user) throws LoginSampleException;
    public int deleteUser(int id) throws LoginSampleException;
    public int addUserToFavorites(int idUser, int idFavorite) throws LoginSampleException;

}