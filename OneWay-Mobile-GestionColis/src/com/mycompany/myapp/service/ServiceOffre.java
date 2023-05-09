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
import com.mycompany.myapp.entities.Categorieoffre;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.entities.TrajetOffre;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author utilisateur
 */
public class ServiceOffre {

    public ArrayList<Offre> Offres;

    public static ServiceOffre instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceOffre() {
        req = new ConnectionRequest();
    }

    public static ServiceOffre getInstance() {
        if (instance == null) {
            instance = new ServiceOffre();
        }
        return instance;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Offre> getAllOffre() {
        ArrayList<Offre> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/displayOffre";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> mapOffre = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapOffre.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Offre t = new Offre();
                        float id = Float.parseFloat(obj.get("idoffre").toString());

                        t.setIdOffre((int) id);

                      
                        t.setDescriptionOffre(obj.get("descriptionoffre").toString());
                        t.setMaxRetard(obj.get("maxretard").toString());
                        float prixoffre = Float.parseFloat(obj.get("prixoffre").toString());

                        t.setPrixOffre(prixoffre);
                        float nbredemande = Float.parseFloat(obj.get("nbredemande").toString());

                        t.setNbreDemande((int) nbredemande);
               // Create the CategorieOffre object
                 float catoffreid = Float.parseFloat(obj.get("catoffreid").toString());

                      
t.setCatOffreId( (int)catoffreid);

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
