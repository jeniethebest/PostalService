package edu.uic.cs.postalservice.rest;

import edu.uic.cs.postalservice.hibernate.HibernateUtils;
import edu.uic.cs.postalservice.model.PackageInformation;
import org.hibernate.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    public String putPackage()
    {
        PackageInformation pkginfo = new PackageInformation();
        pkginfo.setPackageType(2);
        pkginfo.setPackageWeight(1.3);
        pkginfo.setPackageSource("chicago");
        pkginfo.setPackageDestination("sanjose");

        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(pkginfo);
        session.getTransaction().commit();

        return "Successfully updated the package information\n";

    }





}
