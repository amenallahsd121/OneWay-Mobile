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
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.utils.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author amens
 */
public class ServiceLivreur {

    public ArrayList<Livreur> livreurs;

    public static ServiceLivreur instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceLivreur() {
        req = new ConnectionRequest();
    }

    public static ServiceLivreur getInstance() {
        if (instance == null) {
            instance = new ServiceLivreur();
        }
        return instance;
    }

    public boolean ajouterLivreurs(Livreur r) {

        String url = Statics.BASE_URL + "/addlivreur";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("Nom", r.getNom());
        req.addArgument("Prenom", r.getPrenom() + "");
        req.addArgument("CinLivreur", r.getCinLivreur() + "");
        req.addArgument("Vehicule", r.getVehicule() + "");
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
    public ArrayList<Livreur> getAllLivreur() {
        ArrayList<Livreur> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/displaylivreur";
        req.setUrl(url);

       req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
        JSONParser jsonp = new JSONParser();
        try {
            Map<String, Object> maplivreur = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) maplivreur.get("root");
            for (Map<String, Object> obj : listOfMaps) {
                Livreur t = new Livreur();
                float id = Float.parseFloat(obj.get("idLivreur").toString());
                t.setIdlivreur((int) id);
                t.setCinLivreur(obj.get("cinLivreur").toString());
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
                t.setVehicule(obj.get("vehicule").toString());
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
    
    public boolean deletelivreur(int id) {
        String url = Statics.BASE_URL + "/livreurdelete/" + id;

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
    public boolean modifierLivreur(Livreur livreur) {
       String url = Statics.BASE_URL + "/livreurmodify/" + livreur.getIdlivreur();

    ConnectionRequest request = new ConnectionRequest(url);
    request.setHttpMethod("PUT");
    request.addArgument("CinLivreur", livreur.getCinLivreur());
    request.addArgument("Nom", livreur.getNom());
    request.addArgument("Prenom", livreur.getPrenom());
    request.addArgument("Vehicule", livreur.getVehicule());

    NetworkManager.getInstance().addToQueueAndWait(request);

    return request.getResponseCode() == 200;

}
    
    
    
    
    
      public String getNomlivreur(int idlivreur) {
    String result = null;
    ArrayList<Livreur> l = getAllLivreur();
    for (Livreur liv : l) {
        if(idlivreur == liv.getIdlivreur() )
        {
           result=liv.getPrenom(); 
        }
        
        
    }
    return result;
}
      
public List<String> getAllNomlivreur() {
    List<String> result = new ArrayList<>();
    ArrayList<Livreur> livreurList = getAllLivreur();
    for (Livreur livreur : livreurList) {
        
            result.add(livreur.getPrenom());
        
    }
    return result;
}

      


public int getIdlivreur(String Prenom) {
    Integer result = null;
    ArrayList<Livreur> livreurList = getAllLivreur();
    for (Livreur livreur : livreurList) {
        if (livreur.getPrenom().equals(Prenom)) {
            result = livreur.getIdlivreur();
            return result;
        }
    }
    return result;
}


      
      
}
