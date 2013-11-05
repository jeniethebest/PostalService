package edu.uic.cs.postalservice.rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.uic.cs.postalservice.dao.ManagePackage;
import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.AddressInformation;
import edu.uic.cs.postalservice.model.PackageInformation;
import edu.uic.cs.postalservice.model.PackageType;
import edu.uic.cs.postalservice.model.UserInformation;
import org.hibernate.Session;

import org.hibernate.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 8/24/13
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("/v1/Package")
public class V1_Package {

    @GET
    @Produces("text/plain")
    @Path("/{packageId}/")
    public String getPackageDetails(@PathParam("packageId") int packageId)
    {
        String returnData = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        String query = "from PackageInformation as p where p.packageId = :sId";
        List list = session.createQuery(query)
                .setInteger("sId", packageId).list();

        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            PackageInformation obj = (PackageInformation) iterator.next();//cast and assign data to Person type object

            System.out.print("\nPrining the values:"+obj.getPackageId() + "\t" + obj.getPackageWeight() + "\n");
//            returnData.concat(obj.getPackageId() + "\t" + obj.getPackageType() + "\t" + obj.getPackageWeight() + "\n");
        }
        session.close();
        return "Take the value";
    }

    @GET
    @Produces("text/plain")
//    @Path("/{packageType}/{packageWeight}/{packageSource}/{packageDestination}/")
    @Path("/packagePut/")
    public String putPackage()
    {
        int sourcepackage = 1;
        int destinationpackage = 2;
        int package_type_id = 2;

        List<Integer> addressIds = new ArrayList<Integer>();
        ManagePackage mPack = ManagePackage.getInstance();
        addressIds.add(sourcepackage);
        addressIds.add(destinationpackage);
        int increment = 0;
        AddressInformation[] addresssArrays = new AddressInformation[2];
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        String q1 = "from AddressInformation as p where p.address_id in (:Ids)";
        Query queryStatement = session.createQuery(q1);
        queryStatement.setParameterList("Ids",addressIds);
        List result = queryStatement.list();
        Iterator ait = result.iterator();
//
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

        PackageInformation obj1 = new PackageInformation(packageType,35.00,addresssArrays[0],addresssArrays[1]);
        PackageInformation obj2 = new PackageInformation(packageType,56.00,addresssArrays[1],addresssArrays[0]);

        int package_id1 = mPack.addPackage(obj1);
        int package_id2 = mPack.addPackage(obj2);


        session.close();
        return "Successfully updated the package information\n";

    }

    @GET
    @Produces("text/plain")
//    @Path("/{packageType}/{packageWeight}/{packageSource}/{packageDestination}/")
    @Path("/packageGet/")
    public Response packageGet(){

        ManagePackage mPack = ManagePackage.getInstance();
        String returnString = null;
//
//        List  retrievedList = mPack.getPackageList();
//        JSONArray jsonAraay = new JSONArray(retrievedList);
//        for(Iterator it = retrievedList.iterator(); it.hasNext();)
//            {
////                Gson gson = new Gson();
//                PackageInformation packObj = (PackageInformation)it.next();
////                String packagestring = gson.toJson(packObj);
////                System.out.println(packagestring);
////                json.put(packObj);
//
//            }
//            returnString = jsonAraay.toString();
//            System.out.println(returnString);
        List<PackageInformation> packagelist = mPack.getPackageList();
        JsonArray json = new JsonArray();
        Gson gson = new Gson();
//        returnString = gson.toJson(packagelist);
        try{
            for(PackageInformation pack : packagelist){
                JsonObject jsonObj = new JsonObject();
                jsonObj.addProperty("package_id",pack.getPackageId());
                jsonObj.addProperty("package_weight",pack.getPackageWeight());

                AddressInformation sourceAddress = pack.getPackageSource();
                jsonObj.add("sourceAddress",sourceAddress.toJson());

                AddressInformation destinationAddress = pack.getPackageDestination();
                jsonObj.add("destinationAddress",destinationAddress.toJson());

                PackageType packageType = pack.getPackageType();
                jsonObj.add("package_type",packageType.toJson());

                json.add(jsonObj);
            }
            returnString = json.toString();

        }catch(Exception je){
                returnString = je.toString();
        }
        return Response.status(200).entity(returnString).build();
    }




}
