package model;

import org.apache.poi.ss.usermodel.Row;

public class BaseProduct extends MenuItem {



    public BaseProduct() {
    }

    public BaseProduct(String title, float rating, float calories, float protein, float fat, float sodium, float price, int times) {
        super(title, rating, calories, protein, fat, sodium, price, times);
    }

    @Override
    public String toString() {
        return  this.getTitle() +
                "|| kcal = " + this.getCalories() +
                "|| price = " + this.getPrice();
    }

}
