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
public class Colis {

    private int id;
    double poids, prix;
    String typeColis, Ldepart, Larrive;

    public Colis() {
    }

    public Colis(int id, double poids, double prix, String typeColis, String Ldepart, String Larrive) {
        this.id = id;
        this.poids = poids;
        this.prix = prix;
        this.typeColis = typeColis;
        this.Ldepart = Ldepart;
        this.Larrive = Larrive;
    }

    public Colis(double poids, double prix, String typeColis, String Ldepart, String Larrive) {
        
        this.poids = poids;
        this.prix = prix;
        this.typeColis = typeColis;
        this.Ldepart = Ldepart;
        this.Larrive = Larrive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getTypeColis() {
        return typeColis;
    }

    public void setTypeColis(String typeColis) {
        this.typeColis = typeColis;
    }

    public String getLdepart() {
        return Ldepart;
    }

    public void setLdepart(String Ldepart) {
        this.Ldepart = Ldepart;
    }

    public String getLarrive() {
        return Larrive;
    }

    public void setLarrive(String Larrive) {
        this.Larrive = Larrive;
    }

    @Override
    public String toString() {
        return "Colis{" + "id=" + id + ", poids=" + poids + ", prix=" + prix + ", typeColis=" + typeColis + ", Ldepart=" + Ldepart + ", Larrive=" + Larrive + '}';
    }
    
    

    
}
