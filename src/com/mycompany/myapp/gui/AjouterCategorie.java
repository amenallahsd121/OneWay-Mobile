/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.service.ServiceCategorie;

/**
 *
 * @author amens
 */
public class AjouterCategorie extends BaseForm {
    
      public AjouterCategorie(Resources res) {
        setTitle("Ajouter une catégorie");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));


        
       
        TextField nomField = new TextField("", "Type", 40, TextField.ANY);
        

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        nomField.setUnselectedStyle(textFieldStyle);
        

    
        
        Button ajouterButton = new Button("Ajouter");
        ajouterButton.addActionListener(e -> {
 
            
            
            String nom = nomField.getText();
          

            // Create a new Livreur object
            Categorie categorie = new Categorie( nom);

            // Add the Livreur using the LivreurService
            ServiceCategorie.getInstance().ajouterCategories(categorie);

            // Show a confirmation dialog
            Dialog.show("Succès", "La Categorie a été ajouté avec succès", "OK", null);

            // Go back to the LivreurList form
            new CategListe(res).showBack();
        });

        // Add the text fields and button to the form
       
        addComponent(nomField);
       
        addComponent(ajouterButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new CategListe(res).showBack());
        addComponent(retourButton);
    }
    
    
}
