package datingapp.demo.domain;

import datingapp.demo.Data.UserMapper;

import java.util.ArrayList;
import java.util.Random;

public class UserViewerSelector {

    Random random = new Random();
    private UserMapper userMapper = new UserMapper();


    public ArrayList<Integer> userViewSelector(boolean isWoman){
        ArrayList<Integer> selection = new ArrayList<>();
        ArrayList<User> userList;
        int rand;
        if (isWoman ) {
            userList = getArrayListOfWomenInDB();
        }
        else {
            userList = getArrayListOfMenInDB();
        }

        for (int i = 0; i < 4 ; i++) {
            rand = random.nextInt(userList.size()) + 1;
            if (selection.contains(rand) || userList.get(rand).isWoman() == isWoman){
                i--;
            }
            else {
                selection.add(rand);
            }
        }
        return selection;
    }



    public ArrayList<User> getArrayListOfMenInDB(){
        ArrayList<User> listOfMen = new ArrayList<>();
        for (User user: userMapper.getAllUserDataFromDB()) {
            if (!user.isWoman()){
                listOfMen.add(user);
            }
        }
        return listOfMen;
    }

    public ArrayList<User> getArrayListOfWomenInDB(){
        ArrayList<User> listOfWomen = new ArrayList<>();
        for (User user: userMapper.getAllUserDataFromDB()) {
            if (user.isWoman()){
                listOfWomen.add(user);
            }
        }
        return listOfWomen;
    }
}
