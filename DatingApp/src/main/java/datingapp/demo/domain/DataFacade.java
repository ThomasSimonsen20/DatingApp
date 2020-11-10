package datingapp.demo.domain;

import java.util.ArrayList;

public interface DataFacade {

    public User login(String email, String password) throws LoginSampleException;
    public ArrayList<User> getAllUserDataFromDB();
    public User updateUser(User user) throws LoginSampleException;

}