package model;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {

    private String name;
    private ArrayList<MenuItem> items = new ArrayList<>();
    private float price;

    public CompositeProduct(){
        this.items = new ArrayList<>();
        price = -1;
    }

   // public CompositeProduct(String name, ArrayList<MenuItem> items, int price) {
      //  this.name = name;
      //  this.items = items;
       // this.price = price;
   // }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<MenuItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return
                name + " || " +
                " price=" + price;
    }


    @Override
    public float getPrice() {
        int sum = 0;

        for(MenuItem bp : items){
            sum += bp.getPrice();
        }
        return sum;
    }

    public void setPrice() {
        int sum = 0;

        for(MenuItem bp : items){
            sum += bp.getPrice();
        }
        this.price =  sum;
    }

    public void setName(String name){
        this.name = name;
    }
}
