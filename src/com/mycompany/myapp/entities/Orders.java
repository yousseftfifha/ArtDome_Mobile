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
public class Orders {
    private int OrderId;
    private User IDUser;
    private float DueAmount;    
    private double InnoNumber;
    private Date OrderDate;
    private String Status;

    public Orders() {
    }

    public Orders(int OrderId, User IDUser, float DueAmount, double InnoNumber, Date OrderDate, String Status) {
        this.OrderId = OrderId;
        this.IDUser = IDUser;
        this.DueAmount = DueAmount;
        this.InnoNumber = InnoNumber;
        this.OrderDate = OrderDate;
        this.Status = Status;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int OrderId) {
        this.OrderId = OrderId;
    }

    public User getIDUser() {
        return IDUser;
    }

    public void setIDUser(User IDUser) {
        this.IDUser = IDUser;
    }

    public float getDueAmount() {
        return DueAmount;
    }

    public void setDueAmount(float DueAmount) {
        this.DueAmount = DueAmount;
    }

    public double getInnoNumber() {
        return InnoNumber;
    }

    public void setInnoNumber(double InnoNumber) {
        this.InnoNumber = InnoNumber;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = (int) (47 * hash + this.InnoNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Orders other = (Orders) obj;
        if (this.InnoNumber != other.InnoNumber) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Orders{" + "OrderId=" + OrderId + ", IDUser=" + IDUser + ", DueAmount=" + DueAmount + ", InnoNumber=" + InnoNumber + ", OrderDate=" + OrderDate + ", Status=" + Status + '}';
    }
    
}
