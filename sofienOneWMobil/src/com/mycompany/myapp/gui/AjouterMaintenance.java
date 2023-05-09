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
import com.mycompany.myapp.service.ServiceMaintenance;
import com.mycompany.myapp.service.ServiceReclamation;

/**
 *
 * @author hp
 */
public class AjouterMaintenance extends BaseFormBack {

   
      public AjouterMaintenance(Resources res) {
         setTitle("Ajouter un Maintenance");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));


        
        
        TextField nom_sos_repField = new TextField("", " nom_sos_rep", 40, TextField.ANY);
        TextField etatField = new TextField("", "etat", 40, TextField.ANY);
        TextField id_vehiculeField = new TextField("", " id_vehicule", 40, TextField.ANY);
    
        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        nom_sos_repField.setUnselectedStyle(textFieldStyle);
        etatField.setUnselectedStyle(textFieldStyle);
          id_vehiculeField.setUnselectedStyle(textFieldStyle);
        

    
        
        Button ajouterButton = new Button("Ajouter");
        ajouterButton.addActionListener(e -> {
 
            
            String   nom_sos_rep =  nom_sos_repField.getText();
          
            String  etat =  etatField.getText();
           int id_vehicule = Integer.parseInt(id_vehiculeField.getText());
          

            // Create a new Livreur object
           Maintenance maintenance = new Maintenance(nom_sos_rep, etat,id_vehicule);

            // Add the Livreur using the LivreurService
          ServiceMaintenance.getInstance().AjouterMaintenance(maintenance);

            // Show a confirmation dialog
            Dialog.show("Succès", "Le Reclamation a été ajouté avec succès", "OK", null);

            // Go back to the LivreurList form
            new Reclamationlist(res).showBack();
        });

        // Add the text fields and button to the form
        addComponent(nom_sos_repField);
        addComponent(etatField);
          addComponent(id_vehiculeField);
        addComponent(ajouterButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new LivreurList(res).showBack());
        addComponent(retourButton);
    }
    
    
    
    
}
