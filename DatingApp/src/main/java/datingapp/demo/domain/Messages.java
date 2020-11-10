package datingapp.demo.domain;

import java.util.ArrayList;

public class Messages {

    ArrayList<String> messages = new ArrayList<>();

    public void addMessageToList(String message){
        messages.add(message);
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public boolean isEmpty(){
        boolean result = false;
        if (messages.isEmpty()){
            result = true;
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (String s: messages) {
            result += s;
            result += "\n\n";
        }
        return result;
    }
}
