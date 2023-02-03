package presentation;
import businessLogic.*;

import model.MenuItem;
import model.BaseProduct;
import model.CompositeProduct;
import model.Order;
import model.User;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AdminWin {

    private JFrame frame;
    private JTextField textRating;
    private JTextField textCalories;
    private JTextField textProteins;
    private JTextField textFat;
    private JTextField textSodium;
    private JTextField textPrice;
    private JTextField textTitle;
    private JTextField textIntervalBegin;
    private JTextField textIntervalEnd;
    private JTextField textPOMT;
    private JTextField textTimesCOMT;
    private JTextField textPriceCOMT;
    private JTextField textDatePOTD;
    private DefaultListModel listModelMenu;
    private DefaultListModel listModelCompositeMenu;
    private JList menuList;
    private JList compositeMenuList;
    private JScrollPane JSMenu;
    private JScrollPane JSCompositeMenu;

    public AdminWin(DeliveryService dS) {

                frame = new JFrame();
                frame.setBounds(100, 100, 1100, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setLayout(null);

                JLabel title = new JLabel("ADMIN - WELCOME");
                title.setFont(new Font("Tahoma", Font.PLAIN, 21));
                title.setBounds(479, 11, 200, 50);
                frame.getContentPane().add(title);

                JLabel lblProtein = new JLabel("Proteins");
                lblProtein.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblProtein.setBounds(25, 148, 86, 33);
                frame.getContentPane().add(lblProtein);

                JLabel lblCalories = new JLabel("Calories");
                lblCalories.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblCalories.setBounds(25, 104, 86, 33);
                frame.getContentPane().add(lblCalories);

                JButton btnAdd = new JButton("Add product");
                btnAdd.setBounds(10, 332, 110, 33);
                btnAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int uncompleted = 0;
                        String title;
                        float rating;
                        float calories;
                        float proteins;
                        float fat;
                        float sodium;
                        float price;
                        int times;
                        MenuItem item = new BaseProduct();
                        MenuItem item2 = new BaseProduct();

                        try {
                            title = getTextTitle().getText();
                            rating = Float.parseFloat(getTextRating().getText());
                            calories = Float.parseFloat(getTextCalories().getText());
                            proteins = Float.parseFloat(getTextProteins().getText());
                            fat = Float.parseFloat(getTextFat().getText());
                            sodium = Float.parseFloat(getTextSodium().getText());
                            price = Float.parseFloat(getTextPrice().getText());
                            times = 0;
                            item2 = new BaseProduct(title, rating, calories, proteins, fat, sodium, price, times);
                            dS.getMenu().add(item2);

                            listModelMenu.removeAllElements();
                            for(MenuItem m : dS.getMenu()){
                                listModelMenu.addElement(m);
                            }

                            getTextTitle().setText("");
                            getTextCalories().setText("");
                            getTextSodium().setText("");
                            getTextProteins().setText("");
                            getTextPrice().setText("");
                            getTextRating().setText("");
                            getTextFat().setText("");

                            getFrame().repaint();


                        }catch(Exception exception){
                            JOptionPane.showMessageDialog(frame, "Insert all the REQUIRED data and make sure it is the REQUIRED TYPE");
                        }
                    }
                });
                frame.getContentPane().add(btnAdd);

                JLabel lblTitle = new JLabel("Title");
                lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblTitle.setBounds(25, 22, 86, 33);
                frame.getContentPane().add(lblTitle);

                JLabel lblFat = new JLabel("Fat");
                lblFat.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblFat.setBounds(25, 192, 86, 33);
                frame.getContentPane().add(lblFat);

                JLabel lblSodium = new JLabel("Sodium");
                lblSodium.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblSodium.setBounds(25, 236, 86, 33);
                frame.getContentPane().add(lblSodium);

                textRating = new JTextField();
                textRating.setColumns(10);
                textRating.setBounds(110, 61, 149, 34);
                frame.getContentPane().add(textRating);

                textCalories = new JTextField();
                textCalories.setColumns(10);
                textCalories.setBounds(110, 105, 149, 34);
                frame.getContentPane().add(textCalories);

                JButton btnDelete = new JButton("Delete");
                btnDelete.setBounds(10, 376, 110, 33);
                btnDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            dS.getMenu().remove(menuList.getSelectedIndex());

                            listModelMenu.removeAllElements();
                            for(MenuItem m : dS.getMenu()){
                                listModelMenu.addElement(m);
                            }

                            getFrame().repaint();

                        }catch (Exception exc){
                            JOptionPane.showMessageDialog(frame, "ca sa stergeti selectati un item din meniu");
                        }
                    }
                });
                frame.getContentPane().add(btnDelete);

                JButton btnBack = new JButton("Back");
                btnBack.setBounds(10, 417, 110, 33);
                btnBack.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        LogInWin win = new LogInWin(dS);
                        win.getFrame().setVisible(true);
                    }
                });
                frame.getContentPane().add(btnBack);

                JLabel lblPrice = new JLabel("Price");
                lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblPrice.setBounds(25, 280, 86, 33);
                frame.getContentPane().add(lblPrice);

                textProteins = new JTextField();
                textProteins.setColumns(10);
                textProteins.setBounds(110, 149, 149, 34);
                frame.getContentPane().add(textProteins);

                textFat = new JTextField();
                textFat.setColumns(10);
                textFat.setBounds(110, 193, 149, 34);
                frame.getContentPane().add(textFat);

                textSodium = new JTextField();
                textSodium.setColumns(10);
                textSodium.setBounds(110, 237, 149, 34);
                frame.getContentPane().add(textSodium);

                textPrice = new JTextField();
                textPrice.setColumns(10);
                textPrice.setBounds(110, 281, 149, 34);
                frame.getContentPane().add(textPrice);

                JButton btnModify = new JButton("Modify Product");
                btnModify.setBounds(149, 332, 110, 33);
                btnModify.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int uncompleted = 0;
                        String title;
                        float rating;
                        float calories;
                        float proteins;
                        float fat;
                        float sodium;
                        float price;
                        int times;
                        MenuItem item = new BaseProduct();
                        MenuItem item2 = new BaseProduct();

                        try {
                            title = getTextTitle().getText();
                            rating = Float.parseFloat(getTextRating().getText());
                            calories = Float.parseFloat(getTextCalories().getText());
                            proteins = Float.parseFloat(getTextProteins().getText());
                            fat = Float.parseFloat(getTextFat().getText());
                            sodium = Float.parseFloat(getTextSodium().getText());
                            price = Float.parseFloat(getTextPrice().getText());
                            times = dS.getMenu().get(menuList.getSelectedIndex()).getTimes();
                            item2 = new BaseProduct(title, rating, calories, proteins, fat, sodium, price, times);
                            dS.getMenu().set(menuList.getSelectedIndex(), item2);

                            listModelMenu.removeAllElements();
                            for(MenuItem m : dS.getMenu()){
                                listModelMenu.addElement(m);
                            }

                            getTextTitle().setText("");
                            getTextCalories().setText("");
                            getTextSodium().setText("");
                            getTextProteins().setText("");
                            getTextPrice().setText("");
                            getTextRating().setText("");
                            getTextFat().setText("");

                            getFrame().repaint();


                        }catch(Exception exception){
                            JOptionPane.showMessageDialog(frame, "Insert all the REQUIRED data and make sure it is the REQUIRED TYPE");
                        }

                        /*if(getTextTitle().getText().isEmpty() == false){
                            title = getTextTitle().getText();
                        }else uncompleted ++;

                        if(getTextRating().getText().isEmpty() == false){
                            rating = Float.parseFloat(getTextRating().getText());
                        }else uncompleted ++;

                        if(getTextCalories().getText().isEmpty() == false){
                            calories = Float.parseFloat(getTextCalories().getText());
                        }else uncompleted ++;

                        if(getTextProteins().getText().isEmpty() == false){
                            proteins = Float.parseFloat(getTextProteins().getText());
                        }else uncompleted ++;

                        if(getTextFat().getText().isEmpty() == false){
                            proteins = Float.parseFloat(getTextProteins().getText());
                        }else uncompleted ++;*/

                       // MenuItem mi = dS.getMenu().get(menuList.getSelectedIndex());

                    }
                });
                frame.getContentPane().add(btnModify);

                listModelMenu = new DefaultListModel<>();
                menuList = new JList(listModelMenu);

                for(MenuItem m : dS.getMenu()){
                    listModelMenu.addElement(m);
                }
                JSMenu = new JScrollPane(menuList);
                JSMenu.setBounds(294, 104, 285, 346);

                menuList.setBounds(294, 104, 285, 346);
                frame.getContentPane().add(JSMenu);

                listModelCompositeMenu = new DefaultListModel<>();
                compositeMenuList = new JList(listModelCompositeMenu);

                for(MenuItem m : dS.getMenus()){
                    listModelCompositeMenu.addElement(m);
                }

                JSCompositeMenu = new JScrollPane(compositeMenuList);
                JSCompositeMenu.setBounds(589, 104, 285, 346);
                compositeMenuList.setBounds(589, 104, 285, 346);
                frame.getContentPane().add(JSCompositeMenu);

                JLabel lblFullMenu = new JLabel("Full Menu");
                lblFullMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblFullMenu.setBounds(383, 60, 86, 33);
                frame.getContentPane().add(lblFullMenu);

                JLabel lblMenus = new JLabel("Menus");
                lblMenus.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblMenus.setBounds(677, 60, 86, 33);
                frame.getContentPane().add(lblMenus);

                JButton btnCompute = new JButton("Compute Prod.");
                btnCompute.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(getTextTitle().getText().isEmpty() == false){

                            ArrayList<MenuItem> selectedProducts = new ArrayList<>();
                            selectedProducts = (ArrayList<MenuItem>) menuList.getSelectedValuesList();///!! DE CE A DOUA OARA NU MAI FACE CAST ?

                            CompositeProduct menu = new CompositeProduct();
                            String title = getTextTitle().getText();
                            menu.setItems(selectedProducts);
                            menu.setName(title);
                            menu.setPrice();

                            dS.getMenus().add(menu);
                            dS.getMenu().add(menu);

                            listModelMenu.removeAllElements();
                            for(MenuItem b : dS.getMenu()){
                               listModelMenu.addElement(b.toString());
                            }

                            listModelCompositeMenu.removeAllElements();
                            for(MenuItem b : dS.getMenus()){
                                listModelCompositeMenu.addElement(b.toString());
                            }




                            // listModelCompositeMenu.addElement(menu.toString());
                            //listModelMenu.addElement(menu.toString());

                            getFrame().repaint();

                        }else{
                            JOptionPane.showMessageDialog(frame, "Insert the title of the new composed menu");
                        }
                    }
                });
                btnCompute.setBounds(149, 376, 110, 33);
                frame.getContentPane().add(btnCompute);

                textTitle = new JTextField();
                textTitle.setColumns(10);
                textTitle.setBounds(110, 23, 149, 34);
                frame.getContentPane().add(textTitle);

                JLabel lblRating = new JLabel("Rating");
                lblRating.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblRating.setBounds(25, 60, 86, 33);
                frame.getContentPane().add(lblRating);

                JButton btnRaport = new JButton("Gen. Raport");
                btnRaport.setBounds(935, 417, 110, 33);
                btnRaport.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int completedFields = 0;
                        if(!getTextIntervalBegin().getText().equals("")){
                            completedFields ++;
                        }

                        if(!getTextPOMT().getText().equals("")){
                            completedFields ++;
                        }

                        if(!getTextTimesCOMT().getText().equals("")){
                            completedFields ++;
                        }

                        if(!getTextDatePOTD().getText().equals("")){
                            completedFields ++;
                        }

                        if(completedFields > 1){//vedem cate campuri avem completate daca sunt mai multe de una nu e bine
                            JOptionPane.showMessageDialog(frame, "Complete ONLY ONE FIELD");
                        }else if(completedFields < 1){//daca este mai putin de un camp iar nu e bine
                            JOptionPane.showMessageDialog(frame, "Cannot search without any field completed");
                        }else{//daca este fix un cam, atunci detectam care camp e liber
                                if(getTextIntervalBegin().getText().isEmpty() == false){//avem inceputul intervalului
                                    if(getTextIntervalEnd().getText().isEmpty() == false){
                                        //avem datele intervalului orar, si putem sa incepem cautarea folosind stream
                                        ArrayList<Order> selectedOrders = new ArrayList<>();
                                        //selectam in functie de intervalul de inceput
                                        selectedOrders = (dS.getAllOrders().stream().filter(x -> x.getOrderDate()
                                                .contains(getTextIntervalBegin().getText())).collect(Collectors.toCollection(ArrayList::new)));

                                        //selectam in functie de timpul de final
                                        selectedOrders = (selectedOrders.stream().filter(x -> x.getOrderTime()
                                                .contains(getTextIntervalEnd().getText())).collect(Collectors.toCollection(ArrayList::new)));

                                        //acum avem in selectedOrders orderele care sunt in intervalul acela orar
                                        //scriem in fisierul raport
                                        //path-ul unde se afla fisierul in care vreau sa scriu
                                        File file =  new File("C:\\Users\\PC\\Desktop\\facultate\\An 2 sem 2\\TP\\lab\\FoodPanda\\raportComenzi\\raport.txt");

                                        try{
                                            //file.createNewFile();
                                            FileWriter writer = new FileWriter(file);
                                            StringBuilder sb = new StringBuilder();


                                            for(Order o : selectedOrders){
                                                sb.append("====================================\n");
                                                sb.append("COMANDA NR. ");
                                                sb.append(o.getOrderID());
                                                sb.append("\n");
                                                sb.append("====================================\n\n\n");
                                            }

                                            System.out.println(sb);

                                            writer.write(String.valueOf(sb));

                                            writer.close();
                                        }catch(IOException exception){
                                            exception.printStackTrace();
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(frame, "introduceti finalul intervalului!");
                                    }

                                }else if(getTextPOMT().getText().isEmpty() == false){//daca butonul cu more than e completat
                                    //avem datele intervalului orar, si putem sa incepem cautarea folosind stream
                                    ArrayList<MenuItem> selectedOrders = new ArrayList<>();
                                    //selectam in functie de intervalul de inceput
                                    selectedOrders = (dS.getMenu().stream().filter(x -> x.getTimes()
                                            >= Integer.parseInt(getTextPOMT().getText())).collect(Collectors.toCollection(ArrayList::new)));


                                    //scriem in fisierul raport
                                    //path-ul unde se afla fisierul in care vreau sa scriu
                                    File file =  new File("C:\\Users\\PC\\Desktop\\facultate\\An 2 sem 2\\TP\\lab\\FoodPanda\\raportComenzi\\raport.txt");

                                    try{
                                        //file.createNewFile();
                                        FileWriter writer = new FileWriter(file);
                                        StringBuilder sb = new StringBuilder();


                                        for(MenuItem o : selectedOrders){
                                            sb.append("====================================\n");
                                            sb.append("Produsul cu numele : ");
                                            sb.append(o.getTitle());
                                            sb.append("\n Cumparat de : ");
                                            sb.append(o.getTimes());
                                            sb.append(" ori");
                                            sb.append("\n");
                                            sb.append("====================================\n\n\n");
                                        }

                                        System.out.println(sb);

                                        writer.write(String.valueOf(sb));

                                        writer.close();
                                    }catch(IOException exception){
                                        exception.printStackTrace();
                                    }

                                    for(MenuItem mi : selectedOrders){
                                        System.out.println(mi.getTitle() + " || " + mi.getTimes());
                                    }

                                }else if(getTextTimesCOMT().getText().isEmpty() == false){
                                            if(getTextPriceCOMT().getText().isEmpty() == false){

                                                ArrayList<User> selectedUsers = new ArrayList<>();

                                                int times = Integer.parseInt(getTextTimesCOMT().getText());
                                                System.out.println("TIMP " + times);
                                                int price = Integer.parseInt(getTextPriceCOMT().getText());
                                                System.out.println("PRET " + price);

                                                selectedUsers = (dS.getUsers().stream().filter(x -> x.getType()
                                                        .equals("client")).collect(Collectors.toCollection(ArrayList::new)));//selectam toti userii de tip client

                                                selectedUsers = (selectedUsers.stream().filter(x -> x.getOrders().size() >= times)
                                                        .collect(Collectors.toCollection(ArrayList::new)));//selectam numai clientii care au comandat de cel putin times ori

                                                selectedUsers = (selectedUsers.stream().filter(x -> x.getOrders().stream().anyMatch(y -> y.getPrice() >= price)                                                                                                            )
                                                                .collect(Collectors.toCollection(ArrayList::new)));//verificam daca in acele liste de clienti este vreun client
                                                                                                                   //bun

                                              /*  for(User u : dS.getUsers()){//luam fiecare user separat
                                                    if(u.getType().equals("client")){//daca e client
                                                        if(u.getOrders().size() >= times){//daca a avut peste numarul de comenzi necesar
                                                            int ok = 0;
                                                            for(Order o : u.getOrders()){//parcurgem comenzile lui
                                                                System.out.println("PRET COMANDA ===>" + o.getPrice());
                                                                if(o.getPrice() >= price){//daca are o comanda mai mare de pretul necesar
                                                                    ok = 1;
                                                                }
                                                            }

                                                            if(ok == 1){
                                                                selectedUsers.add(u);
                                                            }
                                                        }
                                                        System.out.println("JAJAJ CLIENTUL : " + u.getID() + "\nSIZE : " + u.getOrders().size());
                                                    }
                                                }
                                                */
                                                //scriem in fisierul raport
                                                //path-ul unde se afla fisierul in care vreau sa scriu
                                                File file =  new File("C:\\Users\\PC\\Desktop\\facultate\\An 2 sem 2\\TP\\lab\\FoodPanda\\raportComenzi\\raport.txt");

                                                try{
                                                    //file.createNewFile();
                                                    FileWriter writer = new FileWriter(file);
                                                    StringBuilder sb = new StringBuilder();


                                                    for(User u : selectedUsers){
                                                        sb.append("====================================\n");
                                                        sb.append("Clientul cu numele : ");
                                                        sb.append(u.getName());
                                                        sb.append("\nCu Id-ul : ");
                                                        sb.append(u.getID());
                                                        sb.append("\n");
                                                        sb.append("====================================\n\n\n");
                                                    }

                                                    System.out.println(sb);

                                                    writer.write(String.valueOf(sb));

                                                    writer.close();
                                                }catch(IOException exception){
                                                    exception.printStackTrace();
                                                }

                                                for(User u : selectedUsers){
                                                    System.out.println(u.getName() + " || " + u.getID());
                                                }

                                            }else{
                                                JOptionPane.showMessageDialog(frame, "daca doriti sa cautati in functie de numar de dati si de pret, inserati in amandoua campurie ceva");
                                            }
                                }else if(getTextDatePOTD().getText().isEmpty() == false){//daca vrem sa facem cautarea in functie de data

                                       /* for(Order o : dS.getAllOrders()){
                                            System.out.println(o.getOrderDate());
                                        }*/

                                        ArrayList<Order> selectedOrders = new ArrayList<>();

                                        selectedOrders = (dS.getAllOrders().stream().filter(x -> x.getOrderDate().contains(getTextDatePOTD().getText())
                                            ).collect(Collectors.toCollection(ArrayList::new)));//selectam toate comenzile din ziua respectiva

                                        ArrayList<MenuItem> items = new ArrayList<>();

                                        for(Order o : selectedOrders){

                                            for(MenuItem m : o.getOrderedProducts()){
                                                if(items.contains(m) == false){

                                                    items.add(m);
                                                }
                                            }
                                        }

                                    //scriem in fisierul raport
                                    //path-ul unde se afla fisierul in care vreau sa scriu
                                    File file =  new File("C:\\Users\\PC\\Desktop\\facultate\\An 2 sem 2\\TP\\lab\\FoodPanda\\raportComenzi\\raport.txt");

                                    try{
                                        //file.createNewFile();
                                        FileWriter writer = new FileWriter(file);
                                        StringBuilder sb = new StringBuilder();


                                        for(MenuItem m : items){
                                            sb.append("====================================\n");
                                            sb.append("Produsul : ");
                                            sb.append(m.getTitle());
                                            sb.append("\nCumparat de ");
                                            sb.append(m.getTimes());
                                            sb.append(" ori\n");
                                            sb.append("====================================\n\n\n");
                                        }

                                        System.out.println(sb);

                                        writer.write(String.valueOf(sb));

                                        writer.close();
                                    }catch(IOException exception){
                                        exception.printStackTrace();
                                    }

                                    for(MenuItem m : items){
                                        System.out.println(m.getTitle() + " || " + m.getTimes());
                                    }


                                }
                        }

                    }
                });
                frame.getContentPane().add(btnRaport);

                JLabel lblTimeInterv = new JLabel("Time Interval");
                lblTimeInterv.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblTimeInterv.setBounds(935, 22, 110, 33);
                frame.getContentPane().add(lblTimeInterv);

                textIntervalBegin = new JTextField();
                textIntervalBegin.setColumns(10);
                textIntervalBegin.setBounds(891, 61, 76, 34);
                frame.getContentPane().add(textIntervalBegin);

                textIntervalEnd = new JTextField();
                textIntervalEnd.setColumns(10);
                textIntervalEnd.setBounds(998, 61, 76, 34);
                frame.getContentPane().add(textIntervalEnd);

                JLabel lblDots = new JLabel(":");
                lblDots.setFont(new Font("Tahoma", Font.PLAIN, 41));
                lblDots.setBounds(973, 55, 37, 34);
                frame.getContentPane().add(lblDots);

                JLabel lblProdOrd = new JLabel("Prod. Ordered more than");
                lblProdOrd.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblProdOrd.setBounds(895, 126, 189, 33);
                frame.getContentPane().add(lblProdOrd);

                textPOMT = new JTextField();
                textPOMT.setColumns(10);
                textPOMT.setBounds(950, 156, 76, 34);
                frame.getContentPane().add(textPOMT);

                JLabel lblClientsOrd = new JLabel("Clients Ordered More than");
                lblClientsOrd.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblClientsOrd.setBounds(885, 201, 189, 33);
                frame.getContentPane().add(lblClientsOrd);

                JLabel lblTimesCOMT = new JLabel("Nr. Times");
                lblTimesCOMT.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblTimesCOMT.setBounds(884, 224, 86, 33);
                frame.getContentPane().add(lblTimesCOMT);

                JLabel lblPriceCOMT = new JLabel("Price");
                lblPriceCOMT.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblPriceCOMT.setBounds(998, 224, 86, 33);
                frame.getContentPane().add(lblPriceCOMT);

                textTimesCOMT = new JTextField();
                textTimesCOMT.setColumns(10);
                textTimesCOMT.setBounds(884, 255, 76, 34);
                frame.getContentPane().add(textTimesCOMT);

                textPriceCOMT = new JTextField();
                textPriceCOMT.setColumns(10);
                textPriceCOMT.setBounds(998, 255, 76, 34);
                frame.getContentPane().add(textPriceCOMT);

                JLabel lblDots_1 = new JLabel(":");
                lblDots_1.setFont(new Font("Tahoma", Font.PLAIN, 41));
                lblDots_1.setBounds(973, 247, 37, 34);
                frame.getContentPane().add(lblDots_1);

                JLabel lblProdOrdDay = new JLabel("Prod. Ordered on the date");
                lblProdOrdDay.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblProdOrdDay.setBounds(885, 309, 189, 33);
                frame.getContentPane().add(lblProdOrdDay);

                textDatePOTD = new JTextField();
                textDatePOTD.setColumns(10);
                textDatePOTD.setBounds(950, 338, 76, 34);
                frame.getContentPane().add(textDatePOTD);

    }

    public JFrame getFrame(){
        return this.frame;
    }

    public JTextField getTextRating() {
        return textRating;
    }

    public void setTextRating(JTextField textRating) {
        this.textRating = textRating;
    }

    public JTextField getTextCalories() {
        return textCalories;
    }

    public void setTextCalories(JTextField textCalories) {
        this.textCalories = textCalories;
    }

    public JTextField getTextProteins() {
        return textProteins;
    }

    public void setTextProteins(JTextField textProteins) {
        this.textProteins = textProteins;
    }

    public JTextField getTextFat() {
        return textFat;
    }

    public void setTextFat(JTextField textFat) {
        this.textFat = textFat;
    }

    public JTextField getTextSodium() {
        return textSodium;
    }

    public void setTextSodium(JTextField textSodium) {
        this.textSodium = textSodium;
    }

    public JTextField getTextPrice() {
        return textPrice;
    }

    public void setTextPrice(JTextField textPrice) {
        this.textPrice = textPrice;
    }

    public JTextField getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(JTextField textTitle) {
        this.textTitle = textTitle;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField getTextIntervalBegin() {
        return textIntervalBegin;
    }

    public void setTextIntervalBegin(JTextField textIntervalBegin) {
        this.textIntervalBegin = textIntervalBegin;
    }

    public JTextField getTextIntervalEnd() {
        return textIntervalEnd;
    }

    public void setTextIntervalEnd(JTextField textIntervalEnd) {
        this.textIntervalEnd = textIntervalEnd;
    }

    public JTextField getTextPOMT() {
        return textPOMT;
    }

    public void setTextPOMT(JTextField textPOMT) {
        this.textPOMT = textPOMT;
    }

    public JTextField getTextTimesCOMT() {
        return textTimesCOMT;
    }

    public void setTextTimesCOMT(JTextField textTimesCOMT) {
        this.textTimesCOMT = textTimesCOMT;
    }

    public JTextField getTextPriceCOMT() {
        return textPriceCOMT;
    }

    public void setTextPriceCOMT(JTextField textPriceCOMT) {
        this.textPriceCOMT = textPriceCOMT;
    }

    public JTextField getTextDatePOTD() {
        return textDatePOTD;
    }

    public void setTextDatePOTD(JTextField textDatePOTD) {
        this.textDatePOTD = textDatePOTD;
    }
}
