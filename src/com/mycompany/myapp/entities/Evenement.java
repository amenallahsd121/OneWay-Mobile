/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author amens
 */
public class Evenement {
    private int id_event;
  private  String nom,description,awards;
    Date date_debut,date_fin;
    String dateString1,dateString2;

    public Evenement() {
    }

    public Evenement(int id_event, String nom, String description, String awards, Date date_debut, Date date_fin) {
        this.id_event = id_event;
        this.nom = nom;
        this.description = description;
        this.awards = awards;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
     public Evenement(String nom, String description, String awards, Date date_debut, Date date_fin) {
      
        this.nom = nom;
        this.description = description;
        this.awards = awards;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
     public Evenement(String nom, String description, String awards) {
      
        this.nom = nom;
        this.description = description;
        this.awards = awards;
        
    }
     public Evenement(String nom, String description, String awards, String dateString1) {
      
        this.nom = nom;
        this.description = description;
        this.awards = awards;
        this.dateString1 = dateString1;
      
    }
     public Evenement(String nom, String description, String awards, String dateString1 , String dateString2) {
      
        this.nom = nom;
        this.description = description;
        this.awards = awards;
        this.dateString1 = dateString1;
        this.dateString2 = dateString2;
      
    }

    public int getId_event() {
        return id_event;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getAwards() {
        return awards;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_event=" + id_event + ", nom=" + nom + ", description=" + description + ", awards=" + awards + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }

    public String getDateString1() {
        return dateString1;
    }

    public String getDateString2() {
        return dateString2;
    }

    public void setDateString1(String dateString1) {
        this.dateString1 = dateString1;
    }

    public void setDateString2(String dateString2) {
        this.dateString2 = dateString2;
    }
     
    
    
    
}
