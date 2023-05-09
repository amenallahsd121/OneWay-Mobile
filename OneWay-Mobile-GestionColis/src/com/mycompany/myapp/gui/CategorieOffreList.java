/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.gui.AjouterCategorieOffre;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Categorieoffre;
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.gui.BaseForm;
import com.mycompany.myapp.gui.NewsfeedForm;
import com.mycompany.myapp.service.ServiceCategorieOffre;
import com.mycompany.myapp.service.ServiceCategorieOffre;
import com.mycompany.myapp.service.ServiceLivreur;
import java.util.ArrayList;

/**
 *
 * @author utilisateur
 */
public class CategorieOffreList extends BaseForm {
     public CategorieOffreList(Resources res) {
    NewsfeedFormBack nf = new NewsfeedFormBack(res);
    AjouterCategorieOffre al = new AjouterCategorieOffre(res);
        StatistiquePieForm a2 = new StatistiquePieForm(res);

    ArrayList<Categorieoffre> CategorieOffres = null;
    
    ArrayList<Categorieoffre> list = ServiceCategorieOffre.getInstance().getAllCategorieoffre();
    CategorieOffres = list;
    
    setTitle("Liste des Categories");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    // Create a container for the buttons with a BoxLayout set to X_AXIS
    Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
    buttonsContainer.getAllStyles().setPadding(10, 10, 10, 10);
    buttonsContainer.getAllStyles().setMargin(10, 10, 10, 10);
    
    // Create the buttons and add them to the container
    Button ajouterButtonoffre = new Button("Ajouter Categorie Offre");
    ajouterButtonoffre.addActionListener(e -> al.show()); 
        // Handle adding a new livreur here
    
    buttonsContainer.add(ajouterButtonoffre);
    Button statButtonoffre = new Button("Statistique");
    statButtonoffre.addActionListener(e -> a2.show()); 
        // Handle adding a new livreur here
    
    buttonsContainer.add(statButtonoffre);
    Button retourButton = new Button("Retour");
    retourButton.addActionListener(e -> nf.show());
    buttonsContainer.add(retourButton);
    
    // Add the container with the buttons to the form
    addComponent(buttonsContainer);
    
    // Loop over each Livreur object in the list and display its properties
for (Categorieoffre CategorieOffre : CategorieOffres) {
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    container.getAllStyles().setPadding(10, 10, 10, 10);
    container.getAllStyles().setMargin(10, 10, 10, 10);
    container.getAllStyles().setBorder(Border.createLineBorder(2));

    Label TypeOffre = new Label("Type d' Offre: " + CategorieOffre.getTypeOffre());
    Label PoidsOffre = new Label("poids d' Offre: " + CategorieOffre.getPoidsOffre());
    Label NbreColisOffre = new Label("nombre de Colis par Offre: " + CategorieOffre.getNbreColisOffre());  

    container.add(TypeOffre);
    container.add(PoidsOffre);
    container.add(NbreColisOffre);
     ////////////////////////////////////////////////////////////////////////////////////////
            

            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            Button deleteButton = new Button("Supprimer");
            deleteButton.addActionListener(e -> {
                boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this livreur?", "Yes", "No");
                if (confirmed) {
                    ServiceCategorieOffre.getInstance().deleteCategorieOffre(CategorieOffre.getIdCatOffre());
                    new CategorieOffreList(res).show();
                }
            });
  ModifierCategorieOffre ml = new ModifierCategorieOffre(res, CategorieOffre);
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
