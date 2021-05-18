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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.Exposition;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceExposition {

    public ArrayList<Exposition> expos;
    
    public static ServiceExposition instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceExposition() {
         req = new ConnectionRequest();
    }

    public static ServiceExposition getInstance() {
        if (instance == null) {
            instance = new ServiceExposition();
        }
        return instance;
    }

    public boolean addExposition(Exposition ev) {
        String url = Statics.BASE_URL + "/addExpo/" + ev.getNomExpo()+ "/" + ev.getThemExpo(); //création de l'URL
        //ajouterProduitMobile?nom="+p.getNom() + "&marque="+p.getMarque() +"&prix="+p.getPrix()+"&quantite="+p.getQuantite()+"&id_categorie"+p.getId_categorie();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Exposition> parseExpos(String jsonText){
        try {
            expos=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Exposition ev = new Exposition();
                float id = Float.parseFloat(obj.get("codeExpo").toString());
                ev.setCodeExpo((int)id);
                ev.setNomExpo(obj.get("nomExpo").toString());
                ev.setThemExpo(obj.get("themeExpo").toString());
               
               // ev.setDate(toDateValue((Object)obj.get("date").toString()));
              float nbp = Float.parseFloat(obj.get("nbMaxParticipant").toString());
                ev.setNbMaxParticipant((int)nbp);
             
                expos.add(ev);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return expos;
    }
    
    public ArrayList<Exposition> getAllExpos(){
        String url = Statics.BASE_URL+"/listExpo";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                expos = parseExpos(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return expos;
    }
    
        public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL+"/deleteExpo/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
        
        
        
                 public ArrayList<Exposition> searchExpo(String name){
         //Event ev = new Event();
         String url = Statics.BASE_URL + "/searchExpo/"+name;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                expos = parseExpos(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return expos;
    }
}
