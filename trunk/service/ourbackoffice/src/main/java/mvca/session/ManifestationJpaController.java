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
import mvca.entity.TypeManifestation;
import mvca.entity.Localisation;
import mvca.entity.ReservationManif;
import java.util.ArrayList;
import java.util.List;
import mvca.session.exceptions.NonexistentEntityException;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Manou
 */
public class ManifestationJpaController {

    public ManifestationJpaController() {
        emf = Persistence.createEntityManagerFactory("mvca_ourbackoffice_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Manifestation manifestation) {
        if (manifestation.getReservationManifList() == null) {
            manifestation.setReservationManifList(new ArrayList<ReservationManif>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TypeManifestation typeManifestation = manifestation.getTypeManifestation();
            if (typeManifestation != null) {
                typeManifestation = em.getReference(typeManifestation.getClass(), typeManifestation.getTypeId());
                manifestation.setTypeManifestation(typeManifestation);
            }
            Localisation localisation = manifestation.getLocalisation();
            if (localisation != null) {
                localisation = em.getReference(localisation.getClass(), localisation.getLocalisationId());
                manifestation.setLocalisation(localisation);
            }
            List<ReservationManif> attachedReservationManifList = new ArrayList<ReservationManif>();
            for (ReservationManif reservationManifListReservationManifToAttach : manifestation.getReservationManifList()) {
                reservationManifListReservationManifToAttach = em.getReference(reservationManifListReservationManifToAttach.getClass(), reservationManifListReservationManifToAttach.getReservationManifId());
                attachedReservationManifList.add(reservationManifListReservationManifToAttach);
            }
            manifestation.setReservationManifList(attachedReservationManifList);
            em.persist(manifestation);
            if (typeManifestation != null) {
                typeManifestation.getManifestationList().add(manifestation);
                typeManifestation = em.merge(typeManifestation);
            }
            if (localisation != null) {
                localisation.getManifestationList().add(manifestation);
                localisation = em.merge(localisation);
            }
            for (ReservationManif reservationManifListReservationManif : manifestation.getReservationManifList()) {
                Manifestation oldManifestationOfReservationManifListReservationManif = reservationManifListReservationManif.getManifestation();
                reservationManifListReservationManif.setManifestation(manifestation);
                reservationManifListReservationManif = em.merge(reservationManifListReservationManif);
                if (oldManifestationOfReservationManifListReservationManif != null) {
                    oldManifestationOfReservationManifListReservationManif.getReservationManifList().remove(reservationManifListReservationManif);
                    oldManifestationOfReservationManifListReservationManif = em.merge(oldManifestationOfReservationManifListReservationManif);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Manifestation manifestation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Manifestation persistentManifestation = em.find(Manifestation.class, manifestation.getManifestationId());
            TypeManifestation typeManifestationOld = persistentManifestation.getTypeManifestation();
            TypeManifestation typeManifestationNew = manifestation.getTypeManifestation();
            Localisation localisationOld = persistentManifestation.getLocalisation();
            Localisation localisationNew = manifestation.getLocalisation();
            List<ReservationManif> reservationManifListOld = persistentManifestation.getReservationManifList();
            List<ReservationManif> reservationManifListNew = manifestation.getReservationManifList();
            if (typeManifestationNew != null) {
                typeManifestationNew = em.getReference(typeManifestationNew.getClass(), typeManifestationNew.getTypeId());
                manifestation.setTypeManifestation(typeManifestationNew);
            }
            if (localisationNew != null) {
                localisationNew = em.getReference(localisationNew.getClass(), localisationNew.getLocalisationId());
                manifestation.setLocalisation(localisationNew);
            }
            List<ReservationManif> attachedReservationManifListNew = new ArrayList<ReservationManif>();
            for (ReservationManif reservationManifListNewReservationManifToAttach : reservationManifListNew) {
                reservationManifListNewReservationManifToAttach = em.getReference(reservationManifListNewReservationManifToAttach.getClass(), reservationManifListNewReservationManifToAttach.getReservationManifId());
                attachedReservationManifListNew.add(reservationManifListNewReservationManifToAttach);
            }
            reservationManifListNew = attachedReservationManifListNew;
            manifestation.setReservationManifList(reservationManifListNew);
            manifestation = em.merge(manifestation);
            if (typeManifestationOld != null && !typeManifestationOld.equals(typeManifestationNew)) {
                typeManifestationOld.getManifestationList().remove(manifestation);
                typeManifestationOld = em.merge(typeManifestationOld);
            }
            if (typeManifestationNew != null && !typeManifestationNew.equals(typeManifestationOld)) {
                typeManifestationNew.getManifestationList().add(manifestation);
                typeManifestationNew = em.merge(typeManifestationNew);
            }
            if (localisationOld != null && !localisationOld.equals(localisationNew)) {
                localisationOld.getManifestationList().remove(manifestation);
                localisationOld = em.merge(localisationOld);
            }
            if (localisationNew != null && !localisationNew.equals(localisationOld)) {
                localisationNew.getManifestationList().add(manifestation);
                localisationNew = em.merge(localisationNew);
            }
            for (ReservationManif reservationManifListOldReservationManif : reservationManifListOld) {
                if (!reservationManifListNew.contains(reservationManifListOldReservationManif)) {
                    reservationManifListOldReservationManif.setManifestation(null);
                    reservationManifListOldReservationManif = em.merge(reservationManifListOldReservationManif);
                }
            }
            for (ReservationManif reservationManifListNewReservationManif : reservationManifListNew) {
                if (!reservationManifListOld.contains(reservationManifListNewReservationManif)) {
                    Manifestation oldManifestationOfReservationManifListNewReservationManif = reservationManifListNewReservationManif.getManifestation();
                    reservationManifListNewReservationManif.setManifestation(manifestation);
                    reservationManifListNewReservationManif = em.merge(reservationManifListNewReservationManif);
                    if (oldManifestationOfReservationManifListNewReservationManif != null && !oldManifestationOfReservationManifListNewReservationManif.equals(manifestation)) {
                        oldManifestationOfReservationManifListNewReservationManif.getReservationManifList().remove(reservationManifListNewReservationManif);
                        oldManifestationOfReservationManifListNewReservationManif = em.merge(oldManifestationOfReservationManifListNewReservationManif);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = manifestation.getManifestationId();
                if (findManifestation(id) == null) {
                    throw new NonexistentEntityException("The manifestation with id " + id + " no longer exists.");
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
            Manifestation manifestation;
            try {
                manifestation = em.getReference(Manifestation.class, id);
                manifestation.getManifestationId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The manifestation with id " + id + " no longer exists.", enfe);
            }
            TypeManifestation typeManifestation = manifestation.getTypeManifestation();
            if (typeManifestation != null) {
                typeManifestation.getManifestationList().remove(manifestation);
                typeManifestation = em.merge(typeManifestation);
            }
            Localisation localisation = manifestation.getLocalisation();
            if (localisation != null) {
                localisation.getManifestationList().remove(manifestation);
                localisation = em.merge(localisation);
            }
            List<ReservationManif> reservationManifList = manifestation.getReservationManifList();
            for (ReservationManif reservationManifListReservationManif : reservationManifList) {
                reservationManifListReservationManif.setManifestation(null);
                reservationManifListReservationManif = em.merge(reservationManifListReservationManif);
            }
            em.remove(manifestation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Manifestation> findManifestationEntities() {
        return findManifestationEntities(true, -1, -1);
    }

    public List<Manifestation> findManifestationEntities(int maxResults, int firstResult) {
        return findManifestationEntities(false, maxResults, firstResult);
    }

    private List<Manifestation> findManifestationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
               Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Manifestation.class);

            if (!all) {
                criteria.setMaxResults(maxResults);
                criteria.setFirstResult(firstResult);
            }
            return criteria.list();
        } finally {
            em.close();
        }
    }

    public Manifestation findManifestation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Manifestation.class, id);
        } finally {
            em.close();
        }
    }

    public int getManifestationCount() {
        EntityManager em = getEntityManager();
        try {
          Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Manifestation.class);

          
            return criteria.list().size();
        } finally {
            em.close();
        }
    }

}
