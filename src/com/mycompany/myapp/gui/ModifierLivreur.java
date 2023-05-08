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
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.service.ServiceLivreur;

/**
 *
 * @author amens
 */
public class ModifierLivreur extends BaseForm {
    public ModifierLivreur(Resources res, Livreur livreur) {
        setTitle("Modifier le livreur");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create text fields for each attribute of Livreur that can be modified
        TextField cinField = new TextField(livreur.getCinLivreur(), "Nom", 40, TextField.ANY);
        TextField nomField = new TextField(livreur.getNom(), "Nom", 40, TextField.ANY);
        TextField prenomField = new TextField(livreur.getPrenom(), "Prenom", 40, TextField.ANY);
        TextField vehiculeField = new TextField(livreur.getVehicule(), "Vehicule", 40, TextField.ANY);
        
        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        cinField.setUnselectedStyle(textFieldStyle);
        nomField.setUnselectedStyle(textFieldStyle);
        prenomField.setUnselectedStyle(textFieldStyle);
        vehiculeField.setUnselectedStyle(textFieldStyle);

        // Create a button to save the modifications
        Button modifierButton = new Button("Modifier");
        modifierButton.addActionListener(e -> {
            // Get the new values from the text fields
            String cin = cinField.getText();
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String vehicule = vehiculeField.getText();

            // Create a new Livreur object with the new values
            Livreur newLivreur = new Livreur(livreur.getIdlivreur(), cin , nom, prenom, vehicule);

            // Call the modifierLivreur method of ServiceLivreur to modify the Livreur
            if (ServiceLivreur.getInstance().modifierLivreur(newLivreur)) {
                // Show a confirmation dialog if the modification was successful
                Dialog.show("Succès", "Le livreur a été modifié avec succès", "OK", null);
            } else {
                Dialog.show("Erreur", "Une erreur s'est produite lors de la modification", "OK", null);
            }

            // Go back to the LivreurList form
            new LivreurList(res).showBack();
        });

        // Add the text fields and button to the form
        addComponent(cinField);
        addComponent(nomField);
        addComponent(prenomField);
        addComponent(vehiculeField);
        addComponent(modifierButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new LivreurList(res).showBack());
        addComponent(retourButton);
    }
}

