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
import com.mycompany.myapp.entities.Demande;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author utilisateur
 */
public class ServiceDemande {

    public ArrayList<Demande> Demandes;

    public static ServiceDemande instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceDemande() {
        req = new ConnectionRequest();
    }

    public static ServiceDemande getInstance() {
        if (instance == null) {
            instance = new ServiceDemande();
        }
        return instance;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Demande> getAllDemande(int id) {
        ArrayList<Demande> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/displayDemande/" + id;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> mapDemande = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapDemande.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Demande t = new Demande();
                        System.out.println(t);
                        float id = Float.parseFloat(obj.get("iddemande").toString());
                        System.out.println(id);

                        t.setIdDemande((int) id);

                        t.setDescriptionDemande(obj.get("descriptiondemande").toString());
                      
                   

                        float prix = Float.parseFloat(obj.get("prix").toString());

                        t.setPrix(prix);

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

}
