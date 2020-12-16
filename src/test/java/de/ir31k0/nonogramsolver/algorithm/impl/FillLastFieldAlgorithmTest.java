package de.ir31k0.nonogramsolver.algorithm.impl;

import de.ir31k0.nonogramsolver.algorithm.Algorithm;
import de.ir31k0.nonogramsolver.data.Field;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class FillLastFieldAlgorithmTest {

    private final Algorithm algorithm = new FillLastFieldAlgorithm();

    @Test
    public void noChange() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};

        algorithm.runOverLine(line, Collections.singletonList(1));
        Field[] expect = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillFullRow() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.FILLED, Field.FILLED, Field.FILLED};
        algorithm.runOverLine(line, Collections.singletonList(3));
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillLastFields() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.FILLED, Field.EMPTY, Field.FILLED};
        Field[] expect = new Field[] {Field.FILLED, Field.FILLED, Field.FILLED, Field.EMPTY, Field.FILLED};
        algorithm.runOverLine(line, Arrays.asList(3, 1));
        assertArrayEquals(expect, line);
    }
}
