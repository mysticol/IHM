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
import mvca.entity.Hotel;
import mvca.entity.Localisation;
import mvca.entity.ReservationHotel;
import java.util.ArrayList;
import java.util.List;
import mvca.session.exceptions.NonexistentEntityException;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Manou
 */
public class HotelJpaController {

    public HotelJpaController() {
        emf = Persistence.createEntityManagerFactory("mvca_ourbackoffice_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Hotel hotel) {
        if (hotel.getReservationHotelList() == null) {
            hotel.setReservationHotelList(new ArrayList<ReservationHotel>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Localisation localisation = hotel.getLocalisation();
            if (localisation != null) {
                localisation = em.getReference(localisation.getClass(), localisation.getLocalisationId());
                hotel.setLocalisation(localisation);
            }
            List<ReservationHotel> attachedReservationHotelList = new ArrayList<ReservationHotel>();
            for (ReservationHotel reservationHotelListReservationHotelToAttach : hotel.getReservationHotelList()) {
                reservationHotelListReservationHotelToAttach = em.getReference(reservationHotelListReservationHotelToAttach.getClass(), reservationHotelListReservationHotelToAttach.getReservationHotelId());
                attachedReservationHotelList.add(reservationHotelListReservationHotelToAttach);
            }
            hotel.setReservationHotelList(attachedReservationHotelList);
            em.persist(hotel);
            if (localisation != null) {
                localisation.getHotelList().add(hotel);
                localisation = em.merge(localisation);
            }
            for (ReservationHotel reservationHotelListReservationHotel : hotel.getReservationHotelList()) {
                Hotel oldHotelOfReservationHotelListReservationHotel = reservationHotelListReservationHotel.getHotel();
                reservationHotelListReservationHotel.setHotel(hotel);
                reservationHotelListReservationHotel = em.merge(reservationHotelListReservationHotel);
                if (oldHotelOfReservationHotelListReservationHotel != null) {
                    oldHotelOfReservationHotelListReservationHotel.getReservationHotelList().remove(reservationHotelListReservationHotel);
                    oldHotelOfReservationHotelListReservationHotel = em.merge(oldHotelOfReservationHotelListReservationHotel);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Hotel hotel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hotel persistentHotel = em.find(Hotel.class, hotel.getHotelId());
            Localisation localisationOld = persistentHotel.getLocalisation();
            Localisation localisationNew = hotel.getLocalisation();
            List<ReservationHotel> reservationHotelListOld = persistentHotel.getReservationHotelList();
            List<ReservationHotel> reservationHotelListNew = hotel.getReservationHotelList();
            if (localisationNew != null) {
                localisationNew = em.getReference(localisationNew.getClass(), localisationNew.getLocalisationId());
                hotel.setLocalisation(localisationNew);
            }
            List<ReservationHotel> attachedReservationHotelListNew = new ArrayList<ReservationHotel>();
            for (ReservationHotel reservationHotelListNewReservationHotelToAttach : reservationHotelListNew) {
                reservationHotelListNewReservationHotelToAttach = em.getReference(reservationHotelListNewReservationHotelToAttach.getClass(), reservationHotelListNewReservationHotelToAttach.getReservationHotelId());
                attachedReservationHotelListNew.add(reservationHotelListNewReservationHotelToAttach);
            }
            reservationHotelListNew = attachedReservationHotelListNew;
            hotel.setReservationHotelList(reservationHotelListNew);
            hotel = em.merge(hotel);
            if (localisationOld != null && !localisationOld.equals(localisationNew)) {
                localisationOld.getHotelList().remove(hotel);
                localisationOld = em.merge(localisationOld);
            }
            if (localisationNew != null && !localisationNew.equals(localisationOld)) {
                localisationNew.getHotelList().add(hotel);
                localisationNew = em.merge(localisationNew);
            }
            for (ReservationHotel reservationHotelListOldReservationHotel : reservationHotelListOld) {
                if (!reservationHotelListNew.contains(reservationHotelListOldReservationHotel)) {
                    reservationHotelListOldReservationHotel.setHotel(null);
                    reservationHotelListOldReservationHotel = em.merge(reservationHotelListOldReservationHotel);
                }
            }
            for (ReservationHotel reservationHotelListNewReservationHotel : reservationHotelListNew) {
                if (!reservationHotelListOld.contains(reservationHotelListNewReservationHotel)) {
                    Hotel oldHotelOfReservationHotelListNewReservationHotel = reservationHotelListNewReservationHotel.getHotel();
                    reservationHotelListNewReservationHotel.setHotel(hotel);
                    reservationHotelListNewReservationHotel = em.merge(reservationHotelListNewReservationHotel);
                    if (oldHotelOfReservationHotelListNewReservationHotel != null && !oldHotelOfReservationHotelListNewReservationHotel.equals(hotel)) {
                        oldHotelOfReservationHotelListNewReservationHotel.getReservationHotelList().remove(reservationHotelListNewReservationHotel);
                        oldHotelOfReservationHotelListNewReservationHotel = em.merge(oldHotelOfReservationHotelListNewReservationHotel);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hotel.getHotelId();
                if (findHotel(id) == null) {
                    throw new NonexistentEntityException("The hotel with id " + id + " no longer exists.");
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
            Hotel hotel;
            try {
                hotel = em.getReference(Hotel.class, id);
                hotel.getHotelId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hotel with id " + id + " no longer exists.", enfe);
            }
            Localisation localisation = hotel.getLocalisation();
            if (localisation != null) {
                localisation.getHotelList().remove(hotel);
                localisation = em.merge(localisation);
            }
            List<ReservationHotel> reservationHotelList = hotel.getReservationHotelList();
            for (ReservationHotel reservationHotelListReservationHotel : reservationHotelList) {
                reservationHotelListReservationHotel.setHotel(null);
                reservationHotelListReservationHotel = em.merge(reservationHotelListReservationHotel);
            }
            em.remove(hotel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Hotel> findHotelEntities() {
        return findHotelEntities(true, -1, -1);
    }

    public List<Hotel> findHotelEntities(int maxResults, int firstResult) {
        return findHotelEntities(false, maxResults, firstResult);
    }

    private List<Hotel> findHotelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
           Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Hotel.class);

            if (!all) {
                criteria.setMaxResults(maxResults);
                criteria.setFirstResult(firstResult);
            }
            return criteria.list();
        } finally {
            em.close();
        }
    }

    public Hotel findHotel(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Hotel.class, id);
        } finally {
            em.close();
        }
    }

    public int getHotelCount() {
        EntityManager em = getEntityManager();
        try {
              Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Hotel.class);

        
            return criteria.list().size();
        } finally {
            em.close();
        }
    }

}
