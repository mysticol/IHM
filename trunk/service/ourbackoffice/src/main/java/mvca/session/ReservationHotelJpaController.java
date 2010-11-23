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
import mvca.entity.Client;
import mvca.entity.Reservation;
import java.util.ArrayList;
import java.util.List;
import mvca.entity.ReservationHotel;
import mvca.session.exceptions.NonexistentEntityException;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Manou
 */
public class ReservationHotelJpaController {

    public ReservationHotelJpaController() {
        emf = Persistence.createEntityManagerFactory("mvca_ourbackoffice_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReservationHotel reservationHotel) {
        if (reservationHotel.getReservationList() == null) {
            reservationHotel.setReservationList(new ArrayList<Reservation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hotel hotel = reservationHotel.getHotel();
            if (hotel != null) {
                hotel = em.getReference(hotel.getClass(), hotel.getHotelId());
                reservationHotel.setHotel(hotel);
            }
            Client client = reservationHotel.getClient();
            if (client != null) {
                client = em.getReference(client.getClass(), client.getClientId());
                reservationHotel.setClient(client);
            }
            List<Reservation> attachedReservationList = new ArrayList<Reservation>();
            for (Reservation reservationListReservationToAttach : reservationHotel.getReservationList()) {
                reservationListReservationToAttach = em.getReference(reservationListReservationToAttach.getClass(), reservationListReservationToAttach.getReservationId());
                attachedReservationList.add(reservationListReservationToAttach);
            }
            reservationHotel.setReservationList(attachedReservationList);
            em.persist(reservationHotel);
            if (hotel != null) {
                hotel.getReservationHotelList().add(reservationHotel);
                hotel = em.merge(hotel);
            }
            if (client != null) {
                client.getReservationHotelList().add(reservationHotel);
                client = em.merge(client);
            }
            for (Reservation reservationListReservation : reservationHotel.getReservationList()) {
                ReservationHotel oldReservationHotelOfReservationListReservation = reservationListReservation.getReservationHotel();
                reservationListReservation.setReservationHotel(reservationHotel);
                reservationListReservation = em.merge(reservationListReservation);
                if (oldReservationHotelOfReservationListReservation != null) {
                    oldReservationHotelOfReservationListReservation.getReservationList().remove(reservationListReservation);
                    oldReservationHotelOfReservationListReservation = em.merge(oldReservationHotelOfReservationListReservation);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReservationHotel reservationHotel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReservationHotel persistentReservationHotel = em.find(ReservationHotel.class, reservationHotel.getReservationHotelId());
            Hotel hotelOld = persistentReservationHotel.getHotel();
            Hotel hotelNew = reservationHotel.getHotel();
            Client clientOld = persistentReservationHotel.getClient();
            Client clientNew = reservationHotel.getClient();
            List<Reservation> reservationListOld = persistentReservationHotel.getReservationList();
            List<Reservation> reservationListNew = reservationHotel.getReservationList();
            if (hotelNew != null) {
                hotelNew = em.getReference(hotelNew.getClass(), hotelNew.getHotelId());
                reservationHotel.setHotel(hotelNew);
            }
            if (clientNew != null) {
                clientNew = em.getReference(clientNew.getClass(), clientNew.getClientId());
                reservationHotel.setClient(clientNew);
            }
            List<Reservation> attachedReservationListNew = new ArrayList<Reservation>();
            for (Reservation reservationListNewReservationToAttach : reservationListNew) {
                reservationListNewReservationToAttach = em.getReference(reservationListNewReservationToAttach.getClass(), reservationListNewReservationToAttach.getReservationId());
                attachedReservationListNew.add(reservationListNewReservationToAttach);
            }
            reservationListNew = attachedReservationListNew;
            reservationHotel.setReservationList(reservationListNew);
            reservationHotel = em.merge(reservationHotel);
            if (hotelOld != null && !hotelOld.equals(hotelNew)) {
                hotelOld.getReservationHotelList().remove(reservationHotel);
                hotelOld = em.merge(hotelOld);
            }
            if (hotelNew != null && !hotelNew.equals(hotelOld)) {
                hotelNew.getReservationHotelList().add(reservationHotel);
                hotelNew = em.merge(hotelNew);
            }
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getReservationHotelList().remove(reservationHotel);
                clientOld = em.merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getReservationHotelList().add(reservationHotel);
                clientNew = em.merge(clientNew);
            }
            for (Reservation reservationListOldReservation : reservationListOld) {
                if (!reservationListNew.contains(reservationListOldReservation)) {
                    reservationListOldReservation.setReservationHotel(null);
                    reservationListOldReservation = em.merge(reservationListOldReservation);
                }
            }
            for (Reservation reservationListNewReservation : reservationListNew) {
                if (!reservationListOld.contains(reservationListNewReservation)) {
                    ReservationHotel oldReservationHotelOfReservationListNewReservation = reservationListNewReservation.getReservationHotel();
                    reservationListNewReservation.setReservationHotel(reservationHotel);
                    reservationListNewReservation = em.merge(reservationListNewReservation);
                    if (oldReservationHotelOfReservationListNewReservation != null && !oldReservationHotelOfReservationListNewReservation.equals(reservationHotel)) {
                        oldReservationHotelOfReservationListNewReservation.getReservationList().remove(reservationListNewReservation);
                        oldReservationHotelOfReservationListNewReservation = em.merge(oldReservationHotelOfReservationListNewReservation);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reservationHotel.getReservationHotelId();
                if (findReservationHotel(id) == null) {
                    throw new NonexistentEntityException("The reservationHotel with id " + id + " no longer exists.");
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
            ReservationHotel reservationHotel;
            try {
                reservationHotel = em.getReference(ReservationHotel.class, id);
                reservationHotel.getReservationHotelId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservationHotel with id " + id + " no longer exists.", enfe);
            }
            Hotel hotel = reservationHotel.getHotel();
            if (hotel != null) {
                hotel.getReservationHotelList().remove(reservationHotel);
                hotel = em.merge(hotel);
            }
            Client client = reservationHotel.getClient();
            if (client != null) {
                client.getReservationHotelList().remove(reservationHotel);
                client = em.merge(client);
            }
            List<Reservation> reservationList = reservationHotel.getReservationList();
            for (Reservation reservationListReservation : reservationList) {
                reservationListReservation.setReservationHotel(null);
                reservationListReservation = em.merge(reservationListReservation);
            }
            em.remove(reservationHotel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReservationHotel> findReservationHotelEntities() {
        return findReservationHotelEntities(true, -1, -1);
    }

    public List<ReservationHotel> findReservationHotelEntities(int maxResults, int firstResult) {
        return findReservationHotelEntities(false, maxResults, firstResult);
    }

    private List<ReservationHotel> findReservationHotelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            //OYOOOOO exemple by judu
            Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(ReservationHotel.class);
          if (!all) {
                criteria.setMaxResults(maxResults);
                criteria.setFirstResult(firstResult);
            }

            return criteria.list();
        } finally {
            em.close();
        }
    }

    public ReservationHotel findReservationHotel(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReservationHotel.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservationHotelCount() {
        EntityManager em = getEntityManager();
        try {

            Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(ReservationHotel.class);



            return criteria.list().size();
        } finally {
            em.close();
        }
    }

}
