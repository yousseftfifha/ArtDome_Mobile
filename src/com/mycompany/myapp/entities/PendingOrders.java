/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author yotfi
 */
public class PendingOrders {
    private int ID_PendingOrder;
    private User IDUser;
    private double innonumber;
    private Oeuvre OeuvreID;
    private int Quantity;
    private String Status;

    public PendingOrders() {
    }

    public PendingOrders(int ID_PendingOrder, User IDUser, double innonumber, Oeuvre OeuvreID, int Quantity, String Status) {
        this.ID_PendingOrder = ID_PendingOrder;
        this.IDUser = IDUser;
        this.innonumber = innonumber;
        this.OeuvreID = OeuvreID;
        this.Quantity = Quantity;
        this.Status = Status;
    }

    public int getID_PendingOrder() {
        return ID_PendingOrder;
    }

    public void setID_PendingOrder(int ID_PendingOrder) {
        this.ID_PendingOrder = ID_PendingOrder;
    }

    public User getIDUser() {
        return IDUser;
    }

    public void setIDUser(User IDUser) {
        this.IDUser = IDUser;
    }

    public double getInnonumber() {
        return innonumber;
    }

    public void setInnonumber(double innonumber) {
        this.innonumber = innonumber;
    }

    public Oeuvre getOeuvreID() {
        return OeuvreID;
    }

    public void setOeuvreID(Oeuvre OeuvreID) {
        this.OeuvreID = OeuvreID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "PendingOrders{" + "ID_PendingOrder=" + ID_PendingOrder + ", IDUser=" + IDUser + ", innonumber=" + innonumber + ", OeuvreID=" + OeuvreID + ", Quantity=" + Quantity + ", Status=" + Status + '}';
    }
    
}
