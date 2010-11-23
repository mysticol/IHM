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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Manou
 */
@Entity
@Table(name = "RESTAURANT", catalog = "", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "Restaurant.findAll", query = "SELECT r FROM Restaurant r"),
    @NamedQuery(name = "Restaurant.findByRestaurantId", query = "SELECT r FROM Restaurant r WHERE r.restaurantId = :restaurantId"),
    @NamedQuery(name = "Restaurant.findByAdresse", query = "SELECT r FROM Restaurant r WHERE r.adresse = :adresse"),
    @NamedQuery(name = "Restaurant.findByNomRestaurant", query = "SELECT r FROM Restaurant r WHERE r.nomRestaurant = :nomRestaurant"),
    @NamedQuery(name = "Restaurant.findByNbCouverts", query = "SELECT r FROM Restaurant r WHERE r.nbCouverts = :nbCouverts"),
    @NamedQuery(name = "Restaurant.findByRateRestaurant", query = "SELECT r FROM Restaurant r WHERE r.rateRestaurant = :rateRestaurant"),
    @NamedQuery(name = "Restaurant.findByPrix", query = "SELECT r FROM Restaurant r WHERE r.prix = :prix")})
public class Restaurant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RESTAURANT_ID", nullable = false)
    private Integer restaurantId;
    @Column(name = "ADRESSE", length = 30)
    private String adresse;
    @Column(name = "NOM_RESTAURANT", length = 20)
    private String nomRestaurant;
    @Column(name = "NB_COUVERTS")
    private Integer nbCouverts;
    @Column(name = "RATE_RESTAURANT")
    private Integer rateRestaurant;
    @Column(name = "PRIX")
    private Integer prix;
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private List<ReservationRestau> reservationRestauList;
    @JoinColumn(name = "FK_LOCALISATION_ID", referencedColumnName = "LOCALISATION_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Localisation localisation;

    public Restaurant() {
    }

    public Restaurant(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNomRestaurant() {
        return nomRestaurant;
    }

    public void setNomRestaurant(String nomRestaurant) {
        this.nomRestaurant = nomRestaurant;
    }

    public Integer getNbCouverts() {
        return nbCouverts;
    }

    public void setNbCouverts(Integer nbCouverts) {
        this.nbCouverts = nbCouverts;
    }

    public Integer getRateRestaurant() {
        return rateRestaurant;
    }

    public void setRateRestaurant(Integer rateRestaurant) {
        this.rateRestaurant = rateRestaurant;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public List<ReservationRestau> getReservationRestauList() {
        return reservationRestauList;
    }

    public void setReservationRestauList(List<ReservationRestau> reservationRestauList) {
        this.reservationRestauList = reservationRestauList;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restaurantId != null ? restaurantId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restaurant)) {
            return false;
        }
        Restaurant other = (Restaurant) object;
        if ((this.restaurantId == null && other.restaurantId != null) || (this.restaurantId != null && !this.restaurantId.equals(other.restaurantId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvca.entity.Restaurant[restaurantId=" + restaurantId + "]";
    }

}
