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
@Table(name = "VOYAGE", catalog = "", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "Voyage.findAll", query = "SELECT v FROM Voyage v"),
    @NamedQuery(name = "Voyage.findByVoyageId", query = "SELECT v FROM Voyage v WHERE v.voyageId = :voyageId"),
    @NamedQuery(name = "Voyage.findByFkLocalisationArrive", query = "SELECT v FROM Voyage v WHERE v.fkLocalisationArrive = :fkLocalisationArrive"),
    @NamedQuery(name = "Voyage.findByDate", query = "SELECT v FROM Voyage v WHERE v.date = :date")})
public class Voyage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VOYAGE_ID", nullable = false)
    private Integer voyageId;
    @Column(name = "FK_LOCALISATION_ARRIVE")
    private Integer fkLocalisationArrive;
    @Column(name = "DATE")
    @Temporal(TemporalType.TIME)
    private Date date;
    @JoinColumn(name = "FK_LOCALISATION_DEPART", referencedColumnName = "LOCALISATION_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Localisation localisation;
    @JoinColumn(name = "FK_CLIENT_ID", referencedColumnName = "CLIENT_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;
    @OneToMany(mappedBy = "voyage", fetch = FetchType.EAGER)
    private List<Reservation> reservationList;

    public Voyage() {
    }

    public Voyage(Integer voyageId) {
        this.voyageId = voyageId;
    }

    public Integer getVoyageId() {
        return voyageId;
    }

    public void setVoyageId(Integer voyageId) {
        this.voyageId = voyageId;
    }

    public Integer getFkLocalisationArrive() {
        return fkLocalisationArrive;
    }

    public void setFkLocalisationArrive(Integer fkLocalisationArrive) {
        this.fkLocalisationArrive = fkLocalisationArrive;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voyageId != null ? voyageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voyage)) {
            return false;
        }
        Voyage other = (Voyage) object;
        if ((this.voyageId == null && other.voyageId != null) || (this.voyageId != null && !this.voyageId.equals(other.voyageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvca.entity.Voyage[voyageId=" + voyageId + "]";
    }

}
