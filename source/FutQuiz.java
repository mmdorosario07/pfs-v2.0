import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class FutQuiz extends JFrame {
    ImageIcon icon = new ImageIcon("data/assets/icon.jpg");
    List<Question> QuestionsList = new ArrayList<>();
    private int question_id;
    private int p;
    private JLabel pergunta;
    private JPanel answers;
    private JLabel points;
     
    public FutQuiz() {
        getQuestionsList();
        setSize(600, 450);
        setTitle("Futquizz");
        setIconImage(icon.getImage());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        JPanel head = new JPanel();
        head.setLayout(new GridBagLayout());
        head.setBorder(BorderFactory.createLineBorder(Color.blue, 2));

        JPanel game = new JPanel();
        game.setLayout(new GridBagLayout());
        game.setBorder(BorderFactory.createLineBorder(Color.blue, 2));


        add(head, new GridConf(0,0,1,1,'h','n'));
        add(game, new GridConf(0,1,1,1,'b','c',1,1));

        //Conteudos do titulo

        //SAIR
        JButton exit = new JButton("Sair");
        exit.addActionListener(
            e -> {
                //Sair do jogo configurar melhor depois
                System.exit(0);
            }
        );

        head.add(exit, new GridConf(0,0,1,1,'b'));

        //PONTUAÇÃO
        points = new JLabel("Pontuação: ");
        head.add(points, new GridConf(1,0,1,1,'b'));

        //RANKING
        JButton rank = new JButton("Ranking");
        head.add(rank, new GridConf(2,0,1,1,'b'));




        //Conteudos do "game"
        JPanel questions = new JPanel();
        questions.setLayout(new GridBagLayout());
        game.add(questions, new GridConf(0,0,1,1,'h'));

        JLabel img = new JLabel("IMAGEM");
        questions.add(img, new GridConf(0,0,1,1,'h'));

        pergunta = new JLabel("Pergunta");
        questions.add(pergunta, new GridConf(1,0,1,1,'h'));


        //lugar das respostas
        answers = new JPanel();
        answers.setLayout(new GridBagLayout());
        game.add(answers, new GridConf(0,1,1,1,'h'));

        updateQuestion();
    }


    private void getQuestionsList() {
        java.io.File file = new java.io.File("data/quizz.csv");//Db File
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String data;
            while ((data = reader.readLine()) != null) {
                String[] value = data.split(",");
                // Forma correta e otimizada:
                QuestionsList.add(new Question(
                    value[1], 
                    value[2], 
                    value[3], 
                    value[4], 
                    value[5], 
                    value[6]
                ));
            }
        } catch (Exception e) {
            System.out.println("Erro");
        }

        Collections.shuffle(QuestionsList);//Randomiza as perguntas
    }

    private void updateQuestion() {
        answers.removeAll();//remover todos os botões
        Question q = QuestionsList.get(question_id);//recebe uma pergunta

        pergunta.setText(q.getQuestion());//muda o valor da label pergunta

        String[] tmp = q.guetAnswers();//ignora...
        String[][] lines = {{tmp[0], tmp[1]}, {tmp[2], tmp[3]}};//apenas distribui as respostas por 2 linhas

        for (int x = 1; x < 3; x++) {
            for (int y = 1; y < 3; y++) {
                JButton AnswerBtn = new JButton(lines[x-1][y-1]);
                answers.add(AnswerBtn, new GridConf(x-1, y-1, 1,1,'b','n', 1, 1));
                AnswerBtn.addActionListener(
                    e -> ConfirmAnswer(AnswerBtn.getText(), q.getCorrectAnswer())
                );
            }
        }

    }

    private void ConfirmAnswer (String btn_txt, String correct_answer){
        if (btn_txt.equals(correct_answer)) {
            if (question_id < QuestionsList.size()-1) {
                question_id ++;
                System.out.println("Correct!");
                updateQuestion();
            } else {
                System.out.println("Fim!");
            }
        } else {
            System.out.println("Error!");
        }
    }
}