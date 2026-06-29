
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class Ranking extends JFrame{
    private List<PlayerRanking>RANKING = new ArrayList<>();
    public Ranking() {
        getRanking();
        setSize(400, 500);
        setTitle("Futquizz");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
    }

    public void updateRanking() {}
    public void addRanking(String player, int points) {}
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