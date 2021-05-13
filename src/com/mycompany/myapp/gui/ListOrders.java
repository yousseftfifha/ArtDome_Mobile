/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Orders;
import com.mycompany.myapp.services.ServiceOrders;
import com.mycompany.myapp.utils.UserHolder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
        list = ServiceOrders.getInstance().getAllOrders();
        Container list1 = new Container(BoxLayout.y());
        for(Orders order:list){
            //Creating custom container
                    Container element = new Container(BoxLayout.y());
                    Container line1 = new Container(BoxLayout.x());
                    SpanLabel InnoNumberLabel = new SpanLabel("InnoNumber: " + "  " + Integer.toString((int) order.getInnoNumber()));
                    Label DueAmountLabel = new Label("DueAmount: " + "  " + Float.toString(order.getDueAmount()));
                    line1.add(InnoNumberLabel);
                    line1.add(DueAmountLabel);
                    element.add(line1);
                    
                    Container element2 = new Container(BoxLayout.y());
                    Container line3 = new Container(BoxLayout.x());
                    SpanLabel StatusLabel = new SpanLabel("Status: " + "  " +  order.getStatus());
                    line3.add(StatusLabel);
                    element2.add(line3);
                
                    UserHolder holder = UserHolder.getInstance();
                   

                    Button b = new Button();
                    b.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DESCRIPTION, b.getUnselectedStyle()));
                    b.addActionListener(evt -> Dialog.show("Info","information about User:"+holder.getUser().toString(), "ok", "" ));
                    element.setLeadComponent(b);
                    list1.add(element2);
                    list1.add(element);
                    element.add(b);


            
           
        }
                list1.setScrollableY(true);
                this.add(list1);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
