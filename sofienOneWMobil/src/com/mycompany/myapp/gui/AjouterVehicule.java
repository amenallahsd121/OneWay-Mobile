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
import com.mycompany.myapp.service.ServiceVehicule;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author hp
 */
public class AjouterVehicule extends BaseForm {
    
    
    public AjouterVehicule(Resources res) {
        setTitle("Ajouter une vehicule");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));


        
       
        TextField MatriculeField = new TextField("", "Matricule", 40, TextField.ANY);
        TextField MarqueField = new TextField("", "Marque", 40, TextField.ANY);
         TextField id_categorieField = new TextField("", "id_categorie", 40, TextField.ANY);

        

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        MatriculeField.setUnselectedStyle(textFieldStyle);
        

    
        
        Button ajouterButton = new Button("Ajouter");
        ajouterButton.addActionListener(e -> {
 
            
            
            String Matricule = MatriculeField.getText();
            String Marque = MarqueField.getText();
            String id_categorie = id_categorieField.getText();
            int idcategorie = Integer.parseInt(id_categorie);
          

            // Create a new Livreur object
             Vehicule vehicule = new Vehicule(Matricule , Marque, idcategorie);
            

            // Add the Livreur using the LivreurService
            ServiceVehicule.getInstance().ajouterVehicules(vehicule);

            // Show a confirmation dialog
            Dialog.show("Succès", "La Vehicule a été ajouté avec succès", "OK", null);

            // Go back to the LivreurList form
            new CategListe(res).showBack();
        });

        // Add the text fields and button to the form
       
        addComponent(MatriculeField);
         addComponent(MarqueField);
          addComponent(id_categorieField);
       
        addComponent(ajouterButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new CategListe(res).showBack());
        addComponent(retourButton);
    }
    
    
}
