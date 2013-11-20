package edu.uic.cs.postalservicetest.model;

import com.google.gson.JsonObject;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.BeforeClass;
import edu.uic.cs.postalservice.model.PackageType;

import static org.junit.Assert.assertEquals;

/**
* Created with IntelliJ IDEA.
* User: Ashwath
* Date: 11/10/13
* Time: 9:11 PM
* To change this template use File | Settings | File Templates.
*/
public class PackageTypeTest {

    @BeforeClass
    public static void testSetup(){

    }

    @AfterClass
    public static void testCleanup(){

    }

    @Test
    public void testtoJson()
    {
        PackageType tester = new PackageType();
        tester.setPackage_name("testpackage");
        tester.setPackage_type_id(1);
        String jsotest1 = tester.toJson().toString();

        JsonObject jso = new JsonObject();
        jso.addProperty("package_name","testpackage");
        String jsotest2 = jso.toString();

        assertEquals("Comparing the two json values",jsotest1,jsotest2);

    }
}
