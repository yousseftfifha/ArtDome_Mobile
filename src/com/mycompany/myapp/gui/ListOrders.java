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
import com.mycompany.myapp.entities.Orders;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceOrders;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author yotfi
 */
public class ListOrders extends HomeForm{

    public ListOrders(Form previous,Resources res)  throws IOException {
        super("My Orders", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("My Orders");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
                Tabs swipe = new Tabs();

          swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        setTitle("List Orders");
        setLayout(BoxLayout.yCenter());
        ArrayList<Orders> list;
        list = new ArrayList<>();
        list = ServiceOrders.getInstance().getAllOrders();

        for ( Orders order : list) {
            
            Label i = new Label();
           
           /* String imageFile = FileSystemStorage.getInstance().getAppHomePath() + ev.getImage();
        try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(Image.createImage(500,500), os, ImageIO.FORMAT_JPEG, 1);
        } catch(IOException err) {
            Log.e(err);
        }*/
           
                
                SpanLabel spl = new SpanLabel("InnoNumber: " + "  " + order.getInnoNumber());
                SpanLabel spl2 = new SpanLabel("DueAmount: " + "  " + order.getDueAmount());
                SpanLabel spl3 = new SpanLabel("Status: " + "  " + order.getStatus());
                SpanLabel spl4 = new SpanLabel("User: " + "  " + order.getIDUser());

             //c1.add(BorderLayout.CENTER,i);
             //c1.add(BorderLayout.CENTER,spl);
             
             //c2.addAll(spl2,spl3,spl4,spl5);
              
        addAll(i,spl,spl2,spl3)
                ;}
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
