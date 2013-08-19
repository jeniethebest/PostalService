package edu.uic.cs.postalservice.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created with IntelliJ IDEA.
 * User: ashwath
 * Date: 8/18/13
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */



public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
        try{

            return new Configuration().configure().buildSessionFactory();

        }
        catch (Throwable ex){
            System.out.println("Initial SesstionFactory created failed."+ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSesstionFactory(){
         return sessionFactory;
    }

    public static void shutdown(){
        getSesstionFactory().close();
    }

}
