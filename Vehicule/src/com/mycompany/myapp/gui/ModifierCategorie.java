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
import com.mycompany.myapp.service.ServiceCategorie;

/**
 *
 * @author amens
 */
public class ModifierCategorie extends BaseForm {
   public ModifierCategorie(Resources res, Categorie categorie) {
        setTitle("Modifier le Categorie");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create text fields for each attribute of Livreur that can be modified
        TextField catgf = new TextField(categorie.getType(), "Categ", 40, TextField.ANY);
        

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color

        catgf.setUnselectedStyle(textFieldStyle);
       

        // Create a button to save the modifications
        Button modifierButton = new Button("Modifier");
        modifierButton.addActionListener(e -> {
            // Get the new values from the text fields

            String categ = catgf.getText();
           

           
            // Create a new reclamation object with the new values
            Categorie newcategorie = new Categorie(categorie.getId_categorie(), categ);
            

            // Call the modifierreclamation method of ServiceLivreur to modify the reclamation
            if (ServiceCategorie.getInstance().modifierCategorie(newcategorie)) { 
                // Show a confirmation dialog if the modification was successful
                Dialog.show("Succès", "La Categorie a été modifié avec succès", "OK", null);
            } else {
                Dialog.show("Erreur", "Une erreur s'est produite lors de la modification", "OK", null);
            }

            // Go back to the reclamationList form
            new CategListe(res).showBack();
        });

        // Add the text fields and button to the form
       
       
        
         addComponent(catgf);
        addComponent(modifierButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new CategListe(res).showBack());
        addComponent(retourButton);
    }
}
