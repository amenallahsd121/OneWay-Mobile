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
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.service.ServiceLivreur;
import com.mycompany.myapp.service.ServiceReclamation;
import java.util.ArrayList;

/**
 *
 * @author amens
 */
public class Reclamationlist extends BaseForm {
    
     public Reclamationlist(Resources res) {
    NewsfeedForm nf = new NewsfeedForm(res);
    AjouterReclamation al = new AjouterReclamation(res);
    
    
    
    
    ArrayList<Reclamation> reclamations = null;
    
    ArrayList<Reclamation> list = ServiceReclamation.getInstance().getAllReclamation();
    reclamations = list;
    
    setTitle("Liste des reclamations");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    // Create a container for the buttons with a BoxLayout set to X_AXIS
    Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
    buttonsContainer.getAllStyles().setPadding(10, 10, 10, 10);
    buttonsContainer.getAllStyles().setMargin(10, 10, 10, 10);
    
    // Create the buttons and add them to the container
    Button ajouterButton = new Button("Ajouter Reclamation");
   ajouterButton.addActionListener(e -> al.show()); 
        // Handle adding a new livreur here
    
        
      
        buttonsContainer.add(ajouterButton);
        
        
//    buttonsContainer.add(ajouterButton);
    
    Button retourButton = new Button("Retour");
    retourButton.addActionListener(e -> nf.show());
    buttonsContainer.add(retourButton);
    
    // Add the container with the buttons to the form
    addComponent(buttonsContainer);
    
    // Loop over each Livreur object in the list and display its properties
for ( Reclamation reclamation : reclamations) {
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    container.getAllStyles().setPadding(10, 10, 10, 10);
    container.getAllStyles().setMargin(10, 10, 10, 10);
    container.getAllStyles().setBorder(Border.createLineBorder(2));

    Label CIN = new Label("Sujet: " + reclamation.getSujet());
    Label nomLabel = new Label("Texte de Reclamation : " + reclamation.getText_rec());
   

    container.add(CIN);
    container.add(nomLabel);
   
    ////////////////////////////////////////////////////////////////////////////////////////

       
        
        
       

    
    // Create the "Delete" button and add an ActionListener to handle deleting the livreur
  // Create the "Delete" button and add an ActionListener to handle deleting the livreur
Button deleteButton = new Button("Supprimer");
deleteButton.addActionListener(e -> {
    boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this livreur?", "Yes", "No");
    if (confirmed) {
        ServiceReclamation.getInstance().deletereclamation(reclamation.getId_reclamation());
        new Reclamationlist(res).show();
    }
});


/////////////////////////////////////////////////////////////////////////////////////////////
   ModifierReclamation ml = new ModifierReclamation(res,reclamation);
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
