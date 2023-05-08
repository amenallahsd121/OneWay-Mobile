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
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.service.ServiceReclamation;
import com.mycompany.myapp.service.ServiceReponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amens
 */
public class Reponselist extends BaseForm {

    public Reponselist(Resources res) {
        NewsfeedFormBack nf = new NewsfeedFormBack(res);
      

        
        List<Integer> test = ServiceReponse.getInstance().getAllRecNonRep();
        List<Integer> allReclamationIds = ServiceReclamation.getInstance().getAllReclamationIds();
        List<Integer> IDnontraite = new ArrayList<>();
        
        
         

            for (int i=0 ; i<allReclamationIds.size() ; i++)
       {
         boolean var=true;  
           for(int j=0 ; j<test.size() && var==true; j++ )
           {
               if(allReclamationIds.get(i) != test.get(j))
                var=true;
               else
                   var=false;

           }
           if (var==true)
              
           
           IDnontraite.add(allReclamationIds.get(i));   
       }
       
             
         
                     

             for (int i = 0; i < test.size(); i++) {
    for (int j = 0; j < IDnontraite.size(); j++) {
        if (test.get(i).equals(IDnontraite.get(j))) {
            IDnontraite.remove(j);
            j--; // to adjust the index after removing the element
        }
    }
}


                     
        setTitle("Liste des reponse");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create a container for the buttons with a BoxLayout set to X_AXIS
        Container buttonsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        buttonsContainer.getAllStyles().setPadding(10, 10, 10, 10);
        buttonsContainer.getAllStyles().setMargin(10, 10, 10, 10);

      

       


        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> nf.show());
        buttonsContainer.add(retourButton);

      
        addComponent(buttonsContainer);
        
        ////////////////////////////////////////////////////////////////////////
        
        
       
        
       ArrayList<Reponse> list = ServiceReponse.getInstance().getAllReponse(); 
        ArrayList<Reponse> reponsee = null;
        reponsee = list;
       
        
        
       /////////////////////////////////////////////////////////////////////////// 
        
    
       
       
        
        for (Reponse r : reponsee) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            container.getAllStyles().setPadding(10, 10, 10, 10);
            container.getAllStyles().setMargin(10, 10, 10, 10);
            container.getAllStyles().setBorder(Border.createLineBorder(2));

            
                

                Label texterep = new Label("Texte Réponse : " + r.getText_rep());
                Reclamation recla = ServiceReponse.getInstance().getReclamationById(r.getId_reclamation());
                String texterecla = recla.getText_rec();
                String sujett = recla.getSujet();
                Label textereclamation = new Label("Texte Réclamation : " + texterecla);
                Label sujetreclamation = new Label("Sujet : " + sujett);

                container.add(sujetreclamation);
                container.add(textereclamation);
                container.add(texterep);

                Button deleteButton = new Button("Supprimer");
                deleteButton.addActionListener(e -> {
                    boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this reponse?", "Yes", "No");
                    if (confirmed) {
                        ServiceReponse.getInstance().deletereponse(r.getId_reponse());
                        new Reponselist(res).show();
                    }
                });

/////////////////////////////////////////////////////////////////////////////////////////////
                ModifierReponse ml = new ModifierReponse(res, r);
               
                Button updateButton = new Button("Modifier");
                updateButton.addActionListener(e -> ml.show());
                Container buttonsContainers = new Container(new BoxLayout(BoxLayout.X_AXIS));
                buttonsContainers.add(deleteButton);
                buttonsContainers.add(updateButton);
                container.add(buttonsContainers);
                addComponent(container);
                
               
                
            
            }
            
            
          
            List<Reclamation> reclamations = ServiceReponse.getInstance().getReclamationsNonReponse(IDnontraite);
            
            
           for (Reclamation recla : reclamations) {

                Container container2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                container2.getAllStyles().setPadding(10, 10, 10, 10);
                container2.getAllStyles().setMargin(10, 10, 10, 10);
                container2.getAllStyles().setBorder(Border.createLineBorder(2));

                Label sujetLabel = new Label("Sujet : " + recla.getSujet());
                Label textRecLabel = new Label("Texte Réclamation : " + recla.getText_rec());
                container2.add(sujetLabel);
                container2.add(textRecLabel);

                Button repondreButton = new Button("Répondre");
                  AjouterReponse al = new AjouterReponse(res,recla.getId_reclamation());
                repondreButton.addActionListener(e -> al.show()
                    // Handle response action here
                    // You can use the Reclamation object 'r' to get the ID and other data needed
                );
                container2.add(repondreButton);

                // Add the container to the main form
                addComponent(container2);

           }

         

        

    

}
}
