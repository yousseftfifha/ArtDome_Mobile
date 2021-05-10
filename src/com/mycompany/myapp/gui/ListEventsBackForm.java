/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Event;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceEvent;
import java.io.IOException;

/**
 *
 * @author bhk
 */
public class ListEventsBackForm extends HomeForm{

    public ListEventsBackForm(Form previous,Resources res)  throws IOException {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Newsfeed");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
                Tabs swipe = new Tabs();

          swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        setTitle("List events");
        setLayout(BoxLayout.yCenter());
        ArrayList<Event> list;
        list = new ArrayList<>();
        list = ServiceEvent.getInstance().getAllEvents();

        for ( Event ev : list) {
            
            Label i = new Label();
           
           /* String imageFile = FileSystemStorage.getInstance().getAppHomePath() + ev.getImage();
        try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(Image.createImage(500,500), os, ImageIO.FORMAT_JPEG, 1);
        } catch(IOException err) {
            Log.e(err);
        }*/
            Image img1 = Image.createImage("/"+ev.getImage());
            i.setIcon(img1);
                
                SpanLabel spl = new SpanLabel("Event name: " + "  " + ev.getNomEvent());
                SpanLabel spl2 = new SpanLabel("Theme: " + "  " + ev.getThemeEvent());
                SpanLabel spl3 = new SpanLabel("State: " + "  " + ev.getEtat());
                //SpanLabel spl4 = new SpanLabel("Date: " + "  " + ev.getDate());
                SpanLabel spl5 = new SpanLabel("Contributors: " + "  " + ev.getNbMaxPart());
         Button sup = new Button("Delete");
             //c1.add(BorderLayout.CENTER,i);
             //c1.add(BorderLayout.CENTER,spl);
             
             //c2.addAll(spl2,spl3,spl4,spl5);
                sup.addActionListener((evt) -> {
                   ServiceEvent.getInstance().Supprimer(ev.getCodeEvent());
                    System.out.println("Event deleted successfully");
                    Dialog.show("Alert", "Delete event ?", new Command("OK"));
                    Dialog.show("Success", "Event deleted successfully", new Command("OK"));
                    });
        addAll(i,spl,spl2,spl3,spl5,sup)
                ;}
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
