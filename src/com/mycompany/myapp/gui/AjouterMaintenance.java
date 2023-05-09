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
import com.mycompany.myapp.entities.Vehicule;
import com.mycompany.myapp.service.ServiceMaintenance;
import com.mycompany.myapp.service.ServiceVehicule;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author hp
 */
public class AjouterMaintenance extends BaseFormBack {


    public AjouterMaintenance(Resources res) {
        setTitle("Ajouter une Maintenance");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        TextField etatField = new TextField("", "etat", 40, TextField.ANY);
        TextField nom_sos_repField = new TextField("", " nom_sos_rep", 40, TextField.ANY);

        ArrayList<Vehicule> L = ServiceVehicule.getInstance().getAllVehicule();
        ArrayList<String> Matriculette = new ArrayList<>();
        HashMap<String, Integer> mapTypes = new HashMap<>();
        for (Vehicule c : L) {
            Matriculette.add(c.getMatricule());
            mapTypes.put(c.getMatricule(), c.getId_vehicule());
        }

        // Assuming you have a List<String> called "typesList"
        ComboBox<String> typeBox = new ComboBox<>();
        for (String s : Matriculette) {
            typeBox.addItem(s);
        }
        typeBox.setSelectedItem("Véhicule");

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color

        Button ajouterButton = new Button("Ajouter");
        ajouterButton.addActionListener(e -> {

            String nom_sos_rep = nom_sos_repField.getText();
            String etat = etatField.getText();
            String matricule = typeBox.getSelectedItem();

            // Get the ID of the selected category
            int idVehicule = mapTypes.get(matricule);
            // Create a new Livreur object
            Maintenance maintenance = new Maintenance(nom_sos_rep, etat, idVehicule);
            // Add the Livreur using the LivreurService
            ServiceMaintenance.getInstance().AjouterMaintenance(maintenance);
            // Show a confirmation dialog
            Dialog.show("Succès", "Le Maintenance a été ajouté avec succès", "OK", null);

            // Go back to the LivreurList form
            new MainList(res).showBack();
        });

        // Add the text fields and button to the form
        addComponent(nom_sos_repField);
        addComponent(etatField);
        addComponent(typeBox);
        addComponent(ajouterButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new MainList(res).showBack());
        addComponent(retourButton);
    }

}
