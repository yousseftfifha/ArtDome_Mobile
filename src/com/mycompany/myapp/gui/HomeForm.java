/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.UserHolder;
import java.io.IOException;
import java.util.Date;


/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
   

    public HomeForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public HomeForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
       
        Image img = res.getImage("faza.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));
        
        tb.addMaterialCommandToSideMenu("Home ", FontImage.MATERIAL_UPDATE, e -> new HomeForm(res).show());
                tb.addMaterialCommandToSideMenu("Orders ", FontImage.MATERIAL_LIST, e -> {
            try {
                new ListOrders(this.current,res).show();
            } catch (IOException ex) {
            }
        });
  tb.addMaterialCommandToSideMenu("Events", FontImage.MATERIAL_LIST, e -> {
            try {
                new ListEventsForm(this.current,res).show();
            } catch (IOException ex) {
            }
        });
       
    }
    public HomeForm(Resources res) {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Choose an option"));
       
        Button btnListEvents = new Button("Events");
        Button btnListOrders = new Button("My Orders");
        Button btnListExpos = new Button("Expositions");
          Button btnAddOeuvre = new Button ("ADD OEUVRE");
        Button btnListOeuvres = new Button ("OEUVRE LIST");
        
        btnListEvents.addActionListener(e -> {
            
            try {
                new ListEventsForm(current,res).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        });
       
        btnListOrders.addActionListener(e -> {
            
            try {
                new ListOrders(current,res).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        });
        
            btnListExpos.addActionListener(e -> {
            
            try {
                new ListExposForm(current,res).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        });
   
   btnAddOeuvre.addActionListener(e -> new AddOeuvreForm(current).show());
        btnListOeuvres.addActionListener(e -> {
            
            try {
                new ListOeuvreForm(current).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        });
        
        addAll(btnListEvents,btnListOrders,btnListExpos,btnAddOeuvre,btnListOeuvres);
                    Date d=new Date(2000-02-24);
                  
                    User u = new User (14,"tfifha","youssef",d,"ezzahra","youssef.tfifha@esprit.tn",20245989,"Homme");
                    UserHolder holder = UserHolder.getInstance();
                    holder.setUser(u); 

    }
}