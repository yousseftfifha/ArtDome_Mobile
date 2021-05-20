/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author yotfi
 */
public class Oeuvre {
    private int ID_Oeuvre;
    private String NomOeuvre;
    private float PrixOeuvre;
    private String ImageOeuvre;
    private int NomCat;
    private Date DateOeuvre;
    private  String EmailArtiste;
    private String color;
    private int code_exposition;
    private int ID_Artiste;

    public Oeuvre() {
    }

    public Oeuvre(int ID_Oeuvre, String NomOeuvre, float PrixOeuvre, String ImageOeuvre, int NomCat, Date DateOeuvre, String EmailArtiste, String color, int code_exposition, int ID_Artiste) {
        this.ID_Oeuvre = ID_Oeuvre;
        this.NomOeuvre = NomOeuvre;
        this.PrixOeuvre = PrixOeuvre;
        this.ImageOeuvre = ImageOeuvre;
        this.NomCat = NomCat;
        this.DateOeuvre = DateOeuvre;
        this.EmailArtiste = EmailArtiste;
        this.color = color;
        this.code_exposition = code_exposition;
        this.ID_Artiste = ID_Artiste;
    }

    public int getID_Oeuvre() {
        return ID_Oeuvre;
    }

    public void setID_Oeuvre(int ID_Oeuvre) {
        this.ID_Oeuvre = ID_Oeuvre;
    }

    public String getNomOeuvre() {
        return NomOeuvre;
    }

    public void setNomOeuvre(String NomOeuvre) {
        this.NomOeuvre = NomOeuvre;
    }

    public float getPrixOeuvre() {
        return PrixOeuvre;
    }

    public void setPrixOeuvre(float PrixOeuvre) {
        this.PrixOeuvre = PrixOeuvre;
    }

    public String getImageOeuvre() {
        return ImageOeuvre;
    }

    public void setImageOeuvre(String ImageOeuvre) {
        this.ImageOeuvre = ImageOeuvre;
    }

    public int getNomCat() {
        return NomCat;
    }

    public void setNomCat(int NomCat) {
        this.NomCat = NomCat;
    }

    public Date getDateOeuvre() {
        return DateOeuvre;
    }

    public void setDateOeuvre(Date DateOeuvre) {
        this.DateOeuvre = DateOeuvre;
    }

    public String getEmailArtiste() {
        return EmailArtiste;
    }

    public void setEmailArtiste(String EmailArtiste) {
        this.EmailArtiste = EmailArtiste;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCode_exposition() {
        return code_exposition;
    }

    public void setCode_exposition(int code_exposition) {
        this.code_exposition = code_exposition;
    }

    public int getID_Artiste() {
        return ID_Artiste;
    }

    public void setID_Artiste(int ID_Artiste) {
        this.ID_Artiste = ID_Artiste;
    }

    @Override
    public String toString() {
        return "Oeuvre{" + "ID_Oeuvre=" + ID_Oeuvre + ", NomOeuvre=" + NomOeuvre + ", PrixOeuvre=" + PrixOeuvre + ", ImageOeuvre=" + ImageOeuvre + ", NomCat=" + NomCat + ", DateOeuvre=" + DateOeuvre + ", EmailArtiste=" + EmailArtiste + ", color=" + color + ", code_exposition=" + code_exposition + ", ID_Artiste=" + ID_Artiste + '}';
    }
    
}
