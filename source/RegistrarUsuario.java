import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class RegistrarUsuario extends JDialog {
    private JButton add_user_btn;
    public RegistrarUsuario() {
        //login window
        setLayout(new GridBagLayout());
        setSize(400, 200);
        setResizable(false);
        setLocationRelativeTo(null);//abre no meio
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //parte gráfica
        JLabel loginLabel = new JLabel("Criar Conta");//titulo
        add(loginLabel, new GridConf(0,0,2,1, 'h'));
        loginLabel.setFont(new Font(Font.SERIF,Font.BOLD, 30));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //Username Field
        JLabel name_field = new JLabel("Nome");//depois adicionar icones ilustrativos
        add(name_field, new GridConf(0,1,1,1,'h'));
        JTextField name_input = new JTextField(20);
        name_field.setHorizontalAlignment(SwingConstants.LEFT);
        add(name_input, new GridConf(1,1,1,1,'h'));

        //Password field
        JLabel pswd_field_1 = new JLabel("Password");
        pswd_field_1.setHorizontalAlignment(SwingConstants.LEFT);
        add(pswd_field_1, new GridConf(0,2,1,1,'h'));
        JPasswordField pswd_input_1 = new JPasswordField(20);
        add(pswd_input_1, new GridConf(1,2,1,1,'h'));

        //Confirmar Password field
        JLabel pswd_field_2 = new JLabel("Confirmar Password");
        pswd_field_2.setHorizontalAlignment(SwingConstants.LEFT);
        add(pswd_field_2, new GridConf(0,3,1,1,'h'));
        JPasswordField pswd_input_2 = new JPasswordField(20);
        add(pswd_input_2, new GridConf(1,3,1,1,'h', 'w', 1, 0));

        //Botão confirmar
        add_user_btn = new JButton("Criar Conta");
        add(add_user_btn, new GridConf(0,4,2,1,'h'));
        
        //Evento para criar usuário
        ActionListener eventAddUser = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                //ver se as passwords corespondem
                if (Arrays.equals(pswd_input_2.getPassword(), pswd_input_1.getPassword())) {
                    //ver se o nome está vazio
                    if (name_input.getText() == null || name_input.getText().isBlank()) {
                        add_user_btn.setText("Name cannot be empty");//Alerta!
                    } else {
                        //Cria o usuário
                        seeIfUserExists(name_input.getText(), new String(pswd_input_1.getPassword()));
                    }
                } else {
                    add_user_btn.setText("Pswd dont correspond");//alerta caso as psdw forem diferentes
                }
            }
        };

        add_user_btn.addActionListener(eventAddUser);
    }

    //Adicionar o usuário Ao ficheiro de dados
    private void saveNewUserToFile(String name, String pswd) {
        try (FileWriter new_user = new FileWriter("data/user.csv", true)) {
            //Salva o usuário
            new_user.write(name.replace(",", "")+","+pswd.replace(",", "")+"\n");

            //muda o texto do botão e depois fecha o aplicativo
            add_user_btn.setText("User Created!");
            add_user_btn.setEnabled(false);//desativa o botão

            Timer timer = new Timer(500, e -> {
                RegistrarUsuario.this.dispose();//fecha o dialog
            });
            timer.setRepeats(false);
            timer.start();

        } catch (Exception e) {
            System.out.println("Erro");
        }
    }

    private void seeIfUserExists(String name, String pswd) {

        java.io.File file = new java.io.File("data/user.csv");//Db File
        boolean user_exist = false;//ver se o usuário existe

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String data;
            //tenta encontrar o usuário no banco de dados para evitar replicas antes de salvar
            while ((data = reader.readLine()) != null) {
                String[] value = data.split(",");
                if (value.length > 0 && value[0].trim().equalsIgnoreCase(name)) {
                    user_exist = true;
                    break;
                }
            }

            if (user_exist) {
                add_user_btn.setText("User Already exists");

            } else {
                add_user_btn.setText("Creating User");
                saveNewUserToFile(name, pswd);
            }
        } catch (Exception e) {
            System.out.println("Erro");
        }
    }
}