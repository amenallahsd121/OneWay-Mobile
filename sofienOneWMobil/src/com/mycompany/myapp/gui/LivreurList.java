/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.service.ServiceLivreur;
import java.util.ArrayList;
import com.mycompany.myapp.gui.NewsfeedForm;

/**
 *
 * @author amens
 */
public class LivreurList extends BaseForm {

    public LivreurList(Resources res) {
        NewsfeedFormBack nf = new NewsfeedFormBack(res);
        AjouterLivreur al = new AjouterLivreur(res);

        ArrayList<Livreur> livreurs = null;

        ArrayList<Livreur> list = ServiceLivreur.getInstance().getAllLivreur();
        livreurs = list;

        setTitle("Liste des livreurs");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create a container for the buttons with a BoxLayout set to X_AXIS
        Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonsContainer.getAllStyles().setPadding(10, 10, 10, 10);
        buttonsContainer.getAllStyles().setMargin(10, 10, 10, 10);

        // Create the buttons and add them to the container
        Button ajouterButton = new Button("Ajouter Livreur");
        ajouterButton.addActionListener(e -> al.show());
        // Handle adding a new livreur here

        buttonsContainer.add(ajouterButton);

        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> nf.show());
        buttonsContainer.add(retourButton);

        // Add the container with the buttons to the form
        addComponent(buttonsContainer);

        // Loop over each Livreur object in the list and display its properties
        for (Livreur livreur : livreurs) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            container.getAllStyles().setPadding(10, 10, 10, 10);
            container.getAllStyles().setMargin(10, 10, 10, 10);
            container.getAllStyles().setBorder(Border.createLineBorder(2));

            Label CIN = new Label("CIN: " + livreur.getCinLivreur());
            Label nomLabel = new Label("Nom: " + livreur.getNom());
            Label prenomLabel = new Label("Prenom: " + livreur.getPrenom());
            Label Vehicule = new Label("Vehicule: " + livreur.getVehicule());

            container.add(CIN);
            container.add(nomLabel);
            container.add(prenomLabel);
            container.add(Vehicule);
            ////////////////////////////////////////////////////////////////////////////////////////
            

            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            // Create the "Delete" button and add an ActionListener to handle deleting the livreur
            Button deleteButton = new Button("Supprimer");
            deleteButton.addActionListener(e -> {
                boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this livreur?", "Yes", "No");
                if (confirmed) {
                    ServiceLivreur.getInstance().deletelivreur(livreur.getIdlivreur());
                    new LivreurList(res).show();
                }
            });
            
            ModifierLivreur ml = new ModifierLivreur(res, livreur);
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
