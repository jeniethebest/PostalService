package edu.uic.cs.postalservice.dao;

import com.google.gson.Gson;
import com.sun.rmi.rmid.ExecPermission;
import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.AddressInformation;
import edu.uic.cs.postalservice.model.PackageInformation;
import edu.uic.cs.postalservice.model.PackageType;
import edu.uic.cs.postalservice.model.UserInformation;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.*;

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
    public Integer addPackage(PackageInformation obj){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        Integer packageId = null;
        try{
            tx = session.beginTransaction();
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

    public List<UserInformation> getUserList( ){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        List<UserInformation> userlist = null;
        try{
            tx = session.beginTransaction();
            userlist = session.createQuery("FROM UserInformation").list();
            for(UserInformation userinfo : userlist){
                Hibernate.initialize(userinfo.getPackageInformation());
            }
//            for(Iterator ite = userlist.iterator(); ite.hasNext();){
//                UserInformation tempobj = (UserInformation) ite.next();
//                System.out.println("The id value is"+tempobj.getUserId());
//                List<PackageInformation> packdetails = tempobj.getPackageInformation();
//                System.out.println("The number of packages are:"+packdetails.size());
//            }
            System.out.println(userlist.toString());
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return userlist;
    }

    public List<UserInformation> getUserList(int userid){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        List<UserInformation> userlist = null;
        try{
            tx = session.beginTransaction();
            String q1 = "FROM UserInformation as ui where ui.userId = :uId";
            Query queryStatement = session.createQuery(q1);
            queryStatement.setParameter("uId",userid);
            userlist= queryStatement.list();
            System.out.println(userlist.toString());
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return userlist;
    }


}
