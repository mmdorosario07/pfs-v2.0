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

        JPanel head = new JPanel();
        head.setLayout(new GridBagLayout());
        JPanel game = new JPanel();
        game.setLayout(new GridBagLayout());

        add(head, new GridConf(1,1,1,1,'h'));
        add(game, new GridConf(1,2,1,1,'h'));

        head.add(new JLabel("cabeça"));

        //Conteudos do "game"
        JPanel questions = new JPanel();
        questions.setLayout(new GridBagLayout());
        game.add(questions, new GridConf(1,1,1,1,'h'));

        JLabel img = new JLabel("imag");
        questions.add(img, new GridConf(1,1,1,1,'h'));

        JLabel pergunta = new JLabel("Pergunta");
        questions.add(pergunta, new GridConf(2,1,1,1,'h'));


        //lugar das respostas
        JPanel answers = new JPanel();
        answers.setLayout(new GridBagLayout());
        game.add(answers, new GridConf(1,2,1,1,'h'));

        //botoes teste
        String resposta[][] = {{"Mario", "taissa"}, {"Adriano", "OsZotro"}};
        for (int x = 1; x < 3; x++) {
            for (int y = 1; y < 3; y++) {
                answers.add(new JButton(resposta[x-1][y-1]), new GridConf(x, y, 1,1,'b'));
            }
        }



    }
}