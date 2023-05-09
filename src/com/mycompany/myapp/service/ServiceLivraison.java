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
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.gui.BaseFormBack;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amens
 */
public class ServiceLivraison extends BaseFormBack {

    public ArrayList<Livraison> livr;

    public static ServiceLivraison instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceLivraison() {
        req = new ConnectionRequest();
    }

    public static ServiceLivraison getInstance() {
        if (instance == null) {
            instance = new ServiceLivraison();
        }
        return instance;
    }

    public boolean AjouterLivraison(Livraison l) {

        String url = Statics.BASE_URL + "/addlivraison";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("etat", l.getEtat());
        req.addArgument("id_colis", l.getId_colis() + "");
        req.addArgument("id_livreur", l.getId_livreur() + "");

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
    public ArrayList<Livraison> getAllLivraison() {
        ArrayList<Livraison> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/displaylivraison";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> maplivreur = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) maplivreur.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Livraison l = new Livraison();
                        float id = Float.parseFloat(obj.get("idLivraison").toString());
                        l.setId_livraison((int) id);
                        l.setEtat(obj.get("etat").toString());
                        l.setId_colis((int) Float.parseFloat(obj.get("id_colis").toString()));
                        l.setId_livreur((int) Float.parseFloat(obj.get("id_livreur").toString()));

                        result.add(l);
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
    public boolean deleteLivraison(int id) {
        String url = Statics.BASE_URL + "/livraisondelete/" + id;

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
////
////    //Update 
//
    public boolean modifierLivraison(Livraison l) {

        String url = Statics.BASE_URL + "/livraisonmodify/" + l.getId_livraison();

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("etat", l.getEtat());
        req.addArgument("id_livreur", l.getId_livreur() + "");
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

   
    
    
    public List<Integer> IdColisAffecte() {
        List<Integer> result = new ArrayList<>();
        ArrayList<Livraison> coliette = getAllLivraison();
        for (Livraison l : coliette) {
            result.add(l.getId_colis());
        }
        return result;
    }

}
