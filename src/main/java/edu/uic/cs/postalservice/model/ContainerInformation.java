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
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */

public class ContainerInformation {
    private Integer containerId;
    private String containerName;

    public ContainerInformation()
    {

    }

    public ContainerInformation(String containerName)
    {
        this.containerName = containerName;
    }

    public Integer getContainerId() {
        return containerId;
    }

    public void setContainerId(Integer containerId) {
        this.containerId = containerId;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public JsonElement toJson()
    {
        JsonObject jso = new JsonObject();
        jso.addProperty("container_id",containerId);
        jso.addProperty("container_name",containerName);
        return jso;
    }
}
