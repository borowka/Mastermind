package logic.algorithm;

import java.util.List;

/**
 * Class which is container of data returned by main method in Knuth Algorithm
 *
 * @author Weronika
 */

public class Game {

    private List<String> sequenceList;
    private List<String> resultList;

    /**
     * Class constructor
     *
     * @param sequenceList All sequences selected by the algorithm
     * @param resultList   All results returned by the comparison method
     */
    public Game(List<String> sequenceList, List<String> resultList) {
        this.sequenceList = sequenceList;
        this.resultList = resultList;
    }

    /**
     * Getter for sequenceList
     *
     * @return sequenceList
     */
    public List<String> getSequenceList() {
        return sequenceList;
    }

    /**
     * Getter for resultList
     *
     * @return resultList
     */
    public List<String> getResultList() {
        return resultList;
    }
}
