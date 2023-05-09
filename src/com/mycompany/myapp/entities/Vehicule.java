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
public class Vehicule {
    
    int id_vehicule ; 
    String matricule , marque ; 
    int id_categorie ;

    public Vehicule() {
    }

    public Vehicule(String matricule, String marque, int id_categorie) {
        this.matricule = matricule;
        this.marque = marque;
        this.id_categorie = id_categorie;
    }

    public Vehicule(int id_vehicule, String matricule, String marque, int id_categorie) {
        this.id_vehicule = id_vehicule;
        this.matricule = matricule;
        this.marque = marque;
        this.id_categorie = id_categorie;
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getMarque() {
        return marque;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "id_vehicule=" + id_vehicule + ", matricule=" + matricule + ", marque=" + marque + ", id_categorie=" + id_categorie + '}';
    }
    
    
    
}
