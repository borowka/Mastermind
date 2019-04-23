package logic;

import model.CircleColor;

import java.util.*;

/**
 * Class to implements Sequence Generator. Create random sequence od CircleColors.
 *
 * @author Weronika
 */
public class RandomSequenceGenerator implements SequenceGenerator {

    /**
     * Method to create random sequence of CircleColor
     *
     * @param size Size of returned sequence
     * @return random sequence of CircleColor
     */
    @Override
    public List<CircleColor> getSequence(int size) {
        Random random = new Random();
        List<CircleColor> circleColorSequence = new ArrayList<>();
        CircleColor[] values = CircleColor.values();
        int counter = 0;

        do {
            int randomIndex = random.nextInt(size);
            if (!circleColorSequence.contains(values[randomIndex])) {
                circleColorSequence.add(values[randomIndex]);
                counter++;
            }
        } while (!(counter == 4));

        return circleColorSequence;
    }

}
