package edu.uic.cs.postalservice.model;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 9/22/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddressInformation {
    private Integer address_id;
    private String street_name;
    private String city;
    private String state;
    private Integer zipcode;

    public AddressInformation(){};

    public AddressInformation(String street_name, String city, String state, Integer zipcode) {
        this.street_name = street_name;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
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
}
