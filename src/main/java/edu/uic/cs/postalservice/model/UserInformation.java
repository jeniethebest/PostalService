package edu.uic.cs.postalservice.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserInformation
{
    private Integer userId;
    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String location;
    private UserRoles roleinformation;
    private String userLogin;
    private String userPassword;
    private List packageInformation;

    public UserInformation(){

    }

    public UserInformation(String firstName, String lastName, String dob, String email, String location, UserRoles roleinformation, String userLogin, String userPassword, List packageInformation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.location = location;
        this.roleinformation = roleinformation;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.packageInformation = packageInformation;
    }
    public UserInformation(String firstName, String lastName, String email, String dob, UserRoles roleinformation, String location)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.roleinformation = roleinformation;
        this.location = location;
    }

    public List getPackageInformation() {
        return packageInformation;
    }

    public void setPackageInformation(List packageInformation) {
        this.packageInformation = packageInformation;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public Integer getUserId()
    {
        return this.userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserRoles getRoleinformation() {
        return roleinformation;
    }

    public void setRoleinformation(UserRoles roleinformation) {
        this.roleinformation = roleinformation;
    }
}

