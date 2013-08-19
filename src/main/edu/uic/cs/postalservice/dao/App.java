package edu.uic.cs.postalservice.dao;

import edu.uic.cs.postalservice.entity.Employee;
//import edu.uic.cs.postalservice.utilities.HibernateUtil;
//import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

/**
 * Created with IntelliJ IDEA.
 * User: ashwath
 * Date: 8/18/13
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class App {

    public static void main(String[] args)
    {

        Employee employee = new Employee(10,"ashwath","narayanan",100);
        SessionFactory sessionFactory = new Configuration().configure("/edu/uic/cs/postalservice/hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
    }
}
