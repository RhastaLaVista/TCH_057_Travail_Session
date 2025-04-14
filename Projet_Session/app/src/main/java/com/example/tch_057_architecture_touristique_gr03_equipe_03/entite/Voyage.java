package com.example.tch_057_architecture_touristique_gr03_equipe_03.entite;

import java.util.HashMap;

public class Voyage {
    private int id;
    private String nom_voyage;
    private String description;
    private double prix;
    private String destination;
    private String image_url;
    private int duree_jours;
    private HashMap<String, Integer> trips;
    private String type_de_voyage;
    private String activites_incluses;

    public Voyage(String activites_incluses, String type_de_voyage, HashMap<String, Integer> trips, int duree_jours, String image_url, String destination, double prix, String description, String nom_voyage, int id) {
        this.activites_incluses = activites_incluses;
        this.type_de_voyage = type_de_voyage;
        this.trips = trips;
        this.duree_jours = duree_jours;
        this.image_url = image_url;
        this.destination = destination;
        this.prix = prix;
        this.description = description;
        this.nom_voyage = nom_voyage;
        this.id = id;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
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

    public int getDuree_jours() {
        return duree_jours;
    }

    public void setDuree_jours(int duree_jours) {
        this.duree_jours = duree_jours;
    }

    public HashMap<String, Integer> getTrips() {
        return trips;
    }

    public void setTrips(HashMap<String, Integer> trips) {
        this.trips = trips;
    }

    public String getType_de_voyage() {
        return type_de_voyage;
    }

    public void setType_de_voyage(String type_de_voyage) {
        this.type_de_voyage = type_de_voyage;
    }

    public String getActivites_incluses() {
        return activites_incluses;
    }

    public void setActivites_incluses(String activites_incluses) {
        this.activites_incluses = activites_incluses;
    }
}
