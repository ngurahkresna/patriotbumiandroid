package com.example.patriotbumiandroid.model;

public class Order {
    private String id, cart, user, status;

    public Order(String id, String cart, String user, String status) {
        this.id = id;
        this.cart = cart;
        this.user = user;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
