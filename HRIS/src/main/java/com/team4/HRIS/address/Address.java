package com.team4.HRIS.address;

// Author - Joseph Huntley
// Team 4
public class Address {
    private String street, city, state;
    private boolean primaryResidence;

    public Address() {
    }

    public Address(String street, String city, String state, boolean primaryResidence) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.primaryResidence = primaryResidence;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isPrimaryResidence() {
        return primaryResidence;
    }

    public void setPrimaryResidence(boolean primaryResidence) {
        this.primaryResidence = primaryResidence;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", primaryResidence=" + primaryResidence +
                '}';
    }
}
