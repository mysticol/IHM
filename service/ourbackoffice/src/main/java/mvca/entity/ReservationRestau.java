package mvca.entity;
// Generated 30 nov. 2010 15:49:20 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ReservationRestau generated by hbm2java
 */
@Entity
@Table(name="RESERVATION_RESTAU"
    ,schema="ROOT"
)
public class ReservationRestau  implements java.io.Serializable {


     private int reservationRestauId;
     private Integer fkRestauId;
     private Integer fkIdClient;
     private Date date;

    public ReservationRestau() {
    }

	
    public ReservationRestau(int reservationRestauId) {
        this.reservationRestauId = reservationRestauId;
    }
    public ReservationRestau(int reservationRestauId, Integer fkRestauId, Integer fkIdClient, Date date) {
       this.reservationRestauId = reservationRestauId;
       this.fkRestauId = fkRestauId;
       this.fkIdClient = fkIdClient;
       this.date = date;
    }
   
     @Id 
    
    @Column(name="RESERVATION_RESTAU_ID", unique=true, nullable=false)
    public int getReservationRestauId() {
        return this.reservationRestauId;
    }
    
    public void setReservationRestauId(int reservationRestauId) {
        this.reservationRestauId = reservationRestauId;
    }
    
    @Column(name="FK_RESTAU_ID")
    public Integer getFkRestauId() {
        return this.fkRestauId;
    }
    
    public void setFkRestauId(Integer fkRestauId) {
        this.fkRestauId = fkRestauId;
    }
    
    @Column(name="FK_ID_CLIENT")
    public Integer getFkIdClient() {
        return this.fkIdClient;
    }
    
    public void setFkIdClient(Integer fkIdClient) {
        this.fkIdClient = fkIdClient;
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


