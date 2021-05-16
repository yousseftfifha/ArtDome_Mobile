/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MediaPlayer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.services.ServiceEvent;
import java.util.ArrayList;
import static java.util.Collections.list;
import com.mycompany.myapp.services.ServiceEvent;
import java.io.IOException;
import java.io.OutputStream;
/**
 *
 * @author bhk
 */
public class ListEventsForm extends Form{

    public ListEventsForm(Form previous ) throws IOException {
        Form current;
        current=this;
        TextField search= new TextField("", "Search by name");
        Button btnsearch= new Button("Search");
        addAll(search,btnsearch);
        setTitle("List events");
        setLayout(BoxLayout.yCenter());
        ArrayList<Event> list;
        list = new ArrayList<>();
         btnsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((search.getText().length()==0))
                 Dialog.show("Alert", "Please type an event name", new Command("OK"));
                
                else
                { 
                    
                    try {
                        new SearchEventForm(current,search.getText()).show();
                    } catch (IOException ex) {
                    }
                   
                }
                
                
            }
        });
        list = ServiceEvent.getInstance().getAllEvents();
        for ( Event ev : list) {
              
           Image img;
           EncodedImage enc;
           enc = EncodedImage.create("/faza.jpg");
           enc.scale(570,620);
           String url="http://127.0.0.1:8282/ArtDomeWeb/public/pi/"+ev.getImage();
           img =URLImage.createToStorage(enc, url, url);
           img.scaledHeight(1);
           img.fill(570,620);
           ImageViewer imgv;
           imgv= new ImageViewer(img);
           //Image img1 = Image.createImage(imageFile+ev.getImage());
            
                
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
                Button partager = new Button();
                partager.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SHARE, partager.getUnselectedStyle()));
                partager.addActionListener((evtt)->{
                Display.getInstance().execute("https://www.facebook.com/sharer/sharer.php/?u=127.0.0.1:8000/event/"+ev.getCodeEvent());
                 });
       addAll(imgv,spl,spl2,spl3,spl5,sup,partager);
        
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
      /* getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                    line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        getContentPane().animateLayout(150);
    }
}, 4);*/
    }
//    protected void showOtherForm(Resources res) {
//    }
//    
//     private void initStarRankStyle(Style s, Image star) {
//    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
//    s.setBorder(Border.createEmpty());
//    s.setBgImage(star);
//    s.setBgTransparency(0);
//}
//
//    private Slider createStarRankSlider() {
//    Slider starRank = new Slider();
//    starRank.setEditable(true);
//    starRank.setMinValue(0);
//    starRank.setMaxValue(6);
//    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
//            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
//    Style s = new Style(0xffff33, 0, fnt, (byte)0);
//    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
//    s.setOpacity(100);
//    s.setFgColor(0);
//    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
//    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
//    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
//    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
//    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
//    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
//    starRank.addDataChangedListener((j,t)->{
//    System.out.println(starRank.getProgress()); 
//    Dialog.show("Success", "You successefully eveluated this event ", new Command("OK"));
//    starRank.setEnabled(false);
//    });     
//    
//    return starRank;
//}
    
}
