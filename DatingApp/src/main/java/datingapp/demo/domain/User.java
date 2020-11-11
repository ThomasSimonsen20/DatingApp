package datingapp.demo.domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

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

    /* ----------------------------------------------------------------------------- */
    /* ------------------------------ Contructors ---------------------------------- */
    /* ----------------------------------------------------------------------------- */


    public User(int id, String firstName, String lastName, int telephoneNumber, String email, String password, boolean isAdmin, boolean isWoman, LocalDate birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isWoman = isWoman;
        this.birthday = birthday;
    }

    public User(String firstName, String lastName, int telephoneNumber, String email, String password, Boolean isAdmin, Boolean isWoman, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isWoman = isWoman;
        this.birthday = LocalDate.parse(birthday);

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