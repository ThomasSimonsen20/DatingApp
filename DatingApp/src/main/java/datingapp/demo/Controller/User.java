package datingapp.demo.Controller;

public class User {
    private String firstName;
    private int zipcode;

    public User(String firstName, int zipcode) {
        this.firstName = firstName;
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", zipcode=" + zipcode +
                '}';
    }
}
