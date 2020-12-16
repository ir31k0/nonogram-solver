package de.ir31k0.nonogramsolver.algorithm.impl;

import de.ir31k0.nonogramsolver.algorithm.Algorithm;
import de.ir31k0.nonogramsolver.data.Field;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class FromTheEdgeFillAlgorithmTest {

    private final Algorithm algorithm = new FillFromTheEdgeAlgorithm();

    @Test
    public void noChange() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.FILLED, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.UNKNOWN, Field.FILLED, Field.UNKNOWN};
        algorithm.runOverLine(line, Collections.singletonList(3));
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillFromLeftEdge() {
        Field[] line = new Field[] {Field.FILLED, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.FILLED, Field.FILLED, Field.EMPTY};
        algorithm.runOverLine(line, Collections.singletonList(2));
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillFromRightEdge() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.FILLED};
        Field[] expect = new Field[] {Field.EMPTY, Field.FILLED, Field.FILLED};
        algorithm.runOverLine(line, Collections.singletonList(2));
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillFromBothEdges() {
        Field[] line = new Field[] {Field.FILLED, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.FILLED};
        Field[] expect = new Field[] {Field.FILLED, Field.FILLED, Field.EMPTY, Field.FILLED, Field.FILLED};
        algorithm.runOverLine(line, Arrays.asList(2, 2));
        assertArrayEquals(expect, line);
    }
}
