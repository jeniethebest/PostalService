package edu.uic.cs.postalservice.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import edu.uic.cs.postalservice.dao.ManagePackage;
import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.AddressInformation;
import edu.uic.cs.postalservice.model.PackageInformation;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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

            System.out.print("\nPrining the values:"+obj.getPackageId() + "\t" + obj.getPackageType() + "\t" + obj.getPackageWeight() + "\n");
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
        int destinationpackage = 4;

        List<Integer> addressIds = new ArrayList<Integer>();
        ManagePackage mPack = ManagePackage.getInstance();
        addressIds.add(1);
        addressIds.add(2);

        int increment = 0;
        AddressInformation[] addresssArrays = new AddressInformation[2];

        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        String q1 = "from AddressInformation as p where p.address_id in (:Ids)";
        Query queryStatement = session.createQuery(q1);
        queryStatement.setParameterList("Ids",addressIds);
        List result = queryStatement.list();
        Iterator it = result.iterator();
//
        while(it.hasNext()){
            addresssArrays[increment] = (AddressInformation)it.next();
            increment++;
        }

        int package_id = mPack.addPackage(1,500.00,addresssArrays[0],addresssArrays[1]);
          System.out.println("The value of the addressid is:"+addresssArrays[0].getZipcode());
        System.out.println("The value of the addressid is:"+addresssArrays[1].getZipcode());

//        System.out.println("Added the new package whose id is:" + package_id);
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
        JSONArray json = new JSONArray();
        Gson gson = new Gson();
        returnString = gson.toJson(packagelist);
//        try{
//            for(PackageInformation pack : packagelist){
//                JSONObject jsonObj = new JSONObject();
//                AddressInformation sourceAddress = pack.getPackageSource();
//                JsonElement sourceAddressJson =  gson.toJsonTree(sourceAddress);
//                jsonObj.put("sourceAddress",sourceAddressJson);
//                AddressInformation destinationAddress = pack.getPackageDestination();
//                JsonElement destinationeAddressJson =  gson.toJsonTree(destinationAddress);
//                jsonObj.put("destinationAddress",destinationeAddressJson);
//                jsonObj.put("package_id",pack.getPackageId());
//                jsonObj.put("package_type",pack.getPackageType());
//                jsonObj.put("package_weight",pack.getPackageWeight());
////                JSONObject jsonObjSource = new JSONObject();
////                JSONObject jsonObjDestination = new JSONObject();
////                JSONObject jsonObj = new JSONObject();
////                AddressInformation sourceAddress = pack.getPackageSource();
////                AddressInformation destinationAddress = pack.getPackageDestination();
//
//
//                  json.put(jsonObj);
//            }
//            returnString = json.toString();
//        }catch(JSONException je){
//                returnString = je.toString();
//        }
        return Response.status(200).entity(returnString).build();

    }
}
