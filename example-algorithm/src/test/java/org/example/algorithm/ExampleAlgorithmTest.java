package org.example.algorithm;

import de.ir31k0.nonogramsolver.api.algorithm.Algorithm;
import de.ir31k0.nonogramsolver.api.data.Field;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ExampleAlgorithmTest {
    private final Algorithm algorithm = new ExampleAlgorithm();

    @Test
    public void noChangeOnUnknownLine() {
        Field[] line = new Field[] { Field.UNKNOWN };
        Field[] expect = new Field[] { Field.UNKNOWN };
        algorithm.runOverLine(line, 1);
        assertArrayEquals(expect, line);
    }
}
