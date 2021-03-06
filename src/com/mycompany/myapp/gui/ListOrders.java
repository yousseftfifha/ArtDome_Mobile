/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
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
import com.mycompany.myapp.services.ServiceOrders;
import com.mycompany.myapp.utils.UserHolder;
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
        
        setTitle("List Orders");
        setLayout(BoxLayout.yCenter());
        ArrayList<Orders> list;
        UserHolder holder = UserHolder.getInstance();

        list = ServiceOrders.getInstance().getAllOrders(holder.getUser().getID());
        Container list1 = new Container(BoxLayout.y());
        for(Orders order:list){
            //Creating custom container
                    Container element = new Container(BoxLayout.y());
                    Container line1 = new Container(BoxLayout.x());
                    SpanLabel InnoNumberLabel = new SpanLabel("InnoNumber: " + "  " + order.getInnoNumber());
                    Label DueAmountLabel = new Label("DueAmount: " + "  " + Float.toString(order.getDueAmount()));
                    line1.add(InnoNumberLabel);
                    line1.add(DueAmountLabel);
                    element.add(line1);
                    
                  
                    
                    Container element2 = new Container(BoxLayout.y());
                    Container line3 = new Container(BoxLayout.x());
                    SpanLabel StatusLabel = new SpanLabel("Status: " + "  " +  order.getStatus());
                    line3.add(StatusLabel);
                    element2.add(line3);
                
                   

                    Button b = new Button();
                    b.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DESCRIPTION, b.getUnselectedStyle()));
                    b.addActionListener(evt -> {
                        try {
                            new ListPendingOrders(current,res,order.getInnoNumber()).show();
                        } catch (IOException ex) {
                        }
                    });
                    Button sup = new Button("Delete");
            
                sup.addActionListener((evt) -> {
                   ServiceOrders.getInstance().Cancel(order.getOrderId());
                    System.out.println("Order Cancelled successfully");
                    Dialog.show("Alert", "Cancel Order ?", new Command("OK"));
                    Dialog.show("Success", "Order Cancelled  successfully", new Command("OK"));
                    });
                    element.setLeadComponent(b);
                    list1.add(element);
                    list1.add(element2);
                    list1.add(b);
                    list1.add(sup);

Message m = new Message("Body of message");
Display.getInstance().sendMessage(new String[] {"youssef.tfifha@esprit.tn"}, "Subject of message", m);

            
           
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
