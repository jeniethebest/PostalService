//package edu.uic.cs.postalservicetest.model;
//
//import static org.junit.Assert.assertEquals;
//
//import com.google.gson.JsonObject;
//import org.junit.AfterClass;
//import org.junit.Test;
//import org.junit.BeforeClass;
//
//import edu.uic.cs.postalservice.model.StatusInformation;
//
//
///**
// * Created with IntelliJ IDEA.
// * User: Ashwath
// * Date: 11/10/13
// * Time: 9:30 PM
// * To change this template use File | Settings | File Templates.
// */
//public class StatusInformationTest {
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
//        StatusInformation tester = new StatusInformation();
//        tester.setStatus_id(1);
//        tester.setStatus_type("teststatus");
//        String jsotest1 = tester.toJson().toString();
//
//        JsonObject jso = new JsonObject();
//        jso.addProperty("status_type","teststatus");
//        String jsotest2 = jso.toString();
//
//        assertEquals("Comparing the two json values",jsotest1,jsotest2);
//    }
//
//}
