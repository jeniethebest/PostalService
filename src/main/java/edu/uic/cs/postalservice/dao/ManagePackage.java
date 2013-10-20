package edu.uic.cs.postalservice.dao;

import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.AddressInformation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 10/20/13
 * Time: 2:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class ManagePackage {
    public static ManagePackage instance = null;
    protected ManagePackage(){
        // Does not do public instantiation of objects
    }

    public static ManagePackage getInstance()
    {
        synchronized (ManagePackage.class){
            if(instance == null){
                instance = new ManagePackage();
            }
        }
        return instance;
    }

    public AddressInformation addAddress(String street_name, String city, String state, int zipcode )
    {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        Integer addressId = null;
        AddressInformation address = null;
        try{
            tx = session.beginTransaction();
            address = new AddressInformation(street_name,city,state,zipcode);
            addressId = (Integer) session.save(address);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return address;
    }

    /* Method to add an employee record in the database */
    public Integer addEmployee(Integer packageType, Double packageWeight, AddressInformation packageSource, AddressInformation packageDestination){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try{
            tx = session.beginTransaction();
            Employee employee = new Employee(fname, lname, salary, address);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return employeeID;
    }
}
