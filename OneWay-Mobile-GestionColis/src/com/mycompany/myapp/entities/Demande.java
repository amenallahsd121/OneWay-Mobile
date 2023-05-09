/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author utilisateur
 */
public class Demande {
    private int IdDemande,IdOffre ,IdColis,IdPersonne  ;
 
 private String DescriptionDemande;
 private double prix ;

    public Demande() {
    }

    public Demande(int IdOffre, int IdPersonne, String DescriptionDemande, double prix) {
        this.IdOffre = IdOffre;
        this.IdPersonne = IdPersonne;
        this.DescriptionDemande = DescriptionDemande;
        this.prix = prix;
    }

    public Demande(int IdDemande, int IdOffre, int IdPersonne, String DescriptionDemande, double prix) {
        this.IdDemande = IdDemande;
        this.IdOffre = IdOffre;
        this.IdPersonne = IdPersonne;
        this.DescriptionDemande = DescriptionDemande;
        this.prix = prix;
    }

    public int getIdDemande() {
        return IdDemande;
    }

    public int getIdOffre() {
        return IdOffre;
    }

    public int getIdColis() {
        return IdColis;
    }

    public int getIdPersonne() {
        return IdPersonne;
    }

    public String getDescriptionDemande() {
        return DescriptionDemande;
    }

    public double getPrix() {
        return prix;
    }

    public void setIdDemande(int IdDemande) {
        this.IdDemande = IdDemande;
    }

    public void setIdOffre(int IdOffre) {
        this.IdOffre = IdOffre;
    }

    public void setIdColis(int IdColis) {
        this.IdColis = IdColis;
    }

    public void setIdPersonne(int IdPersonne) {
        this.IdPersonne = IdPersonne;
    }

    public void setDescriptionDemande(String DescriptionDemande) {
        this.DescriptionDemande = DescriptionDemande;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
 
}
