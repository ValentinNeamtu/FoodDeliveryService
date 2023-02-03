package presentation;

import businessLogic.DeliveryService;
import model.MenuItem;
import model.Order;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeWin implements Observer {

        private JFrame frame;
        private JTextField textClientID;
        private JTextField textDate;
        private JTextField textPrice;
        private JTextField textID;
        private JList listContent;
        private DefaultListModel contentListModel;
        private ArrayList<MenuItem> contents;
        private JScrollPane JScontents;


        EmployeeWin(DeliveryService dS) {
            frame = new JFrame();
            frame.setBounds(100, 100, 600, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(null);

            JLabel title = new JLabel("EMPLOYEE - WELCOME");
            title.setFont(new Font("Tahoma", Font.PLAIN, 21));
            title.setBounds(120, 11, 285, 50);
            frame.getContentPane().add(title);

            JLabel lblDate = new JLabel("Date");
            lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblDate.setBounds(25, 236, 86, 33);
            frame.getContentPane().add(lblDate);

            JLabel lblID = new JLabel("ID");
            lblID.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblID.setBounds(25, 154, 86, 33);
            frame.getContentPane().add(lblID);

            textClientID = new JTextField();
            textClientID.setColumns(10);
            textClientID.setBounds(110, 198, 149, 34);
            frame.getContentPane().add(textClientID);

            textDate = new JTextField();
            textDate.setColumns(10);
            textDate.setBounds(110, 237, 149, 34);
            frame.getContentPane().add(textDate);

            JButton btnBack = new JButton("Back");
            btnBack.setBounds(87, 370, 110, 33);
            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //frame.dispose();
                    LogInWin win = new LogInWin(dS);
                    win.getFrame().setVisible(true);
                }
            });
            frame.getContentPane().add(btnBack);

            JLabel lblPrice = new JLabel("Price");
            lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblPrice.setBounds(25, 280, 86, 33);
            frame.getContentPane().add(lblPrice);

            textPrice = new JTextField();
            textPrice.setColumns(10);
            textPrice.setBounds(110, 281, 149, 34);
            frame.getContentPane().add(textPrice);


            contentListModel = new DefaultListModel();
            listContent = new JList(contentListModel);

            listContent.setBounds(294, 104, 285, 346);
            JScontents = new JScrollPane(listContent);
            JScontents.setBounds(294, 104, 285, 346);
            JScontents.createVerticalScrollBar();
            frame.getContentPane().add(JScontents);

            textID = new JTextField();
            textID.setColumns(10);
            textID.setBounds(110, 153, 149, 34);
            frame.getContentPane().add(textID);

            JLabel lblClientID = new JLabel("ClientID");
            lblClientID.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblClientID.setBounds(25, 192, 86, 33);
            frame.getContentPane().add(lblClientID);

            JLabel lblContents = new JLabel("Order Contents");
            lblContents.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblContents.setBounds(358, 72, 170, 33);
            frame.getContentPane().add(lblContents);
        }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField getTextClientID() {
        return textClientID;
    }

    public void setTextClientID(JTextField textClientID) {
        this.textClientID = textClientID;
    }

    public JTextField getTextDate() {
        return textDate;
    }

    public void setTextDate(JTextField textDate) {
        this.textDate = textDate;
    }

    public JTextField getTextPrice() {
        return textPrice;
    }

    public void setTextPrice(JTextField textPrice) {
        this.textPrice = textPrice;
    }

    public JTextField getTextID() {
        return textID;
    }

    public void setTextID(JTextField textID) {
        this.textID = textID;
    }

    public JList getListContent() {
        return listContent;
    }

    public void setListContent(JList listContent, Order O) {
        this.listContent = listContent;
    }

    public DefaultListModel getContentListModel() {
        return contentListModel;
    }

    public void setContentListModel(DefaultListModel contentListModel, Order order) {
            contentListModel.removeAllElements();
            for(MenuItem mi : order.getOrderedProducts()){
                contentListModel.addElement(mi);
            }
        System.out.println("AJAJAJJAJAJAJAJAJJA");
        this.getFrame().revalidate();
        this.getFrame().repaint();
    }

    public ArrayList<MenuItem> getContents() {
        return contents;
    }

    public void setContents(ArrayList<MenuItem> contents) {
        this.contents = contents;
    }

    public JScrollPane getJScontents() {
        return JScontents;
    }

    public void setJScontents(JScrollPane JScontents) {
        this.JScontents = JScontents;
    }

    @Override
         public void update(Observable o, Object arg) {
            Order order = (Order)arg;
            System.out.println(order.getOrderID() + " || " + order.getPrice());
            getTextClientID().setText(String.valueOf(order.getClientID()));
            getTextID().setText(String.valueOf(order.getOrderID()));
            getTextDate().setText(order.getOrderDate());
            getTextPrice().setText(String.valueOf(order.getPrice()));

            setContentListModel(getContentListModel(), order);

         }

}
