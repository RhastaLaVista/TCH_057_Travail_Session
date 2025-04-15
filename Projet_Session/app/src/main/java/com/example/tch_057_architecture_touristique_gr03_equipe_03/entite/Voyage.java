package com.example.tch_057_architecture_touristique_gr03_equipe_03.entite;

public class Voyage {

    int id;
    String nom_voyage;
    String description;
    int prix;
    String destination;
    String image_url;
    String type_de_voyage;
    int duree_jours;
    String[] date;
    int[] nb_places;
    String activites_incluses;
    Object[] trips;

    public Voyage(){

    }

    public Voyage(Object[] trips, int id, String nom_voyage, String description, int prix, String destination, String image_url, String type_de_voyage, int duree_jours, String[] date, int[] nb_places, String activites_incluses) {
        this.id = id;
        this.trips=trips;
        this.nom_voyage = nom_voyage;
        this.description = description;
        this.prix = prix;
        this.destination = destination;
        this.image_url = image_url;
        this.type_de_voyage = type_de_voyage;
        this.duree_jours = duree_jours;
        this.date = date;
        this.nb_places = nb_places;
        this.activites_incluses = activites_incluses;
        setValues();
    }
    public void setValues(){
        for (Object trip:trips){

        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_voyage() {
        return nom_voyage;
    }

    public void setNom_voyage(String nom_voyage) {
        this.nom_voyage = nom_voyage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getType_de_voyage() {
        return type_de_voyage;
    }

    public void setType_de_voyage(String type_de_voyage) {
        this.type_de_voyage = type_de_voyage;
    }

    public int getDuree_jours() {
        return duree_jours;
    }

    public void setDuree_jours(int duree_jours) {
        this.duree_jours = duree_jours;
    }

    public String[] getDate() {
        return date;
    }

    public void setDate(String[] date) {
        this.date = date;
    }

    public int[] getNb_places() {
        return nb_places;
    }

    public void setNb_places(int[] nb_places) {
        this.nb_places = nb_places;
    }

    public String getactivites_incluses() {
        return activites_incluses;
    }

    public void setactivites_incluses(String activites_incluses) {
        this.activites_incluses = activites_incluses;
    }

    public Object[] getTrips() {
        return trips;
    }

    public void setTrips(Object[] trips) {
        this.trips = trips;
    }
}
