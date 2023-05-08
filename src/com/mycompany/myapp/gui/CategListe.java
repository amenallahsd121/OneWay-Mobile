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
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.service.ServiceCategorie;
import java.util.ArrayList;

/**
 *
 * @author amens
 */
public class CategListe extends BaseForm {

    public CategListe(Resources res) {
        NewsfeedFormBack nf = new NewsfeedFormBack(res);
        AjouterCategorie al = new AjouterCategorie(res);

        ArrayList<Categorie> categories = null;

        ArrayList<Categorie> list = ServiceCategorie.getInstance().getAllCategorie();
        categories = list;
        System.out.println(list);
       

        setTitle("Liste des Categ");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create a container for the buttons with a BoxLayout set to X_AXIS
        Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonsContainer.getAllStyles().setPadding(10, 10, 10, 10);
        buttonsContainer.getAllStyles().setMargin(10, 10, 10, 10);

        // Create the buttons and add them to the container
        Button ajouterButton = new Button("Ajouter Categ");
        ajouterButton.addActionListener(e -> al.show());
        // Handle adding a new livreur here

        buttonsContainer.add(ajouterButton);

        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> nf.show());
        buttonsContainer.add(retourButton);

        // Add the container with the buttons to the form
        addComponent(buttonsContainer);

        // Loop over each Livreur object in the list and display its properties
        for (Categorie c : categories) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            container.getAllStyles().setPadding(10, 10, 10, 10);
            container.getAllStyles().setMargin(10, 10, 10, 10);
            container.getAllStyles().setBorder(Border.createLineBorder(2));

            Label Type = new Label("Type: " + c.getType());
          

            container.add(Type);
           
            ////////////////////////////////////////////////////////////////////////////////////////
            

            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            Button deleteButton = new Button("Supprimer");
            deleteButton.addActionListener(e -> {
                boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this categorie?", "Yes", "No");
                if (confirmed) {
                    ServiceCategorie.getInstance().deletecategorie(c.getId_categorie());
                    new CategListe(res).show();
                }
                
            });
            
            ModifierCategorie ml = new ModifierCategorie(res, c);
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
