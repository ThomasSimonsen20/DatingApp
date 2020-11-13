package datingapp.demo.domain;

import java.util.ArrayList;

public interface DataFacade {

    public User login(String email, String password) throws SystemException;
    public ArrayList<User> getAllUserDataFromDB();
    public User updateUser(User user) throws SystemException;
    public int deleteUser(int id) throws SystemException;
    public int addUserToFavorites(int idUser, int idFavorite) throws SystemException;
    public ArrayList<User> getFavorites(int id) throws SystemException;

}