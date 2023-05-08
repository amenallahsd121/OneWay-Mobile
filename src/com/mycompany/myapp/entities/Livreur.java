/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author amens
 */
public class Livreur {

    int idlivreur;
    String cinLivreur, nom, prenom, vehicule;

    public Livreur() {
    }

    public Livreur(String cinLivreur, String nom, String prenom, String vehicule) {
        this.cinLivreur = cinLivreur;
        this.nom = nom;
        this.prenom = prenom;
        this.vehicule = vehicule;
    }

    public Livreur(int idlivreur, String cinLivreur, String nom, String prenom, String vehicule) {
        this.idlivreur = idlivreur;
        this.cinLivreur = cinLivreur;
        this.nom = nom;
        this.prenom = prenom;
        this.vehicule = vehicule;
    }

    public int getIdlivreur() {
        return idlivreur;
    }

    public void setIdlivreur(int id_livreur) {
        this.idlivreur = id_livreur;
    }

    public String getCinLivreur() {
        return cinLivreur;
    }

    public void setCinLivreur(String cinLivreur) {
        this.cinLivreur = cinLivreur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    @Override
    public String toString() {
        return "Livreur{" + "id_livreur=" + idlivreur + ", cinLivreur=" + cinLivreur + ", nom=" + nom + ", prenom=" + prenom + ", vehicule=" + vehicule + '}';
    }

    
}
