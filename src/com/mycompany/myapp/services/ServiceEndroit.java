/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import static com.codename1.io.Util.toDateValue;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.Endroit;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yotfi
 */
public class ServiceEndroit {
    public ArrayList<Endroit> endroit;
    
    public static ServiceEndroit instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEndroit() {
         req = new ConnectionRequest();
    }

    public static ServiceEndroit getInstance() {
        if (instance == null) {
            instance = new ServiceEndroit();
        }
        return instance;
    }

   

    public ArrayList<Endroit> parseEndroit(String jsonText){
        try {
            endroit=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Endroit endroits = new Endroit();
                float id = Float.parseFloat(obj.get("idEndroit").toString());
                endroits.setId_endroit((int)id);
                endroits.setType(obj.get("type").toString());
                float taille = Float.parseFloat(obj.get("taille").toString());
                endroits.setTaille((int)taille);
                float prix_jour = Float.parseFloat(obj.get("prixJour").toString());
                endroits.setPrix_jour((int)prix_jour);
                endroits.setLocation(obj.get("location").toString());
                endroits.setDisponibilite(obj.get("disponibilite").toString());
                
                endroit.add(endroits);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return endroit;
    }
    
    public ArrayList<Endroit> getAllEndroit(){
        String url = Statics.BASE_URL+"/listEndroit/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                endroit = parseEndroit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return endroit;
    }
      public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL+"/deleteEndroit/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
        
         public ArrayList<Endroit> searchEndoit(int name){
         //Event ev = new Event();
         String url = Statics.BASE_URL + "/searchEndroit/"+name;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                endroit = parseEndroit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return endroit;
    }
     
}
