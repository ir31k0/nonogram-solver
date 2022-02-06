package de.ir31k0.nonogramsolver.algorithms;

import de.ir31k0.nonogramsolver.api.algorithm.OneTimeAlgorithm;
import de.ir31k0.nonogramsolver.api.data.Field;

import java.util.List;

/**
 * If a number is greater than half the line, the middle part can be filled. Because if you take the farthest left and the farthest right option, they overlap in the middle. So they must be filled.
 *
 * Example:
 * 6 | | | | | | | | | | | -> | | | | |F|F| | | | |
 */
public class OverHalfAlgorithm implements OneTimeAlgorithm {

    public Field[] runOverLine(Field[] line, List<Integer> numbers) {
        int size = line.length;
        int halfSize = size / 2;
        int number = getOverHalfNumber(numbers, halfSize);

        if (number > 0) {
            int start = halfSize - (number - halfSize);
            int end = halfSize + (number - halfSize);

            if (size % 2 == 1) {
                start++;
            }

            for (int i = start; i < end; i++) {
                line[i] = Field.FILLED;
            }
        }
        return line;
    }

    private int getOverHalfNumber(List<Integer> numbers, int halfSize) {
        for (Integer number : numbers) {
            if (number > halfSize) {
                return number;
            }
        }
        return -1;
    }
}
