package edu.uic.cs.postalservicetest.rest;

import edu.uic.cs.postalservice.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ashwath
 * Date: 11/10/13
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class v1_PackageTest {

    private static Session session;
    private static  SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    @BeforeClass
    public static void before() {

        sessionFactory = buildSessionFactory();
        session = sessionFactory.openSession();
    }

    @Test
    public void testContainerInformationPersistance(){

        // Performing test for the container information
        ContainerInformation obj1 = new ContainerInformation("testtempo");
        session.save(obj1);

        String query = "from ContainerInformation";
        List list = session.createQuery(query).list();
        assertNotNull(list);
        assertEquals(1,list.size());

    }

    @Test
    public void testAddressInformationPersistance(){


        // Performing test for the address information
        AddressInformation obj2 = new AddressInformation("testaddress","testcity","teststate",11111);
        AddressInformation add2 = new AddressInformation("testaddress","testcity","teststate",22222);
        session.save(add2);
        session.save(obj2);

        String query = "from AddressInformation";
        List list = session.createQuery(query).list();
        assertNotNull(list);
        assertEquals(2,list.size());
    }

    @Test
    public void testPackageTypePersistance(){

        // Performing test for the Package Type
        PackageType obj3 = new PackageType("testbox");
        session.save(obj3);

        String query = "from PackageType";
        List list = session.createQuery(query).list();
        assertNotNull(list);
        assertEquals(1,list.size());
    }

    @Test
    public void testStatusInformation(){

        // Peforming test for the status information
        StatusInformation obj4 = new StatusInformation("testdelivered");
        session.save(obj4);

        String query = "from StatusInformation";
        List list = session.createQuery(query).list();
        assertNotNull(list);
        assertEquals(1,list.size());
    }

    @Test
    public void testPackageType(){
        PackageType obj5 = new PackageType("testcover");
        session.save(obj5);

        String query = "from PackageType";
        List list = session.createQuery(query).list();
        assertNotNull(list);
        assertEquals(2,list.size());
    }

//    // Work out this error
//    @Test
//    public void testPackageInformation(){
//
//        String packageTypeQuery = "from PackageType as pt where pt.package_type_id = :pId";
//        List packagetype_list = session.createQuery(packageTypeQuery)
//                .setInteger("pId", 1).list();
//        Iterator pit = packagetype_list.iterator();
//        PackageType packageType = null;
//        while(pit.hasNext())
//        {
//            packageType =  (PackageType)pit.next();
//        }
//
//        PackageInformation obj6 = new PackageInformation(packageType,35.00,null,null);
//        session.save(obj6);
//
//        String query = "from PackageInformation";
//        List list = session.createQuery(query).list();
//        assertNotNull(list);
//        assertEquals(1,list.size());
//
//    }

    @AfterClass
    public static void after()
    {
        session.close();
        sessionFactory.close();
    }


    private static SessionFactory buildSessionFactory()
    {
        try
        {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        }
        catch (Throwable ex)
        {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public  SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }


}
