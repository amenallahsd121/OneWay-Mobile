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
public class Reponse {
    String text_rep;
    int id_reclamation;
    int id_reponse;

    public Reponse() {
    }

    public Reponse(String text_rep, String text_rec) {
        this.text_rep = text_rep;
        this.id_reclamation = id_reclamation;
    }

    public Reponse(String text_rep, int id_reclamation) {
        this.text_rep = text_rep;
        this.id_reclamation = id_reclamation;
    }


    public Reponse( int id_reponse,String text_rep, int id_reclamation) {
        this.text_rep = text_rep;
        this.id_reclamation = id_reclamation;
        this.id_reponse = id_reponse;
    }
    public Reponse( int id_reponse,String text_rep) {
        this.text_rep = text_rep;
        this.id_reponse = id_reponse;
    }

    public String getText_rep() {
        return text_rep;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public int getId_reponse() {
        return id_reponse;
    }

    public void setText_rep(String text_rep) {
        this.text_rep = text_rep;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    @Override
    public String toString() {
        return "Reponse{" + "text_rep=" + text_rep + ", id_reclamation=" + id_reclamation + ", id_reponse=" + id_reponse + '}';
    }
    
    
    
    
}
