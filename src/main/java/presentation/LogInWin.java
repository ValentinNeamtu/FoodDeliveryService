package presentation;
import businessLogic.DeliveryService;
import model.User;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInWin{

    private JFrame frame;
    private JTextField txtName;
    private JTextField txtPassword;

    public LogInWin(DeliveryService dS){

            frame = new JFrame();
            frame.setBounds(100, 100, 500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setLayout(null);

            JLabel title = new JLabel("Log In Window");
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

            JButton btnConnect = new JButton("Connect");
            btnConnect.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = null;
                    String password = null;

                    if(getTxtName().getText().equals("") || getTxtPassword().getText().equals("")){
                        JOptionPane.showMessageDialog(frame, "Insert all the REQUIRED data");
                    }else{
                        name = getTxtName().getText();
                        password = getTxtPassword().getText();

                        User newUser = new User(name, password);

                        dS.printUsers();

                        System.out.println(newUser.getName() + " || " + newUser.getPassword());

                        System.out.println(dS.existentUser(newUser));

                        if(dS.existentUser(newUser) == 1){//verificam daca userul respectiv exista.<?///

                            if(dS.returnExistentUser(newUser).getType().equals("client")){//daca userul este client
                                dS.setLoggedUser(dS.returnExistentUser(newUser));//cautam clientul nou si il legam la dS
                                ClientWin win = new ClientWin(dS);//cream noua pagina, si anume pagina de client
                                win.getFrame().setVisible(true);
                                frame.dispose();
                            }else if(dS.returnExistentUser(newUser).getType().equals("admin")){
                                    dS.setLoggedUser(dS.returnExistentUser(newUser));
                                    AdminWin win = new AdminWin(dS);
                                    win.getFrame().setVisible(true);
                                    frame.dispose();
                            }else{
                                dS.setLoggedUser(dS.returnExistentUser(newUser));
                                EmployeeWin emWin = new EmployeeWin(dS);
                                emWin.getFrame().setVisible(true);
                                frame.dispose();
                            }

                        }else{
                            JOptionPane.showMessageDialog(frame, "Username / Password WRONG");
                        }


                    }
                }
            });
            btnConnect.setBounds(159, 269, 171, 50);
            frame.getContentPane().add(btnConnect);

            JButton btnCreate = new JButton("Create Account");
            btnCreate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CreateAccWin win = new CreateAccWin(dS);
                    win.getFrame().setVisible(true);
                    frame.dispose();
                }
            });
            btnCreate.setBounds(159, 331, 171, 50);
            frame.getContentPane().add(btnCreate);


    }

    public JTextField getTxtName() {
        return txtName;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public JFrame getFrame(){
        return frame;
    }

}
