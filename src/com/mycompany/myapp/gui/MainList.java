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
import com.mycompany.myapp.entities.Maintenance;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.service.ServiceMaintenance;
import com.mycompany.myapp.service.ServiceReclamation;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class MainList extends BaseFormBack {

    public MainList(Resources res) {
        NewsfeedFormBack nf = new NewsfeedFormBack(res);
        AjouterMaintenance al = new AjouterMaintenance(res);

        ArrayList<Maintenance> Maintenance = null;

        ArrayList<Maintenance> list = ServiceMaintenance.getInstance().getAllMaintenance();
        Maintenance = list;

        setTitle("Liste des Maintenance");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create a container for the buttons with a BoxLayout set to X_AXIS
        Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonsContainer.getAllStyles().setPadding(10, 10, 10, 10);
        buttonsContainer.getAllStyles().setMargin(10, 10, 10, 10);

        // Create the buttons and add them to the container
        Button ajouterButton = new Button("Ajouter Maintenance");
        ajouterButton.addActionListener(e -> al.show());
        // Handle adding a new Maintenance here

        buttonsContainer.add(ajouterButton);

//    buttonsContainer.add(ajouterButton);
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> nf.show());
        buttonsContainer.add(retourButton);

        // Add the container with the buttons to the form
        addComponent(buttonsContainer);

        // Loop over each Livreur object in the list and display its properties
        for (Maintenance maintenance : Maintenance) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            container.getAllStyles().setPadding(10, 10, 10, 10);
            container.getAllStyles().setMargin(10, 10, 10, 10);
            container.getAllStyles().setBorder(Border.createLineBorder(2));

            Label nom_sos_rep = new Label("nom_sos_rep: " + maintenance.getNom_sos_rep());
            Label Etat = new Label("Etat: " + maintenance.getEtat());
            int idvh = maintenance.getId_vehicule();
            
            //Label matricule = new Label("Matricule: " + maintenance.getId_vehicule());

            container.add(nom_sos_rep);
            container.add(Etat);
            //container.add(matricule);

            ////////////////////////////////////////////////////////////////////////////////////////
            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            Button deleteButton = new Button("Supprimer");
            deleteButton.addActionListener(e -> {
                boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this Maintenance?", "Yes", "No");
                if (confirmed) {
                    ServiceMaintenance.getInstance().deletemaintenance(maintenance.getId_maintenance());
                    new MainList(res).show();
                }
            });

/////////////////////////////////////////////////////////////////////////////////////////////
            ModifierMaintenance ml = new ModifierMaintenance(res, maintenance);
            // Create the "Update" button and add an ActionListener to handle updating the livreur
            Button updateButton = new Button("Modifier");
            updateButton.addActionListener(e -> ml.show());

///////////////////////////////////////////////////////////////////////////////////////////////
            // Create a container for the buttons with a BoxLayout set to X_AXIS
            Container buttonsContainers = new Container(new BoxLayout(BoxLayout.X_AXIS));
            buttonsContainers.add(deleteButton);
            buttonsContainers.add(updateButton);

            container.add(buttonsContainers);

            addComponent(container);
        }

    }

}
