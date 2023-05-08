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
import com.mycompany.myapp.entities.Maintenance;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.entities.Vehicule;
import com.mycompany.myapp.service.ServiceMaintenance;
import com.mycompany.myapp.service.ServiceReclamation;
import com.mycompany.myapp.service.ServiceReponse;
import com.mycompany.myapp.service.ServiceVehicule;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author hp
 */
public class ModifierMaintenance extends BaseFormBack {

   public ModifierMaintenance(Resources res, Maintenance maintenance) {
        setTitle("Ajouter une vehicule");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        TextField EtatField = new TextField(maintenance.getEtat(),"", 40, TextField.ANY);
        TextField NomSosField = new TextField(maintenance.getNom_sos_rep(),"", 40, TextField.ANY);

        ArrayList<Vehicule> L = ServiceVehicule.getInstance().getAllVehicule();
        ArrayList<String> types = new ArrayList<>();
        HashMap<String, Integer> mapTypes = new HashMap<>();
        for (Vehicule c : L) {
            types.add(c.getMatricule());
            mapTypes.put(c.getMatricule(), c.getId_vehicule());
        }

      
         
        
ComboBox<String> typeBox = new ComboBox<>();
for (Vehicule c : L) {
    typeBox.addItem(c.getMatricule());
}








        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        EtatField.setUnselectedStyle(textFieldStyle);
        NomSosField.setUnselectedStyle(textFieldStyle);
        

        Button ajouterButton = new Button("Modifier");
        ajouterButton.addActionListener(e -> {

            String Etat = EtatField.getText();
            String NomSos = NomSosField.getText();
            String type = typeBox.getSelectedItem();

            // Get the ID of the selected category
            int Idv = mapTypes.get(type);
            

            // Create a new Vehicule object
            Maintenance m = new Maintenance(maintenance.getId_maintenance(),NomSos, Etat, Idv);
           
            
            
        

        // Add the Livreur using the LivreurService
        ServiceMaintenance.getInstance().ModifierMaintenance(m);

        // Show a confirmation dialog
        Dialog.show("Succès", "La Vehicule a été Modifié avec succès", "OK", null);

        // Go back to the LivreurList form
        new MainList(res).showBack();
    }

    );

        // Add the text fields and button to the form
    addComponent(EtatField);

    addComponent(NomSosField);

    addComponent(typeBox);

    addComponent(ajouterButton);

    // Add a back button to the top of the form
    Button retourButton = new Button("Retour");

    retourButton.addActionListener (e 

    -> new MainList(res).showBack());
    addComponent(retourButton);
}

}
