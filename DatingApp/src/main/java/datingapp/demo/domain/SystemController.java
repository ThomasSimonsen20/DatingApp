package datingapp.demo.domain;

import java.util.ArrayList;

public class SystemController {

    // facade to datasource layer
    private DataFacade facade = null;

    public SystemController(DataFacade facade) {
        this.facade = facade;
    }

    public User login(String email, String password) throws SystemException {
        return facade.login(email, password);
    }

    public ArrayList<User> getAllUserDataFromDB() {
        return facade.getAllUserDataFromDB();
    }

    public User updateUser(User user) throws SystemException {
        facade.updateUser(user);
        return user;
    }

    public int deleteUser(int id) throws SystemException {
        facade.deleteUser(id);
        return id;
    }

    public int addUserToFavorites(int idUser, int idFavorite) throws SystemException {
        facade.addUserToFavorites(idUser, idFavorite);
        return idUser;
    }


    public ArrayList<User> getFavorites(int id) throws SystemException {
        return facade.getFavorites(id);
    }
}
