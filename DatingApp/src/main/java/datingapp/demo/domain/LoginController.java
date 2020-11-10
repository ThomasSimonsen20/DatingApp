package datingapp.demo.domain;

import datingapp.demo.Data.UserMapper;

import java.util.ArrayList;

public class LoginController {

    // facade to datasource layer
    private DataFacade facade = null;

    public LoginController(DataFacade facade) {
        this.facade = facade;
    }

    public User login(String email, String password) throws LoginSampleException {
        return facade.login(email, password);
    }

    public ArrayList<User> getAllUserDataFromDB() {
        return facade.getAllUserDataFromDB();
    }

    public User updateUser(User user) throws LoginSampleException {
        facade.updateUser(user);
        return user;
    }

    public int deleteUser(int id) throws LoginSampleException {
        facade.deleteUser(id);
        return id;
    }
}
