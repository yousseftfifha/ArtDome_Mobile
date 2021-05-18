/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Oumaima
 */
public class Exposition {
    
    private int codeExpo;
    private String nomExpo;
    private String themExpo;
    private Date dateExpo;
    private int nbMaxParticipant;
    private Endroit codeEspace;
    private Oeuvre codeOeuvre;

    public Exposition() {
    }

    public Exposition(String nomExpo, String themExpo) {
        this.nomExpo = nomExpo;
        this.themExpo = themExpo;
    }

    
    public Exposition(int codeExpo, String nomExpo, String themExpo, Date dateExpo, int nbMaxParticipant, Endroit codeEspace, Oeuvre codeOeuvre) {
        this.codeExpo = codeExpo;
        this.nomExpo = nomExpo;
        this.themExpo = themExpo;
        this.dateExpo = dateExpo;
        this.nbMaxParticipant = nbMaxParticipant;
        this.codeEspace = codeEspace;
        this.codeOeuvre = codeOeuvre;
    }

    public int getCodeExpo() {
        return codeExpo;
    }

    public void setCodeExpo(int codeExpo) {
        this.codeExpo = codeExpo;
    }

    public String getNomExpo() {
        return nomExpo;
    }

    public void setNomExpo(String nomExpo) {
        this.nomExpo = nomExpo;
    }

    public String getThemExpo() {
        return themExpo;
    }

    public void setThemExpo(String themExpo) {
        this.themExpo = themExpo;
    }

    public Date getDateExpo() {
        return dateExpo;
    }

    public void setDateExpo(Date dateExpo) {
        this.dateExpo = dateExpo;
    }

    public int getNbMaxParticipant() {
        return nbMaxParticipant;
    }

    public void setNbMaxParticipant(int nbMaxParticipant) {
        this.nbMaxParticipant = nbMaxParticipant;
    }

    public Endroit getCodeEspace() {
        return codeEspace;
    }

    public void setCodeEspace(Endroit codeEspace) {
        this.codeEspace = codeEspace;
    }

    public Oeuvre getCodeOeuvre() {
        return codeOeuvre;
    }

    public void setCodeOeuvre(Oeuvre codeOeuvre) {
        this.codeOeuvre = codeOeuvre;
    }

    @Override
    public String toString() {
        return "Exposition{" + "codeExpo=" + codeExpo + ", nomExpo=" + nomExpo + ", themExpo=" + themExpo + ", dateExpo=" + dateExpo + ", nbMaxParticipant=" + nbMaxParticipant + ", codeEspace=" + codeEspace + ", codeOeuvre=" + codeOeuvre + '}';
    }
    
    
    
    
}
