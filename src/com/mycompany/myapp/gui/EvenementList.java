/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.service.ServiceEvenement;
import java.util.ArrayList;

/**
 *
 * @author amens
 */
public class EvenementList extends BaseForm{
     public EvenementList(Resources res) {
        NewsfeedFormBack nf = new NewsfeedFormBack(res);
        AjouterEvenement al = new AjouterEvenement(res);

        ArrayList<Evenement> evenements = null;

        ArrayList<Evenement> list = ServiceEvenement.getInstance().getAllEvenement();
        evenements = list;
        
        
         

        setTitle("Liste des Evenements");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create a container for the buttons with a BoxLayout set to X_AXIS
        Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonsContainer.getAllStyles().setPadding(10, 10, 10, 10);
        buttonsContainer.getAllStyles().setMargin(10, 10, 10, 10);

        // Create the buttons and add them to the container
        Button ajouterButton = new Button("Ajouter Evenement");
        ajouterButton.addActionListener(e -> al.show());
        // Handle adding a new livreur here

        buttonsContainer.add(ajouterButton);

        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> nf.show());
        buttonsContainer.add(retourButton);

        // Add the container with the buttons to the form
        addComponent(buttonsContainer);

        // Loop over each Livreur object in the list and display its properties
        for (Evenement evenement : evenements) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            container.getAllStyles().setPadding(10, 10, 10, 10);
            container.getAllStyles().setMargin(10, 10, 10, 10);
            container.getAllStyles().setBorder(Border.createLineBorder(2));

            Label Awards = new Label("AWARDS: " + evenement.getAwards());
            Label nomLabel = new Label("Nom: " + evenement.getNom());
           Label description = new Label("Description: " + evenement.getDescription());
            Label datedebut = new Label("Date Debut: " + evenement.getDate_debut());
            Label datefin = new Label("Date fin: " + evenement.getDate_fin());

            container.add(Awards);
            container.add(nomLabel);
            container.add(description);
            container.add(datedebut);
            container.add(datefin);
            ////////////////////////////////////////////////////////////////////////////////////////
            

            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            Button deleteButton = new Button("Supprimer");
            deleteButton.addActionListener(e -> {
                boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this livreur?", "Yes", "No");
                if (confirmed) {
                    ServiceEvenement.getInstance().deleteEvenement(evenement.getId_event());
                    new EvenementList(res).show();
                }
            });
            
   //         ModifierEvenement ml = new ModifierEvenement(res, evenement);
            // Create the "Update" button and add an ActionListener to handle updating the livreur
            Button updateButton = new Button("Modifier");
      //      updateButton.addActionListener(e -> ml.show());

            // Create a container for the buttons with a BoxLayout set to X_AXIS
            Container buttonsContainers = new Container(new BoxLayout(BoxLayout.X_AXIS));
            buttonsContainers.add(deleteButton);
            buttonsContainers.add(updateButton);

            container.add(buttonsContainers);

            addComponent(container);
        }

    }

    
}
