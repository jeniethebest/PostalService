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
    private Integer userpackageId;
    private Integer userId;
    private Integer packageId;

    public UserPackage(Integer packageId, Integer userId) {
        this.packageId = packageId;
        this.userId = userId;
    }

    public Integer getUserpackageId() {
        return userpackageId;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserpackageId(Integer userpackageId) {
        this.userpackageId = userpackageId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }
}
