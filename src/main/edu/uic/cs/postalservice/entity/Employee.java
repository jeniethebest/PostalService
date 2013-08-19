package edu.uic.cs.postalservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: ashwath
 * Date: 8/18/13
 * Time: 2:39 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Employee {

    @Id
    private int id;
    private String fname;
    private String lname;
    private int salary;

    public Employee(){};
    public Employee(int id,String fname, String lname, int salary)
    {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.salary = salary;
    }
    public void setSalary(int salary){
        this.salary = salary;
    }
    public void setFname(String fname){
        this.fname = fname;
    }
    public void setLname(String lname){
        this.lname = lname;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public int getSalary(){
        return salary;
    }
    public String getFname(){
        return fname;
    }
    public String getLname(){
        return lname;
    }
}
