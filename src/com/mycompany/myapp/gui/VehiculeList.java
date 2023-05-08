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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.Vehicule;
import com.mycompany.myapp.service.ServiceReclamation;
import com.mycompany.myapp.service.ServiceVehicule;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class VehiculeList extends BaseForm {
    
    
     public VehiculeList(Resources res) {
    NewsfeedFormBack nf = new NewsfeedFormBack(res);
    AjouterVehicule al = new AjouterVehicule(res);
    
    
    
    
    ArrayList<Vehicule> vehicule = null;
    
    ArrayList<Vehicule> list = ServiceVehicule.getInstance().getAllVehicule();
    vehicule = list;
      
    
    setTitle("Liste des reclamations");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    // Create a container for the buttons with a BoxLayout set to X_AXIS
    Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
    buttonsContainer.getAllStyles().setPadding(10, 10, 10, 10);
    buttonsContainer.getAllStyles().setMargin(10, 10, 10, 10);
    
    // Create the buttons and add them to the container
    Button ajouterButton = new Button("Ajouter Vehicule");
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
for ( Vehicule Vehicule : vehicule) {
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    container.getAllStyles().setPadding(10, 10, 10, 10);
    container.getAllStyles().setMargin(10, 10, 10, 10);
    container.getAllStyles().setBorder(Border.createLineBorder(2));

    
    
   // Label TypeLabel = new Label("Type : " + Vehicule.get());
    Label Matricule = new Label("Matricule: " + Vehicule.getMatricule());
    Label MarqueLabel = new Label("Marque : " + Vehicule.getMarque());
    
    


    
   

   
    container.add(MarqueLabel);
       container.add(Matricule);
   
    ////////////////////////////////////////////////////////////////////////////////////////

       
        
        
       

    
    // Create the "Delete" button and add an ActionListener to handle deleting the livreur
  // Create the "Delete" button and add an ActionListener to handle deleting the livreur
Button deleteButton = new Button("Supprimer");
deleteButton.addActionListener(e -> {
    boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this vehicule?", "Yes", "No");
    if (confirmed) {
        ServiceVehicule.getInstance().deletevehicule(Vehicule.getId_vehicule());
        new VehiculeList(res).show();
    }
});


/////////////////////////////////////////////////////////////////////////////////////////////
   ModifierVehicule ml = new ModifierVehicule(res,Vehicule);
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
