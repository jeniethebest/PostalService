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
    private Integer packageType;

    @SerializedName("package_weight")
    private Double packageWeight;

    @SerializedName("package_source")
    private AddressInformation packageSource;


    private AddressInformation packageDestination;

    public PackageInformation()
    {

    }
    public PackageInformation(Integer packageType, Double packageWeight, AddressInformation packageSource, AddressInformation packageDestination)
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

    public Integer getPackageType() {
        return packageType;
    }

    public void setPackageType(Integer packageType) {
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

    public boolean equals (Object obj)
    {
        if(obj == null) return false;
        if(!this.getClass().equals(obj.getClass())) return false;
        PackageInformation obj2 = (PackageInformation) obj;

        if((this.getPackageId() == obj2.getPackageId()) && (this.getPackageWeight() == obj2.getPackageWeight())
            && (this.getPackageType() == obj2.getPackageType()) && (this.getPackageSource().equals(obj2.getPackageSource()))
            && (this.getPackageDestination().equals(obj2.getPackageDestination())) )
        {
            return true;
        }
        return false;
    }

    public int hashCode()
    {
        int tmp=0;
        tmp = packageId ;
        return tmp;

    }
}
