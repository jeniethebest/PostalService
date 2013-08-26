package edu.uic.cs.postalservice.model;

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

@Entity
@Table(name="CONTAINER_DETAILS",uniqueConstraints = {})
public class ContainerDetails {
    private Integer containerId;
    private String containerName;

    @Column(name="CONTAINER_ID",unique = true,nullable = false)
    public Integer getContainerId() {
        return containerId;
    }

    public void setContainerId(Integer containerId) {
        this.containerId = containerId;
    }

    @Column(name="CONTAINER_NAME",unique=true,nullable = false)
    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }
}
