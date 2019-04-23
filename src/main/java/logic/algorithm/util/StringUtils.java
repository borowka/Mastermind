package logic.algorithm.util;

/**
 * Class contains method which is useful to work with String
 *
 * @author Weronika
 */

public class StringUtils {

    /**
     * Method to convert array to String
     *
     * @param arr input array
     * @return converted String
     */
    public static String textFromArray(int[] arr) {
        String result = "";

        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }

        return result;
    }

    /**
     * Method to create string from input character repeat n times.
     *
     * @param character
     * @param n         number of repetition
     * @return String of character repeated n times
     */
    public static String repeatChar(String character, int n) {
        String result = "";

        for (int i = 0; i < n; i++) {
            result += character;
        }

        return result;
    }
}
