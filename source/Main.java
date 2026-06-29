import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.UIManager;
public class Main {
    public User player;
    public static void main(String[] args) {
        Font base_font_1 = new Font(Font.SANS_SERIF,Font.PLAIN, 15);
        Font base_font_2 = new Font(Font.SERIF,Font.PLAIN, 15);
        Font base_font_3 = new Font(Font.MONOSPACED,Font.PLAIN, 15);

        UIManager.put("Label.font", base_font_2);
        UIManager.put("Button.font", base_font_1);
        UIManager.put("TextField.font", base_font_3);
        UIManager.put("ScrollPane.font", base_font_2);
        UIManager.put("Panel.font", base_font_1);
        //sent to log
        JFrame login = new Login();
        
        login.setSize(800, 500);
        login.setVisible(true);
    }
}