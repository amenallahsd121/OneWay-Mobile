/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Participation;
import com.mycompany.myapp.service.ServiceEvenement;
import com.mycompany.myapp.service.ServiceParticipation;
import java.util.ArrayList;

/**
 *
 * @author Meddeb sofien
 */
public class ParticipationAdmin extends BaseForm{
     private TextField searchField;
    
     public ParticipationAdmin(Resources res) {
        NewsfeedForm nf = new NewsfeedForm(res);
        //AjouterEvenement al = new AjouterEvenement(res);

        ArrayList<Participation> participations = null;

        ArrayList<Participation> list = ServiceParticipation.getInstance().getAllParticipation();
        participations = list;
        
        
         System.out.println(list);

        setTitle("Liste des participation");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create a container for the buttons with a BoxLayout set to X_AXIS
        Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonsContainer.getAllStyles().setPadding(10, 10, 10, 10);
        buttonsContainer.getAllStyles().setMargin(10, 10, 10, 10);
        
        Container searchContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        searchContainer.getAllStyles().setPadding(10, 10, 10, 10);
        searchContainer.getAllStyles().setMargin(10, 10, 10, 10);
        
        
         searchField = new TextField();
        searchContainer.add(searchField);
        Button searchButton = new Button("Search");
        searchButton.addActionListener(e -> nf.show());
        searchContainer.add(searchButton);
        
               


        // Create the buttons and add them to the container
        //   ajouterButton.addActionListener(e -> al.show());
        // Handle adding a new livreur here

      //  buttonsContainer.add(ajouterButton);

        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> nf.show());
        buttonsContainer.add(retourButton);

        // Add the container with the buttons to the form
       addComponent(buttonsContainer);
        addComponent(searchContainer);

        // Loop over each Livreur object in the list and display its properties
        for (Participation participation : participations) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            container.getAllStyles().setPadding(10, 10, 10, 10);
            container.getAllStyles().setMargin(10, 10, 10, 10);
            container.getAllStyles().setBorder(Border.createLineBorder(2));

            Label part = new Label("refParticipation: " + participation.getId_participation());
            Label user = new Label("user: " + participation.getId_user());
           Label event = new Label("Description: " + participation.getId_ev());
            

            container.add(part);
            container.add(user);
            container.add(event);
            
            ////////////////////////////////////////////////////////////////////////////////////////
            

            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            Button deleteButton = new Button("Supprimer");
            deleteButton.addActionListener(e -> {
                boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this particicipation?", "Yes", "No");
                if (confirmed) {
                    ServiceParticipation.getInstance().deleteParticipation(participation.getId_participation());
                    new EvenementList(res).show();
                }
            });
            
//            ModifierEvenement ml = new ModifierEvenement(res, evenement);
//            // Create the "Update" button and add an ActionListener to handle updating the livreur
//            Button updateButton = new Button("Modifier");
//            updateButton.addActionListener(e -> ml.show());

            // Create a container for the buttons with a BoxLayout set to X_AXIS
            Container buttonsContainers = new Container(new BoxLayout(BoxLayout.X_AXIS));
            buttonsContainers.add(deleteButton);
          //  buttonsContainers.add(updateButton);

            container.add(buttonsContainers);

            addComponent(container);
        }

    }

    
}
