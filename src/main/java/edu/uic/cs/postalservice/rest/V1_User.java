package edu.uic.cs.postalservice.rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.uic.cs.postalservice.dao.ManagePackage;
import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.*;
import org.hibernate.Session;

import org.hibernate.Query;
import org.hibernate.Transaction;

import javax.ws.rs.GET;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Path;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 11/3/13
 * Time: 3:14 PM
 * To change this template use File | Settings | File Templates.
 */

@Path("v1/User")
public class V1_User {

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

        ManagePackage mPack = ManagePackage.getInstance();
        List<PackageInformation> packagelist = mPack.getPackageList();

        UserInformation obj1 = new UserInformation("ashwath","narayanan","04/26/1989","ashwath26@gmail.com","chicago",userRole,"ashwath26","26061949",packagelist);
        UserInformation obj2 = new UserInformation("ashwath sundaresan","narayanan","04/26/1989","ashwath26@gmail.com","chicago",userRole,"ashwath26","26061949",packagelist);
        session.save(obj1);
        session.save(obj2);
        tx.commit();
        session.close();
        return Response.status(200).entity("Updated userinformation successfully").build();
    }

    @GET
    @Produces("text/plain")
    @Path("/userInformationGet/")
    public Response userInformationGet(){

        ManagePackage mPack = ManagePackage.getInstance();
        String returnString = null;
        List<UserInformation> userlist = mPack.getUserList();

        JsonArray jsonuserarray = new JsonArray();
        for(UserInformation userinfo : userlist){
            JsonObject jsonObj = new JsonObject();
            jsonObj.addProperty("user_id",userinfo.getUserId());
            jsonObj.addProperty("user_first_name",userinfo.getFirstName());
            jsonObj.addProperty("user_last_name",userinfo.getLastName());
            jsonObj.addProperty("user_email",userinfo.getEmail());
            jsonObj.addProperty("user_dob",userinfo.getDob());
            jsonObj.addProperty("user_location",userinfo.getLocation());
            jsonObj.add("user_role_information",userinfo.getRoleinformation().toJson());

//            List<PackageInformation> packagelist = userinfo.getPackageInformation();
//            JsonArray json = new JsonArray();
//            for(PackageInformation pack : packagelist){
//                JsonObject jsonObj1 = new JsonObject();
//                jsonObj1.addProperty("package_id",pack.getPackageId());
//                jsonObj1.addProperty("package_weight",pack.getPackageWeight());
//
//                AddressInformation sourceAddress = pack.getPackageSource();
//                jsonObj1.add("sourceAddress",sourceAddress.toJson());
//
//                AddressInformation destinationAddress = pack.getPackageDestination();
//                jsonObj1.add("destinationAddress",destinationAddress.toJson());
//
//                PackageType packageType = pack.getPackageType();
//                jsonObj1.add("package_type",packageType.toJson());
//
//                json.add(jsonObj1);
//            }
//            jsonObj.add("user_packages",json);
            jsonuserarray.add(jsonObj);
        }
        returnString =  jsonuserarray.toString();
        return Response.status(200).entity(returnString).build();
    }


}
