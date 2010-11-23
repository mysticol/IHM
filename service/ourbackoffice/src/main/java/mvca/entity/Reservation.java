/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Manou
 */
@Entity
@Table(name = "RESERVATION", catalog = "", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByReservationId", query = "SELECT r FROM Reservation r WHERE r.reservationId = :reservationId"),
    @NamedQuery(name = "Reservation.findByDate", query = "SELECT r FROM Reservation r WHERE r.date = :date")})
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RESERVATION_ID", nullable = false)
    private Integer reservationId;
    @Column(name = "DATE")
    @Temporal(TemporalType.TIME)
    private Date date;
    @JoinColumn(name = "FK_VOYAGE", referencedColumnName = "VOYAGE_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Voyage voyage;
    @JoinColumn(name = "FK_RESERVE_RESTAU", referencedColumnName = "RESERVATION_RESTAU_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private ReservationRestau reservationRestau;
    @JoinColumn(name = "FK_RESERVE_MANIF", referencedColumnName = "RESERVATION_MANIF_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private ReservationManif reservationManif;
    @JoinColumn(name = "FK_RESERVE_HOTEL", referencedColumnName = "RESERVATION_HOTEL_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private ReservationHotel reservationHotel;
    @JoinColumn(name = "FK_CLIENT_ID", referencedColumnName = "CLIENT_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public Reservation() {
    }

    public Reservation(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public ReservationRestau getReservationRestau() {
        return reservationRestau;
    }

    public void setReservationRestau(ReservationRestau reservationRestau) {
        this.reservationRestau = reservationRestau;
    }

    public ReservationManif getReservationManif() {
        return reservationManif;
    }

    public void setReservationManif(ReservationManif reservationManif) {
        this.reservationManif = reservationManif;
    }

    public ReservationHotel getReservationHotel() {
        return reservationHotel;
    }

    public void setReservationHotel(ReservationHotel reservationHotel) {
        this.reservationHotel = reservationHotel;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationId != null ? reservationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.reservationId == null && other.reservationId != null) || (this.reservationId != null && !this.reservationId.equals(other.reservationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvca.entity.Reservation[reservationId=" + reservationId + "]";
    }

}
