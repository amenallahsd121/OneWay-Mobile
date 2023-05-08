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
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amens
 */
public class ServiceReponse {

    public ArrayList<Reponse> reponse;

    public static ServiceReponse instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReponse() {
        req = new ConnectionRequest();
    }

    public static ServiceReponse getInstance() {
        if (instance == null) {
            instance = new ServiceReponse();
        }
        return instance;
    }

    public boolean ajouterReponse(Reponse r) {

        String url = Statics.BASE_URL + "/addreponse";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("text_rep", r.getText_rep());
        req.addArgument("id_reclamation", r.getId_reclamation()+"");
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
    public ArrayList<Reponse> getAllReponse() {
        ArrayList<Reponse> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/displayreponse";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> maplivreur = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) maplivreur.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Reponse r = new Reponse();
                        float id = Float.parseFloat(obj.get("id_reponse").toString());
                        r.setId_reponse((int) id);
                        r.setText_rep(obj.get("text_rep").toString());

                        float idrecla = Float.parseFloat(obj.get("id_reclamation").toString());
                        r.setId_reclamation((int) idrecla);

      

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
    public boolean deletereponse(int id) {
        String url = Statics.BASE_URL + "/reponsedelete/" + id;

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

    public boolean modifierreponse(Reponse r) {

        String url = Statics.BASE_URL + "/reponsemodify/" + r.getId_reponse();

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("text_rep", r.getText_rep());
        req.addArgument("id_reclamation", r.getId_reclamation() + "");
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

    
    
    public Reclamation getReclamationById(int id) {
        Reclamation r = new Reclamation();
        String url = Statics.BASE_URL + "/reclamationser/" + id;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    float id = Float.parseFloat(obj.get("id_reclamation").toString());
                    r.setId_reclamation((int) id);
                    r.setSujet(obj.get("sujet").toString());
                    r.setText_rec(obj.get("text_rec").toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return r;
    }

    
    
    public List<Reclamation> getReclamationsNonReponse(List<Integer> ids) {
    List<Reclamation> reclamations = new ArrayList<>();
    for (Integer id : ids) {
        String url = Statics.BASE_URL + "/reclamationnonrepondu/" + id;
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    float id = Float.parseFloat(obj.get("id_reclamation").toString());
                    Reclamation r = new Reclamation();
                    r.setId_reclamation((int) id);
                    r.setSujet(obj.get("sujet").toString());
                    r.setText_rec(obj.get("text_rec").toString());
                    reclamations.add(r);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    return reclamations;
}
    
    
  public List<Integer> getAllRecNonRep() {
    List<Integer> result = new ArrayList<>();
    ArrayList<Reponse> rep = getAllReponse();
    for (Reponse r : rep) {
        result.add(r.getId_reclamation());
    }
    return result;
}
     
}
