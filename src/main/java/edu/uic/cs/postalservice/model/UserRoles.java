package edu.uic.cs.postalservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 8/24/13
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="USER_ROLES",uniqueConstraints = {})
public class UserRoles {
    private int roleId;
    private String roleType;

    @Column(name="ROLE_ID",unique = true, nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Column(name="ROLE_TYPE",nullable = false, unique = true)
    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
