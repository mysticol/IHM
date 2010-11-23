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
@Table(name = "RESERVATION_HOTEL", catalog = "", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "ReservationHotel.findAll", query = "SELECT r FROM ReservationHotel r"),
    @NamedQuery(name = "ReservationHotel.findByReservationHotelId", query = "SELECT r FROM ReservationHotel r WHERE r.reservationHotelId = :reservationHotelId"),
    @NamedQuery(name = "ReservationHotel.findByDate", query = "SELECT r FROM ReservationHotel r WHERE r.date = :date")})
public class ReservationHotel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RESERVATION_HOTEL_ID", nullable = false)
    private Integer reservationHotelId;
    @Column(name = "DATE")
    @Temporal(TemporalType.TIME)
    private Date date;
    @JoinColumn(name = "FK_HOTEL_ID", referencedColumnName = "HOTEL_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Hotel hotel;
    @JoinColumn(name = "FK_ID_CLIENT", referencedColumnName = "CLIENT_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;
    @OneToMany(mappedBy = "reservationHotel", fetch = FetchType.EAGER)
    private List<Reservation> reservationList;

    public ReservationHotel() {
    }

    public ReservationHotel(Integer reservationHotelId) {
        this.reservationHotelId = reservationHotelId;
    }

    public Integer getReservationHotelId() {
        return reservationHotelId;
    }

    public void setReservationHotelId(Integer reservationHotelId) {
        this.reservationHotelId = reservationHotelId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
        hash += (reservationHotelId != null ? reservationHotelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservationHotel)) {
            return false;
        }
        ReservationHotel other = (ReservationHotel) object;
        if ((this.reservationHotelId == null && other.reservationHotelId != null) || (this.reservationHotelId != null && !this.reservationHotelId.equals(other.reservationHotelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvca.entity.ReservationHotel[reservationHotelId=" + reservationHotelId + "]";
    }

}
