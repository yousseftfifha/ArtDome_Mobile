/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

/**
 *
 * @author HP
 */
import com.codename1.share.EmailShare;
import com.codename1.share.FacebookShare;
import com.codename1.share.SMSShare;
import com.codename1.share.ShareService;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import java.util.Vector;


public class ShareButton extends Button implements ActionListener{
    
    private String textToShare;
    
    private String imageToShare;
    
    private String imageMimeType;
    
    private Vector shareServices = new Vector();
    
    /**
     * Default constructor
     */
    public ShareButton() {
        setUIID("ShareButton");
        //Image shareIcon =  Resources.getSystemResource().getImage("share.png");
        //setIcon(shareIcon);
        FontImage.setMaterialIcon(this, FontImage.MATERIAL_SHARE);
        addActionListener(this);
    
        shareServices.addElement(new EmailShare());
        shareServices.addElement(new FacebookShare());
    }
    
    /**
     * Sets the information to share
     * @param textToShare 
     */
    public void setTextToShare(String textToShare){
        this.textToShare = textToShare;
    }

    /**
     * Gets the text to share
     * @return 
     */
    public String getTextToShare() {
        return textToShare;
    }

    /**
     * Sets the image to share.
     * Notice some sharing services cannot share image and a text, therefore if
     * setTextToShare(...) is also used, the sharing service gives image sharing 
     * higher priority.
     * 
     * @param imagePath the full file path
     * @param imageMimeType the image mime type e.g. image/png, image/jpeg
     */
    public void setImageToShare(String imagePath, String imageMimeType) {
        this.imageToShare = imagePath;
        this.imageMimeType = imageMimeType;
    }

    /**
     * Gets the image path to share
     * @return 
     */
    public String getImagePathToShare() {
        return imageToShare;
    }
    

    /**
     * Adds a sharing service.
     * @param share ShareService
     */
    public void addShareService(ShareService share){
        shareServices.addElement(share);
    }
    
    /**
     * invoked when the button is pressed
     * @param evt 
     */
    public void actionPerformed(ActionEvent evt) {
        // postpone the share button action to the next EDT cycle to allow action listeners on the button to 
        // process first
        Display.getInstance().callSerially(new Runnable() {
            public void run() {
                if(Display.getInstance().isNativeShareSupported()){
                    Display.getInstance().share(textToShare, imageToShare, imageMimeType, new Rectangle(
                            ShareButton.this.getAbsoluteX(),
                            ShareButton.this.getAbsoluteY(),
                            ShareButton.this.getWidth(),
                            ShareButton.this.getHeight()
                    ));                
                    return;
                }
                Vector sharing;
                if(imageToShare != null){
                    sharing = new Vector();
                    for (int i = 0; i < shareServices.size(); i++) {
                        ShareService share = (ShareService) shareServices.elementAt(i);
                        if(share.canShareImage()){
                            sharing.add(share);
                        }
                    }
                }else{
                    sharing = shareServices;
                }
                for (int i = 0; i < sharing.size(); i++) {
                    ShareService share = (ShareService) sharing.elementAt(i);
                    share.setMessage(textToShare);
                    share.setImage(imageToShare, imageMimeType);
                    share.setOriginalForm(getComponentForm());
                }
                List l = new List(sharing);
                l.setCommandList(true);
                final Dialog dialog = new Dialog("Share");
                dialog.setLayout(new BorderLayout());
                dialog.addComponent(BorderLayout.CENTER, l);
                dialog.placeButtonCommands(new Command[]{new Command("Cancel")});
                l.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        dialog.dispose();
                    }
                });
                dialog.show();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public String[] getPropertyNames() {
        return new String[]{"textToShare"};
    }

    /**
     * {@inheritDoc}
     */
    public Class[] getPropertyTypes() {
        return new Class[]{String.class};
    }

    /**
     * {@inheritDoc}
     */
    public Object getPropertyValue(String name) {
        if (name.equals("textToShare")) {
            return getTextToShare();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String setPropertyValue(String name, Object value) {
        if (name.equals("textToShare")) {
            setTextToShare((String) value);
            return null;
        }
        return super.setPropertyValue(name, value);
    }
    
}