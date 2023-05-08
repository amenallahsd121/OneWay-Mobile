package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Colis;
import com.mycompany.myapp.service.ServiceColis;

/**
 * author amens
 */
public class AjouterColis extends BaseForm {

    public AjouterColis(Resources res) {
        setTitle("Ajouter Colis");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        TextField Poidsf = new TextField("", "Poids", 40, TextField.ANY);

        ComboBox<String> typeBox = new ComboBox<>("Agro-Alimentaire", "Matériel Electronique", "Meubles", "Pièces Automobiles");
        typeBox.setSelectedItem("Type Colis");

        ComboBox<String> lieudBox = new ComboBox<>("Tunis", "Sousse", "Gabes", "Bizerte");
        lieudBox.setSelectedItem("Lieu Depart");
        ComboBox<String> lieuaBox = new ComboBox<>("Tunis", "Sousse", "Gabes", "Bizerte");
        lieuaBox.setSelectedItem("Lieu Arrive");

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        Poidsf.setUnselectedStyle(textFieldStyle);

        Button ajouterButton = new Button("Ajouter");
        ajouterButton.addActionListener(e -> {

            String Poids = Poidsf.getText();
            float poidsFloat = Float.parseFloat(Poids);
            float Prix = poidsFloat*3 ;
            String type = typeBox.getSelectedItem();
            String depart = lieudBox.getSelectedItem();
            String arrivee = lieuaBox.getSelectedItem();
         

            Colis colis = new Colis(poidsFloat,Prix,type,depart,arrivee);

            // Add the Colis using the ServiceColis
            ServiceColis.getInstance().ajouterCols(colis);

            // Show a confirmation dialog
            Dialog.show("Succès", "Le colis a été ajouté avec succès", "OK", null);

            // Go back to the ColisList form
            new ColisList(res).showBack();
        });

        // Add the text fields and checkboxes to the form
        addComponent(Poidsf);
        addComponent(typeBox);
        addComponent(lieudBox);
        addComponent(lieuaBox);
        addComponent(ajouterButton);
        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new ColisList(res).showBack());
        addComponent(retourButton);
    }
}
