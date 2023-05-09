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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amens
 */
public class ServiceEvenement {

    public ArrayList<Evenement> evenement;

    public static ServiceEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }

    public boolean ajouterEvenement(Evenement r) {

        String url = Statics.BASE_URL + "/addevenement";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("Nom", r.getNom());
        req.addArgument("Date_Debut", r.getDateString1() + "");
        req.addArgument("Date_Fin", r.getDateString2() + "");
        req.addArgument("Description", r.getDescription() + "");
        req.addArgument("Awards", r.getAwards() + "");
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
    public ArrayList<Evenement> getAllEvenement() {
         ArrayList<Evenement> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/displayevenement";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> maplivreur = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) maplivreur.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Evenement r = new Evenement();
                        float id = Float.parseFloat(obj.get("id_event").toString());
                        r.setId_event((int) id);
                        r.setNom(obj.get("nom").toString());
                        r.setAwards(obj.get("awards").toString());
                        r.setDescription(obj.get("description").toString());
                   

                        result.add(r);
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
    public boolean deleteEvenement(int id) {
        String url = Statics.BASE_URL + "/evenementdelete/" + id;

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
    public boolean modifierEvenement(Evenement r) {

String url = Statics.BASE_URL + "/Eventmodify/" + r.getId_event();

    req.setUrl(url);
    req.setPost(false);
        req.addArgument("Nom", r.getNom());
        req.addArgument("Date_Debut", r.getDateString1() + "");
        req.addArgument("Date_Fin", r.getDateString2() + "");
        req.addArgument("Description", r.getDescription() + "");
        req.addArgument("Awards", r.getAwards() + "");
        
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
    
    
    
    ////////Search/////////////////////////////////////////
    
    public ArrayList<Evenement> SearchEvenement(String r) {

 
           

        String url = Statics.BASE_URL + "/searchMobile/"+r;
        
        ArrayList<Evenement> result = new ArrayList<>();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> maplivreur = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) maplivreur.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Evenement re = new Evenement();
                        float id = Float.parseFloat(obj.get("id_event").toString());
                        re.setId_event((int) id);
                        re.setNom(obj.get("nom").toString());
                        re.setAwards(obj.get("awards").toString());
                        re.setDescription(obj.get("description").toString());
                   

                        result.add(re);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println("--------------------------------------------"+result);

        return result;
      
    

}
}



