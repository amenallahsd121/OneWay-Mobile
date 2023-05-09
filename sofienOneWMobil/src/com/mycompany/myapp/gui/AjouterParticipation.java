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
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.Participation;
import com.mycompany.myapp.service.ServiceEvenement;
import com.mycompany.myapp.service.ServiceParticipation;

/**
 *
 * @author Meddeb sofien
 */
public class AjouterParticipation extends BaseForm{
    
     public AjouterParticipation(Resources res, Evenement evenement) {
        setTitle("Ajouter Participation");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));


        
        
        TextField id_userField = new TextField("68", "id_user", 40, TextField.ANY);
        TextField id_evField = new TextField(String.valueOf(evenement.getId_event()), "id_ev", 40, TextField.ANY);
        
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
        id_userField.setUnselectedStyle(textFieldStyle);
        id_evField.setUnselectedStyle(textFieldStyle);
        

    
        
        Button ajouterButton = new Button("Ajouter");
        ajouterButton.addActionListener(e -> {
 
            

           
           int id_user = Integer.parseInt(id_userField.getText());

            int id_ev = Integer.parseInt(id_evField.getText());

            // Create a new Livreur object
            Participation  participation = new Participation(id_user, id_ev);
            // Add the Livreur using the LivreurService
            ServiceParticipation.getInstance().ajouterParticipation(participation, evenement);
            // Show a confirmation dialog
            Dialog.show("Succès", "La participation a été ajouté avec succès", "OK", null);
            // Go back to the LivreurList form
            new ParticipationEventList(res).showBack();
        });

        // Add the text fields and button to the form
   //     addComponent(id_userField);
 //       addComponent(id_evField);
//        addComponent(descriptionn);
//        addComponent(dateField);
//        addComponent(dateField2);
        addComponent(ajouterButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new ParticipationEventList(res).showBack());
        addComponent(retourButton);
    }
    
    
}
