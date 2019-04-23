package logic.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to create input sequence collection
 *
 * @author Weronika
 */

public class VariationWithoutRepetitionsGenerator {

    private List<int[]> combinations;
    private List<int[]> variationsForCombination;

    /**
     * Method to create set of numbers variations without repetition
     *
     * @param n Set of element
     * @param k Size of number sequence
     * @return Input sequence set
     */
    public List<int[]> variationsWithoutRepetitions(int[] n, int k) {
        combinations = new ArrayList<>();
        variationsForCombination = new ArrayList<>();
        countCombinations(n, k);
        List<int[]> variations = new ArrayList<>();
        combinations.forEach(combination -> {
            variationsForCombination = new ArrayList<>();
            countVariations(combination, combination.length);
            variationsForCombination.forEach(perm -> variations.add(perm));
        });

        return variations;
    }

    /**
     * Method calling method countCombinationsRecursive
     *
     * @param n Set of element
     * @param k Size of number sequence
     */
    private void countCombinations(int[] n, int k) {
        int data[] = new int[k];
        countCombinationsRecursive(n, data, 0, n.length - 1, 0, k);
    }

    /**
     * Recursive method to create set of all combinations from input array of numbers
     *
     * @param n     Input array
     * @param data  Temporary array to store current combination
     * @param start Staring index in n[]
     * @param end   Ending index in n[]
     * @param index Current index in data[]
     * @param k     Size of a combination to be created
     */
    private void countCombinationsRecursive(int n[], int data[], int start,
                                            int end, int index, int k) {
        if (index == k) {
            combinations.add(Arrays.copyOf(data, k));
            return;
        }

        for (int i = start; (i <= end) && (end - i + 1 >= k - index); i++) {
            data[index] = n[i];
            countCombinationsRecursive(n, data, i + 1, end, index + 1, k);
        }
    }

    /**
     * Generating permutation using Heap Algorithm
     *
     * @param list Input list
     * @param n    Size of permutation to be created
     */
    private void countVariations(int[] list, int n) {
        if (n == 1) {
            variationsForCombination.add(Arrays.copyOf(list, list.length));
        } else {
            for (int i = 0; i < n; i++) {
                countVariations(list, n - 1);

                int j = (n % 2 == 0) ? i : 0;

                int t = list[n - 1];
                list[n - 1] = list[j];
                list[j] = t;
            }
        }
    }

}
