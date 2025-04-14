package com.example.tch_057_architecture_touristique_gr03_equipe_03.entite;

public class Client {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private int age;
    private String telephone;
    private String adresse;


    //Default Required Constructor for Jackson
    public Client(){}

    public Client(int id, String nom, String email, String prenom, String mdp, int age, String telephone, String adresse) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.prenom = prenom;
        this.mdp = mdp;
        this.age = age;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    //Getters and Setters
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public String getMdp() {return mdp;}
    public void setMdp(String mdp) {this.mdp = mdp;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public String getTelephone() {return telephone;}
    public void setTelephone(String telephone) {this.telephone = telephone;}
    public String getAdresse() {return adresse;}
    public void setAdresse(String adresse) {this.adresse = adresse;}



}
