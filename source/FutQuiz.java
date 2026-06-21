import javax.swing.*;
import java.awt.*;

public class FutQuiz extends JFrame {
    ImageIcon icon = new ImageIcon("data/assets/icon.jpg");
    public FutQuiz() {
        setSize(800, 500);
        setTitle("Futquizz");
        setIconImage(icon.getImage());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

    }
}