package de.ir31k0.nonogramsolver.algorithms;

import de.ir31k0.nonogramsolver.api.algorithm.Algorithm;
import de.ir31k0.nonogramsolver.api.data.Field;
import de.ir31k0.nonogramsolver.api.util.SolverUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class OverlapAlgorithmTest {

    private final Algorithm algorithm = new OverlapAlgorithm();

    @Test
    public void noChangeOnUnknownLine() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        algorithm.runOverLine(line, 2);
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillFullNumber() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.FILLED, Field.FILLED, Field.FILLED, Field.FILLED};
        algorithm.runOverLine(line, 4);
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillFullMultipleNumbers() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.FILLED, Field.FILLED, Field.FILLED, Field.UNKNOWN, Field.FILLED, Field.FILLED};
        algorithm.runOverLine(line, 3, 2);
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillPartOfNumberOnUnknownLine() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.UNKNOWN, Field.FILLED, Field.UNKNOWN};
        algorithm.runOverLine(line, 2);
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillPartsOfNumbersOnUnknownLine() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN};
        Field[] expect = new Field[] {Field.UNKNOWN, Field.FILLED, Field.FILLED, Field.UNKNOWN, Field.UNKNOWN, Field.FILLED, Field.FILLED, Field.UNKNOWN};
        algorithm.runOverLine(line, 3, 3);
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillPartOfNumberWithEmptyField() {
        Field[] line = new Field[] {Field.UNKNOWN, Field.UNKNOWN, Field.UNKNOWN, Field.EMPTY};
        Field[] expect = new Field[] {Field.UNKNOWN, Field.FILLED, Field.UNKNOWN, Field.EMPTY};
        algorithm.runOverLine(line, 2);
        assertArrayEquals(expect, line);
    }

    @Test
    public void fillPartsOfNumbersWithEmptyFields() {
        Field[] line = SolverUtil.createLineByPattern(" | | |X| | | | |X");
        Field[] expect = SolverUtil.createLineByPattern(" |F| |X| |F|F| |X");
        algorithm.runOverLine(line, 2, 3);
        assertArrayEquals(expect, line);
    }
}
