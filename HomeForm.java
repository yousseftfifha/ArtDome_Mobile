/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author user
 */
public class HomeForm extends Form{
Form current ; 
    public HomeForm() {
        current=this;
        setTitle("HOME");
        setLayout(BoxLayout.y());
        add( new Label("Choose an option"));
        Button btnAddOeuvre = new Button ("ADD OEUVRE");
        Button btnListOeuvres = new Button ("OEUVRE LIST");
   btnAddOeuvre.addActionListener(e -> new AddOeuvreForm(current).show());
        btnListOeuvres.addActionListener(e -> {
            
            try {
                new ListOeuvreForm(current).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        });
//        btnListOeuvres.addActionListener(e -> {
//            
//            try {
//                new btnListOeuvres(current).show();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            
//        });
        
        addAll( btnAddOeuvre,btnListOeuvres);

    }
    
}
