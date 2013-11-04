package edu.uic.cs.postalservice.model;

import com.google.gson.annotations.SerializedName;

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

public class PackageInformation {

    @SerializedName("package_id")
    private Integer packageId;

    @SerializedName("package_type")
    private PackageType packageType;

    @SerializedName("package_weight")
    private Double packageWeight;

    @SerializedName("package_source")
    private AddressInformation packageSource;

    @SerializedName("package_destination")
    private AddressInformation packageDestination;

    public PackageInformation()
    {

    }
    public PackageInformation(PackageType packageType, Double packageWeight, AddressInformation packageSource, AddressInformation packageDestination)
    {
        this.packageType = packageType;
        this.packageWeight = packageWeight;
        this.packageSource = packageSource;
        this.packageDestination = packageDestination;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public PackageType getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageType packageType) {
        this.packageType = packageType;
    }

    public Double getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(Double packageWeight) {
        this.packageWeight = packageWeight;
    }

    public AddressInformation getPackageSource() {
        return packageSource;
    }

    public void setPackageSource(AddressInformation packageSource) {
        this.packageSource = packageSource;
    }

    public AddressInformation getPackageDestination() {
        return packageDestination;
    }

    public void setPackageDestination(AddressInformation packageDestination) {
        this.packageDestination = packageDestination;
    }
}
