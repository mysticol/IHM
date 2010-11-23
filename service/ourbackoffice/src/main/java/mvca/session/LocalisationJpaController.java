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
import mvca.entity.Voyage;
import java.util.ArrayList;
import java.util.List;
import mvca.entity.Manifestation;
import mvca.entity.Restaurant;
import mvca.entity.Hotel;
import mvca.session.exceptions.NonexistentEntityException;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Manou
 */
public class LocalisationJpaController {

    public LocalisationJpaController() {
        emf = Persistence.createEntityManagerFactory("mvca_ourbackoffice_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Localisation localisation) {
        if (localisation.getVoyageList() == null) {
            localisation.setVoyageList(new ArrayList<Voyage>());
        }
        if (localisation.getManifestationList() == null) {
            localisation.setManifestationList(new ArrayList<Manifestation>());
        }
        if (localisation.getRestaurantList() == null) {
            localisation.setRestaurantList(new ArrayList<Restaurant>());
        }
        if (localisation.getHotelList() == null) {
            localisation.setHotelList(new ArrayList<Hotel>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Voyage> attachedVoyageList = new ArrayList<Voyage>();
            for (Voyage voyageListVoyageToAttach : localisation.getVoyageList()) {
                voyageListVoyageToAttach = em.getReference(voyageListVoyageToAttach.getClass(), voyageListVoyageToAttach.getVoyageId());
                attachedVoyageList.add(voyageListVoyageToAttach);
            }
            localisation.setVoyageList(attachedVoyageList);
            List<Manifestation> attachedManifestationList = new ArrayList<Manifestation>();
            for (Manifestation manifestationListManifestationToAttach : localisation.getManifestationList()) {
                manifestationListManifestationToAttach = em.getReference(manifestationListManifestationToAttach.getClass(), manifestationListManifestationToAttach.getManifestationId());
                attachedManifestationList.add(manifestationListManifestationToAttach);
            }
            localisation.setManifestationList(attachedManifestationList);
            List<Restaurant> attachedRestaurantList = new ArrayList<Restaurant>();
            for (Restaurant restaurantListRestaurantToAttach : localisation.getRestaurantList()) {
                restaurantListRestaurantToAttach = em.getReference(restaurantListRestaurantToAttach.getClass(), restaurantListRestaurantToAttach.getRestaurantId());
                attachedRestaurantList.add(restaurantListRestaurantToAttach);
            }
            localisation.setRestaurantList(attachedRestaurantList);
            List<Hotel> attachedHotelList = new ArrayList<Hotel>();
            for (Hotel hotelListHotelToAttach : localisation.getHotelList()) {
                hotelListHotelToAttach = em.getReference(hotelListHotelToAttach.getClass(), hotelListHotelToAttach.getHotelId());
                attachedHotelList.add(hotelListHotelToAttach);
            }
            localisation.setHotelList(attachedHotelList);
            em.persist(localisation);
            for (Voyage voyageListVoyage : localisation.getVoyageList()) {
                Localisation oldLocalisationOfVoyageListVoyage = voyageListVoyage.getLocalisation();
                voyageListVoyage.setLocalisation(localisation);
                voyageListVoyage = em.merge(voyageListVoyage);
                if (oldLocalisationOfVoyageListVoyage != null) {
                    oldLocalisationOfVoyageListVoyage.getVoyageList().remove(voyageListVoyage);
                    oldLocalisationOfVoyageListVoyage = em.merge(oldLocalisationOfVoyageListVoyage);
                }
            }
            for (Manifestation manifestationListManifestation : localisation.getManifestationList()) {
                Localisation oldLocalisationOfManifestationListManifestation = manifestationListManifestation.getLocalisation();
                manifestationListManifestation.setLocalisation(localisation);
                manifestationListManifestation = em.merge(manifestationListManifestation);
                if (oldLocalisationOfManifestationListManifestation != null) {
                    oldLocalisationOfManifestationListManifestation.getManifestationList().remove(manifestationListManifestation);
                    oldLocalisationOfManifestationListManifestation = em.merge(oldLocalisationOfManifestationListManifestation);
                }
            }
            for (Restaurant restaurantListRestaurant : localisation.getRestaurantList()) {
                Localisation oldLocalisationOfRestaurantListRestaurant = restaurantListRestaurant.getLocalisation();
                restaurantListRestaurant.setLocalisation(localisation);
                restaurantListRestaurant = em.merge(restaurantListRestaurant);
                if (oldLocalisationOfRestaurantListRestaurant != null) {
                    oldLocalisationOfRestaurantListRestaurant.getRestaurantList().remove(restaurantListRestaurant);
                    oldLocalisationOfRestaurantListRestaurant = em.merge(oldLocalisationOfRestaurantListRestaurant);
                }
            }
            for (Hotel hotelListHotel : localisation.getHotelList()) {
                Localisation oldLocalisationOfHotelListHotel = hotelListHotel.getLocalisation();
                hotelListHotel.setLocalisation(localisation);
                hotelListHotel = em.merge(hotelListHotel);
                if (oldLocalisationOfHotelListHotel != null) {
                    oldLocalisationOfHotelListHotel.getHotelList().remove(hotelListHotel);
                    oldLocalisationOfHotelListHotel = em.merge(oldLocalisationOfHotelListHotel);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Localisation localisation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Localisation persistentLocalisation = em.find(Localisation.class, localisation.getLocalisationId());
            List<Voyage> voyageListOld = persistentLocalisation.getVoyageList();
            List<Voyage> voyageListNew = localisation.getVoyageList();
            List<Manifestation> manifestationListOld = persistentLocalisation.getManifestationList();
            List<Manifestation> manifestationListNew = localisation.getManifestationList();
            List<Restaurant> restaurantListOld = persistentLocalisation.getRestaurantList();
            List<Restaurant> restaurantListNew = localisation.getRestaurantList();
            List<Hotel> hotelListOld = persistentLocalisation.getHotelList();
            List<Hotel> hotelListNew = localisation.getHotelList();
            List<Voyage> attachedVoyageListNew = new ArrayList<Voyage>();
            for (Voyage voyageListNewVoyageToAttach : voyageListNew) {
                voyageListNewVoyageToAttach = em.getReference(voyageListNewVoyageToAttach.getClass(), voyageListNewVoyageToAttach.getVoyageId());
                attachedVoyageListNew.add(voyageListNewVoyageToAttach);
            }
            voyageListNew = attachedVoyageListNew;
            localisation.setVoyageList(voyageListNew);
            List<Manifestation> attachedManifestationListNew = new ArrayList<Manifestation>();
            for (Manifestation manifestationListNewManifestationToAttach : manifestationListNew) {
                manifestationListNewManifestationToAttach = em.getReference(manifestationListNewManifestationToAttach.getClass(), manifestationListNewManifestationToAttach.getManifestationId());
                attachedManifestationListNew.add(manifestationListNewManifestationToAttach);
            }
            manifestationListNew = attachedManifestationListNew;
            localisation.setManifestationList(manifestationListNew);
            List<Restaurant> attachedRestaurantListNew = new ArrayList<Restaurant>();
            for (Restaurant restaurantListNewRestaurantToAttach : restaurantListNew) {
                restaurantListNewRestaurantToAttach = em.getReference(restaurantListNewRestaurantToAttach.getClass(), restaurantListNewRestaurantToAttach.getRestaurantId());
                attachedRestaurantListNew.add(restaurantListNewRestaurantToAttach);
            }
            restaurantListNew = attachedRestaurantListNew;
            localisation.setRestaurantList(restaurantListNew);
            List<Hotel> attachedHotelListNew = new ArrayList<Hotel>();
            for (Hotel hotelListNewHotelToAttach : hotelListNew) {
                hotelListNewHotelToAttach = em.getReference(hotelListNewHotelToAttach.getClass(), hotelListNewHotelToAttach.getHotelId());
                attachedHotelListNew.add(hotelListNewHotelToAttach);
            }
            hotelListNew = attachedHotelListNew;
            localisation.setHotelList(hotelListNew);
            localisation = em.merge(localisation);
            for (Voyage voyageListOldVoyage : voyageListOld) {
                if (!voyageListNew.contains(voyageListOldVoyage)) {
                    voyageListOldVoyage.setLocalisation(null);
                    voyageListOldVoyage = em.merge(voyageListOldVoyage);
                }
            }
            for (Voyage voyageListNewVoyage : voyageListNew) {
                if (!voyageListOld.contains(voyageListNewVoyage)) {
                    Localisation oldLocalisationOfVoyageListNewVoyage = voyageListNewVoyage.getLocalisation();
                    voyageListNewVoyage.setLocalisation(localisation);
                    voyageListNewVoyage = em.merge(voyageListNewVoyage);
                    if (oldLocalisationOfVoyageListNewVoyage != null && !oldLocalisationOfVoyageListNewVoyage.equals(localisation)) {
                        oldLocalisationOfVoyageListNewVoyage.getVoyageList().remove(voyageListNewVoyage);
                        oldLocalisationOfVoyageListNewVoyage = em.merge(oldLocalisationOfVoyageListNewVoyage);
                    }
                }
            }
            for (Manifestation manifestationListOldManifestation : manifestationListOld) {
                if (!manifestationListNew.contains(manifestationListOldManifestation)) {
                    manifestationListOldManifestation.setLocalisation(null);
                    manifestationListOldManifestation = em.merge(manifestationListOldManifestation);
                }
            }
            for (Manifestation manifestationListNewManifestation : manifestationListNew) {
                if (!manifestationListOld.contains(manifestationListNewManifestation)) {
                    Localisation oldLocalisationOfManifestationListNewManifestation = manifestationListNewManifestation.getLocalisation();
                    manifestationListNewManifestation.setLocalisation(localisation);
                    manifestationListNewManifestation = em.merge(manifestationListNewManifestation);
                    if (oldLocalisationOfManifestationListNewManifestation != null && !oldLocalisationOfManifestationListNewManifestation.equals(localisation)) {
                        oldLocalisationOfManifestationListNewManifestation.getManifestationList().remove(manifestationListNewManifestation);
                        oldLocalisationOfManifestationListNewManifestation = em.merge(oldLocalisationOfManifestationListNewManifestation);
                    }
                }
            }
            for (Restaurant restaurantListOldRestaurant : restaurantListOld) {
                if (!restaurantListNew.contains(restaurantListOldRestaurant)) {
                    restaurantListOldRestaurant.setLocalisation(null);
                    restaurantListOldRestaurant = em.merge(restaurantListOldRestaurant);
                }
            }
            for (Restaurant restaurantListNewRestaurant : restaurantListNew) {
                if (!restaurantListOld.contains(restaurantListNewRestaurant)) {
                    Localisation oldLocalisationOfRestaurantListNewRestaurant = restaurantListNewRestaurant.getLocalisation();
                    restaurantListNewRestaurant.setLocalisation(localisation);
                    restaurantListNewRestaurant = em.merge(restaurantListNewRestaurant);
                    if (oldLocalisationOfRestaurantListNewRestaurant != null && !oldLocalisationOfRestaurantListNewRestaurant.equals(localisation)) {
                        oldLocalisationOfRestaurantListNewRestaurant.getRestaurantList().remove(restaurantListNewRestaurant);
                        oldLocalisationOfRestaurantListNewRestaurant = em.merge(oldLocalisationOfRestaurantListNewRestaurant);
                    }
                }
            }
            for (Hotel hotelListOldHotel : hotelListOld) {
                if (!hotelListNew.contains(hotelListOldHotel)) {
                    hotelListOldHotel.setLocalisation(null);
                    hotelListOldHotel = em.merge(hotelListOldHotel);
                }
            }
            for (Hotel hotelListNewHotel : hotelListNew) {
                if (!hotelListOld.contains(hotelListNewHotel)) {
                    Localisation oldLocalisationOfHotelListNewHotel = hotelListNewHotel.getLocalisation();
                    hotelListNewHotel.setLocalisation(localisation);
                    hotelListNewHotel = em.merge(hotelListNewHotel);
                    if (oldLocalisationOfHotelListNewHotel != null && !oldLocalisationOfHotelListNewHotel.equals(localisation)) {
                        oldLocalisationOfHotelListNewHotel.getHotelList().remove(hotelListNewHotel);
                        oldLocalisationOfHotelListNewHotel = em.merge(oldLocalisationOfHotelListNewHotel);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = localisation.getLocalisationId();
                if (findLocalisation(id) == null) {
                    throw new NonexistentEntityException("The localisation with id " + id + " no longer exists.");
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
            Localisation localisation;
            try {
                localisation = em.getReference(Localisation.class, id);
                localisation.getLocalisationId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The localisation with id " + id + " no longer exists.", enfe);
            }
            List<Voyage> voyageList = localisation.getVoyageList();
            for (Voyage voyageListVoyage : voyageList) {
                voyageListVoyage.setLocalisation(null);
                voyageListVoyage = em.merge(voyageListVoyage);
            }
            List<Manifestation> manifestationList = localisation.getManifestationList();
            for (Manifestation manifestationListManifestation : manifestationList) {
                manifestationListManifestation.setLocalisation(null);
                manifestationListManifestation = em.merge(manifestationListManifestation);
            }
            List<Restaurant> restaurantList = localisation.getRestaurantList();
            for (Restaurant restaurantListRestaurant : restaurantList) {
                restaurantListRestaurant.setLocalisation(null);
                restaurantListRestaurant = em.merge(restaurantListRestaurant);
            }
            List<Hotel> hotelList = localisation.getHotelList();
            for (Hotel hotelListHotel : hotelList) {
                hotelListHotel.setLocalisation(null);
                hotelListHotel = em.merge(hotelListHotel);
            }
            em.remove(localisation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Localisation> findLocalisationEntities() {
        return findLocalisationEntities(true, -1, -1);
    }

    public List<Localisation> findLocalisationEntities(int maxResults, int firstResult) {
        return findLocalisationEntities(false, maxResults, firstResult);
    }

    private List<Localisation> findLocalisationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Localisation.class);

            if (!all) {
                criteria.setMaxResults(maxResults);
                criteria.setFirstResult(firstResult);
            }
            return criteria.list();
        } finally {
            em.close();
        }
    }

    public Localisation findLocalisation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Localisation.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalisationCount() {
        EntityManager em = getEntityManager();
        try {
            Session session = (Session) em.getDelegate();
            Criteria criteria = session.createCriteria(Localisation.class);


            return criteria.list().size();
        } finally {
            em.close();
        }
    }

}
