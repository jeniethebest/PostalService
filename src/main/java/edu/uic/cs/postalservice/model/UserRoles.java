package edu.uic.cs.postalservice.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 8/24/13
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */

public class UserRoles {
    private int roleId;
    private String roleType;

    public UserRoles(){

    }

    public UserRoles(String roleType){
        this.roleType = roleType;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public JsonElement toJson(){
        JsonObject jso = new JsonObject();
        jso.addProperty("role_type",roleType);
        return jso;
    }
}
