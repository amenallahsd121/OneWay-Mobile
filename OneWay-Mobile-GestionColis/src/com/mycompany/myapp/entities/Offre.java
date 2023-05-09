package com.mycompany.myapp.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.l10n.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author utilisateur
 */
public class Offre {
     private  int IdOffre, IdCatColis,IdUser;
         private  int   nbreDemande;
    private int IdTrajetOffre,CatOffreId;   
    private String DescriptionOffre, MaxRetard,Etat;
    private TrajetOffre IdTrajetOffres ;
    private float prixOffre;
    private Date DateOffre;
    private Date DateSortieOffre;

    public Offre() {
    }

    @Override
    public String toString() {
        return "Offre{" + "IdOffre=" + IdOffre + ", IdCatColis=" + IdCatColis + ", IdUser=" + IdUser + ", nbreDemande=" + nbreDemande + ", IdTrajetOffre=" + IdTrajetOffre + ", CatOffreId=" + CatOffreId + ", DescriptionOffre=" + DescriptionOffre + ", MaxRetard=" + MaxRetard + ", Etat=" + Etat + ", IdTrajetOffres=" + IdTrajetOffres + ", prixOffre=" + prixOffre + ", DateOffre=" + DateOffre + ", DateSortieOffre=" + DateSortieOffre + '}';
    }

    public Offre(int IdOffre, int nbreDemande, int IdTrajetOffre, String DescriptionOffre, String MaxRetard, int CatOffreId, float prixOffre, Date DateOffre, Date DateSortieOffre) {
        this.IdOffre = IdOffre;
        this.nbreDemande = nbreDemande;
        this.IdTrajetOffre = IdTrajetOffre;
        this.DescriptionOffre = DescriptionOffre;
        this.MaxRetard = MaxRetard;
        this.CatOffreId = CatOffreId;
        this.prixOffre = prixOffre;
        this.DateOffre = DateOffre;
        this.DateSortieOffre = DateSortieOffre;
    }

    public Offre(int IdTrajetOffre, String DescriptionOffre, String MaxRetard, int CatOffreId, float prixOffre, Date DateOffre, Date DateSortieOffre) {
        this.IdTrajetOffre = IdTrajetOffre;
        this.DescriptionOffre = DescriptionOffre;
        this.MaxRetard = MaxRetard;
        this.CatOffreId = CatOffreId;
        this.prixOffre = prixOffre;
        this.DateOffre = DateOffre;
        this.DateSortieOffre = DateSortieOffre;
    }

    public int getIdOffre() {
        return IdOffre;
    }

    public int getIdCatColis() {
        return IdCatColis;
    }

    public int getIdUser() {
        return IdUser;
    }

    public int getNbreDemande() {
        return nbreDemande;
    }

    public int getIdTrajetOffre() {
        return IdTrajetOffre;
    }

    public String getDescriptionOffre() {
        return DescriptionOffre;
    }

    public String getMaxRetard() {
        return MaxRetard;
    }

    public String getEtat() {
        return Etat;
    }

    public int getCatOffreId() {
        return CatOffreId;
    }

    public TrajetOffre getIdTrajetOffres() {
        return IdTrajetOffres;
    }

    public float getPrixOffre() {
        return prixOffre;
    }

    public Date getDateOffre() {
        return DateOffre;
    }

    public Date getDateSortieOffre() {
        return DateSortieOffre;
    }

    public void setIdOffre(int IdOffre) {
        this.IdOffre = IdOffre;
    }

    public void setIdCatColis(int IdCatColis) {
        this.IdCatColis = IdCatColis;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public void setNbreDemande(int nbreDemande) {
        this.nbreDemande = nbreDemande;
    }

    public void setIdTrajetOffre(int IdTrajetOffre) {
        this.IdTrajetOffre = IdTrajetOffre;
    }

    public void setDescriptionOffre(String DescriptionOffre) {
        this.DescriptionOffre = DescriptionOffre;
    }

    public void setMaxRetard(String MaxRetard) {
        this.MaxRetard = MaxRetard;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public void setCatOffreId(int CatOffreId) {
        this.CatOffreId = CatOffreId;
    }

    public void setIdTrajetOffres(TrajetOffre IdTrajetOffres) {
        this.IdTrajetOffres = IdTrajetOffres;
    }

    public void setPrixOffre(float prixOffre) {
        this.prixOffre = prixOffre;
    }

    public void setDateOffre(Date DateOffre) {
        this.DateOffre = DateOffre;
    }

    public void setDateSortieOffre(Date DateSortieOffre) {
        this.DateSortieOffre = DateSortieOffre;
    }
    
}
