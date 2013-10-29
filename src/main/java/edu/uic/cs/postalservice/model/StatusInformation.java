package edu.uic.cs.postalservice.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 9/22/13
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class StatusInformation {
    private Integer status_id;
    private String status_type;

    public StatusInformation()
    {

    }

    public StatusInformation(String status_type)
    {
        this.status_type = status_type;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public String getStatus_type() {
        return status_type;
    }

    public void setStatus_type(String status_type) {
        this.status_type = status_type;
    }

    public JsonElement toJson()
    {
        JsonObject jso = new JsonObject();
        jso.addProperty("status_id",status_id);
        jso.addProperty("status_type",status_type);
        return jso;
    }
}
