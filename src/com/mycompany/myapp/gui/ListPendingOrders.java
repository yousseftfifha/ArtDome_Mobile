/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Orders;
import com.mycompany.myapp.entities.PendingOrders;
import com.mycompany.myapp.services.ServiceOrders;
import com.mycompany.myapp.services.ServicePendingOrders;
import com.mycompany.myapp.utils.UserHolder;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author yotfi
 */
public class ListPendingOrders extends HomeForm{

    public ListPendingOrders(Form previous,Resources res,double innonumber)  throws IOException {
        super("My Orders", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("My Orders");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
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
        
        setTitle("List Pending Orders");
        setLayout(BoxLayout.yCenter());
        ArrayList<PendingOrders> list;
        UserHolder holder = UserHolder.getInstance();

        list = ServicePendingOrders.getInstance().getAllPendingOrders(innonumber);
        Container list1 = new Container(BoxLayout.y());
        for(PendingOrders pendingorder:list){
            //Creating custom container
                    Container element = new Container(BoxLayout.y());
                    Container line1 = new Container(BoxLayout.x());
                    SpanLabel InnoNumberLabel = new SpanLabel("InnoNumber: " + "  " +  pendingorder.getInnonumber());
                    Label DueAmountLabel = new Label("Quantity: " + "  " + Float.toString(pendingorder.getQuantity()));
                    line1.add(InnoNumberLabel);
                    line1.add(DueAmountLabel);
                    element.add(line1);
                    
                  
                    
                    Container element2 = new Container(BoxLayout.y());
                    Container line3 = new Container(BoxLayout.x());
                    SpanLabel StatusLabel = new SpanLabel("Status: " + "  " +  pendingorder.getStatus());
                    line3.add(StatusLabel);
                    element2.add(line3);
                
                    Container element3 = new Container(BoxLayout.y());
                    Container line4 = new Container(BoxLayout.x());
                    SpanLabel ArtworkLabel = new SpanLabel("Artwork: " + "  " +  pendingorder.getOeuvreID());
                    line4.add(ArtworkLabel);
                    element3.add(line4);
                
                   

                    Button b = new Button();
                    b.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DESCRIPTION, b.getUnselectedStyle()));
                    b.addActionListener(evt -> Dialog.show("Info","information about User:"+holder.getUser().toString(), "ok", "" ));
                    element.setLeadComponent(b);
                  
                    list1.add(element);
                    list1.add(element2);
                    list1.add(element3);

                    list1.add(b);


           
        }
                list1.setScrollableY(true);
                this.add(list1);
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
