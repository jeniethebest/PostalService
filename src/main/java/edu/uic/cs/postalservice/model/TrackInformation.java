package edu.uic.cs.postalservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 8/24/13
 * Time: 7:15 PM
 * To change this template use File | Settings | File Templates.
 */

public class TrackInformation {
    private Integer trackId;
    private Integer packageId;
    private Integer containerId;
    private Integer statusId;

    public TrackInformation(int packageId,int containerId,int statusId){
        this.packageId = packageId;
        this.containerId = containerId;
        this.statusId = statusId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public Integer getContainerId() {
        return containerId;
    }

    public void setContainerId(Integer containerId) {
        this.containerId = containerId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}
