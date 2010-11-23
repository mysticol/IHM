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
import mvca.entity.Client;
import mvca.entity.Voyage;
import java.util.ArrayList;
import java.util.List;
import mvca.entity.ReservationHotel;
import mvca.entity.ReservationManif;
import mvca.entity.Reservation;
import mvca.entity.ReservationRestau;
import mvca.session.exceptions.NonexistentEntityException;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Manou
 */
public class ClientJpaController {

    public ClientJpaController() {
        emf = Persistence.createEntityManagerFactory("mvca_ourbackoffice_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Client client) {
        if (client.getVoyageList() == null) {
            client.setVoyageList(new ArrayList<Voyage>());
        }
        if (client.getReservationHotelList() == null) {
            client.setReservationHotelList(new ArrayList<ReservationHotel>());
        }
        if (client.getReservationManifList() == null) {
            client.setReservationManifList(new ArrayList<ReservationManif>());
        }
        if (client.getReservationList() == null) {
            client.setReservationList(new ArrayList<Reservation>());
        }
        if (client.getReservationRestauList() == null) {
            client.setReservationRestauList(new ArrayList<ReservationRestau>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Voyage> attachedVoyageList = new ArrayList<Voyage>();
            for (Voyage voyageListVoyageToAttach : client.getVoyageList()) {
                voyageListVoyageToAttach = em.getReference(voyageListVoyageToAttach.getClass(), voyageListVoyageToAttach.getVoyageId());
                attachedVoyageList.add(voyageListVoyageToAttach);
            }
            client.setVoyageList(attachedVoyageList);
            List<ReservationHotel> attachedReservationHotelList = new ArrayList<ReservationHotel>();
            for (ReservationHotel reservationHotelListReservationHotelToAttach : client.getReservationHotelList()) {
                reservationHotelListReservationHotelToAttach = em.getReference(reservationHotelListReservationHotelToAttach.getClass(), reservationHotelListReservationHotelToAttach.getReservationHotelId());
                attachedReservationHotelList.add(reservationHotelListReservationHotelToAttach);
            }
            client.setReservationHotelList(attachedReservationHotelList);
            List<ReservationManif> attachedReservationManifList = new ArrayList<ReservationManif>();
            for (ReservationManif reservationManifListReservationManifToAttach : client.getReservationManifList()) {
                reservationManifListReservationManifToAttach = em.getReference(reservationManifListReservationManifToAttach.getClass(), reservationManifListReservationManifToAttach.getReservationManifId());
                attachedReservationManifList.add(reservationManifListReservationManifToAttach);
            }
            client.setReservationManifList(attachedReservationManifList);
            List<Reservation> attachedReservationList = new ArrayList<Reservation>();
            for (Reservation reservationListReservationToAttach : client.getReservationList()) {
                reservationListReservationToAttach = em.getReference(reservationListReservationToAttach.getClass(), reservationListReservationToAttach.getReservationId());
                attachedReservationList.add(reservationListReservationToAttach);
            }
            client.setReservationList(attachedReservationList);
            List<ReservationRestau> attachedReservationRestauList = new ArrayList<ReservationRestau>();
            for (ReservationRestau reservationRestauListReservationRestauToAttach : client.getReservationRestauList()) {
                reservationRestauListReservationRestauToAttach = em.getReference(reservationRestauListReservationRestauToAttach.getClass(), reservationRestauListReservationRestauToAttach.getReservationRestauId());
                attachedReservationRestauList.add(reservationRestauListReservationRestauToAttach);
            }
            client.setReservationRestauList(attachedReservationRestauList);
            em.persist(client);
            for (Voyage voyageListVoyage : client.getVoyageList()) {
                Client oldClientOfVoyageListVoyage = voyageListVoyage.getClient();
                voyageListVoyage.setClient(client);
                voyageListVoyage = em.merge(voyageListVoyage);
                if (oldClientOfVoyageListVoyage != null) {
                    oldClientOfVoyageListVoyage.getVoyageList().remove(voyageListVoyage);
                    oldClientOfVoyageListVoyage = em.merge(oldClientOfVoyageListVoyage);
                }
            }
            for (ReservationHotel reservationHotelListReservationHotel : client.getReservationHotelList()) {
                Client oldClientOfReservationHotelListReservationHotel = reservationHotelListReservationHotel.getClient();
                reservationHotelListReservationHotel.setClient(client);
                reservationHotelListReservationHotel = em.merge(reservationHotelListReservationHotel);
                if (oldClientOfReservationHotelListReservationHotel != null) {
                    oldClientOfReservationHotelListReservationHotel.getReservationHotelList().remove(reservationHotelListReservationHotel);
                    oldClientOfReservationHotelListReservationHotel = em.merge(oldClientOfReservationHotelListReservationHotel);
                }
            }
            for (ReservationManif reservationManifListReservationManif : client.getReservationManifList()) {
                Client oldClientOfReservationManifListReservationManif = reservationManifListReservationManif.getClient();
                reservationManifListReservationManif.setClient(client);
                reservationManifListReservationManif = em.merge(reservationManifListReservationManif);
                if (oldClientOfReservationManifListReservationManif != null) {
                    oldClientOfReservationManifListReservationManif.getReservationManifList().remove(reservationManifListReservationManif);
                    oldClientOfReservationManifListReservationManif = em.merge(oldClientOfReservationManifListReservationManif);
                }
            }
            for (Reservation reservationListReservation : client.getReservationList()) {
                Client oldClientOfReservationListReservation = reservationListReservation.getClient();
                reservationListReservation.setClient(client);
                reservationListReservation = em.merge(reservationListReservation);
                if (oldClientOfReservationListReservation != null) {
                    oldClientOfReservationListReservation.getReservationList().remove(reservationListReservation);
                    oldClientOfReservationListReservation = em.merge(oldClientOfReservationListReservation);
                }
            }
            for (ReservationRestau reservationRestauListReservationRestau : client.getReservationRestauList()) {
                Client oldClientOfReservationRestauListReservationRestau = reservationRestauListReservationRestau.getClient();
                reservationRestauListReservationRestau.setClient(client);
                reservationRestauListReservationRestau = em.merge(reservationRestauListReservationRestau);
                if (oldClientOfReservationRestauListReservationRestau != null) {
                    oldClientOfReservationRestauListReservationRestau.getReservationRestauList().remove(reservationRestauListReservationRestau);
                    oldClientOfReservationRestauListReservationRestau = em.merge(oldClientOfReservationRestauListReservationRestau);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Client client) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Client persistentClient = em.find(Client.class, client.getClientId());
            List<Voyage> voyageListOld = persistentClient.getVoyageList();
            List<Voyage> voyageListNew = client.getVoyageList();
            List<ReservationHotel> reservationHotelListOld = persistentClient.getReservationHotelList();
            List<ReservationHotel> reservationHotelListNew = client.getReservationHotelList();
            List<ReservationManif> reservationManifListOld = persistentClient.getReservationManifList();
            List<ReservationManif> reservationManifListNew = client.getReservationManifList();
            List<Reservation> reservationListOld = persistentClient.getReservationList();
            List<Reservation> reservationListNew = client.getReservationList();
            List<ReservationRestau> reservationRestauListOld = persistentClient.getReservationRestauList();
            List<ReservationRestau> reservationRestauListNew = client.getReservationRestauList();
            List<Voyage> attachedVoyageListNew = new ArrayList<Voyage>();
            for (Voyage voyageListNewVoyageToAttach : voyageListNew) {
                voyageListNewVoyageToAttach = em.getReference(voyageListNewVoyageToAttach.getClass(), voyageListNewVoyageToAttach.getVoyageId());
                attachedVoyageListNew.add(voyageListNewVoyageToAttach);
            }
            voyageListNew = attachedVoyageListNew;
            client.setVoyageList(voyageListNew);
            List<ReservationHotel> attachedReservationHotelListNew = new ArrayList<ReservationHotel>();
            for (ReservationHotel reservationHotelListNewReservationHotelToAttach : reservationHotelListNew) {
                reservationHotelListNewReservationHotelToAttach = em.getReference(reservationHotelListNewReservationHotelToAttach.getClass(), reservationHotelListNewReservationHotelToAttach.getReservationHotelId());
                attachedReservationHotelListNew.add(reservationHotelListNewReservationHotelToAttach);
            }
            reservationHotelListNew = attachedReservationHotelListNew;
            client.setReservationHotelList(reservationHotelListNew);
            List<ReservationManif> attachedReservationManifListNew = new ArrayList<ReservationManif>();
            for (ReservationManif reservationManifListNewReservationManifToAttach : reservationManifListNew) {
                reservationManifListNewReservationManifToAttach = em.getReference(reservationManifListNewReservationManifToAttach.getClass(), reservationManifListNewReservationManifToAttach.getReservationManifId());
                attachedReservationManifListNew.add(reservationManifListNewReservationManifToAttach);
            }
            reservationManifListNew = attachedReservationManifListNew;
            client.setReservationManifList(reservationManifListNew);
            List<Reservation> attachedReservationListNew = new ArrayList<Reservation>();
            for (Reservation reservationListNewReservationToAttach : reservationListNew) {
                reservationListNewReservationToAttach = em.getReference(reservationListNewReservationToAttach.getClass(), reservationListNewReservationToAttach.getReservationId());
                attachedReservationListNew.add(reservationListNewReservationToAttach);
            }
            reservationListNew = attachedReservationListNew;
            client.setReservationList(reservationListNew);
            List<ReservationRestau> attachedReservationRestauListNew = new ArrayList<ReservationRestau>();
            for (ReservationRestau reservationRestauListNewReservationRestauToAttach : reservationRestauListNew) {
                reservationRestauListNewReservationRestauToAttach = em.getReference(reservationRestauListNewReservationRestauToAttach.getClass(), reservationRestauListNewReservationRestauToAttach.getReservationRestauId());
                attachedReservationRestauListNew.add(reservationRestauListNewReservationRestauToAttach);
            }
            reservationRestauListNew = attachedReservationRestauListNew;
            client.setReservationRestauList(reservationRestauListNew);
            client = em.merge(client);
            for (Voyage voyageListOldVoyage : voyageListOld) {
                if (!voyageListNew.contains(voyageListOldVoyage)) {
                    voyageListOldVoyage.setClient(null);
                    voyageListOldVoyage = em.merge(voyageListOldVoyage);
                }
            }
            for (Voyage voyageListNewVoyage : voyageListNew) {
                if (!voyageListOld.contains(voyageListNewVoyage)) {
                    Client oldClientOfVoyageListNewVoyage = voyageListNewVoyage.getClient();
                    voyageListNewVoyage.setClient(client);
                    voyageListNewVoyage = em.merge(voyageListNewVoyage);
                    if (oldClientOfVoyageListNewVoyage != null && !oldClientOfVoyageListNewVoyage.equals(client)) {
                        oldClientOfVoyageListNewVoyage.getVoyageList().remove(voyageListNewVoyage);
                        oldClientOfVoyageListNewVoyage = em.merge(oldClientOfVoyageListNewVoyage);
                    }
                }
            }
            for (ReservationHotel reservationHotelListOldReservationHotel : reservationHotelListOld) {
                if (!reservationHotelListNew.contains(reservationHotelListOldReservationHotel)) {
                    reservationHotelListOldReservationHotel.setClient(null);
                    reservationHotelListOldReservationHotel = em.merge(reservationHotelListOldReservationHotel);
                }
            }
            for (ReservationHotel reservationHotelListNewReservationHotel : reservationHotelListNew) {
                if (!reservationHotelListOld.contains(reservationHotelListNewReservationHotel)) {
                    Client oldClientOfReservationHotelListNewReservationHotel = reservationHotelListNewReservationHotel.getClient();
                    reservationHotelListNewReservationHotel.setClient(client);
                    reservationHotelListNewReservationHotel = em.merge(reservationHotelListNewReservationHotel);
                    if (oldClientOfReservationHotelListNewReservationHotel != null && !oldClientOfReservationHotelListNewReservationHotel.equals(client)) {
                        oldClientOfReservationHotelListNewReservationHotel.getReservationHotelList().remove(reservationHotelListNewReservationHotel);
                        oldClientOfReservationHotelListNewReservationHotel = em.merge(oldClientOfReservationHotelListNewReservationHotel);
                    }
                }
            }
            for (ReservationManif reservationManifListOldReservationManif : reservationManifListOld) {
                if (!reservationManifListNew.contains(reservationManifListOldReservationManif)) {
                    reservationManifListOldReservationManif.setClient(null);
                    reservationManifListOldReservationManif = em.merge(reservationManifListOldReservationManif);
                }
            }
            for (ReservationManif reservationManifListNewReservationManif : reservationManifListNew) {
                if (!reservationManifListOld.contains(reservationManifListNewReservationManif)) {
                    Client oldClientOfReservationManifListNewReservationManif = reservationManifListNewReservationManif.getClient();
                    reservationManifListNewReservationManif.setClient(client);
                    reservationManifListNewReservationManif = em.merge(reservationManifListNewReservationManif);
                    if (oldClientOfReservationManifListNewReservationManif != null && !oldClientOfReservationManifListNewReservationManif.equals(client)) {
                        oldClientOfReservationManifListNewReservationManif.getReservationManifList().remove(reservationManifListNewReservationManif);
                        oldClientOfReservationManifListNewReservationManif = em.merge(oldClientOfReservationManifListNewReservationManif);
                    }
                }
            }
            for (Reservation reservationListOldReservation : reservationListOld) {
                if (!reservationListNew.contains(reservationListOldReservation)) {
                    reservationListOldReservation.setClient(null);
                    reservationListOldReservation = em.merge(reservationListOldReservation);
                }
            }
            for (Reservation reservationListNewReservation : reservationListNew) {
                if (!reservationListOld.contains(reservationListNewReservation)) {
                    Client oldClientOfReservationListNewReservation = reservationListNewReservation.getClient();
                    reservationListNewReservation.setClient(client);
                    reservationListNewReservation = em.merge(reservationListNewReservation);
                    if (oldClientOfReservationListNewReservation != null && !oldClientOfReservationListNewReservation.equals(client)) {
                        oldClientOfReservationListNewReservation.getReservationList().remove(reservationListNewReservation);
                        oldClientOfReservationListNewReservation = em.merge(oldClientOfReservationListNewReservation);
                    }
                }
            }
            for (ReservationRestau reservationRestauListOldReservationRestau : reservationRestauListOld) {
                if (!reservationRestauListNew.contains(reservationRestauListOldReservationRestau)) {
                    reservationRestauListOldReservationRestau.setClient(null);
                    reservationRestauListOldReservationRestau = em.merge(reservationRestauListOldReservationRestau);
                }
            }
            for (ReservationRestau reservationRestauListNewReservationRestau : reservationRestauListNew) {
                if (!reservationRestauListOld.contains(reservationRestauListNewReservationRestau)) {
                    Client oldClientOfReservationRestauListNewReservationRestau = reservationRestauListNewReservationRestau.getClient();
                    reservationRestauListNewReservationRestau.setClient(client);
                    reservationRestauListNewReservationRestau = em.merge(reservationRestauListNewReservationRestau);
                    if (oldClientOfReservationRestauListNewReservationRestau != null && !oldClientOfReservationRestauListNewReservationRestau.equals(client)) {
                        oldClientOfReservationRestauListNewReservationRestau.getReservationRestauList().remove(reservationRestauListNewReservationRestau);
                        oldClientOfReservationRestauListNewReservationRestau = em.merge(oldClientOfReservationRestauListNewReservationRestau);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = client.getClientId();
                if (findClient(id) == null) {
                    throw new NonexistentEntityException("The client with id " + id + " no longer exists.");
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
            Client client;
            try {
                client = em.getReference(Client.class, id);
                client.getClientId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The client with id " + id + " no longer exists.", enfe);
            }
            List<Voyage> voyageList = client.getVoyageList();
            for (Voyage voyageListVoyage : voyageList) {
                voyageListVoyage.setClient(null);
                voyageListVoyage = em.merge(voyageListVoyage);
            }
            List<ReservationHotel> reservationHotelList = client.getReservationHotelList();
            for (ReservationHotel reservationHotelListReservationHotel : reservationHotelList) {
                reservationHotelListReservationHotel.setClient(null);
                reservationHotelListReservationHotel = em.merge(reservationHotelListReservationHotel);
            }
            List<ReservationManif> reservationManifList = client.getReservationManifList();
            for (ReservationManif reservationManifListReservationManif : reservationManifList) {
                reservationManifListReservationManif.setClient(null);
                reservationManifListReservationManif = em.merge(reservationManifListReservationManif);
            }
            List<Reservation> reservationList = client.getReservationList();
            for (Reservation reservationListReservation : reservationList) {
                reservationListReservation.setClient(null);
                reservationListReservation = em.merge(reservationListReservation);
            }
            List<ReservationRestau> reservationRestauList = client.getReservationRestauList();
            for (ReservationRestau reservationRestauListReservationRestau : reservationRestauList) {
                reservationRestauListReservationRestau.setClient(null);
                reservationRestauListReservationRestau = em.merge(reservationRestauListReservationRestau);
            }
            em.remove(client);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Client> findClientEntities() {
        return findClientEntities(true, -1, -1);
    }

    public List<Client> findClientEntities(int maxResults, int firstResult) {
        return findClientEntities(false, maxResults, firstResult);
    }

    private List<Client> findClientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {


           Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Client.class);

            if (!all) {
                criteria.setMaxResults(maxResults);
                criteria.setFirstResult(firstResult);
            }
            return criteria.list();
        } finally {
            em.close();
        }
    }

    public Client findClient(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Client.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientCount() {
        EntityManager em = getEntityManager();
        try {
             Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Client.class);

       
            return criteria.list().size();
            
        } finally {
            em.close();
        }
    }

}
