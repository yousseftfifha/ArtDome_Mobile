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
import com.mycompany.myapp.entities.Blog;
import com.mycompany.myapp.services.ServiceBlog;
import java.util.ArrayList;
import static java.util.Collections.list;
import com.mycompany.myapp.services.ServiceBlog;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author bhk
 */
public class ListBlogsForm extends Form{

    public ListBlogsForm(Form previous) throws IOException {
    
        setTitle("List blogs");
        setLayout(BoxLayout.yCenter());
        ArrayList<Blog> list;
        list = new ArrayList<>();
        list = ServiceBlog.getInstance().getAllBlogs();

        for ( Blog bg : list) {
            
            Label i = new Label();
           
           /* String imageFile = FileSystemStorage.getInstance().getAppHomePath() + ev.getImage();
        try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(Image.createImage(500,500), os, ImageIO.FORMAT_JPEG, 1);
        } catch(IOException err) {
            Log.e(err);
        }*/
            Image img1 = Image.createImage("/"+bg.getImage());
            i.setIcon(img1);
                
                SpanLabel spl = new SpanLabel("Blog Title: " + "  " + bg.getTitle());
                SpanLabel spl2 = new SpanLabel("Categorie: " + "  " + bg.getCategorie());
                SpanLabel spl3 = new SpanLabel("State: " + "  " + bg.getDescription());
                //SpanLabel spl4 = new SpanLabel("Date: " + "  " + ev.getDate());
//                SpanLabel spl5 = new SpanLabel("Contributors: " + "  " + bg.getNbMaxPart());
                Button partager = new Button();
                partager.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SHARE, partager.getUnselectedStyle()));
                partager.addActionListener((evtt)->{
                Display.getInstance().execute("https://www.facebook.com/sharer/sharer.php/?u=127.0.0.1:8001/blog/"+bg.getIdBlog() + 
                       "http://127.0.0.1:8001/detailEvent/"+bg.getIdBlog());
                 });
        addAll(i,spl,spl2,spl3,partager)
                ;}
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
