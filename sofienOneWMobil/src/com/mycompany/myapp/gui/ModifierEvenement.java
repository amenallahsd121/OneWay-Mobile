/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.service.ServiceEvenement;


/**
 *
 * @author Meddeb sofien
 */
public class ModifierEvenement extends  BaseForm{
    
    
    
    
      public ModifierEvenement(Resources res, Evenement evenement) {
        setTitle("Modifier le evenement");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create text fields for each attribute of Livreur that can be modified
        
              //  TextField cinField = new TextField(livreur.getCinLivreur(), "Nom", 40, TextField.ANY);

        TextField nomField = new TextField(evenement.getNom(), "Nom", 40, TextField.ANY);
        TextField Awards = new TextField(evenement.getAwards(), "Awards", 40, TextField.ANY);
        TextField descriptionn = new TextField(evenement.getDescription(), "description", 40, TextField.ANY);
        //TextField dateField = new TextField(evenement.getDate_debut(), "date_debut", 40, TextField.ANY);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      //String dateDebutString = dateFormat.format(evenement.getDateString1());
       TextField dateField = new TextField(evenement.getDateString1(), "date_debut", 40, TextField.ANY);
    //  String dateFinString = dateFormat.format(evenement.getDate_fin());
        TextField dateField2 = new TextField("", "date_Fin", 40, TextField.ANY);
        
        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        Awards.setUnselectedStyle(textFieldStyle);
        nomField.setUnselectedStyle(textFieldStyle);
        descriptionn.setUnselectedStyle(textFieldStyle);
        dateField.setUnselectedStyle(textFieldStyle);
        dateField2.setUnselectedStyle(textFieldStyle);

        // Create a button to save the modifications
        Button modifierButton = new Button("Modifier");
        modifierButton.addActionListener(e -> {
            // Get the new values from the text fields
            String awards = Awards.getText();
            String nom = nomField.getText();
            String description = descriptionn.getText();
            String dateStr = dateField.getText();
            String dateStr2 = dateField2.getText();

            // Create a new Livreur object with the new values
            Evenement  newevenement = new Evenement(evenement.getId_event(),nom, description, awards, dateStr , dateStr2);

            // Call the modifierLivreur method of ServiceLivreur to modify the Livreur
            if (ServiceEvenement.getInstance().modifierEvenement(newevenement)) {
                // Show a confirmation dialog if the modification was successful
                Dialog.show("Succès", "Le evenement a été modifié avec succès", "OK", null);
            } else {
                Dialog.show("Erreur", "Une erreur s'est produite lors de la modification", "OK", null);
            }

            // Go back to the LivreurList form
            new EvenementList(res).showBack();
        });

        // Add the text fields and button to the form
        addComponent(nomField);
        addComponent(Awards);
        addComponent(descriptionn);
        addComponent(dateField);
        addComponent(dateField2);
        
        addComponent(modifierButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new EvenementList(res).showBack());
        addComponent(retourButton);
    }
    
}
