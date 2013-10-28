package edu.uic.cs.postalservice.dao;

import com.google.gson.Gson;
import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.AddressInformation;
import edu.uic.cs.postalservice.model.PackageInformation;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public Integer addPackage(Integer packageType, Double packageWeight, AddressInformation packageSource, AddressInformation packageDestination){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        Integer packageId = null;
        try{
            tx = session.beginTransaction();
            PackageInformation obj = new PackageInformation(packageType,packageWeight,packageSource,packageDestination);
            packageId = (Integer) session.save(obj);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return packageId;
    }

    /* Method to get the list of all the package information */
    public List<PackageInformation> getPackageList( ){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        List<PackageInformation> packagelist = null;
        JSONArray json = new JSONArray();
        Gson gson = new Gson();
        try{
            tx = session.beginTransaction();
            packagelist = session.createQuery("FROM PackageInformation").list();
            System.out.println(packagelist.toString());
            tx.commit();
        }catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return packagelist;
    }

}
