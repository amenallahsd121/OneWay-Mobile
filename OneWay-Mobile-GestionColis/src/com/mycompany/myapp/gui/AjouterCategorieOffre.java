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
import com.mycompany.myapp.entities.Categorieoffre;
import com.mycompany.myapp.gui.BaseForm;
import com.mycompany.myapp.gui.CategorieOffreList;
import com.mycompany.myapp.gui.LivreurList;
import com.mycompany.myapp.service.ServiceCategorieOffre;

/**
 *
 * @author utilisateur
 */
public class AjouterCategorieOffre extends BaseForm  {
    boolean checkifstringisfloat(String s) {
        try {
            float i;
            i =  Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to float.");
        }
        return false;
    }  
    boolean checkifstringisnumber(String s) {
        try {
            int i;
            i =  Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to int.");
        }
        return false;
    }
    public AjouterCategorieOffre (Resources res) {
  
         setTitle("Ajouter une Categorie pour Offre");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));


        
        TextField nbreColisOffreField = new TextField("", "nbreColisOffre", 40, TextField.ANY);
        TextField poidsOffreField = new TextField("", "poids d' Offre", 40, TextField.ANY);
        TextField TypeOffreField = new TextField("", "Type d' Offre:", 40, TextField.ANY);

          Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        nbreColisOffreField.setUnselectedStyle(textFieldStyle);
        poidsOffreField.setUnselectedStyle(textFieldStyle);
        TypeOffreField.setUnselectedStyle(textFieldStyle);
           

    
     

    
        
        Button ajouterButton = new Button("Ajouter");
        ajouterButton.addActionListener(e -> {
 
          if (nbreColisOffreField.getText().isEmpty() || poidsOffreField.getText().isEmpty() || TypeOffreField.getText().isEmpty() ) {

                Dialog.show("Error", "Remplir Vos Champs", "No", null);
            } else if (TypeOffreField.getText().length()> 3) {
                Dialog.show("Error", "Le Type D'offre Doit Etre Composer maximux de 3 Caractere", "No", null);

            } else if (this.checkifstringisfloat(poidsOffreField.getText()) == false) {
                Dialog.show("Error", "Le poidsOffre Doit Etre De type float exp :10.0", "No", null);

            } else if (this.checkifstringisnumber(nbreColisOffreField.getText()) == false) {
                Dialog.show("Error", "Le nombre De colis Doit Etre un nombre exp :1,2,3..", "No", null);

            }else {
   
            
        int nbreColisOffre = Integer.parseInt(nbreColisOffreField.getText());
            float poidsOffre = Float.parseFloat(poidsOffreField.getText());
            String TypeOffre = TypeOffreField.getText();

            // Create a new Livreur object
            Categorieoffre CategorieOffre = new Categorieoffre(nbreColisOffre, poidsOffre, TypeOffre);

            // Add the Livreur using the LivreurService
            ServiceCategorieOffre.getInstance().ajouterCategorieOffre(CategorieOffre);

            // Show a confirmation dialog
            Dialog.show("Succès", "La categorie  a été ajouté avec succès", "OK", null);

            // Go back to the LivreurList form
            new CategorieOffreList(res).showBack();
            }  });

        // Add the text fields and button to the form
       
  addComponent(nbreColisOffreField);
        addComponent(TypeOffreField);
        addComponent(poidsOffreField);       
        addComponent(ajouterButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new CategorieOffreList(res).showBack());
        addComponent(retourButton);
    }
}
