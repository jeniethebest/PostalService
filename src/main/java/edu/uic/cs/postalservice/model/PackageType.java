package edu.uic.cs.postalservice.model;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 9/22/13
 * Time: 4:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class PackageType {
    private Integer package_id;
    private String package_name;

    public Integer getPackage_id() {
        return package_id;
    }

    public void setPackage_id(Integer package_id) {
        this.package_id = package_id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }
}
