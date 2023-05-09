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
import com.mycompany.myapp.entities.Maintenance;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.service.ServiceMaintenance;
import com.mycompany.myapp.service.ServiceReclamation;
import com.mycompany.myapp.service.ServiceReponse;

/**
 *
 * @author hp
 */
public class ModifierMaintenance extends BaseFormBack {
    
      public ModifierMaintenance(Resources res, Maintenance maintenance) {
        setTitle("Modifier le Maintenance");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create text fields for each attribute of Livreur that can be modified
         
        TextField nom_sos_repField = new TextField("", " nom_sos_rep", 40, TextField.ANY);
        TextField etatField = new TextField("", "etat", 40, TextField.ANY);
        TextField id_vehiculeField = new TextField("", " id_vehicule", 40, TextField.ANY);

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color

          nom_sos_repField.setUnselectedStyle(textFieldStyle);
        etatField.setUnselectedStyle(textFieldStyle);
          id_vehiculeField.setUnselectedStyle(textFieldStyle);

        // Create a button to save the modifications
        Button modifierButton = new Button("Modifier");
        modifierButton.addActionListener(e -> {
            // Get the new values from the text fields

            
            String   nom_sos_rep =  nom_sos_repField.getText();
          
            String  etat =  etatField.getText();
           int id_vehicule = Integer.parseInt(id_vehiculeField.getText());

            // Create a new reclamation object with the new values
            Maintenance maintenancee = new Maintenance(nom_sos_rep, etat,id_vehicule);

            // Call the modifierreclamation method of ServiceLivreur to modify the reclamation
            if (ServiceMaintenance.getInstance().ModifierMaintenance(maintenancee)) { 
                // Show a confirmation dialog if the modification was successful
                Dialog.show("Succès", "Le maintenance a été modifié avec succès", "OK", null);
            } else {
                Dialog.show("Erreur", "Une erreur s'est produite lors de la modification", "OK", null);
            }

            // Go back to the reclamationList form
            new Reclamationlist(res).showBack();
        });

        // Add the text fields and button to the form
       
       addComponent(nom_sos_repField);
        addComponent(etatField);
          addComponent(id_vehiculeField);
    
        addComponent(modifierButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new Reclamationlist(res).showBack());
        addComponent(retourButton);
    }
}
