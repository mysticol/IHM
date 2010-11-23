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
@Table(name = "CLIENT", catalog = "", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findByClientId", query = "SELECT c FROM Client c WHERE c.clientId = :clientId"),
    @NamedQuery(name = "Client.findByNomClient", query = "SELECT c FROM Client c WHERE c.nomClient = :nomClient"),
    @NamedQuery(name = "Client.findByPrenomClient", query = "SELECT c FROM Client c WHERE c.prenomClient = :prenomClient")})
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CLIENT_ID", nullable = false)
    private Integer clientId;
    @Column(name = "NOM_CLIENT", length = 12)
    private String nomClient;
    @Column(name = "PRENOM_CLIENT", length = 12)
    private String prenomClient;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Voyage> voyageList;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<ReservationHotel> reservationHotelList;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<ReservationManif> reservationManifList;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Reservation> reservationList;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<ReservationRestau> reservationRestauList;

    public Client() {
    }

    public Client(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public List<Voyage> getVoyageList() {
        return voyageList;
    }

    public void setVoyageList(List<Voyage> voyageList) {
        this.voyageList = voyageList;
    }

    public List<ReservationHotel> getReservationHotelList() {
        return reservationHotelList;
    }

    public void setReservationHotelList(List<ReservationHotel> reservationHotelList) {
        this.reservationHotelList = reservationHotelList;
    }

    public List<ReservationManif> getReservationManifList() {
        return reservationManifList;
    }

    public void setReservationManifList(List<ReservationManif> reservationManifList) {
        this.reservationManifList = reservationManifList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public List<ReservationRestau> getReservationRestauList() {
        return reservationRestauList;
    }

    public void setReservationRestauList(List<ReservationRestau> reservationRestauList) {
        this.reservationRestauList = reservationRestauList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientId != null ? clientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.clientId == null && other.clientId != null) || (this.clientId != null && !this.clientId.equals(other.clientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvca.entity.Client[clientId=" + clientId + "]";
    }

}
