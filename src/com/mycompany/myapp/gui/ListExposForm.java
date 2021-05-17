/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.Exposition;
import com.mycompany.myapp.services.ServiceEvent;
import java.util.ArrayList;
import static java.util.Collections.list;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceExposition;
import java.io.IOException;
import java.io.OutputStream;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author bhk
 */
public class ListExposForm extends Form{

    public ListExposForm(Form previous) throws IOException {
                Form current;
        current=this;
        TextField search= new TextField("", "Search...");
        Button btnsearch= new Button("Search");
        addAll(search,btnsearch);
    
        setTitle("List expos");
        setLayout(BoxLayout.yCenter());
        ArrayList<Exposition> list;
        list = new ArrayList<>();
        
        
                 btnsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((search.getText().length()==0))
                 Dialog.show("Alert", "Please type an exposition code", new Command("OK"));

                else
                { 

                    try {
                        new SearchExpoForm(current,Integer.valueOf(search.getText())).show();
                    } catch (IOException ex) {
                    }

                }


            }
        });
        
        
        
        list = ServiceExposition.getInstance().getAllExpos();

        for ( Exposition ev : list) {
            
            Label i = new Label();
           
           /* String imageFile = FileSystemStorage.getInstance().getAppHomePath() + ev.getImage();
        try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(Image.createImage(500,500), os, ImageIO.FORMAT_JPEG, 1);
        } catch(IOException err) {
            Log.e(err);
        }*/
            Image img1 = Image.createImage("/Art.jpg");
            i.setIcon(img1);
                
                SpanLabel spl = new SpanLabel("Expo name: " + "  " + ev.getNomExpo());
                SpanLabel spl2 = new SpanLabel("Theme: " + "  " + ev.getThemExpo());
                
                 Button sup = new Button("Delete");
             //c1.add(BorderLayout.CENTER,i);
             //c1.add(BorderLayout.CENTER,spl);
             
             //c2.addAll(spl2,spl3,spl4,spl5);
                sup.addActionListener((evt) -> {
                   ServiceExposition.getInstance().Supprimer(ev.getCodeExpo());
                    System.out.println("Exposition deleted successfully");
                    Dialog.show("Alert", "Delete exposition ?", new Command("OK"));
                    Dialog.show("Success", "Exposition deleted successfully", new Command("OK"));
                    });
               
                //SpanLabel spl4 = new SpanLabel("Date: " + "  " + ev.getDate());
                SpanLabel spl5 = new SpanLabel("Contributors: " + "  " + ev.getNbMaxParticipant());
                Button partager = new Button();
                partager.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SHARE, partager.getUnselectedStyle()));
                partager.addActionListener((evtt)->{
                Display.getInstance().execute("https://www.facebook.com/sharer/sharer.php/?u=127.0.0.1:8000/exposition/"+ev.getCodeExpo());
                 });
        addAll(i,spl,spl2,spl5,partager,sup)
                ;}
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
