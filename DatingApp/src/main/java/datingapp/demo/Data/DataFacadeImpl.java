package datingapp.demo.Data;

import datingapp.demo.domain.DataFacade;
import datingapp.demo.domain.LoginSampleException;
import datingapp.demo.domain.User;

import java.util.ArrayList;

public class DataFacadeImpl implements DataFacade {
   private UserMapper userMapper = new UserMapper();

    public User login(String email, String password) throws LoginSampleException {
        return userMapper.login(email, password);
    }

    @Override
    public ArrayList<User> getAllUserDataFromDB() {
        return userMapper.getAllUserDataFromDB();
    }

    @Override
    public User updateUser(User user) throws LoginSampleException {
        userMapper.updateUser(user);
        return user;
    }

    @Override
    public int deleteUser(int id) throws LoginSampleException {
        userMapper.deleteUser(id);
        return id;
    }


}
