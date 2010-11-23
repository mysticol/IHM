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
import mvca.entity.Localisation;
import mvca.entity.ReservationRestau;
import java.util.ArrayList;
import java.util.List;
import mvca.entity.Restaurant;
import mvca.session.exceptions.NonexistentEntityException;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Manou
 */
public class RestaurantJpaController {

    public RestaurantJpaController() {
        emf = Persistence.createEntityManagerFactory("mvca_ourbackoffice_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Restaurant restaurant) {
        if (restaurant.getReservationRestauList() == null) {
            restaurant.setReservationRestauList(new ArrayList<ReservationRestau>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Localisation localisation = restaurant.getLocalisation();
            if (localisation != null) {
                localisation = em.getReference(localisation.getClass(), localisation.getLocalisationId());
                restaurant.setLocalisation(localisation);
            }
            List<ReservationRestau> attachedReservationRestauList = new ArrayList<ReservationRestau>();
            for (ReservationRestau reservationRestauListReservationRestauToAttach : restaurant.getReservationRestauList()) {
                reservationRestauListReservationRestauToAttach = em.getReference(reservationRestauListReservationRestauToAttach.getClass(), reservationRestauListReservationRestauToAttach.getReservationRestauId());
                attachedReservationRestauList.add(reservationRestauListReservationRestauToAttach);
            }
            restaurant.setReservationRestauList(attachedReservationRestauList);
            em.persist(restaurant);
            if (localisation != null) {
                localisation.getRestaurantList().add(restaurant);
                localisation = em.merge(localisation);
            }
            for (ReservationRestau reservationRestauListReservationRestau : restaurant.getReservationRestauList()) {
                Restaurant oldRestaurantOfReservationRestauListReservationRestau = reservationRestauListReservationRestau.getRestaurant();
                reservationRestauListReservationRestau.setRestaurant(restaurant);
                reservationRestauListReservationRestau = em.merge(reservationRestauListReservationRestau);
                if (oldRestaurantOfReservationRestauListReservationRestau != null) {
                    oldRestaurantOfReservationRestauListReservationRestau.getReservationRestauList().remove(reservationRestauListReservationRestau);
                    oldRestaurantOfReservationRestauListReservationRestau = em.merge(oldRestaurantOfReservationRestauListReservationRestau);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Restaurant restaurant) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Restaurant persistentRestaurant = em.find(Restaurant.class, restaurant.getRestaurantId());
            Localisation localisationOld = persistentRestaurant.getLocalisation();
            Localisation localisationNew = restaurant.getLocalisation();
            List<ReservationRestau> reservationRestauListOld = persistentRestaurant.getReservationRestauList();
            List<ReservationRestau> reservationRestauListNew = restaurant.getReservationRestauList();
            if (localisationNew != null) {
                localisationNew = em.getReference(localisationNew.getClass(), localisationNew.getLocalisationId());
                restaurant.setLocalisation(localisationNew);
            }
            List<ReservationRestau> attachedReservationRestauListNew = new ArrayList<ReservationRestau>();
            for (ReservationRestau reservationRestauListNewReservationRestauToAttach : reservationRestauListNew) {
                reservationRestauListNewReservationRestauToAttach = em.getReference(reservationRestauListNewReservationRestauToAttach.getClass(), reservationRestauListNewReservationRestauToAttach.getReservationRestauId());
                attachedReservationRestauListNew.add(reservationRestauListNewReservationRestauToAttach);
            }
            reservationRestauListNew = attachedReservationRestauListNew;
            restaurant.setReservationRestauList(reservationRestauListNew);
            restaurant = em.merge(restaurant);
            if (localisationOld != null && !localisationOld.equals(localisationNew)) {
                localisationOld.getRestaurantList().remove(restaurant);
                localisationOld = em.merge(localisationOld);
            }
            if (localisationNew != null && !localisationNew.equals(localisationOld)) {
                localisationNew.getRestaurantList().add(restaurant);
                localisationNew = em.merge(localisationNew);
            }
            for (ReservationRestau reservationRestauListOldReservationRestau : reservationRestauListOld) {
                if (!reservationRestauListNew.contains(reservationRestauListOldReservationRestau)) {
                    reservationRestauListOldReservationRestau.setRestaurant(null);
                    reservationRestauListOldReservationRestau = em.merge(reservationRestauListOldReservationRestau);
                }
            }
            for (ReservationRestau reservationRestauListNewReservationRestau : reservationRestauListNew) {
                if (!reservationRestauListOld.contains(reservationRestauListNewReservationRestau)) {
                    Restaurant oldRestaurantOfReservationRestauListNewReservationRestau = reservationRestauListNewReservationRestau.getRestaurant();
                    reservationRestauListNewReservationRestau.setRestaurant(restaurant);
                    reservationRestauListNewReservationRestau = em.merge(reservationRestauListNewReservationRestau);
                    if (oldRestaurantOfReservationRestauListNewReservationRestau != null && !oldRestaurantOfReservationRestauListNewReservationRestau.equals(restaurant)) {
                        oldRestaurantOfReservationRestauListNewReservationRestau.getReservationRestauList().remove(reservationRestauListNewReservationRestau);
                        oldRestaurantOfReservationRestauListNewReservationRestau = em.merge(oldRestaurantOfReservationRestauListNewReservationRestau);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = restaurant.getRestaurantId();
                if (findRestaurant(id) == null) {
                    throw new NonexistentEntityException("The restaurant with id " + id + " no longer exists.");
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
            Restaurant restaurant;
            try {
                restaurant = em.getReference(Restaurant.class, id);
                restaurant.getRestaurantId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The restaurant with id " + id + " no longer exists.", enfe);
            }
            Localisation localisation = restaurant.getLocalisation();
            if (localisation != null) {
                localisation.getRestaurantList().remove(restaurant);
                localisation = em.merge(localisation);
            }
            List<ReservationRestau> reservationRestauList = restaurant.getReservationRestauList();
            for (ReservationRestau reservationRestauListReservationRestau : reservationRestauList) {
                reservationRestauListReservationRestau.setRestaurant(null);
                reservationRestauListReservationRestau = em.merge(reservationRestauListReservationRestau);
            }
            em.remove(restaurant);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Restaurant> findRestaurantEntities() {
        return findRestaurantEntities(true, -1, -1);
    }

    public List<Restaurant> findRestaurantEntities(int maxResults, int firstResult) {
        return findRestaurantEntities(false, maxResults, firstResult);
    }

    private List<Restaurant> findRestaurantEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Restaurant.class);

            if (!all) {
                criteria.setMaxResults(maxResults);
                criteria.setFirstResult(firstResult);
            }
            return criteria.list();
        } finally {
            em.close();
        }
    }

    public Restaurant findRestaurant(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Restaurant.class, id);
        } finally {
            em.close();
        }
    }

    public int getRestaurantCount() {
        EntityManager em = getEntityManager();
        try {
           Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Restaurant.class);


            return criteria.list().size();
        } finally {
            em.close();
        }
    }

}
