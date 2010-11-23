/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Manou
 */
@Entity
@Table(name = "MANIFESTATION", catalog = "", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "Manifestation.findAll", query = "SELECT m FROM Manifestation m"),
    @NamedQuery(name = "Manifestation.findByManifestationId", query = "SELECT m FROM Manifestation m WHERE m.manifestationId = :manifestationId"),
    @NamedQuery(name = "Manifestation.findByNbPlace", query = "SELECT m FROM Manifestation m WHERE m.nbPlace = :nbPlace"),
    @NamedQuery(name = "Manifestation.findByNomManifestation", query = "SELECT m FROM Manifestation m WHERE m.nomManifestation = :nomManifestation"),
    @NamedQuery(name = "Manifestation.findByDate", query = "SELECT m FROM Manifestation m WHERE m.date = :date"),
    @NamedQuery(name = "Manifestation.findByAdresse", query = "SELECT m FROM Manifestation m WHERE m.adresse = :adresse"),
    @NamedQuery(name = "Manifestation.findByDescription", query = "SELECT m FROM Manifestation m WHERE m.description = :description"),
    @NamedQuery(name = "Manifestation.findByPrix", query = "SELECT m FROM Manifestation m WHERE m.prix = :prix")})
public class Manifestation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MANIFESTATION_ID", nullable = false)
    private Integer manifestationId;
    @Column(name = "NB_PLACE")
    private Integer nbPlace;
    @Column(name = "NOM_MANIFESTATION", length = 20)
    private String nomManifestation;
    @Column(name = "DATE")
    @Temporal(TemporalType.TIME)
    private Date date;
    @Column(name = "ADRESSE", length = 50)
    private String adresse;
    @Column(name = "DESCRIPTION", length = 80)
    private String description;
    @Column(name = "PRIX")
    private Integer prix;
    @JoinColumn(name = "FK_ID_TYPE", referencedColumnName = "TYPE_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private TypeManifestation typeManifestation;
    @JoinColumn(name = "FK_LOCALISATION_ID", referencedColumnName = "LOCALISATION_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Localisation localisation;
    @OneToMany(mappedBy = "manifestation", fetch = FetchType.EAGER)
    private List<ReservationManif> reservationManifList;

    public Manifestation() {
    }

    public Manifestation(Integer manifestationId) {
        this.manifestationId = manifestationId;
    }

    public Integer getManifestationId() {
        return manifestationId;
    }

    public void setManifestationId(Integer manifestationId) {
        this.manifestationId = manifestationId;
    }

    public Integer getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(Integer nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getNomManifestation() {
        return nomManifestation;
    }

    public void setNomManifestation(String nomManifestation) {
        this.nomManifestation = nomManifestation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public TypeManifestation getTypeManifestation() {
        return typeManifestation;
    }

    public void setTypeManifestation(TypeManifestation typeManifestation) {
        this.typeManifestation = typeManifestation;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public List<ReservationManif> getReservationManifList() {
        return reservationManifList;
    }

    public void setReservationManifList(List<ReservationManif> reservationManifList) {
        this.reservationManifList = reservationManifList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (manifestationId != null ? manifestationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manifestation)) {
            return false;
        }
        Manifestation other = (Manifestation) object;
        if ((this.manifestationId == null && other.manifestationId != null) || (this.manifestationId != null && !this.manifestationId.equals(other.manifestationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvca.entity.Manifestation[manifestationId=" + manifestationId + "]";
    }

}
