package com.mycompany.myapp.gui;


import com.mycompany.myapp.entities.Offre;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author utilisateur
 */
 class OffreComparator implements Comparator<Offre> {
        private String selectedCatOffreId;

        public OffreComparator(String selectedCatOffreId) {
            this.selectedCatOffreId = selectedCatOffreId;
        }

    OffreComparator(int catOffreId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        @Override
        public int compare(Offre o1, Offre o2) {
            int catOffreId1 = o1.getCatOffreId();
            int catOffreId2 = o2.getCatOffreId();

            return Integer.compare(catOffreId1, Integer.parseInt(selectedCatOffreId));
        }
    }
