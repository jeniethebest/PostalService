package edu.uic.cs.postalservice.rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.AddressInformation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
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

/*        Session session = HibernateUtils.getSessionFactory().openSession();

        session.beginTransaction();
        User user = new User();

        user.setFirstName("Ashwath");
        user.setLastName("Narayanan");
        user.setEmail("test@email.com");
        session.save(user);
        PackageInformation pkg = new PackageInformation() ;

        session.getTransaction().commit();*/

//        return Response.status(200).entity(output).build();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx =  session.beginTransaction();

        AddressInformation obj = new AddressInformation("ashwath","salem","tamilnadu",6360079);
        System.out.println(obj.getZipcode());
        session.save(obj);
        tx.commit();

        return "Status updated successfully\n";

    }

    @GET
    @Path("/addressInformation")
    public Response getAllAddressInformation(){

        String returnString = null;
        JSONArray json = new JSONArray();
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        String query = "from AddressInformation";
        List list = session.createQuery(query).list();

        Iterator it = list.iterator();
        try{
            while(it.hasNext())
            {
                Gson gson = new Gson();
                AddressInformation addressObj = (AddressInformation)it.next();
                String addressInformation = gson.toJson(addressObj);
                json.put(addressInformation);

            }
            returnString = json.toString();
            session.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }

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
