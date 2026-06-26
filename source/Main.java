import javax.swing.JFrame;
public class Main {
    public static void main(String[] args) {
        //sent to log
        System.out.println("game executed at $time, $date");
        //JFrame login = new Login();
        JFrame login = new FutQuiz();
        //login.setSize(600, 500);
        login.setVisible(true);
        
    }
}