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


}
