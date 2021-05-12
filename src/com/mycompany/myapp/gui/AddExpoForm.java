/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.Exposition;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceExposition;

/**
 *
 * @author bhk
 */
public class AddExpoForm extends Form{

    public AddExpoForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new exposition");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","Exposition Name");
        TextField tfTheme= new TextField("", "Exposition Theme");
        Button btnValider = new Button("Add Exposition");
     
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfTheme.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   /* try {*/
                        Exposition ev = new Exposition(tfName.getText(),tfTheme.getText());
                        if( ServiceExposition.getInstance().addExposition(ev))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                   /* } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }*/
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfTheme,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
