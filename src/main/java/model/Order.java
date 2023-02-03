package model;

import java.util.ArrayList;

public class Order{
    private int orderID;
    private int clientID;
    private String orderDate;
    private String orderTime;
    int price;
    private ArrayList<MenuItem> orderedProducts;

    public Order(){
        this.orderedProducts = new ArrayList<>();
    }

    public Order(int orderID, int clientID, String orderDate, String orderTime, int price, ArrayList<BaseProduct> orderedProducts) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.price = price;
        this.orderedProducts = new ArrayList<>();
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setorderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<MenuItem> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(ArrayList<MenuItem> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
