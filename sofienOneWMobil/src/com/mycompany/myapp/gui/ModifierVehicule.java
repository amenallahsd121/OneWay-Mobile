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
import com.mycompany.myapp.entities.Vehicule;
import com.mycompany.myapp.service.ServiceReclamation;
import com.mycompany.myapp.service.ServiceVehicule;

/**
 *
 * @author hp
 */
public class ModifierVehicule extends BaseForm {
 
     public ModifierVehicule(Resources res, Vehicule vehicule) {
        setTitle("Modifier le Vehicule");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create text fields for each attribute of Livreur that can be modified
        TextField matriculeField = new TextField(vehicule.getMatricule(), "Matricule", 40, TextField.ANY);
        TextField marqueField = new TextField(vehicule.getMarque(), "Marque", 40, TextField.ANY);
        int catgid = vehicule.getId_categorie();
        String catgidStr = Integer.toString(catgid);
        
         TextField Id_categorieField = new TextField(catgidStr, "Id_categorie", 40, TextField.ANY);

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color

        matriculeField.setUnselectedStyle(textFieldStyle);
        marqueField.setUnselectedStyle(textFieldStyle);
            Id_categorieField.setUnselectedStyle(textFieldStyle);

        // Create a button to save the modifications
        Button modifierButton = new Button("Modifier");
        modifierButton.addActionListener(e -> {
            // Get the new values from the text fields

            
            String Matricule = matriculeField.getText();
            String Marque = marqueField.getText();
            String id_categoriee = Id_categorieField.getText();
            int idcategorie = Integer.parseInt(id_categoriee);

            // Create a new reclamation object with the new values
            Vehicule newvehicule = new Vehicule(vehicule.getId_vehicule(), Matricule, Marque,idcategorie);

            // Call the modifierreclamation method of ServiceLivreur to modify the reclamation
            if (ServiceVehicule.getInstance().modifierVehicule(newvehicule)) { 
                // Show a confirmation dialog if the modification was successful
                Dialog.show("Succès", "Le Vehicule a été modifié avec succès", "OK", null);
            } else {
                Dialog.show("Erreur", "Une erreur s'est produite lors de la modification", "OK", null);
            }

            // Go back to the reclamationList form
            new VehiculeList(res).showBack();
        });

        // Add the text fields and button to the form
       
      addComponent(matriculeField);
         addComponent(marqueField);
          addComponent(Id_categorieField);
        addComponent(modifierButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new VehiculeList(res).showBack());
        addComponent(retourButton);
    }
    
    
}
