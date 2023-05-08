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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.service.ServiceReclamation;
import com.mycompany.myapp.service.ServiceReponse;

/**
 *
 * @author amens
 */
public class AjouterReponse extends BaseForm{
    
    
    
     public AjouterReponse(Resources res , int id) {
         setTitle("Ajouter une Reponse");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));


        
        
        TextField text_repField = new TextField("", " text_rep", 40, TextField.ANY);
     
       

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        text_repField.setUnselectedStyle(textFieldStyle);
       
        

    
        
        Button RepondreButton = new Button("Repondre");
        RepondreButton.addActionListener(e -> {
 
            
           
          
            String text_rep = text_repField.getText();
          

      
            Reponse reponse = new Reponse(text_rep, id);

           
          ServiceReponse.getInstance().ajouterReponse(reponse);

            // Show a confirmation dialog
            Dialog.show("Succès", "La reponse a été ajouté avec succès", "OK", null);

           
            new Reponselist(res).showBack();
        });

        // Add the text fields and button to the form
     
        addComponent(text_repField);
      
        addComponent(RepondreButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new Reponselist(res).showBack());
        addComponent(retourButton);
    }
    
    
}
