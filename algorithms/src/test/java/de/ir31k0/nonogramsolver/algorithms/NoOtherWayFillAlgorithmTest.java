package de.ir31k0.nonogramsolver.algorithms;

import de.ir31k0.nonogramsolver.api.algorithm.Algorithm;
import de.ir31k0.nonogramsolver.api.data.Field;
import de.ir31k0.nonogramsolver.api.util.SolverUtil;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class NoOtherWayFillAlgorithmTest {

    private final Algorithm algorithm = new NoOtherWayFillAlgorithm();

    @Test
    public void noChange() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        algorithm.runOverLine(line, Collections.singletonList(2));
        assertArrayEquals(expect, line);
    }

    @Test
    public void canFillFullNumberOnBothSides() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.FILLED, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.FILLED, Field.FILLED, Field.FILLED, Field.FILLED};
        algorithm.runOverLine(line, Collections.singletonList(4));
        assertArrayEquals(expect, line);
    }

    @Test
    public void noFillBecauseOtherOnLeft() {
        Field[] line = new Field[] {Field.FILLED, Field.UNKNOWN, Field.FILLED, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.FILLED, Field.UNKNOWN, Field.FILLED, Field.UNKNOWN};
        algorithm.runOverLine(line, Arrays.asList(1, 2));
        assertArrayEquals(expect, line);
    }

    @Test
    public void noFillBecauseOtherOnRight() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.FILLED, Field.UNKNOWN, Field.FILLED};
        Field[] expect = new Field[] {Field.UNKNOWN, Field.FILLED, Field.UNKNOWN, Field.FILLED};
        algorithm.runOverLine(line, Arrays.asList(2, 1));
        assertArrayEquals(expect, line);
    }

    @Test
    public void multipleNumbers() {
        Field[] line = new Field[] {Field.FILLED, Field.UNKNOWN, Field.EMPTY, Field.UNKNOWN, Field.FILLED, Field.EMPTY, Field.UNKNOWN, Field.FILLED, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.FILLED, Field.FILLED, Field.EMPTY, Field.FILLED, Field.FILLED, Field.EMPTY, Field.FILLED, Field.FILLED, Field.FILLED};
        algorithm.runOverLine(line, Arrays.asList(2, 2, 3));
        assertArrayEquals(expect, line);
    }

    @Test
    public void fromTheHorse() {
        Field[] input = SolverUtil.createLineByPattern("_ _ _ X _ _ ■ _ X _");
        Field[] expect = SolverUtil.createLineByPattern("■ ■ ■ X _ _ ■ _ X _");
        Logger.info("Input: {}", SolverUtil.createPattern(input));
        algorithm.runOverLine(input, 3, 1, 1);
        Logger.info("Output: ", SolverUtil.createPattern(input));
        Logger.info("Expected: {}", SolverUtil.createPattern(expect));
        assertArrayEquals(expect, input);
    }
}
