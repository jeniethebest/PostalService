package edu.uic.cs.postalservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 8/24/13
 * Time: 7:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name ="STATION_INFORMATION",uniqueConstraints = {})
public class StationInformation {
    private Integer stationId;
    private String stationName;
    private String stationLocationCity;

    @Column(name="STATION_ID", unique = true, nullable = false)
    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    @Column(name="STATION_NAME", unique = true, nullable = false)
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Column(name="STATION_LOCATION_CITY", unique = true, nullable = false)
    public String getStationLocationCity() {
        return stationLocationCity;
    }

    public void setStationLocationCity(String stationLocationCity) {
        this.stationLocationCity = stationLocationCity;
    }
}
