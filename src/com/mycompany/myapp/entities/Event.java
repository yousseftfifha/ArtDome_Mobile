/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Event {
    
    private int codeEvent;
    private String nomEvent;
    private String themeEvent;
    private String etat;
    private Date date;
    private int nbMaxPart;
    private String image;
    private String video;
    private Endroit codeEspace;
    private User codeArtiste;

    public Event() {
    }

    public Event(String nomEvent, String themeEvent) {
        this.nomEvent = nomEvent;
        this.themeEvent = themeEvent;
    }

    
    public Event(int codeEvent, String nomEvent, String themeEvent, String etat, Date date, int nbMaxPart, String image, String video, Endroit codeEspace, User codeArtiste) {
        this.codeEvent = codeEvent;
        this.nomEvent = nomEvent;
        this.themeEvent = themeEvent;
        this.etat = etat;
        this.date = date;
        this.nbMaxPart = nbMaxPart;
        this.image = image;
        this.video = video;
        this.codeEspace = codeEspace;
        this.codeArtiste = codeArtiste;
    }

    public int getCodeEvent() {
        return codeEvent;
    }

    public void setCodeEvent(int codeEvent) {
        this.codeEvent = codeEvent;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public String getThemeEvent() {
        return themeEvent;
    }

    public void setThemeEvent(String themeEvent) {
        this.themeEvent = themeEvent;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbMaxPart() {
        return nbMaxPart;
    }

    public void setNbMaxPart(int nbMaxPart) {
        this.nbMaxPart = nbMaxPart;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Endroit getCodeEspace() {
        return codeEspace;
    }

    public void setCodeEspace(Endroit codeEspace) {
        this.codeEspace = codeEspace;
    }

    public User getCodeArtiste() {
        return codeArtiste;
    }

    public void setCodeArtiste(User codeArtiste) {
        this.codeArtiste = codeArtiste;
    }

    @Override
    public String toString() {
        return "Event{" + "codeEvent=" + codeEvent + ", nomEvent=" + nomEvent + ", themeEvent=" + themeEvent + ", etat=" + etat + ", date=" + date + ", nbMaxPart=" + nbMaxPart + ", image=" + image + ", video=" + video + ", codeEspace=" + codeEspace + ", codeArtiste=" + codeArtiste + '}';
    }

    
   

    
}
