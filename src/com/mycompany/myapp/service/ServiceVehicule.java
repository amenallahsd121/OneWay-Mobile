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
import com.mycompany.myapp.entities.Vehicule;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hp
 */
public class ServiceVehicule {

    public ArrayList<Vehicule> vehicules;

    public static ServiceVehicule instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceVehicule() {
        req = new ConnectionRequest();
    }

    public static ServiceVehicule getInstance() {
        if (instance == null) {
            instance = new ServiceVehicule();
        }
        return instance;
    }

//////////////////////////////////////////////////////////////////////////////////////
    public boolean ajouterVehicules(Vehicule v) {

        String url = Statics.BASE_URL + "/addvehicule";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("Matricule", v.getMatricule()+"");
        System.out.println(v.getMatricule()+"kokokokokoko");
        req.addArgument("Marque", v.getMarque() + "");
        req.addArgument("id_categorie", v.getId_categorie() + "");
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
    public ArrayList<Vehicule> getAllVehicule() {
        ArrayList<Vehicule> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/displayvehiculemobile";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> maplivreur = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) maplivreur.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Vehicule t = new Vehicule();
                        float id = Float.parseFloat(obj.get("idVehicule").toString());
                        t.setId_vehicule((int) id);
                        t.setMatricule(obj.get("matricule").toString());
                        t.setMarque(obj.get("marque").toString());

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
    public boolean deletevehicule(int id) {
        String url = Statics.BASE_URL + "/vehiculedelete/" + id;

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

//////////////////////////////////////////////////////////////////////////
    
    
   public boolean modifiervehicule(Vehicule v) {

        String url = Statics.BASE_URL + "/vehiculemodifyy/" + v.getId_vehicule();

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("Matricule", v.getMatricule());
        req.addArgument("Marque", v.getMarque());
        req.addArgument("id_categorie", v.getId_categorie()+"");
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
   
   ////////////////////////////////////////////////////////////////////////

    public ArrayList<Integer> getAllVehiculeIDs() {
        ArrayList<Integer> result = new ArrayList<>();

        // Call getAllCategorie to get a list of Categorie objects
        ArrayList<Vehicule> vehicules = getAllVehicule();

        // Extract the type field from each Categorie object and add it to the result list
        for (Vehicule vehicule : vehicules) {
            result.add(vehicule.getId_vehicule());
        }

        return result;
    }

}
