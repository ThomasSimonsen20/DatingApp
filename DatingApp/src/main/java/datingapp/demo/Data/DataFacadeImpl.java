package datingapp.demo.Data;

import datingapp.demo.domain.DataFacade;
import datingapp.demo.domain.SystemException;
import datingapp.demo.domain.User;

import java.util.ArrayList;

public class DataFacadeImpl implements DataFacade {
   private UserMapper userMapper = new UserMapper();

    public User login(String email, String password) throws SystemException {
        return userMapper.login(email, password);
    }

    @Override
    public ArrayList<User> getAllUserDataFromDB() {
        return userMapper.getAllUserDataFromDB();
    }

    @Override
    public User updateUser(User user) throws SystemException {
        userMapper.updateUser(user);
        return user;
    }

    @Override
    public int deleteUser(int id) throws SystemException {
        userMapper.deleteUser(id);
        return id;
    }

    @Override
    public int addUserToFavorites(int idUser, int idFavorite) throws SystemException {
        userMapper.addUserToFavorites(idUser, idFavorite);
        return idUser;
    }

    @Override
    public ArrayList<User> getFavorites(int id) {
        return userMapper.getFavorites(id);
    }
}
