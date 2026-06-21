import javax.swing.JFrame;
public class Main {
    public static void main(String[] args) {
        //sent to log
        System.out.println("game executed at $time, $date");
        JFrame login = new Login();
        login.setSize(800, 500);
        login.setVisible(true);
        
    }
}