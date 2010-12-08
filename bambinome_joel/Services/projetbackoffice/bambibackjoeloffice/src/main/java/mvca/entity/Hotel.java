package mvca.entity;
// Generated 30 nov. 2010 15:49:20 by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Hotel generated by hbm2java
 */
@Entity
@Table(name="HOTEL"
    ,schema="ROOT"
)
public class Hotel  implements java.io.Serializable {


     private int hotelId;
     private Integer fkLocalisationId;
     private String nomHotel;
     private String adresse;
     private Integer nbChambre;
     private Integer rankHotel;
     private Double prixMoyen;
     private Double prix;

    public Hotel() {
    }

	
    public Hotel(int hotelId) {
        this.hotelId = hotelId;
    }
    public Hotel(int hotelId, Integer fkLocalisationId, String nomHotel, String adresse, Integer nbChambre, Integer rankHotel, Double prixMoyen, Double prix) {
       this.hotelId = hotelId;
       this.fkLocalisationId = fkLocalisationId;
       this.nomHotel = nomHotel;
       this.adresse = adresse;
       this.nbChambre = nbChambre;
       this.rankHotel = rankHotel;
       this.prixMoyen = prixMoyen;
       this.prix = prix;
    }
   
     @Id 
    
    @Column(name="HOTEL_ID", unique=true, nullable=false)
    public int getHotelId() {
        return this.hotelId;
    }
    
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
    
    @Column(name="FK_LOCALISATION_ID")
    public Integer getFkLocalisationId() {
        return this.fkLocalisationId;
    }
    
    public void setFkLocalisationId(Integer fkLocalisationId) {
        this.fkLocalisationId = fkLocalisationId;
    }
    
    @Column(name="NOM_HOTEL", length=20)
    public String getNomHotel() {
        return this.nomHotel;
    }
    
    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }
    
    @Column(name="ADRESSE", length=30)
    public String getAdresse() {
        return this.adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    @Column(name="NB_CHAMBRE")
    public Integer getNbChambre() {
        return this.nbChambre;
    }
    
    public void setNbChambre(Integer nbChambre) {
        this.nbChambre = nbChambre;
    }
    
    @Column(name="RANK_HOTEL")
    public Integer getRankHotel() {
        return this.rankHotel;
    }
    
    public void setRankHotel(Integer rankHotel) {
        this.rankHotel = rankHotel;
    }
    
    @Column(name="PRIX_MOYEN", precision=52, scale=0)
    public Double getPrixMoyen() {
        return this.prixMoyen;
    }
    
    public void setPrixMoyen(Double prixMoyen) {
        this.prixMoyen = prixMoyen;
    }
    
    @Column(name="PRIX", precision=52, scale=0)
    public Double getPrix() {
        return this.prix;
    }
    
    public void setPrix(Double prix) {
        this.prix = prix;
    }




}


