package pojos;
// Generated 30 nov. 2010 15:49:20 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Reservation generated by hbm2java
 */
@Entity
@Table(name="RESERVATION"
    ,schema="ROOT"
)
public class Reservation  implements java.io.Serializable {


     private int reservationId;
     private Integer fkReserveHotel;
     private Integer fkReserveRestau;
     private Integer fkReserveManif;
     private Integer fkVoyage;
     private Integer fkClientId;
     private Date date;

    public Reservation() {
    }

	
    public Reservation(int reservationId) {
        this.reservationId = reservationId;
    }
    public Reservation(int reservationId, Integer fkReserveHotel, Integer fkReserveRestau, Integer fkReserveManif, Integer fkVoyage, Integer fkClientId, Date date) {
       this.reservationId = reservationId;
       this.fkReserveHotel = fkReserveHotel;
       this.fkReserveRestau = fkReserveRestau;
       this.fkReserveManif = fkReserveManif;
       this.fkVoyage = fkVoyage;
       this.fkClientId = fkClientId;
       this.date = date;
    }
   
     @Id 
    
    @Column(name="RESERVATION_ID", unique=true, nullable=false)
    public int getReservationId() {
        return this.reservationId;
    }
    
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
    
    @Column(name="FK_RESERVE_HOTEL")
    public Integer getFkReserveHotel() {
        return this.fkReserveHotel;
    }
    
    public void setFkReserveHotel(Integer fkReserveHotel) {
        this.fkReserveHotel = fkReserveHotel;
    }
    
    @Column(name="FK_RESERVE_RESTAU")
    public Integer getFkReserveRestau() {
        return this.fkReserveRestau;
    }
    
    public void setFkReserveRestau(Integer fkReserveRestau) {
        this.fkReserveRestau = fkReserveRestau;
    }
    
    @Column(name="FK_RESERVE_MANIF")
    public Integer getFkReserveManif() {
        return this.fkReserveManif;
    }
    
    public void setFkReserveManif(Integer fkReserveManif) {
        this.fkReserveManif = fkReserveManif;
    }
    
    @Column(name="FK_VOYAGE")
    public Integer getFkVoyage() {
        return this.fkVoyage;
    }
    
    public void setFkVoyage(Integer fkVoyage) {
        this.fkVoyage = fkVoyage;
    }
    
    @Column(name="FK_CLIENT_ID")
    public Integer getFkClientId() {
        return this.fkClientId;
    }
    
    public void setFkClientId(Integer fkClientId) {
        this.fkClientId = fkClientId;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DATE", length=10)
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }




}


