package de.ir31k0.nonogramsolver.algorithm.impl;

import de.ir31k0.nonogramsolver.algorithm.MultipleTimeAlgorithm;
import de.ir31k0.nonogramsolver.data.Field;

import java.util.List;

/**
 * If the sum of all numbers plus all empty fields is equal to the line length, then all unknown fields can be filled.
 *
 * Example:
 * 3 | | | | -> |F|F|F|
 * 2 |X| | | -> |X|F|F|
 */
public class FillLastFieldAlgorithm implements MultipleTimeAlgorithm {
    @Override
    public Field[] runOverLine(Field[] line, List<Integer> numbers) {
        int size = line.length;
        int sum = sum(numbers);
        int emptyFieldCount = count(line, Field.EMPTY);
        if (size - sum - emptyFieldCount == 0) {
            for (int i = 0; i < size; i++) {
                if (line[i] == Field.UNKNOWN) {
                    line[i] = Field.FILLED;
                }
            }
        }
        return line;
    }
}
