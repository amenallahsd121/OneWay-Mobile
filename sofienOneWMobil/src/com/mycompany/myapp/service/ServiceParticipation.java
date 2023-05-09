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
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.Participation;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Meddeb sofien
 */
public class ServiceParticipation {
    
     public ArrayList<Participation> partticipation;

    public static ServiceParticipation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceParticipation() {
        req = new ConnectionRequest();
    }

    public static ServiceParticipation getInstance() {
        if (instance == null) {
            instance = new ServiceParticipation();
        }
        return instance;
    }

    public boolean ajouterParticipation( Participation r , Evenement e) {

        String url = Statics.BASE_URL + "/addparticipation/"+ e.getId_event();

        req.setUrl(url);
        req.setPost(false);

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
    public ArrayList<Participation> getAllParticipation() {
        ArrayList<Participation> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/displayparticipation";
        req.setUrl(url);

       req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
        JSONParser jsonp = new JSONParser();
        try {
            Map<String, Object> maplivreur = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) maplivreur.get("root");
            for (Map<String, Object> obj : listOfMaps) {
                Participation t = new Participation();
                float id = Float.parseFloat(obj.get("id_participation").toString());
                t.setId_participation((int) id);
                t.setId_user(68);
                t.setId_ev(50);
               
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

//
//    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean deleteParticipation(int id) {
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
//
//    //Update 
//    public boolean modifierEvenement(Evenement r) {
//
//String url = Statics.BASE_URL + "/Eventmodify/" + r.getId_event();
//
//    req.setUrl(url);
//    req.setPost(false);
//        req.addArgument("Nom", r.getNom());
//        req.addArgument("Date_Debut", r.getDateString1() + "");
//        req.addArgument("Date_Fin", r.getDateString2() + "");
//        req.addArgument("Description", r.getDescription() + "");
//        req.addArgument("Awards", r.getAwards() + "");
//        
//    req.addResponseListener(new ActionListener<NetworkEvent>() {
//        @Override
//        public void actionPerformed(NetworkEvent evt) {
//            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//            req.removeResponseListener(this);
//        }
//    });
//    NetworkManager.getInstance().addToQueueAndWait(req);
//    return resultOK;
//
//    }
    
}
