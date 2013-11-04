package edu.uic.cs.postalservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 8/24/13
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="USER_PACKAGES",uniqueConstraints = {})
public class UserPackage {
    private Integer userId;
    private Set packageInformation;

    public UserPackage(Set packageInformation, Integer userId) {
        this.packageInformation = packageInformation;
        this.userId = userId;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set getPackageInformation() {
        return packageInformation;
    }

    public void setPackageInformation(Set packageInformation) {
        this.packageInformation = packageInformation;
    }
}
