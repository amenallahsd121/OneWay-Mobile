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
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.entities.TrajetOffre;
import com.mycompany.myapp.service.ServiceDemande;
import com.mycompany.myapp.service.ServiceOffre;
import com.mycompany.myapp.service.ServiceTrajetOffre;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author utilisateur
 */
public class OffreList extends BaseForm {  
    public OffreList(Resources res) {
    NewsfeedFormBack nf = new NewsfeedFormBack(res);
    ArrayList<Offre> Offres = null;

    ArrayList<Offre> list = ServiceOffre.getInstance().getAllOffre();
    Offres = list;
    
    setTitle("Liste des Offres");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
   
    // Create a container for the buttons with a BoxLayout set to X_AXIS
    Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
    buttonsContainer.getAllStyles().setPadding(10, 10, 10, 10);
    buttonsContainer.getAllStyles().setMargin(10, 10, 10, 10);
    

    // Create the buttons and add them to the container
    
    Button retourButton = new Button("Retour");
    retourButton.addActionListener(e -> nf.show());
    buttonsContainer.add(retourButton);
    
    // Add the container with the buttons to the form
    addComponent(buttonsContainer);
    
    // Loop over each Livreur object in the list and display its properties
for (Offre Offre : Offres) {
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    container.getAllStyles().setPadding(10, 10, 10, 10);
    container.getAllStyles().setMargin(10, 10, 10, 10);
    container.getAllStyles().setBorder(Border.createLineBorder(2));
 

    Label IdOffreField = new Label("Addresse de arrivee : " + Offre.getIdOffre());
    Label IdTrajetOffreField = new Label("Addresse de depart: " + Offre.getIdTrajetOffre());
    Label LimitekmoffreField = new Label("Limitekmoffre: " + Offre.getDescriptionOffre());  
    Label DescriptionOffreField = new Label("Nombre d' escale : " + Offre.getCatOffreId());  
    Label CatOffreIdField = new Label("Nombre d' escale : " + Offre.getPrixOffre());  
    Label PrixOffreField = new Label("Nombre d' escale : " + Offre.getNbreDemande());  
    Label IdUserField = new Label("Nombre d' escale : " + Offre.getIdUser());  


    container.add(IdOffreField);
    container.add(IdTrajetOffreField);
            container.add(CatOffreIdField);

    container.add(LimitekmoffreField);
        container.add(PrixOffreField);
        container.add(IdUserField);

     ////////////////////////////////////////////////////////////////////////////////////////
            

            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
         
            // Create the "Update" button and add an ActionListener to handle updating the livreur
            Button updateButton = new Button("Demandes");
              updateButton.addActionListener(e -> {
                
                    new DemandeOffre(res,Offre.getIdOffre()).show();
                
            });
      //      updateButton.addActionListener(e -> ml.show());

            // Create a container for the buttons with a BoxLayout set to X_AXIS
            Container buttonsContainers = new Container(new BoxLayout(BoxLayout.X_AXIS));
            buttonsContainers.add(updateButton);

            container.add(buttonsContainers);

            addComponent(container);
        }
}
    
}
