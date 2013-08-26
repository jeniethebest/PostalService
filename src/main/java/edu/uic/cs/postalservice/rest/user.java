package edu.uic.cs.postalservice.rest;


import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.*;
import org.hibernate.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/user")

public class user {

    @GET
    @Produces("text/plain")
    @Path("/{firstName}/")
    public String getMsg(@PathParam("firstName") String firstname)
    {

        String output = "Jersey say : " + firstname;

        Session session = HibernateUtils.getSessionFactory().openSession();

        session.beginTransaction();
        User user = new User();

        user.setFirstName("Ashwath");
        user.setLastName("Narayanan");
        user.setEmail("test@email.com");
        session.save(user);
        PackageInformation pkg = new PackageInformation() ;

        session.getTransaction().commit();

//        return Response.status(200).entity(output).build();
        return "Status updated successfully\n";

    }


}
