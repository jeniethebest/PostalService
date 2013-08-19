package edu.uic.cs.postalservice.entity;

import javax.persistence.Entity;


@Entity
public class PackageInformation
{
    private Long packageId;

    private String packageType;

    private Long packageWeight;

    private String sourceAddress;

    private String destinationAddress;

    public void setPackageId(Long packageId){
        this.packageId = packageId;
    }

    public void setPackageType(String packageType){
        this.packageType = packageType;
    }

    public void setPackageWeight(Long packageWeight){
        this.packageWeight = packageWeight;
    }

    public void setSourceAddress(String sourceAddress){
        this.sourceAddress = sourceAddress;
    }

    public void setDestinationAddress(String destinationAddress){
        this.destinationAddress = destinationAddress;
    }

    public Long getPackageId(){
        return this.packageId;
    }
}