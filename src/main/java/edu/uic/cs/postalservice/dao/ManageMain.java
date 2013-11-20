package edu.uic.cs.postalservice.dao;

import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.AddressInformation;
import edu.uic.cs.postalservice.model.PackageInformation;
import edu.uic.cs.postalservice.model.UserInformation;
import org.hibernate.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 10/20/13
 * Time: 2:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class ManageMain {
    public static ManageMain instance = null;
    protected ManageMain(){
        // Does not do public instantiation of objects
    }

    public static ManageMain getInstance()
    {
        synchronized (ManageMain.class){
            if(instance == null){
                instance = new ManageMain();
            }
        }
        return instance;
    }


    // Method which is used to add the address information into the database if the string values is passed as parameter
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

    // Method which is used to add the address information into the database if the object is passed as parameter
    public AddressInformation addAddress(AddressInformation objinfo)
    {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        Integer addressId = null;
        AddressInformation address = null;
        try{
            tx = session.beginTransaction();
            address = new AddressInformation(objinfo.getStreet_name(),objinfo.getCity(),objinfo.getState(),objinfo.getZipcode());
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


    // Method which is used to add the package information into the database if the object is passed as parameter
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

    // Method which is used to add the user information into the database if the user object is passed as parameter
    public Integer addUser(UserInformation obj){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        Integer userId = null;
        try{
            tx = session.beginTransaction();
            userId = (Integer) session.save(obj);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return userId;

    }

    // Method which is used to get the list of all the packages
    public List<PackageInformation> getPackageList( ){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        List<PackageInformation> packagelist = null;
        try{
            tx = session.beginTransaction();
            packagelist = session.createQuery("FROM PackageInformation").list();
            for(PackageInformation packageinfo:packagelist){
                Hibernate.initialize(packageinfo);
            }
            tx.commit();
        }catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return packagelist;
    }


    // Method which is used to get the list of all the packages
    public List<PackageInformation> getPackageList(Integer packageId){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        List<PackageInformation> packagelist = null;
        try{
            tx = session.beginTransaction();
            String q1 = "FROM PackageInformation as pi where pi.packageId = :pId";
            Query queryStatement = session.createQuery(q1);
            queryStatement.setParameter("pId",packageId);
            packagelist= queryStatement.list();

            for(PackageInformation packageinfo:packagelist){
                Hibernate.initialize(packageinfo);
            }
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
            tx.commit();
        }catch (Exception e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return userlist;
    }

    public List<UserInformation> getUserList(Integer userid){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        List<UserInformation> userlist = null;
        try{
            tx = session.beginTransaction();
            String q1 = "FROM UserInformation as ui where ui.userId = :uId";
            Query queryStatement = session.createQuery(q1);
            queryStatement.setParameter("uId",userid);
            userlist= queryStatement.list();
            for(UserInformation userinfo : userlist){
                Hibernate.initialize(userinfo.getPackageInformation());
            }
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
