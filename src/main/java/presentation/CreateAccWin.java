package presentation;
import businessLogic.DeliveryService;
import model.User;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CreateAccWin {

    private JFrame frame;
    private JTextField txtName;
    private JTextField txtPassword;

    public CreateAccWin(DeliveryService dS){

            frame = new JFrame();
            frame.setBounds(100, 100, 500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(null);

            JLabel title = new JLabel("Create Account");
            title.setFont(new Font("Tahoma", Font.PLAIN, 21));
            title.setBounds(159, 11, 200, 50);
            frame.getContentPane().add(title);

            JLabel lblName = new JLabel("Nume");
            lblName.setBounds(76, 93, 131, 31);
            frame.getContentPane().add(lblName);

            txtName = new JTextField();
            txtName.setBounds(217, 93, 171, 31);
            frame.getContentPane().add(txtName);
            txtName.setColumns(10);

            JLabel lblPassword = new JLabel("Password");
            lblPassword.setBounds(76, 156, 131, 31);
            frame.getContentPane().add(lblPassword);

            txtPassword = new JTextField();
            txtPassword.setColumns(10);
            txtPassword.setBounds(217, 161, 171, 31);
            frame.getContentPane().add(txtPassword);

            JButton btnCreate = new JButton("Create Account");
            btnCreate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    String name = null;
                    String password = null;

                    if(getTxtName().getText().equals("") || getTxtPassword().getText().equals("")){
                        JOptionPane.showMessageDialog(frame, "Insert all the REQUIRED data");
                    }else{
                        name = getTxtName().getText();
                        password = getTxtPassword().getText();

                        int ID = dS.getUsers().size();

                        User newUser = new User(ID + 1,"client", name, password);

                        //System.out.println(newUser.toString());

                        dS.inserUser(newUser);

                        dS.printUsers();

                        System.out.println(dS.existentUser(newUser));

                        LogInWin win = new LogInWin(dS);
                        win.getFrame().setVisible(true);
                        frame.dispose();
                    }
                }
            });
            btnCreate.setBounds(148, 274, 171, 50);
            frame.getContentPane().add(btnCreate);

            JButton btnBack = new JButton("Back");
            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    LogInWin win = new LogInWin(dS);
                    win.getFrame().setVisible(true);
                }
            });
            btnBack.setBounds(148, 325, 171, 50);
            frame.getContentPane().add(btnBack);

    }

    public JTextField getTxtName() {
        return txtName;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public JFrame getFrame(){
        return this.frame;
    }

}
