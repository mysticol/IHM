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
import mvca.entity.Manifestation;
import mvca.entity.Client;
import mvca.entity.Reservation;
import java.util.ArrayList;
import java.util.List;
import mvca.entity.ReservationManif;
import mvca.session.exceptions.NonexistentEntityException;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Manou
 */
public class ReservationManifJpaController {

    public ReservationManifJpaController() {
        emf = Persistence.createEntityManagerFactory("mvca_ourbackoffice_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReservationManif reservationManif) {
        if (reservationManif.getReservationList() == null) {
            reservationManif.setReservationList(new ArrayList<Reservation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Manifestation manifestation = reservationManif.getManifestation();
            if (manifestation != null) {
                manifestation = em.getReference(manifestation.getClass(), manifestation.getManifestationId());
                reservationManif.setManifestation(manifestation);
            }
            Client client = reservationManif.getClient();
            if (client != null) {
                client = em.getReference(client.getClass(), client.getClientId());
                reservationManif.setClient(client);
            }
            List<Reservation> attachedReservationList = new ArrayList<Reservation>();
            for (Reservation reservationListReservationToAttach : reservationManif.getReservationList()) {
                reservationListReservationToAttach = em.getReference(reservationListReservationToAttach.getClass(), reservationListReservationToAttach.getReservationId());
                attachedReservationList.add(reservationListReservationToAttach);
            }
            reservationManif.setReservationList(attachedReservationList);
            em.persist(reservationManif);
            if (manifestation != null) {
                manifestation.getReservationManifList().add(reservationManif);
                manifestation = em.merge(manifestation);
            }
            if (client != null) {
                client.getReservationManifList().add(reservationManif);
                client = em.merge(client);
            }
            for (Reservation reservationListReservation : reservationManif.getReservationList()) {
                ReservationManif oldReservationManifOfReservationListReservation = reservationListReservation.getReservationManif();
                reservationListReservation.setReservationManif(reservationManif);
                reservationListReservation = em.merge(reservationListReservation);
                if (oldReservationManifOfReservationListReservation != null) {
                    oldReservationManifOfReservationListReservation.getReservationList().remove(reservationListReservation);
                    oldReservationManifOfReservationListReservation = em.merge(oldReservationManifOfReservationListReservation);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReservationManif reservationManif) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReservationManif persistentReservationManif = em.find(ReservationManif.class, reservationManif.getReservationManifId());
            Manifestation manifestationOld = persistentReservationManif.getManifestation();
            Manifestation manifestationNew = reservationManif.getManifestation();
            Client clientOld = persistentReservationManif.getClient();
            Client clientNew = reservationManif.getClient();
            List<Reservation> reservationListOld = persistentReservationManif.getReservationList();
            List<Reservation> reservationListNew = reservationManif.getReservationList();
            if (manifestationNew != null) {
                manifestationNew = em.getReference(manifestationNew.getClass(), manifestationNew.getManifestationId());
                reservationManif.setManifestation(manifestationNew);
            }
            if (clientNew != null) {
                clientNew = em.getReference(clientNew.getClass(), clientNew.getClientId());
                reservationManif.setClient(clientNew);
            }
            List<Reservation> attachedReservationListNew = new ArrayList<Reservation>();
            for (Reservation reservationListNewReservationToAttach : reservationListNew) {
                reservationListNewReservationToAttach = em.getReference(reservationListNewReservationToAttach.getClass(), reservationListNewReservationToAttach.getReservationId());
                attachedReservationListNew.add(reservationListNewReservationToAttach);
            }
            reservationListNew = attachedReservationListNew;
            reservationManif.setReservationList(reservationListNew);
            reservationManif = em.merge(reservationManif);
            if (manifestationOld != null && !manifestationOld.equals(manifestationNew)) {
                manifestationOld.getReservationManifList().remove(reservationManif);
                manifestationOld = em.merge(manifestationOld);
            }
            if (manifestationNew != null && !manifestationNew.equals(manifestationOld)) {
                manifestationNew.getReservationManifList().add(reservationManif);
                manifestationNew = em.merge(manifestationNew);
            }
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getReservationManifList().remove(reservationManif);
                clientOld = em.merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getReservationManifList().add(reservationManif);
                clientNew = em.merge(clientNew);
            }
            for (Reservation reservationListOldReservation : reservationListOld) {
                if (!reservationListNew.contains(reservationListOldReservation)) {
                    reservationListOldReservation.setReservationManif(null);
                    reservationListOldReservation = em.merge(reservationListOldReservation);
                }
            }
            for (Reservation reservationListNewReservation : reservationListNew) {
                if (!reservationListOld.contains(reservationListNewReservation)) {
                    ReservationManif oldReservationManifOfReservationListNewReservation = reservationListNewReservation.getReservationManif();
                    reservationListNewReservation.setReservationManif(reservationManif);
                    reservationListNewReservation = em.merge(reservationListNewReservation);
                    if (oldReservationManifOfReservationListNewReservation != null && !oldReservationManifOfReservationListNewReservation.equals(reservationManif)) {
                        oldReservationManifOfReservationListNewReservation.getReservationList().remove(reservationListNewReservation);
                        oldReservationManifOfReservationListNewReservation = em.merge(oldReservationManifOfReservationListNewReservation);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reservationManif.getReservationManifId();
                if (findReservationManif(id) == null) {
                    throw new NonexistentEntityException("The reservationManif with id " + id + " no longer exists.");
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
            ReservationManif reservationManif;
            try {
                reservationManif = em.getReference(ReservationManif.class, id);
                reservationManif.getReservationManifId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservationManif with id " + id + " no longer exists.", enfe);
            }
            Manifestation manifestation = reservationManif.getManifestation();
            if (manifestation != null) {
                manifestation.getReservationManifList().remove(reservationManif);
                manifestation = em.merge(manifestation);
            }
            Client client = reservationManif.getClient();
            if (client != null) {
                client.getReservationManifList().remove(reservationManif);
                client = em.merge(client);
            }
            List<Reservation> reservationList = reservationManif.getReservationList();
            for (Reservation reservationListReservation : reservationList) {
                reservationListReservation.setReservationManif(null);
                reservationListReservation = em.merge(reservationListReservation);
            }
            em.remove(reservationManif);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReservationManif> findReservationManifEntities() {
        return findReservationManifEntities(true, -1, -1);
    }

    public List<ReservationManif> findReservationManifEntities(int maxResults, int firstResult) {
        return findReservationManifEntities(false, maxResults, firstResult);
    }

    private List<ReservationManif> findReservationManifEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
              Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(ReservationManif.class);

            if (!all) {
                criteria.setMaxResults(maxResults);
                criteria.setFirstResult(firstResult);
            }
            return criteria.list();
        } finally {
            em.close();
        }
    }

    public ReservationManif findReservationManif(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReservationManif.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservationManifCount() {
        EntityManager em = getEntityManager();
        try {
           Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(ReservationManif.class);


            return criteria.list().size();
        } finally {
            em.close();
        }
    }

}
