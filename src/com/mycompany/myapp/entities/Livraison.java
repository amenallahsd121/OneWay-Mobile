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
public class Livraison {

    int id_livraison, id_colis, id_livreur;
    String etat;
    
    public Livraison(){};

    public Livraison(int id_livraison, int id_colis, int id_livreur, String etat) {
        this.id_livraison = id_livraison;
        this.id_colis = id_colis;
        this.id_livreur = id_livreur;
        this.etat = etat;
    }

    public Livraison(int id_colis, int id_livreur, String etat) {
        this.id_colis = id_colis;
        this.id_livreur = id_livreur;
        this.etat = etat;
    }
    public Livraison(String etat,int id_livraison, int id_livreur) {
        this.id_livraison = id_livraison;
        this.id_livreur = id_livreur;
        this.etat = etat;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public int getId_colis() {
        return id_colis;
    }

    public void setId_colis(int id_colis) {
        this.id_colis = id_colis;
    }

    public int getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id_livraison=" + id_livraison + ", id_colis=" + id_colis + ", id_livreur=" + id_livreur + ", etat=" + etat + '}';
    }

    
}
