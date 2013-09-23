package edu.uic.cs.postalservice.model;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 9/22/13
 * Time: 10:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserLoginInformation {
    private Integer userId;
    private String userName;
    private String passWord;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
