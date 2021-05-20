/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
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
public class ListEventsForm extends HomeForm{

    public ListEventsForm(Form previous,Resources res)  throws IOException {
       super("Events", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Events");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        //tb.addSearchCommand(e -> {});
                Tabs swipe = new Tabs();
                        Label spacer1 = new Label();
        Label spacer2 = new Label();

        addTab(swipe, res.getImage("faza.jpg"), spacer1);
          swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
          swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        setTitle("Events");
        setLayout(BoxLayout.yCenter());
        TextField search= new TextField("", "Search by name");
        Button btnsearch= new Button("Search");
        addAll(search,btnsearch);
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
                        new SearchEventForm(current,res,search.getText()).show();
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
    }
    
     private void addTab(Tabs swipe, Image img, Label spacer) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
       

      
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                           
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
}