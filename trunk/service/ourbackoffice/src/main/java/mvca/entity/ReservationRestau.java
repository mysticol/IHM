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
@Table(name = "RESERVATION_RESTAU", catalog = "", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "ReservationRestau.findAll", query = "SELECT r FROM ReservationRestau r"),
    @NamedQuery(name = "ReservationRestau.findByReservationRestauId", query = "SELECT r FROM ReservationRestau r WHERE r.reservationRestauId = :reservationRestauId"),
    @NamedQuery(name = "ReservationRestau.findByDate", query = "SELECT r FROM ReservationRestau r WHERE r.date = :date")})
public class ReservationRestau implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RESERVATION_RESTAU_ID", nullable = false)
    private Integer reservationRestauId;
    @Column(name = "DATE")
    @Temporal(TemporalType.TIME)
    private Date date;
    @OneToMany(mappedBy = "reservationRestau", fetch = FetchType.EAGER)
    private List<Reservation> reservationList;
    @JoinColumn(name = "FK_RESTAU_ID", referencedColumnName = "RESTAURANT_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;
    @JoinColumn(name = "FK_ID_CLIENT", referencedColumnName = "CLIENT_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public ReservationRestau() {
    }

    public ReservationRestau(Integer reservationRestauId) {
        this.reservationRestauId = reservationRestauId;
    }

    public Integer getReservationRestauId() {
        return reservationRestauId;
    }

    public void setReservationRestauId(Integer reservationRestauId) {
        this.reservationRestauId = reservationRestauId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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
        hash += (reservationRestauId != null ? reservationRestauId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservationRestau)) {
            return false;
        }
        ReservationRestau other = (ReservationRestau) object;
        if ((this.reservationRestauId == null && other.reservationRestauId != null) || (this.reservationRestauId != null && !this.reservationRestauId.equals(other.reservationRestauId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvca.entity.ReservationRestau[reservationRestauId=" + reservationRestauId + "]";
    }

}
