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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amens
 */
public class ServiceReclamation {

    public ArrayList<Reclamation> reclamation;

    public static ServiceReclamation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public boolean ajouterReclamation(Reclamation r) {

        String url = Statics.BASE_URL + "/addreclamationmobile";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("text_rec", r.getText_rec());
        req.addArgument("sujet", r.getSujet());
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
    public ArrayList<Reclamation> getAllReclamation() {
        ArrayList<Reclamation> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/displayreclamation";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> maplivreur = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) maplivreur.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Reclamation r = new Reclamation();
                        float id = Float.parseFloat(obj.get("id_reclamation").toString());
                        r.setId_reclamation((int) id);
                        r.setSujet(obj.get("sujet").toString());
                        r.setText_rec(obj.get("text_rec").toString());

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
    public boolean deletereclamation(int id) {
        String url = Statics.BASE_URL + "/reclamationdelete/" + id;

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

  public boolean modifierReclamation(Reclamation r) {

    String url = Statics.BASE_URL + "/reclamationmodify/" + r.getId_reclamation();

    req.setUrl(url);
    req.setPost(false);
    req.addArgument("text_rec", r.getText_rec());
    req.addArgument("sujet", r.getSujet() + "");
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
    ArrayList<Reclamation> reclamations = getAllReclamation();
    for (Reclamation r : reclamations) {
        result.add(r.getId_reclamation());
    }
    return result;
}

  
  
}
