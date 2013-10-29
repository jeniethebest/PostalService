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
 * Time: 4:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class PackageType {

    @SerializedName("package_type_id")
    private Integer package_type_id;

    @SerializedName("package_name")
    private String package_name;

    public PackageType()
    {

    }

    public PackageType(String package_name)
    {
        this.package_name = package_name;
    }

    public Integer getPackage_type_id() {
        return package_type_id;
    }

    public void setPackage_type_id(Integer package_type_id) {
        this.package_type_id = package_type_id;
    }


    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public JsonElement toJson(){
        JsonObject jso = new JsonObject();
        jso.addProperty("package_type_id",package_type_id);
        jso.addProperty("package_name",package_name);
        return jso;
    }
}
