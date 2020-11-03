package datingapp.demo.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @Column(name = "idUsers")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUsers;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "SurName")
    private String surName;
    @Column(name = "TelePhoneNumber")
    private int telePhoneNumber;
    @Column(name = "ZipCode")
    private int zipCode;
    @Column(name = "City")
    private String city;
    @Column(name = "Street")
    private String street;
    @Column(name = "HouseNumber")
    private String houseNumber;
    @Column(name = "HouseFloor")
    private String houseFloor;

    public long getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(long idUsers) {
        this.idUsers = idUsers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getTelePhoneNumber() {
        return telePhoneNumber;
    }

    public void setTelePhoneNumber(int telePhoneNumber) {
        this.telePhoneNumber = telePhoneNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseFloor() {
        return houseFloor;
    }

    public void setHouseFloor(String houseFloor) {
        this.houseFloor = houseFloor;
    }
}
