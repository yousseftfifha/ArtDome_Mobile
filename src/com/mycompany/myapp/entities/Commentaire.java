/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author max
 */    
public class Commentaire {
    private int id_commentaire;
    private String text;
    private int CreatedAt;
    private int UpdatedAt;
    private int id_User;
    private int idBlog;

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(int CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public int getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(int UpdatedAt) {
        this.UpdatedAt = UpdatedAt;
    }

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int id_User) {
        this.id_User = id_User;
    }

    public int getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(int idBlog) {
        this.idBlog = idBlog;
    }

    @Override
    public String toString() {
        return "commentaire{" + "id_commentaire=" + id_commentaire + ", text=" + text + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + ", id_User=" + id_User + ", idBlog=" + idBlog + '}';
    }

}

