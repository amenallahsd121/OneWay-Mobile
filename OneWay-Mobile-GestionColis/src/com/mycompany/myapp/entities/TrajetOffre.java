/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author utilisateur
 */
public class TrajetOffre {
     private  long IdTrajetOffre ;

    private int LimiteKmOffre, NbreEscaleOffre, nbreOffre;
    private List<Offre> idoffres = new ArrayList<>();

    private String AddArriveOffre, AddDepartOffre, description;

    public TrajetOffre(int LimiteKmOffre, int NbreEscaleOffre, String AddArriveOffre, String AddDepartOffre) {
        this.LimiteKmOffre = LimiteKmOffre;
        this.NbreEscaleOffre = NbreEscaleOffre;
        this.AddArriveOffre = AddArriveOffre;
        this.AddDepartOffre = AddDepartOffre;
        this.description = description;
    }

    @Override
    public String toString() {
        return "TrajetOffre{" + "IdTrajetOffre=" + IdTrajetOffre + ", LimiteKmOffre=" + LimiteKmOffre + ", NbreEscaleOffre=" + NbreEscaleOffre + ", nbreOffre=" + nbreOffre + ", idoffres=" + idoffres + ", AddArriveOffre=" + AddArriveOffre + ", AddDepartOffre=" + AddDepartOffre + ", description=" + description + '}';
    }

    public TrajetOffre(long IdTrajetOffre, int LimiteKmOffre, int NbreEscaleOffre, String AddArriveOffre, String AddDepartOffre) {
        this.IdTrajetOffre = IdTrajetOffre;
        this.LimiteKmOffre = LimiteKmOffre;
        this.NbreEscaleOffre = NbreEscaleOffre;
        this.AddArriveOffre = AddArriveOffre;
        this.AddDepartOffre = AddDepartOffre;
    }

    public TrajetOffre() {
    }

    public void setIdTrajetOffre(long IdTrajetOffre) {
        this.IdTrajetOffre = IdTrajetOffre;
    }

    public void setLimiteKmOffre(int LimiteKmOffre) {
        this.LimiteKmOffre = LimiteKmOffre;
    }

    public void setNbreEscaleOffre(int NbreEscaleOffre) {
        this.NbreEscaleOffre = NbreEscaleOffre;
    }

    public void setNbreOffre(int nbreOffre) {
        this.nbreOffre = nbreOffre;
    }

    public void setIdoffres(List<Offre> idoffres) {
        this.idoffres = idoffres;
    }

    public void setAddArriveOffre(String AddArriveOffre) {
        this.AddArriveOffre = AddArriveOffre;
    }

    public void setAddDepartOffre(String AddDepartOffre) {
        this.AddDepartOffre = AddDepartOffre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getIdTrajetOffre() {
        return IdTrajetOffre;
    }

    public int getLimiteKmOffre() {
        return LimiteKmOffre;
    }

    public int getNbreEscaleOffre() {
        return NbreEscaleOffre;
    }

    public int getNbreOffre() {
        return nbreOffre;
    }

    public List<Offre> getIdoffres() {
        return idoffres;
    }

    public String getAddArriveOffre() {
        return AddArriveOffre;
    }

    public String getAddDepartOffre() {
        return AddDepartOffre;
    }

    public String getDescription() {
        return description;
    }

}
