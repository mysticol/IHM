/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacereservation;

/**
 *
 * @author JulienSambre
 */
public class Manifestation {

    private String id = "";
    private String nom = "";
    private String adresse = "";
    private String description = "";
    private Float prix;
    private int placesRestantes = 0;

    // Constructeur
    public Manifestation(String id, String nom, String adresse, Float prix, String description, int placesRestantes) {
        setId(id);
        setNom(nom);
        setAdresse(adresse);
        setPrix(prix);
        setDescription(description);
        setPlacesRestantes(placesRestantes);
    }

    Manifestation() {
    }


    // Getters-setters
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPlacesRestantes() {
        return placesRestantes;
    }

    public void setPlacesRestantes(int placesRestantes) {
        this.placesRestantes = placesRestantes;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

}
