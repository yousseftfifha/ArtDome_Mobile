/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author max
 */
public class Blog {
    private int idBlog;
    private String Title;
    private String Categorie;
    private Date DateOfPub;
    private String Description;
    private String Publisher;
    private String Image;

    public Blog(String Title, String Categorie) {
        this.Title = Title;
        this.Categorie = Categorie;
    }

    public Blog(int idBlog, String Title, String Categorie, Date DateOfPub, String Description, String Publisher, String Image) {
        
        this.idBlog = idBlog;
        this.Title = Title;
        this.Categorie = Categorie;
        this.DateOfPub = DateOfPub;
        this.Description=Description;
        this.Publisher = Publisher;
        this.Image = Image;
    }

    public Blog() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(int idBlog) {
        this.idBlog = idBlog;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public Date getDateOfPub() {
        return DateOfPub;
    }

    public void setDateOfPub(Date DateOfPub) {
        this.DateOfPub = DateOfPub;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String Publisher) {
        this.Publisher = Publisher;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    @Override
    public String toString() {
        return "blog{" + "idBlog=" + idBlog + ", Title=" + Title + ", Categorie=" + Categorie + ", DateOfPub=" + DateOfPub + ", Description=" + Description + ", Publisher=" + Publisher + ", Image=" + Image + '}';
    }
    
}
