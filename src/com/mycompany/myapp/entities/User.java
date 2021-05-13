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
public class User {
    private int ID;
    private String nom;
    private String prenom;
    private Date datenaissance;
    private String ville;
    private int numero;
    private String image;
    private String roles;
    private String Password;
    private String sexe;
    private int is_verified;

    public User() {
    }

    public User(int ID, String nom, String prenom, Date datenaissance, String ville, int numero, String image, String roles, String Password, String sexe, int is_verified) {
        this.ID = ID;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.ville = ville;
        this.numero = numero;
        this.image = image;
        this.roles = roles;
        this.Password = Password;
        this.sexe = sexe;
        this.is_verified = is_verified;
    }

    public User(int ID, String nom, String prenom, Date datenaissance, String ville, int numero, String Password, String sexe) {
        this.ID = ID;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.ville = ville;
        this.numero = numero;
        this.Password = Password;
        this.sexe = sexe;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(int is_verified) {
        this.is_verified = is_verified;
    }
    
}
