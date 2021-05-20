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
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author asus
 */
public class ServiceUser {
    public ArrayList<User> users;
    
    public static ServiceUser instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
     private ServiceUser() {
         req = new ConnectionRequest();
    }
     
     public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
       public ArrayList<User> parseEvents(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
             
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setID((int)id);
                u.setNom(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setSexe(obj.get("sexe").toString()); 
                u.setVille(obj.get("ville").toString()); 
                u.setEmail(obj.get("email").toString());
              // u.setDatenaissance(toDateValue((Object)obj.get("datenaissance").toString()));
              float num = Float.parseFloat(obj.get("numero").toString());
                u.setNumero((int)num);
                
               
                users.add(u);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return users;
    }
    
    public ArrayList<User> getAllUsers(){
        String url = Statics.BASE_URL+"/listUsers";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
 public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL+"/deleteUser/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

}
