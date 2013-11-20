package edu.uic.cs.postalservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 8/24/13
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/hello")
public class Testing_Class {


    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Testing_Class Jersey" + "</hello>";
    }

    // This method is called if HTML is request
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "Testing_Class Jersey" + "</title>"
                + "<body><h1>" + "Testing_Class Jersey" + "</body></h1>" + "</html> ";
    }

    // This method is called if TEXT_PLAIN is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Testing_Class Jersey";
    }

}
