/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.service.ServiceLivreur;

/**
 *
 * @author amens
 */
public class AjouterLivreur extends BaseForm {

    public AjouterLivreur(Resources res) {
        setTitle("Ajouter un livreur");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));


        
        TextField cinField = new TextField("", "CIN", 40, TextField.ANY);
        TextField nomField = new TextField("", "Nom", 40, TextField.ANY);
        TextField prenomField = new TextField("", "Prenom", 40, TextField.ANY);
        TextField vehiculeField = new TextField("", "Vehicule", 40, TextField.ANY);

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        cinField.setUnselectedStyle(textFieldStyle);
        nomField.setUnselectedStyle(textFieldStyle);
        prenomField.setUnselectedStyle(textFieldStyle);
        vehiculeField.setUnselectedStyle(textFieldStyle);

    
        
        Button ajouterButton = new Button("Ajouter");
        ajouterButton.addActionListener(e -> {
 
            
            String cin = cinField.getText();
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String vehicule = vehiculeField.getText();

            // Create a new Livreur object
            Livreur livreur = new Livreur(cin, nom, prenom, vehicule);

            // Add the Livreur using the LivreurService
            ServiceLivreur.getInstance().ajouterLivreurs(livreur);

            // Show a confirmation dialog
            Dialog.show("Succès", "Le livreur a été ajouté avec succès", "OK", null);

            // Go back to the LivreurList form
            new LivreurList(res).showBack();
        });

        // Add the text fields and button to the form
        addComponent(cinField);
        addComponent(nomField);
        addComponent(prenomField);
        addComponent(vehiculeField);
        addComponent(ajouterButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new LivreurList(res).showBack());
        addComponent(retourButton);
    }
}
