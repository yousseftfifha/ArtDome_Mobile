/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.Exposition;
import com.mycompany.myapp.services.ServiceExposition;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class SearchExpoForm extends Form {
     public SearchExpoForm(Form previous, Integer code) throws IOException {
         setLayout(BoxLayout.yCenter());
          ArrayList<Exposition> list;
        list = new ArrayList<>();
                    list =ServiceExposition.getInstance().searchExpo(code);
                    for ( Exposition ev : list) {

        
           //Image img1 = Image.createImage(imageFile+ev.getImage());


                SpanLabel spl = new SpanLabel("Expo name: " + "  " + ev.getNomExpo());
                SpanLabel spl2 = new SpanLabel("Theme: " + "  " + ev.getThemExpo());
                
                //SpanLabel spl4 = new SpanLabel("Date: " + "  " + ev.getDate());
                SpanLabel spl5 = new SpanLabel("Contributors: " + "  " + ev.getNbMaxParticipant());
                 Button sup = new Button("Delete");
             //c1.add(BorderLayout.CENTER,i);
             //c1.add(BorderLayout.CENTER,spl);

             //c2.addAll(spl2,spl3,spl4,spl5);
                sup.addActionListener((evtS) -> {
                   ServiceExposition.getInstance().Supprimer(ev.getCodeExpo());
                    System.out.println("Expo deleted successfully");
                    Dialog.show("Alert", "Delete expo ?", new Command("OK"));
                    Dialog.show("Success", "Expo deleted successfully", new Command("OK"));
                    });
                Button partager = new Button();
                partager.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SHARE, partager.getUnselectedStyle()));
                partager.addActionListener((evtt)->{
                Display.getInstance().execute("https://www.facebook.com/sharer/sharer.php/?u=127.0.0.1:8000/exposition/"+ev.getCodeExpo());
                 });
        addAll(spl,spl2,spl5,sup,partager);

        }
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
     }
}