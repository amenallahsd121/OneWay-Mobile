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
import com.mycompany.myapp.entities.TrajetOffre;
import com.mycompany.myapp.service.ServiceTrajetOffre;
import java.util.ArrayList;

/**
 *
 * @author utilisateur
 */
public class TrajetOffreList extends BaseForm {
     public TrajetOffreList(Resources res) {
    NewsfeedFormBack nf = new NewsfeedFormBack(res);
    AjouterTrajetOffre al = new AjouterTrajetOffre (res);
    ArrayList<TrajetOffre> TrajetOffres = null;
    
    ArrayList<TrajetOffre> list = ServiceTrajetOffre.getInstance().getAllTrajetOffre();
    TrajetOffres = list;
    
    setTitle("Liste des Categories");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    // Create a container for the buttons with a BoxLayout set to X_AXIS
    Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
    buttonsContainer.getAllStyles().setPadding(10, 10, 10, 10);
    buttonsContainer.getAllStyles().setMargin(10, 10, 10, 10);
    
    // Create the buttons and add them to the container
    Button ajouterButtonoffre = new Button("Ajouter Trajet Offre");
    ajouterButtonoffre.addActionListener(e -> al.show()); 
        // Handle adding a new livreur here
    
    buttonsContainer.add(ajouterButtonoffre);
    
    Button retourButton = new Button("Retour");
    retourButton.addActionListener(e -> nf.show());
    buttonsContainer.add(retourButton);
    
    // Add the container with the buttons to the form
    addComponent(buttonsContainer);
    
    // Loop over each Livreur object in the list and display its properties
for (TrajetOffre TrajetOffre : TrajetOffres) {
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    container.getAllStyles().setPadding(10, 10, 10, 10);
    container.getAllStyles().setMargin(10, 10, 10, 10);
    container.getAllStyles().setBorder(Border.createLineBorder(2));
 

    Label AddarriveoffreField = new Label("Addresse de arrivee : " + TrajetOffre.getAddArriveOffre());
    Label AdddepartoffreField = new Label("Addresse de depart: " + TrajetOffre.getAddDepartOffre());
    Label LimitekmoffreField = new Label("Limitekmoffre: " + TrajetOffre.getLimiteKmOffre());  
    Label NbreescaleoffreField = new Label("Nombre d' escale : " + TrajetOffre.getNbreEscaleOffre());  

    container.add(AddarriveoffreField);
    container.add(AdddepartoffreField);
    container.add(LimitekmoffreField);
        container.add(NbreescaleoffreField);

     ////////////////////////////////////////////////////////////////////////////////////////
            

            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            Button deleteButton = new Button("Supprimer");
            deleteButton.addActionListener(e -> {
                boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this Trajet ?", "Yes", "No");
                if (confirmed) {
                    ServiceTrajetOffre.getInstance().deleteTrajetOffre( TrajetOffre.getIdTrajetOffre());
                    new TrajetOffreList(res).show();
                }
            });
 ModifierTrajetOffre ml = new ModifierTrajetOffre(res, TrajetOffre);
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
}}
