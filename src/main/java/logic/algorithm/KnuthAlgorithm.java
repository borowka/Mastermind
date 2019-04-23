package logic.algorithm;

import logic.algorithm.util.ListUtils;

import java.util.*;

import static logic.algorithm.util.StringUtils.repeatChar;
import static logic.algorithm.util.StringUtils.textFromArray;

/**
 * Class implements Knuth Algorithm to solve mastermind game
 *
 * @author Weronika
 */
public class KnuthAlgorithm {

    /**
     * Method that solves a game
     *
     * @param hiddenSequence sequence to guess
     * @return Game object with list of results and sequences
     */
    public Game solveGame(String hiddenSequence) {

        KnuthAlgorithm knuthAlgorithm = new KnuthAlgorithm();
        List<String> sequenceList = new ArrayList<>();
        List<String> resultOfCompareList = new ArrayList<>();

        VariationWithoutRepetitionsGenerator gen = new VariationWithoutRepetitionsGenerator();
        List<int[]> variations = gen.variationsWithoutRepetitions(new int[]{1, 2, 3, 4, 5, 6}, 4);
        int randIndex = ListUtils.randomIndexFromList(variations);
        String firstChoice = textFromArray(variations.get(randIndex));
        sequenceList.add(firstChoice);

        String resultOfCompare = knuthAlgorithm.compareFunction(firstChoice, hiddenSequence);
        resultOfCompareList.add(resultOfCompare);

        List<int[]> s1 = knuthAlgorithm.reduceChoiceSet(variations, firstChoice, resultOfCompare);
        System.out.println("Reduced set: " + s1.size());

        while (!resultOfCompare.equals("BBBB")) {
            String nextChoice = knuthAlgorithm.applyMinMax(s1, knuthAlgorithm.allPossibleResults(4));
            sequenceList.add(nextChoice);
            resultOfCompare = knuthAlgorithm.compareFunction(nextChoice, hiddenSequence);
            resultOfCompareList.add(resultOfCompare);
            s1 = knuthAlgorithm.reduceChoiceSet(s1, nextChoice, resultOfCompare);
            System.out.println("Reduced set: " + s1.size());
        }

        Game game = new Game(sequenceList, resultOfCompareList);
        return game;
    }

    /**
     * Function to compare two sequences
     * B is returned when sequences are similar in the same position
     * W id returned when is similarity, but the position is wrong
     *
     * @param input Sequences which is compared to key
     * @param key   Sequence which is a key (hidden sequence)
     * @return Similarity between these two sequences.
     */
    public String compareFunction(String input, String key) {
        int white = 0;
        int black = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == key.charAt(i)) {
                black++;
            } else if (key.contains(String.valueOf(input.charAt(i)))) {
                white++;
            }
        }

        String result = "";
        for (int i = 0; i < black; i++) {
            result += "B";
        }

        for (int i = 0; i < white; i++) {
            result += "W";
        }

        return result;
    }

    /**
     * Method to reduced the set of numbers. It use compareFunction to find similarity between input sequences
     * and all the elements of set. Method remove all element which are not similar to input sequence.
     *
     * @param s      Input set
     * @param input  Input sequence
     * @param result Similarity between input sequence and hidden sequence
     * @return Reduced set of numbers
     */
    private List<int[]> reduceChoiceSet(List<int[]> s, String input, String result) {
        List<int[]> list = new ArrayList<>();
        for (int[] seq : s) {
            if (compareFunction(input, textFromArray(seq)).equals(result)) {
                list.add(seq);
            }
        }
        return list;
    }

    /**
     * Method to generate all possible returns of similarity
     *
     * @param n Size of max result
     * @return List of all possible result
     */
    private List<String> allPossibleResults(int n) {

        List<String> resultList = new ArrayList<>();

        for (int black = 0; black <= n; black++) {
            String result = "";
            result += repeatChar("B", black);

            for (int white = 0; (white + black) <= n; white++) {
                resultList.add(result + repeatChar("W", white));
            }
        }

        return resultList;
    }

    /**
     * Method which optimizes selection of next sequences by the algorithm. It look for minimum of reduced
     * elements from set for all elements of set in all cases of result. From list of this minimum select
     * the max value and corresponding sequence.
     *
     * @param s                  Set of sequences
     * @param allPossibleResults List of all possible results
     * @return Sequence which is used by the algorithm in next round
     */
    private String applyMinMax(List<int[]> s, List<String> allPossibleResults) {

        Map<String, Integer> bestChoices = new HashMap<>();

        for (int i = 0; i < s.size(); i++) {
            String choice = textFromArray(s.get(i));

            List<Integer> reducedSetSizeList = new ArrayList<>();
            for (String r : allPossibleResults) {
                Integer size = reduceChoiceSet(s, choice, r).size();
                reducedSetSizeList.add(size);
            }

            Collections.sort(reducedSetSizeList);
            bestChoices.put(choice, reducedSetSizeList.get(reducedSetSizeList.size() - 1));
        }

        String bestChoice = "";
        int bestChoiceReduction = 0;

        for (String key : bestChoices.keySet()) {
            if (bestChoices.get(key) > bestChoiceReduction) {
                bestChoiceReduction = bestChoices.get(key);
                bestChoice = key;
            }
        }

        return bestChoice;
    }
}
