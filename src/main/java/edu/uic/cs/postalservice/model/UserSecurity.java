package edu.uic.cs.postalservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 8/24/13
 * Time: 7:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="USER_SECURITY", uniqueConstraints = {})
public class UserSecurity {
    private Integer userId;
    private String userLoginName;
    private String passWord;

    @Column(name="USER_ID", nullable = false,unique = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name="USER_LOGIN_NAME", nullable = false,unique = true)
    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    @Column(name="USER_PASSWORD", nullable = false, unique = true)
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
