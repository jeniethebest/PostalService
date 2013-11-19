package edu.uic.cs.postalservice.dao;

import com.google.gson.annotations.SerializedName;
import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.*;
import org.hibernate.Session;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 11/11/13
 * Time: 7:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManageDependent {

    // Creating a singleton object for the ManageDependent class
    public static ManageDependent instance = null;
    public static HashMap<String,ContainerInformation> conid_containerName;
    protected ManageDependent(){
        // Does not do public instantiation of objects
    }

    public static ManageDependent getInstance()
    {
        synchronized (ManageDependent.class){
            if(instance == null){
                instance = new ManageDependent();
            }
        }
        return instance;
    }

    // Getting the container object information.
    public ContainerInformation getContainerObj(String containerName)
    {
        Session session = null;
        ContainerInformation containerInformation = null;
        try{

            session = HibernateUtils.getSessionFactory().openSession();
            String q2 = "from ContainerInformation as ci where ci.containerName = :cName";
            List containerinformation_list = session.createQuery(q2)
                    .setString("cName", containerName).list();

            Iterator pit = containerinformation_list.iterator();

            while(pit.hasNext())
            {
                containerInformation =  (ContainerInformation)pit.next();
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return containerInformation;
    }

    // getting the package object information
    public PackageType getPackageObj(String packageName)
    {
        Session session = null;
        PackageType packageType = null;
        try{

            session = HibernateUtils.getSessionFactory().openSession();
            String q2 = "from PackageType as pt where pt.package_name = :pName";
            List Packagetype_list = session.createQuery(q2)
                    .setString("pName", packageName).list();

            Iterator pit = Packagetype_list.iterator();

            while(pit.hasNext())
            {
                packageType =  (PackageType)pit.next();
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return packageType;
    }



    // getting the user role information
    public UserRoles getRoleObj(String roleName){
        Session session = null;
        UserRoles userRoles = null;
        try{

            session = HibernateUtils.getSessionFactory().openSession();
            String q2 = "from UserRoles as ur where ur.roleType = :rType";
            List userrole_list = session.createQuery(q2)
                    .setString("rType", roleName).list();

            Iterator pit = userrole_list.iterator();

            while(pit.hasNext())
            {
                userRoles =  (UserRoles)pit.next();
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return userRoles;
    }

    // getting the the status infomation object
    public StatusInformation getStatusObj (String statusName){
        Session session = null;
        StatusInformation statusInformation = null;
        try{

            session = HibernateUtils.getSessionFactory().openSession();
            String q2 = "from StatusInformation as si where si.status_type = :sType";
            List userrole_list = session.createQuery(q2)
                    .setString("sType", statusName).list();

            Iterator pit = userrole_list.iterator();

            while(pit.hasNext())
            {
                statusInformation =  (StatusInformation)pit.next();
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return statusInformation;
    }

}
