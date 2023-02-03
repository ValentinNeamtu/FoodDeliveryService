package presentation;
import businessLogic.*;
import model.BaseProduct;
import model.MenuItem;
import model.Order;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Collectors;

public class ClientWin {

    private JFrame frame;
    private JTextField textTitle;
    private JTextField textCalories;
    private JTextField textPrice;
    private  JList listMenu;
    private JList listBasket;
    private JScrollPane JSMenu;
    private JScrollPane JSBasket;
    private DefaultListModel listModelMenu;
    private Order actualOrder = new Order();
    private DefaultListModel listModelBasket;
    private DeliveryService dS;
    private EmployeeWin emp = new EmployeeWin(dS);


    public ClientWin(DeliveryService dS) {

            dS.addObserver(emp);
            frame = new JFrame();
            frame.setBounds(100, 100, 900, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(null);

            JLabel title = new JLabel("Welcome");
            title.setFont(new Font("Tahoma", Font.PLAIN, 21));
            title.setBounds(333, 11, 200, 50);
            frame.getContentPane().add(title);

            listModelMenu = new DefaultListModel();

            listMenu = new JList(listModelMenu);

          

            for(MenuItem b : dS.getMenuSearch()){
                    listModelMenu.addElement(b.toString());
            }



            JSMenu = new JScrollPane(listMenu);
            JSMenu.createVerticalScrollBar();
            listMenu.setBounds(30, 145, 274, 239);
            JSMenu.setBounds(30, 145, 274, 239);
            frame.getContentPane().add(JSMenu);

            listModelBasket = new DefaultListModel();
            listBasket = new JList(listModelBasket);

            ArrayList<String> basket = new ArrayList<String>();

            for(MenuItem b : actualOrder.getOrderedProducts()){
                listModelBasket.addElement(b.toString());
            }

            JSBasket = new JScrollPane(listBasket);
            JSBasket.createVerticalScrollBar();
            JSBasket.setBounds(333, 145, 274, 239);
            listBasket.setBounds(333, 145, 274, 239);
            frame.getContentPane().add(JSBasket);

            JLabel lblMenu = new JLabel("Menu");
            lblMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblMenu.setBounds(30, 101, 86, 33);
            frame.getContentPane().add(lblMenu);

            JLabel lblBasket = new JLabel("Basket");
            lblBasket.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblBasket.setBounds(333, 101, 86, 33);
            frame.getContentPane().add(lblBasket);

            JLabel lblTitle = new JLabel("Title");
            lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblTitle.setBounds(617, 146, 86, 33);
            frame.getContentPane().add(lblTitle);

            JLabel lblCalories = new JLabel("Calories");
            lblCalories.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblCalories.setBounds(617, 202, 86, 33);
            frame.getContentPane().add(lblCalories);

            JLabel lblPrice = new JLabel("Price");
            lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblPrice.setBounds(617, 258, 86, 33);
            frame.getContentPane().add(lblPrice);

            textTitle = new JTextField();
            textTitle.setBounds(713, 145, 149, 34);
            frame.getContentPane().add(textTitle);
            textTitle.setColumns(10);

            textCalories = new JTextField();
            textCalories.setColumns(10);
            textCalories.setBounds(713, 202, 149, 34);
            frame.getContentPane().add(textCalories);

            textPrice = new JTextField();
            textPrice.setColumns(10);
            textPrice.setBounds(713, 258, 149, 34);
            frame.getContentPane().add(textPrice);

            JButton btnRefresh = new JButton("Refresh");
            btnRefresh.setBounds(676, 335, 139, 33);
            btnRefresh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dS.setMenuSearch(dS.getMenu());//dam reser la meniul de search
                    listModelMenu.removeAllElements();
                    listModelBasket.removeAllElements();
                    for(MenuItem b : dS.getMenuSearch()){
                        listModelMenu.addElement(b);
                    }

                    getTextPrice().setText("");
                    getTextCalories().setText("");
                    getTextTitle().setText("");

                    getFrame().repaint();
                }
            });
            frame.getContentPane().add(btnRefresh);

            JButton btnSearch = new JButton("Search");
            btnSearch.setBounds(676, 300, 139, 33);
            btnSearch.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            ArrayList<BaseProduct> newList = new ArrayList<>();
                            int completedFields = 0;
                            if(!getTextCalories().getText().isEmpty()){
                                    completedFields++;
                            }

                            if(!getTextPrice().getText().isEmpty()){
                                    completedFields++;
                            }

                            if(!getTextTitle().getText().isEmpty()){
                                    completedFields++;
                            }

                            if(completedFields == 1){
                                if(!getTextCalories().getText().isEmpty()){//cautam in functie de de calorii
                                    dS.setMenuSearch(dS.getMenu().stream().filter(x -> x.getCalories() == Float.parseFloat(getTextCalories().getText())).collect(Collectors.toCollection(ArrayList::new)));
                                    listModelMenu.removeAllElements();
                                    ArrayList<String> menu = new ArrayList<String>();

                                    for(MenuItem b : dS.getMenuSearch()){
                                        System.out.println(b.toString());
                                        listModelMenu.addElement(b.toString());
                                    }


                                    getFrame().add(JSMenu);
                                    getFrame().repaint();


                                }else if(!getTextPrice().getText().isEmpty()){//cautam in functie de pret
                                    dS.setMenuSearch(dS.getMenu().stream().filter(x -> x.getPrice()
                                            == Float.parseFloat(getTextPrice().getText())).collect(Collectors.toCollection(ArrayList::new)));
                                    listModelMenu.removeAllElements();
                                    ArrayList<String> menu = new ArrayList<String>();

                                    for(MenuItem b : dS.getMenuSearch()){
                                        System.out.println(b.toString());
                                        listModelMenu.addElement(b.toString());
                                    }


                                    getFrame().add(JSMenu);
                                    getFrame().repaint();

                                }else{//cautam in functie de titlu
                                    dS.setMenuSearch(dS.getMenu().stream().filter(x -> x.getTitle()
                                            .contains(getTextTitle().getText())).collect(Collectors.toCollection(ArrayList::new)));
                                    listModelMenu.removeAllElements();
                                    ArrayList<String> menu = new ArrayList<String>();

                                    for(MenuItem b : dS.getMenuSearch()){
                                        System.out.println(b.toString());
                                        listModelMenu.addElement(b.toString());
                                    }


                                    getFrame().add(JSMenu);
                                    getFrame().repaint();
                                }
                            }else{
                                    if(completedFields > 1){
                                            JOptionPane.showMessageDialog(frame, "You can search by one field only");
                                    }else{
                                            JOptionPane.showMessageDialog(frame, "Complete one field of search");
                                    }

                            }

                    }
            });
            frame.getContentPane().add(btnSearch);

            JButton btnOrder = new JButton("Order");
            btnOrder.setBounds(406, 395, 139, 33);
            btnOrder.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    actualOrder.setorderDate(String.valueOf(Calendar.getInstance().getTime()));
                   // actualOrder.setOrderTime();

                    int clientID = dS.getLoggedUser().getID();

                    int orderid =  dS.getAllOrders().toArray().length;
                    actualOrder.setClientID(clientID);
                    actualOrder.setOrderID(orderid);
                    dS.getAllOrders().add(actualOrder);

                    int price = 0;

                    for(MenuItem mi : actualOrder.getOrderedProducts()){
                        mi.setTimes(mi.getTimes() + 1);
                        price += mi.getPrice();
                    }

                    actualOrder.setPrice(price);
                    dS.getLoggedUser().getOrders().add(actualOrder);

                    for(Order o : dS.getAllOrders()){
                        for(MenuItem mi : o.getOrderedProducts()){
                            System.out.println(mi.getTitle() + " || " + mi.getTimes());
                        }
                        System.out.println("ORDERED BY THE client with id : " + o.getClientID());
                    }

                    /*for(MenuItem mi : dS.getMenu()){
                        if(mi.getTimes() > 0){
                            System.out.println(mi.getTitle() + " || " + mi.getTimes());
                        }
                    }*/

                    //path-ul unde se afla fisierul in care vreau sa scriu
                    File file =  new File("C:\\Users\\PC\\Desktop\\facultate\\An 2 sem 2\\TP\\lab\\FoodPanda\\comenzi\\comanda.txt");

                    try{
                        //file.createNewFile();
                        FileWriter writer = new FileWriter(file);
                        writer.write("clientul " + dS.getLoggedUser().getName() + " a efectuat comanda " + actualOrder.getOrderID() + " \nin data de " + actualOrder.getOrderDate());
                        writer.close();
                    }catch(IOException exception){
                        exception.printStackTrace();
                    }
                    dS.notifyEmployee(actualOrder);
                    actualOrder = new Order();
                    listModelBasket.removeAllElements();

                    getFrame().repaint();

                }
            });
            frame.getContentPane().add(btnOrder);

            JButton btnBack = new JButton("Back");
            btnBack.setBounds(676, 395, 139, 33);
            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    LogInWin win = new LogInWin(dS);
                    win.getFrame().setVisible(true);
                }
            });
            frame.getContentPane().add(btnBack);

            JButton btnAdd = new JButton("Add product");
            btnAdd.setBounds(84, 395, 139, 33);
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MenuItem selectedProduct = dS.getMenuSearch().get(listMenu.getSelectedIndex());//luam produsul selectat
                    actualOrder.getOrderedProducts().add(selectedProduct);//in orderul temporar adaugam produsul
                    listModelBasket.addElement(selectedProduct);

                    getFrame().add(JSBasket);
                    getFrame().repaint();

                }
            });
            frame.getContentPane().add(btnAdd);
    }




    public JFrame getFrame(){
        return frame;
    }

    public JTextField getTextTitle() {
                return textTitle;
        }

    public JTextField getTextCalories() {
                return textCalories;
        }

    public JTextField getTextPrice() {
                return textPrice;
        }

    public JList getListMenu() {
        return listMenu;
    }

    public JScrollPane getJSMenu() {
        return JSMenu;
    }
}
