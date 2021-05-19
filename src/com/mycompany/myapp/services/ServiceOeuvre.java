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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.TextField;
import static com.codename1.ui.events.ActionEvent.Type.Calendar;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Oeuvre;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
//import static java.nio.file.Files.list;
//import static java.rmi.Naming.list;
//import static com.oracle.nio.BufferSecrets.instance;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.Map;
//import java.util.logging.*;
//import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import sun.security.jca.GetInstance.Instance;

/**
 *
 * @author user
 */
public class ServiceOeuvre {
    public ArrayList<Oeuvre> oeuvres;
    public static ServiceOeuvre instance ; 
    public boolean resultOK;
    private  ConnectionRequest req; 
    private ServiceOeuvre() {
        req = new ConnectionRequest() ; 
         }
    
    public static ServiceOeuvre getInstance() {
        if (instance == null)
        {
            instance = new ServiceOeuvre();
        }
         return instance;
    }
    public boolean addOeuvre (TextField nomOUV,TextField Prixoeuvre,TextField Emailartiste)
    { 

       String url = Statics.BASE_URL+"/addOeuvre?nomoeuvre="+nomOUV.getText()+"&prixoeuvre="+Prixoeuvre.getText()+"&emailartiste="+Emailartiste.getText();
       req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){ 
           @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
             }
    });
        System.out.println(""+resultOK);
       NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }
 public ArrayList<Oeuvre> parseOeuvres(String jsonText){
        try {
            oeuvres= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> OeuvreListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) OeuvreListJson.get("root");
           for ( Map<String,Object> obj: list){
             Oeuvre o = new Oeuvre();
             float id = Float.parseFloat(obj.get("idOeuvre").toString());
              float Prixoeuvre = Float.parseFloat(obj.get("prixoeuvre").toString());
               System.out.println("ouvreee=="+list.toString());
               System.out.println("imageeee=="+obj.get("imageoeuvre"));
//                       float idA = Float.parseFloat(obj.get("idArtiste").toString());
////               Date date = Calendar.getTime();  
////                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
////                String strDate = dateFormat.format(date);  
////                System.out.println("Converted String: " + strDate);  
                
               o.setIdOeuvre((int)id);
               o.setNomoeuvre(obj.get("nomoeuvre").toString());
               o.setPrixoeuvre(Prixoeuvre);
        //   o.setDateOeuvre(obj.get("Date ajout").toString());
               o.setImageoeuvre(obj.get("imageoeuvre").toString());
               //o.setNomcat(obj.get("nomcat").toString());
               o.setEmailartiste(obj.get("emailartiste").toString());
            //  o.setIdArtiste((int)idA);
                oeuvres.add(o);
         
        } }
           catch (IOException ex) {
//            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);
             
        }
          return oeuvres;
 }
     public ArrayList<Oeuvre> getAllOeuvres(){
          String url = Statics.BASE_URL+"/listOeuvre";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                oeuvres = parseOeuvres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return oeuvres;
    }
     
        public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL+"/deleteOeuvre?ID_Oeuvre="+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
}