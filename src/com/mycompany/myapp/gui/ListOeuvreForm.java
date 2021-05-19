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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.entities.Oeuvre;
import com.mycompany.myapp.services.ServiceOeuvre;
import java.util.ArrayList;
import static java.util.Collections.list;
import com.mycompany.myapp.services.ServiceOeuvre;
import java.io.IOException;
import java.io.OutputStream;

/**

/**
 *
 * @author user
 */
public class ListOeuvreForm extends Form {

    public ListOeuvreForm(Form previous) throws IOException {
          ArrayList<Oeuvre> list;
        list = new ArrayList<>();
        list = ServiceOeuvre.getInstance().getAllOeuvres();

        for ( Oeuvre o : list) {
            
              
                  Label i = new Label();
                  
                  /* String imageFile = FileSystemStorage.getInstance().getAppHomePath() + ev.getImage();
                  try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                  ImageIO.getImageIO().save(Image.createImage(500,500), os, ImageIO.FORMAT_JPEG, 1);
                  } catch(IOException err) {
                  Log.e(err);
                  }*/
                 // Image img1 = Image.createImage("/"+o.getImageoeuvre());
  // i.setIcon(img1);
       
         SpanLabel spl = new SpanLabel("Oeuvre name: " + "  " + o.getNomoeuvre()); 
         SpanLabel spl2 = new SpanLabel("Oeuvre price: " + "  " + o.getPrixoeuvre()); 
        // SpanLabel spl3 = new SpanLabel("oeuvre category: " + "  " + o.getNomcat());
         //SpanLabel spl4 = new SpanLabel("Date: " + "  " + ev.getDate());
         SpanLabel spl5 = new SpanLabel("artist's Email: " + "  " + o.getEmailartiste());
//         SpanLabel spl6 = new SpanLabel("artist's ID: " + "  " + o.getIdArtiste());
         Button sup = new Button("Delete");
             //c1.add(BorderLayout.CENTER,i);
             //c1.add(BorderLayout.CENTER,spl);
             
             //c2.addAll(spl2,spl3,spl4,spl5);
                sup.addActionListener((evt) -> {
                   ServiceOeuvre.getInstance().Supprimer(o.getIdOeuvre());
                    System.out.println("Event deleted successfully");
                    Dialog.show("Alert", "Delete event ?", new Command("OK"));
                    Dialog.show("Success", "Event deleted successfully", new Command("OK"));
                    });
        addAll(i,spl,spl2,spl5,sup)
                ;}
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
