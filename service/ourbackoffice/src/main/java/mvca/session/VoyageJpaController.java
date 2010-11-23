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
import mvca.entity.Client;
import mvca.entity.Reservation;
import java.util.ArrayList;
import java.util.List;
import mvca.entity.Voyage;
import mvca.session.exceptions.NonexistentEntityException;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Manou
 */
public class VoyageJpaController {

    public VoyageJpaController() {
        emf = Persistence.createEntityManagerFactory("mvca_ourbackoffice_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Voyage voyage) {
        if (voyage.getReservationList() == null) {
            voyage.setReservationList(new ArrayList<Reservation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Localisation localisation = voyage.getLocalisation();
            if (localisation != null) {
                localisation = em.getReference(localisation.getClass(), localisation.getLocalisationId());
                voyage.setLocalisation(localisation);
            }
            Client client = voyage.getClient();
            if (client != null) {
                client = em.getReference(client.getClass(), client.getClientId());
                voyage.setClient(client);
            }
            List<Reservation> attachedReservationList = new ArrayList<Reservation>();
            for (Reservation reservationListReservationToAttach : voyage.getReservationList()) {
                reservationListReservationToAttach = em.getReference(reservationListReservationToAttach.getClass(), reservationListReservationToAttach.getReservationId());
                attachedReservationList.add(reservationListReservationToAttach);
            }
            voyage.setReservationList(attachedReservationList);
            em.persist(voyage);
            if (localisation != null) {
                localisation.getVoyageList().add(voyage);
                localisation = em.merge(localisation);
            }
            if (client != null) {
                client.getVoyageList().add(voyage);
                client = em.merge(client);
            }
            for (Reservation reservationListReservation : voyage.getReservationList()) {
                Voyage oldVoyageOfReservationListReservation = reservationListReservation.getVoyage();
                reservationListReservation.setVoyage(voyage);
                reservationListReservation = em.merge(reservationListReservation);
                if (oldVoyageOfReservationListReservation != null) {
                    oldVoyageOfReservationListReservation.getReservationList().remove(reservationListReservation);
                    oldVoyageOfReservationListReservation = em.merge(oldVoyageOfReservationListReservation);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Voyage voyage) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Voyage persistentVoyage = em.find(Voyage.class, voyage.getVoyageId());
            Localisation localisationOld = persistentVoyage.getLocalisation();
            Localisation localisationNew = voyage.getLocalisation();
            Client clientOld = persistentVoyage.getClient();
            Client clientNew = voyage.getClient();
            List<Reservation> reservationListOld = persistentVoyage.getReservationList();
            List<Reservation> reservationListNew = voyage.getReservationList();
            if (localisationNew != null) {
                localisationNew = em.getReference(localisationNew.getClass(), localisationNew.getLocalisationId());
                voyage.setLocalisation(localisationNew);
            }
            if (clientNew != null) {
                clientNew = em.getReference(clientNew.getClass(), clientNew.getClientId());
                voyage.setClient(clientNew);
            }
            List<Reservation> attachedReservationListNew = new ArrayList<Reservation>();
            for (Reservation reservationListNewReservationToAttach : reservationListNew) {
                reservationListNewReservationToAttach = em.getReference(reservationListNewReservationToAttach.getClass(), reservationListNewReservationToAttach.getReservationId());
                attachedReservationListNew.add(reservationListNewReservationToAttach);
            }
            reservationListNew = attachedReservationListNew;
            voyage.setReservationList(reservationListNew);
            voyage = em.merge(voyage);
            if (localisationOld != null && !localisationOld.equals(localisationNew)) {
                localisationOld.getVoyageList().remove(voyage);
                localisationOld = em.merge(localisationOld);
            }
            if (localisationNew != null && !localisationNew.equals(localisationOld)) {
                localisationNew.getVoyageList().add(voyage);
                localisationNew = em.merge(localisationNew);
            }
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getVoyageList().remove(voyage);
                clientOld = em.merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getVoyageList().add(voyage);
                clientNew = em.merge(clientNew);
            }
            for (Reservation reservationListOldReservation : reservationListOld) {
                if (!reservationListNew.contains(reservationListOldReservation)) {
                    reservationListOldReservation.setVoyage(null);
                    reservationListOldReservation = em.merge(reservationListOldReservation);
                }
            }
            for (Reservation reservationListNewReservation : reservationListNew) {
                if (!reservationListOld.contains(reservationListNewReservation)) {
                    Voyage oldVoyageOfReservationListNewReservation = reservationListNewReservation.getVoyage();
                    reservationListNewReservation.setVoyage(voyage);
                    reservationListNewReservation = em.merge(reservationListNewReservation);
                    if (oldVoyageOfReservationListNewReservation != null && !oldVoyageOfReservationListNewReservation.equals(voyage)) {
                        oldVoyageOfReservationListNewReservation.getReservationList().remove(reservationListNewReservation);
                        oldVoyageOfReservationListNewReservation = em.merge(oldVoyageOfReservationListNewReservation);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = voyage.getVoyageId();
                if (findVoyage(id) == null) {
                    throw new NonexistentEntityException("The voyage with id " + id + " no longer exists.");
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
            Voyage voyage;
            try {
                voyage = em.getReference(Voyage.class, id);
                voyage.getVoyageId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The voyage with id " + id + " no longer exists.", enfe);
            }
            Localisation localisation = voyage.getLocalisation();
            if (localisation != null) {
                localisation.getVoyageList().remove(voyage);
                localisation = em.merge(localisation);
            }
            Client client = voyage.getClient();
            if (client != null) {
                client.getVoyageList().remove(voyage);
                client = em.merge(client);
            }
            List<Reservation> reservationList = voyage.getReservationList();
            for (Reservation reservationListReservation : reservationList) {
                reservationListReservation.setVoyage(null);
                reservationListReservation = em.merge(reservationListReservation);
            }
            em.remove(voyage);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Voyage> findVoyageEntities() {
        return findVoyageEntities(true, -1, -1);
    }

    public List<Voyage> findVoyageEntities(int maxResults, int firstResult) {
        return findVoyageEntities(false, maxResults, firstResult);
    }

    private List<Voyage> findVoyageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
          Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Voyage.class);

            if (!all) {
                criteria.setMaxResults(maxResults);
                criteria.setFirstResult(firstResult);
            }
            return criteria.list();
        } finally {
            em.close();
        }
    }

    public Voyage findVoyage(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Voyage.class, id);
        } finally {
            em.close();
        }
    }

    public int getVoyageCount() {
        EntityManager em = getEntityManager();
        try {
           Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Voyage.class);

       
            return criteria.list().size();
        } finally {
            em.close();
        }
    }

}
