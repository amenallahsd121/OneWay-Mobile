/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.service.ServiceLivreur;
import com.mycompany.myapp.service.ServiceReclamation;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author amens
 */
public class AjouterReclamation extends BaseForm {

    public AjouterReclamation(Resources res) {
        setTitle("Ajouter une Reclamation");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        List<String> grosmot = Arrays.asList("merde", "fuck", "shit", "con", "connart", "putain", "pute", "chier", "bitch", "bèullshit", "bollocks", "damn", "putin");

        TextField text_recField = new TextField("", " text_rec", 40, TextField.ANY);

        ComboBox<String> typeBox = new ComboBox<>("Livreur", "Service", "Retard de livraison", "Autres");
        typeBox.setSelectedItem("Sujet ");

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        text_recField.setUnselectedStyle(textFieldStyle);
        //sujetField.setUnselectedStyle(textFieldStyle);
        Button ajouterButton = new Button("Ajouter");
        ajouterButton.addActionListener(e -> {

            String text_rec = text_recField.getText();

            String sujet = typeBox.getSelectedItem();

            boolean containsGrosmot = false;

            for (String grosmotWord : grosmot) {
                if (text_rec.contains(grosmotWord)) {
                    containsGrosmot = true;
                    break;
                }
            }

            if (containsGrosmot) {
                Dialog.show("Alerte", "Le texte contient un gros mot !", "OK", null);
                return;
            } else {
                Reclamation reclamation = new Reclamation(text_rec, sujet);

                // Add the Livreur using the LivreurService
                ServiceReclamation.getInstance().ajouterReclamation(reclamation);

                // Show a confirmation dialog
                Dialog.show("Succès", "La Reclamation a été ajouté avec succès", "OK", null);

                // Go back to the LivreurList form
                new Reclamationlist(res).showBack();

            }

        });

        // Add the text fields and button to the form
         addComponent(typeBox);
        addComponent(text_recField);
        
      
        addComponent(ajouterButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new Reclamationlist(res).showBack());
        addComponent(retourButton);
    }

}
