/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.TrajetOffre;
import com.mycompany.myapp.service.ServiceTrajetOffre;

/**
 *
 * @author utilisateur
 */
public class ModifierTrajetOffre extends BaseForm{
    boolean checkifstringisnumber(String s) {
        try {
            int i;
            i = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    
}
     public ModifierTrajetOffre(Resources res,TrajetOffre TrajetOffre) {
        
         setTitle("Modifier un Trajet pour Offre");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
   
        TextField AddarriveoffreField = new TextField("", "Addresse de arrivee ", 40, TextField.ANY);
        TextField AdddepartoffreField = new TextField("", "Addresse de depart", 40, TextField.ANY);
        TextField LimitekmoffreField = new TextField("", "Limitekmoffre", 40, TextField.ANY);
        TextField NbreescaleoffreField = new TextField("", "Limitekmoffre", 40, TextField.ANY);

        

        Style textFieldStyle = new Style();
         textFieldStyle.setFgColor(0x000000); // black color
        AddarriveoffreField.setUnselectedStyle(textFieldStyle);
        AdddepartoffreField.setUnselectedStyle(textFieldStyle);
        LimitekmoffreField.setUnselectedStyle(textFieldStyle);
        NbreescaleoffreField.setUnselectedStyle(textFieldStyle);
    
        // Create a button to save the modifications
        Button modifierButton = new Button("Modifier");
        modifierButton.addActionListener(e -> {
            // Get the new values from the text fields
             if (AddarriveoffreField.getText().isEmpty() || AdddepartoffreField.getText().isEmpty() || LimitekmoffreField.getText().isEmpty() ) {

                Dialog.show("Error", "Remplir Vos Champs", "No", null);
            } else if (AddarriveoffreField.getText().length() < 2) {
                Dialog.show("Error", "Addresse d'arriveoffre Doit Etre Composer au minumun  de 2 Caractere", "No", null);

            } else if (AdddepartoffreField.getText().length() < 2) {
                Dialog.show("Error", "Addresse de departoffre Doit Etre Composer au minimum de 2 Caractere", "No", null);

            } else if (AddarriveoffreField.getText().equals(AdddepartoffreField.getText()) == true) {
                Dialog.show("Error", "LES deux addaresse dans identique Vous Devez changez", "No", null);

            } else if (this.checkifstringisnumber(LimitekmoffreField.getText()) == false) {
                Dialog.show("Error", "Limite de km  Doit Etre un nombre exp :1,2,3..", "No", null);

            }else {
         int Nbreescaleoffre = Integer.parseInt(NbreescaleoffreField.getText());
            int Limitekmoffre = Integer.parseInt(LimitekmoffreField.getText());
            String Adddepartoffre = AdddepartoffreField.getText();
            String Addarriveoffre = AddarriveoffreField.getText();

            // Create a new Livreur object with the new values
            TrajetOffre CategorieOffre = new TrajetOffre(Limitekmoffre, Nbreescaleoffre,Addarriveoffre,Adddepartoffre);
            // Call the modifierLivreur method of ServiceLivreur to modify the Livreur
            if (ServiceTrajetOffre.getInstance().modifierTrajetOffre(TrajetOffre)) {
                // Show a confirmation dialog if the modification was successful
                Dialog.show("Succès", "Le Trajet  a été modifié avec succès", "OK", null);
            } else {
                Dialog.show("Erreur", "Une erreur s'est produite lors de la modification", "OK", null);
            }

            // Go back to the LivreurList form
            new TrajetOffreList(res).showBack();
            } });


        // Add the text fields and button to the form
       
  addComponent(AdddepartoffreField);
        addComponent(AddarriveoffreField);
        addComponent(LimitekmoffreField);   
                addComponent(NbreescaleoffreField);
        addComponent(modifierButton);


        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new TrajetOffreList(res).showBack());
        addComponent(retourButton);
    }
    
    
}
