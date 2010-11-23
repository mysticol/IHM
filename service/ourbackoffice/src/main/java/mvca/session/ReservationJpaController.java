/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.session;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mvca.entity.Reservation;
import mvca.entity.Voyage;
import mvca.entity.ReservationRestau;
import mvca.entity.ReservationManif;
import mvca.entity.ReservationHotel;
import mvca.entity.Client;
import mvca.session.exceptions.NonexistentEntityException;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Manou
 */
public class ReservationJpaController {

    public ReservationJpaController() {
        emf = Persistence.createEntityManagerFactory("mvca_ourbackoffice_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reservation reservation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Voyage voyage = reservation.getVoyage();
            if (voyage != null) {
                voyage = em.getReference(voyage.getClass(), voyage.getVoyageId());
                reservation.setVoyage(voyage);
            }
            ReservationRestau reservationRestau = reservation.getReservationRestau();
            if (reservationRestau != null) {
                reservationRestau = em.getReference(reservationRestau.getClass(), reservationRestau.getReservationRestauId());
                reservation.setReservationRestau(reservationRestau);
            }
            ReservationManif reservationManif = reservation.getReservationManif();
            if (reservationManif != null) {
                reservationManif = em.getReference(reservationManif.getClass(), reservationManif.getReservationManifId());
                reservation.setReservationManif(reservationManif);
            }
            ReservationHotel reservationHotel = reservation.getReservationHotel();
            if (reservationHotel != null) {
                reservationHotel = em.getReference(reservationHotel.getClass(), reservationHotel.getReservationHotelId());
                reservation.setReservationHotel(reservationHotel);
            }
            Client client = reservation.getClient();
            if (client != null) {
                client = em.getReference(client.getClass(), client.getClientId());
                reservation.setClient(client);
            }
            em.persist(reservation);
            if (voyage != null) {
                voyage.getReservationList().add(reservation);
                voyage = em.merge(voyage);
            }
            if (reservationRestau != null) {
                reservationRestau.getReservationList().add(reservation);
                reservationRestau = em.merge(reservationRestau);
            }
            if (reservationManif != null) {
                reservationManif.getReservationList().add(reservation);
                reservationManif = em.merge(reservationManif);
            }
            if (reservationHotel != null) {
                reservationHotel.getReservationList().add(reservation);
                reservationHotel = em.merge(reservationHotel);
            }
            if (client != null) {
                client.getReservationList().add(reservation);
                client = em.merge(client);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reservation reservation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservation persistentReservation = em.find(Reservation.class, reservation.getReservationId());
            Voyage voyageOld = persistentReservation.getVoyage();
            Voyage voyageNew = reservation.getVoyage();
            ReservationRestau reservationRestauOld = persistentReservation.getReservationRestau();
            ReservationRestau reservationRestauNew = reservation.getReservationRestau();
            ReservationManif reservationManifOld = persistentReservation.getReservationManif();
            ReservationManif reservationManifNew = reservation.getReservationManif();
            ReservationHotel reservationHotelOld = persistentReservation.getReservationHotel();
            ReservationHotel reservationHotelNew = reservation.getReservationHotel();
            Client clientOld = persistentReservation.getClient();
            Client clientNew = reservation.getClient();
            if (voyageNew != null) {
                voyageNew = em.getReference(voyageNew.getClass(), voyageNew.getVoyageId());
                reservation.setVoyage(voyageNew);
            }
            if (reservationRestauNew != null) {
                reservationRestauNew = em.getReference(reservationRestauNew.getClass(), reservationRestauNew.getReservationRestauId());
                reservation.setReservationRestau(reservationRestauNew);
            }
            if (reservationManifNew != null) {
                reservationManifNew = em.getReference(reservationManifNew.getClass(), reservationManifNew.getReservationManifId());
                reservation.setReservationManif(reservationManifNew);
            }
            if (reservationHotelNew != null) {
                reservationHotelNew = em.getReference(reservationHotelNew.getClass(), reservationHotelNew.getReservationHotelId());
                reservation.setReservationHotel(reservationHotelNew);
            }
            if (clientNew != null) {
                clientNew = em.getReference(clientNew.getClass(), clientNew.getClientId());
                reservation.setClient(clientNew);
            }
            reservation = em.merge(reservation);
            if (voyageOld != null && !voyageOld.equals(voyageNew)) {
                voyageOld.getReservationList().remove(reservation);
                voyageOld = em.merge(voyageOld);
            }
            if (voyageNew != null && !voyageNew.equals(voyageOld)) {
                voyageNew.getReservationList().add(reservation);
                voyageNew = em.merge(voyageNew);
            }
            if (reservationRestauOld != null && !reservationRestauOld.equals(reservationRestauNew)) {
                reservationRestauOld.getReservationList().remove(reservation);
                reservationRestauOld = em.merge(reservationRestauOld);
            }
            if (reservationRestauNew != null && !reservationRestauNew.equals(reservationRestauOld)) {
                reservationRestauNew.getReservationList().add(reservation);
                reservationRestauNew = em.merge(reservationRestauNew);
            }
            if (reservationManifOld != null && !reservationManifOld.equals(reservationManifNew)) {
                reservationManifOld.getReservationList().remove(reservation);
                reservationManifOld = em.merge(reservationManifOld);
            }
            if (reservationManifNew != null && !reservationManifNew.equals(reservationManifOld)) {
                reservationManifNew.getReservationList().add(reservation);
                reservationManifNew = em.merge(reservationManifNew);
            }
            if (reservationHotelOld != null && !reservationHotelOld.equals(reservationHotelNew)) {
                reservationHotelOld.getReservationList().remove(reservation);
                reservationHotelOld = em.merge(reservationHotelOld);
            }
            if (reservationHotelNew != null && !reservationHotelNew.equals(reservationHotelOld)) {
                reservationHotelNew.getReservationList().add(reservation);
                reservationHotelNew = em.merge(reservationHotelNew);
            }
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getReservationList().remove(reservation);
                clientOld = em.merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getReservationList().add(reservation);
                clientNew = em.merge(clientNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reservation.getReservationId();
                if (findReservation(id) == null) {
                    throw new NonexistentEntityException("The reservation with id " + id + " no longer exists.");
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
            Reservation reservation;
            try {
                reservation = em.getReference(Reservation.class, id);
                reservation.getReservationId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservation with id " + id + " no longer exists.", enfe);
            }
            Voyage voyage = reservation.getVoyage();
            if (voyage != null) {
                voyage.getReservationList().remove(reservation);
                voyage = em.merge(voyage);
            }
            ReservationRestau reservationRestau = reservation.getReservationRestau();
            if (reservationRestau != null) {
                reservationRestau.getReservationList().remove(reservation);
                reservationRestau = em.merge(reservationRestau);
            }
            ReservationManif reservationManif = reservation.getReservationManif();
            if (reservationManif != null) {
                reservationManif.getReservationList().remove(reservation);
                reservationManif = em.merge(reservationManif);
            }
            ReservationHotel reservationHotel = reservation.getReservationHotel();
            if (reservationHotel != null) {
                reservationHotel.getReservationList().remove(reservation);
                reservationHotel = em.merge(reservationHotel);
            }
            Client client = reservation.getClient();
            if (client != null) {
                client.getReservationList().remove(reservation);
                client = em.merge(client);
            }
            em.remove(reservation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reservation> findReservationEntities() {
        return findReservationEntities(true, -1, -1);
    }

    public List<Reservation> findReservationEntities(int maxResults, int firstResult) {
        return findReservationEntities(false, maxResults, firstResult);
    }

    private List<Reservation> findReservationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
             Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Reservation.class);

            if (!all) {
                criteria.setMaxResults(maxResults);
                criteria.setFirstResult(firstResult);
            }
            return criteria.list();
        } finally {
            em.close();
        }
    }

    public Reservation findReservation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reservation.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservationCount() {
        EntityManager em = getEntityManager();
        try {
             Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Reservation.class);


            return criteria.list().size();
        } finally {
            em.close();
        }
    }

}
