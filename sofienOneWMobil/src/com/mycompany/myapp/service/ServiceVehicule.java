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

    public boolean ajouterVehicules(Vehicule v) {

        String url = Statics.BASE_URL + "/addvehicule";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("Matricule", v.getMatricule());
        req.addArgument("Marque", v.getMarque()+ "");
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

        String url = Statics.BASE_URL + "/displayvehicule";
        req.setUrl(url);

       req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
        JSONParser jsonp = new JSONParser();
        try {
            Map<String, Object> maplivreur = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) maplivreur.get("root");
            for (Map<String, Object> obj : listOfMaps) {
                Vehicule v = new Vehicule();
                float id = Float.parseFloat(obj.get("idLivreur").toString());
                v.setId_vehicule((int) id);
                v.setMarque(obj.get("matricule").toString());
                v.setMarque(obj.get("marque").toString());
                result.add(v);
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

//    //Update 
    public boolean modifierVehicule(Vehicule vehicule) {
       String url = Statics.BASE_URL + "/vehiculemodify/" + vehicule.getId_vehicule();

    ConnectionRequest request = new ConnectionRequest(url);
    request.setHttpMethod("PUT");
    request.addArgument("Marque", vehicule.getMarque());
    request.addArgument("Matricule", vehicule.getMatricule());
   

    NetworkManager.getInstance().addToQueueAndWait(request);

    return request.getResponseCode() == 200;

}
    
}
