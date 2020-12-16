package de.ir31k0.nonogramsolver.algorithm.impl;

import de.ir31k0.nonogramsolver.AbstractNonogramSolverTest;
import de.ir31k0.nonogramsolver.algorithm.Algorithm;
import de.ir31k0.nonogramsolver.data.Field;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class NoOtherWayFillAlgorithmTest extends AbstractNonogramSolverTest {

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
        Field[] line = createLineByPattern("_ _ _ X _ _ ■ _ X _");
        Field[] expect = createLineByPattern("_ _ _ X _ _ ■ _ X _");
        algorithm.runOverLine(line, 3, 1, 1);
        assertArrayEquals(expect, line);
    }



}
