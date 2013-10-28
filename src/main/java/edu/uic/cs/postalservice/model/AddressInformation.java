package edu.uic.cs.postalservice.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 9/22/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddressInformation {
    @SerializedName("address_id")
    private Integer address_id;

    @SerializedName("street_name")
    private String street_name;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    @SerializedName("zipcode")
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

    public JsonElement toJson(){
        JsonObject jso = new JsonObject();
        jso.addProperty("address_id",address_id);
        jso.addProperty("street_name",street_name);
        jso.addProperty("city",city);
        jso.addProperty("state",state);
        jso.addProperty("zipcode",zipcode);
        return jso;
    }

//    public String changeToString()
//    {
//         String addressInformationString =null;
//        addressInformationString ="{ address_id:"+address_id+", street_name:"+street_name+", city:"+city+", zipcode:"+zipcode;
//        return addressInformationString;
//    }
}
