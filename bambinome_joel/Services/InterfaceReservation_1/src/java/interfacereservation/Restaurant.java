/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacereservation;

/**
 *
 * @author JulienSambre
 */
public class Restaurant {

    private String id = "";
    private String nom = "";
    private String adresse = "";
    private Float prix;
    private int rank = 0;
    private Float prixMoyen;
    private int placesRestantes = 0;

    // Constructeur
    public Restaurant(String id, String nom, String adresse, Float prix, int rank, Float prixMoyen, int placesRestantes) {
        setId(id);
        setNom(nom);
        setAdresse(adresse);
        setPrix(prix);
        setRank(rank);
        setPrixMoyen(prixMoyen);
        setPlacesRestantes(placesRestantes);
    }

    Restaurant() {
    }


    // Getters-setters
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    public Float getPrixMoyen() {
        return prixMoyen;
    }

    public void setPrixMoyen(Float prixMoyen) {
        this.prixMoyen = prixMoyen;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


}
