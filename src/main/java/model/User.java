package model;

import model.Order;

import java.util.ArrayList;

public class User {
    private int ID;
    private String type;
    private String name;
    private String password;
    private ArrayList<Order> orders;

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public User(){
        this.type = null;
        this.name = null;
        this.password = null;
        this.orders = new ArrayList<>();
    }

    public User(int ID, String type, String name, String password) {
        this.ID = ID;
        this.type = type;
        this.name = name;
        this.password = password;
        this.orders = new ArrayList<>();
    }

    public User(String type, String name, String password) {
        this.type = type;
        this.name = name;
        this.password = password;
        this.orders = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int minPrice(int price){
        for(Order o : this.getOrders()){//parcurgem comenzile lui
            if(o.getPrice() >= price){//daca are o comanda mai mare de pretul necesar
               return 1;
            }
        }
        return 0;
    }
}
