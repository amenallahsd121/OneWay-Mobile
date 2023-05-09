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
import com.mycompany.myapp.entities.Colis;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amens
 */
public class ServiceColis {

    public ArrayList<Colis> colis;

    public static ServiceColis instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceColis() {
        req = new ConnectionRequest();
    }

    public static ServiceColis getInstance() {
        if (instance == null) {
            instance = new ServiceColis();
        }
        return instance;
    }

    public boolean ajouterCols(Colis c) {

        String url = Statics.BASE_URL + "/addcolis";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("poids", c.getPoids() + "");
        req.addArgument("prix", c.getPrix() + "");
        req.addArgument("typeColis", c.getTypeColis());
        req.addArgument("lieuDepart", c.getLdepart());
        req.addArgument("lieuArrive", c.getLarrive());
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
    public ArrayList<Colis> getAllColis() {
        ArrayList<Colis> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/displaycolis";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> maplivreur = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) maplivreur.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Colis c = new Colis();
                        float id = Float.parseFloat(obj.get("id_colis").toString());
                        c.setId((int) id);
                        c.setPoids(Float.parseFloat(obj.get("poids").toString()));
                        c.setPrix(Float.parseFloat(obj.get("prix").toString()));
                        c.setTypeColis(obj.get("typeColis").toString());
                        c.setLdepart(obj.get("lieuDepart").toString());
                        c.setLarrive(obj.get("lieuArrive").toString());
                        result.add(c);
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
    public boolean deletecolis(long id) {
        String url = Statics.BASE_URL + "/colisdelete/" + id;

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
    public boolean modifierColis(Colis c) {

        String url = Statics.BASE_URL + "/colismodify/" + c.getId();

        ConnectionRequest request = new ConnectionRequest(url);
        request.setHttpMethod("PUT");
        req.addArgument("poids", c.getPoids() + "");
        req.addArgument("prix", c.getPrix() + "");
        req.addArgument("typeColis", c.getTypeColis());
        req.addArgument("lieuDepart", c.getLdepart());
        req.addArgument("lieuArrive", c.getLarrive());

        NetworkManager.getInstance().addToQueueAndWait(request);

        return request.getResponseCode() == 200;

    }

    public List<Integer> IdAllColis() {
        List<Integer> result = new ArrayList<>();
        ArrayList<Colis> coliette = getAllColis();
        for (Colis c : coliette) {
            result.add(c.getId());
        }
        return result;
    }
    
    
    
public List<Colis> findColisByIds(List<Integer> colisIds) {
    List<Colis> result = new ArrayList<>();
    ArrayList<Colis> colisList = getAllColis();
    for (Colis colis : colisList) {
        if (colisIds.contains(colis.getId())) {
            result.add(colis);
        }
    }
    return result;
}

    public Colis findColisById(int colisId) {
    ArrayList<Colis> colisList = getAllColis();
    for (Colis colis : colisList) {
        if (colis.getId()== colisId) {
            return colis;
        }
    }
    return null;
}
}
