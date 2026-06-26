import java.awt.*;
import javax.swing.*;

public class FutQuiz extends JFrame {
    ImageIcon icon = new ImageIcon("data/assets/icon.jpg");
    public FutQuiz() {
        setSize(600, 450);
        setTitle("Futquizz");
        setIconImage(icon.getImage());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        JPanel head = new JPanel();
        head.setLayout(new GridBagLayout());
        head.setBorder(BorderFactory.createLineBorder(Color.blue, 2));

        JPanel game = new JPanel();
        game.setLayout(new GridBagLayout());
        game.setBorder(BorderFactory.createLineBorder(Color.blue, 2));


        add(head, new GridConf(0,0,1,1,'h','n'));
        add(game, new GridConf(0,1,1,1,'b','c',1,1));

        //Conteudos do titulo
        JButton exit = new JButton("Sair");
        exit.addActionListener(
            e -> {
                //Sair do jogo configurar melhor depois
                System.exit(0);
            }
        );
        head.add(exit, new GridConf(0,0,1,1,'n'));
        JLabel points = new JLabel("Pontuação: ");
        head.add(points, new GridConf(1,0,1,1,'n'));
        JButton rank = new JButton("Ranking");
        head.add(rank, new GridConf(2,0,1,1,'n'));




        //Conteudos do "game"
        JPanel questions = new JPanel();
        questions.setLayout(new GridBagLayout());
        game.add(questions, new GridConf(0,0,1,1,'h'));

        JLabel img = new JLabel("IMAGEM");
        questions.add(img, new GridConf(0,0,1,1,'h'));

        JLabel pergunta = new JLabel("Pergunta");
        questions.add(pergunta, new GridConf(1,0,1,1,'h'));


        //lugar das respostas
        JPanel answers = new JPanel();
        answers.setLayout(new GridBagLayout());
        game.add(answers, new GridConf(0,1,1,1,'h'));

        //botoes teste
        String resposta[][] = {{"Mario", "taissa"}, {"Adriano", "OsZotro"}};
        for (int x = 1; x < 3; x++) {
            for (int y = 1; y < 3; y++) {
                answers.add(new JButton(resposta[x-1][y-1]), new GridConf(x-1, y-1, 1,1,'b'));
            }
        }



    }
}