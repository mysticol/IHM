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
import java.util.ArrayList;
import java.util.List;
import mvca.entity.TypeManifestation;
import mvca.session.exceptions.NonexistentEntityException;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Manou
 */
public class TypeManifestationJpaController {

    public TypeManifestationJpaController() {
        emf = Persistence.createEntityManagerFactory("mvca_ourbackoffice_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TypeManifestation typeManifestation) {
        if (typeManifestation.getManifestationList() == null) {
            typeManifestation.setManifestationList(new ArrayList<Manifestation>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Manifestation> attachedManifestationList = new ArrayList<Manifestation>();
            for (Manifestation manifestationListManifestationToAttach : typeManifestation.getManifestationList()) {
                manifestationListManifestationToAttach = em.getReference(manifestationListManifestationToAttach.getClass(), manifestationListManifestationToAttach.getManifestationId());
                attachedManifestationList.add(manifestationListManifestationToAttach);
            }
            typeManifestation.setManifestationList(attachedManifestationList);
            em.persist(typeManifestation);
            for (Manifestation manifestationListManifestation : typeManifestation.getManifestationList()) {
                TypeManifestation oldTypeManifestationOfManifestationListManifestation = manifestationListManifestation.getTypeManifestation();
                manifestationListManifestation.setTypeManifestation(typeManifestation);
                manifestationListManifestation = em.merge(manifestationListManifestation);
                if (oldTypeManifestationOfManifestationListManifestation != null) {
                    oldTypeManifestationOfManifestationListManifestation.getManifestationList().remove(manifestationListManifestation);
                    oldTypeManifestationOfManifestationListManifestation = em.merge(oldTypeManifestationOfManifestationListManifestation);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TypeManifestation typeManifestation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TypeManifestation persistentTypeManifestation = em.find(TypeManifestation.class, typeManifestation.getTypeId());
            List<Manifestation> manifestationListOld = persistentTypeManifestation.getManifestationList();
            List<Manifestation> manifestationListNew = typeManifestation.getManifestationList();
            List<Manifestation> attachedManifestationListNew = new ArrayList<Manifestation>();
            for (Manifestation manifestationListNewManifestationToAttach : manifestationListNew) {
                manifestationListNewManifestationToAttach = em.getReference(manifestationListNewManifestationToAttach.getClass(), manifestationListNewManifestationToAttach.getManifestationId());
                attachedManifestationListNew.add(manifestationListNewManifestationToAttach);
            }
            manifestationListNew = attachedManifestationListNew;
            typeManifestation.setManifestationList(manifestationListNew);
            typeManifestation = em.merge(typeManifestation);
            for (Manifestation manifestationListOldManifestation : manifestationListOld) {
                if (!manifestationListNew.contains(manifestationListOldManifestation)) {
                    manifestationListOldManifestation.setTypeManifestation(null);
                    manifestationListOldManifestation = em.merge(manifestationListOldManifestation);
                }
            }
            for (Manifestation manifestationListNewManifestation : manifestationListNew) {
                if (!manifestationListOld.contains(manifestationListNewManifestation)) {
                    TypeManifestation oldTypeManifestationOfManifestationListNewManifestation = manifestationListNewManifestation.getTypeManifestation();
                    manifestationListNewManifestation.setTypeManifestation(typeManifestation);
                    manifestationListNewManifestation = em.merge(manifestationListNewManifestation);
                    if (oldTypeManifestationOfManifestationListNewManifestation != null && !oldTypeManifestationOfManifestationListNewManifestation.equals(typeManifestation)) {
                        oldTypeManifestationOfManifestationListNewManifestation.getManifestationList().remove(manifestationListNewManifestation);
                        oldTypeManifestationOfManifestationListNewManifestation = em.merge(oldTypeManifestationOfManifestationListNewManifestation);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = typeManifestation.getTypeId();
                if (findTypeManifestation(id) == null) {
                    throw new NonexistentEntityException("The typeManifestation with id " + id + " no longer exists.");
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
            TypeManifestation typeManifestation;
            try {
                typeManifestation = em.getReference(TypeManifestation.class, id);
                typeManifestation.getTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The typeManifestation with id " + id + " no longer exists.", enfe);
            }
            List<Manifestation> manifestationList = typeManifestation.getManifestationList();
            for (Manifestation manifestationListManifestation : manifestationList) {
                manifestationListManifestation.setTypeManifestation(null);
                manifestationListManifestation = em.merge(manifestationListManifestation);
            }
            em.remove(typeManifestation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TypeManifestation> findTypeManifestationEntities() {
        return findTypeManifestationEntities(true, -1, -1);
    }

    public List<TypeManifestation> findTypeManifestationEntities(int maxResults, int firstResult) {
        return findTypeManifestationEntities(false, maxResults, firstResult);
    }

    private List<TypeManifestation> findTypeManifestationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
             Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(TypeManifestation.class);

            if (!all) {
                criteria.setMaxResults(maxResults);
                criteria.setFirstResult(firstResult);
            }
            return criteria.list();
        } finally {
            em.close();
        }
    }

    public TypeManifestation findTypeManifestation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TypeManifestation.class, id);
        } finally {
            em.close();
        }
    }

    public int getTypeManifestationCount() {
        EntityManager em = getEntityManager();
        try {
            Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(TypeManifestation.class);


            return criteria.list().size();
        } finally {
            em.close();
        }
    }

}
