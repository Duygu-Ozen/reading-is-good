package com.readingIsGood.readingIsGood.models.entity;


import com.readingIsGood.readingIsGood.models.dto.CustomerDTO;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String emailAdress;
    private String postalCode;
    private String street;
    private String buildingNo;
    private String flatNo;
    private String city;
    private String nip;
    private String phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CustomerDTO toDTO(){

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setBuildingNo(buildingNo);
        customerDTO.setCity(city);
        customerDTO.setEmailAdress(emailAdress);
        customerDTO.setFirstName(firstName);
        customerDTO.setId(id);
        customerDTO.setFlatNo(flatNo);
        customerDTO.setLastName(lastName);
        customerDTO.setNip(nip);
        customerDTO.setPhoneNumber(phoneNumber);
        customerDTO.setPostalCode(postalCode);
        customerDTO.setStreet(street);

        return customerDTO;

    }


}
