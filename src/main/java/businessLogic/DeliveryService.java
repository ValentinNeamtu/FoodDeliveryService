package businessLogic;
import java.io.File;
import java.io.FileInputStream;

import model.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

public class DeliveryService extends Observable {
    private ArrayList<MenuItem> menu = new ArrayList<>();
    private ArrayList<MenuItem> menuSearch = new ArrayList<>();
    private ArrayList<MenuItem> menus = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private User loggedUser = new User();
    private ArrayList<Order> allOrders = new ArrayList<>();

    public DeliveryService(){
        this.menu = new ArrayList<>();
        this.users = new ArrayList<>();
        User u1 = new User(1, "client", "v", "v");
        User u2 = new User (2, "admin", "a", "a");
        User u3 = new User(3, "angajat", "e", "e");
        this.users.add(u1);
        this.users.add(u2);
        this.users.add(u3);

        this.allOrders = new ArrayList<>();
    }

    public DeliveryService(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

    public void ReadFile2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\PC\\Desktop\\facultate\\An 2 sem 2\\TP\\lab\\FoodPanda\\src\\XclMenu\\products.csv"));


        sc.nextLine();//trecem peste prima linie din excel

        while(sc.hasNextLine()){

            String xclLine = sc.nextLine();
            Scanner sc2 = new Scanner(xclLine);
            sc2.useDelimiter(",");

            while(sc2.hasNext()){
                String title = sc2.next();
                float rating = Float.parseFloat(sc2.next());
                float calories = Float.parseFloat(sc2.next());
                float protein = Float.parseFloat(sc2.next());
                float fat = Float.parseFloat(sc2.next());
                float sodium = Float.parseFloat(sc2.next());
                float price = Float.parseFloat(sc2.next());
                int times = 0;


                MenuItem product = new BaseProduct(title, rating, calories, protein, fat, sodium, price, times);

                insertProduct(product);
            }

        }
        this.setMenuSearch(this.getMenu());
        sc.close();

    }

    public void ReadFile (ArrayList<BaseProduct> menu)throws IOException {

        FileInputStream file = new FileInputStream(new File("C:\\Users\\PC\\Desktop\\facultate\\An 2 sem 2\\TP\\lab\\FoodPanda\\src\\XclMenu\\products.csv"));
        HSSFWorkbook wb = new HSSFWorkbook(file);
         HSSFSheet sheet = wb.getSheetAt(0); //excelul nostru are un singur sheet, si acela se afla pe pozitia 0 si este meniul

        int rowPos = 0;
        for(Row row : sheet){

            BaseProduct product = new BaseProduct();//cream un nou menuItem pentru fiecare row, menu item care va fii
                                               //populat in functie de ceea ce se afla in cell-uri
            if(rowPos != 0){
                product.createProduct(row);

            }
            rowPos ++;
        }
        /* */
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public void setMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

    public void insertProduct(MenuItem bP){
        for(MenuItem bp : this.menu){
            if(bP.getTitle().equals(bp.getTitle())){
                return;
            }
        }

        this.menu.add(bP);
    }

    public void inserUser(User user){
        this.users.add(user);
    }

    public void printMenu(){
        for(MenuItem m : this.getMenu()){
            System.out.println(m.getTitle());
        }
    }

    public void printUsers(){
        for(User u : this.users){
            System.out.println(u.getName() + " || " + u.getPassword() + " || " + u.getType() + " || " + u.getID());
        }
    }

    public int existentUser(User user){

        for(User u : this.users){
            if(u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword())){

                return 1;
            }
        }
        return 0;
    }

    public void insertMenus(CompositeProduct cp){
        this.menus.add(cp);
    }

    public User returnExistentUser(User user){
        for(User u : this.users){
            if(u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword())){

                return u;
            }
        }
        return null;
    }

    public ArrayList<MenuItem> getMenuSearch() {
        return menuSearch;
    }

    public void setMenuSearch(ArrayList<MenuItem> menuSearch) {
        this.menuSearch = menuSearch;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public ArrayList<Order> getAllOrders() {
        return allOrders;
    }

    public ArrayList<MenuItem> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<MenuItem> menus) {
        this.menus = menus;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setAllOrders(ArrayList<Order> allOrders) {
        this.allOrders = allOrders;
    }

    public void notifyEmployee(Order o){
        setChanged();
        notifyObservers(o);
    }
}
