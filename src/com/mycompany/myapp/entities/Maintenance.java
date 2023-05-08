/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author hp
 */
public class Maintenance {
    
    int id_maintenance ;
    String nom_sos_rep , etat ;
    int id_vehicule ; 

    public Maintenance(int id_maintenance, String nom_sos_rep, String etat, int id_vehicule) {
        this.id_maintenance = id_maintenance;
        this.nom_sos_rep = nom_sos_rep;
        this.etat = etat;
        this.id_vehicule = id_vehicule;
    }

    public Maintenance() {
    }

    public Maintenance(String nom_sos_rep, String etat, int id_vehicule) {
        this.nom_sos_rep = nom_sos_rep;
        this.etat = etat;
        this.id_vehicule = id_vehicule;
    }

    public int getId_maintenance() {
        return id_maintenance;
    }

    public String getNom_sos_rep() {
        return nom_sos_rep;
    }

    public String getEtat() {
        return etat;
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_maintenance(int id_maintenance) {
        this.id_maintenance = id_maintenance;
    }

    public void setNom_sos_rep(String nom_sos_rep) {
        this.nom_sos_rep = nom_sos_rep;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    @Override
    public String toString() {
        return "Maintenance{" + "id_maintenance=" + id_maintenance + ", nom_sos_rep=" + nom_sos_rep + ", etat=" + etat + ", id_vehicule=" + id_vehicule + '}';
    }
    
    
    

   
}
