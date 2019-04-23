package gui;

import logic.algorithm.Game;
import logic.algorithm.KnuthAlgorithm;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import logic.HiddenSequence;
import logic.RandomSequenceGenerator;
import logic.SequenceGenerator;
import model.CircleColor;

import java.net.URL;
import java.util.*;

/**
 * Class to integrate logic of app with gui
 */

public class Controller implements Initializable {

    private static final int GRID_ROWS = 6;
    private static final int GRID_COLS = 4;
    private int numberOfColors = 6;
    private HiddenSequence hiddenSequence;
    private SequenceGenerator sequenceGenerator;
    private KnuthAlgorithm knuthAlgorithm;

    private Circle[][] gameCircles = new Circle[GRID_ROWS][GRID_COLS];
    private Circle[][] resultCircles = new Circle[GRID_ROWS][GRID_COLS];
    private Circle[][] hiddenCircles = new Circle[1][GRID_COLS];

    @FXML
    private GridPane gameGrid;

    @FXML
    private GridPane resultsGrid;

    @FXML
    private GridPane hiddenSequenceGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCircles(hiddenSequenceGrid, hiddenCircles);
        initCircles(gameGrid, gameCircles);
        initCircles(resultsGrid, resultCircles);
        sequenceGenerator = new RandomSequenceGenerator();
        knuthAlgorithm = new KnuthAlgorithm();
    }

    /**
     * Method create matrix of Circle from GridPane
     * @param gridPane
     * @param circles
     */
    private void initCircles(GridPane gridPane, Circle[][] circles) {
        ObservableList<Node> gridChildren = gridPane.getChildren();
        for (Node node : gridChildren) {
            Circle circle = (Circle) node;
            Integer rowIndex = GridPane.getRowIndex(circle);
            Integer col = GridPane.getColumnIndex(circle);
            // System.out.println(rowIndex + " " + col);
            circles[rowIndex][col] = circle;
        }
    }

    /**
     * Method which is executed when user push the button. Start the game. Initialize objects creating
     * hiddenSequence, input set and solve the game.
     * @param event
     */
    @FXML
    void onClickStart(ActionEvent event) {
        clearGame();
        hiddenSequence = new HiddenSequence(numberOfColors, sequenceGenerator);

        setCircleColor(hiddenCircles, 0, 0, hiddenSequence.getColor(0));
        setCircleColor(hiddenCircles, 0, 1, hiddenSequence.getColor(1));
        setCircleColor(hiddenCircles, 0, 2, hiddenSequence.getColor(2));
        setCircleColor(hiddenCircles, 0, 3, hiddenSequence.getColor(3));

        String sequence = hiddenSequence.getSequence();
        Game game = knuthAlgorithm.solveGame(sequence);
        List<String> sequenceList = game.getSequenceList();
        List<String> resultList = game.getResultList();

        List<CircleColor[]> nextMoveColors = new ArrayList<>();
        for (String move : sequenceList) {
            nextMoveColors.add(CircleColor.translate(move));
        }

        new Thread(() -> {
            for (int roundCounter = 0; roundCounter < nextMoveColors.size(); roundCounter++) {
                nextRound(roundCounter, nextMoveColors);
                properColors(roundCounter, resultList);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(roundCounter);
            }
        }).start();
    }

    /**
     * Method to fill results circles
     * @param roundCounter number of round
     * @param resultList list of all results for each round
     */
    private void properColors(int roundCounter, List<String> resultList) {
        List<CircleColor> properColors = new ArrayList<>();

        String result = resultList.get(roundCounter);
        for (int j = 0; j < result.length(); j++) {
            if (result.charAt(j) == 'B') {
                properColors.add(CircleColor.BLACK);
            } else if (result.charAt(j) == 'W') {
                properColors.add(CircleColor.WHITE);
            }
        }

        System.out.println("WINNER");
        if (result == "BBBB") {
            Thread.interrupted();
        }

        for (int i = 0; i < properColors.size(); i++) {
            setCircleColor(resultCircles, 5 - roundCounter, i, properColors.get(i));
        }
        System.out.println("START");
        System.out.println(properColors);
        System.out.println("STOP");
    }

    /**
     * Method to fill indicated circles
     * @param tab matrix of circles
     * @param row number of row
     * @param column number of column
     * @param circleColor color which to be circle fill
     */
    public void setCircleColor(Circle[][] tab, int row, int column, CircleColor circleColor) {
        tab[row][column].setFill(circleColor.getColor());
    }

    /**
     * Method to paint circles for indicate round
     * @param round number of round
     * @param colors sequence od CircelColor to be paint
     */
    public void nextRound(int round, List<CircleColor[]> colors) {
        List<CircleColor> circleColors = Arrays.asList(colors.get(round));
        setCircleColor(gameCircles, 5 - round, 0, circleColors.get(0));
        setCircleColor(gameCircles, 5 - round, 1, circleColors.get(1));
        setCircleColor(gameCircles, 5 - round, 2, circleColors.get(2));
        setCircleColor(gameCircles, 5 - round, 3, circleColors.get(3));

    }

    /**
     * Restart game method
     */
    public void clearGame() {
        for (int i = 0; i < 6; i++) {
            setCircleColor(gameCircles, 5 - i, 0, CircleColor.INITIAL);
            setCircleColor(gameCircles, 5 - i, 1, CircleColor.INITIAL);
            setCircleColor(gameCircles, 5 - i, 2, CircleColor.INITIAL);
            setCircleColor(gameCircles, 5 - i, 3, CircleColor.INITIAL);

            setCircleColor(resultCircles, 5 - i, 0, CircleColor.INITIAL);
            setCircleColor(resultCircles, 5 - i, 1, CircleColor.INITIAL);
            setCircleColor(resultCircles, 5 - i, 2, CircleColor.INITIAL);
            setCircleColor(resultCircles, 5 - i, 3, CircleColor.INITIAL);
        }

    }

}
