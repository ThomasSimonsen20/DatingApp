package datingapp.demo.domain;

import datingapp.demo.Data.DataFacadeImpl;
import datingapp.demo.Data.UserMapper;

import java.util.ArrayList;
import java.util.Random;

public class UserViewerSelector {

    Random random = new Random();
    private SystemController systemController = new SystemController(new DataFacadeImpl());

    public ArrayList<User> userViewSelector(boolean isWoman){
        ArrayList<Integer> selection = new ArrayList<>();
        ArrayList<User> userList = getUsersDividedBySex(isWoman);
        ArrayList<User> resultList = new ArrayList<>();
        int counter = 0;
        int rand;

        for (int i = 0; i < 4 ; i++) {
            rand = random.nextInt(userList.size()) ;
            if (!selection.contains(rand)){
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

    public ArrayList<User> getUsersDividedBySex(boolean iswoman){
        ArrayList<User> resultList = new ArrayList<>();

        for (User user: systemController.getAllUserDataFromDB()) {
            if (user.isWoman() != iswoman){
                resultList.add(user);
            }
        }
        return resultList;
    }




}
