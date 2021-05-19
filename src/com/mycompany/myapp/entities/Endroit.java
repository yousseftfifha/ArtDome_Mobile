/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author HP
 */
public class Endroit {
     private int id_endroit;
    private String type;
    private int taille;
    private int prix_jour;
    private String location;
    private String disponibilite;

    public Endroit() {
    }

    public Endroit(int id_endroit, String type, int taille, int prix_jour, String location, String disponibilite) {
        this.id_endroit = id_endroit;
        this.type = type;
        this.taille = taille;
        this.prix_jour = prix_jour;
        this.location = location;
        this.disponibilite = disponibilite;
    }

    public int getId_endroit() {
        return id_endroit;
    }

    public void setId_endroit(int id_endroit) {
        this.id_endroit = id_endroit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getPrix_jour() {
        return prix_jour;
    }

    public void setPrix_jour(int prix_jour) {
        this.prix_jour = prix_jour;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return "Endroit{" + "id_endroit=" + id_endroit + ", type=" + type + ", taille=" + taille + ", prix_jour=" + prix_jour + ", location=" + location + ", disponibilite=" + disponibilite + '}';
    }

    
}
