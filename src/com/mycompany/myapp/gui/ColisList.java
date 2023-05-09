/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Colis;
import com.mycompany.myapp.service.ServiceColis;
import java.util.ArrayList;

/**
 *
 * @author amens
 */
public class ColisList extends BaseForm {
    
    
     public ColisList(Resources res) {
        AjouterColis ac = new AjouterColis(res);
        NewsfeedForm nf = new NewsfeedForm(res);
      

        ArrayList<Colis> coliette = null;

        ArrayList<Colis> list = ServiceColis.getInstance().getAllColis();
        coliette = list;
         

        setTitle("Liste des Colis");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create a container for the buttons with a BoxLayout set to X_AXIS
        Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonsContainer.getAllStyles().setPadding(10, 10, 10, 10);
        buttonsContainer.getAllStyles().setMargin(10, 10, 10, 10);

        // Create the buttons and add them to the container
        Button ajouterButton = new Button("Ajouter Colis");
        ajouterButton.addActionListener(e -> ac.show());
      

        buttonsContainer.add(ajouterButton);

        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> nf.show());
        buttonsContainer.add(retourButton);

        // Add the container with the buttons to the form
        addComponent(buttonsContainer);

        // Loop over each Livreur object in the list and display its properties
        for (Colis c : coliette) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            container.getAllStyles().setPadding(10, 10, 10, 10);
            container.getAllStyles().setMargin(10, 10, 10, 10);
            container.getAllStyles().setBorder(Border.createLineBorder(2));

            Label Poidsf = new Label("Poids : " + c.getPoids()+" Kg");
            Label Prixf = new Label("Prix : " + c.getPrix()+" DT");
            Label Typef = new Label("Type : " + c.getTypeColis());
            Label departf = new Label("Lieu Depart : " + c.getLdepart());
            Label arrivef = new Label("Lieu Arrive : " + c.getLarrive());

            container.add(Poidsf);
            container.add(Prixf);
            container.add(Typef);
            container.add(departf);
            container.add(arrivef);
            ////////////////////////////////////////////////////////////////////////////////////////
            

            
            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            Button deleteButton = new Button("Supprimer");
            deleteButton.addActionListener(e -> {
                boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this livreur?", "Yes", "No");
                if (confirmed) {
                  
                    ServiceColis.getInstance().deletecolis(c.getId());
                    new ColisList(res).show();
                }
            });
            
            ModifierColis ml = new ModifierColis(res, c);
            // Create the "Update" button and add an ActionListener to handle updating the livreur
            Button updateButton = new Button("Modifier");
            updateButton.addActionListener(e -> ml.show());

            // Create a container for the buttons with a BoxLayout set to X_AXIS
            Container buttonsContainers = new Container(new BoxLayout(BoxLayout.X_AXIS));
            buttonsContainers.add(deleteButton);
            buttonsContainers.add(updateButton);

            container.add(buttonsContainers);

            addComponent(container);
        }

    }
    
}
