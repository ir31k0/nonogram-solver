package de.ir31k0.nonogramsolver;

import de.ir31k0.nonogramsolver.algorithm.Algorithm;
import de.ir31k0.nonogramsolver.algorithm.AlgorithmLoader;
import de.ir31k0.nonogramsolver.data.Field;
import de.ir31k0.nonogramsolver.data.Nonogram;
import de.ir31k0.nonogramsolver.exception.IllegalFieldException;

import java.util.Collection;

/**
 * The nonogram solver base code class.
 */
public class NonogramSolver {

    /** The nonogram to solve */
    private final Nonogram nonogram;

    /** Instance of algorithm loader */
    private final AlgorithmLoader algorithmLoader;

    public NonogramSolver(Nonogram nonogram) {
        this.nonogram = nonogram;
        algorithmLoader = AlgorithmLoader.getInstance();
    }

    /**
     * Runs the nonogram solver.
     *
     * @return the solved board
     * @throws IllegalFieldException if a field conflict occurs
     */
    public Field[][] run() throws IllegalFieldException {
        executeAlgorithms(algorithmLoader.getOneTimeAlgorithms());
        while (executeAlgorithms(algorithmLoader.getMultipleTimeAlgorithms()));
        return nonogram.getBoard();
    }

    /**
     * Prints the board in the console.
     */
    public void printBoard() {
        for (int y = 0; y < nonogram.getHeight(); y++) {
            String[] line = new String[nonogram.getWidth()];
            for (int x = 0; x < nonogram.getWidth(); x++) {
                line[x] = nonogram.getField(x, y).getCharacter();
            }
            System.out.println(String.join(" ", line));
        }
    }

    /**
     * Executes the given collection of algorithms once with each line (horizontal and vertical).
     *
     * @param algorithms collection of algorithms
     * @return true if one algorithm made a change, otherwise false
     * @throws IllegalFieldException if a field conflict occurs
     */
    private boolean executeAlgorithms(Collection<? extends Algorithm> algorithms) throws IllegalFieldException {
        boolean change = false;
        System.out.println("horizontal");
        for (int y = 0; y < nonogram.getHeight(); y++) {
            Field[] horizontalLine = nonogram.getHorizontalLine(y);
            for (Algorithm algorithm : algorithms) {
                algorithm.runOverLine(horizontalLine, nonogram.getHorizontalLineNumbers(y));
                if (nonogram.updateHorizontalLine(y, horizontalLine)) {
                    change = true;
                    System.out.println(algorithm.getClass().getSimpleName());
                    printBoard();
                }
            }
        }

        System.out.println("vertical");
        for (int x = 0; x < nonogram.getWidth(); x++) {
            Field[] verticalLine = nonogram.getVerticalLine(x);
            for (Algorithm algorithm : algorithms) {
                System.out.println(algorithm.getClass().getSimpleName());
                algorithm.runOverLine(verticalLine, nonogram.getVerticalLineNumbers(x));
                if (nonogram.updateVerticalLine(x, verticalLine)) {
                    change = true;
                    printBoard();
                }
            }
        }
        return change;
    }
}
