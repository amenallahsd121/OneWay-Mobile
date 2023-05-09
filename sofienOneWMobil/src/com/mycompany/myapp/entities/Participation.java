/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Meddeb sofien
 */
public class Participation {
    
    private int id_participation,id_user,id_ev;

    public Participation() {
    }

    public Participation(int id_participation, int id_user, int id_ev) {
        this.id_participation = id_participation;
        this.id_user = id_user;
        this.id_ev = id_ev;
    }
    public Participation( int id_user, int id_ev) {
       
        this.id_user = id_user;
        this.id_ev = id_ev;
    }

    public int getId_participation() {
        return id_participation;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_ev() {
        return id_ev;
    }

    public void setId_participation(int id_participation) {
        this.id_participation = id_participation;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_ev(int id_ev) {
        this.id_ev = id_ev;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
   
    
}
