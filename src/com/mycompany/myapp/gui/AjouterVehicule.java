/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.Vehicule;
import com.mycompany.myapp.service.ServiceCategorie;
import com.mycompany.myapp.service.ServiceVehicule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        typeBox.setSelectedItem("Type");

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        MatriculeField.setUnselectedStyle(textFieldStyle);

        Button ajouterButton = new Button("Ajouter");
        ajouterButton.addActionListener(e -> {

            String Matricule = MatriculeField.getText();
            String Marque = MarqueField.getText();
            String type = typeBox.getSelectedItem();

            // Get the ID of the selected category
            int idcategorie = mapTypes.get(type);

            // Create a new Vehicule object
            Vehicule vehicule = new Vehicule(Matricule, Marque, idcategorie);

            // Add the Livreur using the LivreurService
            ServiceVehicule.getInstance().ajouterVehicules(vehicule);

            // Show a confirmation dialog
            Dialog.show("Succès", "La Vehicule a été ajouté avec succès", "OK", null);
            
            LocalNotification n = new LocalNotification();
            n.setId("demo-notification");
            n.setAlertBody("Votre Ajout a été effectuée avec succès");
            n.setAlertTitle("Succès d Ajout");
            n.setAlertSound("/notification_sound_Mario.mp3"); //file name must begin with notification_sound

            Display.getInstance().scheduleLocalNotification(
                    n,
                    System.currentTimeMillis() + 10 * 1000, // fire date/time
                    LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency
                    );

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

        retourButton.addActionListener(e
                -> new VehiculeList(res).showBack());
        addComponent(retourButton);
    }

}
