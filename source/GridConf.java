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

    public GridConf(int col, int line, int colspan, int linespan, char fill, char anchor) {
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

        this.anchor = switch (anchor) {
            case 'n' -> GridBagConstraints.NORTH;
            case 'e' -> GridBagConstraints.EAST;
            case 'w' -> GridBagConstraints.WEST;
            case 's' -> GridBagConstraints.SOUTH;

            case '1' -> GridBagConstraints.NORTHEAST;
            case '2' -> GridBagConstraints.NORTHWEST;
            case '3' -> GridBagConstraints.SOUTHEAST;
            case '4' -> GridBagConstraints.SOUTHWEST;
            default  -> GridBagConstraints.CENTER;
        };

        this.insets = new Insets(5, 5, 5, 5);
        this.weightx = 1.0;
        this.weighty = 0.0;
    }

    public GridConf(int col, int line, int colspan, int linespan, char fill, char anchor, double weightx, double weighty) {
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

        this.anchor = switch (anchor) {
            case 'n' -> GridBagConstraints.NORTH;
            case 'e' -> GridBagConstraints.EAST;
            case 'w' -> GridBagConstraints.WEST;
            case 's' -> GridBagConstraints.SOUTH;

            case '1' -> GridBagConstraints.NORTHEAST;
            case '2' -> GridBagConstraints.NORTHWEST;
            case '3' -> GridBagConstraints.SOUTHEAST;
            case '4' -> GridBagConstraints.SOUTHWEST;
            default  -> GridBagConstraints.NORTHWEST;
        };

        this.insets = new Insets(5, 5, 5, 5);
        this.weightx = weightx;
        this.weighty = weighty;
    }
    
    
}
