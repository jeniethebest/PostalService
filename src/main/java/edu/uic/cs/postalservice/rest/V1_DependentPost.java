package edu.uic.cs.postalservice.rest;

import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.AddressInformation;
import edu.uic.cs.postalservice.model.ContainerInformation;
import edu.uic.cs.postalservice.model.StatusInformation;
import edu.uic.cs.postalservice.model.UserRoles;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 11/19/13
 * Time: 11:11 PM
 * To change this template use File | Settings | File Templates.
 */

@Path("v1/DependentPost")
public class V1_DependentPost {

    // Post method to update the package information to add new package to a user
    @POST
    @Path("addContainer")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces(MediaType.TEXT_PLAIN)
    public Response addContainer(
            @FormParam("containerName") String containerName
    )
    {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        int result = 0;
        try{
            tx = session.beginTransaction();
            ContainerInformation obj = new ContainerInformation(containerName);
            session.save(obj);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return Response.status(200).entity("Failure in updating container information").build();
    }

    // Post method to update the package information to add new package to a user
    @POST
    @Path("addStatus")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces(MediaType.TEXT_PLAIN)
    public Response addStatus(
            @FormParam("statusName") String statusName
    )
    {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        int result = 0;
        try{
            tx = session.beginTransaction();
            StatusInformation obj = new StatusInformation(statusName);
            session.save(obj);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return Response.status(200).entity("Success in updating status information").build();
    }


    // Post method to update the package information to add new package to a user
    @POST
    @Path("addRole")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces(MediaType.TEXT_PLAIN)
    public Response addRole(
            @FormParam("roleName") String roleName
    )
    {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            UserRoles obj = new UserRoles(roleName);
            session.save(obj);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return Response.status(200).entity("Success in updating role information").build();
    }


    // Post method to update the package information to add new package to a user
    @POST
    @Path("addAddressInformation")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces(MediaType.TEXT_PLAIN)
    public Response addAddressInformation(
            @FormParam("streetName") String street,
            @FormParam("cityName") String city,
            @FormParam("stateName") String state,
            @FormParam("zipcode") Integer zip
    )
    {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            AddressInformation obj = new AddressInformation(street,city,state,zip);
            session.save(obj);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return Response.status(200).entity("Success in updating address information").build();
    }

}
