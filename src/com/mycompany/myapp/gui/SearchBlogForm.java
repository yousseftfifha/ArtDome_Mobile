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
import com.mycompany.myapp.entities.Blog;
import com.mycompany.myapp.services.ServiceBlog;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class SearchBlogForm extends Form {
     public SearchBlogForm(Form previous, String name) throws IOException {
         setLayout(BoxLayout.yCenter());
          ArrayList<Blog> list;
        list = new ArrayList<>();
                    list = ServiceBlog.getInstance().searchBlog(name.toLowerCase());
                    for ( Blog bg : list) {
              
           Image img;
           EncodedImage enc;
                  
                        enc = EncodedImage.create("/faza.jpg");
                    
           enc.scale(570,620);
           String url="http://127.0.0.1:8282/ArtDomeWeb/public/pi/"+bg.getImage();
           img =URLImage.createToStorage(enc, url, url);
           img.scaledHeight(1);
           img.fill(570,620);
           ImageViewer imgv;
           imgv= new ImageViewer(img);
           //Image img1 = Image.createImage(imageFile+ev.getImage());
            
                SpanLabel spl = new SpanLabel("Blog Title: " + "  " + bg.getTitle());
                SpanLabel spl2 = new SpanLabel("Categorie: " + "  " + bg.getCategorie());
                SpanLabel spl3 = new SpanLabel("State: " + "  " + bg.getDescription());
                
                 Button sup = new Button("Delete");
             //c1.add(BorderLayout.CENTER,i);
             //c1.add(BorderLayout.CENTER,spl);
             
             //c2.addAll(spl2,spl3,spl4,spl5);
                sup.addActionListener((evtS) -> {
                   ServiceBlog.getInstance().Supprimer(bg.getIdBlog());
                    System.out.println("Blog deleted successfully");
                    Dialog.show("Alert", "Delete blog ?", new Command("OK"));
                    Dialog.show("Success", "Blog deleted successfully", new Command("OK"));
                    });
                Button partager = new Button();
                partager.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SHARE, partager.getUnselectedStyle()));
                partager.addActionListener((evtt)->{
                Display.getInstance().execute("https://www.facebook.com/sharer/sharer.php/?u=127.0.0.1:8000/event/"+bg.getIdBlog());
                 });
        addAll(imgv,spl,spl2,spl3,sup,partager);
        
        }
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
     }
}
