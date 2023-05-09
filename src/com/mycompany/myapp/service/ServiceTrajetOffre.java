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
import com.mycompany.myapp.entities.TrajetOffre;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author utilisateur
 */
public class ServiceTrajetOffre {
       public ArrayList<TrajetOffre> TrajetOffres;

    public static ServiceTrajetOffre instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTrajetOffre() {
        req = new ConnectionRequest();
    }
  
    public static ServiceTrajetOffre getInstance() {
        if (instance == null) {
            instance = new ServiceTrajetOffre();
        }
        return instance;
    }

    public boolean ajouterTrajetOffre(TrajetOffre r) {

        String url = Statics.BASE_URL + "/addTrajetOffre";

        req.setUrl(url);
        req.setPost(true);
        req.addArgument("Addarriveoffre", r.getAddArriveOffre());
        req.addArgument("Adddepartoffre", r.getAddDepartOffre()+ "");
        req.addArgument("Limitekmoffre", r.getLimiteKmOffre()+ "");
        req.addArgument("Nbreescaleoffre", r.getNbreEscaleOffre()+ "");

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
   public ArrayList<TrajetOffre> getAllTrajetOffre() {
        ArrayList<TrajetOffre> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/displayTrajetoffre";
        req.setUrl(url);

       req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
        JSONParser jsonp = new JSONParser();
        try {
            Map<String, Object> mapTrajetOffre = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapTrajetOffre.get("root");
            for (Map<String, Object> obj : listOfMaps) {
                TrajetOffre t = new TrajetOffre();
                System.out.println(t);
 float id = Float.parseFloat(obj.get("idtrajetoffre").toString());
                 System.out.println(id);

                t.setIdTrajetOffre((int) id);
                float nbreescaleoffre = Float.parseFloat(obj.get("nbreescaleoffre").toString());
                t.setNbreEscaleOffre((int) nbreescaleoffre);
              float limitekmoffre =  Float.parseFloat(obj.get("limitekmoffre").toString());
                t.setLimiteKmOffre( (int) limitekmoffre);
                t.setAddArriveOffre(obj.get("addarriveoffre").toString());
                t.setAddDepartOffre(obj.get("adddepartoffre").toString());
                t.setDescription(obj.get("description").toString());

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
    
    public boolean deleteTrajetOffre(long id) {
        String url = Statics.BASE_URL + "/TrajetOffredelete/" + id;

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
    public boolean modifierTrajetOffre(TrajetOffre TrajetOffre) {
       String url = Statics.BASE_URL +"/TrajetOffremodify/" + TrajetOffre.getIdTrajetOffre();

    ConnectionRequest request = new ConnectionRequest(url);
    request.setHttpMethod("PUT");

       req.addArgument("Addarriveoffre", TrajetOffre.getAddArriveOffre());
        req.addArgument("Adddepartoffre", TrajetOffre.getAddDepartOffre()+ "");
        req.addArgument("Limitekmoffre", TrajetOffre.getLimiteKmOffre()+ "");
        req.addArgument("Nbreescaleoffre", TrajetOffre.getNbreEscaleOffre()+ "");
    NetworkManager.getInstance().addToQueueAndWait(request);

    return request.getResponseCode() == 200;

}
}
