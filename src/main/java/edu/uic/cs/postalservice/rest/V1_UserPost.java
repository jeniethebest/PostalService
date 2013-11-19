package edu.uic.cs.postalservice.rest;

import edu.uic.cs.postalservice.dao.ManageMain;
import edu.uic.cs.postalservice.dao.ManageDependent;
import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.UserInformation;
import edu.uic.cs.postalservice.model.UserRoles;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 11/11/13
 * Time: 6:59 PM
 * To change this template use File | Settings | File Templates.
 */

@Path("v1/UserPost")
public class V1_UserPost {
    ManageMain mPack = ManageMain.getInstance();
    ManageDependent mDep = ManageDependent.getInstance();


    // Post method which is used to post the user information and save in the database
    @POST
    @Path("createUser")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces(MediaType.TEXT_PLAIN)
    public String postUserInfo(@FormParam("userFirstName") String userfirstname,
                                  @FormParam("userLastName") String userlastname,
                                  @FormParam("userDob") String userDob,
                                  @FormParam("userEmail") String emailAddress,
                                  @FormParam("userLocation") String city,
                                  @FormParam("userRole") String rolename,
                                  @FormParam("userLogin") String userLogin,
                                  @FormParam("userPassword") String userPassword
                                  )
    {
        String returnData = null;
        ManageDependent mDep = ManageDependent.getInstance();
        ManageMain mMain = ManageMain.getInstance();
        UserRoles userRoles = mDep.getRoleObj(rolename);
        UserInformation obj1 = new UserInformation(userfirstname,userlastname,userDob,emailAddress,city,userRoles,userLogin,userPassword,null);
        mMain.addUser(obj1);
        return returnData;
    }

    // Post method to update the package information to add new package to a user
    @POST
    @Path("updateUserLocation")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces(MediaType.TEXT_PLAIN)
    public String postUserUpdate(
                                  @FormParam("userLogin") String userLogin,
                                  @FormParam("userLocation") String userLocation
                                )
    {
        String returnData = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        int result = 0;
        try{
            tx = session.beginTransaction();
            String hql = "UPDATE UserInformation set location = :userLocation"+
                    "WHERE userLogin = :userLogin";
            Query query = session.createQuery(hql);
            query.setParameter("userLocation",userLocation);
            query.setParameter("userLogin",userLogin);
            result = query.executeUpdate();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        if(result > 0)
        {
            return "Update Successful";
        }
        else
        {
            return "Update Failure";
        }
    }


    // Post method to update the package information to add new package to a user
    @POST
    @Path("updateUserRole")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces(MediaType.TEXT_PLAIN)
    public String postUserUpdateRole(
            @FormParam("userLogin") String userLogin,
            @FormParam("userRole") String userRole
    )
    {
        String returnData = null;
        ManageDependent mDep = ManageDependent.getInstance();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        int result = 0;
        try{
            tx = session.beginTransaction();
            UserRoles newRole = mDep.getRoleObj(userRole);
            String hql = "UPDATE UserInformation set role = :roleName"+
                    "WHERE userLogin = :userLogin";
            Query query = session.createQuery(hql);

            query.setParameter("roleName",newRole);
            query.setParameter("userLogin",userLogin);
            result = query.executeUpdate();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        if(result > 0)
        {
            return "Update Successful";
        }
        else
        {
            return "Update Failure";
        }
    }

}
