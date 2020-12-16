package de.ir31k0.nonogramsolver.algorithm.impl;

import de.ir31k0.nonogramsolver.algorithm.Algorithm;
import de.ir31k0.nonogramsolver.data.Field;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class OverHalfAlgorithmTest {

    private final Algorithm algorithm = new OverHalfAlgorithm();

    @Test
    public void noChange() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        algorithm.runOverLine(line, Collections.singletonList(2));
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillFullRowEvenNumber() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.FILLED, Field.FILLED};
        algorithm.runOverLine(line, Collections.singletonList(2));
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillFullRowOddNumber() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.FILLED, Field.FILLED, Field.FILLED};
        algorithm.runOverLine(line, Collections.singletonList(3));
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillRowEvenNumber() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.FILLED, Field.FILLED, Field.UNKNOWN, Field.UNKNOWN};
        algorithm.runOverLine(line, Collections.singletonList(4));
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillRowOddNumber() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.UNKNOWN, Field.FILLED, Field.FILLED, Field.UNKNOWN};
        algorithm.runOverLine(line, Collections.singletonList(3));
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillRowOddBoard() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.UNKNOWN, Field.FILLED, Field.UNKNOWN};
        algorithm.runOverLine(line, Collections.singletonList(2));
        assertArrayEquals(expect, line);
    }
}
