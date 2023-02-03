package model;

import org.apache.poi.ss.usermodel.Row;

public abstract class MenuItem {
    private String title;
    private float rating;
    private float calories;
    private float protein;
    private float fat;
    private float sodium;
    private float price;
    private int times;

    public MenuItem(){

    }

    public MenuItem(String title, float rating, float calories, float protein, float fat, float sodium, float price, int times) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
        this.times = times;
    }


    public void createProduct(Row row){
        this.title = row.getCell(0).toString();
        this.rating = Float.parseFloat(row.getCell(1).toString());
        this.calories = Float.parseFloat(row.getCell(2).toString());
        this.protein = Float.parseFloat(row.getCell(3).toString());
        this.fat = Float.parseFloat(row.getCell(4).toString());
        this.sodium = Float.parseFloat(row.getCell(5).toString());
        this.price = Float.parseFloat(row.getCell(6).toString());
    }

    public void computePrice(int newPrice){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
