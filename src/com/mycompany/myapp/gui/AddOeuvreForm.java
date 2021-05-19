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
import com.mycompany.myapp.entities.Oeuvre;
import com.mycompany.myapp.services.ServiceOeuvre;

/**
 *
 * @author user
 */
public class AddOeuvreForm extends Form {

    public AddOeuvreForm(Form previous) {
         setTitle("add Oeuvre");
         setLayout(BoxLayout.y());
         
         
        TextField tfNomOeuvre = new TextField("","Oeuvre Name ");
        TextField tfPrixOeuvre = new TextField("","Oeuvre Price ");
//        TextField tfImageOeuvre = new TextField("","Oeuvre Image ");
    //    TextField tfNomCat= new TextField("", "Oeuvre Category");
        TextField tfemailArtiste= new TextField("", "artist email");
//        TextField tfID_Artiste = new TextField("","Artist ID");
        Button btnValider = new Button("Add Oeuvre");
     
        btnValider.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 if ((tfNomOeuvre.getText().length()==0)||(tfPrixOeuvre.getText().length()==0)||(tfemailArtiste.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                 else {
                    try {
                     
                        
                        if(ServiceOeuvre.getInstance().addOeuvre(tfNomOeuvre, tfPrixOeuvre, tfemailArtiste))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                 
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "price must be a number", new Command("OK"));
                    
                   }
  Dialog.show("Success","Connection accepted",new Command("OK"));
                 }}
        });
      addAll(tfNomOeuvre,tfPrixOeuvre,tfemailArtiste,btnValider);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    
    }}
    

