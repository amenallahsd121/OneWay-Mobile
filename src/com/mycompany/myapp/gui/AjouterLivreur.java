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

    boolean checkifstringisnumber(String s) {
        try {
            int i;
            i = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }

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

            // Create a new Livreur object
            // Add the Livreur using the LivreurService
            if (cinField.getText().isEmpty() || prenomField.getText().isEmpty() || nomField.getText().isEmpty() || vehiculeField.getText().isEmpty()) {

                Dialog.show("Error", "Remplir Vos Champs", "No", null);
            } else if (cinField.getText().length() != 8) {
                Dialog.show("Error", "Le Numéro de CIN Doit Etre Composer de 8 Chiffres", "No", null);

            } else if (this.checkifstringisnumber(cinField.getText()) == false) {
                Dialog.show("Error", "Le Numéro de CIN Doit Etre Contenir Des Chiffres Seulement", "No", null);

            } else {

                String cin = cinField.getText();
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String vehicule = vehiculeField.getText();

                Livreur livreur = new Livreur(cin, nom, prenom, vehicule);
                ServiceLivreur.getInstance().ajouterLivreurs(livreur);
                Dialog.show("Succès", "Le livreur a été ajouté avec succès", "OK", null);
                new LivreurList(res).showBack();

            }

            // Show a confirmation dialog
            // Go back to the LivreurList form
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
