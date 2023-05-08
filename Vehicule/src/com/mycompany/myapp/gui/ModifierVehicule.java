/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.Vehicule;
import com.mycompany.myapp.service.ServiceCategorie;
import com.mycompany.myapp.service.ServiceReclamation;
import com.mycompany.myapp.service.ServiceVehicule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hp
 */
public class ModifierVehicule extends BaseForm {

    public ModifierVehicule(Resources res, Vehicule vehicule) {
        setTitle("Ajouter une vehicule");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        TextField MatriculeField = new TextField(vehicule.getMatricule(),"", 40, TextField.ANY);
        TextField MarqueField = new TextField(vehicule.getMarque(),"", 40, TextField.ANY);

        ArrayList<Categorie> L = ServiceCategorie.getInstance().getAllCategorie();
        ArrayList<String> types = new ArrayList<>();
        HashMap<String, Integer> mapTypes = new HashMap<>();
        for (Categorie c : L) {
            types.add(c.getType());
            mapTypes.put(c.getType(), c.getId_categorie());
        }

      
         
        
ComboBox<String> typeBox = new ComboBox<>();
for (Categorie c : L) {
    typeBox.addItem(c.getType());
}








        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        MatriculeField.setUnselectedStyle(textFieldStyle);

        Button ajouterButton = new Button("Modifier");
        ajouterButton.addActionListener(e -> {

            String Matricule = MatriculeField.getText();
            String Marque = MarqueField.getText();
            String type = typeBox.getSelectedItem();

            // Get the ID of the selected category
            int idcategorie = mapTypes.get(type);
            

            // Create a new Vehicule object
            Vehicule v = new Vehicule(vehicule.getId_vehicule(),Matricule, Marque, idcategorie);
           
            
            
        

        // Add the Livreur using the LivreurService
        ServiceVehicule.getInstance().modifiervehicule(v);

        // Show a confirmation dialog
        Dialog.show("Succès", "La Vehicule a été Modifié avec succès", "OK", null);

        // Go back to the LivreurList form
        new VehiculeList(res).showBack();
    }

    );

        // Add the text fields and button to the form
    addComponent(MatriculeField);

    addComponent(MarqueField);

    addComponent(typeBox);

    addComponent(ajouterButton);

    // Add a back button to the top of the form
    Button retourButton = new Button("Retour");

    retourButton.addActionListener (e 

    -> new VehiculeList(res).showBack());
    addComponent(retourButton);
}

}
