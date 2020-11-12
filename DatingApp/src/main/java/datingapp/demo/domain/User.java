package datingapp.demo.domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private int telephoneNumber;
    private String email;
    private String password;
    private boolean isAdmin;
    private boolean isWoman;
    private LocalDate birthday;
    private ArrayList<Integer> favorites;

    /* ----------------------------------------------------------------------------- */
    /* ------------------------------ Contructors ---------------------------------- */
    /* ----------------------------------------------------------------------------- */


    public User(String firstName, String lastName, int telephoneNumber, String email, String password, boolean isAdmin, boolean isWoman, String birthday, ArrayList<Integer> favorites) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isWoman = isWoman;
        this.birthday = LocalDate.parse(birthday);
        this.favorites = favorites;

    }

    public User() {

    }

    /* ----------------------------------------------------------------------------- */
    /* ------------------------------ Udregnere ------------------------------------ */
    /* ----------------------------------------------------------------------------- */


    public String calculateAge(){
        LocalDate todaysDate = LocalDate.now();
        return String.valueOf(Period.between(birthday, todaysDate).getYears());
    }

    /* ----------------------------------------------------------------------------- */
    /* ------------------------------ Getters -------------------------------------- */
    /* ----------------------------------------------------------------------------- */

    public String getBirthday() {
        return birthday.toString();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isWoman() {
        return isWoman;
    }

    public ArrayList<Integer> getFavorites() {
        return favorites;
    }

    /* ----------------------------------------------------------------------------- */
    /* ------------------------------ Setters -------------------------------------- */
    /* ----------------------------------------------------------------------------- */

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setWoman(boolean woman) {
        isWoman = woman;
    }

    public void setBirthday(String birthday) {
        this.birthday = LocalDate.parse(birthday);
    }

    public void setFavorites(ArrayList<Integer> favorites) {
        this.favorites = favorites;
    }

    /* ----------------------------------------------------------------------------- */
    /* ------------------------------ ToString ------------------------------------- */
    /* ----------------------------------------------------------------------------- */


    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName +
                " Number: " + telephoneNumber +
                " email: " + email +
                " birthday: " + birthday;
    }
}