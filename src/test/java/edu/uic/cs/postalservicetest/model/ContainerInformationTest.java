//package edu.uic.cs.postalservicetest.model;
//
//import static org.junit.Assert.assertEquals;
//
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import org.junit.AfterClass;
//import org.junit.Test;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import edu.uic.cs.postalservice.model.ContainerInformation;
//
///**
// * Created with IntelliJ IDEA.
// * User: Ashwath
// * Date: 11/10/13
// * Time: 9:03 PM
// * To change this template use File | Settings | File Templates.
// */
//public class ContainerInformationTest {
//
//    @BeforeClass
//    public static void testSetup(){
//
//    }
//
//    @AfterClass
//    public static void testCleanup(){
//
//    }
//
//    @Test
//    public void testtoJson()
//    {
//
//        // Creating the new AddressInformation object and placing the stuff
//        ContainerInformation tester = new ContainerInformation();
//        tester.setContainerId(1);
//        tester.setContainerName("testcontainer");
//        String jsotest1 = tester.toJson().toString();
//
//
//        JsonObject jso = new JsonObject();
//        jso.addProperty("container_name","testcontainer");
//        String jsotest2 = jso.toString();
//
//
//        assertEquals("Copmaring the Json values returned by the function",jsotest1,jsotest2);
//    }
//
//}
