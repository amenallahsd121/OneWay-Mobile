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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Categorieoffre;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author utilisateur
 */
public class ServiceCategorieOffre {
    
    public ArrayList<Categorieoffre> CatOffres;

    public static ServiceCategorieOffre instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCategorieOffre() {
        req = new ConnectionRequest();

    }
  
    public static ServiceCategorieOffre getInstance() {
        if (instance == null) {
            instance = new ServiceCategorieOffre();
        }
        return instance;
    }
      private Categorieoffre parseCategorieOffre(Map<String, Object> json) {
        // Extract the necessary data from the JSON object
        int idcatoffre = (int) Float.parseFloat(json.get("idcatoffre").toString());
        String typeoffre =  json.get("typeoffre").toString();
        float poidsoffre =  Float.parseFloat(json.get("poidsoffre").toString());
        int nbrecolisoffre = (int) Float.parseFloat(json.get("nbrecolisoffre").toString());

        // Create a new CategorieOffre object
            Categorieoffre categorieOffre = new Categorieoffre(idcatoffre,nbrecolisoffre, poidsoffre,typeoffre);

        // Additional parsing and data extraction if needed

        // Return the created CategorieOffre object
        return categorieOffre;
    }
  public Categorieoffre getCategorieOffreById(int id) {
    Categorieoffre result = null;

    // Assuming you have a NetworkRequest instance named "req"
    String url = Statics.BASE_URL + "/findbyid/" + id;
    req.setUrl(url);
    req.setPost(true);

    NetworkManager.getInstance().addToQueueAndWait(req);

    byte[] responseData = req.getResponseData();
    if (responseData != null) {
        JSONParser jsonp = new JSONParser();
        try {
            Map<String, Object> mapCategorie = jsonp.parseJSON(new CharArrayReader(new String(responseData).toCharArray()));
            List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapCategorie.get("root");
            if (!listOfMaps.isEmpty()) {
                Map<String, Object> obj = listOfMaps.get(0);
                result = parseCategorieOffre(obj);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    return result;
}

  


public HashMap<String, Integer> getCategoryCount() {
    ArrayList<Offre> offres = ServiceOffre.getInstance().getAllOffre();

    HashMap<String, Integer> typeOffreCounts = new HashMap<>();
    for (Offre offre : offres) {
        String typeOffre = String.valueOf(offre.getCatOffreId());
        if (typeOffreCounts.containsKey(typeOffre)) {
            typeOffreCounts.put(typeOffre, typeOffreCounts.get(typeOffre) + 1);
        } else {
            typeOffreCounts.put(typeOffre, 1);
        }
    }
    
    return typeOffreCounts;
}
    public boolean ajouterCategorieOffre(Categorieoffre r) {

        String url = Statics.BASE_URL + "/addcatoffre";

        req.setUrl(url);
        req.setPost(true);
        req.addArgument("Typeoffre", r.getTypeOffre());
        req.addArgument("Nbrecolisoffre", r.getNbreColisOffre()+ "");
        req.addArgument("Poidsoffre", r.getPoidsOffre() + "");
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
   public ArrayList<Categorieoffre> getAllCategorieoffre() {
        ArrayList<Categorieoffre> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/displayCategorieOffre";
        req.setUrl(url);

       req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
        JSONParser jsonp = new JSONParser();
        try {
            Map<String, Object> mapCategorieOffre = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapCategorieOffre.get("root");
            for (Map<String, Object> obj : listOfMaps) {
                Categorieoffre t = new Categorieoffre();
 float id = Float.parseFloat(obj.get("idcatoffre").toString());
                t.setIdCatOffre((int) id);
                float nbrecolisoffre = Float.parseFloat(obj.get("nbrecolisoffre").toString());

                t.setNbreColisOffre( (int) nbrecolisoffre);
                t.setPoidsOffre(Float.parseFloat(obj.get("poidsoffre").toString()));
                t.setTypeOffre(obj.get("typeoffre").toString());
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

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public boolean deleteCategorieOffre(int id) {
        String url = Statics.BASE_URL + "/Categorieoffredelete/" + id;

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

    //Update 
    public boolean modifierCategorieoffre(Categorieoffre Categorieoffre) {
       int  IdCatOffre =Categorieoffre.getIdCatOffre();
       String url = Statics.BASE_URL +"/Categorieoffremodify/" + IdCatOffre;

    ConnectionRequest request = new ConnectionRequest(url);
    request.setHttpMethod("PUT");

        request.addArgument("Nombre De Colis", Categorieoffre.getNbreColisOffre()+ "");
        request.addArgument("Poids De Colis ", Categorieoffre.getPoidsOffre() + "");
    NetworkManager.getInstance().addToQueueAndWait(request);

    return request.getResponseCode() == 200;

}
}
