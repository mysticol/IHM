package mvca.entity;
// Generated 30 nov. 2010 15:49:20 by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TypeManifestation generated by hbm2java
 */
@Entity
@Table(name="TYPE_MANIFESTATION"
    ,schema="ROOT"
)
public class TypeManifestation  implements java.io.Serializable {


     private int typeId;
     private String nomType;

    public TypeManifestation() {
    }

	
    public TypeManifestation(int typeId) {
        this.typeId = typeId;
    }
    public TypeManifestation(int typeId, String nomType) {
       this.typeId = typeId;
       this.nomType = nomType;
    }
   
     @Id 
    
    @Column(name="TYPE_ID", unique=true, nullable=false)
    public int getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    
    @Column(name="NOM_TYPE", length=15)
    public String getNomType() {
        return this.nomType;
    }
    
    public void setNomType(String nomType) {
        this.nomType = nomType;
    }




}


