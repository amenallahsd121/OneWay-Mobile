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
public class Reclamation {
    int id_reclamation;
    String text_rec;
    String sujet;

     public Reclamation() {
    } 
     
    public int getId_reclamation() {
        return id_reclamation;
    }

    public String getText_rec() {
        return text_rec;
    }

    public String getSujet() {
        return sujet;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setText_rec(String text_rec) {
        this.text_rec = text_rec;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Reclamation(int id_reclamation, String text_rec, String sujet) {
        this.id_reclamation = id_reclamation;
        this.text_rec = text_rec;
        this.sujet = sujet;
    }

      public Reclamation( String text_rec, String sujet) {
        
        this.text_rec = text_rec;
        this.sujet = sujet;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", text_rec=" + text_rec + ", sujet=" + sujet + '}';
    }
      
      
      
   
    
}
