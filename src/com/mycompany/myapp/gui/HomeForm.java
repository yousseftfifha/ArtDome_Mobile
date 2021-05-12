/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddEvent = new Button("Add Event");
        Button btnListEvents = new Button("List Events");
        Button btnListExpos = new Button("List Expos");
        Button btnListEventsB = new Button("List Events Back");

        btnAddEvent.addActionListener(e -> new AddEventForm(current).show());
        btnListEvents.addActionListener(e -> {
            
            try {
                new ListEventsForm(current).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        });
        btnListEventsB.addActionListener(e -> {
            
            try {
                new ListEventsBackForm(current).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        });
        
        
          btnListExpos.addActionListener(e -> {
            
            try {
                new ListExposForm(current).show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        });
        
        addAll(btnAddEvent, btnListEvents, btnListExpos,btnListEventsB);

    }

}
