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
import com.mycompany.myapp.entities.Maintenance;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hp
 */
public class ServiceMaintenance {
    
   public ArrayList<Maintenance> maintenance;

    public static ServiceMaintenance instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceMaintenance() {
        req = new ConnectionRequest();
    }

    public static ServiceMaintenance getInstance() {
        if (instance == null) {
            instance = new ServiceMaintenance();
        }
        return instance;
    }

    public boolean AjouterMaintenance(Maintenance r) {

        String url = Statics.BASE_URL + "/addmaintenance";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("Nom_sos_rep", r.getNom_sos_rep());
        req.addArgument("Etat", r.getEtat() + "");
        req.addArgument("Id_vehicule", r.getId_vehicule() + "");
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
    public ArrayList<Maintenance> getAllMaintenance() {
        ArrayList<Maintenance> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/displaymaintenance";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> maplivreur = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) maplivreur.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Maintenance r = new Maintenance();
                        float id = Float.parseFloat(obj.get("id_maintenance").toString());
                        r.setId_maintenance((int) id);
                        r.setNom_sos_rep(obj.get("Nom_sos_rep").toString());
                        r.setEtat(obj.get("Etat").toString());
                        r.setId_vehicule(Integer.parseInt(obj.get("Id_vehicule").toString()));


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
    public boolean deletemaintenance(int id) {
        String url = Statics.BASE_URL + "/maintenancedelete/" + id;

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

  public boolean ModifierMaintenance(Maintenance r) {

    String url = Statics.BASE_URL + "/maintenancemodify/" + r.getId_maintenance();

    req.setUrl(url);
    req.setPost(false);
     req.addArgument("Nom_sos_rep", r.getNom_sos_rep());
        req.addArgument("Etat", r.getEtat());
     req.addArgument("Id_vehicule", Integer.toString(r.getId_vehicule()));
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


  
  
  public List<Integer> getAllReclamationIds() {
    List<Integer> result = new ArrayList<>();
    ArrayList<Maintenance> reclamations = getAllMaintenance();
    for (Maintenance r : maintenance) {
        result.add(r.getId_maintenance());
    }
    return result;
}
    
}
