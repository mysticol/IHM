/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import pojos.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author Manou
 */
public class HibernateUtil<E> {

    private static final SessionFactory sessionFactory;
    private Class<E> ref;
    private static HashMap<Class, String> bibIdTable = new HashMap<Class, String>();

    static {
        bibIdTable.put(Client.class, "clientId");
        bibIdTable.put(Hotel.class, "hotelId");
        bibIdTable.put(Localisation.class, "localisationId");
        bibIdTable.put(Manifestation.class, "manifestationId");
        bibIdTable.put(Reservation.class, "reservationId");
        bibIdTable.put(ReservationHotel.class, "reservationHotelId");
        bibIdTable.put(ReservationManif.class, "reservationManifId");
        bibIdTable.put(ReservationRestau.class, "reservationRestauId");
        bibIdTable.put(Restaurant.class, "restaurantId");
        bibIdTable.put(Voyage.class, "voyageId");

    }

    public HibernateUtil(Class<E> d) {
        ref = d;

    }

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public LinkedList<E> findAll() {

        Session session = getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(ref);

        LinkedList<E> result = new LinkedList<E>(criteria.list());
        session.close();



        return result;




    }

    public E findById(int id) {
        Session session = getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(ref).add(Restrictions.naturalId().set(this.bibIdTable.get(ref), id));

        return (E) criteria.uniqueResult();
    }

    public void delete(int id) {
        Session session = getSessionFactory().openSession();
        session.delete(findById(id));
        session.flush();
        session.close();


    }

    public void insert(E object) {



        Session session = getSessionFactory().openSession();



        session.save(object);
        session.flush();
        session.close();


    }

    public void update(E object) {
        Session session = getSessionFactory().openSession();
        session.update(object);
        session.flush();
        session.close();


    }

    public void insertOrUpdate(E Object) {

        try {
            Method mm = Object.getClass().getMethod(contructGetId(bibIdTable.get(Object.getClass())));


            Integer id = (Integer) mm.invoke(Object, new Object[0]);
            if (id == null || id == 0) {
                this.insert(Object);
            } else {
                this.update(Object);
            }



        } catch (Exception e) {
            try {
                this.insert(Object);

            } catch (Exception r) {
                this.update(Object);
            }


        }
    }

    private String contructGetId(String name) {


        return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);


    }

    public Class<E> getRef() {
        return ref;
    }

    

}
