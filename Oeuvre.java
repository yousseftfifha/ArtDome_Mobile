/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author user
 */
public class Oeuvre {
    private int idOeuvre;
    private String nomoeuvre;
    private float prixoeuvre ;
    private Date dateoeuvre;
    private String imageoeuvre;
    private String nomcat;
    private String emailartiste ;
    private int idArtiste;

    public Oeuvre() {
    }

    public Oeuvre(String nomoeuvre, float prixoeuvre, Date dateoeuvre, String imageoeuvre, String nomcat, String emailartiste, int idArtiste) {
        this.nomoeuvre = nomoeuvre;
        this.prixoeuvre = prixoeuvre;
        this.dateoeuvre = dateoeuvre;
        this.imageoeuvre = imageoeuvre;
        this.nomcat = nomcat;
        this.emailartiste = emailartiste;
        this.idArtiste = idArtiste;
    }

    public Oeuvre(int idOeuvre, String nomoeuvre, float prixoeuvre, Date dateoeuvre, String imageoeuvre, String nomcat, String emailartiste, int idArtiste) {
        this.idOeuvre = idOeuvre;
        this.nomoeuvre = nomoeuvre;
        this.prixoeuvre = prixoeuvre;
        this.dateoeuvre = dateoeuvre;
        this.imageoeuvre = imageoeuvre;
        this.nomcat = nomcat;
        this.emailartiste = emailartiste;
        this.idArtiste = idArtiste;
    }

    public Oeuvre(String nomoeuvre, float prixoeuvre, String nomcat, String emailartiste) {
        this.nomoeuvre = nomoeuvre;
        this.prixoeuvre = prixoeuvre;
        this.nomcat = nomcat;
        this.emailartiste = emailartiste;
    }


    

    public Oeuvre(int parseInt, String text, String text0, String text1) {
       
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public String getNomoeuvre() {
        return nomoeuvre;
    }

    public void setNomoeuvre(String nomoeuvre) {
        this.nomoeuvre = nomoeuvre;
    }

    public float getPrixoeuvre() {
        return prixoeuvre;
    }

    public void setPrixoeuvre(float prixoeuvre) {
        this.prixoeuvre = prixoeuvre;
    }

    public Date getDateoeuvre() {
        return dateoeuvre;
    }

    public void setDateoeuvre(Date dateoeuvre) {
        this.dateoeuvre = dateoeuvre;
    }

    public String getImageoeuvre() {
        return imageoeuvre;
    }

    public void setImageoeuvre(String imageoeuvre) {
        this.imageoeuvre = imageoeuvre;
    }

    public String getNomcat() {
        return nomcat;
    }

    public void setNomcat(String nomcat) {
        this.nomcat = nomcat;
    }

    public String getEmailartiste() {
        return emailartiste;
    }

    public void setEmailartiste(String emailartiste) {
        this.emailartiste = emailartiste;
    }

    public int getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(int idArtiste) {
        this.idArtiste = idArtiste;
    }

    @Override
    public String toString() {
        return "Oeuvre{" + "idOeuvre=" + idOeuvre + ", nomoeuvre=" + nomoeuvre + ", prixoeuvre=" + prixoeuvre + ", dateoeuvre=" + dateoeuvre + ", imageoeuvre=" + imageoeuvre + ", nomcat=" + nomcat + ", emailartiste=" + emailartiste + ", idArtiste=" + idArtiste + '}';
    }

       
}




  
