package edu.uic.cs.postalservice.rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.uic.cs.postalservice.dao.ManageMain;
import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.*;
import edu.uic.cs.postalservice.dao.ManageDependent;

import org.hibernate.Session;

import org.hibernate.Query;

import javax.ws.rs.*;
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
        }
        session.close();
        return "Take the value";
    }


    @GET
    @Produces("text/plain")
    @Path("/packagePut/")
    public String putPackage()
    {
        int sourcepackage = 1;
        int destinationpackage = 2;
        int package_type_id = 2;
        String containertype ="van";
        String statustype ="delivered";

        List<Integer> addressIds = new ArrayList<Integer>();
        ManageMain mPack = ManageMain.getInstance();
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

        ManageDependent mObj = ManageDependent.getInstance();

        PackageInformation obj1 = new PackageInformation(packageType,35.00,addresssArrays[0],addresssArrays[1],containertype,statustype);
        PackageInformation obj2 = new PackageInformation(packageType,56.00,addresssArrays[1],addresssArrays[0],containertype,statustype);

        int package_id1 = mPack.addPackage(obj1);
        int package_id2 = mPack.addPackage(obj2);


        session.close();
        return "Successfully updated the package information\n";

    }

    @GET
    @Produces("text/plain")
    @Path("/packageGet/")
    public Response packageGet(){

        ManageMain mPack = ManageMain.getInstance();
        String returnString = null;
        List<PackageInformation> packagelist = mPack.getPackageList();
        JsonArray json = new JsonArray();
        Gson gson = new Gson();
        try{
            for(PackageInformation pack : packagelist){
                JsonObject jsonObj = new JsonObject();
                jsonObj.addProperty("package_id",pack.getPackageId());
                jsonObj.addProperty("package_weight",pack.getPackageWeight());

                AddressInformation sourceAddress = pack.getPackageSource();
                jsonObj.add("source_address",sourceAddress.toJson());

                AddressInformation destinationAddress = pack.getPackageDestination();
                jsonObj.add("destination_address",destinationAddress.toJson());

                PackageType packageType = pack.getPackageType();
                jsonObj.add("package_type",packageType.toJson());

                jsonObj.addProperty("container_info",pack.getPackageContainer());

                jsonObj.addProperty("status_info",pack.getPackageStatus());
                json.add(jsonObj);
            }
            returnString = json.toString();

        }catch(Exception je){
                returnString = je.toString();
        }
        return Response.status(200).entity(returnString).build();
    }




}
