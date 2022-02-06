package de.ir31k0.nonogramsolver.core;

import de.ir31k0.nonogramsolver.api.algorithm.Algorithm;
import de.ir31k0.nonogramsolver.api.data.Field;
import de.ir31k0.nonogramsolver.api.data.Nonogram;
import de.ir31k0.nonogramsolver.api.exception.IllegalFieldException;
import de.ir31k0.nonogramsolver.core.algorithm.AlgorithmLoader;
import org.tinylog.Logger;

/**
 * The nonogram solver base code class.
 */
public class NonogramSolver {
    /** Instance of algorithm loader */
    private final AlgorithmLoader algorithmLoader = AlgorithmLoader.getInstance();

    /**
     * Tries to solve the nonogram.
     *
     * @throws IllegalFieldException if a field conflict occurs
     */
    public void solve(final Nonogram nonogram) {
        tryExecuteAlgorithms(algorithmLoader.getOneTimeAlgorithms(), nonogram);
        while (tryExecuteAlgorithms(algorithmLoader.getMultipleTimeAlgorithms(), nonogram));
    }

    /**
     * Executes the given collection of algorithms once with each line (horizontal and vertical).
     *
     * @param algorithms collection of algorithms
     * @return true if one algorithm made a change, otherwise false
     * @throws IllegalFieldException if a field conflict occurs
     */
    private static boolean tryExecuteAlgorithms(final Iterable<? extends Algorithm> algorithms, final Nonogram nonogram) throws IllegalFieldException {
        Logger.debug("horizontal");
        boolean change = false;
        for (int y = 0; y < nonogram.getHeight(); y++) {
            final Field[] horizontalLine = nonogram.getHorizontalLine(y);
            for (final Algorithm algorithm : algorithms) {
                algorithm.runOverLine(horizontalLine, nonogram.getHorizontalLineNumbers(y));
                if (nonogram.updateHorizontalLine(y, horizontalLine)) {
                    change = true;
                    Logger.debug(algorithm.getClass().getSimpleName());
                    Logger.debug(nonogram::toString);
                }
            }
        }

        Logger.debug("vertical");
        for (int x = 0; x < nonogram.getWidth(); x++) {
            final Field[] verticalLine = nonogram.getVerticalLine(x);
            for (final Algorithm algorithm : algorithms) {
                Logger.debug(algorithm.getClass().getSimpleName());
                algorithm.runOverLine(verticalLine, nonogram.getVerticalLineNumbers(x));
                if (nonogram.updateVerticalLine(x, verticalLine)) {
                    change = true;
                    Logger.debug(algorithm.getClass().getSimpleName());
                    Logger.debug(nonogram::toString);
                }
            }
        }
        return change;
    }
}
