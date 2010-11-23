/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mvca.entity.ReservationRestau;
import mvca.entity.Restaurant;
import mvca.entity.Client;
import mvca.entity.Reservation;
import java.util.ArrayList;
import java.util.List;
import mvca.session.exceptions.NonexistentEntityException;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Manou
 */
public class ReservationRestauJpaController {

    public ReservationRestauJpaController() {
        emf = Persistence.createEntityManagerFactory("mvca_ourbackoffice_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReservationRestau reservationRestau) {
        if (reservationRestau.getReservationList() == null) {
            reservationRestau.setReservationList(new ArrayList<Reservation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Restaurant restaurant = reservationRestau.getRestaurant();
            if (restaurant != null) {
                restaurant = em.getReference(restaurant.getClass(), restaurant.getRestaurantId());
                reservationRestau.setRestaurant(restaurant);
            }
            Client client = reservationRestau.getClient();
            if (client != null) {
                client = em.getReference(client.getClass(), client.getClientId());
                reservationRestau.setClient(client);
            }
            List<Reservation> attachedReservationList = new ArrayList<Reservation>();
            for (Reservation reservationListReservationToAttach : reservationRestau.getReservationList()) {
                reservationListReservationToAttach = em.getReference(reservationListReservationToAttach.getClass(), reservationListReservationToAttach.getReservationId());
                attachedReservationList.add(reservationListReservationToAttach);
            }
            reservationRestau.setReservationList(attachedReservationList);
            em.persist(reservationRestau);
            if (restaurant != null) {
                restaurant.getReservationRestauList().add(reservationRestau);
                restaurant = em.merge(restaurant);
            }
            if (client != null) {
                client.getReservationRestauList().add(reservationRestau);
                client = em.merge(client);
            }
            for (Reservation reservationListReservation : reservationRestau.getReservationList()) {
                ReservationRestau oldReservationRestauOfReservationListReservation = reservationListReservation.getReservationRestau();
                reservationListReservation.setReservationRestau(reservationRestau);
                reservationListReservation = em.merge(reservationListReservation);
                if (oldReservationRestauOfReservationListReservation != null) {
                    oldReservationRestauOfReservationListReservation.getReservationList().remove(reservationListReservation);
                    oldReservationRestauOfReservationListReservation = em.merge(oldReservationRestauOfReservationListReservation);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReservationRestau reservationRestau) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReservationRestau persistentReservationRestau = em.find(ReservationRestau.class, reservationRestau.getReservationRestauId());
            Restaurant restaurantOld = persistentReservationRestau.getRestaurant();
            Restaurant restaurantNew = reservationRestau.getRestaurant();
            Client clientOld = persistentReservationRestau.getClient();
            Client clientNew = reservationRestau.getClient();
            List<Reservation> reservationListOld = persistentReservationRestau.getReservationList();
            List<Reservation> reservationListNew = reservationRestau.getReservationList();
            if (restaurantNew != null) {
                restaurantNew = em.getReference(restaurantNew.getClass(), restaurantNew.getRestaurantId());
                reservationRestau.setRestaurant(restaurantNew);
            }
            if (clientNew != null) {
                clientNew = em.getReference(clientNew.getClass(), clientNew.getClientId());
                reservationRestau.setClient(clientNew);
            }
            List<Reservation> attachedReservationListNew = new ArrayList<Reservation>();
            for (Reservation reservationListNewReservationToAttach : reservationListNew) {
                reservationListNewReservationToAttach = em.getReference(reservationListNewReservationToAttach.getClass(), reservationListNewReservationToAttach.getReservationId());
                attachedReservationListNew.add(reservationListNewReservationToAttach);
            }
            reservationListNew = attachedReservationListNew;
            reservationRestau.setReservationList(reservationListNew);
            reservationRestau = em.merge(reservationRestau);
            if (restaurantOld != null && !restaurantOld.equals(restaurantNew)) {
                restaurantOld.getReservationRestauList().remove(reservationRestau);
                restaurantOld = em.merge(restaurantOld);
            }
            if (restaurantNew != null && !restaurantNew.equals(restaurantOld)) {
                restaurantNew.getReservationRestauList().add(reservationRestau);
                restaurantNew = em.merge(restaurantNew);
            }
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getReservationRestauList().remove(reservationRestau);
                clientOld = em.merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getReservationRestauList().add(reservationRestau);
                clientNew = em.merge(clientNew);
            }
            for (Reservation reservationListOldReservation : reservationListOld) {
                if (!reservationListNew.contains(reservationListOldReservation)) {
                    reservationListOldReservation.setReservationRestau(null);
                    reservationListOldReservation = em.merge(reservationListOldReservation);
                }
            }
            for (Reservation reservationListNewReservation : reservationListNew) {
                if (!reservationListOld.contains(reservationListNewReservation)) {
                    ReservationRestau oldReservationRestauOfReservationListNewReservation = reservationListNewReservation.getReservationRestau();
                    reservationListNewReservation.setReservationRestau(reservationRestau);
                    reservationListNewReservation = em.merge(reservationListNewReservation);
                    if (oldReservationRestauOfReservationListNewReservation != null && !oldReservationRestauOfReservationListNewReservation.equals(reservationRestau)) {
                        oldReservationRestauOfReservationListNewReservation.getReservationList().remove(reservationListNewReservation);
                        oldReservationRestauOfReservationListNewReservation = em.merge(oldReservationRestauOfReservationListNewReservation);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reservationRestau.getReservationRestauId();
                if (findReservationRestau(id) == null) {
                    throw new NonexistentEntityException("The reservationRestau with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReservationRestau reservationRestau;
            try {
                reservationRestau = em.getReference(ReservationRestau.class, id);
                reservationRestau.getReservationRestauId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservationRestau with id " + id + " no longer exists.", enfe);
            }
            Restaurant restaurant = reservationRestau.getRestaurant();
            if (restaurant != null) {
                restaurant.getReservationRestauList().remove(reservationRestau);
                restaurant = em.merge(restaurant);
            }
            Client client = reservationRestau.getClient();
            if (client != null) {
                client.getReservationRestauList().remove(reservationRestau);
                client = em.merge(client);
            }
            List<Reservation> reservationList = reservationRestau.getReservationList();
            for (Reservation reservationListReservation : reservationList) {
                reservationListReservation.setReservationRestau(null);
                reservationListReservation = em.merge(reservationListReservation);
            }
            em.remove(reservationRestau);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReservationRestau> findReservationRestauEntities() {
        return findReservationRestauEntities(true, -1, -1);
    }

    public List<ReservationRestau> findReservationRestauEntities(int maxResults, int firstResult) {
        return findReservationRestauEntities(false, maxResults, firstResult);
    }

    private List<ReservationRestau> findReservationRestauEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
             Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(ReservationRestau.class);

            if (!all) {
                criteria.setMaxResults(maxResults);
                criteria.setFirstResult(firstResult);
            }
            return criteria.list();
        } finally {
            em.close();
        }
    }

    public ReservationRestau findReservationRestau(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReservationRestau.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservationRestauCount() {
        EntityManager em = getEntityManager();
        try {
             Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(ReservationRestau.class);


            return criteria.list().size();
        } finally {
            em.close();
        }
    }

}
