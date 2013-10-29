package edu.uic.cs.postalservice.rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.AddressInformation;
import edu.uic.cs.postalservice.model.ContainerInformation;
import edu.uic.cs.postalservice.model.PackageType;
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

//    @GET
//    @Path("/detailsPut")
//    public Response detailsPut(){
//
//        packagetypePut();
//        containerPut();
//        return Response.status(200).entity(ret+"\nReturning after executing /packagetypePut and /containerPut").build();
//    }

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

}
