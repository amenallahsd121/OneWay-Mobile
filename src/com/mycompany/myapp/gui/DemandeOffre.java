/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Demande;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.service.ServiceDemande;
import com.mycompany.myapp.service.ServiceOffre;
import java.util.ArrayList;

/**
 *
 * @author utilisateur
 */
public class DemandeOffre extends BaseForm {
     public DemandeOffre(Resources res,int id) {
    NewsfeedForm nf = new NewsfeedForm(res);
    ArrayList<Demande> Demandes = null;
    
        ArrayList<Demande> list = ServiceDemande.getInstance().getAllDemande(id);

   Demandes = list;
    
        setTitle("Liste des Demandes");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    // Create a container for the buttons with a BoxLayout set to X_AXIS
    Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
    buttonsContainer.getAllStyles().setPadding(10, 10, 10, 10);
    buttonsContainer.getAllStyles().setMargin(10, 10, 10, 10);
    
    // Create the buttons and add them to the container
    
         Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new OffreList(res).showBack());
        addComponent(retourButton);
    
    // Add the container with the buttons to the form
    addComponent(buttonsContainer);
    
    // Loop over each Livreur object in the list and display its properties
for (Demande Demande : Demandes) {
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    container.getAllStyles().setPadding(10, 10, 10, 10);
    container.getAllStyles().setMargin(10, 10, 10, 10);
    container.getAllStyles().setBorder(Border.createLineBorder(2));
 

    Label IdDemandeField = new Label("IdDemande : " + Demande.getIdDemande());
    Label IdOffreField = new Label("IdOffre : " + Demande.getIdOffre());
    Label DescriptionField = new Label("Description : " + Demande.getDescriptionDemande());  
    Label PrixField = new Label("Prix  : " + Demande.getPrix());  
    Label IdPersonneField = new Label("Id Demandeur : " + Demande.getIdPersonne());  
 


    container.add(IdDemandeField);
            container.add(DescriptionField);
    container.add(IdOffreField);

    container.add(PrixField);
        container.add(IdPersonneField);

     ////////////////////////////////////////////////////////////////////////////////////////
            

            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
          
  

            // Create a container for the buttons with a BoxLayout set to X_AXIS
            Container buttonsContainers = new Container(new BoxLayout(BoxLayout.X_AXIS));

            container.add(buttonsContainers);

            addComponent(container);
        }
}
    
}
