package edu.uic.cs.postalservicetest.model;

import static org.junit.Assert.assertEquals;

import com.google.gson.JsonObject;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.BeforeClass;
import edu.uic.cs.postalservice.model.AddressInformation;

/**
* Created with IntelliJ IDEA.
* User: Ashwath
* Date: 11/9/13
* Time: 5:54 PM
* To change this template use File | Settings | File Templates.
*/
public class AddressInformationTest {

    @BeforeClass
    public static void testSetup(){

    }

    @AfterClass
    public static void testCleanup(){

    }

    @Test
    public void testtoJson()
    {

        // Creating the new AddressInformation object and placing the stuff
        AddressInformation tester = new AddressInformation();
        tester.setAddress_id(1);
        tester.setStreet_name("morse avenue");
        tester.setCity("sunnyvale");
        tester.setState("california");
        tester.setZipcode(111111);
        String jsotest1 = tester.toJson().toString();


        JsonObject jso = new JsonObject();
        jso.addProperty("street_name","morse avenue");
        jso.addProperty("city","sunnyvale");
        jso.addProperty("state","california");
        jso.addProperty("zipcode",111111);
        String jsotest2 = jso.toString();


        assertEquals("Comparing the two json values",jsotest1,jsotest2);
    }

}
