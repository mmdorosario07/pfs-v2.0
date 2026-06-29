
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class Ranking extends JFrame{
    private final List<PlayerRanking> RANKING = new ArrayList<>();
    private final JPanel lista;

    public Ranking() {
        getRanking();
        setTitle("Futquizz");
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        
        // 1. Primeiro definimos o tamanho da janela
        setSize(400, 300);

        lista = new JPanel();
        lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));

        // Título estilizado e centralizado
        JLabel titulo = new JLabel("Ranking");
        titulo.setFont(new Font("Serif", Font.BOLD, 30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, new GridConf(0, 0, 1, 1, 'h', 'n', 1, 0));

        // Scroll da lista
        JScrollPane scroll = new JScrollPane(lista);
        add(scroll, new GridConf(0, 1, 1, 1, 'b', 'c', 1, 1));
        
        // 2. AGORA SIM centralizamos a janela na tela do monitor
        setLocationRelativeTo(null); 
        
        updateRanking();
    }

    private void updateRanking() {
        lista.removeAll();
        
        for (PlayerRanking tmp : RANKING) {
            JLabel _tmp = new JLabel(("Player: "+tmp.getName()+" >> "+tmp.getPoints()));
            _tmp.setFont(new Font("monospaced", Font.PLAIN, 16));
            lista.add(_tmp);
        }

        lista.revalidate();
        lista.repaint();
    }
    
    public void addRanking(String player, int points) {
        java.io.File file = new java.io.File("data/ranking.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Escreve o formato "Nome,Pontos"
            writer.write(player + "," + points);
            // Pula para a próxima linha para o próximo jogador não grudar neste
            writer.newLine();
        } catch (Exception e) {
            System.out.println("Erro ao salvar jogador no arquivo de ranking.");
        }
    }

    private void getRanking() {
        RANKING.clear();
        java.io.File file = new java.io.File("data/ranking.csv");//Db File
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String data;
            while ((data = reader.readLine()) != null) {
                String[] value = data.split(",");
                RANKING.add(new PlayerRanking(value[0], Integer.parseInt(value[1])));
            }
        } catch (Exception e) {
            System.out.println("Erro");
        }
        
        //compara os pontos optidos e ordena-os do maior para o menpr
        Collections.sort(RANKING, (user_1, user_2) -> Integer.compare(user_2.getPoints(), user_1.getPoints()));
    }

}

class PlayerRanking{
    private final String name;
    private final int points;

    public PlayerRanking(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }
    
}