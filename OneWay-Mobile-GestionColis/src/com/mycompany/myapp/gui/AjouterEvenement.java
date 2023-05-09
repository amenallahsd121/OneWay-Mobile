/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.service.ServiceEvenement;




/**
 *
 * @author amens
 */
public class AjouterEvenement extends BaseForm{
    
     public AjouterEvenement(Resources res) {
        setTitle("Ajouter un evenement");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));


        
        
        TextField nomField = new TextField("", "Nom", 40, TextField.ANY);
        TextField Awards = new TextField("", "Awards", 40, TextField.ANY);
        TextField descriptionn = new TextField("", "description", 40, TextField.ANY);
        TextField dateField = new TextField("", "date", 40, TextField.ANY);
        TextField dateField2 = new TextField("", "date", 40, TextField.ANY);
//        Picker datePicker = new Picker();
//        datePicker.setType(Display.PICKER_TYPE_DATE);
//datePicker.addActionListener(e -> {
//    Date date = datePicker.getDate();
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    String dateStr = dateFormat.format(date);
//    dateField.setText(dateStr);
//});

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        Awards.setUnselectedStyle(textFieldStyle);
        nomField.setUnselectedStyle(textFieldStyle);
        descriptionn.setUnselectedStyle(textFieldStyle);
        dateField.setUnselectedStyle(textFieldStyle);
        dateField2.setUnselectedStyle(textFieldStyle);

    
        
        Button ajouterButton = new Button("Ajouter");
        ajouterButton.addActionListener(e -> {
 
            

            String awards = Awards.getText();
            String nom = nomField.getText();
            String description = descriptionn.getText();
            String dateStr = dateField.getText();
            String dateStr2 = dateField2.getText();

            // Create a new Livreur object
            Evenement  evenement = new Evenement(nom, description, awards, dateStr , dateStr2);
            // Add the Livreur using the LivreurService
            ServiceEvenement.getInstance().ajouterEvenement(evenement);
            // Show a confirmation dialog
            Dialog.show("Succès", "Le evenement a été ajouté avec succès", "OK", null);
            // Go back to the LivreurList form
            new EvenementList(res).showBack();
        });

        // Add the text fields and button to the form
        addComponent(nomField);
        addComponent(Awards);
        addComponent(descriptionn);
        addComponent(dateField);
        addComponent(dateField2);
        addComponent(ajouterButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new EvenementList(res).showBack());
        addComponent(retourButton);
    }
    
}
