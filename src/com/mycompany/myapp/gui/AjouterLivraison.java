/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.service.ServiceLivraison;
import com.mycompany.myapp.service.ServiceLivreur;
import java.util.List;

/**
 *
 * @author amens
 */
public class AjouterLivraison extends BaseFormBack {
    
    public AjouterLivraison(Resources res, int id) {
        setTitle("Affecter Colis");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create ComboBox for Livreur names
        List<String> Livreurette = ServiceLivreur.getInstance().getAllNomlivreur();
        
        ComboBox<String> Livreurg = new ComboBox<>();
        for (String s : Livreurette) {
            Livreurg.addItem(s);
        }
        
        // Create ComboBox for Etat
        ComboBox<String> Etat = new ComboBox<>("En Cours", "Terminé");

        // Create container for ComboBoxes
        Container container = new Container(new GridLayout(2, 2));
        container.add(new Label("Livreur:"));
        container.add(Livreurg);
        container.add(new Label("Etat:"));
        container.add(Etat);
        
        
        //////
        
        
        
         Button ajouterButton = new Button("Affecter");
        ajouterButton.addActionListener(e -> {

           int idliv = ServiceLivreur.getInstance().getIdlivreur(Livreurg.getSelectedItem());
            // Create a new Livreur object
            Livraison L = new Livraison(id, idliv, Etat.getSelectedItem());
            // Add the Livreur using the LivreurService
           ServiceLivraison.getInstance().AjouterLivraison(L);
            // Show a confirmation dialog
            Dialog.show("Succès", "La Livraison a été ajouté avec succès", "OK", null);

            // Go back to the LivreurList form
            new LivreurList(res).showBack();
        });

        // Add container to form
        addComponent(container);
        addComponent(ajouterButton);
    }
}

