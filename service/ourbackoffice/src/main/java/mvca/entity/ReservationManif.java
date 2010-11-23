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
@Table(name = "RESERVATION_MANIF", catalog = "", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "ReservationManif.findAll", query = "SELECT r FROM ReservationManif r"),
    @NamedQuery(name = "ReservationManif.findByReservationManifId", query = "SELECT r FROM ReservationManif r WHERE r.reservationManifId = :reservationManifId"),
    @NamedQuery(name = "ReservationManif.findByDate", query = "SELECT r FROM ReservationManif r WHERE r.date = :date")})
public class ReservationManif implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RESERVATION_MANIF_ID", nullable = false)
    private Integer reservationManifId;
    @Column(name = "DATE")
    @Temporal(TemporalType.TIME)
    private Date date;
    @JoinColumn(name = "FK_ID_MANIF", referencedColumnName = "MANIFESTATION_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Manifestation manifestation;
    @JoinColumn(name = "FK_ID_CLIENT", referencedColumnName = "CLIENT_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;
    @OneToMany(mappedBy = "reservationManif", fetch = FetchType.EAGER)
    private List<Reservation> reservationList;

    public ReservationManif() {
    }

    public ReservationManif(Integer reservationManifId) {
        this.reservationManifId = reservationManifId;
    }

    public Integer getReservationManifId() {
        return reservationManifId;
    }

    public void setReservationManifId(Integer reservationManifId) {
        this.reservationManifId = reservationManifId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Manifestation getManifestation() {
        return manifestation;
    }

    public void setManifestation(Manifestation manifestation) {
        this.manifestation = manifestation;
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
        hash += (reservationManifId != null ? reservationManifId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservationManif)) {
            return false;
        }
        ReservationManif other = (ReservationManif) object;
        if ((this.reservationManifId == null && other.reservationManifId != null) || (this.reservationManifId != null && !this.reservationManifId.equals(other.reservationManifId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvca.entity.ReservationManif[reservationManifId=" + reservationManifId + "]";
    }

}
