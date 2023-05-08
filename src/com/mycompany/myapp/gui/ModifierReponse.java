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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.service.ServiceReclamation;
import com.mycompany.myapp.service.ServiceReponse;

/**
 *
 * @author amens
 */
public class ModifierReponse extends BaseFormBack {
    
    
      public ModifierReponse(Resources res, Reponse reponse) {
        setTitle("Modifier le reponse");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create text fields for each attribute of Livreur that can be modified
        TextField text_repField = new TextField(reponse.getText_rep(), "text_rec", 40, TextField.ANY);
       


        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color

        text_repField.setUnselectedStyle(textFieldStyle);
       

        // Create a button to save the modifications
        Button modifierButton = new Button("Modifier");
        modifierButton.addActionListener(e -> {
            // Get the new values from the text fields

            String text_rep = text_repField.getText();
           

            // Create a new reclamation object with the new values
            Reponse newreponse = new Reponse(reponse.getId_reponse(), text_rep);

            // Call the modifierreclamation method of ServiceLivreur to modify the reclamation
            if (ServiceReponse.getInstance().modifierreponse(newreponse)) { 
                // Show a confirmation dialog if the modification was successful
                Dialog.show("Succès", "Le reponse a été modifié avec succès", "OK", null);
            } else {
                Dialog.show("Erreur", "Une erreur s'est produite lors de la modification", "OK", null);
            }

            // Go back to the reclamationList form
            new Reponselist(res).showBack();
        });

        // Add the text fields and button to the form
       
       
        
         addComponent(text_repField);
        addComponent(modifierButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new Reponselist(res).showBack());
        addComponent(retourButton);
    }

    
    
}
