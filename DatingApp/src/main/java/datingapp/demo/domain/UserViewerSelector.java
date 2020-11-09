package datingapp.demo.domain;

import datingapp.demo.Data.UserMapper;

import java.util.ArrayList;
import java.util.Random;

public class UserViewerSelector {

    Random random = new Random();
    private UserMapper userMapper = new UserMapper();


    public ArrayList<User> userViewSelector(boolean isWoman){
        ArrayList<Integer> selection = new ArrayList<>();
        ArrayList<User> userList;
        ArrayList<User> resultList = new ArrayList<>();
        int counter = 0;
        int rand;
        if (isWoman) {
            userList = getArrayListOfMenInDB();
        }
        else {
            userList = getArrayListOfWomenInDB();
        }

        for (int i = 0; i < 4 ; i++) {
            rand = random.nextInt(userList.size()) ;
            if (!selection.contains(rand) && userList.get(rand).isWoman() != isWoman){
                selection.add(rand);
                resultList.add(userList.get(selection.get(counter)));
                counter++;
            }
            else {
                i--;
            }
        }
        return resultList;
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
