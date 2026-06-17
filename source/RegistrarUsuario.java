import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistrarUsuario extends JDialog {

    public RegistrarUsuario() {
        //login window
        setLayout(new GridBagLayout());
        setSize(400, 200);
        setResizable(false);
        setLocationRelativeTo(null);//abre no meio

        //parte gráfica
        JLabel loginLabel = new JLabel("Criar Conta");//titulo
        add(loginLabel, new GridConf(0,0,2,1, 'h'));

        JLabel name_field = new JLabel("Nome");//depois adicionar icones ilustrativos
        add(name_field, new GridConf(0,1,1,1,'n'));

        JTextField name_input = new JTextField(20);
        add(name_input, new GridConf(1,1,1,1,'n'));

        JLabel pswd_field_1 = new JLabel("Password");
        add(pswd_field_1, new GridConf(0,2,1,1,'n'));

        JPasswordField pswd_input_1 = new JPasswordField(20);
        add(pswd_input_1, new GridConf(1,2,1,1,'n'));

        JLabel pswd_field_2 = new JLabel("Confirmar Password");
        add(pswd_field_2, new GridConf(0,3,1,1,'n'));

        JPasswordField pswd_input_2 = new JPasswordField(20);
        add(pswd_input_2, new GridConf(1,3,1,1,'n'));

        JButton goto_game = new JButton("Go!");
        add(goto_game, new GridConf(0,4,2,1,'h'));
        
    }
    
}