package logic.algorithm.util;

import java.util.List;
import java.util.Random;

/**
 * Class contains method which is useful to work with list
 *
 * @author Weronika
 */

public class ListUtils {

    /**
     * Creates a new random index of list
     *
     * @param list for which is looking random index
     * @return random index
     */
    public static int randomIndexFromList(List<int[]> list) {
        Random random = new Random();
        int randIndex = random.nextInt(list.size());
        return randIndex;
    }
}
