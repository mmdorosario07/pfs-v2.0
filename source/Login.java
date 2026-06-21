import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.io.*;

public class Login extends JFrame{

    ImageIcon icon = new ImageIcon("data/assets/icon.jpg");
    ImageIcon img = new ImageIcon("data/assets/campo.jpg");
    private String[] user = new String[2];
    //sempre qu mecher nos valores de tamanho da tela mude esses valores tambem 
    //para escalar a imagem e ocupar metade da tela
    Image imgred = img.getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH);
    ImageIcon img01 = new ImageIcon(imgred);

    Login() {
        //window configs
        setTitle("FutQuizz");
        setIconImage(icon.getImage());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(0, 2));
        
        //painel paara apresentar a imagem do campo 
        //pf n mecher deu dor de cabeça 
        JPanel imgJPanel = new JPanel();
        imgJPanel.setBackground(Color.black);
        JLabel ImgLabel = new JLabel(img01);
        imgJPanel.add(ImgLabel);

        //login window
        JPanel loginPanel = new JPanel(new GridBagLayout());
        JLabel loginLabel = new JLabel("Login");//titulo
        loginPanel.add(loginLabel, new GridConf(0,0,2,1, 'h'));

        //entrada do nome
        JLabel name_field = new JLabel("Nome");//depois adicionar icones ilustrativos
        loginPanel.add(name_field, new GridConf(0,1,1,1,'n'));
        JTextField name_input = new JTextField(20);
        loginPanel.add(name_input, new GridConf(1,1,1,1,'n'));

        //entrada do pswd
        JLabel pswd_field = new JLabel("Password");
        loginPanel.add(pswd_field, new GridConf(0,2,1,1,'n'));
        JPasswordField pswd_input = new JPasswordField(20);
        loginPanel.add(pswd_input, new GridConf(1,2,1,1,'n'));

        JButton goto_game = new JButton("Go!");
        loginPanel.add(goto_game, new GridConf(0,3,2,1,'h'));

        //Adicionar usuario
        JLabel add_acct = new JLabel("Sem Conta?");
        loginPanel.add(add_acct, new GridConf(0,4,1,1,'n'));
        JButton add_user = new JButton("Criar Conta");
        loginPanel.add(add_user, new GridConf(1,4,1,1,'h'));
        add_user.addActionListener(create_user);
        //Ver ranking
        JButton ranking = new JButton("Ver Ranking Global");
        loginPanel.add(ranking, new GridConf(0,5,2,1,'h'));

        //por fim adicionar os elementos
        add(imgJPanel);
        add(loginPanel);
        ActionListener login_to_game = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                loginToGame(name_input.getText(), new String(pswd_input.getPassword()));
            }
        };
        goto_game.addActionListener(login_to_game);

    }

    ActionListener create_user = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            JDialog createUser = new RegistrarUsuario();
            createUser.setVisible(true);
        }
    };

    private void loginToGame(String name, String pswd) {
        if (getUserdata(name)) {
            if (pswd.equals(user[1])) {
                JFrame q = new FutQuiz();
                q.setVisible(true);
                setVisible(false);
            }
        } else {
            System.out.println("erro de login");
            //goto_game.setText("credentials error");
        }
    }

    private boolean getUserdata(String name) {
        java.io.File file = new java.io.File("data/user.csv");//Db File
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String data;
            while ((data = reader.readLine()) != null) {
                String[] value = data.split(",");
                if (value.length > 0 && value[0].trim().equalsIgnoreCase(name)) {
                    user[0] = value[0];
                    user[1] = value[1];
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro");
            e.printStackTrace();
        }
        return false;
    }
}
