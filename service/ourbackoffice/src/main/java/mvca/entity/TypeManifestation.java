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
@Table(name = "TYPE_MANIFESTATION", catalog = "", schema = "ROOT")
@NamedQueries({
    @NamedQuery(name = "TypeManifestation.findAll", query = "SELECT t FROM TypeManifestation t"),
    @NamedQuery(name = "TypeManifestation.findByTypeId", query = "SELECT t FROM TypeManifestation t WHERE t.typeId = :typeId"),
    @NamedQuery(name = "TypeManifestation.findByNomType", query = "SELECT t FROM TypeManifestation t WHERE t.nomType = :nomType")})
public class TypeManifestation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TYPE_ID", nullable = false)
    private Integer typeId;
    @Column(name = "NOM_TYPE", length = 15)
    private String nomType;
    @OneToMany(mappedBy = "typeManifestation", fetch = FetchType.EAGER)
    private List<Manifestation> manifestationList;

    public TypeManifestation() {
    }

    public TypeManifestation(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public List<Manifestation> getManifestationList() {
        return manifestationList;
    }

    public void setManifestationList(List<Manifestation> manifestationList) {
        this.manifestationList = manifestationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeId != null ? typeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeManifestation)) {
            return false;
        }
        TypeManifestation other = (TypeManifestation) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvca.entity.TypeManifestation[typeId=" + typeId + "]";
    }

}
