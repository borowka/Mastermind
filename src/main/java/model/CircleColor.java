package model;

import javafx.scene.paint.Color;

/**
 * Class represent all colors used in game
 *
 * @author Weronika
 */
public enum CircleColor {

    RED(Color.RED, 1),
    GREEN(Color.GREEN, 2),
    BLUE(Color.BLUE, 3),
    YELLOW(Color.YELLOW, 4),
    ORANGE(Color.ORANGE, 5),
    VIOLET(Color.VIOLET, 6),
    BLACK(Color.BLACK),
    WHITE(Color.WHITE),
    INITIAL(Color.DODGERBLUE);

    private Color color;
    private Integer numb;

    /**
     * Class constructor without parameters
     *
     * @param color
     */
    CircleColor(Color color) {
        this.color = color;
    }

    /**
     * Class constructor
     *
     * @param color Color of circle
     * @param numb  Number indicates this color
     */
    CircleColor(Color color, Integer numb) {
        this.color = color;
        this.numb = numb;
    }

    /**
     * Numb getter
     *
     * @return
     */
    public Integer getNumb() {
        return numb;
    }

    /**
     * Color getter
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * Method to convert String sequence of numbers for CircleColor sequence
     *
     * @param seq Input String sequence
     * @return CirceColor sequence
     */
    public static CircleColor[] translate(String seq) {
        CircleColor[] colors = new CircleColor[seq.length()];

        for (int i = 0; i < seq.length(); i++) {
            CircleColor[] values = CircleColor.values();
            for (CircleColor circleColor : values) {
                if (circleColor.getNumb() == Integer.valueOf("" + seq.charAt(i))) {
                    colors[i] = (circleColor);
                }
            }
        }

        return colors;
    }
}
