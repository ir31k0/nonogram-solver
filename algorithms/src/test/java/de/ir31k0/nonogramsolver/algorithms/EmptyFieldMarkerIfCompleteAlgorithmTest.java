package de.ir31k0.nonogramsolver.algorithms;

import de.ir31k0.nonogramsolver.api.algorithm.Algorithm;
import de.ir31k0.nonogramsolver.api.data.Field;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class EmptyFieldMarkerIfCompleteAlgorithmTest {

    private final Algorithm algorithm = new EmptyFieldMarkerIfAllNumbersKnownAlgorithm();

    @Test
    void emptyLine() {
        final Field[] line = { Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN };

        algorithm.runOverLine(line, Collections.singletonList(1));

        final Field[] expect = { Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN };
        assertArrayEquals(expect, line);
    }

    @Test
    void oneFieldIsMissingForCompletion() {
        final Field[] line = { Field.FILLED, Field.FILLED, Field.UNKNOWN, Field.FILLED, Field.UNKNOWN };

        algorithm.runOverLine(line, Arrays.asList(2, 2));

        final Field[] expect = { Field.FILLED, Field.FILLED, Field.UNKNOWN, Field.FILLED, Field.UNKNOWN };
        assertArrayEquals(expect, line);
    }

    @Test
    void completeWithOneNumber() {
        final Field[] line = { Field.FILLED, Field.UNKNOWN, Field.UNKNOWN };

        algorithm.runOverLine(line, Collections.singletonList(1));

        final Field[] expect = { Field.FILLED, Field.EMPTY, Field.EMPTY };
        assertArrayEquals(expect, line);
    }

    @Test
    void completeWithMultipleNumbers() {
        final Field[] line = { Field.FILLED, Field.FILLED, Field.UNKNOWN, Field.FILLED, Field.UNKNOWN };

        algorithm.runOverLine(line, new ArrayList<>(Arrays.asList(2, 1)));

        final Field[] expect = { Field.FILLED, Field.FILLED, Field.EMPTY, Field.FILLED, Field.EMPTY };
        assertArrayEquals(expect, line);
    }
}
