/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Manou
 */
@Entity
@Table(name = "LOCALISATION", catalog = "", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "Localisation.findAll", query = "SELECT l FROM Localisation l"),
    @NamedQuery(name = "Localisation.findByLocalisationId", query = "SELECT l FROM Localisation l WHERE l.localisationId = :localisationId"),
    @NamedQuery(name = "Localisation.findByPays", query = "SELECT l FROM Localisation l WHERE l.pays = :pays"),
    @NamedQuery(name = "Localisation.findByVille", query = "SELECT l FROM Localisation l WHERE l.ville = :ville")})
public class Localisation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LOCALISATION_ID", nullable = false)
    private Integer localisationId;
    @Column(name = "PAYS", length = 15)
    private String pays;
    @Column(name = "VILLE", length = 15)
    private String ville;
    @OneToMany(mappedBy = "localisation", fetch = FetchType.EAGER)
    private List<Voyage> voyageList;
    @OneToMany(mappedBy = "localisation", fetch = FetchType.EAGER)
    private List<Manifestation> manifestationList;
    @OneToMany(mappedBy = "localisation", fetch = FetchType.EAGER)
    private List<Restaurant> restaurantList;
    @OneToMany(mappedBy = "localisation", fetch = FetchType.EAGER)
    private List<Hotel> hotelList;

    public Localisation() {
    }

    public Localisation(Integer localisationId) {
        this.localisationId = localisationId;
    }

    public Integer getLocalisationId() {
        return localisationId;
    }

    public void setLocalisationId(Integer localisationId) {
        this.localisationId = localisationId;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<Voyage> getVoyageList() {
        return voyageList;
    }

    public void setVoyageList(List<Voyage> voyageList) {
        this.voyageList = voyageList;
    }

    public List<Manifestation> getManifestationList() {
        return manifestationList;
    }

    public void setManifestationList(List<Manifestation> manifestationList) {
        this.manifestationList = manifestationList;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (localisationId != null ? localisationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localisation)) {
            return false;
        }
        Localisation other = (Localisation) object;
        if ((this.localisationId == null && other.localisationId != null) || (this.localisationId != null && !this.localisationId.equals(other.localisationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvca.entity.Localisation[localisationId=" + localisationId + "]";
    }

}
