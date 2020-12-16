package de.ir31k0.nonogramsolver.algorithm.impl;

import de.ir31k0.nonogramsolver.algorithm.Algorithm;
import de.ir31k0.nonogramsolver.data.Field;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class EmptyFieldMarkerIfCompleteAlgorithmTest {

    private final Algorithm algorithm = new EmptyFieldMarkerIfAllNumbersKnownAlgorithm();

    @Test
    public void emptyLine() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        algorithm.runOverLine(line, Collections.singletonList(1));
        assertArrayEquals(expect, line);
    }

    @Test
    public void oneFieldIsMissingForCompletion() {
        Field[] line = new Field[] {Field.FILLED, Field.FILLED, Field.UNKNOWN, Field.FILLED, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.FILLED, Field.FILLED, Field.UNKNOWN, Field.FILLED, Field.UNKNOWN};
        algorithm.runOverLine(line, Arrays.asList(2, 2));
        assertArrayEquals(expect, line);
    }

    @Test
    public void completeWithOneNumber() {
        Field[] line = new Field[] {Field.FILLED, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.FILLED, Field.EMPTY, Field.EMPTY};
        algorithm.runOverLine(line, Collections.singletonList(1));
        assertArrayEquals(expect, line);
    }

    @Test
    public void completeWithMultipleNumbers() {
        Field[] line = new Field[] {Field.FILLED, Field.FILLED, Field.UNKNOWN, Field.FILLED, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.FILLED, Field.FILLED, Field.EMPTY, Field.FILLED, Field.EMPTY};
        algorithm.runOverLine(line, new ArrayList<>(Arrays.asList(2, 1)));
        assertArrayEquals(expect, line);
    }
}
