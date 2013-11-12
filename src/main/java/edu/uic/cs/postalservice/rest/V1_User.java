package edu.uic.cs.postalservice.rest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.uic.cs.postalservice.dao.ManageDependent;
import edu.uic.cs.postalservice.dao.ManageMain;
import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.*;
import org.hibernate.Session;

import org.hibernate.Query;
import org.hibernate.Transaction;

import javax.ws.rs.*;

import javax.ws.rs.core.Response;
import java.util.*;

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
        ManageMain mPack = ManageMain.getInstance();
        ManageDependent mDep = ManageDependent.getInstance();
        List<Integer> addressIds = new ArrayList<Integer>();
        int sourcepackage = 1;
        int destinationpackage = 2;
        int package_type_id = 2;
        int increment = 0;

        AddressInformation[] addresssArrays = new AddressInformation[2];
        addressIds.add(sourcepackage);
        addressIds.add(destinationpackage);

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String q1 = "from AddressInformation as p where p.address_id in (:Ids)";
        Query queryStatement = session.createQuery(q1);
        queryStatement.setParameterList("Ids",addressIds);
        List result = queryStatement.list();
        Iterator ait = result.iterator();

        while(ait.hasNext()){
            addresssArrays[increment] = (AddressInformation)ait.next();
            increment++;
        }

        String q2 = "from PackageType as pt where pt.package_type_id = :pId";
        List packagetype_list = session.createQuery(q2)
                .setInteger("pId", package_type_id).list();
        Iterator pit = packagetype_list.iterator();
        PackageType packageType = null;
        while(pit.hasNext())
        {
            packageType =  (PackageType)pit.next();
        }

        PackageInformation packageinfo = new PackageInformation(packageType,35.00,addresssArrays[0],addresssArrays[1],mDep.getContainerObj("tempo"),mDep.getStatusObj("shipped"));
        PackageInformation packageinfo1 = new PackageInformation(packageType,56.00,addresssArrays[1],addresssArrays[0],mDep.getContainerObj("van"),mDep.getStatusObj("transit"));
        PackageInformation packageinfo2 = new PackageInformation(packageType,500.00,addresssArrays[1],addresssArrays[0],mDep.getContainerObj("cargo"),mDep.getStatusObj("delivered"));

        UserRoles userRole = null;
        String query = "from UserRoles as u where u.roleType = :rType";
        List<UserRoles> list = session.createQuery(query).setString("rType","admin").list();
        Iterator it = list.iterator();
        while(it.hasNext())
        {
            userRole = (UserRoles)it.next();

        }

//        ArrayList<PackageInformation> packagelist = new ArrayList<PackageInformation>();

        // Defining the set of certificates which needs to be persisted in the database
        HashSet packageSet = new HashSet();
        packageSet.add(packageinfo);
        packageSet.add(packageinfo1);

        HashSet packageSet1 = new HashSet();
        packageSet1.add(packageinfo2);

        UserInformation obj1 = new UserInformation("ashwath","narayanan","04/26/1989","ashwath26@gmail.com","chicago",userRole,"ashwath26","26061949",packageSet);

        UserInformation obj2 = new UserInformation("rajiv reddy","Rao","04/26/1989","reddy@gmail.com","chicago",userRole,"reddy","123456",packageSet1);
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

        ManageMain mPack = ManageMain.getInstance();
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

//            HashSet<PackageInformation> packagelist = userinfo.getPackageInformation();
            Set<PackageInformation> packageset = userinfo.getPackageInformation();
            System.out.println(packageset.size());
            JsonArray json = new JsonArray();
            for(PackageInformation pack : packageset){
                JsonObject jsonObj1 = new JsonObject();
                jsonObj1.addProperty("package_id",pack.getPackageId());
                jsonObj1.addProperty("package_weight",pack.getPackageWeight());

                AddressInformation sourceAddress = pack.getPackageSource();
                jsonObj1.add("sourceAddress",sourceAddress.toJson());

                AddressInformation destinationAddress = pack.getPackageDestination();
                jsonObj1.add("destinationAddress",destinationAddress.toJson());

                PackageType packageType = pack.getPackageType();
                jsonObj1.add("package_type",packageType.toJson());

                json.add(jsonObj1);
            }
            jsonObj.add("user_packages",json);

            jsonuserarray.add(jsonObj);
        }
        returnString =  jsonuserarray.toString();
        return Response.status(200).entity(returnString).build();
    }

//    UserInformation obj1 = new UserInformation("ashwath","narayanan","04/26/1989","ashwath26@gmail.com","chicago",userRole,"ashwath26","26061949",packageSet);


}
