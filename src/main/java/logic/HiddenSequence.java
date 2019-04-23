package logic;

import model.CircleColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to create hidden sequence for mastermind
 *
 * @author Weronika
 */

public class HiddenSequence {

    private List<CircleColor> hiddenSequence;
    private SequenceGenerator colorGenerator;

    /**
     * Class constructor
     *
     * @param size              size of hidden sequences to be created
     * @param sequenceGenerator service which deliver the way of generating sequences
     */
    public HiddenSequence(int size, SequenceGenerator sequenceGenerator) {
        this.hiddenSequence = new ArrayList<>();
        this.colorGenerator = sequenceGenerator;
        hiddenSequence = colorGenerator.getSequence(size);
    }

    /**
     * Method that returns color from a sequence with a given index
     *
     * @param index indicate which element will be returned
     * @return CircleColor
     */
    public CircleColor getColor(int index) {
        CircleColor circleColor = hiddenSequence.get(index);
        return circleColor;
    }

    /**
     * Method converting sequence of CircleColor to sequence of numbers.
     *
     * @return String number sequence
     */
    public String getSequence() {
        String sequence = "";
        for (int i = 0; i < hiddenSequence.size(); i++) {
            sequence += "" + hiddenSequence.get(i).getNumb();
        }
        return sequence;
    }


}
