/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amens
 */
public class ServiceCategorie {
    
       


    public static ServiceCategorie instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCategorie() {
        req = new ConnectionRequest();
    }

    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }

    public boolean ajouterCategories(Categorie c) {

        String url = Statics.BASE_URL + "/addcategorie";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("Type", c.getType());
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Categorie> getAllCategorie() {
        ArrayList<Categorie> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/displaycategorie";
        req.setUrl(url);

       req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
        JSONParser jsonp = new JSONParser();
        try {
            Map<String, Object> maplivreur = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) maplivreur.get("root");
            for (Map<String, Object> obj : listOfMaps) {
                Categorie t = new Categorie();
                float id = Float.parseFloat(obj.get("idCategorie").toString());
                t.setId_categorie((int) id);
                t.setType(obj.get("type").toString());

                result.add(t);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
});


        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;

    }

/////////////////////////////////////////////////////////////////
    
   public boolean modifierCategorie(Categorie c) {
       String url = Statics.BASE_URL + "/categoriemodify/" + c.getId_categorie();
      

    ConnectionRequest request = new ConnectionRequest(url);
    request.setHttpMethod("PUT");
    request.addArgument("Typee", c.getType());
    

    NetworkManager.getInstance().addToQueueAndWait(request);

    return request.getResponseCode() == 200;

}


////////////////////////////////////////////////////////////////////

    
      public boolean deletecategorie(int id) {
        String url = Statics.BASE_URL + "/categoriedelete/" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      
      
      
      public ArrayList<String> getAllCategorieTypes() {
    ArrayList<String> result = new ArrayList<>();

    // Call getAllCategorie to get a list of Categorie objects
    ArrayList<Categorie> categories = getAllCategorie();

    // Extract the type field from each Categorie object and add it to the result list
    for (Categorie categorie : categories) {
        result.add(categorie.getType());
    }

    return result;
}

    
}
