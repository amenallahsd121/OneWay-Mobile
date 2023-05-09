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
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.service.ServiceColis;
import com.mycompany.myapp.service.ServiceLivraison;
import com.mycompany.myapp.service.ServiceLivreur;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amens
 */
public class ListerLivraison extends BaseFormBack {

    public ListerLivraison(Resources res) {
        NewsfeedFormBack nf = new NewsfeedFormBack(res);

        List<Integer> IdColisAffecte = ServiceLivraison.getInstance().IdColisAffecte();

        List<Integer> IdALLColis = ServiceColis.getInstance().IdAllColis();
        List<Integer> IDColisNonAffecte = new ArrayList<>(IdALLColis);

        IDColisNonAffecte.removeAll(IdColisAffecte);

        setTitle("Liste des Livraison");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create a container for the buttons with a BoxLayout set to X_AXIS
        Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonsContainer.getAllStyles().setPadding(10, 10, 10, 10);
        buttonsContainer.getAllStyles().setMargin(10, 10, 10, 10);

        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> nf.show());
        buttonsContainer.add(retourButton);

        addComponent(buttonsContainer);

        ArrayList<Livraison> list = ServiceLivraison.getInstance().getAllLivraison();
        ArrayList<Livraison> livraisonette = null;
        livraisonette = list;

        for (Livraison l : livraisonette) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            container.getAllStyles().setPadding(10, 10, 10, 10);
            container.getAllStyles().setMargin(10, 10, 10, 10);
            container.getAllStyles().setBorder(Border.createLineBorder(2));

            Label etat = new Label("Etat : " + l.getEtat());

            String livra = ServiceLivreur.getInstance().getNomlivreur(l.getId_livreur());
            Label livreur = new Label("Livreur : " + livra);

            Colis coooo = ServiceColis.getInstance().findColisById(l.getId_colis());
            Label Poidss = new Label("Poids : " + coooo.getPoids());
            Label Prixss = new Label("Prix : " + coooo.getPrix());
            Label typoo = new Label("Type : " + coooo.getTypeColis());
            Label LiD = new Label("Lieu Depart : " + coooo.getLdepart());
            Label LiA = new Label("Lieu Arrive : " + coooo.getLarrive());

            container.add(Poidss);
            container.add(Prixss);
            container.add(typoo);
            container.add(LiD);
            container.add(LiA);
            container.add(livreur);
            container.add(etat);

            Button deleteButton = new Button("Supprimer");
            deleteButton.addActionListener(e -> {
                boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this reponse?", "Yes", "No");
                if (confirmed) {
                    ServiceLivraison.getInstance().deleteLivraison(l.getId_livraison());
                    new ListerLivraison(res).show();
                }
            });

/////////////////////////////////////////////////////////////////////////////////////////////
            ModifierLivraison ml = new ModifierLivraison(res, l.getId_livraison());
            Button updateButton = new Button("Modifier");
            updateButton.addActionListener(e -> ml.show());
            Container buttonsContainers = new Container(new BoxLayout(BoxLayout.X_AXIS));
            buttonsContainers.add(deleteButton);
            buttonsContainers.add(updateButton);
            container.add(buttonsContainers);
            addComponent(container);

        }

        List<Colis> coliet = ServiceColis.getInstance().findColisByIds(IDColisNonAffecte);

        for (Colis c : coliet) {

            Container container2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            container2.getAllStyles().setPadding(10, 10, 10, 10);
            container2.getAllStyles().setMargin(10, 10, 10, 10);
            container2.getAllStyles().setBorder(Border.createLineBorder(2));

            Label Prix = new Label("Prix : " + c.getPrix());
            Label Poids = new Label("Poids : " + c.getPoids());
            Label Type = new Label("Type : " + c.getTypeColis());
            Label LD = new Label("Lieu Depart : " + c.getLdepart());
            Label LA = new Label("Lieu Arrivé: " + c.getLarrive());

            container2.add(Poids);
            container2.add(Prix);
            container2.add(Type);
            container2.add(LD);
            container2.add(LA);

            Button addeButton = new Button("Affecter Ce Colis");
            AjouterLivraison al = new AjouterLivraison(res, c.getId());
            addeButton.addActionListener(e -> al.show()
            );
            container2.add(addeButton);

            // Add the container to the main form
            addComponent(container2);

        }

    }
}
