package edu.uic.cs.postalservice.rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Path("/user")

public class user {

    @GET
    @Produces("text/plain")
    @Path("/name/{firstName}/")
    public String getMsg(@PathParam("firstName") String firstname)
    {

        String output = "Jersey say : " + firstname;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx =  session.beginTransaction();

        AddressInformation obj = new AddressInformation("ashwath","salem","tamilnadu",6360079);
        System.out.println(obj.getZipcode());
        session.save(obj);
        tx.commit();

        return "Status updated successfully\n";

    }

    @GET
    @Path("/detailsPut")
    public Response detailsPut(){

        packagetypePut();
        containerPut();
        userRolesPut();
        statusInformationPut();
        addressInformationPut();
        userInformationPut();
        return Response.status(200).entity("\nReturning after executing /packagetypePut and /containerPut and userRolesPut").build();
    }

    @GET
    @Produces("text/plain")
    @Path("/addressInformationPut/")
    public String addressInformationPut()
    {

//        String output = "Jersey say : " + firstname;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx =  session.beginTransaction();

        AddressInformation obj = new AddressInformation("1514 W Taylor Street","Chicago","Illinois",60607);
        AddressInformation obj1 = new AddressInformation("1063 Morse Avenue","Sunnyvale","California",94089);
        System.out.println(obj.getZipcode());
        session.save(obj);
        session.save(obj1);
        tx.commit();
        return "Status updated successfully\n";
    }

    @GET
    @Path("/packagetypePut")
    public Response packagetypePut(){

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        PackageType obj = new PackageType("box");
        PackageType obj1 = new PackageType("container");
        session.save(obj);
        session.save(obj1);
        tx.commit();
        session.close();
        return Response.status(200).entity("Package information successfully updated in the database").build();
    }


    @GET
    @Path("/containerPut")
    public Response containerPut(){

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        ContainerInformation obj = new ContainerInformation("truck");
        ContainerInformation obj1 = new ContainerInformation("van");
        ContainerInformation obj2 = new ContainerInformation("tempo");
        session.save(obj);
        session.save(obj1);
        session.save(obj2);
        tx.commit();
        session.close();
        return Response.status(200).entity("Container information successfully updated in the database").build();
    }

    @GET
    @Path("/addressInformation")
    public Response getAllAddressInformation(){

        String returnString = null;
        JSONArray json = new JSONArray();
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        String query = "from AddressInformation";
        List<AddressInformation> list = session.createQuery(query).list();
        Gson gson = new Gson();
        returnString = gson.toJson(list);
        session.close();
        System.out.println("The size of list is"+list.size());
        return Response.status(200).entity(returnString).build();
    }

    @GET
    @Path("{id: \\d+}")
    public Response  getAddressInformation(@PathParam("id")int address_id){
        String addressInformation = null;
        Gson gson = new Gson();
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        String query = "from AddressInformation as p where p.address_id = :Id";
        List list = session.createQuery(query)
                .setInteger("Id", address_id).list();

        Iterator it = list.iterator();
        System.out.println("The size of list is"+list.size());
        AddressInformation obj = null;

        while(it.hasNext()){
             obj = (AddressInformation)it.next();
             addressInformation = gson.toJson(obj);
        }
        session.close();
        return Response.status(200).entity(addressInformation).build();
    }


    @GET
    @Path("/userRolesPut")
    public Response userRolesPut()
    {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        UserRoles obj1 = new UserRoles("user");
        UserRoles obj2 = new UserRoles("admin");
        session.save(obj1);
        session.save(obj2);
        tx.commit();
        session.close();
        return Response.status(200).entity("Updated user Roles in the database").build();
    }

    @GET
    @Path("/statusInformationPut")
    public Response statusInformationPut()
    {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        StatusInformation obj1 = new StatusInformation("shipped");
        StatusInformation obj2 = new StatusInformation("transit");
        StatusInformation obj3 = new StatusInformation("delayed");
        StatusInformation obj4 = new StatusInformation("delivered");
        session.save(obj1);
        session.save(obj2);
        session.save(obj3);
        session.save(obj4);
        tx.commit();
        session.close();
        return Response.status(200).entity("Updated status information in the database").build();
    }

    @GET
    @Path("/userInformationPut")
    public Response userInformationPut()
    {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        UserRoles userRole = null;
        String query = "from UserRoles as u where u.roleType = :rType";
        List<UserRoles> list = session.createQuery(query).setString("rType","admin").list();
        Iterator it = list.iterator();
        while(it.hasNext())
        {
            userRole = (UserRoles)it.next();
        }
        UserInformation obj1 = new UserInformation("ashwath","narayanan","04/26/1989","ashwath26@gmail.com","chicago",userRole,"ashwath26","26061949");
        UserInformation obj2 = new UserInformation("ashwath sundaresan","narayanan","04/26/1989","ashwath26@gmail.com","chicago",userRole,"ashwath26","26061949");
        session.save(obj1);
        session.save(obj2);
        tx.commit();;
        session.close();
        return Response.status(200).entity("Updated userinformation successfully").build();
    }
}
