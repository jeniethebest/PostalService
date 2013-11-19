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

    @SerializedName("package_container")
    private String packageContainer;

    @SerializedName("package_status")
    private String packageStatus;

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

    public PackageInformation(PackageType packageType, Double packageWeight, AddressInformation packageSource, AddressInformation packageDestination, String packageContainer, String packageStatus) {
        this.packageType = packageType;
        this.packageWeight = packageWeight;
        this.packageSource = packageSource;
        this.packageDestination = packageDestination;
        this.packageContainer = packageContainer;
        this.packageStatus = packageStatus;
    }

    public String getPackageContainer() {
        return packageContainer;
    }

    public void setPackageContainer(String packageContainer) {
        this.packageContainer = packageContainer;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackageInformation)) return false;

        PackageInformation that = (PackageInformation) o;

        if (packageDestination != null ? !packageDestination.equals(that.packageDestination) : that.packageDestination != null)
            return false;
        if (packageId != null ? !packageId.equals(that.packageId) : that.packageId != null) return false;
        if (packageSource != null ? !packageSource.equals(that.packageSource) : that.packageSource != null)
            return false;
        if (packageType != null ? !packageType.equals(that.packageType) : that.packageType != null) return false;
        if (packageWeight != null ? !packageWeight.equals(that.packageWeight) : that.packageWeight != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = packageId != null ? packageId.hashCode() : 0;
        result = 31 * result + (packageType != null ? packageType.hashCode() : 0);
        result = 31 * result + (packageWeight != null ? packageWeight.hashCode() : 0);
        result = 31 * result + (packageSource != null ? packageSource.hashCode() : 0);
        result = 31 * result + (packageDestination != null ? packageDestination.hashCode() : 0);
        return result;
    }
}
