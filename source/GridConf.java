import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GridConf extends  GridBagConstraints{
    public GridConf(int col, int line, int colspan, int linespan, char fill) {
        this.gridx = col;
        this.gridy = line;
        this.gridwidth = colspan;
        this.gridheight = linespan;

        this.fill = switch (fill) {
            case 'b' -> GridBagConstraints.BOTH;
            case 'h' -> GridBagConstraints.HORIZONTAL;
            case 'v' -> GridBagConstraints.VERTICAL;
            case 'n' -> GridBagConstraints.NONE;
            default  -> GridBagConstraints.NONE;
        };
    
        this.insets = new Insets(5, 5, 5, 5);
        this.weightx = 1.0;      
        this.weighty = 0.0;
    }
}
