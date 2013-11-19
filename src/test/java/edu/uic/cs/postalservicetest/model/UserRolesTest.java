//package edu.uic.cs.postalservicetest.model;
//
//import com.google.gson.JsonObject;
//import edu.uic.cs.postalservice.model.UserRoles;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created with IntelliJ IDEA.
// * User: Ashwath
// * Date: 11/10/13
// * Time: 9:34 PM
// * To change this template use File | Settings | File Templates.
// */
//public class UserRolesTest {
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
//        UserRoles tester = new UserRoles();
//        tester.setRoleId(1);
//        tester.setRoleType("admintest");
//        String jsotest1 = tester.toJson().toString();
//
//        JsonObject jso = new JsonObject();
//        jso.addProperty("role_type","admintest");
//        String jsotest2 = jso.toString();
//
//        assertEquals("Comparing the two json values",jsotest1,jsotest2);
//    }
//
//}
