package edu.uic.cs.postalservice.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 8/24/13
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="PACKAGE_INFORMATION" ,uniqueConstraints = {
        @UniqueConstraint(columnNames = "PACKAGE_ID")
})

public class PackageInformation {

    private Integer packageId;
    private Integer packageType;
    private Double packageWeight;
    private String packageSource;
    private String packageDestination;
    private Date startDateTime;
    private Date endDateTime;

    public PackageInformation()
    {

    }
    public PackageInformation(Integer packageType, Double packageWeight, String packageSource, String packageDestination)
    {
        this.packageType = packageType;
        this.packageWeight = packageWeight;
        this.packageSource = packageSource;
        this.packageDestination = packageDestination;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="PACKAGE_ID", unique = true, nullable = false)
    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @Column(name="PACKAGE_TYPE",nullable = false)
    public Integer getPackageType() {
        return packageType;
    }

    public void setPackageType(Integer packageType) {
        this.packageType = packageType;
    }

    @Column(name="PACKAGE_WEIGHT", nullable = false)
    public Double getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(Double packageWeight) {
        this.packageWeight = packageWeight;
    }


    @Column(name="PACKAGE_SOURCE", nullable= false)
    public String getPackageSource() {
        return packageSource;
    }

    public void setPackageSource(String packageSource) {
        this.packageSource = packageSource;
    }

    @Column(name="PACKAGE_DESTINATION", nullable= false)
    public String getPackageDestination() {
        return packageDestination;
    }

    public void setPackageDestination(String packageDestination) {
        this.packageDestination = packageDestination;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="PACKAGE_START_DATE_TIME")
    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    @Column(name="PACKAGE_END_DATE_TIME")
    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }
}
