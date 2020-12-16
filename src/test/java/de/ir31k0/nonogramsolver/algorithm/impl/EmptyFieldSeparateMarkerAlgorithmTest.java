package de.ir31k0.nonogramsolver.algorithm.impl;

import de.ir31k0.nonogramsolver.algorithm.Algorithm;
import de.ir31k0.nonogramsolver.data.Field;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class EmptyFieldSeparateMarkerAlgorithmTest {

    private final Algorithm algorithm = new EmptyFieldSeparateMarkerAlgorithm();

    @Test
    public void noChange() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        algorithm.runOverLine(line, Collections.singletonList(1));
        assertArrayEquals(expect, line);
    }

    @Test
    public void markEmptyFieldsOfNumberOne() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.FILLED, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.EMPTY, Field.FILLED, Field.EMPTY, Field.UNKNOWN};
        algorithm.runOverLine(line, Collections.singletonList(1));
        assertArrayEquals(expect, line);
    }

    @Test
    public void markEmptyFieldsOfNumberTwo() {
        Field[] line = new Field[] {Field.FILLED, Field.FILLED, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.FILLED, Field.FILLED, Field.EMPTY, Field.UNKNOWN};
        algorithm.runOverLine(line, Collections.singletonList(2));
        assertArrayEquals(expect, line);
    }

    @Test
    public void rowWithNoEmptyFields() {
        Field[] line = new Field[] {Field.FILLED, Field.FILLED};
        Field[] expect = new Field[] {Field.FILLED, Field.FILLED};
        algorithm.runOverLine(line, Collections.singletonList(2));
        assertArrayEquals(expect, line);
    }

    @Test
    public void multipleNumbersEasy() {
        Field[] line = new Field[] {Field.FILLED, Field.UNKNOWN, Field.UNKNOWN, Field.FILLED, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.FILLED, Field.EMPTY, Field.EMPTY, Field.FILLED, Field.EMPTY};
        algorithm.runOverLine(line, new ArrayList<>(Arrays.asList(1, 1)));
        assertArrayEquals(expect, line);
    }


    @Test
    public void multipleNumbersHard() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.FILLED, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.FILLED, Field.UNKNOWN};
        algorithm.runOverLine(line, new ArrayList<>(Arrays.asList(1, 3)));
        assertArrayEquals(expect, line);
    }
}
