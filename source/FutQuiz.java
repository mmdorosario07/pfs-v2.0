import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class FutQuiz extends JFrame {
    ImageIcon icon = new ImageIcon("data/assets/icon.jpg");
    List<Question> QuestionsList = new ArrayList<>();
    private int question_id;
    private int pa = 0;//pontos acumulados
    private int pv = 3;//pontos de vida
    private final JLabel question;
    private final JPanel answers;
    private final JLabel points;
    private final JPanel game;
    private final JPanel head;
    private final String[] opt = {"Reiniciar", "Sair"};
    private final int MAX_QUESTIONS = 25;

    
    public FutQuiz() {
        getQuestionsList();
        setTitle("Futquizz");
        setIconImage(icon.getImage());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        head = new JPanel();
        head.setLayout(new GridBagLayout());
        head.setBorder(BorderFactory.createLineBorder(Color.blue, 2));

        game = new JPanel();
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
        points = new JLabel("Pontuação: " + pa + "   Vida: "+pv);
        head.add(points, new GridConf(1,0,1,1,'b'));

        //RANKING
        JButton rank = new JButton("Ranking");
        head.add(rank, new GridConf(2,0,1,1,'b'));


        //Conteudos do "game"
        question = new JLabel("Pergunta");
        game.add(question, new GridConf(0,0,2,1,'c'));
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
                if (value.length >= 7) { 
                    QuestionsList.add(new Question(
                        value[1],//Pergunta
                        value[2], //Resposta 1
                        value[3], //Resposta 2
                        value[4], //Resposta 3
                        value[5], //Resposta 4
                        value[6] /*Resposta correta*/
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o csv");
        }

        Collections.shuffle(QuestionsList);//Randomiza as perguntas
    }

    private void updateQuestion() {
        //limpa o ecrã de botões
        answers.removeAll();
        answers.revalidate();
        answers.repaint();

        Question q = QuestionsList.get(question_id);//recebe uma pergunta

        question.setText("<html><body style='width: 400px; text-align: center;'>" + q.getQuestion() + "</body></html>");//muda o valor da label pergunta

        List<String> tmp = Arrays.asList(q.guetAnswers());//ignora...
        Collections.shuffle(tmp);

        char[] alineas = {'a', 'b', 'c', 'd'};
        for (int x = 0; x < 4; x++) {
            String ANSWER = tmp.get(x);
            JButton AnswerBtn = new JButton("<html><body style='width: 400px;'>"+alineas[x]+") "+ ANSWER + "</body></html>");
            answers.add(AnswerBtn, new GridConf(0, x, 1,1,'h','n', 1, 1));
            //deixar o texto 
            AnswerBtn.setHorizontalAlignment(SwingConstants.LEFT);
            AnswerBtn.addActionListener(
                e -> ConfirmAnswer(ANSWER, q.getCorrectAnswer())
            );
        }
    }

    private void ConfirmAnswer (String btn_txt, String correct_answer){
        question_id++;
        if (question_id > MAX_QUESTIONS) {
            int x = JOptionPane.showOptionDialog(
                this, 
                "Chegaste ao fim do jogo!","Congratulations",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null , opt, opt[0]);
            if (x == 0) {
                Collections.shuffle(QuestionsList);
                question_id = 0;
                pv = 3;
                pa = 0;
                updateQuestion();
            } else if (x == 1) {
                System.exit(0);
            }
            
        } else if (correct_answer.equals(btn_txt)) {
            pa++;
            updateQuestion();

            //o que fazer caso a resposta for a certa
        } else if (pv == 1) {
            //O que fazer quando o jogo chegar ao fim
            pv--;
            int x = JOptionPane.showOptionDialog(
                this, 
                "Vc perdeu!","Se Fudeu!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE
                ,null , opt, opt[0]);
            if (x == 0) {
                Collections.shuffle(QuestionsList);
                question_id = 0;
                pv = 3;
                pa = 0;
                updateQuestion();
            } else if (x == 1) {
                System.exit(0);
            }
        } else {
            pv--;
            JOptionPane.showMessageDialog(this, "Vc Errou! \n Perdeste Uma vida","Se Fudeu!", JOptionPane.ERROR_MESSAGE);
            updateQuestion();
        }
        points.setText("Pontuação: "+pa+"   Vida: "+pv);
    }
}