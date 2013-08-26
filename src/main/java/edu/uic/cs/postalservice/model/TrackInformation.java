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
@Entity
@Table(name="TRACK_INFORMATION",uniqueConstraints = {})
public class TrackInformation {
    private Integer trackId;
    private Integer containerId;
    private Integer statusId;

    @Column(name ="TRACK_ID",unique = true,nullable = false)
    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    @Column(name ="CONTAINER_ID",unique = true,nullable = false)
    public Integer getContainerId() {
        return containerId;
    }

    public void setContainerId(Integer containerId) {
        this.containerId = containerId;
    }

    @Column(name ="STATUS_ID",unique = true, nullable = false)
    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}
