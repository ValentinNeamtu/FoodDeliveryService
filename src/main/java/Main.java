import businessLogic.DeliveryService;
import presentation.LogInWin;

import java.io.IOException;

public class Main {

     public static void main(String[] args) throws IOException {



         DeliveryService test = new DeliveryService();

         LogInWin win = new LogInWin(test);
         win.getFrame().setVisible(true);

         test.ReadFile2();
         test.printMenu();


     }

}
