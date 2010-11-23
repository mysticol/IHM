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
@Table(name = "HOTEL", catalog = "", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "Hotel.findAll", query = "SELECT h FROM Hotel h"),
    @NamedQuery(name = "Hotel.findByHotelId", query = "SELECT h FROM Hotel h WHERE h.hotelId = :hotelId"),
    @NamedQuery(name = "Hotel.findByNomHotel", query = "SELECT h FROM Hotel h WHERE h.nomHotel = :nomHotel"),
    @NamedQuery(name = "Hotel.findByAdresse", query = "SELECT h FROM Hotel h WHERE h.adresse = :adresse"),
    @NamedQuery(name = "Hotel.findByNbChambre", query = "SELECT h FROM Hotel h WHERE h.nbChambre = :nbChambre"),
    @NamedQuery(name = "Hotel.findByRankHotel", query = "SELECT h FROM Hotel h WHERE h.rankHotel = :rankHotel"),
    @NamedQuery(name = "Hotel.findByPrix", query = "SELECT h FROM Hotel h WHERE h.prix = :prix")})
public class Hotel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HOTEL_ID", nullable = false)
    private Integer hotelId;
    @Column(name = "NOM_HOTEL", length = 20)
    private String nomHotel;
    @Column(name = "ADRESSE", length = 30)
    private String adresse;
    @Column(name = "NB_CHAMBRE")
    private Integer nbChambre;
    @Column(name = "RANK_HOTEL")
    private Integer rankHotel;
    @Column(name = "PRIX")
    private Integer prix;
    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<ReservationHotel> reservationHotelList;
    @JoinColumn(name = "FK_LOCALISATION_ID", referencedColumnName = "LOCALISATION_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Localisation localisation;

    public Hotel() {
    }

    public Hotel(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getNomHotel() {
        return nomHotel;
    }

    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getNbChambre() {
        return nbChambre;
    }

    public void setNbChambre(Integer nbChambre) {
        this.nbChambre = nbChambre;
    }

    public Integer getRankHotel() {
        return rankHotel;
    }

    public void setRankHotel(Integer rankHotel) {
        this.rankHotel = rankHotel;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public List<ReservationHotel> getReservationHotelList() {
        return reservationHotelList;
    }

    public void setReservationHotelList(List<ReservationHotel> reservationHotelList) {
        this.reservationHotelList = reservationHotelList;
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
        hash += (hotelId != null ? hotelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotel)) {
            return false;
        }
        Hotel other = (Hotel) object;
        if ((this.hotelId == null && other.hotelId != null) || (this.hotelId != null && !this.hotelId.equals(other.hotelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvca.entity.Hotel[hotelId=" + hotelId + "]";
    }

}
