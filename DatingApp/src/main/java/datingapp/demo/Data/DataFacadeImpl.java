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


    public User createUser(User user) throws LoginSampleException {
      //  userMapper.createUser(user);
        return user;
    }

    @Override
    public ArrayList<User> getAllUserDataFromDB() {
        return userMapper.getAllUserDataFromDB();
    }


}
